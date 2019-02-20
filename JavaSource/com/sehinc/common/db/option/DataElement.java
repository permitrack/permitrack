package com.sehinc.common.db.option;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.security.CapModule;

import java.util.Collection;

public class DataElement
    extends HibernateData
{
    private
    String
        name;
    private
    String
        description;
    private
    String
        type;
    private
    CapModule
        capModule;
    private
    Collection
        dataElementOptionValueList;

    public DataElement()
    {
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type =
            type;
    }

    public CapModule getCapModule()
    {
        return capModule;
    }

    public void setCapModule(CapModule capModule)
    {
        this.capModule =
            capModule;
    }

    public Collection getDataElementOptionValueList()
    {
        return dataElementOptionValueList;
    }

    public void setDataElementOptionValueList(Collection dataElementOptionValueList)
    {
        this.dataElementOptionValueList =
            dataElementOptionValueList;
    }
}
