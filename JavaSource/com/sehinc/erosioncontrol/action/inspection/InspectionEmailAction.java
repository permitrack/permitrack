package com.sehinc.erosioncontrol.action.inspection;

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
import com.sehinc.erosioncontrol.action.report.ECInspectionReport;
import com.sehinc.erosioncontrol.action.report.ECReportParameter;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContact;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
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

public class InspectionEmailAction
    extends InspectionBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionEmailAction.class);

    public ActionForward inspectionAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        InspectionEmailForm
            inspectionEmailForm =
            (InspectionEmailForm) form;
        LOG.info("In InspectionEmailAction");
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            inspectionEmailForm.reset();
            return (mapping.findForward("inspection.list.page"));
        }
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        InspectionValue
            inspectionValue =
            (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                  request);
        if (inspectionValue
            == null)
        {
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.not.found.in.session"));
            addError(new ActionMessage("inspection.error.inspection.not.found.in.session"),
                     request);
            addError(new ActionMessage("inspection.error.email.not.sent"),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        ProjectValue
            projectValue;
        EcProject
            project =
            new EcProject(inspectionValue.getProjectId());
        try
        {
            if (!project.load())
            {
                throw new Exception();
            }
            projectValue =
                ProjectService.getProjectValue(project,
                                               clientValue,
                                               userValue,
                                               securityManager);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionValue.getProjectId()};
            LOG.error(ApplicationResources.getProperty("project.error.loadingProject",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("project.error.loadingProject",
                                       parameters),
                     request);
            return mapping.findForward("inspection.list.page");
        }
        Integer[]
            projectContactList =
            inspectionEmailForm.getProjectContacts();
        if (projectContactList
            == null
            || projectContactList.length
               == 0)
        {
            addMessage(new ActionMessage("inspection.error.email.not.selected"),
                       request);
            return mapping.findForward("inspection.list.page");
        }
        //Build the TO address list
        //The TO list is a HashMap where:
        // key = (String) email address
        // value = (UserValue) the UserValue associated with the contact, or null if the contact is not a user
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
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
/*
        try
        {
            */
/*
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
        catch (Exception exception)
        {
        }
*/
        try
        {
            ECInspectionReport
                ecInspectionReport =
                new ECInspectionReport();
            ecInspectionReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                  clientValue.getId());
            ecInspectionReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                                  inspectionValue.getProjectId());
            ecInspectionReport.setReportParameter(ECReportParameter.INSPECTION_ID,
                                                  inspectionValue.getId());
            PDFReportRunner
                pdfReportRunner =
                new PDFReportRunner(ecInspectionReport);
            pdfReportRunner.runToFile(request,
                                      getOutputFile(inspectionValue,
                                                    clientValue,
                                                    "PDF"));
            try
            {
                sendInspectionEmail(fromAddress,
                                    toAddress,
                                    pdfReportRunner.getOutputFile(),
                                    projectValue,
                                    clientValue);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("inspection.error.sending.email"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("inspection.error.sending.email"),
                         request);
                return mapping.findForward("continue");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {inspectionValue.getId()};
            LOG.error(ApplicationResources.getProperty("inspection.error.inspection.report.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("inspection.error.inspection.report.failed",
                                       parameters),
                     request);
            addError(new ActionMessage("inspection.error.email.not.sent"),
                     request);
            return mapping.findForward("continue");
        }
        addMessage(new ActionMessage("inspection.email.success"),
                   request);
        return mapping.findForward("continue");
    }

    private File getOutputFile(InspectionValue inspectionValue, ClientValue clientValue, String fileExt)
    {
        Date
            nowDate =
            new Date();
        String
            nowTime =
            String.valueOf(nowDate.getTime());
        String
            outputFileName =
            "inspection_report_"
            + inspectionValue.getId()
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

    public void sendInspectionEmail(String fromAddress, HashMap toAddressHash, File inspectionReportFile, ProjectValue projectValue, ClientValue clientValue)
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
                                  + " has recently submitted an inspection report for the following project.");
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(projectValue.getName());
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(projectValue.getViewAddress());
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append("Please see the attached inspection report for more information.");
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
                                      + ApplicationProperties.getProperty("base.url"));
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append("To access PermiTrack technical support, you can send an email to "
                                      + ApplicationProperties.getProperty("mail.support.address")
                                      + " with a subject of 'Customer Support', or call 612-284-6331.");
                messageContent.append(TextEmailMessage.NEWLINE);
                messageContent.append(TextEmailMessage.NEWLINE);
            }
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append(TextEmailMessage.NEWLINE);
            messageContent.append("Thank you!");
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
            MimeBodyPart
                mbp2 =
                new MimeBodyPart();
            FileDataSource
                fds =
                new FileDataSource(inspectionReportFile);
            mbp2.setDataHandler(new DataHandler(fds));
            mbp2.setFileName(fds.getName());
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
            emailMessage.setSubject("Erosion Control (ESC) Inspection Notification - "
                                    + projectValue.getName());
            emailMessage.setMultipartContent(mp);
            emailMessage.setSentDate(new Date());
            LOG.info("Before send email");
            emailService.send(emailMessage);
            LOG.info("Successfully sent email");
        }
    }
}