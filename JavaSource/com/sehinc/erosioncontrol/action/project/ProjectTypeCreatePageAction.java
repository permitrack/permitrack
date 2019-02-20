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
import java.sql.SQLException;

public class ProjectTypeCreatePageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeCreatePageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    { // project Action
        ProjectTypeForm
            projectTypeForm;
        String
            strForward =
            "continue";
        String
            strLog =
            "ProjectTypeCreatePageAction.projectAction: ";
        String
            strError;
        ProjectTypeValue
            projectTypeValue =
            new ProjectTypeValue();
        LOG.debug(strLog
                  + "In project type create page action.");
        if (securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.PROJECT_UPDATE))
        {
            // Reset the project type form.
            projectTypeForm =
                (ProjectTypeForm) form;
            if (projectTypeForm
                != null)
            {
                projectTypeForm.reset();
            }
/*
            clientValue =
                getClientValue(request);
*/
            if (getSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                    request)
                != null)
            {
                if (getSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                        request).toString()
                    == "false")
                {
                    projectTypeValue.resetProjectTypeValue();
                    setSessionAttribute(SessionKeys.PROJECT_TYPE_MESSAGE,
                                        "",
                                        request);
                }
                else
                {
                    projectTypeValue =
                        (ProjectTypeValue) getSessionAttribute(SessionKeys.PROJECT_TYPE_VALUE,
                                                               request);
                }
            }
            else
            {
                setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                    "false",
                                    request);
                projectTypeValue.resetProjectTypeValue();
                setSessionAttribute(SessionKeys.PROJECT_TYPE_MESSAGE,
                                    "",
                                    request);
            }
            try
            { // Get a list of project types
                setSessionAttribute(SessionKeys.PROJECT_TYPE_END_POINT_TYPE_LIST,
                                    ProjectTypeService.getEndPointTypeList(),
                                    request);
                setSessionAttribute(SessionKeys.PROJECT_TYPE_VALUE,
                                    projectTypeValue,
                                    request);
            } // Get a list of project types
            catch (Exception e)
            { // Project type list errors
                strError =
                    strLog
                    + "Error retrieving end point types. Message: "
                    + e.getMessage();
                LOG.debug(strError);
            } // Project type list errors
        }
        else
        {
            LOG.debug(strLog
                      + "user does not have access to this page.");
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "Create New Project Type",
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
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_TYPE_LIST_MENU_NAME),
                         request);
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.PROJECT_TYPE_CREATE_MENU_ITEM_NAME);
    }
}
