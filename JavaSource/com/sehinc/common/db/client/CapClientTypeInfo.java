package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class CapClientTypeInfo
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
        Logger.getLogger(CapClientTypeInfo.class);
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        description;
    private static final
    String
        FIND_BY_CLIENT_ID =
        "com.sehinc.common.db.client.CapClientTypeInfo.byClientId";

    public CapClientTypeInfo()
    {
    }

    public CapClientTypeInfo(Integer id)
    {
        setId(id);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer integer)
    {
        id =
            integer;
    }

    public String getDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String string)
    {
        description =
            string;
    }

    public void setName(String string)
    {
        name =
            string;
    }

    public static CapClientTypeInfo findByName(String name)
    {
        Object
            parameters
            [
            ] =
            {name};
        String
            queryString =
            "select data from CapClientTypeInfo as data where upper(data.name) = upper(?)";
        return (CapClientTypeInfo) HibernateUtil.findUnique(queryString,
                                                            parameters);
    }

    public static CapClientTypeInfo findByClientId(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        return (CapClientTypeInfo) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CLIENT_ID,
                                                                        parameters);
    }

    public static ArrayList findListByClientId(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId}};
        return (ArrayList) HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                                          parameters);
    }

    public static List findAll()
    {
        return HibernateUtil.findAll(CapClientTypeInfo.class);
    }
}