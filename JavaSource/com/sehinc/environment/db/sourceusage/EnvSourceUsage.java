package com.sehinc.environment.db.sourceusage;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureData;
import com.sehinc.environment.db.lookup.LookupData;
import com.sehinc.environment.db.status.StatusData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EnvSourceUsage
    extends StatusData
{
    public static
    String
        FIND_BY_ASSET_SOURCE_ID =
        "EnvSourceUsage.findByAssetSourceId";
    public static
    String
        FIND_BY_ASSET_SOURCE_ID_AND_DATE_RANGE =
        "EnvSourceUsage.findByAssetSourceIdAndDateRange";
    private
    Integer
        assetSourceId;
    private
    BigDecimal
        meterReading;
    private
    Date
        periodStartTs;
    private
    Date
        periodEndTs;
    private
    EnvUnitOfMeasureData
        unitOfMeasureCd;
    private
    long
        version;
    private
    BigDecimal
        transferRate;

    public EnvSourceUsage()
    {
    }

    public EnvSourceUsage(Integer id)
    {
        setId(id);
    }

    public String getPeriodStartTsText()
    {
        if (periodStartTs
            != null)
        {
            return DateUtil.mdyDate(periodStartTs);
        }
        else
        {
            return "";
        }
    }

    public String getPeriodEndTsText()
    {
        if (periodEndTs
            != null)
        {
            return DateUtil.mdyDate(periodEndTs);
        }
        else
        {
            return "";
        }
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

    public BigDecimal getMeterReading()
    {
        return meterReading;
    }

    public void setMeterReading(BigDecimal meterReading)
    {
        this.meterReading =
            meterReading;
    }

    public String getMeterReadingString()
    {
        if (meterReading
            == null)
        {
            return "0";
        }
        else
        {
            return meterReading.toString();
        }
    }

    public void setMeterReadingString(String meterReading)
    {
        if (meterReading
            != null
            && meterReading.length()
               > 0)
        {
            this.meterReading =
                new BigDecimal(meterReading);
        }
        else
        {
            this.meterReading =
                new BigDecimal(0);
        }
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

    public EnvUnitOfMeasureData getUnitOfMeasureCd()
    {
        return unitOfMeasureCd;
    }

    public void setUnitOfMeasureCd(EnvUnitOfMeasureData unitOfMeasureCd)
    {
        this.unitOfMeasureCd =
            unitOfMeasureCd;
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

    public void setUnitOfMeasureType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setUnitOfMeasureCd((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public BigDecimal getTransferRate()
    {
        return transferRate;
    }

    public String getTransferRateString()
    {
        if (transferRate
            == null)
        {
            return "";
        }
        else
        {
            return transferRate.toString();
        }
    }

    public void setTransferRate(BigDecimal transferRate)
    {
        this.transferRate =
            transferRate;
    }

    public void setTransferRateString(String transferRate)
    {
        if (transferRate
            != null
            && transferRate.length()
               > 0)
        {
            this.transferRate =
                new BigDecimal(transferRate);
        }
        else
        {
            this.transferRate = null;
//                new BigDecimal(0);
        }
    }

    public static List findByAssetSourceId(Integer assetSourceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "assetSourceId",
                    assetSourceId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ASSET_SOURCE_ID,
                                              parameters);
    }

    public static List findByAssetSourceIdAndDateRange(Integer assetSourceId, Date activeTs, Date inactiveTs)
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from com.sehinc.environment.db.sourceusage.EnvSourceUsage as data where data.assetSourceId = "
            + assetSourceId
            + " and data.periodStartTs >= '"
            + DateUtil.ymdDate(activeTs)
            + "' and data.periodEndTs <= '"
            + DateUtil.ymdDate(inactiveTs)
            + "' order by data.assetSourceId asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
