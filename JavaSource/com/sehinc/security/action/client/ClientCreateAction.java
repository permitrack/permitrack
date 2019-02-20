package com.sehinc.security.action.client;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.client.EcClientInformation;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.message.HtmlEmailMessage;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.BaseAction;
import com.sehinc.security.action.user.UserBaseAction;
import com.sehinc.security.action.user.UserForm;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

public class ClientCreateAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientCreateAction.class);
    private static
    String
        strMod =
        "com.sehinc.security.action.client.ClientCreateAction. ";

    public void sendNewUserEmail(UserData user, Integer clientId)
        throws Exception
    {
        if (user
            == null
            || user.getEmailAddress()
               == null)
        {
            LOG.debug(strMod
                      + "sendNewUserEmail("
                      + (user
                         != null
                             ? user.getUsername()
                             : "")
                      + ", "
                      + clientId.toString()
                      + ": Unable to send user e-mail, no email address provided.");
        }
        else
        {
            String
                fromAddress =
                EcClientInformation.findGeneralReplyToByClientId(clientId);
            if (fromAddress
                == null
                || fromAddress
                   == "")
            {
                fromAddress =
                    ApplicationProperties.getProperty("mail.support.address");
            }
            String
                name =
                null;
            List
                clientDataList =
                ClientData.findById(clientId);
            Iterator
                iCD =
                clientDataList.iterator();
            if (iCD.hasNext())
            {
                ClientData
                    clientData =
                    (ClientData) iCD.next();
                name =
                    clientData.getName();
            }
            if (name
                == null)
            {
                name =
                    "Unknown";
            }
            StringBuffer
                messageContent =
                new StringBuffer();
            messageContent.append(user.getFirstName()
                                  + ",");
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append(name
                                  + " has added you as a new user to PermiTrack Client Access Point.");
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("Your username and password are provided below.");
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("Username: "
                                  + user.getUsername());
            messageContent.append(HtmlEmailMessage.NEWLINE);
            messageContent.append("Password: "
                                  + user.getPassword());
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("You can access the application by clicking on the following link to the PermiTrack Client Access Point: "
                                  + ApplicationProperties.getProperty("base.url"));
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("To access PermiTrack support, contact PermiTrack via email "
                                  + ApplicationProperties.getProperty("mail.support.address")
                                  + " with the subject \"Customer Support\", or call 612-284-6331.");
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("Thank you!");
            LOG.debug(strMod
                      + "sendNewUserEmail("
                      + user.getUsername()
                      + ", "
                      + clientId.toString()
                      + ": Message: "
                      + messageContent.toString());
            try
            {
                sendEmail(user.getEmailAddress(),
                          fromAddress,
                          "PermiTrack Client Access Point - New User Notification",
                          messageContent.toString());
            }
            catch (Exception exMail)
            {
                LOG.debug(strMod
                          + "sendNewUserEmail("
                          + user.getUsername()
                          + ", "
                          + clientId.toString()
                          + ": Unable to send user e-mail");
                throw new Exception(exMail);
            }
        }
    }

    private void sendEmail(String toAddress, String fromAddress, String subject, String message)
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

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        String
            strLog =
            strMod
            + "clientAction ";
        String
            strError =
            strLog;
        ActionMessages
            objClientErrors =
            new ActionMessages();
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("create.client.unauthorized"));
            addError(new ActionMessage("create.client.unauthorized"), request);
            return mapping.findForward("client.list.page");
        }
        if (isCancelled(request))
        {
            LOG.info(ApplicationResources.getProperty("create.client.canceled"));
            //addError(new ActionMessage("create.client.canceled"), request);
            return mapping.findForward("client.list.page");
        }
        ClientForm
            clientForm =
            (ClientForm) form;
        clientForm.setShortName(ClientService.getNewClientShortName(clientForm.getName()));
        clientForm.validateForm(objClientErrors);
        if (checkActionMessages(clientForm.getClientErrors(), request))
        {
            LOG.info("Validation of the client form failed."
                     + clientForm.getClientErrors()
                .toString());
            ActionMessages
                clientErrors =
                clientForm.getClientErrors();
            Iterator
                ei =
                clientErrors.get();
            while (ei.hasNext())
            {
                ActionMessage
                    errorTxt =
                    (ActionMessage) ei.next();
                addError(errorTxt, request);
            }
            return mapping.getInputForward();
        }
        if (clientForm.getId()
            != 0)
        {
            if (ClientService.clientExists(clientForm.getId()))
            {
                LOG.info(ApplicationResources.getProperty("create.client.already.exists"));
                addError(new ActionMessage("create.client.already.exists"), request);
                return mapping.findForward("client.create.page");
            }
        }
        try
        {
            clientForm.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
            clientForm.setId(ClientService.saveNewClient(clientForm,
                                                         getUserValue(request)));
            ClientData
                createdClient =
                ClientService.getActiveClientById(clientForm.getId());
            if (createdClient.getContactId()
                != null)
            {
                clientForm.setContactId(createdClient.getContactId());
            }
            this.setClientInSession(clientForm.getId(), request);
            setMessage("New client <b>"
                       + clientForm.getName()
                       + "</b > was created.", request);
        }
        catch (Exception c)
        {
            strError =
                strError
                + "Unable to save new client to the database.	Message: "
                + c.getMessage();
            LOG.debug(strError);
            addError(new ActionMessage("error.insert.client.failed"), request);
            return mapping.findForward("client.create.page");
        }
        if (clientForm.getContactAsUser())
        {
            Integer
                intUserId;
            try
            {
                intUserId =
                    saveClientMainContactAsUser(clientForm,
                                                getUserValue(request));
            }
            catch (Exception cue)
            {
                LOG.error("Failed to create new user for client "
                          + clientForm.getId());
                addError(new ActionMessage("error.create.user.failed"), request);
                return mapping.findForward("client.create.page");
            }
            try
            {
                LOG.debug("Sending new user email to user "
                          + intUserId.toString()
                          + " for clientId "
                          + clientForm.getId()
                    .toString());
                UserData
                    objU =
                    new UserData();
                objU.setId(intUserId);
                objU.load();
                sendNewUserEmail(objU,
                                 clientForm.getId());
            }
            catch (Exception emailEx)
            {
                strError =
                    strError
                    + "Unable to send email to new user.	<br>Message:<br>"
                    + emailEx.getMessage();
                LOG.debug(strError);
                addError(new ActionMessage("email.user.failed"), request);
                return mapping.findForward("continue");
            }
        }
        return mapping.findForward("continue");
    }

    private static Integer saveClientMainContactAsUser(ClientForm clientForm, UserValue objUser)
        throws Exception
    {
        String
            strLog =
            strMod
            + "saveClientMainContactAsUser() ";
        LOG.debug(strLog
                  + "in method.");
        UserData
            newUser =
            new UserData();
        try
        {
            if (clientForm
                == null)
            {
                strLog =
                    strLog
                    + "Client form is null.";
                LOG.debug(strLog);
                throw new Exception(strLog);
            }
            UserData
                user =
                UserData.findByContactId(clientForm.getContactId());
            if (user
                == null)
            {
                if (clientForm.getContactAsUser())
                {
                    UserForm
                        objUF =
                        new UserForm();
                    GroupData
                        group =
                        GroupData.findClientAdmin();
                    objUF.setGroupId(group.getId());
                    objUF.setUsername(SecurityManager.getUniqueUsername(clientForm.getContactFirstName(),
                                                                        clientForm.getContactLastName(),
                                                                        50));
                    objUF.setPassword(SecurityManager.generatePassword(8));
                    objUF.setFirstName(clientForm.getContactFirstName());
                    objUF.setLastName(clientForm.getContactLastName());
                    objUF.setTitle(clientForm.getContactTitle());
                    objUF.setAddressLine1(clientForm.getContactAddress());
                    objUF.setAddressLine2(clientForm.getContactAddress2());
                    objUF.setCity(clientForm.getContactCity());
                    objUF.setState(clientForm.getContactState());
                    objUF.setCity(clientForm.getContactCity());
                    objUF.setPostalCode(clientForm.getContactZip());
                    objUF.setClientId(clientForm.getId());
                    objUF.setClientName(clientForm.getName());
                    objUF.setEmailAddress(clientForm.getContactEMail());
                    objUF.setPrimaryPhone(clientForm.getContactPrimaryPhone());
                    objUF.setSecondaryPhone(clientForm.getContactSecondaryPhone());
                    objUF.setFaxPhone(clientForm.getContactFaxPhone());
                    objUF.setStatusCode(clientForm.getStatusCode());
                    objUF.setRoleIdDV(0);
                    objUF.setRoleIdEC(0);
                    objUF.setRoleIdSW(0);
                    try
                    {
                        newUser =
                            UserBaseAction.createUser(objUF,
                                                      clientForm.getId(),
                                                      objUser,
                                                      clientForm.getContactId());
                    }
                    catch (Exception e)
                    {
                        LOG.error("Failed to create new user");
                        LOG.error(e.getMessage());
                        throw e;
                    }
                    LOG.debug(strLog
                              + "New user created for client. User Id: "
                              + newUser.getId());
                    GroupData
                        groupData =
                        new GroupData(newUser.getGroupId());
                    groupData.load();
                    List
                        clientModuleList =
                        ClientModule.findAllClientModulesByClientId(clientForm.getId());
                    Iterator
                        mi =
                        clientModuleList.iterator();
                    while (mi.hasNext())
                    {
                        ClientModule
                            module =
                            (ClientModule) mi.next();
                        if (groupData
                            != null)
                        {
                            Integer
                                securityLevel =
                                groupData.getSecurityLevelId();
                            if (securityLevel.intValue()
                                <= SecurityManager.CLIENT_ADMINISTRATOR_SECURITY_LEVEL_ID)
                            {
                                CapUserModule
                                    userModule =
                                    CapUserModule.findByUserIdAndModuleId(newUser.getId(),
                                                                          module.getModuleId(),
                                                                          clientForm.getId());
                                if (userModule
                                    == null)
                                {
                                    LOG.debug("synchClientUserAccess(): Inserting new module access for client admin.");
                                    CapUserModule
                                        newModule =
                                        new CapUserModule();
                                    newModule.setModuleId(module.getModuleId());
                                    newModule.setUserId(newUser.getId());
                                    newModule.setClientId(clientForm.getId());
                                    newModule.setIsActive(true);
                                    newModule.insert();
                                }
                            }
                        }
                    }
                }
                else
                {
                    LOG.debug(strLog
                              + "New client main contact is not be be created as a new user.");
                }
            }
            else
            {
                strLog =
                    strLog
                    + "Contact Id: "
                    + clientForm.getContactId()
                    + " is already assocated with a user.	Unable to create a new user for the Client Main Contact";
                LOG.debug(strLog);
                throw new Exception(strLog);
            }
        }
        catch (Exception e)
        {
            strLog =
                strLog
                + "Error saving client main contact as user."
                + e.getMessage();
            LOG.debug(strLog);
            throw new Exception(strLog);
        }
        return newUser.getId();
    }
}