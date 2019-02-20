package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

public class EcProjectEvent
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectEvent.class);
    private
    Integer
        projectId;
    private
    Integer
        eventId;

    public EcProjectEvent()
    {
    }

    public EcProjectEvent(Integer id)
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