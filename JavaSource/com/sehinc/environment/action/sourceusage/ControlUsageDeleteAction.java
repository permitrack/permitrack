package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.controlusage.EnvControlUsage;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlUsageDeleteAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageDeleteAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "ControlUsageDeleteAction. ";
        String
            strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_DELETE,
                           request))
        {
            return mapping.findForward("page.permission.denied");
        }
        Integer
            controlUsageId;
        if (request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID)
            != null)
        {
            controlUsageId =
                new Integer(request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID));
            LOG.debug("controlUsageId="
                      + request.getParameter(RequestKeys.EV_CONTROL_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                     request)
                 != null)
        {
            controlUsageId =
                (Integer) getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                              request);
            LOG.debug("Got controlUsageId="
                      + controlUsageId
                      + " from the session.");
            LOG.debug("controlUsageId="
                      + getSessionAttribute(SessionKeys.EV_CONTROL_USAGE_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("control.usage.error.no.source.usage.on.request"));
            addError(new ActionMessage("control.usage.error.no.source.usage.on.request"), request);
            return mapping.findForward("control.usage.list.page");
        }
        EnvControlUsage
            envControlUsage =
            new EnvControlUsage(controlUsageId);
        try
        {
            envControlUsage.load();
            envControlUsage.delete();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {controlUsageId};
            LOG.error(ApplicationResources.getProperty("control.usage.delete.page.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("control.usage.delete.page.failed",
                                       parameters), request);
            return mapping.findForward("control.usage.list.page");
        }
        addMessage(new ActionMessage("control.usage.delete.success"), request);
        return mapping.findForward("continue");
    }
}
