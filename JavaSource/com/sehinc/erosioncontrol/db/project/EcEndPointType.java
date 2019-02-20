package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class EcEndPointType
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcEndPointType.class);
    private
    String
        name;
    private
    String
        description;

    public EcEndPointType()
    {
    }

    public EcEndPointType(Integer id)
    {
        setId(id);
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

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }
}