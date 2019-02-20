package com.sehinc.environment.value;

import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.processasset.EnvProcessAsset;

import java.util.Date;

public class ProcessAssetValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        processId;
    private
    Integer
        assetId;
    private
    String
        assetNumber;
    private
    String
        assetName;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;

    public ProcessAssetValue()
    {
    }

    public ProcessAssetValue(EnvProcessAsset pa)
    {
        this.id =
            pa.getId();
        this.processId =
            pa.getProcessId();
        this.assetId =
            pa.getAssetId();
        this.activeTs =
            pa.getActiveTs();
        this.inactiveTs =
            pa.getInactiveTs();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public String getAssetNumber()
    {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber)
    {
        this.assetNumber =
            assetNumber;
    }

    public String getAssetName()
    {
        return assetName;
    }

    public void setAssetName(String assetName)
    {
        this.assetName =
            assetName;
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

    public String getActiveTsString()
    {
        return DateUtil.mdyDate(activeTs);
    }

    public void setActiveTsString(String activeTs)
    {
        this.activeTs =
            DateUtil.parseDate(activeTs);
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

    public String getInactiveTsString()
    {
        return DateUtil.mdyDate(inactiveTs);
    }

    public void setInactiveTsString(String inactiveTs)
    {
        this.inactiveTs =
            DateUtil.parseDate(inactiveTs);
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProcessAssetValue)
        {
            ProcessAssetValue
                other =
                (ProcessAssetValue) o;
            return other.getProcessId()
                .equals(processId);
        }
        return false;
    }
}
