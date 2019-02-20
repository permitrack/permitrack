package com.sehinc.erosioncontrol.db.event;

import com.sehinc.common.db.hibernate.HibernateData;

public class EcEventType
    extends HibernateData
{
    private
    String
        name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String toString()
    {
        return "EcEventType:[id="
               + getId()
               + ",name="
               + getName()
               + "]";
    }
}
