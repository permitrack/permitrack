package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class SourceUsageForm
    extends BaseValidatorForm
{
    private
    Integer
        assetSourceId;
    private
    String
        sourceDisplayName;
    private
    String
        meterReading;
    private
    Date
        periodStartTs;
    private
    Date
        periodEndTs;
    private
    Integer
        unitOfMeasureCd;
    private
    String
        sourceDensity;
    private
    Integer
        sourceDensityUm;
    private
    String
        sourceDensityUmDesc;
    private
    String
        transferRate;
    Integer
        sourceUsageId;
    String
        unitOfMeasureDesc;
    private
    Integer
        sourceUsagesPerPage =
        null;
    private
    Boolean
        sourceUsagesPerPageChanged =
        false;
    private
    String
        description;

    public Integer getSourceUsagesPerPage()
    {
        return sourceUsagesPerPage;
    }

    public void setSourceUsagesPerPage(Integer sourceUsagesPerPage)
    {
        this.sourceUsagesPerPage =
            sourceUsagesPerPage;
    }

    public Boolean getSourceUsagesPerPageChanged()
    {
        return sourceUsagesPerPageChanged;
    }

    public void setSourceUsagesPerPageChanged(Boolean sourceUsagesPerPageChanged)
    {
        this.sourceUsagesPerPageChanged =
            sourceUsagesPerPageChanged;
    }

    public Integer getAssetSourceId()
    {
        return assetSourceId;
    }

    public void setAssetSourceId(Integer assetSourceId)
    {
        this.assetSourceId =
            assetSourceId;
    }

    public String getSourceDisplayName()
    {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName)
    {
        this.sourceDisplayName =
            sourceDisplayName;
    }

    public String getMeterReading()
    {
        return meterReading;
    }

    public void setMeterReading(String meterReading)
    {
        this.meterReading =
            meterReading;
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

    public Date getPeriodEndTs()
    {
        return periodEndTs;
    }

    public void setPeriodEndTs(Date periodEndTs)
    {
        this.periodEndTs =
            periodEndTs;
    }

    public Integer getUnitOfMeasureCd()
    {
        return unitOfMeasureCd;
    }

    public void setUnitOfMeasureCd(Integer unitOfMeasureCd)
    {
        this.unitOfMeasureCd =
            unitOfMeasureCd;
    }

    public void setSourceDensity(String sourceDensity)
    {
        this.sourceDensity =
            sourceDensity;
    }

    public String getSourceDensity()
    {
        return sourceDensity;
    }

    public void setSourceDensityUm(Integer sourceDensityUm)
    {
        this.sourceDensityUm =
            sourceDensityUm;
    }

    public Integer getSourceDensityUm()
    {
        return sourceDensityUm;
    }

    public void setSourceDensityUmDesc(String sourceDensityUmDesc)
    {
        this.sourceDensityUmDesc =
            sourceDensityUmDesc;
    }

    public String getSourceDensityUmDesc()
    {
        return sourceDensityUmDesc;
    }

    public void setPeriodStartTsString(String periodStartTs)
    {
        this.periodStartTs =
            DateUtil.parseDate(periodStartTs);
    }

    public String getPeriodStartTsString()
    {
        return DateUtil.mdyDate(periodStartTs);
    }

    public void setPeriodEndTsString(String periodEndTs)
    {
        this.periodEndTs =
            DateUtil.parseDate(periodEndTs);
    }

    public String getPeriodEndTsString()
    {
        return DateUtil.mdyDate(periodEndTs);
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

    public String getUnitOfMeasureDesc()
    {
        return unitOfMeasureDesc;
    }

    public void setUnitOfMeasureDesc(String unitOfMeasureDesc)
    {
        this.unitOfMeasureDesc =
            unitOfMeasureDesc;
    }

    public String getTransferRate()
    {
        return transferRate;
    }

    public void setTransferRate(String transferRate)
    {
        this.transferRate =
            transferRate;
    }

    public void reset()
    {
        assetSourceId =
            null;
        sourceDisplayName =
            null;
        meterReading =
            null;
        periodStartTs =
            null;
        periodEndTs =
            null;
        unitOfMeasureCd =
            null;
        sourceUsageId =
            null;
        unitOfMeasureDesc =
            null;
        sourceDensity =
            null;
        sourceDensityUm =
            null;
        sourceDensityUmDesc =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getDescription()
    {
        return description;
    }
}

