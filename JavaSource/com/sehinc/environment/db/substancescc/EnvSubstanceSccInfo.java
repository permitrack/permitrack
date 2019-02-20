package com.sehinc.environment.db.substancescc;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvSubstanceSccInfo
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_SUBSTANCE_ID =
        "EnvSubstanceSccInfo.findBySubstanceId";
    public static
    String
        FIND_BY_SCC_INFO_ID =
        "EnvSubstanceSccInfo.findBySccInfoId";
    public static
    String
        FIND_BY_SUBSTANCE_ID_AND_SCC_INFO_ID =
        "EnvSubstanceSccInfo.findBySubstanceIdAndSccInfoId";
    private
    Integer
        substanceId;
    private
    Integer
        sccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDescription;

    public EnvSubstanceSccInfo()
    {
    }

    public EnvSubstanceSccInfo(Integer id)
    {
        setId(id);
    }

    public void setSubstanceId(Integer substanceId)
    {
        this.substanceId =
            substanceId;
    }

    public Integer getSubstanceId()
    {
        return substanceId;
    }

    public Integer getSccInfoId()
    {
        return sccInfoId;
    }

    public void setSccInfoId(Integer sccInfoId)
    {
        this.sccInfoId =
            sccInfoId;
    }

    public String getSccNumber()
    {
        return sccNumber;
    }

    public void setSccNumber(String sccNumber)
    {
        this.sccNumber =
            sccNumber;
    }

    public String getSccDescription()
    {
        return sccDescription;
    }

    public void setSccDescription(String sccDescription)
    {
        this.sccDescription =
            sccDescription;
    }

    public static List findBySubstanceId(Integer substanceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceId",
                    substanceId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_ID,
                                              parameters);
    }

    public static List findBySccInfoId(Integer sccInfoId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "sccInfoId",
                    sccInfoId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SCC_INFO_ID,
                                              parameters);
    }

    public static EnvSubstanceSccInfo findBySubstanceIdAndSccInfoId(Integer substanceId, Integer sccInfoId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceId",
                    substanceId},
                {
                    "sccInfoId",
                    sccInfoId}};
        return (EnvSubstanceSccInfo) HibernateUtil.findUniqueByNamedQuery(FIND_BY_SUBSTANCE_ID_AND_SCC_INFO_ID,
                                                                          parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nsubstanceId=")
            .append(substanceId);
        buffer.append("\nsccInfoId=")
            .append(sccInfoId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}

