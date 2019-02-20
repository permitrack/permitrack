package com.sehinc.environment.action.facilitycontact;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.facility.EnvFacilityContact;
import com.sehinc.environment.db.lookup.EnvFacilityContactTypeData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityContactCreateAction
    extends FacilityContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityContactCreateAction.class);

    public ActionForward facilityContactAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "FacilityContactCreateAction. ";
        strLog =
            strMod
            + "facilityContactAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("facility.contact.create.cancel.action"), request);
            return mapping.findForward("facility.view.page");
        }
        else
        {
            try
            {
                FacilityContactForm
                    fcForm =
                    (FacilityContactForm) form;
                Integer
                    facilityId =
                    fcForm.getFacilityId();
                setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                    facilityId,
                                    request);
                setSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                                    fcForm.getFacilityName(),
                                    request);
                EnvFacility
                    facility =
                    new EnvFacility(facilityId);
                facility.load();
                fcForm.setFacilityId(facilityId);
                fcForm.setFacilityName(facility.getName());
                ClientValue
                    clientValue =
                    getClientValue(request);
                List
                    contacts =
                    CapContact.findByClientContactListAndStatus(clientValue.getId(),
                                                                EnvStatusCodeData.STATUS_CODE_ACTIVE);
                setSessionAttribute(SessionKeys.EV_CONTACT_ACTIVE_LIST_BY_CLIENT,
                                    contacts,
                                    request);
                List
                    roleTypes =
                    EnvFacilityContactTypeData.findAllByClientId(clientValue.getId());
                request.setAttribute(SessionKeys.EV_FACILITY_ROLES_LIST,
                                     roleTypes);
                if (fcForm.getContactId()
                    == 0)
                {
                    addMessage(new ActionMessage("facility.contact.select"), request);
                    return mapping.getInputForward();
                }
                EnvFacilityContact
                    dupFC =
                    EnvFacilityContact.findByFacilityIdAndContactId(fcForm.getFacilityId(),
                                                                    fcForm.getContactId());
                if (dupFC
                    != null)
                {
                    addMessage(new ActionMessage("facility.contact.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                EnvFacilityContact
                    dupeFC =
                    EnvFacilityContact.findByFacilityIdAndRole(fcForm.getFacilityId(),
                                                               fcForm.getRoleCd());
                if (dupeFC
                    != null)
                {
                    addMessage(new ActionMessage("facility.contact.duplicate.error2"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvFacilityContact
                        facilityContact =
                        new EnvFacilityContact();
                    facilityContact.setContactId(fcForm.getContactId());
                    facilityContact.setFacilityId(facilityId);
                    facilityContact.setRoleCd(fcForm.getRoleCd());
                    Integer
                        facilityContactId =
                        facilityContact.save();
                    LOG.debug(strLog
                              + "New Facility Contact created with ID = "
                              + facilityContactId.toString());
                }
            }
            catch (Exception eActive)
            {
                LOG.debug("Error.  Message: "
                          + eActive.getMessage());
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strLog
                                                  + "Error.  Message: "
                                                  + eActive.getMessage()),
                                    request);
            }
        }
        return mapping.findForward("continue");
    }
}