package com.sehinc.environment.db.sourcescc;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvSourceSccInfo
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_SOURCE_ID =
        "EnvSourceSccInfo.findBySourceId";
    public static
    String
        FIND_BY_SCC_INFO_ID =
        "EnvSourceSccInfo.findBySccInfoId";
    public static
    String
        FIND_BY_SOURCE_ID_AND_SCC_INFO_ID =
        "EnvSourceSccInfo.findBySourceIdAndSccInfoId";
    private
    Integer
        sourceId;
    private
    Integer
        sccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDescription;

    public EnvSourceSccInfo()
    {
    }

    public EnvSourceSccInfo(Integer id)
    {
        setId(id);
    }

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
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

    public static List findBySourceId(Integer sourceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "sourceId",
                    sourceId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SOURCE_ID,
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

    public static EnvSourceSccInfo findBySourceIdAndSccInfoId(Integer sourceId, Integer sccInfoId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "sourceId",
                    sourceId},
                {
                    "sccInfoId",
                    sccInfoId}};
        return (EnvSourceSccInfo) HibernateUtil.findUniqueByNamedQuery(FIND_BY_SOURCE_ID_AND_SCC_INFO_ID,
                                                                       parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nsourceId=")
            .append(sourceId);
        buffer.append("\nsccInfoId=")
            .append(sccInfoId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
