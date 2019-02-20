package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class InspectionDefaultAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionDefaultAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        LOG.info("In InspectionDefaultAction");
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            return mapping.findForward("project.list.page");
        }
        return mapping.findForward("inspection.list.page");
    }
}