package com.sehinc.environment.value;

import com.sehinc.common.util.DateUtil;

import java.util.Date;

public class ControlUsageValue
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
    private
    Integer
        substanceTypeCd;
    private
    Integer
        efficiencyFactor;
    private
    String
        additionalInfo;

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

    public Integer getAssetControlId()
    {
        return assetControlId;
    }

    public void setAssetControlId(Integer assetSourceId)
    {
        this.assetControlId =
            assetSourceId;
    }

    private
    Integer
        id;
    private
    String
        sourceName;
    private
    Integer
        sourceUsageId;

    public ControlUsageValue()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getSourceUsageId()
    {
        return sourceUsageId;
    }

    public void setSourceUsageId(Integer sourceUsageId)
    {
        this.sourceUsageId =
            sourceUsageId;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName =
            sourceName;
    }

    public String getPeriodStartTsString()
    {
        return DateUtil.mdyDate(periodStartTs);
    }

    public void setPeriodStartTsString(String periodStartTs)
    {
        this.periodStartTs =
            DateUtil.parseDate(periodStartTs);
    }

    public String getPeriodEndTsString()
    {
        return DateUtil.mdyDate(periodEndTs);
    }

    public void setPeriodEndTsString(String periodEndTs)
    {
        this.periodEndTs =
            DateUtil.parseDate(periodEndTs);
    }

    public Integer getSubstanceTypeCd()
    {
        return substanceTypeCd;
    }

    public void setSubstanceTypeCd(Integer substanceTypeCd)
    {
        this.substanceTypeCd =
            substanceTypeCd;
    }

    public Integer getEfficiencyFactor()
    {
        return efficiencyFactor;
    }

    public void setEfficiencyFactor(Integer efficiencyFactor)
    {
        this.efficiencyFactor =
            efficiencyFactor;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo =
            additionalInfo;
    }
}

