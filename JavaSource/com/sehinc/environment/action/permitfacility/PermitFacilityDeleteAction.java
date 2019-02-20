package com.sehinc.environment.action.permitfacility;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.permitfacility.EnvPermitFacility;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitFacilityDeleteAction
    extends PermitFacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitFacilityDeleteAction.class);

    public ActionForward permitFacilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitFacilityDeleteAction. ";
        strLog =
            strMod
            + "permitFacilityAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_FACILITY_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("permit.facility.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.facility.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("permit.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.facility.delete.cancel.action"), request);
            return mapping.findForward("permit.view.page");
        }
        else
        {
            Integer
                permitFacilityId;
            LOG.debug("permitFacilityId="
                      + request.getParameter(RequestKeys.EV_PERMIT_FACILITY_ID));
            if (request.getParameter(RequestKeys.EV_PERMIT_FACILITY_ID)
                != null)
            {
                permitFacilityId =
                    new Integer(request.getParameter(RequestKeys.EV_PERMIT_FACILITY_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("permit.facility.error.no.permit.facility.on.request"));
                addError(new ActionMessage("permit.facility.error.no.permit.facility.on.request"), request);
                return mapping.findForward("permit.view.page");
            }
            EnvPermitFacility
                envPermitFacility =
                new EnvPermitFacility(permitFacilityId);
            try
            {
                envPermitFacility.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {envPermitFacility};
                LOG.error(ApplicationResources.getProperty("permit.facility.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.facility.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("permit.view.page");
            }
            addMessage(new ActionMessage("permit.facility.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
