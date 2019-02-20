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

public class ProjectTypeEditPageAction
    extends ProjectBaseAction
{ // Project Type List Page Action Class
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
            "ProjectTypeEditPageAction.projectAction: ";
        String
            strError;
        ProjectTypeValue
            projectTypeValue =
            new ProjectTypeValue();
        boolean
            blnDuplicate =
            false;
        Integer
            intProjectTypeId;
        LOG.debug(strLog
                  + "In project type edit page action.");
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

            if (getSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                    request)
                != null)
            {
                blnDuplicate =
                    getSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                        request).toString()
                    == "true";
            }
            if (blnDuplicate)
            { // User attempted to create a duplicate
                LOG.debug(strLog
                          + "Proccessing duplicate project type");
                projectTypeValue =
                    (ProjectTypeValue) getSessionAttribute(SessionKeys.PROJECT_TYPE_VALUE,
                                                           request);
            } // User attempted to create a duplicate
            else
            { // User did not create a duplicate before hand
                setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                    "false",
                                    request);
                projectTypeValue.resetProjectTypeValue();
                intProjectTypeId =
                    ProjectTypeService.getProjectTypeIdFromSessionAndRequest(request);
                if (intProjectTypeId.intValue()
                    == 0)
                { // project type id not found
                    strError =
                        strLog
                        + "project Id is zero.  Unable to edit project type.";
                    LOG.debug(strError);
                    throw new Exception(strError);
                } // project type id not found
                else
                { // Valid project type id
                    try
                    { // Get the project type information from the project type id
                        projectTypeValue =
                            ProjectTypeService.getProjectTypeById(intProjectTypeId);
                        setSessionAttribute(SessionKeys.PROJECT_TYPE_MESSAGE,
                                            "Update project type "
                                            + projectTypeValue.getName(),
                                            request);
                    } // Get the project type information from the project type id
                    catch (Exception a)
                    { // Unable to get project type by id
                        strError =
                            strLog
                            + "Unable to get project type from the project type id";
                        LOG.debug(strError);
                        throw new Exception(strError);
                    } // Unable to get project type by id
                } // Valid project type id
            } // User did not create a duplicate before hand
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
                                "Edit Project Type",
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
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.PROJECT_TYPE_EDIT_MENU_ITEM_NAME);
    }
}
