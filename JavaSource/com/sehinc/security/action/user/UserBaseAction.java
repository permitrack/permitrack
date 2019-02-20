package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.CapClientContact;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.ClientUserData;
import com.sehinc.common.db.client.EcClientInformation;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.contact.CapContactType;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.CapUserRole;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.message.HtmlEmailMessage;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.client.ClientBaseAction;
import com.sehinc.security.action.navigation.PrimaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public abstract class UserBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserBaseAction.class);

    public abstract ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return userAction(mapping,
                          form,
                          request,
                          response);
    }

    public void sendChangePasswordUserEmail(UserData user, Integer clientId)
        throws Exception
    {
        ClientData
            clientData =
            new ClientData(clientId);
        if (!clientData.load())
        {
            LOG.error("No ClientData object found for clientId = "
                      + clientId.toString());
            throw new Exception("No ClientData object found for clientId = "
                                + clientId.toString());
        }
        String
            bccAddress =
            "";
        EcClientInformation
            ecClientInformation =
            EcClientInformation.findByClientId(clientId);
        if (ecClientInformation
            == null)
        {
            LOG.warn("No EcClientInformation object found for clientId = "
                     + clientId.toString());
        }
        else
        {
            bccAddress =
                ecClientInformation.getGeneralReplyToEMail();
        }
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
        StringBuffer
            messageContent =
            new StringBuffer();
        messageContent.append(user.getFirstName()
                              + ",");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(clientData.getName()
                              + " has changed your user password to PermiTrack Client Access Point.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Your username and password are provided below.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Username:  "
                              + user.getUsername());
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Password: "
                              + user.getPassword());
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("You can access the PermiTrack Client Access Point by clicking on the following link: "
                              + ApplicationProperties.getProperty("base.url"));
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("To access PermiTrack support, please send an email to "
                              + ApplicationProperties.getProperty("mail.support.address")
                              + " with a subject of 'Customer Support', or call 612-284-6331.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Thank you!");
        try
        {
            sendEmail(user.getEmailAddress(),
                      fromAddress,
                      bccAddress,
                      "PermiTrack Client Access Point - New User Notification",
                      messageContent.toString());
        }
        catch (Exception exMail)
        {
            LOG.error("sendChangePasswordUserEmail("
                      + user.getUsername()
                      + ", "
                      + clientId.toString()
                      + ": Unable to send user e-mail");
        }
    }

    public void sendNewUserEmail(UserData user, Integer clientId)
        throws Exception
    {
        ClientData
            clientData =
            new ClientData(clientId);
        if (!clientData.load())
        {
            LOG.error("No ClientData object found for clientId = "
                      + clientId.toString());
            throw new Exception("No ClientData object found for clientId = "
                                + clientId.toString());
        }
        String
            bccAddress =
            "";
        EcClientInformation
            ecClientInformation =
            EcClientInformation.findByClientId(clientId);
        if (ecClientInformation
            == null)
        {
            LOG.warn("No EcClientInformation object found for clientId = "
                     + clientId.toString());
        }
        else
        {
            bccAddress =
                ecClientInformation.getGeneralReplyToEMail();
        }
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
        StringBuffer
            messageContent =
            new StringBuffer();
        messageContent.append(user.getFirstName()
                              + ",");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(clientData.getName()
                              + " has added you as a new user to PermiTrack Client Access Point.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Your username and password are provided below.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Username:  "
                              + user.getUsername());
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Password: "
                              + user.getPassword());
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("You can access the PermiTrack Client Access Point by clicking on the following link: "
                              + ApplicationProperties.getProperty("base.url"));
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("To access PermiTrack technical support, you can send an email to: "
                              + ApplicationProperties.getProperty("mail.support.address")
                              + " with a subject of 'Customer Support', or call 612-284-6331.");
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append(HtmlEmailMessage.NEWLINE);
        messageContent.append("Thank you!");
        sendEmail(user.getEmailAddress(),
                  fromAddress,
                  bccAddress,
                  "PermiTrack Client Access Point - New User Notification",
                  messageContent.toString());
    }

    private void sendEmail(String toAddress, String fromAddress, String bccAddress, String subject, String message)
        throws MessagingException
    {
        HtmlEmailMessage
            emailMessage =
            new HtmlEmailMessage();
        InternetAddress[]
            fromAddr =
            new InternetAddress[1];
        fromAddr[0] =
            new InternetAddress(fromAddress);
        emailMessage.setFrom(fromAddr);
        InternetAddress[]
            toAddrs =
            new InternetAddress[1];
        toAddrs[0] =
            new InternetAddress(toAddress);
        emailMessage.setRecipients(Message.RecipientType.TO,
                                   toAddrs);
        if (!StringUtil.isEmpty(bccAddress))
        {
            InternetAddress[]
                bccAddrs =
                new InternetAddress[1];
            bccAddrs[0] =
                new InternetAddress(bccAddress);
            emailMessage.setRecipients(Message.RecipientType.BCC,
                                       bccAddrs);
        }
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        LOG.info(emailMessage.toString());
        LOG.info("Before send email");
        EmailService
            emailService =
            new EmailService();
        emailService.send(emailMessage);
        LOG.info("Successfully sent email");
    }

    public UserForm getUserForm(UserForm userFormInstance, Integer userId, Integer clientId, HttpServletRequest request)
        throws Exception
    {
        LOG.debug("In getUserForm(Integer userId)");
        try
        {
            if (userId
                == null
                || userId
                   <= 0)
            {
                String
                    msg =
                    "Invalid User ID passed to UserBaseAction.getUserForm(Integer userId): "
                    + userId;
                LOG.error("Invalid User ID passed to UserBaseAction.getUserForm(Integer userId): "
                          + userId);
                addError(new ActionMessage("invalid.userid.passed",
                                           "UserBaseAction.getUserForm(Integer userId)",
                                           userId), request);
                throw new Exception(msg);
            }
            UserData
                userData =
                new UserData(userId);
            if (!userData.load())
            {
                LOG.debug("getUserForm() UserData object failed to load");
                addError(new ActionMessage("error.load.user.failed",
                                           userId), request);
                throw new Exception("Failed to load user ID "
                                    + userId);
            }
            CapContact
                contact =
                new CapContact();
            contact.setId(userData.getContactId());
            if (!contact.load())
            {
                LOG.error("Failed to load contact ID "
                          + userData.getContactId());
                contact =
                    new CapContact();
            }
            AddressData
                addressData =
                new AddressData();
            if (contact.getAddressData()
                != null)
            {
                addressData =
                    contact.getAddressData();
            }
            CapModule
                ecModule =
                CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
            Iterator
                ecUserRoleList =
                CapUserRole.findByModuleAndUser(ecModule.getId(),
                                                userData.getId())
                    .iterator();
            while (ecUserRoleList.hasNext())
            {
                CapUserRole
                    ecUserRole =
                    (CapUserRole) ecUserRoleList.next();
                CapRole
                    ecRole =
                    new CapRole(ecUserRole.getRoleId());
                if (ecRole.load())
                {
                    userFormInstance.setRoleIdEC(ecRole.getId());
                    userFormInstance.setRoleNameEC(ecRole.getName());
                }
            }
            CapModule
                dvModule =
                CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE);
            Iterator
                dvUserRoleList =
                CapUserRole.findByModuleAndUser(dvModule.getId(),
                                                userData.getId())
                    .iterator();
            while (dvUserRoleList.hasNext())
            {
                CapUserRole
                    dvUserRole =
                    (CapUserRole) dvUserRoleList.next();
                CapRole
                    dvRole =
                    new CapRole(dvUserRole.getId());
                if (dvRole.load())
                {
                    userFormInstance.setRoleIdDV(dvRole.getId());
                    userFormInstance.setRoleNameDV(dvRole.getName());
                }
            }
            CapModule
                evModule =
                CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
            Iterator
                evUserRoleList =
                CapUserRole.findByModuleAndUser(evModule.getId(),
                                                userData.getId())
                    .iterator();
            while (evUserRoleList.hasNext())
            {
                CapUserRole
                    evUserRole =
                    (CapUserRole) evUserRoleList.next();
                CapRole
                    evRole =
                    new CapRole(evUserRole.getRoleId());
                if (evRole.load())
                {
                    userFormInstance.setRoleIdEV(evRole.getId());
                    userFormInstance.setRoleNameEV(evRole.getName());
                }
            }
            boolean
                accessEC =
                false;
            boolean
                accessSW =
                false;
            boolean
                accessDV =
                false;
            boolean
                accessEV =
                false;
            CapUserModule
                userModule =
                CapUserModule.findByUserIdAndModuleCode(userData.getId(),
                                                        CommonConstants.EROSION_CONTROL_MODULE,
                                                        clientId);
            if (userModule
                != null)
            {
                accessEC =
                    true;
            }
            userModule =
                CapUserModule.findByUserIdAndModuleCode(userData.getId(),
                                                        CommonConstants.STORM_WATER_MODULE,
                                                        clientId);
            if (userModule
                != null)
            {
                accessSW =
                    true;
            }
            userModule =
                CapUserModule.findByUserIdAndModuleCode(userData.getId(),
                                                        CommonConstants.DATA_VIEW_MODULE,
                                                        clientId);
            if (userModule
                != null)
            {
                accessDV =
                    true;
            }
            userModule =
                CapUserModule.findByUserIdAndModuleCode(userData.getId(),
                                                        CommonConstants.ENVIRONMENT_MODULE,
                                                        clientId);
            if (userModule
                != null)
            {
                accessEV =
                    true;
            }
            userFormInstance.setId(userData.getId());
            userFormInstance.setStatusCode(userData.getStatus()
                                               .getCode());
            userFormInstance.setUsername(userData.getUsername());
            userFormInstance.setPassword(userData.getPassword());
            userFormInstance.setFirstName(contact.getFirstName());
            userFormInstance.setLastName(contact.getLastName());
            userFormInstance.setTitle(contact.getTitle());
            userFormInstance.setGroupId(userData.getGroupId());
            userFormInstance.setAddressId(addressData.getId());
            userFormInstance.setAddressLine1(addressData.getLine1());
            userFormInstance.setAddressLine2(addressData.getLine2());
            userFormInstance.setCity(addressData.getCity());
            userFormInstance.setState(addressData.getState());
            userFormInstance.setPostalCode(addressData.getPostalCode());
            userFormInstance.setEmailAddress(contact.getEmail());
            userFormInstance.setPrimaryPhone(contact.getPrimaryPhone());
            userFormInstance.setSecondaryPhone(contact.getSecondaryPhone());
            userFormInstance.setFaxPhone(contact.getFaxPhone());
            userFormInstance.setAccessEC(accessEC);
            userFormInstance.setAccessSW(accessSW);
            userFormInstance.setAccessDV(accessDV);
            userFormInstance.setAccessEV(accessEV);
            GroupData
                group =
                new GroupData(userData.getGroupId());
            if (group.load())
            {
                userFormInstance.setAccountType(group.getName());
            }
            return userFormInstance;
        }
        catch (Exception e)
        {
            String
                msg =
                "getUserForm() method failed for userId: "
                + userId
                + " Exception: "
                + e.getLocalizedMessage();
            LOG.error(msg);
            addError(new ActionMessage("load.user.form.failed",
                                       "UserBaseAction.getUserForm(Integer userId)",
                                       userId), request);
            throw new Exception(msg);
        }
    }

    public static UserData createUser(UserForm objNewUserForm, Integer clientId, UserValue createdByUser)
        throws Exception
    {
        return createUser(objNewUserForm,
                          clientId,
                          createdByUser,
                          0);
    }

    public static UserData createUser(UserForm objNewUserForm, Integer clientId, UserValue createdByUser, Integer contactId)
        throws Exception
    {
        AddressData
            addressData =
            new AddressData();
        CapContactOrganization
            contactOrganization =
            CapContactOrganization.findByClientId(clientId);
        if (contactOrganization
            == null)
        {
            LOG.error("CapContactOrganization does not exist for ClientId = "
                      + clientId);
            throw new Exception("CapContactOrganization does not exist for ClientId = "
                                + clientId);
        }
        if (contactId
            != null)
        {
            if (contactId
                == 0)
            {
                addressData.setLine1(objNewUserForm.getAddressLine1());
                addressData.setLine2(objNewUserForm.getAddressLine2());
                addressData.setName1(objNewUserForm.getAddressName1());
                addressData.setCity(objNewUserForm.getCity());
                addressData.setState(objNewUserForm.getState());
                addressData.setPostalCode(objNewUserForm.getPostalCode());
                addressData.save(createdByUser);
                CapContact
                    newContact =
                    new CapContact();
                newContact.setAddressData(addressData);
                newContact.setEmail(objNewUserForm.getEmailAddress());
                newContact.setFirstName(objNewUserForm.getFirstName());
                newContact.setLastName(objNewUserForm.getLastName());
                newContact.setMi((objNewUserForm.getMiddleName()
                                  != null
                                  && objNewUserForm.getMiddleName()
                                         .length()
                                     > 0)
                                     ? objNewUserForm.getMiddleName()
                    .charAt(0)
                                     : null);
                newContact.setTitle(objNewUserForm.getTitle());
                newContact.setOrganization(contactOrganization);
                newContact.setOrganizationName(contactOrganization.getName());
                newContact.setAddress(addressData.getLine1());
                newContact.setAddress2(addressData.getLine2());
                newContact.setCity(addressData.getCity());
                newContact.setStateCode(addressData.getState());
                newContact.setZip(addressData.getPostalCode());
                newContact.setPrimaryPhone(objNewUserForm.getPrimaryPhone());
                newContact.setSecondaryPhone(objNewUserForm.getSecondaryPhone());
                newContact.setFaxPhone(objNewUserForm.getFaxPhone());
                newContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                contactId =
                    newContact.save(createdByUser);
            }
            else
            {
                CapContact
                    contact =
                    new CapContact(contactId);
                contact.load();
                addressData.setId(contact.getAddressData()
                                      .getId());
                addressData.load();
            }
        }
        UserData
            newUser =
            new UserData();
        newUser.setUsername(SecurityManager.getUniqueUsername(objNewUserForm.getFirstName()
                                                                  .trim(),
                                                              objNewUserForm.getLastName()
                                                                  .trim(),
                                                              50));
        newUser.setPassword(SecurityManager.generatePassword(8));
        newUser.setFirstName(objNewUserForm.getFirstName());
        newUser.setLastName(objNewUserForm.getLastName());
        newUser.setTitle(objNewUserForm.getTitle());
        newUser.setAddressId(addressData.getId());
        newUser.setContactId(contactId);
        Integer
            groupId =
            objNewUserForm.getGroupId();
        if (groupId
            == null
            || groupId
               == 0)
        {
            GroupData
                group =
                GroupData.findUser();
            groupId =
                group.getId();
        }
        newUser.setGroupId(groupId);
        newUser.setPrimaryPhone(objNewUserForm.getPrimaryPhone());
        newUser.setSecondaryPhone(objNewUserForm.getSecondaryPhone());
        newUser.setFaxPhone(objNewUserForm.getFaxPhone());
        newUser.setEmailAddress(objNewUserForm.getEmailAddress());
        newUser.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        newUser.save(createdByUser);
        associateUserToClient(clientId,
                              contactId,
                              newUser);
        CapUserModule
            userModule =
            new CapUserModule();
        userModule.setIsActive(true);
        userModule.setModuleId(CapModule.findByCode(CommonConstants.CAP_USER)
                                   .getId());
        userModule.setUserId(newUser.getId());
        userModule.setClientId(clientId);
        userModule.save();
        userModule =
            new CapUserModule();
        userModule.setIsActive(true);
        userModule.setModuleId(CapModule.findByCode(CommonConstants.SECURITY_MODULE)
                                   .getId());
        userModule.setUserId(newUser.getId());
        userModule.setClientId(clientId);
        userModule.save();
        if (objNewUserForm.getAccessEC())
        {
            LOG.debug("userAction: Inserting ESC access for userId: "
                      + newUser.getId());
            CapUserModule
                module =
                new CapUserModule();
            module.setIsActive(true);
            module.setModuleId(CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE)
                                   .getId());
            module.setUserId(newUser.getId());
            module.setClientId(clientId);
            module.insert();
            createUserRole(objNewUserForm.getRoleIdEC(),
                           newUser);
        }
        if (objNewUserForm.getAccessSW())
        {
            LOG.debug("userAction: Inserting MS4 access for userId: "
                      + newUser.getId());
            CapUserModule
                module =
                new CapUserModule();
            module.setIsActive(true);
            module.setModuleId(CapModule.findByCode(CommonConstants.STORM_WATER_MODULE)
                                   .getId());
            module.setUserId(newUser.getId());
            module.setClientId(clientId);
            module.insert();
        }
        if (objNewUserForm.getAccessDV())
        {
            LOG.debug("userAction: Inserting DVO access for userId: "
                      + newUser.getId());
            CapUserModule
                module =
                new CapUserModule();
            module.setIsActive(true);
            module.setModuleId(CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE)
                                   .getId());
            module.setUserId(newUser.getId());
            module.setClientId(clientId);
            module.insert();
        }
        if (objNewUserForm.getAccessEV())
        {
            LOG.debug("userAction: Inserting ENV access for userId: "
                      + newUser.getId());
            CapUserModule
                module =
                new CapUserModule();
            module.setIsActive(true);
            module.setModuleId(CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE)
                                   .getId());
            module.setUserId(newUser.getId());
            module.setClientId(clientId);
            module.insert();
            createUserRole(objNewUserForm.getRoleIdEV(),
                           newUser);
        }
        return newUser;
    }

    public static void createUserRole(int roleId, UserData newUser)
    {
        int
            securityLevel =
            GroupService.getGroupSecurityLevel(newUser.getGroupId());
        if (securityLevel
            == SecurityManager.USER_SECURITY_LEVEL)
        {
            CapRole
                role =
                new CapRole(roleId);
            if (role.load())
            {
                CapUserRole
                    userRole =
                    new CapUserRole();
                userRole.setRoleId(role.getId());
                userRole.setUserId(newUser.getId());
                userRole.save();
            }
        }
    }

    public static void associateUserToClient(Integer clientId, Integer contactId, UserData newUser)
        throws Exception
    {
        ClientUserData
            clientUser =
            new ClientUserData();
        clientUser.setClientId(clientId);
        clientUser.setUserId(newUser.getId());
        clientUser.save();
        CapContact
            theContact =
            new CapContact(contactId);
        theContact.load();
        CapContactType
            userContactType =
            CapContactType.getUserContactType();
        CapClientContact
            clientContact =
            new CapClientContact();
        clientContact.setClient(ClientService.getActiveClientById(clientId));
        clientContact.setContact(theContact);
        clientContact.setContactType(userContactType);
        clientContact.save();
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws Exception
    {
        String
            strMod =
            "com.sehinc.security.action.user.UserBaseAction.";
        LOG.info(strMod
                 + "setPrimaryMenuItem in action.");
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_USER_MENU_ITEM_NAME);
    }
}
