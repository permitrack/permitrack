package com.sehinc.erosioncontrol.value.event;

import com.sehinc.erosioncontrol.db.event.EcEventType;

public class EventTypeValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;

    public EventTypeValue()
    {
    }

    public EventTypeValue(EcEventType eventType)
    {
        this.id =
            eventType.getId();
        this.name =
            eventType.getName();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String toString()
    {
        return "EventTypeValue:[id="
               + getId()
               + ",name="
               + getName()
               + "]";
    }
}