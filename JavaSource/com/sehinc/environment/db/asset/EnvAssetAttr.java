package com.sehinc.environment.db.asset;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.math.BigDecimal;

public class EnvAssetAttr
    extends HibernateData
{
    public static
    String
        FIND_BY_ASSET_ID =
        "EnvAssetAttr.findByAssetId";
    private
    Integer
        assetId;
    private
    String
        tankCarrier;
    private
    String
        tankLoadingMethod;
    private
    Boolean
        tankVaporRecovery;
    private
    String
        tankTypeDesc;
    private
    String
        tankContentsDesc;
    private
    String
        tankDiameter;
    private
    String
        tankHeight;
    private
    BigDecimal
        tankCapacity;
    private
    String
        stackLatitude;
    private
    String
        stackLongitude;
    private
    String
        stackHeight;
    private
    String
        stackDiameter;
    private
    String
        stackExitTemp;
    private
    String
        stackExitVelocity;
    private
    String
        stackExitFlowRate;
    private
    String
        stackDesc;
    private
    BigDecimal
        epRatedMmbtu;
    private
    BigDecimal
        epCapacityMmbtu;
    private
    BigDecimal
        transferRate;

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
        }
    }

    public EnvAssetAttr()
    {
    }

    public EnvAssetAttr(Integer id)
    {
        setId(id);
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

    public String getTankCarrier()
    {
        return tankCarrier;
    }

    public void setTankCarrier(String tankCarrier)
    {
        this.tankCarrier =
            tankCarrier;
    }

    public String getTankLoadingMethod()
    {
        return tankLoadingMethod;
    }

    public void setTankLoadingMethod(String tankLoadingMethod)
    {
        this.tankLoadingMethod =
            tankLoadingMethod;
    }

    public Boolean getTankVaporRecovery()
    {
        return tankVaporRecovery;
    }

    public void setTankVaporRecovery(Boolean tankVaporRecovery)
    {
        this.tankVaporRecovery =
            tankVaporRecovery;
    }

    public String getTankTypeDesc()
    {
        return tankTypeDesc;
    }

    public void setTankTypeDesc(String tankTypeDesc)
    {
        this.tankTypeDesc =
            tankTypeDesc;
    }

    public String getTankContentsDesc()
    {
        return tankContentsDesc;
    }

    public void setTankContentsDesc(String tankContentsDesc)
    {
        this.tankContentsDesc =
            tankContentsDesc;
    }

    public String getTankDiameter()
    {
        return tankDiameter;
    }

    public void setTankDiameter(String tankDiameter)
    {
        this.tankDiameter =
            tankDiameter;
    }

    public String getTankHeight()
    {
        return tankHeight;
    }

    public void setTankHeight(String tankHeight)
    {
        this.tankHeight =
            tankHeight;
    }

    public BigDecimal getTankCapacity()
    {
        return tankCapacity;
    }

    public String getTankCapacityString()
    {
        if (tankCapacity
            == null)
        {
            return "0";
        }
        else
        {
            return tankCapacity.toString();
        }
    }

    public void setTankCapacity(BigDecimal tankCapacity)
    {
        this.tankCapacity =
            tankCapacity;
    }

    public void setTankCapacityString(String tankCapacity)
    {
        if (tankCapacity
            != null
            && tankCapacity.length()
               > 0)
        {
            this.tankCapacity =
                new BigDecimal(tankCapacity);
        }
        else
        {
            this.tankCapacity =
                new BigDecimal(0);
        }
    }

    public String getStackLatitude()
    {
        return stackLatitude;
    }

    public void setStackLatitude(String stackLatitude)
    {
        this.stackLatitude =
            stackLatitude;
    }

    public String getStackLongitude()
    {
        return stackLongitude;
    }

    public void setStackLongitude(String stackLongitude)
    {
        this.stackLongitude =
            stackLongitude;
    }

    public String getStackHeight()
    {
        return stackHeight;
    }

    public void setStackHeight(String stackHeight)
    {
        this.stackHeight =
            stackHeight;
    }

    public String getStackDiameter()
    {
        return stackDiameter;
    }

    public void setStackDiameter(String stackDiameter)
    {
        this.stackDiameter =
            stackDiameter;
    }

    public String getStackExitTemp()
    {
        return stackExitTemp;
    }

    public void setStackExitTemp(String stackExitTemp)
    {
        this.stackExitTemp =
            stackExitTemp;
    }

    public String getStackExitVelocity()
    {
        return stackExitVelocity;
    }

    public void setStackExitVelocity(String stackExitVelocity)
    {
        this.stackExitVelocity =
            stackExitVelocity;
    }

    public String getStackExitFlowRate()
    {
        return stackExitFlowRate;
    }

    public void setStackExitFlowRate(String stackExitFlowRate)
    {
        this.stackExitFlowRate =
            stackExitFlowRate;
    }

    public void setStackDesc(String stackDesc)
    {
        this.stackDesc =
            stackDesc;
    }

    public String getStackDesc()
    {
        return stackDesc;
    }

    public BigDecimal getEpRatedMmbtu()
    {
        return epRatedMmbtu;
    }

    public String getEpRatedMmbtuString()
    {
        if (epRatedMmbtu
            == null)
        {
            return "0";
        }
        else
        {
            return epRatedMmbtu.toString();
        }
    }

    public void setEpRatedMmbtu(BigDecimal epRatedMmbtu)
    {
        this.epRatedMmbtu =
            epRatedMmbtu;
    }

    public void setEpRatedMmbtuString(String epRatedMmbtu)
    {
        if (epRatedMmbtu
            != null
            && epRatedMmbtu.length()
               > 0)
        {
            this.epRatedMmbtu =
                new BigDecimal(epRatedMmbtu);
        }
        else
        {
            this.epRatedMmbtu =
                new BigDecimal(0);
        }
    }

    public BigDecimal getEpCapacityMmbtu()
    {
        return epCapacityMmbtu;
    }

    public String getEpCapacityMmbtuString()
    {
        if (epCapacityMmbtu
            == null)
        {
            return "0";
        }
        else
        {
            return epCapacityMmbtu.toString();
        }
    }

    public void setEpCapacityMmbtu(BigDecimal epCapacityMmbtu)
    {
        this.epCapacityMmbtu =
            epCapacityMmbtu;
    }

    public void setEpCapacityMmbtuString(String epCapacityMmbtu)
    {
        if (epCapacityMmbtu
            != null
            && epCapacityMmbtu.length()
               > 0)
        {
            this.epCapacityMmbtu =
                new BigDecimal(epCapacityMmbtu);
        }
        else
        {
            this.epCapacityMmbtu =
                new BigDecimal(0);
        }
    }

    public static EnvAssetAttr findByAssetId(Integer assetId)
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
                    assetId}};
        return (EnvAssetAttr) HibernateUtil.findUniqueByNamedQuery(FIND_BY_ASSET_ID,
                                                                   parameters);
    }
}