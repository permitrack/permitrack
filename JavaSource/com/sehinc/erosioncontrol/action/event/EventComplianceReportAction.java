package com.sehinc.erosioncontrol.action.event;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventComplianceReportAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventComplianceReportAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In EventComplianceReportAction");
        return mapping.findForward("continue");
    }
}
