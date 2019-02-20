package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ProjectTypeViewAction
    extends ProjectBaseAction
{ // Project Type List Page Action Class
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectTypeViewAction.class);

    //-------------------------------------------------------------------------------------
    // Purpose : Perform the save action for a new project type
    // Inputs  :
    // Outputs :
    // Remarks :
    // History : 4/19/2006: Created New
    //-------------------------------------------------------------------------------------
    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, com.sehinc.common.security.SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    { // project Action
        String
            strForward =
            "projecttype.list.page";
        ProjectTypeForm
            projectTypeForm;
        String
            strLog =
            new String("ProjectTypeViewAction.projectAction: ");
        LOG.debug(strLog
                  + "In project type view action.");
        setSessionAttribute(SessionKeys.PROJECT_TYPE_DUPLICATE,
                            "false",
                            request);
        projectTypeForm =
            (ProjectTypeForm) form;
        if (projectTypeForm
            != null)
        {
            projectTypeForm.reset();
        }
        return mapping.findForward(strForward);
    } // project action
} // Project Type List Page Action Class
