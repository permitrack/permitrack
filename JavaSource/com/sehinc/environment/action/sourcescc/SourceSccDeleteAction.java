package com.sehinc.environment.action.sourcescc;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.sourcescc.EnvSourceSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceSccDeleteAction
    extends SourceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSccDeleteAction.class);

    public ActionForward sourceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSccDeleteAction. ";
        strLog =
            strMod
            + "sourceSccAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_SCC_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("source.scc.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("source.scc.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("source.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.scc.delete.cancel.action"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            Integer
                sourceSccId;
            LOG.debug("sourceSccId="
                      + request.getParameter(RequestKeys.EV_SOURCE_SCC_ID));
            if (request.getParameter(RequestKeys.EV_SOURCE_SCC_ID)
                != null)
            {
                sourceSccId =
                    new Integer(request.getParameter(RequestKeys.EV_SOURCE_SCC_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("source.scc.error.no.source.scc.on.request"));
                addError(new ActionMessage("source.scc.error.no.source.scc.on.request"), request);
                return mapping.findForward("source.view.page");
            }
            EnvSourceSccInfo
                envSourceScc =
                new EnvSourceSccInfo(sourceSccId);
            try
            {
                envSourceScc.load();
                envSourceScc.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sourceSccId};
                LOG.error(ApplicationResources.getProperty("source.scc.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.scc.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("source.view.page");
            }
            addMessage(new ActionMessage("source.scc.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}

