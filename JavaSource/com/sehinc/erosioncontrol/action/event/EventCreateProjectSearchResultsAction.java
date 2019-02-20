package com.sehinc.erosioncontrol.action.event;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.project.ProjectSearchForm;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.event.EcEvent;
import com.sehinc.erosioncontrol.db.event.EcEventDocument;
import com.sehinc.erosioncontrol.db.event.EcEventProject;
import com.sehinc.erosioncontrol.db.event.EcEventType;
import com.sehinc.erosioncontrol.server.event.EventService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.hibernate.exception.GenericJDBCException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class EventCreateProjectSearchResultsAction
    extends EventBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventCreateProjectSearchResultsAction.class);

    public ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In EventCreateProjectSearchResultsAction");
        String
            strForward;
        EventCreateForm
            eventForm =
            (EventCreateForm) form;
        ProjectSearchForm
            projectSearchForm =
            eventForm.getProjectSearchForm();
        ClientValue
            clientValue =
            getClientValue(request);
        String[]
            selectedProjects =
            projectSearchForm.getSelectedProjects();
        setSessionAttribute(SessionKeys.EC_SELECTED_PROJECT_LIST,
                            selectedProjects,
                            request);
        UserValue
            userValue =
            getUserValue(request);
        if (eventForm.getNextPage()
            == null)
        {
            strForward =
                "error";
        }
        else if (eventForm.getNextPage()
                     .compareToIgnoreCase("next")
                 == 0)
        {
            strForward =
                "continue";
            try
            {
                EcEvent
                    event =
                    new EcEvent();
                event.setClient((ClientData) HibernateUtil.load(ClientData.class,
                                                                clientValue.getId()));
                event.setEventType((EcEventType) HibernateUtil.load(EcEventType.class,
                                                                    eventForm.getEventType()));
                event.setEventDate(eventForm.getEventDate());
                event.setDescription(eventForm.getDescription());
                event.setNotice(eventForm.getNotice());
                // MAKE AND SAVE THE HIBERNATE DATE OBJECT
                if (!eventForm.getEventType()
                    .equals(EventService.OTHER_EVENT_TYPE))
                {
                    if (!eventForm.getIsComplianceByDate()
                        .booleanValue()
                        && eventForm.getComplianceHours()
                           != null)
                    {
                        event.setComplianceHours(eventForm.getComplianceHours());
                        // TO MAKE THINGS EASIER FOR QUERIES, ALSO STORE THE COMPLIANCE DATE, (MAYBE WE DON'T NEED THE COMPIANCE_HOURS COLUMN FOR PURPOSE)
                        event.setComplianceDate(EventService.determineEventTimeFrameEnd(eventForm.getEventDate(),
                                                                                        eventForm.getComplianceHours()));
                    }
                    else
                    {
                        event.setComplianceDate(eventForm.getComplianceDate());
                    }
                }
                else
                {
                    Date
                        today =
                        new Date(); // use today's date for an "Other" event type.
                    event.setComplianceDate(today);
                }
                StatusCodeData
                    status =
                    new StatusCodeData();
                status.setCode(StatusCodeData.STATUS_CODE_ACTIVE);
                event.setStatus(status);
                event.save(userValue);
                LOG.debug("Event "
                          + event.getId()
                          + ", "
                          + event.getDescription()
                          + " saved to the database");
                // MAKE AND SAVE THE PROJECT-EVENT ASSOCIATION OBJECTS
                // the 'selectedProjects' is a String[] of the PROJECT_ID
                EcEventProject[]
                    eventProjects =
                    new EcEventProject[selectedProjects.length];
                int
                    i;
                for (
                    i =
                        0; i
                           < selectedProjects.length; i++)
                {
                    eventProjects[i] =
                        new EcEventProject();
                    try
                    {
                        int
                            projectId =
                            Integer.parseInt(selectedProjects[i]);
                        eventProjects[i].setProjectId(new Integer(projectId));
                    }
                    catch (Exception e)
                    {
                        LOG.debug("Error saving event project associations: "
                                  + e.getMessage());
                        setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                            "ERROR Saving Event Project Associations "
                                            + e.getMessage(),
                                            request);
                        return mapping.getInputForward();
                    }
                    eventProjects[i].setEventId(event.getId());
                    eventProjects[i].save();
                }
                // Save event document
                FormFile
                    eventFile =
                    eventForm.getFormFile();
                EcEventDocument
                    eventDocument =
                    new EcEventDocument();
                if (eventFile.getFileName()
                    != null
                    && eventFile.getFileName()
                           .trim()
                           .length()
                       > 0)
                {
                    LOG.debug("Trying to save the document: "
                              + eventFile.getFileName());
                    String
                        aFileLocation =
                        ApplicationProperties.getProperty("base.document.directory")
                        + "/client"
                        + event.getClient()
                            .getId()
                        + "/"
                        + ApplicationProperties.getProperty("application.erosioncontrol")
                        + "/event"
                        + event.getId()
                        + "/";
                    eventDocument.setEventId(event.getId());
                    eventDocument.setLocation(aFileLocation);
                    eventDocument.setName(eventFile.getFileName());
                    try
                    {
                        eventDocument.save();
                        processDocument(eventFile,
                                        aFileLocation);
                    }
                    catch (Exception e)
                    {
                        LOG.debug("Error saving event document: "
                                  + e.getMessage());
                        setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                            "ERROR Saving Event Document "
                                            + e.getMessage(),
                                            request);
                        return mapping.getInputForward();
                    }
                }
                // Call EventService.sendEventNotice(eventProjects) to send
                // email notices to all clients related to projects on this event
                try
                {
                    EventService.sendEventNotice(event.getId());
                }
                catch (MessagingException me)
                {
                    LOG.debug("Error Sending Event Notice: "
                              + me.getMessage());
                    setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                        "ERROR Sending Event Notice "
                                        + me.getMessage(),
                                        request);
                    return mapping.getInputForward();
                }
                catch (Exception e)
                {
                    LOG.debug("Error Sending Event Notice: "
                              + e.getMessage());
                    setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                        "ERROR Sending Event Notice "
                                        + e.getMessage(),
                                        request);
                    return mapping.getInputForward();
                }
                LOG.info(eventProjects.length
                         + " EcEventProject saved to the database for EcEvent.id = "
                         + event.getId());
                setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                    "You have successfully created a new <b>Event</b>.  Email notifications have been sent to all appropriate project contacts.",
                                    request);
            }
            catch (Exception e)
            {
                LOG.debug("Error saving Event to the database, exception: "
                          + e);
                setSessionAttribute(SessionKeys.EC_EVENT_MESSAGE,
                                    "ERROR:  An error occured while trying to save the Event to the database.",
                                    request);
                if (e instanceof GenericJDBCException)
                {
                    GenericJDBCException
                        ge =
                        (GenericJDBCException) e;
                    String[]
                        messages =
                        ge.getMessages();
                    for (
                        int
                            i =
                            0; i
                               < messages.length; i++)
                    {
                        LOG.debug("message:"
                                  + messages[i]);
                    }
                }
                return mapping.getInputForward();
            }
        }
        else
        {
            strForward =
                eventForm.getNextPage()
                    .toLowerCase();
        }
        return mapping.findForward(strForward);
    }

    private void processDocument(FormFile file, String aFileLocation)
        throws Exception
    {
        InputStream
            stream =
            file.getInputStream();
        File
            aNewFile =
            new File(aFileLocation);
        aNewFile.mkdirs();
        OutputStream
            bos =
            new FileOutputStream(aFileLocation
                                 + "/"
                                 + file.getFileName());
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       stream.read(buffer,
                                   0,
                                   8192))
               != -1)
        {
            bos.write(buffer,
                      0,
                      bytesRead);
        }
        bos.close();
        stream.close();
    }
}