package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.server.project.ProjectTypeService;
import com.sehinc.erosioncontrol.value.project.ProjectTypeValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectTypeCreateAction
    extends ProjectBaseAction
{ // Project Type List Page Action Class
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectCreateAction.class);

    //-------------------------------------------------------------------------------------
    // Purpose : Perform the save action for a new project type
    // Inputs  :
    // Outputs :
    // Remarks :
    // History : 4/19/2006: Created New
    //-------------------------------------------------------------------------------------
    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    { // project Action
        String
            strForward =
            "continue";
        ProjectTypeForm
            projectTypeForm;
        String
            strLog =
            new String("ProjectTypeCreateAction.projectAction: ");
        String
            strError;
        ProjectTypeValue
            projectTypeValue;
        Integer
            intProjectTypeId;
        LOG.debug(strLog
                  + "In project type create action.");
        setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                            "false",
                            request);
        projectTypeForm =
            (ProjectTypeForm) form;
        if (isCancelled(request))
        { // User canceled the create projec type action
            LOG.info(strLog
                     + "Request was CANCELED");
            if (projectTypeForm
                != null)
            {
                projectTypeForm.reset();
            }
        } // User canceled the create projec type action
        else
        { // User did not cancel
            // Get the project type form
            if (projectTypeForm
                != null)
            { // Project type form is not null
                clientValue =
                    getClientValue(request);
                LOG.debug(strLog
                          + " check box "
                          + projectTypeForm.getSwmp()
                    .toString());
                //Check for duplicate name
                projectTypeValue =
                    ProjectTypeService.getProjectTypeByName(projectTypeForm.getName(),
                                                            clientValue.getId());
                if (projectTypeValue.getId()
                        .intValue()
                    == 0)
                { // project type value is not a duplicate
                    try
                    { // Save the new project type
                        intProjectTypeId =
                            ProjectTypeService.saveProjectType(projectTypeForm,
                                                               clientValue.getId(),
                                                               userValue);
                        //Save the new project type into the request and the session.
                        ProjectTypeService.setProjectTypeInSessionAndRequest(intProjectTypeId,
                                                                             request);
                        addMessage(new ActionMessage("project.type.create.success"),
                                   request);
                    } // Save the new project type
                    catch (Exception e)
                    { // Error saving the new project type
                        strError =
                            strLog
                            + "Error: Unable to save the new project type. Message: "
                            + e.getMessage();
                        LOG.debug(strError);
                        throw new Exception(strError);
                    } // Error saving the new project type
                } // project type value is not a duplicate
                else
                { // Project type value is a duplicate, redirect to create new page
                    LOG.debug(strLog
                              + "Project Type "
                              + projectTypeForm.getName()
                              + " is a duplicate");
                    //Put the form back into the request
                    projectTypeValue.resetProjectTypeValue();
                    projectTypeValue.setName(projectTypeForm.getName());
                    projectTypeValue.setDescription(projectTypeForm.getDescription());
                    projectTypeValue.setEndPointTypeId(projectTypeForm.getEndPointTypeId());
                    projectTypeValue.setExtendedOnlineAccessMonths(projectTypeForm.getExtendedOnlineAccessMonths());
                    projectTypeValue.setSwmp(projectTypeForm.getSwmp());
                    projectTypeValue.setMonthsFromStart(projectTypeForm.getMonthsFromStart());
                    setSessionAttribute(SessionKeys.PROJECT_TYPE_VALUE,
                                        projectTypeValue,
                                        request);
                    setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                                        "true",
                                        request);
                    //Update the message
                    Object[]
                        parameters =
                        {projectTypeForm.getName()};
                    addError(new ActionMessage(("project.type.error.duplicate.name"),
                                               parameters),
                             request);
                    //Re-direct the user back to the create property type page.
                    strForward =
                        "projecttype.create.page";
                } // Project type value is a duplicate, redirect to create new page
            } // Project type form is not null
            else
            { // project Type Form is null
                strError =
                    strLog
                    + "Project Type Form is null from request";
                LOG.debug(strError);
                throw new Exception(strError);
            } // project Type Form is null
        } // User did not cancel
        return mapping.findForward(strForward);
    } // project action

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_TYPE_NONE_MENU_NAME),
                         request);
    }
}
