package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.Collection;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class CapClientType
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(CapClientType.class);
    public static
    String
        FIND_CLIENT_TYPE_BY_CLIENT_ID =
        "com.sehinc.common.db.client.CapClientType.byClientId";
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    Integer
        clientTypeId;

    public CapClientType()
    {
    }

    public CapClientType(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public Integer getClientTypeId()
    {
        return clientTypeId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setClientId(Integer integer)
    {
        clientId =
            integer;
    }

    public void setClientTypeId(Integer integer)
    {
        clientTypeId =
            integer;
    }

    public void setId(Integer integer)
    {
        id =
            integer;
    }

    public static Collection getClientTypeByClientId(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_CLIENT_TYPE_BY_CLIENT_ID,
                                              parameters);
    }
}