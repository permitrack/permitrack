package com.sehinc.common.service.message;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.message.MessageData;
import com.sehinc.common.message.EmailMessage;
import com.sehinc.common.message.MessageHeader;
import org.apache.log4j.Logger;

import javax.mail.*;
import java.util.Properties;

public class EmailService
{
    private static
    Logger
        LOG =
        Logger.getLogger(EmailService.class);
    private
    Session
        session;

    public EmailService()
    {
        session =
            Session.getInstance(getMailProperties(),
                                null);
    }

    private Properties getMailProperties()
    {
        Properties
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

    public void send(EmailMessage emailMessage)
        throws MessagingException
    {
        try
        {
            LOG.debug("EmailService: Sending Message");
            javax.mail.Message
                mimeMessage =
                emailMessage.getMimeMessage(session);
            LOG.debug("EmailService: Got mimeMessage");
            Transport
                tr =
                session.getTransport("smtp");
            LOG.debug("EmailService: Got SMTP transport");
            tr.connect(ApplicationProperties.getProperty("mail.smtp.host"),
                       ApplicationProperties.getProperty("mail.smtp.user"),
                       ApplicationProperties.getProperty("mail.smtp.password"));
            LOG.debug("EmailService: Transport connect successful");
            mimeMessage.saveChanges();
            LOG.debug("EmailService: mimeMessage.saveChanges()");
            tr.sendMessage(mimeMessage,
                           mimeMessage.getAllRecipients());
            LOG.debug("EmailService: Transport sendMessage()");
            tr.close();
            LOG.debug("EmailService: Transport close()");
        }
        catch (MessagingException mex)
        {
            mex.printStackTrace();
            Exception
                ex =
                mex;
            do
            {
                if (ex instanceof SendFailedException)
                {
                    SendFailedException
                        sfex =
                        (SendFailedException) ex;
                    Address[]
                        invalid =
                        sfex.getInvalidAddresses();
                    if (invalid
                        != null)
                    {
                        LOG.error("    ** Invalid Addresses");
                        for (
                            int
                                i =
                                0; i
                                   < invalid.length; i++)
                        {
                            LOG.debug("         "
                                      + invalid[i]);
                            LOG.error("         "
                                      + invalid[i]);
                            MessageData
                                messageData =
                                new MessageData();
                            messageData.setTo(invalid[i].toString());
                            messageData.setFrom(emailMessage.getHeaderInfo(MessageHeader.FROM));
                            messageData.setSubject(emailMessage.getHeaderInfo(MessageHeader.SUBJECT));
                            messageData.setContextType(emailMessage.getHeaderInfo(MessageHeader.CONTEXT_TYPE));
                            messageData.setText(emailMessage.getMessage());
                        }
                    }
                    Address[]
                        validUnsent =
                        sfex.getValidUnsentAddresses();
                    if (validUnsent
                        != null)
                    {
                        LOG.error("    ** ValidUnsent Addresses");
                        for (
                            int
                                i =
                                0; i
                                   < validUnsent.length; i++)
                        {
                            LOG.error("         "
                                      + validUnsent[i]);
                            MessageData
                                messageData =
                                new MessageData();
                            messageData.setTo(validUnsent[i].toString());
                            messageData.setFrom(emailMessage.getHeaderInfo(MessageHeader.FROM));
                            messageData.setSubject(emailMessage.getHeaderInfo(MessageHeader.SUBJECT));
                            messageData.setContextType(emailMessage.getHeaderInfo(MessageHeader.CONTEXT_TYPE));
                            messageData.setText(emailMessage.getMessage());
                        }
                    }
                    Address[]
                        validSent =
                        sfex.getValidSentAddresses();
                    if (validSent
                        != null)
                    {
                        LOG.error("    ** ValidSent Addresses");
                        for (
                            int
                                i =
                                0; i
                                   < validSent.length; i++)
                        {
                            LOG.error("         "
                                      + validSent[i]);
                        }
                    }
                }
                if (ex instanceof MessagingException)
                {
                    ex =
                        ((MessagingException) ex).getNextException();
                }
                else
                {
                    ex =
                        null;
                }
            }
            while (ex
                   != null);
            throw mex;
        }
    }
}
