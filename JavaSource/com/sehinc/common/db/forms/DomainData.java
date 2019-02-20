package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

import java.util.Collection;

@SuppressWarnings(value = {"unused"})
public class DomainData
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(DomainData.class);
    private
    String
        name;
    private
    Collection
        domainValues;

    public DomainData()
    {
    }

    public DomainData(Integer id)
    {
        setId(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String string)
    {
        name =
            string;
    }

    public Collection getDomainValues()
    {
        return domainValues;
    }

    public void setDomainValues(Collection collection)
    {
        domainValues =
            collection;
    }
}