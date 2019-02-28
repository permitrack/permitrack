package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionDocument;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.*;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InspectionEditPageAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionEditPageAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        inspectionForm.reset();
        LOG.info("In InspectionEditPageAction.");
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
        UserData
            user =
            UserService.getUser(userValue.getId());
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        Integer
            inspectionId =
            null;
        if (request.getParameter(RequestKeys.EC_INSPECTION_ID)
            != null)
        {
            inspectionId =
                new Integer(request.getParameter(RequestKeys.EC_INSPECTION_ID));
        }
        else
        {
            InspectionValue
                inspectionValue =
                (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                      request);
            if (inspectionValue
                != null)
            {
                inspectionId =
                    inspectionValue.getId();
            }
        }
        if (inspectionId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.not.found.in.session"));
            addError(new ActionMessage("inspection.error.inspection.not.found.in.session"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        EcInspection
            inspection =
            new EcInspection(inspectionId);
        InspectionReportValue
            inspectionValue;
        try
        {
            if (!inspection.load())
            {
                throw new Exception();
            }
            inspectionValue =
                new InspectionReportValue(inspection);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionId};
            LOG.error(ApplicationResources.getProperty("inspection.error.loading.inspection",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("inspection.error.loading.inspection",
                                       parameters),
                     request);
            return (mapping.findForward("inspection.list.page"));
        }
        EcProject
            project =
            new EcProject(inspectionValue.getProjectId());
        ProjectValue
            projectValue;
        try
        {
            if (!project.load())
            {
                throw new Exception();
            }
            projectValue =
                ProjectService.getProjectValue(project,
                                               clientValue,
                                               userValue,
                                               securityManager);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionValue.getProjectId()};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        if (inspection.getStatus()
                .getCode()
                .equals(StatusCodeData.STATUS_CODE_ACTIVE)
            && !securityManager.getIsSystemAdministrator())
        {
            addError(new ActionMessage("inspection.error.already.final"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        securityManager.setProjectID(projectValue);
/*
        setSessionAttribute(SessionKeys.IS_PROJECT_PERMIT_AUTHORITY,
                            securityManager.getIsProjectPermitAuthority(),
                            request);
*/
        inspectionForm.setId(inspection.getId());
        inspectionForm.setProjectId(projectValue.getId());
        inspectionForm.setInspectionDate(inspection.getInspectionDate());
        inspectionForm.setHours(inspection.getHours());
        inspectionForm.setMinutes(inspection.getMinutes());
        inspectionForm.setTimePeriod(inspection.getTimePeriod());
        inspectionForm.setEnteredDate(inspection.getEnteredDate());
        inspectionForm.setWeatherTrends(inspection.getWeatherTrends());
        inspectionForm.setTemperature(inspection.getTemperature());
        inspectionForm.setComment(inspection.getComment());
        inspectionForm.setPrecipEndDate(inspection.getPrecipEndDate());
        inspectionForm.setPrecipAmount(inspection.getPrecipAmount());
        inspectionForm.setPrecipSource(inspection.getPrecipSource());
        inspectionForm.setInspectionActionComment(inspection.getInspectionActionComment());
        inspectionForm.setInspectionAction(new InspectionActionValue(inspection.getInspectionAction()));
        inspectionForm.setStatusCode(inspection.getStatus()
                                         .getCode());
        inspectionForm.setReportURL(inspectionValue.getUrl());
        List<EcInspectionDocument>
            documentList =
            EcInspectionDocument.findByInspectionId(inspection.getId());
        ArrayList<InspectionDocumentValue>
            inspectionDocumentArray =
            new ArrayList<InspectionDocumentValue>();
        for (EcInspectionDocument doc : documentList)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(doc);
            docValue.setClientId(clientValue.getId());
            docValue.setUsername(userValue.getUsername());
            docValue.setDownloadURL(docValue.getDownloadURL(request));
            docValue.setId(doc.getId());
            inspectionDocumentArray.add(docValue);
        }
        inspectionForm.setInspectionDocumentArray(inspectionDocumentArray);
        setSessionAttribute(SessionKeys.EC_FILE_DOC_ARRAY,
                            inspectionDocumentArray,
                            request);
        CapContact
            inspector =
            inspection.getInspector();
        inspectionForm.setInspector(inspector);
        ClientData
            inspectorClient =
            ClientData.findUniqueById(inspector.getOrganization()
                                          .getClientId());
        inspectionForm.setInspectorClient(inspectorClient);
        inspectionForm.setSelectedInspector(inspector.getId());
        List
            inspectorList =
            SpringServiceLocator.getInspectorService()
                .getAuthorizedInspectors(projectValue,
                                         clientValue.getId());
        if (!inspectorList.contains(inspector))
        {
            inspectorList.add(inspector);
        }
        request.setAttribute(SessionKeys.EC_INSPECTOR_LIST,
                             inspectorList);
        UserData
            enteredBy =
            UserService.getUserIgnoreStatus(inspection.getUpdateUserId());
        setSessionAttribute(SessionKeys.EC_INSPECTOR_USER,
                            enteredBy,
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_LIST,
                            InspectionService.getInspectionBmpValuesByInspectionId(inspectionValue.getId(),
                                                                                   clientValue.getId(),
                                                                                   user.getUsername(),
                                                                                   request),
                            request);
        setSessionAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT,
                            EcBmp.findByClientId(project.getOwnerClient()
                                                     .getId()),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_ACTION_LIST,
                            InspectionService.getInspectionActionValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_REASON_LIST,
                            InspectionService.getInspectionReasonValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_INSPECTION_REASON_LIST,
                            InspectionService.getInspectionInspectionReasonValueList(inspectionValue.getId()),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_STATUS_LIST,
                            InspectionService.getInspectionBmpStatusValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_CONDITION_LIST,
                            InspectionService.getInspectionBmpConditionValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_PRECIP_SOURCE_LIST,
                            InspectionService.getPrecipSourceLabelValueList(inspection.getPrecipSource()),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION,
                            inspectionValue,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            projectValue,
                            request);
        request.setAttribute(SessionKeys.EC_STATE_LIST,
                             UserService.getStateValueList());
        request.setAttribute(SessionKeys.EC_ORGANIZATION_LIST,
                             ProjectService.getOrgLabelValueList(projectValue.getPermitAuthorityClientId(),
                                                                 projectValue.getPermittedClientId(),
                                                                 projectValue.getAuthorizedInspectorClientId()));
        request.setAttribute(SessionKeys.CAP_CONTACT_LIST,
                             InspectionService.getContactsForProject(projectValue));
        return (mapping.findForward("continue"));
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME);
    }

}