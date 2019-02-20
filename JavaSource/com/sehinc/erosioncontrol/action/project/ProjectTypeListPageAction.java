package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.project.ProjectTypeService;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectTypeListPageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectCreateAction.class);

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
            "ProjectTypeListPageAction.projectAction: ";
        String
            strError;
        LOG.debug(strLog
                  + "In project type list page action.");
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
            clientValue =
                getClientValue(request);
            try
            { // Get a list of project types
                setSessionAttribute(SessionKeys.PROJECT_TYPE_LIST,
                                    ProjectTypeService.getProjectTypeList(clientValue.getId()),
                                    request);
                if (getSessionAttribute(SessionKeys.PROJECT_TYPE_MESSAGE,
                                        request)
                    == null)
                {
                    setSessionAttribute(SessionKeys.PROJECT_TYPE_MESSAGE,
                                        "",
                                        request);
                }
            } // Get a list of project types
            catch (Exception e)
            { // Project type list errors
                strError =
                    strLog
                    + "Error retrieving list of project types for client. Message: "
                    + e.getMessage();
                LOG.debug(strError);
            } // Project type list errors
            setSessionAttribute(SessionKeys.PROJECT_UPDATE,
                                securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_UPDATE),
                                request);
            setSessionAttribute(SessionKeys.PROJECT_DELETE,
                                securityManager.HasECPermission(SecureObjectPermissionData.PROJECT_DELETE),
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
        LOG.debug(strLog
                  + "forwarding to "
                  + strForward);
        return mapping.findForward(strForward);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROJECT_TYPE_LIST_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_TYPE_LIST_MENU_NAME),
                         request);
    }
}
