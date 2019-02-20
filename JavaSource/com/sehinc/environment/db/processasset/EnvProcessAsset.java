package com.sehinc.environment.db.processasset;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.status.StatusData;

import java.util.Date;
import java.util.List;

public class EnvProcessAsset
    extends StatusData
{
    public static
    String
        FIND_PROCESS_ASSET_BY_PROCESS_ID =
        "EnvProcessAsset.processAssetByProcessId";
    public static
    String
        FIND_PROCESS_ASSET_BY_ASSET_ID =
        "EnvProcessAsset.processAssetByAssetId";
    public static
    String
        FIND_ALL_PROCESS_ASSETS_BY_PROCESS_ID =
        "EnvProcessAsset.allProcessAssetsByProcessId";
    public static
    String
        FIND_PROCESS_ASSET_BY_PROCESS_AND_ASSET_ID =
        "EnvProcessAsset.processAssetByProcessAndAssetId";
    private
    Integer
        processId;
    private
    Integer
        assetId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;

    public EnvProcessAsset()
    {
    }

    public EnvProcessAsset(Integer id)
    {
        setId(id);
    }

    public Integer getProcessId()
    {
        return processId;
    }

    public void setProcessId(Integer processId)
    {
        this.processId =
            processId;
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

    public Date getActiveTs()
    {
        return activeTs;
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

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public static List findActiveByProcessId(Integer processId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "processId",
                    processId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_PROCESS_ASSET_BY_PROCESS_ID,
                                              parameters);
    }

    public static List findActiveByAssetId(Integer assetId)
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
        return HibernateUtil.findByNamedQuery(FIND_PROCESS_ASSET_BY_ASSET_ID,
                                              parameters);
    }

    public static List findAllByProcessId(Integer processId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "processId",
                    processId}};
        return HibernateUtil.findByNamedQuery(FIND_ALL_PROCESS_ASSETS_BY_PROCESS_ID,
                                              parameters);
    }

    public static EnvProcessAsset findActiveByProcessAndAssetId(Integer processId, Integer assetId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "processId",
                    processId},
                {
                    "assetId",
                    assetId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return (EnvProcessAsset) HibernateUtil.findUniqueByNamedQuery(FIND_PROCESS_ASSET_BY_PROCESS_AND_ASSET_ID,
                                                                      parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nprocessId=")
            .append(processId);
        buffer.append("\nassetId=")
            .append(assetId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
