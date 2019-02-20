package com.sehinc.environment.db.substance;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.lookup.LookupData;
import com.sehinc.environment.db.status.StatusData;
import org.apache.log4j.Logger;

import java.util.List;

public class EnvSubstance
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EnvSubstance.class);
    public static
    String
        FIND_BY_CLIENT_ID =
        "EnvSubstance.substanceListByClientId";
    public static
    String
        FIND_BY_NAME_AND_NUMBER =
        "EnvSubstance.substanceByNameAndNumber";
    public static
    String
        FIND_BY_SUBSTANCE_TYPE =
        "EnvSubstance.substanceListBySubstanceType";
    public static
    String
        FIND_BY_CLIENT_ID_AND_SUBSTANCE_ID =
        "EnvSubstance.substanceListByClientIdAndSubstanceId";
    public static
    String
        FIND_BY_SUBSTANCE_TYPE_AND_CLIENT_ID =
        "EnvSubstance.bySubstanceTypeAndClientId";
    private
    String
        name;
    private
    String
        partNum;
    private
    Integer
        clientId;
    private
    EnvSubstanceTypeData
        substanceType;
    private
    long
        version;

    public EnvSubstance()
    {
    }

    public EnvSubstance(Integer id)
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

    public String getPartNum()
    {
        return partNum;
    }

    public void setPartNum(String partNum)
    {
        this.partNum =
            partNum;
    }

    public String getNumberAndName()
    {
        return this.partNum
               + " - "
               + this.name;
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

    public EnvSubstanceTypeData getSubstanceType()
    {
        return substanceType;
    }

    public void setSubstanceType(EnvSubstanceTypeData substanceType)
    {
        this.substanceType =
            substanceType;
    }

    public void setSubstanceType(Integer substanceTypeId)
    {
        List<LookupData>
            list =
            this.findAll(EnvSubstanceTypeData.class);
        for (LookupData code : list)
        {
            if (substanceTypeId.equals(code.getCode()))
            {
                setSubstanceType((EnvSubstanceTypeData) code);
                break;
            }
        }
    }

    public void setVersion(long version)
    {
        this.version =
            version;
    }

    public long getVersion()
    {
        return version;
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

    public static EnvSubstance findByClientIdAndSubstanceId(Integer clientId, Integer id)
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
                    "id",
                    id}};
        return (EnvSubstance) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CLIENT_ID_AND_SUBSTANCE_ID,
                                                                   parameters);
    }

    public static EnvSubstance findByNameAndNumber(Integer clientId, String name, String number)
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
                    "number",
                    number},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvSubstance) HibernateUtil.findUniqueByNamedQuery(FIND_BY_NAME_AND_NUMBER,
                                                                   parameters);
    }

    public static List findBySubstanceType(Integer substanceType)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceType",
                    substanceType},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_TYPE,
                                              parameters);
    }

    public static List findBySubstanceTypeAndClientId(Integer substanceType, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceType",
                    substanceType},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_TYPE_AND_CLIENT_ID,
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
        buffer.append("\npartNum=")
            .append(partNum);
        buffer.append("\n\n");
        return buffer.toString();
    }
}