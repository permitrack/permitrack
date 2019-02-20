package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.project.EcProject;
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

public class ProjectViewPageAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectViewPageAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws Exception, SQLException, ServletException
    {
        ProjectForm
            projectForm =
            (ProjectForm) form;
        projectForm.reset();
        //get the ID from the request
        //if it exists, then we changed Projects
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
        //get the Project from the database
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
        setMapLink(request,
                   clientValue, project.getId());
        ProjectUtil.loadProjectIntoForm(project,
                                        projectForm);
        try
        {
            setSessionAttributes(request,
                                 userValue,
                                 clientValue,
                                 securityManager,
                                 project);
        }
        catch (Exception e)
        {
            return handleError(mapping,
                               "project.error.loadingProjectInfo",
                               request,
                               project.getId());
        }
        return mapping.findForward("continue");
    }

    private void setSessionAttributes(HttpServletRequest request, UserValue userValue, ClientValue clientValue, SecurityManager securityManager, EcProject project)
    {
        setBmpList(request,
                   project);
        setProjectContactList(request,
                              project);
        setProjectDocumentList(request,
                               userValue,
                               clientValue,
                               project);
        //        setLastProjectPage(request);
        setProject(request,
                   userValue,
                   clientValue,
                   securityManager,
                   project);
    }

    private void setProject(HttpServletRequest request, UserValue userValue, ClientValue clientValue, SecurityManager securityManager, EcProject project)
    {
        setSessionAttribute(SessionKeys.EC_PROJECT,
                            ProjectService.getProjectValue(project,
                                                           clientValue,
                                                           userValue,
                                                           securityManager),
                            request);
    }

    private void setProjectDocumentList(HttpServletRequest request, UserValue userValue, ClientValue clientValue, EcProject project)
    {
        setSessionAttribute(SessionKeys.EC_PROJECT_DOCUMENT_LIST,
                            ProjectService.getProjectDocumentValueList(project.getId(),
                                                                       clientValue.getId(),
                                                                       userValue.getUsername(),
                                                                       request),
                            request);
    }

    private void setProjectContactList(HttpServletRequest request, EcProject project)
    {
        setSessionAttribute(SessionKeys.EC_PROJECT_CONTACT_LIST,
                            ProjectService.getProjectContactValueList(project.getId(),
                                                                      false),
                            request);
    }

    private void setBmpList(HttpServletRequest request, EcProject project)
    {
        setSessionAttribute(SessionKeys.EC_PROJECT_BMP_LIST,
                            ProjectService.getProjectBmpValueList(project.getId()),
                            request);
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(request);
        secondaryMenu.setCurrentItem(SecondaryMenu.PROJECT_VIEW_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.PROJECT_VIEW_MENU_NAME),
                         request);
    }
}