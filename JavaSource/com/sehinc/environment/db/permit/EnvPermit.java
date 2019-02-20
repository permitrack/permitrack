package com.sehinc.environment.db.permit;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.status.StatusData;

import java.util.Date;
import java.util.List;

public class EnvPermit
    extends StatusData
{
    public static
    String
        FIND_BY_CLIENT_ID =
        "EnvPermit.permitListByClientId";
    public static
    String
        FIND_BY_CLIENT_ID_AND_NAME =
        "EnvPermit.permitByClientIdAndName";
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;

    public EnvPermit()
    {
    }

    public EnvPermit(Integer id)
    {
        setId(id);
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

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public String getActiveTsText()
    {
        if (activeTs
            != null)
        {
            return DateUtil.mdyDate(activeTs);
        }
        else
        {
            return "";
        }
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public String getInactiveTsText()
    {
        if (inactiveTs
            != null)
        {
            return DateUtil.mdyDate(inactiveTs);
        }
        else
        {
            return "";
        }
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public static EnvPermit findByClientIdAndName(Integer clientId, String name)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "name",
                    name},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvPermit) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CLIENT_ID_AND_NAME,
                                                                parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\nname=")
            .append(name);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\n\n");
        return buffer.toString();
    }
}