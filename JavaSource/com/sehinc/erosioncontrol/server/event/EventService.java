package com.sehinc.erosioncontrol.server.event;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.message.HtmlEmailMessage;
import com.sehinc.common.message.MessageHeader;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.message.EmailService;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.project.ProjectSearchForm;
import com.sehinc.erosioncontrol.db.event.EcEvent;
import com.sehinc.erosioncontrol.db.event.EcEventDocument;
import com.sehinc.erosioncontrol.db.event.EcEventType;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.EcProjectContact;
import com.sehinc.erosioncontrol.db.project.EcProjectContactType;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import com.sehinc.erosioncontrol.value.event.EventTypeValue;
import com.sehinc.erosioncontrol.value.event.EventValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.*;

public class EventService
{
    private static
    Logger
        LOG =
        Logger.getLogger(EventService.class);
    public static
    Integer
        RAIN_EVENT_TYPE =
        new Integer(1);
    public static
    Integer
        DROUGHT_EVENT_TYPE =
        new Integer(2);
    public static
    Integer
        OTHER_EVENT_TYPE =
        new Integer(3);

    public static List getEventValueList(ClientValue clientValue)
    {
        ArrayList
            list =
            new ArrayList();
        for (
            Iterator
                iter =
                EcEvent.findByClientId(clientValue.getId())
                    .iterator(); iter.hasNext(); )
        {
            EcEvent
                event =
                (EcEvent) iter.next();
            LOG.debug(event.toString());
            list.add(new EventValue(event));
        }
        return list;
    }

    public static List getEventTypeList()
    {
        ArrayList
            list =
            new ArrayList();
        for (
            Iterator
                iter =
                HibernateUtil.findAll(EcEventType.class)
                    .iterator(); iter.hasNext(); )
        {
            EcEventType
                eventType =
                (EcEventType) iter.next();
            list.add(new EventTypeValue(eventType));
        }
        return list;
    }

    public static Date determineEventTimeFrameEnd(Date eventDate, Integer hours)
    {
        if (eventDate
            == null)
        {
            LOG.info("EventDate passed is null");
            return eventDate;
        }
        if (hours
            == null)
        {
            LOG.info("Hours passed is null");
            return eventDate;
        }
        Calendar
            calendar =
            new GregorianCalendar();
        int
            currentHours =
            calendar.get(Calendar.HOUR_OF_DAY);
        LOG.debug("*** EventService.determineEventTimeFrameEnd() - current hour of day = "
                  + currentHours);
        LOG.debug("*** EventService.determineEventTimeFrameEnd() - current getTime() = "
                  + calendar.getTime());
        int
            newHours =
            hours.intValue()
            + currentHours;
        //	Now look for end compliance times that roll into a new day and possibly a new year
        if (newHours
            > 24)
        {
            int
                newDays =
                newHours
                / 24;
            newHours %=
                24;
            if (newDays
                > 0)
            {
                int
                    currentYear =
                    calendar.get(Calendar.YEAR);
                int
                    daysPerYear =
                    365;
                if (((currentYear
                      % 4)
                     == 0)
                    && ((currentYear
                         % 100)
                        != 0))
                {
                    daysPerYear =
                        366;
                }
                int
                    dayOfYear =
                    calendar.get(Calendar.DAY_OF_YEAR)
                    + newDays;
                if (dayOfYear
                    > daysPerYear)
                {
                    int
                        newYear =
                        calendar.get(Calendar.YEAR)
                        + 1;
                    calendar.set(Calendar.YEAR,
                                 newYear);
                    dayOfYear %=
                        daysPerYear;
                }
                calendar.set(Calendar.DAY_OF_YEAR,
                             dayOfYear);
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY,
                     newHours);
        LOG.debug("*** EventService.determineEventTimeFrameEnd() - new hour of day = "
                  + calendar.get(Calendar.HOUR_OF_DAY));
        LOG.debug("*** EventService.determineEventTimeFrameEnd() - new getTime() = "
                  + calendar.getTime());
        return calendar.getTime();
    }

    public static List determineCompliantProjects(ClientValue clientValue, EventValue eventValue, SecurityManager securityManager)
    {
        ProjectSearchForm
            projectSearchForm =
            new ProjectSearchForm();
        String
            inspectionBegin =
            DateUtil.mdyDate(eventValue.getEventDate());
        String
            inspectionEnd =
            DateUtil.mdyDate(eventValue.getComplianceDate());
        projectSearchForm.setClientId(clientValue.getId());
        projectSearchForm.setEventId(eventValue.getId());
        projectSearchForm.setEventConcern("compliant");
        projectSearchForm.setInspectionDateBegin(inspectionBegin);
        projectSearchForm.setInspectionDateEnd(inspectionEnd);
        return getCompliantProjects(projectSearchForm,
                                    securityManager);
    }

    public static List determineNonCompliantProjects(ClientValue clientValue, EventValue eventValue, SecurityManager securityManager)
    {
        ProjectSearchForm
            projectSearchForm =
            new ProjectSearchForm();
        String
            inspectionBegin =
            DateUtil.mdyDate(eventValue.getEventDate());
        String
            inspectionEnd =
            DateUtil.mdyDate(eventValue.getComplianceDate());
        projectSearchForm.setClientId(clientValue.getId());
        projectSearchForm.setEventId(eventValue.getId());
        projectSearchForm.setEventConcern("noncompliant");
        projectSearchForm.setInspectionDateBegin(inspectionBegin);
        projectSearchForm.setInspectionDateEnd(inspectionEnd);
        return getCompliantProjects(projectSearchForm,
                                    securityManager);
    }

    //	Get a list of all the projects that meet the search criteria
    public static List getAllProjects(ProjectSearchForm form)
    {
        LOG.debug("*** EventService.getAllProjects() - form = "
                  + form);
        boolean
            inspectionData =
            false;
        boolean
            eventData =
            false;
        // AT THE END OF THIS METHOD WE COULD CHECK TO SEE THAT THE 'ands' VECTOR HAS MORE
        // THAN ZERO ITEMS IN IT, IF NOT, THE USER NEEDS TO ENTER MORE DATA ON THE FORM.
        // THE JSP VALIDATION SHOULD HAVE HANDLED THIS BEFORE WE GOT TO HERE THOUGH
        ArrayList
            ands =
            new ArrayList();
        if ((form.getName()
             != null)
            && (form.getName()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.name like '%"
                     + form.getName()
                .trim()
                     + "%' ");
        }
        if ((form.getDescription()
             != null)
            && (form.getDescription()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.description like '%"
                     + form.getDescription()
                .trim()
                     + "%' ");
        }
        if ((form.getParcelNumber()
             != null)
            && (form.getParcelNumber()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.parcelNumber like '%"
                     + form.getParcelNumber()
                .trim()
                     + "%' ");
        }
        if ((form.getPermitNumber()
             != null)
            && (form.getPermitNumber()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.permitNumber like '%"
                     + form.getPermitNumber()
                .trim()
                     + "%' ");
        }
        if ((form.getAddress()
             != null)
            && (form.getAddress()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.address like '%"
                     + form.getAddress()
                .trim()
                     + "%' ");
        }
        if ((form.getCity()
             != null)
            && (form.getCity()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.city like '%"
                     + form.getCity()
                .trim()
                     + "%' ");
        }
        if ((form.getState()
             != null)
            && (form.getState()
                    .trim()
                    .length()
                > 0))
        {
            ands.add(" project.state like '%"
                     + form.getState()
                .trim()
                     + "%' ");
        }
        // ZIP CODE
        // IF THERE IS A ',' DO A "IN" COMPARE
        // IF THERE IS NO ',' CHARACTER IN THE TEXT, DO A "LIKE" COMPARE
        if ((form.getZipCode()
             != null)
            && (form.getZipCode()
                    .trim()
                    .length()
                > 0))
        {
            String
                zipCode =
                form.getZipCode()
                    .trim();
            boolean
                commaFound =
                zipCode.indexOf(',')
                != -1;
            if (commaFound)
            {
                ands.add(" project.zip in ("
                         + zipCode
                         + ")");
            }
            else
            {
                ands.add(" project.zip like '%"
                         + zipCode
                         + "%' ");
            }
        }
        // ZONE
        LOG.debug("EventService.getAllProjects()- project ZONES: "
                  + form.getSelectedProjectZones());
        if (form.getSelectedProjectZones()
            != null)
        {
            String
                projectZones =
                commaSep(form.getSelectedProjectZones());
            if (projectZones.trim()
                    .length()
                > 0)
            {
                ands.add(" project.zone in ("
                         + projectZones
                         + ")");
            }
        }
        // AREA SIZE
        try
        {
            if (form.getAreaSizeMin()
                != null)
            {
                final
                int
                    min =
                    Integer.parseInt(form.getAreaSizeMin());
                ands.add(" project.areaSize >= "
                         + min
                         + " ");
            }
        }
        catch (Exception e)
        {
        }
        try
        {
            if (form.getAreaSizeMax()
                != null)
            {
                final
                int
                    max =
                    Integer.parseInt(form.getAreaSizeMax());
                ands.add(" project.areaSize <= "
                         + max
                         + " ");
            }
        }
        catch (Exception e)
        {
        }
        // CREATION DATE
        if ((form.getCreationDateMin()
             != null)
            && (form.getCreationDateMin()
                    .trim()
                    .length()
                > 0))
        {
            try
            {
                Date
                    creationDateMin =
                    DateUtil.parseDate(form.getCreationDateMin()
                                           .trim());
                ands.add(" project.createTimestamp >= '"
                         + DateUtil.mdyDate(creationDateMin)
                         + "' ");
            }
            catch (Exception e)
            {
            }
        }
        if ((form.getCreationDateMax()
             != null)
            && (form.getCreationDateMax()
                    .trim()
                    .length()
                > 0))
        {
            try
            {
                Date
                    creationDateMax =
                    DateUtil.parseDate(form.getCreationDateMax()
                                           .trim());
                ands.add(" project.createTimestamp <= '"
                         + DateUtil.mdyDate(creationDateMax)
                         + "' ");
            }
            catch (Exception e)
            {
            }
        }
        // -------------------------------------------------------------------------
        // STATUS CODE
        // 'ANY' with value of 0
        //  or 'ACTIVE', 'INACTIVE', 'DELETED' from the StatusCodeData class
        if (form.getProjectStatusAIB()
            != null)
        {
            String
                aida =
                form.getProjectStatusAIB()
                    .trim();
            if (!"0".equalsIgnoreCase(aida))
            {
                ands.add(" project.projectStatus = "
                         + aida);
            }
            else
            {
                ands.add(" project.projectStatus in (1,2,3)");
            }
        }
        if (form.getSelectedProjectTypes()
            != null)
        {
            String
                projectTypes =
                commaSep(form.getSelectedProjectTypes());
            if (projectTypes.trim()
                    .length()
                > 0)
            {
                ands.add(" project.projectType in ("
                         + projectTypes
                         + ")");
            }
        }
        if ((form.getInspectionDateBegin()
             != null)
            && (form.getInspectionDateBegin()
                    .trim()
                    .length()
                > 0))
        {
            inspectionData =
                true;
            try
            {
                Date
                    begin =
                    DateUtil.parseDate(form.getInspectionDateBegin()
                                           .trim());
                ands.add(" project.id in (select distinct inspection.projectId from EcInspection as inspection where inspection.inspectionDate >= '"
                         + DateUtil.mdyDate(begin)
                         + "') ");
            }
            catch (Exception e)
            {
            }
        }
        if ((form.getInspectionDateEnd()
             != null)
            && (form.getInspectionDateEnd()
                    .trim()
                    .length()
                > 0))
        {
            inspectionData =
                true;
            try
            {
                Date
                    end =
                    DateUtil.parseDate(form.getInspectionDateEnd()
                                           .trim());
                ands.add(" project.id in (select distinct inspection.projectId from EcInspection as inspection where inspection.inspectionDate <= '"
                         + DateUtil.mdyDate(end)
                         + "') ");
            }
            catch (Exception e)
            {
            }
        }
        if (form.getSelectedInspectionActions()
            != null)
        {
            String
                inspectionActions =
                commaSep(form.getSelectedInspectionActions());
            if (inspectionActions.trim()
                    .length()
                > 0)
            {
                inspectionData =
                    true;
                ands.add(" inspection.inspectionAction in ("
                         + inspectionActions
                         + ") and inspection.projectId = project.id ");
            }
        }
        if (form.getSelectedInspectionReasons()
            != null)
        {
            String
                inspectionReasons =
                commaSep(form.getSelectedInspectionReasons());
            if (inspectionReasons.trim()
                    .length()
                > 0)
            {
                inspectionData =
                    true;
                ands.add(" inspection.inspectionReason in ("
                         + inspectionReasons
                         + ") and inspection.projectId = project.id ");
            }
        }
        if (form.getEventId()
            != null
            && form.getEventId()
                   .intValue()
               > 0)
        {
            eventData =
                true;
            ands.add(" eventProject.projectId = project.id "
                     + " and eventProject.eventId = event.id "
                     + " and eventProject.eventId = "
                     + form.getEventId()
                .intValue());
        }
        StringBuffer
            queryHEAD =
            new StringBuffer("select distinct project.id from EcProject as project ");
        if (inspectionData
            == true)
        {
            queryHEAD.append(", EcInspection as inspection");
        }
        if (eventData
            == true)
        {
            queryHEAD.append(", EcEventProject as eventProject, EcEvent as event");
        }
        queryHEAD.append(" WHERE \n ");
        queryHEAD.append(" project.permitAuthorityClient.id = "
                         + form.getClientId()
                         + "\n ");
        StringBuffer
            queryWith =
            new StringBuffer();
        for (
            Iterator
                iter =
                ands.iterator(); iter.hasNext(); )
        {
            queryWith.append(" and "
                             + iter.next()
                             + "\n ");
        }
        String
            query =
            "select project from EcProject as project where project.id in ("
            +
            queryHEAD.toString()
            + queryWith.toString()
            + ")";
        LOG.debug("EventService.getAllProjects() query = "
                  + query);
        ArrayList
            list =
            new ArrayList();
        Object
            parameters
            [
            ] =
            {};
        try
        {
            Iterator
                iter =
                HibernateUtil.find(query,
                                   parameters)
                    .iterator();
            while (iter.hasNext())
            {
                list.add(new ProjectValue((EcProject) iter.next(),
                                          true));
            }
        }
        catch (Exception e)
        {
            LOG.debug("Exception trying to do projectSearch: "
                      + e);
        }
        return list;
    }

    public static List getCompliantProjects(ProjectSearchForm form, SecurityManager securityManager)
    {
        LOG.debug("*** EventService.getCompliantProjects() - form = "
                  + form);
        if (form.getEventId()
            == null
            && form.getEventId()
                   .intValue()
               <= 0)
        {
            return null;
        }
        String
            complianceFlag;
        if (form.getEventConcern()
                .compareToIgnoreCase("noncompliant")
            == 0)
        {
            //	Search for non-compliant projects - either the inspecion date for this project
            //	was before the event or after the compliant end date, or no inspecions at all
            //	have been done for this project.
            LOG.debug("EventService.getCompliantProjects() - searching for Non-compliant projects");
            complianceFlag =
                "not in";
        }
        else
        {
            //	Search for compliant projects - where an inspecion date for this project
            //	was after the event and before the compliant end date.
            LOG.debug("EventService.getCompliantProjects() - searching for compliant projects");
            complianceFlag =
                "in";
        }
        //	Create the query.  The complianceFlag variable alters the query to
        //  select the appropriate dataset.
        StringBuffer
            query =
            new StringBuffer("select project from EcProject as project where project.id in (");
        query.append(" select distinct project.id from EcProject as project, ");
        query.append(" EcEventProject as eventProject, EcEvent as event\n");
        query.append(" WHERE (project.ownerClient.id = "
                     + form.getClientId()
                     + "\n ");
        query.append(" or project.permitAuthorityClient.id = "
                     + form.getClientId()
                     + ") \n ");
        query.append(" and eventProject.projectId = project.id "
                     +
                     " and eventProject.eventId = event.id "
                     +
                     " and event.id = "
                     + form.getEventId()
            .intValue()
                     + "\n ");
        query.append(" and project.id "
                     + complianceFlag
                     + " ( select distinct project.id from EcProject as project,\n ");
        query.append(" EcEventProject as eventProject, EcEvent as event, EcInspection as inspection\n ");
        query.append(" where event.id = "
                     + form.getEventId()
            .intValue()
                     + " and event.id = eventProject.eventId "
                     +
                     " and eventProject.projectId = project.id and project.id = inspection.projectId "
                     +
                     " and inspection.inspectionDate between event.eventDate and event.complianceDate"
                     +
                     " and inspection.status.code in (1, 4)))");
        LOG.debug("*** EventService.getCompliantProjects() query = "
                  + query);
        //	Execute the query and build up a list or ProjectValues
        ArrayList
            list =
            new ArrayList();
        Object
            parameters
            [
            ] =
            {};
        try
        {
            // DO THE HIBERNATE QUERY AND PUT THE RESULTS INTO A LIST
            List
                hibernateResults =
                HibernateUtil.find(query.toString(),
                                   parameters);
            LOG.debug("*** EventService.getCompliantProjects() hibernateResults.size() = "
                      + hibernateResults.size());
            Iterator
                iter =
                hibernateResults.iterator();
            while (iter.hasNext())
            {
                EcProject
                    ecProject =
                    (EcProject) iter.next();
                ProjectValue
                    projectValue =
                    ProjectService.getProjectValue(ecProject,
                                                   true,
                                                   securityManager);
                list.add(projectValue);
            }
        }
        catch (Exception e)
        {
            LOG.debug("Exception trying to do projectSearch: "
                      + e);
        }
        return list;
    }

    public static final String commaSep(String[] items)
    {
        if (items
            == null
            || items.length
               == 0)
        {
            return "";
        }
        StringBuffer
            buffer =
            new StringBuffer();
        final
        int
            count =
            items.length;
        for (
            int
                i =
                0; i
                   < count; i++)
        {
            final
            String
                item =
                items[i];
            if (!"0".equals(item))
            {
                buffer.append(items[i]);
            }
            if (i
                + 1
                < count)
            {
                buffer.append(',');
            }
        }
        return buffer.toString();
    }

    public static void sendEventNotice(Integer eventId)
        throws MessagingException, Exception
    {
        HashMap
            contactsToEmail =
            new HashMap();
        EcEvent
            event =
            new EcEvent();
        event.setId(eventId);
        if (!event.load())
        {
            throw new Exception("ERROR: Could not load Event ID = "
                                + eventId);
        }
        ClientData
            eventClient =
            event.getClient();
        String
            fromAddress =
            ApplicationProperties.getProperty("mail.noreply.address");
        InternetAddress[]
            fromAddr =
            new InternetAddress[1];
        fromAddr[0] =
            new InternetAddress(fromAddress);
        // Authorized Inspector and Permittee contacts are emailed
        // by way of association with the project, not an association with a client.
        Iterator
            projectIterator =
            EcEvent.findProjectsByEventId(eventId)
                .iterator();
        HashMap
            projects;
        while (projectIterator.hasNext())
        {
            EcProject
                project =
                (EcProject) projectIterator.next();
            List
                projectContactList =
                EcProjectContact.findByProjectId(project.getId());
            Iterator
                pclIterator =
                projectContactList.iterator();
            while (pclIterator.hasNext())
            {
                LOG.debug("sendEventNotice(): Retrieving project contacts. ");
                EcProjectContact
                    projectContact =
                    (EcProjectContact) pclIterator.next();
                LOG.debug("sendEventNotice(): projectContact retrieved. ");
                LOG.debug("sendEventNotice(): projectContact ["
                          + projectContact.getId()
                          + "]");
                String
                    contactTypeCode =
                    projectContact.getContactType()
                        .getCode();
                LOG.debug("sendEventNotice(): contact and contact type code retrieved. ");
                LOG.debug("sendEventNotice(): contact type code: ["
                          + contactTypeCode
                          + "]");
                if (contactTypeCode
                    != null)
                {
                    if (contactTypeCode.equalsIgnoreCase(EcProjectContactType.PERMITTEE)
                        || contactTypeCode.equalsIgnoreCase(EcProjectContactType.AUTHORIZED_INSPECTOR))
                    {
                        LOG.debug("sendEventNotice(): Contact type matched = "
                                  + contactTypeCode);
                        CapContact
                            contact =
                            projectContact.getContact();
                        // Add contact to the main contact hash,
                        // updating the list of projects for this event
                        // that they are associated with.
                        projects =
                            (HashMap) contactsToEmail.get(contact.getEmail());
                        if (projects
                            != null)
                        {
                            if (!projects.containsKey(project.getId()))
                            {
                                projects.put(project.getId(),
                                             project.getId());
                            }
                        }
                        else
                        {
                            projects =
                                new HashMap();
                            projects.put(project.getId(),
                                         project.getId());
                        }
                        contactsToEmail.put(contact.getEmail(),
                                            projects);
                    }
                }
            }
        }
        int
            i =
            1;
        Collection
            emailAddresses =
            contactsToEmail.keySet();
        Iterator
            toAddressIterator =
            emailAddresses.iterator();
        while (toAddressIterator.hasNext())
        {
            String
                recipient =
                (String) toAddressIterator.next();
            LOG.debug("sendEventNotice(): Getting project info and email data for email to "
                      + recipient);
            InternetAddress[]
                recipientAddress =
                new InternetAddress[i];
            recipientAddress[0] =
                new InternetAddress(recipient);
            HashMap
                recipientsProjectHash =
                (HashMap) contactsToEmail.get(recipient);
            Collection
                recipientsProjectCollection =
                recipientsProjectHash.values();
            Iterator
                recipProjectListIterator =
                recipientsProjectCollection.iterator();
            StringBuffer
                projectListText =
                new StringBuffer();
            while (recipProjectListIterator.hasNext())
            {
                Integer
                    projectId =
                    (Integer) recipProjectListIterator.next();
                EcProject
                    projectData =
                    new EcProject(projectId);
                projectData.load();
                LOG.debug("sendEventNotice(): Added projectId "
                          + projectId
                          + " data to email body.");
                projectListText.append("Project Address: "
                                       + projectData.getAddress());
                projectListText.append(HtmlEmailMessage.NEWLINE
                                       + " "
                                       + projectData.getCity()
                                       + " "
                                       + projectData.getState()
                                       + " "
                                       + projectData.getZip());
                projectListText.append(HtmlEmailMessage.NEWLINE);
                projectListText.append("Permit Number: "
                                       + projectData.getPermitNumber());
                projectListText.append(HtmlEmailMessage.NEWLINE
                                       + HtmlEmailMessage.NEWLINE);
            }
            StringBuffer
                messageContent =
                new StringBuffer();
            messageContent.append(eventClient.getName()
                                  + " has issued the following event notice."
                                  + HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append(event.getNotice());
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE
                                  + "Projects in need of inspection:"
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + projectListText.toString());
            messageContent.append(HtmlEmailMessage.NEWLINE
                                  + HtmlEmailMessage.NEWLINE);
            messageContent.append("Please log-in to the <a href='"
                                  + ApplicationProperties.getProperty("base.url")
                                  + "'>PermiTrack Client Access Point</a> to submit project inspections.");
            messageContent.append(HtmlEmailMessage.NEWLINE);
            messageContent.append("To access PermiTrack technical support, you can send an email to: "
                                  + ApplicationProperties.getProperty("mail.support.address")
                                  + " with a subject of 'Customer Support', or call 612-284-6331.");
            messageContent.append(HtmlEmailMessage.NEWLINE);
            EcEventDocument
                eventDocument =
                EcEventDocument.findByEventId(event.getId());
            if (eventDocument
                != null
                && !eventDocument.load())
            {
                throw new Exception("ERROR: Could not load Event Document for Event ID = "
                                    + event.getId());
            }
            Multipart
                mp =
                new MimeMultipart();
            if (eventDocument
                != null)
            {
                MimeBodyPart
                    mbp1 =
                    new MimeBodyPart();
                mbp1.setText(messageContent.toString());
                mbp1.setHeader(MessageHeader.CONTEXT_TYPE
                                   .toString(),
                               HtmlEmailMessage.CONTEXT_TYPE_VALUE);
                mbp1.setHeader(MessageHeader.MIME_VERSION
                                   .toString(),
                               HtmlEmailMessage.MIME_VERSION_VALUE);
                MimeBodyPart
                    mbp2 =
                    new MimeBodyPart();
                FileDataSource
                    fds =
                    new FileDataSource(eventDocument.getLocation()
                                       + eventDocument.getName());
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
            }
            EmailService
                emailService =
                new EmailService();
            HtmlEmailMessage
                emailMessage =
                new HtmlEmailMessage();
            emailMessage.setFrom(fromAddr);
            emailMessage.setRecipients(Message.RecipientType.TO,
                                       recipientAddress);
            emailMessage.setSubject("Event Notification");
            emailMessage.setText(messageContent.toString());
            emailMessage.setSentDate(new Date());
            if (eventDocument
                != null)
            {
                emailMessage.setMultipartContent(mp);
            }
            LOG.info(emailMessage.toString());
            LOG.info("Before sending email to "
                     + recipient);
            emailService.send(emailMessage);
            LOG.info("Successfully sent email to "
                     + recipient);
        }
    }
}