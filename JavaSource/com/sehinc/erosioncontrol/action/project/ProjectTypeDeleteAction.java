package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.server.project.ProjectTypeService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectTypeDeleteAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeDeleteAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    {
        ProjectTypeForm
            projectTypeForm;
        String
            strLog =
            "ProjectTypeDeleteAction.projectAction: ";
        String
            strError;
        LOG.debug(strLog
                  + "In project type delete action.");
        setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                            "false",
                            request);
        projectTypeForm =
            (ProjectTypeForm) form;
        try
        {
            ProjectTypeService.deleteProjectType(projectTypeForm.getId(),
                                                 userValue);
            ProjectTypeService.setProjectTypeInSessionAndRequest(null,
                                                                 request);
            addMessage(new ActionMessage("project.type.delete.success"),
                       request);
        }
        catch (Exception e)
        {
            strError =
                strLog
                + "Error: Unable to save the project type. Message: "
                + e.getMessage();
            LOG.debug(strError);
            addError(new ActionMessage("project.type.delete.failed"),
                     request);
        }
        //        }
        return mapping.findForward("continue");
    }
}
