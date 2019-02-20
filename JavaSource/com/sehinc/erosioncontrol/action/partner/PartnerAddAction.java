package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PartnerAddAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerAddAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        PartnerAddForm
            partnerAddForm =
            (PartnerAddForm) form;
        LOG.info("In PartnerAddAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            partnerAddForm.reset();
            return (mapping.findForward("partner.list.page"));
        }
        UserValue
            userValue =
            getUserValue(request);
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
        Integer
            partnerClientId =
            partnerAddForm.getId();
        LOG.debug("partnerForm.getId() = "
                  + partnerAddForm.getId());
        if (partnerClientId
            == null)
        {
            LOG.error(ApplicationResources.getProperty("partner.error.missing.partner.id"));
            addError(new ActionMessage("partner.error.missing.partner.id"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        ClientData
            partnerClientData;
        try
        {
            partnerClientData =
                ClientService.getActiveClientById(partnerClientId);
            if (partnerClientData
                == null)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {partnerClientId};
            LOG.error(ApplicationResources.getProperty("partner.error.could.not.load.partner",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.could.not.load.partner",
                                       parameters),
                     request);
            return mapping.findForward("partner.list.page");
        }
        EcClientRelationshipType
            clientRelationshipType =
            new EcClientRelationshipType(partnerAddForm.getClientRelationshipTypeId());
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
                      + partnerAddForm.getClientRelationshipTypeId());
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.client.relationship.type.not.found",
                                       partnerAddForm.getClientRelationshipTypeId()),
                     request);
            return mapping.getInputForward();
        }
        if (partnerAddForm.getSelectedIndex()
            == null)
        {
            try
            {
                partnerAddForm.setClientInfo(partnerClientData);
                setSessionAttribute(SessionKeys.EC_PARTNER_USER_LIST,
                                    UserService.findUserValuesByClient(new ClientValue(partnerClientData)),
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {partnerClientData.getId()};
                LOG.error(ApplicationResources.getProperty("partner.error.loading.main.contact",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("partner.error.loading.main.contact",
                                           parameters),
                         request);
                return mapping.findForward("partner.list.page");
            }
            addError(new ActionMessage("partner.add.error.no.contact.selected"),
                     request);
            return mapping.getInputForward();
        }
        UserData
            selectedUser =
            null;
        try
        {
            if (partnerAddForm.getSelectedIndex()
                != null
                && partnerAddForm.getSelectedIndex()
                   < partnerAddForm.getContactUserList().length)
            {
                Integer
                    selectedUserId =
                    partnerAddForm.getContactUserList()[partnerAddForm.getSelectedIndex()];
                selectedUser =
                    UserService.getUser(selectedUserId);
            }
            if (selectedUser
                == null)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("partner.add.error.could.not.load.user"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.add.error.could.not.load.user"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        String
            selectedEmail =
            null;
        try
        {
            if (partnerAddForm.getSelectedIndex()
                != null
                && partnerAddForm.getSelectedIndex()
                   < partnerAddForm.getContactEmailList().length)
            {
                selectedEmail =
                    partnerAddForm.getContactEmailList()[partnerAddForm.getSelectedIndex()];
            }
            if (selectedEmail
                == null)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            partnerAddForm.setClientInfo(partnerClientData);
            setSessionAttribute(SessionKeys.EC_PARTNER_USER_LIST,
                                UserService.findUserValuesByClient(new ClientValue(partnerClientData)),
                                request);
            LOG.info(new ActionMessage("partner.add.error.missing.email: "
                                       + e.getMessage()));
            addError(new ActionMessage("partner.add.error.missing.email: "
                                       + e.getMessage()),
                     request);
            return mapping.getInputForward();
        }
        // Check to see if the parent client has an
        // organization that already references the partner being added
        CapContactOrganization
            refContactOrg;
        try
        {
            refContactOrg =
                CapContactOrganization.findByRefClientId(clientValue.getId(),
                                                         partnerClientData.getId());
            if (refContactOrg
                == null)
            {
                LOG.debug("Existing contact org is NULL");
                AddressData
                    newOrgAddressData =
                    AddressData.copyAddress(partnerClientData.getAddressId());
                newOrgAddressData.save(userValue);
                refContactOrg =
                    new CapContactOrganization();
                refContactOrg.setAddress(newOrgAddressData);
                refContactOrg.setClientId(clientValue.getId());  //This contact belongs to the current client
                refContactOrg.setRefClientId(partnerClientData.getId());   //It refers to the new partner
                refContactOrg.setIsClient(false);  //This is not a client
                refContactOrg.setName(partnerClientData.getName());
                refContactOrg.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                refContactOrg.save(userValue);
                refContactOrg.load();
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {
                    clientValue.getId(),
                    partnerClientData.getId()};
            LOG.error(ApplicationResources.getProperty("partner.error.could.not.create.contact.org",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.could.not.create.contact.org",
                                       parameters),
                     request);
            return mapping.findForward("partner.list.page");
        }
        // Only create new contacts if we created a new contact organization
        // Otherwise, continue
        // Check to see if the parent client has a contact for the selected user (same name)
        CapContact
            refContact;
        try
        {
            refContact =
                CapContact.getActiveByNameAndOrg(refContactOrg.getId(),
                                                 selectedUser.getFirstName(),
                                                 selectedUser.getLastName());
            if (refContact
                != null)
            {
                // We found a contact belongng to the parent's ref org for the partner
                // Update the contact's email address
                refContact.setEmail(partnerAddForm.getContactEmail());
            }
            else
            {
                // We need to create a new contact
                // Create a contact for the partner contact user and associate them with the parent client's reference organization
                // which references the new partner
                AddressData
                    refContactAddress =
                    AddressData.copyAddress(refContactOrg.getAddress());
                refContactAddress.save(userValue);
                refContact =
                    new CapContact();
                refContact.setOrganization(refContactOrg);
                refContact.setOrganizationName(refContactOrg.getName());
                refContact.setFirstName(selectedUser.getFirstName());
                refContact.setLastName(selectedUser.getLastName());
                refContact.setAddressData(refContactAddress);
                refContact.setPrimaryPhone(selectedUser.getPrimaryPhone());
                refContact.setEmail(selectedEmail);
                refContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                refContact.save(userValue);
                //Define this contact as a contact for the parent client
                CapClientContact
                    parentClientContact =
                    new CapClientContact();
                parentClientContact.setClient(clientData);
                parentClientContact.setContact(refContact);
                parentClientContact.save();
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.adding.contact",
                                       refContactOrg.getId()),
                     request);
        }
        // Create the client relationship between the parent client and the partner being added
        EcClientRelationship
            clientRelationship =
            EcClientRelationship.findByClientAndRelatedClient(clientValue.getId(),
                                                              partnerClientData.getId());
        if (clientRelationship
            == null)
        {
            clientRelationship =
                new EcClientRelationship();
            clientRelationship.setClientId(clientValue.getId());
            clientRelationship.setRelatedClientId(partnerClientData.getId());
        }
        clientRelationship.setRelatedClientUserId(selectedUser.getId());
        clientRelationship.setClientRelationshipType(clientRelationshipType);
        clientRelationship.save();
        //Previously: If the partner is being added as Preferred, set the user's account type to Client Administrator
        // Now, all partner users are added as Admins.
        if (selectedUser
            != null)
        {
            try
            {
                selectedUser.setGroupId(GroupData.CLIENT_ADMINISTRATOR_GROUP_ID);
                selectedUser.update(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {selectedUser.getId()};
                LOG.error(ApplicationResources.getProperty("partner.error.setting.main.contact.priviledges",
                                                           parameters));
                LOG.error(e.getMessage());
            }
        }
        // Send new partner notification email to the new user with their username and password
        try
        {
            // Send email to the partner contact user
            // Update the user's email address on the value (not permanantly)
            // so that we sent the mail to the correct address
            UserValue
                selectedUserValue =
                new UserValue(selectedUser);
            selectedUserValue.setEmailAddress(selectedEmail);
            sendPartnerEmail(selectedUserValue,
                             partnerClientData,
                             clientValue);
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("partner.error.sending.email"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("partner.error.sending.email"),
                     request);
            return mapping.findForward("partner.list.page");
        }
        addMessage(new ActionMessage("partner.add.success",
                                     partnerClientData.getName()),
                   request);
        return mapping.findForward("continue");
    }
}