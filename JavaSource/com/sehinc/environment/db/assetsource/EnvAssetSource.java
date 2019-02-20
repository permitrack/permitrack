package com.sehinc.environment.db.assetsource;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.status.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Date;
import java.util.List;

public class EnvAssetSource
    extends StatusData
{
    public static
    String
        FIND_BY_ASSET_ID =
        "EnvAssetSource.findByAssetId";
    public static
    String
        FIND_BY_SOURCE_ID =
        "EnvAssetSource.findBySourceId";
    public static
    String
        FIND_BY_ASSET_ID_AND_SOURCE_ID =
        "EnvAssetSource.findByAssetIdAndSourceId";
    public static
    String
        FIND_BY_ASSET_ID_AND_DATE =
        "EnvAssetSource.findByAssetIdAndDate";
    private
    Integer
        assetId;
    private
    Integer
        sourceId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;

    public EnvAssetSource()
    {
    }

    public EnvAssetSource(Integer id)
    {
        setId(id);
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public String getActiveTsString()
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

    public String getInactiveTsString()
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

    public Integer getAssetId()
    {
        return assetId;
    }

    public void setAssetId(Integer assetId)
    {
        this.assetId =
            assetId;
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

    public static List findByAssetId(Integer assetId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "assetId",
                    assetId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ASSET_ID,
                                              parameters);
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
                    sourceId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SOURCE_ID,
                                              parameters);
    }

    public static EnvAssetSource findByAssetIdAndSourceId(Integer assetId, Integer sourceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "assetId",
                    assetId},
                {
                    "sourceId",
                    sourceId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvAssetSource) HibernateUtil.findUniqueByNamedQuery(FIND_BY_ASSET_ID_AND_SOURCE_ID,
                                                                     parameters);
    }

    public static List<EnvAssetSource> findByAssetIdAndDate(Integer assetId, Date activeTs)
    {
        Object
            parameters
            [
            ] =
            {assetId};
        String
            queryString =
            "select data from com.sehinc.environment.db.assetsource.EnvAssetSource as data where data.assetId = ? and data.activeTs >= '"
            + DateUtil.ymdDate(activeTs)
            + "' and data.status.code = "
            + StatusCodeData.STATUS_CODE_ACTIVE
            + " order by data.assetId asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List<EnvAssetSource> findByAssetIdAndDateRange(Integer assetId, Date activeTs, Date inactiveTs)
    {
        Object
            parameters
            [
            ] =
            {assetId};
        String
            queryString =
            "select data from com.sehinc.environment.db.assetsource.EnvAssetSource as data where data.assetId = ? and data.activeTs >= '"
            + DateUtil.ymdDate(activeTs)
            + "' and data.inactiveTs <= '"
            + DateUtil.ymdDate(inactiveTs)
            + "' and data.status.code = "
            + StatusCodeData.STATUS_CODE_ACTIVE
            + " order by data.assetId asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nassetId=")
            .append(assetId);
        buffer.append("\nsourceId=")
            .append(sourceId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}