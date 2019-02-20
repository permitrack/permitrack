package com.sehinc.erosioncontrol.batch;

import com.sehinc.common.constants.CodeDataFieldConstants;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.service.spring.EmailService;
import com.sehinc.common.service.spring.GenericDaoService;
import com.sehinc.common.service.spring.impl.GenericDaoServiceImpl;
import com.sehinc.erosioncontrol.constants.EcProjectFieldConstants;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionOverdueEmailLogEntry;
import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.mail.MessagingException;
import java.util.*;

public class InspectionOverdueQuartzJob
    extends QuartzJobBean
    implements StatefulJob
{
    static
    Logger
        LOG =
        Logger.getLogger(InspectionOverdueQuartzJob.class);
    private static final
    String
        DEFAULT_INITIAL_MESSAGE =
        "INSPECTION IS OVERDUE. THIS IS THE INITIAL MESSAGE.";
    private static final
    String
        DEFAULT_SECONDARY_MESSAGE =
        "INSPECTION IS OVERDUE. THIS IS THE SECONDARY MESSAGE.";
    private static final
    Integer
        INITIAL_level =
        1;
    private static final
    Integer
        SECONDARY_level =
        2;
    private
    EmailService
        emailService;
    private
    GenericDaoService
        genericDaoService;
    private static final
    String
        $PROJECT_NAME =
        "$project.name";
    private static final
    String
        $PROJECT_LAST_INSPECTION_DATE =
        "$project.lastInspectionDate";
    private static final
    String
        N_A =
        "N/A";
    private static final
    String
        $INSPECTION_INSPECTOR =
        "$inspection.inspector";
    private static final
    String
        $PROJECT_DAYS_SINCE_LAST_INSPECTION =
        "$project.days_since_last_inspection";

    public EmailService getEmailService()
    {
        return emailService;
    }

    public void setEmailService(EmailService emailService)
    {
        this.emailService =
            emailService;
    }

    public GenericDaoService getGenericDaoService()
    {
        return genericDaoService;
    }

    public void setGenericDaoService(GenericDaoService genericDaoService)
    {
        this.genericDaoService =
            genericDaoService;
    }

    public void processManually()
        throws JobExecutionException
    {
        GenericDaoService
            dao =
            new GenericDaoServiceImpl();
        this.setGenericDaoService(dao);
        this.executeInternal(null);
    }

    protected void executeInternal(JobExecutionContext jobExecutionContext)
        throws JobExecutionException
    {
        LOG.info("--------------------------------------  Inspection Overdue Quartz Job Starting  --------------------------------------");
        List<ClientData>
            clients =
            getClients();
        for (ClientData client : clients)
        {
            processClient(client);
        }
        LOG.info("--------------------------------------  Inspection Overdue Quartz Job Complete --------------------------------------");
    }

    private void processClient(ClientData client)
    {
        List<EcProject>
            clientProjects =
            fetchProjectsForClient(client);
        if (clientProjects
            != null
            && clientProjects.size()
               > 0)
        {
            processProjects(clientProjects,
                            client,
                            INITIAL_level);
        }
    }

    private void processProjects(List<EcProject> clientProjects, ClientData client, int thresholdLevel)
    {
        for (EcProject project : clientProjects)
        {
            Integer
                threshold =
                null;
            String
                message =
                null;
            if (thresholdLevel
                == INITIAL_level)
            {
                if (projectIsEnabled(project))
                {
                    threshold =
                        project.getInspectionOverdueInitialThreshold();
                    message =
                        project.getInspectionOverdueInitialMessage();
                }
                else if (clientIsEnabled(client))
                {
                    threshold =
                        client.getClientAdminSettings()
                            .getInspectionOverdueInitialThreshold();
                    message =
                        client.getClientAdminSettings()
                            .getInspectionOverdueInitialMessage();
                }
            }
            else if (thresholdLevel
                     == SECONDARY_level)
            {
                if (projectIsSecEnabled(project))
                {
                    threshold =
                        project.getInspectionOverdueSecondThreshold();
                    message =
                        project.getInspectionOverdueSecondMessage();
                }
                else if (clientIsSecEnabled(client))
                {
                    threshold =
                        client.getClientAdminSettings()
                            .getInspectionOverdueSecondThreshold();
                    message =
                        client.getClientAdminSettings()
                            .getInspectionOverdueSecondMessage();
                }
            }
            if (threshold
                != null)
            {
                List<EcProject>
                    projects =
                    new ArrayList<EcProject>();
                if (projectIsOverdue(project,
                                     threshold,
                                     thresholdLevel))
                {
                    projects.add(project);
                }
                if (projects.size()
                    > 0)
                {
                    sendEmailForProjects(projects,
                                         message,
                                         thresholdLevel,
                                         threshold);
                }
                if (thresholdLevel
                    == INITIAL_level)
                {
                    processProjects(clientProjects,
                                    client,
                                    SECONDARY_level);
                }
            }
        }
    }

    private void sendEmailForProjects(List<EcProject> projects, String rawMessage, int level, int daysOverdue)
    {
        if (projects
            == null
            || projects.size()
               < 1)
        {
            return;
        }
        for (EcProject project : projects)
        {
            try
            {
                String
                    subject =
                    createSubject(project);
                String
                    message =
                    createMessage(rawMessage,
                                  project,
                                  level,
                                  daysOverdue);
                if (emailService
                    != null)
                {
                    List<String>
                        recipients =
                        ProjectService.getAllRecipients(project);
                    emailService.sendEmail(recipients,
                                           message,
                                           subject,
                                           project);
                    EcInspection
                        inspection =
                        project.getLastInspection();
                    if (inspection
                        != null)
                    {
                        inspection.getOverdueLogEntries()
                            .add(createNewLogEntry(new Date(),
                                                   level));
                    }
                    else
                    {
                        project.getOverdueLogEntries()
                            .add(createNewLogEntry(new Date(),
                                                   level));
                    }
                    genericDaoService.save(project);
                }
                else
                {
                    LOG.info("------------  INSPECTION OVERDUE - EMAIL SERVICE IS NULL - FOLLOWING MESSAGE NOT SENT  ------------");
                    LOG.info(message);
                }
            }
            catch (MessagingException e)
            {
                LOG.error(e);
            }
            catch (Exception e)
            {
                LOG.error(e);
            }
            // WTF?
            //project.load();
        }
    }

    private EcInspectionOverdueEmailLogEntry createNewLogEntry(Date date, int level)
    {
        EcInspectionOverdueEmailLogEntry
            entry =
            new EcInspectionOverdueEmailLogEntry();
        entry.setSendDate(date);
        entry.setThresholdLevel(level);
        return entry;
    }

    private String createMessage(String rawMessage, EcProject project, int level, int daysOverdue)
    {
        if (rawMessage
            == null)
        {
            if (level
                == INITIAL_level)
            {
                rawMessage =
                    DEFAULT_INITIAL_MESSAGE;
            }
            else if (level
                     == SECONDARY_level)
            {
                rawMessage =
                    DEFAULT_SECONDARY_MESSAGE;
            }
        }
        String
            message =
            rawMessage.replace($PROJECT_NAME,
                               project.getName());
        if (project.getLastInspection()
            == null
            || project.getLastInspection()
                   .getInspectionDate()
               == null)
        {
            message =
                message.replace($PROJECT_LAST_INSPECTION_DATE,
                                N_A);
        }
        else
        {
            message =
                message.replace($PROJECT_LAST_INSPECTION_DATE,
                                project.getLastInspection()
                                    .getInspectionDate()
                                    .toString());
        }
        if (project.getLastInspection()
            != null
            && project.getLastInspection()
                   .getInspector()
               != null)
        {
            String
                inspectorName =
                project.getLastInspection()
                    .getInspector()
                    .getFirstName()
                + " "
                + project.getLastInspection()
                    .getInspector()
                    .getLastName();
            if (inspectorName.trim()
                    .length()
                < 1)
            {
                inspectorName =
                    N_A;
            }
            message =
                message.replace($INSPECTION_INSPECTOR,
                                inspectorName);
        }
        else
        {
            message =
                message.replace($INSPECTION_INSPECTOR,
                                N_A);
        }
        message =
            message.replace($PROJECT_DAYS_SINCE_LAST_INSPECTION,
                            String.valueOf(daysOverdue));
        return message;
    }

    private String createSubject(EcProject project)
    {
        return new StringBuilder("Erosion Control (ESC) Overdue Inspection Notification - ").append(project.getName())
            .toString();
    }

    private boolean clientIsEnabled(ClientData client)
    {
        return client.getClientAdminSettings()
            .isInspectionOverdueNotificationEnabled();
    }

    private boolean projectIsEnabled(EcProject project)
    {
        return project.getInspectionOverdueNotificationEnabled();
    }

    private boolean clientIsSecEnabled(ClientData client)
    {
        return client.getClientAdminSettings()
            .isSecondInspectionOverdueNotificationEnabled();
    }

    private boolean projectIsSecEnabled(EcProject project)
    {
        return project.getSecondInspectionOverdueNotificationEnabled();
    }

    private List<ClientData> getClients()
    {
        DetachedCriteria
            criteria =
            DetachedCriteria.forClass(ClientData.class);
        criteria.add(Restrictions.eq("status.code",
                                     StatusCodeData.STATUS_CODE_ACTIVE));
        return genericDaoService.findByDetachedCriteria(criteria);
    }

    private List<EcProject> fetchProjectsForClient(ClientData client)
    {
        DetachedCriteria
            projectCriteria =
            DetachedCriteria.forClass(EcProject.class);
        projectCriteria.createCriteria(EcProjectFieldConstants.OWNER_CLIENT)
            .add(Restrictions.eq("id",
                                 client.getId()));
        projectCriteria.createCriteria(EcProjectFieldConstants.PROJECT_STATUS)
            .add(Restrictions.eq(CodeDataFieldConstants.CODE,
                                 ProjectStatusCodeData.ACTIVE));
        return genericDaoService.findByDetachedCriteria(projectCriteria);
    }

    private Boolean projectIsOverdue(EcProject project, int overdueDays, int thresholdLevel)
    {
        Calendar
            cal =
            Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,
                (-1
                 * overdueDays));
        if (project.getLastInspection()
            != null)
        {
            if (project.getLastInspection()
                .getInspectionDate()
                .before(cal.getTime()))
            {
                if (!hasPreviousEmailBeenSent(thresholdLevel,
                                              project))
                {
                    return true;
                }
            }
        }
        else
        {
            if (project.getStartDate()
                != null
                && project.getStartDate()
                .before(cal.getTime()))
            {
                if (!hasPreviousEmailBeenSent(thresholdLevel,
                                              project))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPreviousEmailBeenSent(int thresholdLevel, EcProject project)
    {
        boolean
            alreadySent;
        if (project.getLastInspection()
            != null)
        {
            alreadySent =
                hasOverdueLogEntry(thresholdLevel,
                                   project.getLastInspection()
                                       .getOverdueLogEntries());
        }
        else
        {
            alreadySent =
                hasOverdueLogEntry(thresholdLevel,
                                   project.getOverdueLogEntries());
        }
        return alreadySent;
    }

    private boolean hasOverdueLogEntry(int thresholdLevel, Set<EcInspectionOverdueEmailLogEntry> entries)
    {
        boolean
            alreadySent =
            false;
        if (entries
            != null
            && entries.size()
               > 0)
        {
            for (EcInspectionOverdueEmailLogEntry entry : entries)
            {
                if (entry.getThresholdLevel()
                    == thresholdLevel)
                {
                    alreadySent =
                        true;
                    break;
                }
            }
        }
        return alreadySent;
    }
}
