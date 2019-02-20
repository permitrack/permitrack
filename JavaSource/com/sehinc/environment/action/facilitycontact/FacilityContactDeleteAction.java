package com.sehinc.environment.action.facilitycontact;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.facility.EnvFacilityContact;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacilityContactDeleteAction
    extends FacilityContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityContactDeleteAction.class);

    public ActionForward facilityContactAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "FacilityContactDeleteAction. ";
        strLog =
            strMod
            + "facilityContactAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_FACILITY_CONTACT_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("facility.contact.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("facility.contact.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("facility.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("facility.contact.delete.cancel.action"), request);
            return mapping.findForward("facility.view.page");
        }
        else
        {
            Integer
                facilityContactId;
            LOG.debug("facilityContactId="
                      + request.getParameter(RequestKeys.EV_FACILITY_CONTACT_ID));
            if (request.getParameter(RequestKeys.EV_FACILITY_CONTACT_ID)
                != null)
            {
                facilityContactId =
                    new Integer(request.getParameter(RequestKeys.EV_FACILITY_CONTACT_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("facility.contact.error.no.facility.contact.on.request"));
                addError(new ActionMessage("facility.contact.error.no.facility.contact.on.request"), request);
                return mapping.findForward("facility.view.page");
            }
            EnvFacilityContact
                envFacilityContact =
                new EnvFacilityContact(facilityContactId);
            try
            {
                envFacilityContact.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {facilityContactId};
                LOG.error(ApplicationResources.getProperty("facility.contact.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("facility.contact.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("facility.view.page");
            }
            addMessage(new ActionMessage("facility.contact.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}