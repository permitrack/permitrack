package com.sehinc.environment.value;

import com.sehinc.environment.db.substance.EnvSubstance;

public class SourceSubstanceValue
{
    private
    String
        substanceName;
    private
    String
        substanceTypeDesc;
    private
    String
        substancePartNumber;
    private
    String
        substanceType;
    private
    Integer
        substanceId;
    private
    Integer
        sourceId;
    private
    String
        ratio;
    private
    Integer
        sourceSubstanceId;
    private
    String
        calculatedTotalUnit;
    private
    String
        natGasEmFactor;
    private
    Integer
        natGasEfUnit;
    private
    String
        natGasEfUnitDesc;
    private
    String
        bulkLiqEmFactor;
    private
    Integer
        bulkLiqEfUnit;
    private
    String
        bulkLiqEfUnitDesc;
    private
    Integer
        sourceTypeCode;
    private
    Float
        newReading;
    private
    String
        assetName;
    private
    Float
        calculatedTotal;

    public SourceSubstanceValue()
    {
    }

    public SourceSubstanceValue(EnvSubstance substance)
    {
        this.setSubstanceName(substance.getName());
        this.setSubstanceTypeDesc(substance.getSubstanceType()
                                      .getDescription());
        this.setSubstanceId(substance.getId());
        this.setSubstancePartNumber(substance.getPartNum());
    }

    public void setAssetName(String assetName)
    {
        this.assetName =
            assetName;
    }

    public String getAssetName()
    {
        return assetName;
    }

    public void setSubstanceType(String substanceType)
    {
        this.substanceType =
            substanceType;
    }

    public String getSubstanceType()
    {
        return substanceType;
    }

    public String getSubstanceName()
    {
        return substanceName;
    }

    public void setSubstanceName(String substanceName)
    {
        this.substanceName =
            substanceName;
    }

    public String getSubstanceTypeDesc()
    {
        return substanceTypeDesc;
    }

    public void setSubstanceTypeDesc(String substanceTypeDesc)
    {
        this.substanceTypeDesc =
            substanceTypeDesc;
    }

    public String getSubstancePartNumber()
    {
        return substancePartNumber;
    }

    public void setSubstancePartNumber(String substancePartNumber)
    {
        this.substancePartNumber =
            substancePartNumber;
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

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
    }

    public String getRatio()
    {
        return ratio;
    }

    public void setRatio(String ratio)
    {
        this.ratio =
            ratio;
    }

    public Integer getSourceSubstanceId()
    {
        return sourceSubstanceId;
    }

    public void setSourceSubstanceId(Integer sourceSubstanceId)
    {
        this.sourceSubstanceId =
            sourceSubstanceId;
    }

    public void setCalculatedTotal(Float calculatedTotal)
    {
        this.calculatedTotal =
            calculatedTotal;
    }

    public Float getCalculatedTotal()
    {
        return calculatedTotal;
    }

    public String getCalculatedTotalUnit()
    {
        return calculatedTotalUnit;
    }

    public void setCalculatedTotalUnit(String calculatedTotalUnit)
    {
        this.calculatedTotalUnit =
            calculatedTotalUnit;
    }

    public String getNatGasEmFactor()
    {
        return natGasEmFactor;
    }

    public void setNatGasEmFactor(String natGasEmFactor)
    {
        this.natGasEmFactor =
            natGasEmFactor;
    }

    public Integer getNatGasEfUnit()
    {
        return natGasEfUnit;
    }

    public void setNatGasEfUnit(Integer natGasEfUnit)
    {
        this.natGasEfUnit =
            natGasEfUnit;
    }

    public String getBulkLiqEmFactor()
    {
        return bulkLiqEmFactor;
    }

    public void setBulkLiqEmFactor(String bulkLiqEmFactor)
    {
        this.bulkLiqEmFactor =
            bulkLiqEmFactor;
    }

    public Integer getBulkLiqEfUnit()
    {
        return bulkLiqEfUnit;
    }

    public void setBulkLiqEfUnit(Integer bulkLiqEfUnit)
    {
        this.bulkLiqEfUnit =
            bulkLiqEfUnit;
    }

    public String getNatGasEfUnitDesc()
    {
        return natGasEfUnitDesc;
    }

    public void setNatGasEfUnitDesc(String natGasEfUnitDesc)
    {
        this.natGasEfUnitDesc =
            natGasEfUnitDesc;
    }

    public String getBulkLiqEfUnitDesc()
    {
        return bulkLiqEfUnitDesc;
    }

    public void setBulkLiqEfUnitDesc(String bulkLiqEfUnitDesc)
    {
        this.bulkLiqEfUnitDesc =
            bulkLiqEfUnitDesc;
    }

    public void setSourceTypeCode(Integer sourceTypeCode)
    {
        this.sourceTypeCode =
            sourceTypeCode;
    }

    public Integer getSourceTypeCode()
    {
        return sourceTypeCode;
    }

    public void setNewReading(Float newReading)
    {
        this.newReading =
            newReading;
    }

    public Float getNewReading()
    {
        return newReading;
    }
}
