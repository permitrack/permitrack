package com.sehinc.service.servlet.util;

import com.sehinc.common.action.base.RequestKeys;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.message.TextEmailMessage;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgotPasswordServlet
    extends HttpServlet
{
    private static
    Logger
        log =
        Logger.getLogger(ForgotPasswordServlet.class);

    private void displayErrorPage(HttpServletRequest request, HttpServletResponse response, String msg)
        throws javax.servlet.ServletException, java.io.IOException
    {
        PortalUtils.forward("/sehsvc/error.do",
                            request,
                            response);
    }

    public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        performTask(request,
                    response);
    }

    public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        performTask(request,
                    response);
    }

    public String getServletInfo()
    {
        return super.getServletInfo();
    }

    public void init(ServletConfig configIn)
    {
    }

    public void performTask(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        log.debug("performTask: requests context path = "
                  + request.getContextPath());
        log.debug("performTask: servlet context = "
                  + request.getSession()
            .getServletContext());
        String
            action =
            request.getParameter(RequestKeys.ACTION_PARAMETER);
        if (action
            == null)
        {
            doForgotPasswordAction(request,
                                   response);
            return;
        }
        if (action.equalsIgnoreCase(RequestKeys.FORGOT_PASSWORD_ACTION))
        {
            doForgotPasswordAction(request,
                                   response);
        }
        else if (action.equalsIgnoreCase(RequestKeys.SEND_PASSWORD_ACTION))
        {
            doSendPasswordAction(request,
                                 response);
        }
        else
        {
            String
                msg =
                "The requested action '"
                + action
                + "' is undefined.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
        }
    }

    private void doForgotPasswordAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        try
        {
            request.getRequestDispatcher("/password/forgotPassword.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doForgotPasswordAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doForgotPasswordAction(): "
                      + ioe.getMessage());
        }
    }

    private void doSendPasswordAction(HttpServletRequest request, HttpServletResponse response)
        throws javax.servlet.ServletException, java.io.IOException
    {
        String
            username =
            request.getParameter(RequestKeys.FORGOT_PASSWORD_USERNAME);
        if (username
            == null)
        {
            String
                msg =
                "The USERNAME parameter is required to process this request.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        String
            email =
            request.getParameter(RequestKeys.FORGOT_PASSWORD_EMAIL);
        if (email
            == null)
        {
            String
                msg =
                "The EMAIL ADDRESS parameter is required to process this request.";
            log.error("performTask(): "
                      + msg);
            displayErrorPage(request,
                             response,
                             msg);
            return;
        }
        UserData
            user =
            UserData.findByUsernameAndEmail(username,
                                            email);
        if (user
            == null)
        {
            request.setAttribute(RequestKeys.FORGOT_PASSWORD_CONFIRMATION_MESSAGE,
                                 "We're sorry, we could not locate your account with the information you provided.<br/><br/>Please contact <a href='/sehsvc/contact' target='_blank'>PermiTrack technical support</a> for assistance.");
        }
        else
        {
            try
            {
                sendForgotPasswordEmail(user);
                request.setAttribute(RequestKeys.FORGOT_PASSWORD_CONFIRMATION_MESSAGE,
                                     "Congratulations!<br/><br/>We have sent your current password to "
                                     + user.getEmailAddress()
                                     + ".<br/><br/>Thank you.");
                request.setAttribute(RequestKeys.FORGOT_PASSWORD_IS_FOUND,
                                     "true");
            }
            catch (MessagingException mex)
            {
                request.setAttribute(RequestKeys.FORGOT_PASSWORD_CONFIRMATION_MESSAGE,
                                     "We're sorry, we could not send your password at this time.<br/><br/>Please contact PermiTrack technical support for assistance.");
            }
        }
        try
        {
            request.getRequestDispatcher("/password/confirmation.jsp")
                .forward(request,
                         response);
        }
        catch (ServletException se)
        {
            log.error("doSendPasswordAction(): "
                      + se.getMessage());
        }
        catch (IOException ioe)
        {
            log.error("doSendPasswordAction(): "
                      + ioe.getMessage());
        }
    }

    private void sendEmail(String fromAddress, String toAddress, String subject, String message)
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
        InternetAddress[]
            toAddrs =
            new InternetAddress[1];
        toAddrs[0] =
            new InternetAddress(toAddress);
        emailMessage.setFrom(fromAddr);
        emailMessage.setRecipients(Message.RecipientType.TO,
                                   toAddrs);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);
        log.info(emailMessage.toString());
        log.info("Before sending Forgot Password email");
        EmailService
            emailService =
            new EmailService();
        emailService.send(emailMessage);
        log.info("Successfully sent Forgot Password email");
    }

    private void sendForgotPasswordEmail(UserData userData)
        throws MessagingException
    {
        String
            fromEmail;
        String
            toEmail;
        fromEmail =
            ApplicationProperties.getProperty("mail.support.address");
        toEmail =
            userData.getEmailAddress();
        StringBuffer
            messageContent =
            new StringBuffer();
        messageContent.append("Dear "
                              + userData.getFirstName()
                              + ":  ");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Your PermiTrack Client Access Point account information is provided below.");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Account Name:  "
                              + userData.getUsername());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Password:  "
                              + userData.getPassword());
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("You can access the PermiTrack Client Access Point at "
                              + ApplicationProperties.getProperty("base.url"));
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("You can contact PermiTrack technical support via email at "
                              + ApplicationProperties.getProperty("mail.support.address")
                              + ", or call 612-284-6331.");
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append(TextEmailMessage.NEWLINE);
        messageContent.append("Thank you!");
        sendEmail(fromEmail,
                  toEmail,
                  "PermiTrack Client Access Point - Customer Inquiry Reply",
                  messageContent.toString());
    }
}
