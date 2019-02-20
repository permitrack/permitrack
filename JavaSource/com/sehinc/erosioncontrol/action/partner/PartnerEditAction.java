package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartnerEditAction
    extends PartnerBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerEditAction.class);

    public ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        PartnerForm
            partnerForm =
            (PartnerForm) form;
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            partnerForm.reset();
            return (mapping.findForward("partner.list.page"));
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.PARTNER_UPDATE))
        {
            addError(new ActionMessage("partner.error.update.not.authorized"),
                     request);
            return mapping.findForward("partner.list.page");
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
            partnerForm.getId();
        LOG.debug("partnerForm.getId() = "
                  + partnerForm.getId());
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
        if (partnerForm.getSelectedIndex()
            == null)
        {
            try
            {
                partnerForm.setClientInfo(partnerClientData);
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
            if (partnerForm.getSelectedIndex()
                != null
                && partnerForm.getSelectedIndex()
                   < partnerForm.getContactUserList().length)
            {
                Integer
                    selectedUserId =
                    partnerForm.getContactUserList()[partnerForm.getSelectedIndex()];
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
            if (partnerForm.getSelectedIndex()
                != null
                && partnerForm.getSelectedIndex()
                   < partnerForm.getContactEmailList().length)
            {
                selectedEmail =
                    partnerForm.getContactEmailList()[partnerForm.getSelectedIndex()];
            }
            if (selectedEmail
                == null)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            partnerForm.setClientInfo(partnerClientData);
            setSessionAttribute(SessionKeys.EC_PARTNER_USER_LIST,
                                UserService.findUserValuesByClient(new ClientValue(partnerClientData)),
                                request);
            LOG.info(new ActionMessage("partner.add.error.missing.email"));
            addError(new ActionMessage("partner.add.error.missing.email"),
                     request);
            return mapping.getInputForward();
        }
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
                refContactOrg.setClientId(clientValue.getId());
                refContactOrg.setRefClientId(partnerClientData.getId());
                refContactOrg.setIsClient(false);
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
                refContact.setEmail(partnerForm.getContactEmail());
            }
            else
            {
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
        try
        {
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
        addMessage(new ActionMessage("partner.update.success",
                                     partnerClientData.getName()),
                   request);
        return mapping.findForward("continue");
    }
}