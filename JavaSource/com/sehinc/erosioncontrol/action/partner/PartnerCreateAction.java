package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.*;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.*;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.security.RoleService;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class PartnerCreateAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerCreateAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception, ServletException
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        LOG.info("In PartnerCreateAction");
        request.setAttribute(RequestKeys.EC_PARTNER_FORM,
                             partnerForm);
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            partnerForm.reset();
            return (mapping.findForward("partner.list.page"));
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            LOG.error(PortalResources.getProperty("error.no.user.in.session"));
            addError(new ActionMessage("error.no.user.in.session"),
                     request);
            return mapping.findForward(CommonConstants.FORWARD_ERROR);
        }
        ClientValue
            clientValue =
            getClientValue(request);
        ClientData
            clientData =
            ClientService.getClient(clientValue);
        if (clientData
            == null)
        {
            Object[]
                parameters =
                {clientValue.getId()};
            LOG.error(ApplicationResources.getProperty("client.error.loading.client",
                                                       parameters));
            addError(new ActionMessage("client.error.loading.client",
                                       parameters),
                     request);
            return mapping.findForward(CommonConstants.FORWARD_ERROR);
        }
        String
            shortName =
            ClientService.getNewClientShortName(partnerForm.getName());
        LOG.debug("wpsGroupName="
                  + shortName);
        try
        {
            boolean
                isValid =
                securityManager.validateUserName(partnerForm.getContactUsername(),
                                                 null);
            LOG.debug("validateUserName() isValid = "
                      + isValid);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.username.not.valid",
                                       e.getMessage()),
                     request);
            return mapping.getInputForward();
        }
        EcClientRelationshipType
            clientRelationshipType =
            new EcClientRelationshipType(partnerForm.getClientRelationshipTypeId());
        try
        {
            if (!clientRelationshipType.load())
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            LOG.error("Could not load user selected client relationship type ID = "
                      + partnerForm.getClientRelationshipTypeId());
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.client.relationship.type.not.found",
                                       partnerForm.getClientRelationshipTypeId()),
                     request);
            return mapping.getInputForward();
        }
        AddressData
            partnerAddressData =
            new AddressData();
        partnerAddressData.setLine1(partnerForm.getAddressLine1());
        partnerAddressData.setLine2(partnerForm.getAddressLine2());
        partnerAddressData.setCity(partnerForm.getCity());
        partnerAddressData.setState(partnerForm.getState());
        partnerAddressData.setPostalCode(partnerForm.getPostalCode());
        CapState
            state =
            CapState.findByCode(partnerForm.getState());
        if (state
            != null)
        {
            partnerAddressData.setCountry(state.getCountry()
                                              .getCode());
        }
        partnerAddressData.save(userValue);
        ClientData
            partnerClientData =
            new ClientData();
        partnerClientData.setAddressId(partnerAddressData.getId());
        partnerClientData.setShortName(shortName);
        partnerClientData.setName(partnerForm.getName());
        partnerClientData.setContactName(partnerForm.getContactFirstName()
                                         + " "
                                         + partnerForm.getContactLastName());
        partnerClientData.setContactPhone(partnerForm.getContactPhone());
        partnerClientData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        ClientAdminSettingsData
            settings =
            new ClientAdminSettingsData();
        settings.setInspectionOverdueNotificationEnabled(false);
        settings.setSecondInspectionOverdueNotificationEnabled(false);
        settings.setProjectStatusEmailsEnabled(false);
        partnerClientData.setClientAdminSettings(settings);
        Integer
            partnerClientId =
            partnerClientData.save(userValue);
        partnerClientData.setId(partnerClientId);
        partnerClientData.load();
        CapContactOrganization
            partnerOrganization =
            new CapContactOrganization();
        partnerOrganization.setAddress(partnerAddressData);
        partnerOrganization.setClientId(partnerClientId);
        partnerOrganization.setRefClientId(partnerClientId);
        partnerOrganization.setIsClient(true);
        partnerOrganization.setName(partnerForm.getName());
        partnerOrganization.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        partnerOrganization.save(userValue);
        partnerOrganization.load();
        Collection
            capClientTypeList =
            CapClientType.getClientTypeByClientId(partnerClientId);
        Iterator
            cti =
            capClientTypeList.iterator();
        CapClientType
            capClientType;
        if (!cti.hasNext())
        {
            capClientType =
                new CapClientType();
            capClientType.setClientId(partnerClientId);
            capClientType.setClientTypeId(CapClientTypeInfo.findByName(CapClientTypeInfo.SECONDARY)
                                              .getId());
            capClientType.save();
        }
        CapModule
            ecModule =
            CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
        ClientModule
            ecClientModule =
            ClientModule.findClientModule(partnerClientId,
                                          CommonConstants.EROSION_CONTROL_MODULE);
        if (ecClientModule
            == null)
        {
            ecClientModule =
                new ClientModule();
            ecClientModule.setClientId(partnerClientId);
            ecClientModule.setModuleId(ecModule.getId());
            ecClientModule.insert();
        }
        CapModule
            secModule =
            CapModule.findByCode(CommonConstants.SECURITY_MODULE);
        ClientModule
            secClientModule =
            ClientModule.findClientModule(partnerClientId,
                                          CommonConstants.SECURITY_MODULE);
        if (secClientModule
            == null)
        {
            secClientModule =
                new ClientModule();
            secClientModule.setClientId(partnerClientId);
            secClientModule.setModuleId(secModule.getId());
            secClientModule.insert();
        }
        CapContact
            partnerContact;
        try
        {
            AddressData
                partnerContactAddress =
                AddressData.copyAddress(partnerAddressData);
            partnerContactAddress.save(userValue);
            partnerContact =
                new CapContact();
            partnerContact.setOrganization(partnerOrganization);
            partnerContact.setOrganizationName(partnerForm.getName());
            partnerContact.setFirstName(partnerForm.getContactFirstName());
            partnerContact.setLastName(partnerForm.getContactLastName());
            partnerContact.setAddressData(partnerContactAddress);
            partnerContact.setPrimaryPhone(partnerForm.getContactPhone());
            partnerContact.setEmail(partnerForm.getContactEmail());
            partnerContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            Integer
                partnerContactId =
                partnerContact.save(userValue);
            partnerClientData.setContactId(partnerContactId);
            partnerClientData.save(userValue);
            CapClientContact
                userClientContact =
                new CapClientContact();
            userClientContact.setClient(partnerClientData);
            userClientContact.setContact(partnerContact);
            userClientContact.setContactType(CapContactType.getUserContactType());
            userClientContact.save();
            CapClientContact
                mainClientContact =
                new CapClientContact();
            mainClientContact.setClient(partnerClientData);
            mainClientContact.setContact(partnerContact);
            mainClientContact.setContactType(CapContactType.getMainContactType());
            mainClientContact.save();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.adding.contact",
                                       partnerOrganization.getId(),
                                       ". Exception: "
                                       + e.getMessage()),
                     request);
            return mapping.getInputForward();
        }
        String
            newPassword =
            SecurityManager.generatePassword(8);
        AddressData
            partnerContactAddressData =
            AddressData.copyAddress(partnerAddressData);
        Integer
            partnerContactAddressId =
            partnerContactAddressData.save(userValue);
        UserData
            partnerUserData =
            new UserData();
        partnerUserData.setUsername(partnerForm.getContactUsername());
        partnerUserData.setPassword(newPassword);
        partnerUserData.setFirstName(partnerForm.getContactFirstName());
        partnerUserData.setLastName(partnerForm.getContactLastName());
        partnerUserData.setAddressId(partnerContactAddressId);
        partnerUserData.setEmailAddress(partnerForm.getContactEmail());
        partnerUserData.setPrimaryPhone(partnerForm.getContactPhone());
        partnerUserData.setContactId(partnerContact.getId());
        Calendar
            calendar =
            Calendar.getInstance();
        calendar.add(Calendar.DATE,
                     SecurityManager.PASSWORD_EXPIRATION_PERIOD);
        partnerUserData.setPasswordExpirationDate(calendar.getTime());
        partnerUserData.setPasswordChangeRequired(false);
        partnerUserData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        partnerUserData.setGroupId(GroupService.CLIENT_ADMINISTRATOR_GROUP_ID);
        Integer
            partnerUserId;
        try
        {
            partnerUserId =
                partnerUserData.insert(userValue);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.insert.user.failed",
                                       partnerForm.getContactUsername()),
                     request);
            return mapping.getInputForward();
        }
        try
        {
            ClientUserData
                clientUserData =
                new ClientUserData();
            clientUserData.setClientId(partnerClientId);
            clientUserData.setUserId(partnerUserId);
            clientUserData.insert();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.adding.user.to.client",
                                       partnerUserData.getUsername(),
                                       partnerClientData.getName()),
                     request);
            return mapping.getInputForward();
        }
        // Create a contact organization for the current client
        // that refers to the partner client.  This organization
        // is owned by the current client, but refers to the partner
        // First, create the reference client contact Address
        AddressData
            refAddressData =
            AddressData.copyAddress(partnerAddressData);
        refAddressData.save(userValue);
        // Now, create the ref organization
        CapContactOrganization
            refContactOrganization =
            new CapContactOrganization();
        refContactOrganization.setAddress(refAddressData);
        refContactOrganization.setClientId(clientValue.getId());  //This contact belongs to the current client
        refContactOrganization.setRefClientId(partnerClientId);   //It refers to the new partner
        refContactOrganization.setIsClient(false);  //This is not a client
        refContactOrganization.setName(partnerForm.getName());
        refContactOrganization.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        refContactOrganization.save(userValue);
        refContactOrganization.load();
        // Create a contact for the partner main contact and associate them with the current client's reference organization
        // that references the new partner
        try
        {
            AddressData
                refContactAddress =
                AddressData.copyAddress(partnerAddressData);
            refContactAddress.save(userValue);
            CapContact
                refContact =
                new CapContact();
            refContact.setOrganization(refContactOrganization);
            refContact.setOrganizationName(partnerForm.getName());
            refContact.setFirstName(partnerForm.getContactFirstName());
            refContact.setLastName(partnerForm.getContactLastName());
            refContact.setAddressData(refContactAddress);
            refContact.setPrimaryPhone(partnerForm.getContactPhone());
            refContact.setEmail(partnerForm.getContactEmail());
            refContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            refContact.save(userValue);
            //Define this contact as a contact for the current client
            CapClientContact
                userClientContact =
                new CapClientContact();
            userClientContact.setClient(clientData);
            userClientContact.setContact(refContact);
            userClientContact.save();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.adding.contact",
                                       refContactOrganization.getId()),
                     request);
            return mapping.getInputForward();
        }
        try
        {
            CapUserModule
                userModule =
                new CapUserModule();
            userModule.setUserId(partnerUserId);
            userModule.setModuleId(ecModule.getId());
            userModule.setClientId(partnerClientId);
            userModule.setIsActive(true);
            userModule.insert();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.granting.user.module.access",
                                       partnerUserId,
                                       CommonConstants.EROSION_CONTROL_MODULE),
                     request);
            return mapping.getInputForward();
        }
        try
        {
            CapUserModule
                userModule =
                new CapUserModule();
            userModule.setUserId(partnerUserId);
            userModule.setModuleId(secModule.getId());
            userModule.setClientId(partnerClientId);
            userModule.setIsActive(true);
            userModule.insert();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.granting.user.module.access",
                                       partnerUserId,
                                       CommonConstants.SECURITY_MODULE),
                     request);
            return mapping.getInputForward();
        }
        if (clientRelationshipType.getCode()
            .equalsIgnoreCase(EcClientRelationshipType.CODE_STANDARD))
        {
            try
            {
                CapRole
                    stdPartnerRole =
                    CapRole.findByCode(CommonConstants.EC_ROLE_STANDARD_PARTNER_CODE,
                                       CommonConstants.SEH_CLIENT_ID);
                if (stdPartnerRole
                    == null)
                {
                    LOG.error("Could not locate standard partner role EC_STDPTNR for client ID = "
                              + CommonConstants.SEH_CLIENT_ID);
                    addError(new ActionMessage("partner.error.standard.partner.role.not.found",
                                               CommonConstants.EC_ROLE_STANDARD_PARTNER_CODE,
                                               CommonConstants.SEH_CLIENT_ID),
                             request);
                    return mapping.getInputForward();
                }
                Integer
                    partnerRoleId =
                    RoleService.copyRole(stdPartnerRole.getId(),
                                         partnerClientId,
                                         userValue);
                CapUserRole
                    userRole =
                    new CapUserRole();
                userRole.setUserId(partnerUserId);
                userRole.setRoleId(partnerRoleId);
                userRole.insert();
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                addError(new ActionMessage("partner.error.copy.role.failed",
                                           CommonConstants.EC_ROLE_STANDARD_PARTNER_CODE,
                                           partnerClientId),
                         request);
                return mapping.getInputForward();
            }
        }
        EcClientRelationship
            clientRelationship =
            EcClientRelationship.findByClientAndRelatedClient(clientValue.getId(),
                                                              partnerClientId);
        if (clientRelationship
            == null)
        {
            clientRelationship =
                new EcClientRelationship();
            clientRelationship.setClientId(clientValue.getId());
            clientRelationship.setRelatedClientId(partnerClientId);
        }
        clientRelationship.setRelatedClientUserId(partnerUserId);
        clientRelationship.setClientRelationshipType(clientRelationshipType);
        try
        {
            clientRelationship.save();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.saving.client.relationship",
                                       clientValue.getName(),
                                       partnerForm.getName()),
                     request);
            return mapping.getInputForward();
        }
        EcClientInformation
            clientInformation =
            EcClientInformation.findByClientId(partnerClientId);
        if (clientInformation
            == null)
        {
            clientInformation =
                new EcClientInformation();
            clientInformation.setClientId(partnerClientId);
        }
        clientInformation.setGeneralReplyToEMail(partnerForm.getContactEmail());
        clientInformation.setPublicCommentEMail(partnerForm.getContactEmail());
        try
        {
            clientInformation.save();
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.saving.client.information",
                                       partnerForm.getName()),
                     request);
            return mapping.getInputForward();
        }
        try
        {
            sendPartnerEmail(new UserValue(partnerUserData),
                             partnerClientData,
                             clientValue);
        }
        catch (MessagingException mex)
        {
            LOG.error(mex.getMessage());
            addError(new ActionMessage("partner.error.sending.email"),
                     request);
            mapping.findForward("continue");
        }
        request.removeAttribute(RequestKeys.EC_PARTNER_FORM);
        addMessage(new ActionMessage("partner.create.success",
                                     partnerClientData.getName()),
                   request);
        return mapping.findForward("continue");
    }
}