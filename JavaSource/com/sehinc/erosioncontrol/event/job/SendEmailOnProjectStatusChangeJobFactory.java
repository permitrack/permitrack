package com.sehinc.erosioncontrol.event.job;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.event.EventRunnable;
import com.sehinc.common.event.JobCreator;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.erosioncontrol.event.ProjectStatusChangeEvent;
import com.sehinc.erosioncontrol.server.project.ProjectService;

import java.util.List;

public class SendEmailOnProjectStatusChangeJobFactory
    implements JobCreator<ProjectStatusChangeEvent>
{
    public EventRunnable createJob(ProjectStatusChangeEvent event)
    {
        SendEmailOnProjectStatusChangeJob
            job =
            new SendEmailOnProjectStatusChangeJob();
        job.setContext(event);
        return job;
    }

    public class SendEmailOnProjectStatusChangeJob
        implements EventRunnable<ProjectStatusChangeEvent>
    {
        private
        ProjectStatusChangeEvent
            context;

        public void setContext(ProjectStatusChangeEvent event)
        {
            context =
                event;
        }

        public void run()
        {
            if (isEmailEnabled())
            {
                List<String>
                    recipients =
                    ProjectService.getAllRecipients(context.getProject());
                String
                    message =
                    createMessage();
                String
                    subject =
                    createSubject();
                SpringServiceLocator.getEmailService()
                    .sendEmailExceptionHandled(recipients,
                                               message,
                                               subject,
                                               context.getProject());
            }
        }

        private String createMessage()
        {
            StringBuilder
                message =
                new StringBuilder();
            message.append("The ")
                .append(context.getProject()
                            .getName())
                .append(" project status has changed from ");
            message.append(context.getOriginalStatus()
                               .getDescription())
                .append(" to ")
                .append(context.getNewStatus()
                            .getDescription());
            message.append(".  This status change was made by ");
            UserData
                updateUser =
                new UserData(context.getProject()
                                 .getUpdateUserId());
            updateUser.load();
            message.append(updateUser.getFirstName())
                .append(" ")
                .append(updateUser.getLastName());
            message.append(" on ")
                .append(context.getProject()
                            .getUpdateTimestamp());
            return message.toString();
        }

        private String createSubject()
        {
            StringBuilder
                subject =
                new StringBuilder("Erosion Control (ESC) Project Notification - ");
            subject.append(context.getProject()
                               .getName());
            subject.append(" status has changed to ");
            subject.append(context.getNewStatus()
                               .getDescription());
            return subject.toString();
        }

        private boolean isEmailEnabled()
        {
            return context.getProject()
                .getOwnerClient()
                .getClientAdminSettings()
                .isProjectStatusEmailsEnabled();
        }
    }
}
