package com.sehinc.environment.action.permitsubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.permitsubstance.EnvPermitSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitSubstanceDeleteAction
    extends PermitSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitSubstanceDeleteAction.class);

    public ActionForward permitSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitSubstanceDeleteAction. ";
        strLog =
            strMod
            + "permitSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_SUBSTANCE_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("permit.substance.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.substance.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("permit.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.substance.delete.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            Integer
                permitSubstanceId;
            LOG.debug("permitSubstanceId="
                      + request.getParameter(RequestKeys.EV_PERMIT_SUBSTANCE_ID));
            if (request.getParameter(RequestKeys.EV_PERMIT_SUBSTANCE_ID)
                != null)
            {
                permitSubstanceId =
                    new Integer(request.getParameter(RequestKeys.EV_PERMIT_SUBSTANCE_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("permit.substance.error.no.permit.substance.on.request"));
                addError(new ActionMessage("permit.substance.error.no.permit.substance.on.request"), request);
                return mapping.findForward("permit.view.page");
            }
            EnvPermitSubstance
                envPermitSubstance =
                new EnvPermitSubstance(permitSubstanceId);
            try
            {
                envPermitSubstance.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitSubstanceId};
                LOG.error(ApplicationResources.getProperty("permit.substance.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.substance.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("permit.view.page");
            }
            addMessage(new ActionMessage("permit.substance.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}