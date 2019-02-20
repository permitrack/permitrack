package com.sehinc.common.service.spring.impl;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.message.EmailMessage;
import com.sehinc.common.message.MessageHeader;
import com.sehinc.common.message.TextEmailMessage;
import com.sehinc.common.service.spring.EmailService;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailServiceImpl
    implements EmailService
{
    private static
    Logger
        LOG =
        Logger.getLogger(EmailServiceImpl.class);
    private
    Session
        session;
    private
    Properties
        props;

    public EmailServiceImpl()
    {
        session =
            Session.getInstance(getMailProperties(),
                                null);
    }

    public void sendEmail(List<String> recipients, String message, String subject, EcProject project)
        throws MessagingException
    {
        sendEmail(recipients,
                  message,
                  subject,
                  true,
                  project);
    }

    public void sendEmailExceptionHandled(List<String> recipients, String message, String subject, EcProject project)
    {
        try
        {
            sendEmail(recipients,
                      message,
                      subject,
                      false,
                      project);
        }
        catch (MessagingException e)
        {
            LOG.info(e);
        }
    }

    private void sendEmail(List<String> recipients, String message, String subject, boolean throwException, EcProject project)
        throws MessagingException
    {
        for (String recipient : recipients)
        {
            try
            {
                TextEmailMessage
                    email =
                    new TextEmailMessage();
                email.setFrom(getFromAddress(project));
                email.setRecipients(Message.RecipientType.TO,
                                    stringToInternetAddressArray(recipient));
                email.setSubject(subject);
                email.setMultipartContent(createMimeMultipart(message));
                email.setSentDate(new Date());
                send(email);
            }
            catch (MessagingException e)
            {
                LOG.error(ApplicationResources.getProperty("project.error.sending.email"));
                LOG.error(e.getMessage());
                if (throwException)
                {
                    throw e;
                }
            }
        }
    }

    private void send(EmailMessage email)
        throws MessagingException
    {
        LOG.debug("EmailService: Sending Message");
        Message
            mimeMessage =
            email.getMimeMessage(session);
        LOG.debug("EmailService: Got mimeMessage");
        Transport
            tr =
            session.getTransport("smtp");
        LOG.debug("EmailService: Got SMTP transport");
        try
        {
            tr.connect(props.getProperty("mail.smtp.host"),
                       props.getProperty("mail.smtp.user"),
                       props.getProperty("mail.smtp.password"));
            LOG.debug("EmailService: Trasport connect successful");
            mimeMessage.saveChanges();
            LOG.debug("EmailService: mimeMessage.saveChanges()");
            tr.sendMessage(mimeMessage,
                           mimeMessage.getAllRecipients());
            LOG.debug("EmailService: Transport sendMessage()");
        }
        finally
        {
            tr.close();
            LOG.debug("EmailService: Transport close()");
        }
    }

    private Properties getMailProperties()
    {
        props =
            new Properties();
        props.setProperty("mail.smtp.host",
                          ApplicationProperties.getProperty("mail.smtp.host"));
        props.setProperty("mail.smtp.user",
                          ApplicationProperties.getProperty("mail.smtp.user"));
        props.setProperty("mail.smtp.password",
                          ApplicationProperties.getProperty("mail.smtp.password"));
        props.setProperty("mail.debug",
                          ApplicationProperties.getProperty("mail.debug"));
        return props;
    }

    private Multipart createMimeMultipart(String message)
        throws MessagingException
    {
        Multipart
            mp =
            new MimeMultipart();
        mp.addBodyPart(createMessageBody(message));
        return mp;
    }

    private Address[] getFromAddress(EcProject project)
        throws AddressException
    {
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
/*
        try
        {
            if (project
                != null)
            {
                CapContact
                    contact =
                    CapContact.getActiveById(project.getPermitAuthorityClient()
                                                 .getContactId());
                fromAddress =
                    contact.getEmail();
            }
        }
        catch (Exception e)
        {
        }
*/
        return stringToInternetAddressArray(fromAddress);
    }

    private Address[] stringToInternetAddressArray(String addressString)
        throws AddressException
    {
        return new Address[] {new InternetAddress(addressString)};
    }

    private MimeBodyPart createMessageBody(String message)
        throws MessagingException
    {
        MimeBodyPart
            body =
            new MimeBodyPart();
        body.setText(message);
        body.setHeader(MessageHeader.CONTEXT_TYPE
                           .toString(),
                       TextEmailMessage.CONTEXT_TYPE_VALUE);
        return body;
    }
}
