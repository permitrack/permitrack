package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class EcClientType
    extends HibernateData
{
    public static
    String
        PRIMARY =
        "PRIMARY";
    public static
    String
        SECONDARY =
        "SECONDARY";
    private static
    Logger
        LOG =
        Logger.getLogger(EcClientType.class);
    private
    String
        name;
    private
    String
        description;

    public EcClientType()
    {
    }

    public EcClientType(Integer id)
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

    public static EcClientType findByName(String name)
    {
        Object
            parameters
            [
            ] =
            {name};
        String
            queryString =
            "select data from EcClientType as data where upper(data.name) = upper(?)";
        return (EcClientType) HibernateUtil.findUnique(queryString,
                                                       parameters);
    }
}