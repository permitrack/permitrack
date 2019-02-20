package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.*;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectBmp;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.service.impl.InspectorServiceImpl;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.portal.resources.PortalResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class InspectionEditAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionEditAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            inspectionForm.reset();
            return mapping.findForward("inspection.view.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            LOG.error(PortalResources.getProperty("error.no.user.in.session"));
            addError(new ActionMessage("error.no.user.in.session"),
                     request);
            return mapping.findForward(CommonConstants.FORWARD_ERROR);
        }
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            return mapping.findForward("project.list.page");
        }
        EcProject
            project =
            new EcProject(projectValue.getId());
        try
        {
            if (!project.load())
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {projectValue.getId()};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        InspectionValue
            inspectionValue =
            (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                  request);
        if (inspectionValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.not.found.in.session"));
            addError(new ActionMessage("inspection.error.inspection.not.found.in.session"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        EcInspection
            inspection =
            new EcInspection();
        inspection.setId(inspectionValue.getId());
        try
        {
            if (!inspection.load())
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionValue.getId()};
            LOG.error(ApplicationResources.getProperty("inspection.error.loading.inspection",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("inspection.error.loading.inspection",
                                       parameters),
                     request);
            return (mapping.findForward("inspection.list.page"));
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (inspection.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE)
            && !securityManager.getIsSystemAdministrator())
        {
            addError(new ActionMessage("inspection.error.already.final"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        if (inspectionForm.getSubmit()
            != null
            && inspectionForm.getSubmit()
            .equalsIgnoreCase("Delete"))
        {
            //Make sure that this user has the authority to delete an inspection
            if (!securityManager.HasECPermission(SecureObjectPermissionData.INSPECTION_DELETE,
                                                 inspectionValue.getProjectId()))
            {
                LOG.error(ApplicationResources.getProperty("inspection.delete.no.access"));
                addError(new ActionMessage("inspection.delete.no.access"),
                         request);
                return mapping.findForward("inspection.list.page");
            }
            inspection.setInspector(InspectorServiceImpl.getInspectorFromForm(inspectionForm,
                                                                              userValue));
            inspection.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
            inspection.save(userValue);
            addMessage(new ActionMessage("inspection.delete.success"),
                       request);
            setSessionAttribute(SessionKeys.EC_INSPECTION,
                                null,
                                request);
            return mapping.findForward("inspection.list.page");
        }
        try
        {
            inspection.setInspectionDate(inspectionForm.getInspectionDate());
            Integer
                hours =
                inspectionForm.getHours();
            Integer
                minutes =
                inspectionForm.getMinutes();
            if (hours
                == null
                || hours
                   == 0
                || minutes
                   == null)
            {
                hours =
                    null;
                minutes =
                    null;
            }
            else if (hours
                     != 0
                     && minutes
                        == 0)
            {
                minutes =
                    0;
            }
            inspection.setHours(hours);
            inspection.setMinutes(minutes);
            inspection.setTimePeriod(inspectionForm.getTimePeriod());
            inspection.setWeatherTrends(inspectionForm.getWeatherTrends());
            inspection.setTemperature(inspectionForm.getTemperature());
            inspection.setComment(inspectionForm.getComment());
            inspection.setPrecipEndDate(inspectionForm.getPrecipEndDate());
            inspection.setPrecipAmount(inspectionForm.getPrecipAmount());
            if (inspectionForm.getPrecipSource()
                .equalsIgnoreCase("Other"))
            {
                inspection.setPrecipSource(inspectionForm.getPrecipSourceOther());
            }
            else
            {
                inspection.setPrecipSource(inspectionForm.getPrecipSource());
            }
            inspection.setInspectionActionComment(inspectionForm.getInspectionActionComment());
            inspection.setInspectionAction((EcInspectionAction) HibernateUtil.load(EcInspectionAction.class,
                                                                                   inspectionForm.getInspectionAction()
                                                                                       .getId()));
            inspection.setInspectionReason((EcInspectionReason) HibernateUtil.load(EcInspectionReason.class,
                                                                                   inspectionForm.getInspectionReason()
                                                                                       .getId()));
            inspection.setStatusCode(inspectionForm.getStatusCode());
            inspection.setInspector(InspectorServiceImpl.getInspectorFromForm(inspectionForm,
                                                                              userValue));
            inspection.update(userValue);
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("error.inspection.update.failed"));
            LOG.error("Project ID = "
                      + projectValue.getId()
                      + " Inspection ID = "
                      + inspectionValue.getId());
            LOG.error(e.getMessage());
            addError(new ActionMessage("error.inspection.update.failed"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        try
        {
            inspection.load();
        }
        catch (Exception el)
        {
            LOG.error(ApplicationResources.getProperty("error.inspection.load.failed"));
            LOG.error("Project ID = "
                      + projectValue.getId()
                      + " Inspection ID = "
                      + inspectionValue.getId());
            LOG.error(el.getMessage());
            addError(new ActionMessage("error.inspection.load.failed"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        boolean
            isMostRecentInspection =
            false;
        List
            mostRecentInspectionList =
            EcInspection.findMostRecentActiveByProjectId(projectValue.getId());
        if (mostRecentInspectionList
            != null
            && !mostRecentInspectionList.isEmpty())
        {
            EcInspection
                mostRecentInspection =
                (EcInspection) mostRecentInspectionList.get(0);
            if (mostRecentInspection.getInspectionDate()
                    .compareTo(inspection.getInspectionDate())
                <= 0)
            {
                isMostRecentInspection =
                    true;
            }
        }
        else
        {
            isMostRecentInspection =
                true;
        }
        //If this is the most recent inspection, update the project with the last inspection date
        //Also, check to see if the project status is AUTO_ACTIVATE.  If so, set the project
        //status to ACTIVE upon the first inspection.
        if (isMostRecentInspection)
        {
            try
            {
                project.load();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {projectValue.getId()};
                LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("project.error.loadingProject",
                                           parameters),
                         request);
                return mapping.findForward("inspection.list.page");
            }
            project.setLastInspectionDate(inspection.getInspectionDate());
            if (project.getProjectStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_AUTO_ACTIVATE))
            {
                project.setProjectStatus(StatusCodeData.STATUS_CODE_ACTIVE);
            }
            try
            {
                project.save(userValue);
                setSessionAttribute(SessionKeys.EC_RESET_PROJECT_LIST,
                                    "true",
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {project.getId()};
                LOG.error(ApplicationResources.getProperty("project.error.project.save.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("project.error.project.save.failed",
                                           parameters),
                         request);
            }
        }
        InspectionDocumentList
            inspectionDocumentList =
            inspectionForm.getDocuments();
        ArrayList<InspectionDocumentValue>
            inspectionDocumentArray =
            processInspectionDocumentList(inspectionDocumentList);
        for (InspectionDocumentValue inspectionDocumentValue : inspectionDocumentArray)
        {
            if (inspectionDocumentValue.getFormFile()
                != null)
            {
                LOG.debug("Going to update the document: "
                          + inspectionDocumentValue.getFormFile()
                    .getFileName());
            }
            inspectionDocumentValue.setInspectionId(inspection.getId());
            try
            {
                processInspectionDocument(inspectionDocumentValue,
                                          projectValue.getOwnerClientId(),
                                          projectValue.getId(),
                                          inspection.getId());
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {inspectionDocumentValue.getName()};
                LOG.error(ApplicationResources.getProperty("inspection.error.document.save.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("inspection.error.document.save.failed",
                                           parameters),
                         request);
                return mapping.findForward("inspection.list.page");
            }
        }
        try
        {
            Hashtable
                inspectionBmpList =
                inspectionForm.getBmps()
                    .getAllBmps();
            for (
                int
                    inspectionBmpListIndex =
                    0; inspectionBmpListIndex
                       < BMP_MAX; inspectionBmpListIndex++)
            {
                if (inspectionBmpList.containsKey(new Integer(inspectionBmpListIndex)))
                {
                    InspectionBmpValue
                        inspectionBmpValue =
                        (InspectionBmpValue) inspectionBmpList.get(new Integer(inspectionBmpListIndex));
                    if (inspectionBmpValue.getIsDeleted())
                    {
                        if (inspectionBmpValue.getId()
                            != null
                            && inspectionBmpValue.getId()
                               > 0)
                        {
                            try
                            {
                                EcInspectionBmp
                                    deleteInspectionBmp =
                                    new EcInspectionBmp();
                                deleteInspectionBmp.setId(inspectionBmpValue.getId());
                                if (deleteInspectionBmp.load())
                                {
                                    deleteInspectionBmp.delete();
                                }
                            }
                            catch (Exception e)
                            {
                                Object[]
                                    parameters =
                                    {inspectionBmpValue.getId()};
                                LOG.error(ApplicationResources.getProperty("inspection.bmp.error.could.not.delete",
                                                                           parameters));
                                LOG.error(e.getMessage());
                            }
                        }
                        continue;
                    }
                    // Skip this bmp if the required fields were not entered
                    if (StringUtil.isEmpty(inspectionBmpValue.getBmpCategoryName())
                        || StringUtil.isEmpty(inspectionBmpValue.getName()))
                    {
                        continue;
                    }
                    // Load the inspection Bmp if it already exists
                    EcInspectionBmp
                        inspectionBmp =
                        new EcInspectionBmp();
                    if (inspectionBmpValue.getId()
                        != null
                        && inspectionBmpValue.getId()
                           > 0)
                    {
                        inspectionBmp.setId(inspectionBmpValue.getId());
                        inspectionBmp.load();
                    }
                    // Load the Project BMP associated with this Inspection BMP
                    // If it is found, then use it's values to set ProjectBmpId, description, and isRequired
                    // Otherwise, use the values coming in from the form.  The Else condition here would
                    // represent the situation where a user started an inspection and someone else deleted the
                    // BMP from the project before the user submitted the inspection.
                    EcProjectBmp
                        projectBmp =
                        null;
                    if (inspectionBmpValue.getProjectBmpId()
                        != null
                        && inspectionBmpValue.getProjectBmpId()
                           > 0)
                    {
                        projectBmp =
                            new EcProjectBmp();
                        projectBmp.setId(inspectionBmpValue.getProjectBmpId());
                        if (projectBmp.load())
                        {
                            inspectionBmp.setProjectBmpId(projectBmp.getId());
                            inspectionBmp.setBmpName(inspectionBmpValue.getName());
                            inspectionBmp.setBmpCategoryName(inspectionBmpValue.getBmpCategoryName());
                            inspectionBmp.setDescription(inspectionBmpValue.getDescription());
                            inspectionBmp.setIsRequired(projectBmp.getIsRequired());
                        }
                        else
                        {
                            projectBmp =
                                null;  //Set the projectBmp to null so that we know that the Project BMP was not loaded
                            inspectionBmp.setBmpName(inspectionBmpValue.getName());
                            inspectionBmp.setBmpCategoryName(inspectionBmpValue.getBmpCategoryName());
                            inspectionBmp.setDescription(inspectionBmpValue.getDescription());
                            inspectionBmp.setIsRequired(inspectionBmpValue.getIsRequired());
                        }
                    }
                    else
                    {
                        inspectionBmp.setBmpName(inspectionBmpValue.getName());
                        inspectionBmp.setBmpCategoryName(inspectionBmpValue.getBmpCategoryName());
                        inspectionBmp.setDescription(inspectionBmpValue.getDescription());
                        inspectionBmp.setIsRequired(inspectionBmpValue.getIsRequired());
                    }
                    inspectionBmp.setInspectionId(inspection.getId());
                    inspectionBmp.setComment(inspectionBmpValue.getComment());
                    inspectionBmp.setIsInspected(inspectionBmpValue.getIsInspected());
                    if (inspectionBmpValue.getBmpCondition()
                        != null
                        && inspectionBmpValue.getBmpCondition()
                               .getId()
                           > 0)
                    {
                        inspectionBmp.setInspectionBmpCondition((EcInspectionBmpCondition) HibernateUtil.load(EcInspectionBmpCondition.class,
                                                                                                              inspectionBmpValue.getBmpCondition()
                                                                                                                  .getId()));
                    }
                    if (inspectionBmpValue.getBmpStatus()
                        != null
                        && inspectionBmpValue.getBmpStatus()
                               .getId()
                           > 0)
                    {
                        inspectionBmp.setInspectionBmpStatus((EcInspectionBmpStatus) HibernateUtil.load(EcInspectionBmpStatus.class,
                                                                                                        inspectionBmpValue.getBmpStatus()
                                                                                                            .getId()));
                    }
                    inspectionBmp.save();
                    LOG.debug("After save: inspectionBmpId="
                              + inspectionBmp.getId());
                    InspectionBmpDocumentValue
                        inspectionBmpDocumentValue =
                        inspectionBmpValue.getBmpDocument();
                    if (inspectionBmpDocumentValue.getFormFile()
                        != null
                        && inspectionBmpDocumentValue.getFormFile()
                               .getFileName()
                           != null
                        && inspectionBmpDocumentValue.getFormFile()
                               .getFileName()
                               .trim()
                               .length()
                           > 0)
                    {
                        LOG.debug("Going to update photo:  "
                                  + inspectionBmpDocumentValue.getFormFile()
                            .getFileName());
                        inspectionBmpDocumentValue.setInspectionBmpId(inspectionBmp.getId());
                        if (inspectionBmpDocumentValue.getFormFile()
                                .getFileSize()
                            == 0)
                        {
                            Object[]
                                parameters =
                                {inspectionBmpDocumentValue.formFileNiceToString()};
                            LOG.error(ApplicationResources.getProperty("inspection.error.document.save.failed.file.size",
                                                                       parameters));
                            LOG.error("Error: "
                                      + inspectionBmpDocumentValue.getFormFile()
                                .getFileName()
                                      + " file size is 0.");
                            addError(new ActionMessage("inspection.error.document.save.failed.file.size",
                                                       parameters),
                                     request);
                            return mapping.findForward("inspection.list.page");
                        }
                        try
                        {
                            processInspectionBmpDocument(inspectionBmpDocumentValue,
                                                         projectValue.getOwnerClientId(),
                                                         projectValue.getId(),
                                                         inspectionValue.getId());
                        }
                        catch (Exception e)
                        {
                            Object[]
                                parameters =
                                {inspectionBmpDocumentValue.toString()};
                            LOG.error(ApplicationResources.getProperty("inspection.error.document.save.failed",
                                                                       parameters));
                            LOG.error(e.getMessage());
                            addError(new ActionMessage("inspection.error.document.save.failed",
                                                       parameters),
                                     request);
                            return mapping.findForward("inspection.list.page");
                        }
                    }
                    //Update the projectBmp with the current status and condition
                    if (inspection.getStatus()
                            .getCode()
                            .equals(StatusCodeData.STATUS_CODE_ACTIVE)
                        && isMostRecentInspection
                        && projectBmp
                           != null)
                    {
                        LOG.debug("updating projectBmp status and condition for projectBmpId="
                                  + inspectionBmpValue.getProjectBmpId());
                        projectBmp.setInspectionBmpCondition(inspectionBmp.getInspectionBmpCondition());
                        projectBmp.setInspectionBmpStatus(inspectionBmp.getInspectionBmpStatus());
                        projectBmp.save(userValue);
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("error.inspection.update.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("error.inspection.update.failed"),
                     request);
            return mapping.findForward("inspection.view.page");
        }
        addMessage(new ActionMessage("inspection.update.success"),
                   request);
        setSessionAttribute(SessionKeys.EC_INSPECTION,
                            new InspectionValue(inspection),
                            request);
        if (inspection.getStatus()
            .getCode()
            .equals(StatusCodeData.STATUS_CODE_ACTIVE))
        {
            return mapping.findForward("inspection.email.page");
        }
        return mapping.findForward("continue");
    }

    private ArrayList<InspectionDocumentValue> processInspectionDocumentList(InspectionDocumentList list)
    {
        ArrayList<InspectionDocumentValue>
            inspectionDocArray =
            new ArrayList<InspectionDocumentValue>();
        if (list.getFormFile0()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile0());
            docValue.setId(list.getId0());
            docValue.setIsDeleted(list.getDelete0());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile1()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile1());
            docValue.setId(list.getId1());
            docValue.setIsDeleted(list.getDelete1());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile2()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile2());
            docValue.setId(list.getId2());
            docValue.setIsDeleted(list.getDelete2());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile3()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile3());
            docValue.setId(list.getId3());
            docValue.setIsDeleted(list.getDelete3());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile4()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile4());
            docValue.setId(list.getId4());
            docValue.setIsDeleted(list.getDelete4());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile5()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile5());
            docValue.setId(list.getId5());
            docValue.setIsDeleted(list.getDelete5());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile6()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile6());
            docValue.setId(list.getId6());
            docValue.setIsDeleted(list.getDelete6());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile7()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile7());
            docValue.setId(list.getId7());
            docValue.setIsDeleted(list.getDelete7());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile8()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile8());
            docValue.setId(list.getId8());
            docValue.setIsDeleted(list.getDelete8());
            inspectionDocArray.add(docValue);
        }
        if (list.getFormFile9()
            != null)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(list.getFormFile9());
            docValue.setId(list.getId9());
            docValue.setIsDeleted(list.getDelete9());
            inspectionDocArray.add(docValue);
        }
        return inspectionDocArray;
    }
}
