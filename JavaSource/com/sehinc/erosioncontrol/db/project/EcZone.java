package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EcZone
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcZone.class);
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;

    public EcZone()
    {
    }

    public EcZone(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
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

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcZone as data where data.clientId in (?)";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static EcZone findDuplicateByName(Integer clientId, String zoneName)
    {
        Object[]
            parameters =
            {
                clientId,
                zoneName};
        String
            queryString =
            "select data from EcZone as data where data.clientId = ? and lower(data.name) = lower(?)";
        return (EcZone) HibernateUtil.findUnique(queryString,
                                                 parameters);
    }

    public static EcZone findDuplicateByName(Integer clientId, Integer zoneId, String zoneName)
    {
        Object[]
            parameters =
            {
                clientId,
                zoneId,
                zoneName};
        String
            queryString =
            "select data from EcZone as data where data.clientId = ? and data.id != ? and lower(data.name) = lower(?)";
        return (EcZone) HibernateUtil.findUnique(queryString,
                                                 parameters);
    }
}