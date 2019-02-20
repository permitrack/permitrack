package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.client.EcClientInformation;
import com.sehinc.common.message.TextEmailMessage;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Iterator;

public abstract class PartnerBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerBaseAction.class);

    public abstract ActionForward partnerAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In ClientBaseAction");
        return partnerAction(mapping,
                             form,
                             request,
                             response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PARTNER_MENU_ITEM_NAME);
    }

    public void sendEmail(String toAddress, String fromAddress, HashSet<String> bccAddressSet, String subject, String message)
        throws MessagingException
    {
        TextEmailMessage
            emailMessage =
            new TextEmailMessage();
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
        if (!bccAddressSet.isEmpty())
        {
            InternetAddress[]
                bccAddrs =
                new InternetAddress[bccAddressSet.size()];
            Iterator<String>
                bccIter =
                bccAddressSet.iterator();
            int
                i =
                0;
            while (bccIter.hasNext())
            {
                String
                    bccAddr =
                    bccIter.next();
                bccAddrs[i++] =
                    new InternetAddress(bccAddr);
            }
            LOG.debug("BCC="
                      + bccAddrs.toString());
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

    public void sendPartnerEmail(UserValue toUser, ClientData partnerClientData, ClientValue clientValue)
        throws MessagingException
    {
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
        HashSet<String>
            bccAddressSet =
            new HashSet<String>();
        EcClientInformation
            clientInformation =
            null;
        try
        {
            clientInformation =
                EcClientInformation.findByClientId(clientValue.getId());
        }
        catch (HibernateException he)
        {
            LOG.warn("sendPartnerEmail: Could not locate EcClientInformation for client_id="
                     + clientValue.getId());
        }
        if (clientInformation
            != null)
        {
            if (!clientInformation.getGeneralReplyToEMail()
                .isEmpty())
            {
                bccAddressSet.add(clientInformation.getGeneralReplyToEMail());
            }
        }
        StringBuffer
            messageContent =
            new StringBuffer();
        messageContent.append(toUser.getFirstName())
            .append(",");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(clientValue.getName())
            .append(" has added you as a partner to Erosion Control (ESC).");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Your username and password are provided below.");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Username: ")
            .append(toUser.getUsername());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Password: ")
            .append(toUser.getPassword());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("You can access the PermiTrack Client Access Point here: ")
            .append(ApplicationProperties.getProperty("base.url"));
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("For more instructions, reference the Erosion Control (ESC) FAQ: ")
            .append(ApplicationProperties.getProperty("base.url"))
            .append(ApplicationProperties.getProperty("base.webapp.context"))
            .append("/help.do");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("To access PermiTrack technical support, you can send an email to ")
            .append(ApplicationProperties.getProperty("mail.support.address"))
            .append(" with the subject 'Customer Support', or call 612-284-6331.");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Thank you!");
        sendEmail(toUser.getEmailAddress(),
                  fromAddress,
                  bccAddressSet,
                  "Erosion Control (ESC) New Partner Notification - "
                  + partnerClientData.getName(),
                  messageContent.toString());
    }
}