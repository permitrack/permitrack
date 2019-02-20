package com.sehinc.environment.action.substancescc;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.substancescc.EnvSubstanceSccInfo;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubstanceSccDeleteAction
    extends SubstanceSccBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceSccDeleteAction.class);

    public ActionForward substanceSccAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SubstanceSccDeleteAction. ";
        strLog =
            strMod
            + "substanceSccAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SUBSTANCE_SCC_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("substance.scc.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("substance.scc.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("substance.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("substance.scc.delete.cancel.action"), request);
            return mapping.findForward("substance.view.page");
        }
        else
        {
            Integer
                substanceSccId;
            LOG.debug("substanceSccId="
                      + request.getParameter(RequestKeys.EV_SUBSTANCE_SCC_ID));
            if (request.getParameter(RequestKeys.EV_SUBSTANCE_SCC_ID)
                != null)
            {
                substanceSccId =
                    new Integer(request.getParameter(RequestKeys.EV_SUBSTANCE_SCC_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("substance.scc.error.no.substance.scc.on.request"));
                addError(new ActionMessage("substance.scc.error.no.substance.scc.on.request"), request);
                return mapping.findForward("substance.view.page");
            }
            EnvSubstanceSccInfo
                envSubstanceScc =
                new EnvSubstanceSccInfo(substanceSccId);
            try
            {
                envSubstanceScc.load();
                envSubstanceScc.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {substanceSccId};
                LOG.error(ApplicationResources.getProperty("substance.scc.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("substance.scc.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("substance.view.page");
            }
            addMessage(new ActionMessage("substance.scc.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}

