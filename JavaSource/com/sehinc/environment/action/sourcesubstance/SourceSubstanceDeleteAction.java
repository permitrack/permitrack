package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceSubstanceDeleteAction
    extends SourceSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceSubstanceDeleteAction.class);

    public ActionForward sourceSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceSubstanceDeleteAction. ";
        strLog =
            strMod
            + "sourceSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_SOURCE_SUBSTANCE_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("source.substance.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("source.substance.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("source.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.substance.delete.cancel.action"), request);
            return mapping.findForward("source.view.page");
        }
        else
        {
            Integer
                sourceSubstanceId;
            LOG.debug("sourceSubstanceId="
                      + request.getParameter(RequestKeys.EV_SOURCE_SUBSTANCE_ID));
            if (request.getParameter(RequestKeys.EV_SOURCE_SUBSTANCE_ID)
                != null)
            {
                sourceSubstanceId =
                    new Integer(request.getParameter(RequestKeys.EV_SOURCE_SUBSTANCE_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("source.substance.error.no.source.substance.on.request"));
                addError(new ActionMessage("source.substance.error.no.source.substance.on.request"), request);
                return mapping.findForward("source.view.page");
            }
            EnvSourceSubstance
                envSourceSubstance =
                new EnvSourceSubstance(sourceSubstanceId);
            try
            {
                envSourceSubstance.load();
                envSourceSubstance.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {sourceSubstanceId};
                LOG.error(ApplicationResources.getProperty("source.substance.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("source.substance.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("source.view.page");
            }
            addMessage(new ActionMessage("source.substance.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}