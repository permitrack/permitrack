package com.sehinc.erosioncontrol.db.event;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

public class EcEventDocument
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcEventDocument.class);
    private
    Integer
        eventId;
    private
    String
        name;
    private
    String
        location;

    public EcEventDocument()
    {
    }

    public EcEventDocument(Integer id)
    {
        setId(id);
    }

    public Integer getEventId()
    {
        return eventId;
    }

    public void setEventId(Integer eventId)
    {
        this.eventId =
            eventId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public static EcEventDocument findByEventId(Integer eventId)
    {
        Object[][]
            parameters =
            {
                {
                    "eventId",
                    eventId}};
        String
            FIND_BY_EVENT_ID =
            "com.sehinc.erosioncontrol.db.event.EcEventDocument.byEventId";
        return (EcEventDocument) HibernateUtil.findUniqueByNamedQuery(FIND_BY_EVENT_ID,
                                                                      parameters);
    }
}
