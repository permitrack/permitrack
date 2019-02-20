package com.sehinc.environment.db.controlusage;

import com.sehinc.environment.db.status.StatusData;

import java.util.Date;

public class EnvControlUsage
    extends StatusData
{
    private
    int
        assetControlId;
    private
    Date
        periodStartTs;
    private
    Date
        periodEndTs;
    private
    String
        description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Date getPeriodEndTs()
    {
        return periodEndTs;
    }

    public void setPeriodEndTs(Date periodEndTs)
    {
        this.periodEndTs =
            periodEndTs;
    }

    public Date getPeriodStartTs()
    {
        return periodStartTs;
    }

    public void setPeriodStartTs(Date periodStartTs)
    {
        this.periodStartTs =
            periodStartTs;
    }

    public EnvControlUsage()
    {
    }

    public EnvControlUsage(Integer id)
    {
        setId(id);
    }

    public Integer getAssetControlId()
    {
        return assetControlId;
    }

    public void setAssetControlId(Integer assetSourceId)
    {
        this.assetControlId =
            assetSourceId;
    }
}
