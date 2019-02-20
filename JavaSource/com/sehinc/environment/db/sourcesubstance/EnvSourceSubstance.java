package com.sehinc.environment.db.sourcesubstance;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureData;
import com.sehinc.environment.db.lookup.LookupData;
import com.sehinc.environment.db.status.StatusData;

import java.math.BigDecimal;
import java.util.List;

public class EnvSourceSubstance
    extends StatusData
{
    public static
    String
        FIND_BY_SUBSTANCE_ID =
        "EnvSourceSubstance.findBySubstanceId";
    public static
    String
        FIND_BY_SOURCE_ID =
        "EnvSourceSubstance.findBySourceId";
    public static
    String
        FIND_BY_SUBSTANCE_ID_AND_SOURCE_ID =
        "EnvSourceSubstance.findBySubstanceIdAndSourceId";
    private
    Integer
        substanceId;
    private
    Integer
        sourceId;
    private
    BigDecimal
        ratio;
    private
    BigDecimal
        natGasEmFactor;
    private
    EnvUnitOfMeasureData
        natGasEfUnit;
    private
    BigDecimal
        bulkLiqEmFactor;
    private
    EnvUnitOfMeasureData
        bulkLiqEfUnit;

    public EnvSourceSubstance()
    {
    }

    public EnvSourceSubstance(Integer id)
    {
        setId(id);
    }

    public void setRatio(BigDecimal ratio)
    {
        this.ratio =
            ratio;
    }

    public BigDecimal getRatio()
    {
        return ratio;
    }

    public String getRatioString()
    {
        if (ratio
            == null)
        {
            return "0";
        }
        else
        {
            return ratio.toString();
        }
    }

    public void setRatioString(String ratio)
    {
        if (ratio
            != null
            && ratio.length()
               > 0)
        {
            this.ratio =
                new BigDecimal(ratio);
        }
        else
        {
            this.ratio =
                new BigDecimal(0);
        }
    }

    public BigDecimal getNatGasEmFactor()
    {
        return natGasEmFactor;
    }

    public void setNatGasEmFactor(BigDecimal natGasEmFactor)
    {
        this.natGasEmFactor =
            natGasEmFactor;
    }

    public String getNatGasEmFactorString()
    {
        if (natGasEmFactor
            == null)
        {
            return "0";
        }
        else
        {
            return natGasEmFactor.toString();
        }
    }

    public void setNatGasEmFactorString(String natGasEmFactor)
    {
        if (natGasEmFactor
            != null
            && natGasEmFactor.length()
               > 0)
        {
            this.natGasEmFactor =
                new BigDecimal(natGasEmFactor);
        }
        else
        {
            this.natGasEmFactor =
                new BigDecimal(0);
        }
    }

    public BigDecimal getBulkLiqEmFactor()
    {
        return bulkLiqEmFactor;
    }

    public void setBulkLiqEmFactor(BigDecimal bulkLiqEmFactor)
    {
        this.bulkLiqEmFactor =
            bulkLiqEmFactor;
    }

    public String getBulkLiqEmFactorString()
    {
        if (bulkLiqEmFactor
            == null)
        {
            return "0";
        }
        else
        {
            return bulkLiqEmFactor.toString();
        }
    }

    public void setBulkLiqEmFactorString(String bulkLiqEmFactor)
    {
        if (bulkLiqEmFactor
            != null
            && bulkLiqEmFactor.length()
               > 0)
        {
            this.bulkLiqEmFactor =
                new BigDecimal(bulkLiqEmFactor);
        }
        else
        {
            this.bulkLiqEmFactor =
                new BigDecimal(0);
        }
    }

    public void setNatGasEfUnit(EnvUnitOfMeasureData natGasEfUnit)
    {
        this.natGasEfUnit =
            natGasEfUnit;
    }

    public EnvUnitOfMeasureData getNatGasEfUnit()
    {
        return natGasEfUnit;
    }

    public void setNatGasEfUnitType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setNatGasEfUnit((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public void setBulkLiqEfUnit(EnvUnitOfMeasureData bulkLiqEfUnit)
    {
        this.bulkLiqEfUnit =
            bulkLiqEfUnit;
    }

    public EnvUnitOfMeasureData getBulkLiqEfUnit()
    {
        return bulkLiqEfUnit;
    }

    public void setBulkLiqEfUnitType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setBulkLiqEfUnit((EnvUnitOfMeasureData) code);
                break;
            }
        }
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

    public Integer getSubstanceId()
    {
        return substanceId;
    }

    public void setSubstanceId(Integer substanceId)
    {
        this.substanceId =
            substanceId;
    }

    public static List<EnvSourceSubstance> findBySubstanceId(Integer substanceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceId",
                    substanceId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_ID,
                                              parameters);
    }

    public static List<EnvSourceSubstance> findBySourceId(Integer sourceId)
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

    public static List findBySubstanceIdAndSourceId(Integer substanceId, Integer sourceId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceId",
                    substanceId},
                {
                    "sourceId",
                    sourceId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_ID_AND_SOURCE_ID,
                                              parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nsubstanceId=")
            .append(substanceId);
        buffer.append("\nsourceId=")
            .append(sourceId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}