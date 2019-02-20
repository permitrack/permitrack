package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.sourceusage.EnvSourceUsage;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceUsageDeleteAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageDeleteAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "SourceUsageDeleteAction. ";
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
            sourceUsageId;
        if (request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID)
            != null)
        {
            sourceUsageId =
                new Integer(request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID));
            LOG.debug("sourceUsageId="
                      + request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                     request)
                 != null)
        {
            sourceUsageId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                              request);
            LOG.debug("Got sourceUsageId="
                      + sourceUsageId
                      + " from the session.");
            LOG.debug("sourceUsageId="
                      + getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("source.usage.error.no.source.usage.on.request"));
            addError(new ActionMessage("source.usage.error.no.source.usage.on.request"), request);
            return mapping.findForward("source.usage.list.page");
        }
        EnvSourceUsage
            envSourceUsage =
            new EnvSourceUsage(sourceUsageId);
        try
        {
            envSourceUsage.load();
            envSourceUsage.delete();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {sourceUsageId};
            LOG.error(ApplicationResources.getProperty("source.usage.delete.page.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("source.usage.delete.page.failed",
                                       parameters), request);
            return mapping.findForward("source.usage.list.page");
        }
        addMessage(new ActionMessage("source.usage.delete.success"), request);
        return mapping.findForward("continue");
    }
}