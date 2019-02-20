package com.sehinc.erosioncontrol.db.event;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class EcEventProject
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcEventProject.class);
    private
    Integer
        projectId;
    private
    Integer
        eventId;

    public EcEventProject()
    {
    }

    public EcEventProject(Integer id)
    {
        setId(id);
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getEventId()
    {
        return this.eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId =
            eventId;
    }
}