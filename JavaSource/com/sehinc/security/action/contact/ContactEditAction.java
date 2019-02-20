package com.sehinc.security.action.contact;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class ContactEditAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactEditAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.user.ContactEditAction. ";
        String
            strLog =
            new String(strMod
                       + "userAction() ");
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("cancel.action"), request);
            return mapping.findForward("contact.view.page");
        }
        else
        {
            try
            {
                ContactForm
                    contactForm =
                    (ContactForm) form;
                UserValue
                    objUser =
                    getUserValue(request);
                Integer
                    intClientId =
                    getClientIdFromRequestOrSession(request);
                Integer
                    intContactId =
                    contactForm.getId();
                Integer
                    intOrganizationId =
                    null;
                try
                {
                    if (contactForm.getOrganizationId()
                            .intValue()
                        == 0)
                    {
                        LOG.debug(strLog
                                  + "org id is zero. User is creating a new organization.");
                        AddressData
                            objA =
                            new AddressData();
                        objA.setLine1(contactForm.getOrganizationAddress());
                        objA.setLine2(contactForm.getOrganizationAddress2());
                        objA.setCity(contactForm.getOrganizationCity());
                        objA.setState(contactForm.getOrganizationState());
                        objA.setPostalCode(contactForm.getOrganizationZip());
                        Integer
                            addressId =
                            objA.insert(objUser);
                        CapContactOrganization
                            objCCO =
                            new CapContactOrganization();
                        objCCO.setClientId(intClientId);
                        objCCO.setIsClient(false);
                        objCCO.setName(contactForm.getOrganizationName());
                        objCCO.setAddressId(addressId);
                        objCCO.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                        intOrganizationId =
                            objCCO.insert(objUser);
                    }
                    else
                    {
                        LOG.debug(strLog
                                  + "User has selected an organization.");
                        CapContactOrganization
                            contactOrganization =
                            new CapContactOrganization(contactForm.getOrganizationId());
                        if (contactOrganization.load())
                        {
                            LOG.debug(strLog
                                      + "User selected org id is valid");
                            intOrganizationId =
                                contactForm.getOrganizationId();
                        }
                        else
                        {
                            LOG.debug(strLog
                                      + "User selected org id not valid.  Get client or id.");
                            intOrganizationId =
                                ContactService.getClientContactOrganizationId(intClientId,
                                                                              objUser);
                        }
                    }
                }
                catch (Exception o)
                {
                    Throw(strLog,
                          "Error establishing contact organization for edited contact.",
                          o);
                }
                LOG.debug(strLog
                          + "New Contact's organization id is "
                          + intOrganizationId.toString());
                contactForm.setOrganizationId(intOrganizationId);
                try
                {
                    intContactId =
                        ContactService.saveContact(contactForm,
                                                   getUserValue(request));
                    contactForm.setId(intContactId);
                    setMessage("Existing Contact "
                               + contactForm.getFirstName()
                               + " "
                               + contactForm.getLastName()
                               + " has been saved.", request);
                }
                catch (Exception s)
                {
                    Throw(strLog,
                          "Error saving contact to the CAP database.",
                          s);
                }
                CapModule
                    module =
                    CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
                List
                    ccContactList =
                    CapClientContact.findModuleSpecificTypes(intClientId,
                                                             intContactId,
                                                             module.getId());
                Iterator
                    ccci =
                    ccContactList.iterator();
                while (ccci.hasNext())
                {
                    CapClientContact
                        ccContact =
                        (CapClientContact) ccci.next();
                    ccContact.delete();
                }
                try
                {
                    ContactService.saveClientContactFromContactForm(contactForm,
                                                                    intClientId);
                }
                catch (Exception s)
                {
                    Throw(strLog,
                          "Error saving existing client contact to the CAP database.",
                          s);
                }
                this.setContactIdInSession(intContactId, request);
                try
                {
                    UserData
                        userData =
                        UserData.findByContactId(intContactId);
                    if (userData
                        != null)
                    {
                        userData.setFirstName(contactForm.getFirstName());
                        userData.setLastName(contactForm.getLastName());
                        userData.setTitle(contactForm.getTitle());
                        userData.setEmailAddress(contactForm.getEmail());
                        userData.setPrimaryPhone(contactForm.getPrimaryPhone());
                        userData.setSecondaryPhone(contactForm.getSecondaryPhone());
                        userData.setFaxPhone(contactForm.getFaxPhone());
                        try
                        {
                            userData.update(objUser);
                        }
                        catch (Exception x)
                        {
                            Throw(strLog,
                                  "Error updating the user with updated related contact information.",
                                  x);
                        }
                        LOG.debug(strLog
                                  + "User update with contact information.  User Id: "
                                  + userData.getId()
                                  + " contact id: "
                                  + contactForm.getId());
                        AddressData
                            userAddress =
                            new AddressData(userData.getAddressId());
                        userAddress.load();
                        if (userAddress
                            != null)
                        {
                            userAddress.setLine1(contactForm.getAddress());
                            userAddress.setLine2(contactForm.getAddress2());
                            userAddress.setCity(contactForm.getCity());
                            userAddress.setState(contactForm.getStateCode());
                            userAddress.setPostalCode(contactForm.getZip());
                            try
                            {
                                userAddress.update(objUser);
                            }
                            catch (Exception au)
                            {
                                Throw(strLog,
                                      "Error updating the user's address with contact address data. address id: "
                                      +
                                      userData.getAddressId()
                                      + " for user id: "
                                      + userData.getId(),
                                      au);
                            }
                        }
                    }
                    ContactService.getContactForm(contactForm,
                                                  intClientId,
                                                  intContactId);
                }
                catch (Exception u)
                {
                    Throw(strLog,
                          "Error checking if contact is a user",
                          u);
                }
            }
            catch (Exception z)
            {
                Throw(strLog,
                      "Error saving contact information from edit",
                      z);
            }
        }
        return mapping.findForward("continue");
    }
}