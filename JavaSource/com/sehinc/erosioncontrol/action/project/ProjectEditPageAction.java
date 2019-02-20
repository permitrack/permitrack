package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.option.DataElementOptionValueService;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.util.ProjectUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectEditPageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectEditPageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    {
        ProjectForm
            projectForm =
            (ProjectForm) form;
        projectForm.reset();
        LOG.info("In ProjectEditPageAction");
        Integer
            projectId =
            getProjectId(request);
        if (projectId
            == null)
        {
            return handleError(mapping,
                               "project.error.projectIdNotFound",
                               request,
                               CommonConstants.FORWARD_FAIL);
        }
        EcProject
            project =
            new EcProject();
        project.setId(projectId);
        if (!project.load())
        {
            return handleError(mapping,
                               "project.error.loadingProject",
                               request,
                               projectId);
        }
        ProjectUtil.loadProjectIntoForm(project,
                                        projectForm);
        securityManager.setProjectID(project.getId());
        setSessionAttributes(request,
                             userValue,
                             securityManager,
                             clientValue,
                             project);
        return mapping.findForward("continue");
    }

    private void setSessionAttributes(HttpServletRequest request, UserValue userValue, SecurityManager securityManager, ClientValue clientValue, EcProject project)
    {
        request.getSession()
            .setAttribute(SessionKeys.EC_PERMIT_AUTHORITY_PROJECT_CONTACT_TYPE_ID,
                          ProjectService.getProjectContactTypeByCode(EcProjectContactType.PERMIT_AUTHORITY)
                              .getId());
        request.getSession()
            .setAttribute(SessionKeys.EC_PERMITTED_PROJECT_CONTACT_TYPE_ID,
                          ProjectService.getProjectContactTypeByCode(EcProjectContactType.PERMITTEE)
                              .getId());
        request.getSession()
            .setAttribute(SessionKeys.EC_AUTHORIZED_INSPECTOR_PROJECT_CONTACT_TYPE_ID,
                          ProjectService.getProjectContactTypeByCode(EcProjectContactType.AUTHORIZED_INSPECTOR)
                              .getId());
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_INSPECTION_TEMPLATE_BMP_LIST,
                          ProjectService.getProjectBmpValueList(project.getId()));
        request.getSession()
            .setAttribute(SessionKeys.EC_BMP_LIST_BY_CLIENT,
                          EcBmp.findByClientId(project.getOwnerClient()
                                                   .getId()));
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_TYPE_LIST,
                          ProjectService.getProjectTypeLabelValueList(clientValue));
        request.getSession()
            .setAttribute(SessionKeys.EC_STATE_LIST,
                          UserService.getStateValueList());
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                          ProjectService.getProjectContactValueList(project.getId(),
                                                                    false));
        request.getSession()
            .setAttribute(SessionKeys.EC_INTERNAL_PROJECT_CONTACT_LIST,
                          ProjectService.getProjectContactValueList(project.getId(),
                                                                    true));
        request.getSession()
            .setAttribute(SessionKeys.EC_CLIENT_CONTACT_LIST,
                          ClientService.getClientContactValueList(clientValue));
        request.getSession()
            .setAttribute(SessionKeys.EC_CONTACT_TYPE_LIST,
                          ProjectService.getProjectContactTypeValueListByClientId(clientValue.getId()));
        request.getSession()
            .setAttribute(SessionKeys.EC_AUTHORIZED_CLIENT_LIST,
                          ProjectService.getAuthorizedClientLabelValueList(clientValue));
        request.getSession()
            .setAttribute(SessionKeys.EC_PERMIT_AUTHORITY_CLIENT_LIST,
                          ProjectService.getPermitAuthorityClientLabelValueList(clientValue));
        request.getSession()
            .setAttribute(SessionKeys.EC_ZONE_LIST,
                          ProjectService.getZonesByClientLabelValueList(clientValue));
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_DOCUMENT_LIST,
                          ProjectService.getProjectDocumentValueList(project.getId(),
                                                                     clientValue.getId(),
                                                                     userValue.getUsername(),
                                                                     request));
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT_AREA_UNITS_LIST,
                          DataElementOptionValueService.getECOptionsList(ProjectDataElementOptionValue.AREA_UNITS));
        request.setAttribute(SessionKeys.PROJECT_STATUS_CODE_LIST,
                             SpringServiceLocator.getLookupService()
                                 .fetchCodesDisplayable(ProjectStatusCodeData.class));
        request.getSession()
            .setAttribute(SessionKeys.EC_PROJECT,
                          ProjectService.getProjectValue(project,
                                                         clientValue,
                                                         userValue,
                                                         securityManager));
    }

    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PROJECT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROJECT_EDIT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_VIEW_MENU_NAME),
                         request);
    }
}