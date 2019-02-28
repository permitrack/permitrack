package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.db.client.ClientData;
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
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.server.inspection.InspectionService;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InspectionCreatePageAction
    extends InspectionBaseAction
{
    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        InspectionForm
            inspectionForm =
            (InspectionForm) form;
        if (isCancelled(request))
        {
            inspectionForm.reset();
            return mapping.findForward("inspection.list.page");
        }
        UserValue
            userValue =
            getUserValue(request);
        UserData
            user =
            UserService.getUser(userValue.getId());
        ClientValue
            clientValue =
            getClientValue(request);
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientValue.getId());
        clientData.load();
        SecurityManager
            securityManager =
            getSecurityManager(request);
        Integer
            projectId =
            null;
        if (request.getParameter(RequestKeys.EC_PROJECT_ID)
            != null)
        {
            projectId =
                new Integer(request.getParameter(RequestKeys.EC_PROJECT_ID));
        }
        else
        {
            ProjectValue
                projectValue =
                (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                                   request);
            if (projectValue
                != null)
            {
                projectId =
                    projectValue.getId();
            }
        }
        if (projectId
            == null)
        {
            return mapping.findForward("project.list.page");
        }
        EcProject
            project =
            new EcProject(projectId);
        project.load();
        ProjectValue
            projectValue =
            ProjectService.getProjectValue(project,
                                           clientValue,
                                           userValue,
                                           securityManager);
        securityManager.setProjectID(projectValue.getId()
                                         .intValue());
/*
        setSessionAttribute(SessionKeys.IS_PROJECT_PERMIT_AUTHORITY,
                            securityManager.getIsProjectPermitAuthority(),
                            request);
*/
        setSessionAttribute(SessionKeys.EC_INSPECTOR_USER,
                            user,
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT_BMP_LIST,
                            ProjectService.getProjectBmpValueList(projectValue.getId()),
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
                            InspectionService.getInspectionInspectionReasonValueList(0),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_STATUS_LIST,
                            InspectionService.getInspectionBmpStatusValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_CONDITION_LIST,
                            InspectionService.getInspectionBmpConditionValueList(),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_BMP_DEFECT_LIST,
                            InspectionService.getInspectionDefectBmpValueList(projectValue.getId(),
                                                                              null,
                                                                              null,
                                                                              clientValue.getId(),
                                                                              userValue.getUsername(),
                                                                              request),
                            request);
        setSessionAttribute(SessionKeys.EC_INSPECTION_PRECIP_SOURCE_LIST,
                            InspectionService.getPrecipSourceLabelValueList(null),
                            request);
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            projectValue,
                            request);
        List
            inspectorList =
            SpringServiceLocator.getInspectorService()
                .getAuthorizedInspectors(projectValue,
                                         clientData.getId());
        request.setAttribute(SessionKeys.EC_INSPECTOR_LIST,
                             inspectorList);
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
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.INSPECTION_CREATE_MENU_ITEM_NAME);
    }
}