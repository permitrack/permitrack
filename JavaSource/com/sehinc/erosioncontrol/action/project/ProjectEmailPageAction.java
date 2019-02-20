package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProjectEmailPageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectEmailPageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception
    {
        Integer
            projectId;
        if (request.getParameter(RequestKeys.EC_PROJECT_ID)
            != null)
        {
            projectId =
                new Integer(request.getParameter(RequestKeys.EC_PROJECT_ID));
        }
        else
        {
            //get the Project from the session
            ProjectValue
                projectValue =
                (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                                   request);
            projectId =
                projectValue.getId();
        }
        //Return an error if we could not locate a project ID
        if (projectId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        //Load the project
        EcProject
            project =
            new EcProject();
        project.setId(projectId);
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
                {projectId};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return (mapping.findForward("project.list.page"));
        }
        //Get the project contact list
        List
            projectContactList =
            ProjectService.getProjectContactValueListAll(projectId);
        //If the project contact list is empty, then return to the project list page
        if (projectContactList
            == null
            || projectContactList.isEmpty())
        {
            return mapping.findForward("project.list.page");
        }
        //Set the project contact list in the session
        setSessionAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                            projectContactList,
                            request);
        //save the project in the session
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            ProjectService.getProjectValue(project,
                                                           clientValue,
                                                           userValue,
                                                           securityManager),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_EMAIL_MENU_NAME),
                         request);
    }
}