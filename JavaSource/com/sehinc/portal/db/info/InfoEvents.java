package com.sehinc.portal.db.info;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class InfoEvents
    extends StatusData
    implements java.io.Serializable
{
    public static
    String
        INFO_EVENTS_BY_STATUS_CODE =
        "com.sehinc.portal.db.info.InfoEvents.byStatusCode";
    private
    String
        eventDate;
    private
    String
        title;
    private
    String
        link;
    private
    String
        subtitle;
    private
    String
        location;

    public InfoEvents()
    {
    }

    public InfoEvents(Integer id)
    {
        setId(id);
    }

    public String getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(String eventDate)
    {
        this.eventDate =
            eventDate;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link =
            link;
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

    public String getSubtitle()
    {
        return subtitle;
    }

    public void setSubtitle(String subtitle)
    {
        this.subtitle =
            subtitle;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title =
            title;
    }

    public static List findActive()
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(INFO_EVENTS_BY_STATUS_CODE,
                                              parameters);
    }

    public boolean equals(Object o)
    {
        if (o instanceof InfoEvents)
        {
            InfoEvents
                other =
                (InfoEvents) o;
            return other.getId()
                .equals(this.getId());
        }
        return false;
    }
}