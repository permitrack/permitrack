package com.sehinc.security.action.contact;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.user.AddressData;
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

public class ContactCreateAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactCreateAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strForward =
            CommonConstants.FORWARD_CONTINUE;
        String
            strMod =
            "com.sehinc.security.action.contact.ContactCreateAction.";
        String
            strLog =
            new String(strMod
                       + "userAction() ");
        String
            strError =
            new String(strLog);
        ContactForm
            objC;
        CapContactOrganization
            objCCO =
            new CapContactOrganization();
        Integer
            intOrganizationId =
            null;
        AddressData
            objA =
            new AddressData();
        Integer
            intContactId =
            null;
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("cancel.action"), request);
            return mapping.findForward("contact.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                Integer
                    intClientId =
                    getClientIdFromRequestOrSession(request);
                objC =
                    (ContactForm) form;
                try
                {
                    LOG.debug(strLog
                              + "objC.getorganizationId() "
                              + objC.getOrganizationId()
                        .toString());
                    if (objC.getOrganizationId()
                            .intValue()
                        == 0)
                    {
                        LOG.debug(strLog
                                  + "org id is zero");
                        if (objC.getOrganizationName()
                                .trim()
                                .length()
                            == 0)
                        {
                            LOG.debug(strLog
                                      + "User did not select an organization.  Getting client org id");
                            intOrganizationId =
                                ContactService.getClientContactOrganizationId(intClientId,
                                                                              objUser);
                        }
                        else
                        {
                            LOG.debug(strLog
                                      + "User is creating a new organization.");
                            objA.setLine1(objC.getOrganizationAddress());
                            objA.setLine2(objC.getOrganizationAddress2());
                            objA.setCity(objC.getOrganizationCity());
                            objA.setState(objC.getOrganizationState());
                            objA.setPostalCode(objC.getOrganizationZip());
                            Integer
                                addressId =
                                objA.insert(objUser);
                            objCCO.setClientId(intClientId);
                            objCCO.setIsClient(false);
                            objCCO.setName(objC.getOrganizationName());
                            objCCO.setAddressId(addressId);
                            objCCO.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                            intOrganizationId =
                                objCCO.insert(objUser);
                        }
                    }
                    else
                    {
                        LOG.debug(strLog
                                  + "User has selected an organization.");
                        CapContactOrganization
                            contactOrganization =
                            new CapContactOrganization(objC.getOrganizationId());
                        if (contactOrganization.load())
                        {
                            LOG.debug(strLog
                                      + "User selected org id is valid");
                            intOrganizationId =
                                objC.getOrganizationId();
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
                          "Error establishing contact organization for new contact.",
                          o);
                }
                LOG.debug(strLog
                          + "New Contact's organization id is "
                          + intOrganizationId.toString());
                objC.setOrganizationId(intOrganizationId);
                try
                {
                    objC.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                    intContactId =
                        ContactService.saveContact(objC,
                                                   getUserValue(request));
                    objC.setId(intContactId);
                    setMessage("New Contact "
                               + objC.getFirstName()
                               + " "
                               + objC.getLastName()
                               + " has been created.", request);
                }
                catch (Exception s)
                {
                    Throw(strLog,
                          "Error saving new contact to the CAP database.",
                          s);
                }
                try
                {
                    CapModule
                        module =
                        CapModule.findByCode(CommonConstants.SECURITY_MODULE);
                    CapContactType
                        contactType =
                        CapContactType.findSecurityGeneralType(module.getId());
                    ContactService.saveClientContact(intClientId,
                                                     intContactId,
                                                     contactType.getId());
                }
                catch (Exception s)
                {
                    Throw(strLog,
                          "Error saving generic new client contact to the CAP database.",
                          s);
                }
                try
                {
                    ContactService.saveClientContactFromContactForm(objC,
                                                                    intClientId);
                }
                catch (Exception s)
                {
                    Throw(strLog,
                          "Error saving new client contacts to the CAP database.",
                          s);
                }
                this.setContactIdInSession(intContactId, request);
            }
            catch (Exception eActive)
            {
                strError =
                    strError
                    + "Error.  Message: "
                    + eActive.getMessage();
                LOG.debug(strError);
                strForward =
                    "fail";
            }
        }
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
    }
}