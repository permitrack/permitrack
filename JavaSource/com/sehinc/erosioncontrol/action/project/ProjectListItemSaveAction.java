package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectListItemSaveAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectListItemSaveAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException
    {
        ProjectListItemForm
            projectListItemForm =
            (ProjectListItemForm) form;
        // process if the user canceled
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            projectListItemForm.reset();
            return mapping.findForward("project.list.page");
        }
        //Save the project list items to the database for this user
        ProjectService.saveUserProjectListItems(projectListItemForm.getEcProjectListItems(),
                                                userValue);
        return mapping.findForward("continue");
    }
}
