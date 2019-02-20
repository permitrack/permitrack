package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.project.ProjectTypeService;
import com.sehinc.erosioncontrol.value.project.ProjectTypeValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectTypeViewPageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeViewPageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws ServletException, Exception
    {
        ProjectTypeForm
            projectTypeForm;
        String
            strForward =
            "continue";
        String
            strLog =
            "ProjectTypeViewPageAction.projectAction: ";
        String
            strError;
        ProjectTypeValue
            projectTypeValue =
            new ProjectTypeValue();
        Integer
            intProjectTypeId;
        LOG.debug(strLog
                  + "In project type delete page action.");
        if (securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.PROJECT_READ))
        {
            // Reset the project type form.
            projectTypeForm =
                (ProjectTypeForm) form;
            if (projectTypeForm
                != null)
            {
                projectTypeForm.reset();
            }
            setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                "false",
                                request);
            projectTypeValue.resetProjectTypeValue();
            projectTypeValue.checkForHTML();
            intProjectTypeId =
                ProjectTypeService.getProjectTypeIdFromSessionAndRequest(request);
            if (intProjectTypeId
                == 0)
            { // project type id not found
                strError =
                    strLog
                    + "project Id is zero.  Unable to delete project type.";
                LOG.debug(strError);
                throw new Exception(strError);
            } // project type id not found
            else
            { // Valid project type id
                try
                { // Get the project type information from the project type id
                    projectTypeValue =
                        ProjectTypeService.getProjectTypeById(intProjectTypeId);
                } // Get the project type information from the project type id
                catch (Exception a)
                { // Unable to get project type by id
                    strError =
                        strLog
                        + "Unable to get project type from the project type id.  Message: "
                        + a.getMessage();
                    LOG.debug(strError);
                    throw new Exception(strError);
                } // Unable to get project type by id
            } // Valid project type id
            setSessionAttribute(SessionKeys.PROJECT_TYPE_VALUE,
                                projectTypeValue,
                                request);
            ProjectTypeService.setProjectTypeInSessionAndRequest(intProjectTypeId,
                                                                 request);
        }
        else
        {
            LOG.debug(strLog
                      + "user does not have access to this page.");
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Project Types",
                                request);
            strForward =
                "page.permission.denied";
        }
        return mapping.findForward(strForward);
    } // project action

    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_TYPE_VIEW_MENU_NAME),
                         request);
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.PROJECT_TYPE_VIEW_MENU_ITEM_NAME);
    }
}
