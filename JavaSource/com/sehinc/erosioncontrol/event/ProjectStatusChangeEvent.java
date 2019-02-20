package com.sehinc.erosioncontrol.event;

import com.sehinc.erosioncontrol.db.project.EcProject;
import com.sehinc.erosioncontrol.db.project.ProjectStatusCodeData;
import org.springframework.context.ApplicationEvent;

public class ProjectStatusChangeEvent
    extends ApplicationEvent
{
    private
    ProjectStatusCodeData
        originalStatus;
    private
    ProjectStatusCodeData
        newStatus;

    public ProjectStatusChangeEvent(EcProject project, ProjectStatusCodeData originalStatus, ProjectStatusCodeData newStatus)
    {
        super(project);
        this.newStatus =
            newStatus;
        this.originalStatus =
            originalStatus;
    }

    public ProjectStatusCodeData getOriginalStatus()
    {
        return originalStatus;
    }

    public void setOriginalStatus(ProjectStatusCodeData originalStatus)
    {
        this.originalStatus =
            originalStatus;
    }

    public ProjectStatusCodeData getNewStatus()
    {
        return newStatus;
    }

    public void setNewStatus(ProjectStatusCodeData newStatus)
    {
        this.newStatus =
            newStatus;
    }

    public EcProject getProject()
    {
        return (EcProject) source;
    }
}
