package com.sehinc.environment.value;

import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureData;

import java.util.Date;

public class SourceUsageValue
{
    private
    Integer
        id;
    private
    Integer
        assetSourceId;
    private
    Integer
        sourceId;
    private
    String
        sourceName;
    private
    Integer
        sourceUsageId;
    private
    String
        meterReading;
    private
    String
        transferRate;
    private
    Date
        periodStartTs;
    private
    Date
        periodEndTs;
    private
    EnvUnitOfMeasureData
        measureType;
    private
    Integer
        unitOfMeasureCd;
    private
    Boolean
        ableToEnterReading;
    private
    String
        unitDisplayName;
    private
    String
        displayColorCd;
    public static final
    String
        SOURCE_USAGE_NAME =
        "sourceUsage_name";
    public static final
    String
        ASCENDING =
        "Asc";

    public SourceUsageValue()
    {
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

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
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

    public EnvUnitOfMeasureData getMeasureType()
    {
        return measureType;
    }

    public void setMeasureType(EnvUnitOfMeasureData measureType)
    {
        this.measureType =
            measureType;
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

    public Boolean getAbleToEnterReading()
    {
        return ableToEnterReading;
    }

    public void setAbleToEnterReading(Boolean ableToEnterReading)
    {
        this.ableToEnterReading =
            ableToEnterReading;
    }

    public String getUnitDisplayName()
    {
        return unitDisplayName;
    }

    public void setUnitDisplayName(String unitDisplayName)
    {
        this.unitDisplayName =
            unitDisplayName;
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

    public String getDisplayColorCd()
    {
        if (this.displayColorCd
            != null
            && !this.displayColorCd
            .isEmpty())
        {
            String
                colorHashCode =
                this.displayColorCd;
            int
                length =
                colorHashCode.length();
            colorHashCode =
                colorHashCode.substring(1,
                                        length);
            StringBuffer
                fullPath =
                new StringBuffer();
            fullPath.append("/sehsvc/img/icons/colors/");
            fullPath.append(colorHashCode);
            fullPath.append(".gif");
            return fullPath.toString();
        }
        else
        {
            return "/sehsvc/img/icons/sehblank.gif";
        }
    }

    public void setDisplayColorCd(String displayColorCd)
    {
        this.displayColorCd =
            displayColorCd;
    }
}

