package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionDocument;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.InspectionActionValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionReasonValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionReportValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class InspectionViewPageAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionViewPageAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        inspectionForm.reset();
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        ClientValue
            clientValue =
            getClientValue(request);
        Integer
            inspectionId =
            getInspectionId(request);
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
        try
        {
            inspection.load();
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
            new EcProject(inspection.getProjectId());
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
                {inspection.getProjectId()};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        ProjectValue
            projectValue =
            ProjectService.getProjectValue(project,
                                           clientValue,
                                           userValue,
                                           securityManager);
        InspectionReportValue
            inspectionValue =
            new InspectionReportValue(inspection);
        SecurityPermissionValue
            inspectionSecurityPermissionValue =
            ProjectService.getInspectionSecurityPermissionValue(securityManager,
                                                                projectValue);
        // User must have INSPECTION_UPDATE permission, if the project is FINAL they cannot update it unless they are the Permit Authority
        boolean
            hasUpdatePerm =
            InspectionService.hasInspectionUpdatePermission(inspectionSecurityPermissionValue.getIsUpdate(),
                                                            inspection.getClientId(),
                                                            clientValue.getId(),
                                                            inspection.getStatus()
                                                                .getCode(),
                                                            securityManager);
        inspectionSecurityPermissionValue.setIsUpdate(hasUpdatePerm);
        // If you can't Update you can't Delete
        inspectionSecurityPermissionValue.setIsDelete(inspectionSecurityPermissionValue.getIsDelete()
                                                      && hasUpdatePerm);
        inspectionValue.setInspectionSecurityPermissionValue(inspectionSecurityPermissionValue);
        inspectionForm.setId(inspection.getId());
        inspectionForm.setProjectId(projectValue.getId());
        inspectionForm.setInspectionDate(inspection.getInspectionDate());
        inspectionForm.setHours(inspection.getHours());
        inspectionForm.setMinutes(inspection.getMinutes());
        inspectionForm.setTimePeriod(inspection.getTimePeriod());
        inspectionForm.setTimeString(InspectionService.displayTimeString(inspection.getHours(),
                                                                         inspection.getMinutes(),
                                                                         inspection.getTimePeriod()));
        inspectionForm.setEnteredDate(inspection.getEnteredDate());
        inspectionForm.setWeatherTrends(inspection.getWeatherTrends());
        inspectionForm.setTemperature(inspection.getTemperature());
        inspectionForm.setComment(inspection.getComment());
        inspectionForm.setPrecipEndDate(inspection.getPrecipEndDate());
        inspectionForm.setPrecipAmount(inspection.getPrecipAmount());
        inspectionForm.setPrecipSource(inspection.getPrecipSource());
        inspectionForm.setInspectionActionComment(inspection.getInspectionActionComment());
        inspectionForm.setInspectionAction(new InspectionActionValue(inspection.getInspectionAction()));
        inspectionForm.setInspectionReason(new InspectionReasonValue(inspection.getInspectionReason()));
        inspectionForm.setStatusCode(inspection.getStatus()
                                         .getCode());
        inspectionForm.setReportURL(inspectionValue.getUrl());
        List<EcInspectionDocument>
            documentList =
            EcInspectionDocument.findByInspectionId(inspection.getId());
        ArrayList<InspectionDocumentValue>
            newArray =
            new ArrayList<InspectionDocumentValue>();
        for (EcInspectionDocument doc : documentList)
        {
            InspectionDocumentValue
                docValue =
                new InspectionDocumentValue(doc);
            docValue.setClientId(clientValue.getId());
            docValue.setUsername(userValue.getUsername());
            docValue.setDownloadURL(docValue.getDownloadURL(request));
            newArray.add(docValue);
        }
        inspectionForm.setInspectionDocumentArray(newArray);
        CapContact
            contact =
            inspection.getInspector();
        inspectionForm.setInspector(contact);
        ClientData
            inspectorClient =
            ClientData.findUniqueById(contact.getOrganization()
                                          .getClientId());
        inspectionForm.setInspectorClient(inspectorClient);
        UserData
            inspector =
            UserService.getUserIgnoreStatus(inspection.getUpdateUserId());
        request.getSession()
            .setAttribute(SessionKeys.EC_INSPECTOR_USER,
                          inspector);
        request.getSession()
            .setAttribute(SessionKeys.EC_INSPECTION_BMP_LIST,
                          InspectionService.getInspectionBmpValuesByInspectionId(inspectionValue.getId(),
                                                                                 clientValue.getId(),
                                                                                 userValue.getUsername(),
                                                                                 request));
        request.getSession()
            .setAttribute(SessionKeys.EC_INSPECTION,
                          inspectionValue);
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT,
                          projectValue);
        /* NOT USED 8/20/2016 JRA
        // Get inspection update permission
        // User must have INSPECTION_UPDATE permission, if the project is FINAL
        // they cannot update it unless they are the Permit Authority
        Boolean
            canUpdate =
            InspectionService.getInspectionPermissions(projectValue,
                                                       securityManager,
                                                       inspection.getClientId(),
                                                       clientValue.getId(),
                                                       inspection.getStatus()
                                                           .getCode());
        request.getSession()
            .setAttribute(SessionKeys.INSPECTION_CAN_UPDATE,
                          canUpdate);
        */
        return (mapping.findForward("continue"));
    }

    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_VIEW_MENU_NAME),
                         request);
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.INSPECTION_LIST_MENU_ITEM_NAME);
    }
}