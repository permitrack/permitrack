package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.message.MessageHeader;
import com.sehinc.common.message.TextEmailMessage;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.report.ECProjectReport;
import com.sehinc.erosioncontrol.action.report.ECReportParameter;
import com.sehinc.erosioncontrol.db.project.EcProjectContact;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class ProjectEmailAction
    extends ProjectBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectEmailAction.class);

    public ActionForward projectAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, ClientValue clientValue, UserValue userValue, SecurityManager securityManager)
        throws SQLException, ServletException, Exception
    {
        ProjectEmailForm
            projectEmailForm =
            (ProjectEmailForm) form;
        LOG.info("In ProjectEmailAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            projectEmailForm.reset();
            return (mapping.findForward("project.list.page"));
        }
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("project.error.project.not.found.in.session"));
            addError(new ActionMessage("project.error.project.not.found.in.session"),
                     request);
            addError(new ActionMessage("project.error.email.not.sent"),
                     request);
            return mapping.findForward("project.list.page");
        }
        Integer[]
            projectContactList =
            projectEmailForm.getProjectContacts();
        if (projectContactList
            == null
            || projectContactList.length
               == 0)
        {
            return mapping.findForward("project.list.page");
        }
        HashMap
            toAddress =
            new HashMap();
        for (
            int
                i =
                0; i
                   < projectContactList.length; i++)
        {
            Integer
                projectContactId =
                projectContactList[i];
            EcProjectContact
                projectContact =
                new EcProjectContact();
            projectContact.setId(projectContactId);
            if (projectContact.load())
            {
                if (projectContact.getContact()
                    != null
                    && projectContact.getContact()
                           .getId()
                       != null)
                {
                    UserValue
                        uv =
                        UserService.getUserValueByContactId(projectContact.getContact()
                                                                .getId());
                    if (!StringUtil.isEmpty(projectContact.getContact()
                                                .getEmail()))
                    {
                        toAddress.put(projectContact.getContact()
                                          .getEmail(),
                                      uv);
                    }
                }
            }
        }
        //Get the FROM address
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
/*
        try
        {
            */
/*
            EcProject
                project =
                new EcProject(projectValue.getId());
            project.load();
            CapContact
                contact =
                CapContact.getActiveById(project.getPermitAuthorityClient()
                                             .getContactId());
            fromAddress =
                contact.getEmail();
            *//*

            if (userValue.getEmailAddress()
                != null
                && !userValue.getEmailAddress()
                .isEmpty())
            {
                fromAddress =
                    userValue.getEmailAddress();
            }
        }
        catch (Exception e)
        {
        }
*/
        // Run the project report and stream the output to the client.
        try
        {
            //Create the Project Report
            ECProjectReport
                ecProjectReport =
                new ECProjectReport();
            //Set report parameters
            ecProjectReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                               clientValue.getId());
            ecProjectReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                               projectValue.getId());
            //Create a PDF report runner
            PDFReportRunner
                pdfReportRunner =
                new PDFReportRunner(ecProjectReport);
            //Run the report
            pdfReportRunner.runToFile(request,
                                      getOutputFile(projectValue,
                                                    clientValue,
                                                    "PDF"));
            // Send an email to the new user with their username and password
            try
            {
                sendProjectEmail(fromAddress,
                                 toAddress,
                                 pdfReportRunner.getOutputFile(),
                                 projectValue,
                                 clientValue);
            }
            catch (MessagingException mex)
            {
                LOG.error(ApplicationResources.getProperty("project.error.sending.email"));
                LOG.error(mex.getMessage());
                addError(new ActionMessage("project.error.sending.email"),
                         request);
                return mapping.findForward("continue");
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("project.error.sending.email"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("project.error.sending.email"),
                         request);
                return mapping.findForward("continue");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {projectValue.getId()};
            LOG.error(ApplicationResources.getProperty("project.error.project.report.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.project.report.failed",
                                       parameters),
                     request);
            addError(new ActionMessage("project.error.email.not.sent"),
                     request);
            return mapping.findForward("continue");
        }
        //Set the success message
        addMessage(new ActionMessage("project.email.success"),
                   request);
        //Continue
        return mapping.findForward("continue");
    }

    private File getOutputFile(ProjectValue projectValue, ClientValue clientValue, String fileExt)
    {
        // Create the output file name based on the current time, user, and report format
        Date
            nowDate =
            new Date();
        String
            nowTime =
            String.valueOf(nowDate.getTime());
        String
            outputFileName =
            "project_report_"
            + projectValue.getId()
            + nowTime;
        String
            outputFilePath =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientValue.getId()
            + "/temp/";
        return new File(outputFilePath
                        + outputFileName
                        + "."
                        + fileExt.toLowerCase());
    }

    public void sendProjectEmail(String fromAddress, HashMap toAddressHash, File projectReportFile, ProjectValue projectValue, ClientValue clientValue)
        throws MessagingException
    {
        EmailService
            emailService =
            new EmailService();
        Iterator
            toAddressIter =
            toAddressHash.keySet()
                .iterator();
        while (toAddressIter.hasNext())
        {
            String
                toAddress =
                (String) toAddressIter.next();
            UserValue
                userValue =
                (UserValue) toAddressHash.get(toAddress);
            TextEmailMessage
                emailMessage =
                new TextEmailMessage();
            StringBuffer
                messageContent =
                new StringBuffer();
            messageContent.append(clientValue.getName()
                                  + " has recently created or modified the following project.");
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(projectValue.getName());
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(projectValue.getViewAddress());
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append("Please see the attached project report for more information.");
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            if (userValue
                != null)
            {
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("Your PermiTrack Client Access Point user name and password are provided below.");
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("User Name:  "
                                      + userValue.getUsername());
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("Password: "
                                      + userValue.getPassword());
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("You can access Erosion Control (ESC) at "
                                      + ApplicationProperties.getProperty("base.url")
                                      + ".");
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("To access PermiTrack technical support, you can send an email to "
                                      + ApplicationProperties.getProperty("mail.support.address")
                                      + " with a subject of 'Customer Support', or call 612-284-6331.");
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
            }
            messageContent.append("If you do not have Adobe Acrobat Reader you will need to install it on your computer in order to view this report.  Please see http://www.adobe.com/products/acrobat/readstep2.html for installation instructions.");
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append("Thank you!");
            // create and fill the first message part
            MimeBodyPart
                mbp1 =
                new MimeBodyPart();
            mbp1.setText(messageContent.toString());
            mbp1.setHeader(MessageHeader.CONTEXT_TYPE
                               .toString(),
                           TextEmailMessage.CONTEXT_TYPE_VALUE);
            mbp1.setHeader(MessageHeader.MIME_VERSION
                               .toString(),
                           TextEmailMessage.MIME_VERSION_VALUE);
            // create the second message part
            MimeBodyPart
                mbp2 =
                new MimeBodyPart();
            // attach the file to the message
            FileDataSource
                fds =
                new FileDataSource(projectReportFile);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
            // create the Multipart and add its parts to it
            Multipart
                mp =
                new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);
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
            emailMessage.setSubject("Erosion Control (ESC) Project Notification - "
                                    + projectValue.getName());
            emailMessage.setMultipartContent(mp);
            emailMessage.setSentDate(new Date());
            LOG.info("Before send email");
            emailService.send(emailMessage);
            LOG.info("Successfully sent email");
        }
    }
}