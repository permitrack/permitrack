package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SourceSubstanceForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
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
    String
        sourceDisplayName;
    private
    Integer[]
        selectedSubstances;
    private
    String
        natGasEmFactor;
    private
    Integer
        natGasEfUnit;
    private
    String
        bulkLiqEmFactor;
    private
    Integer
        bulkLiqEfUnit;
    private
    Integer
        srcCodeType;

    public Integer getSrcCodeType()
    {
        return srcCodeType;
    }

    public void setSrcCodeType(Integer srcCodeType)
    {
        this.srcCodeType =
            srcCodeType;
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

    public String getSourceDisplayName()
    {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName)
    {
        this.sourceDisplayName =
            sourceDisplayName;
    }

    public Integer[] getSelectedSubstances()
    {
        return selectedSubstances;
    }

    public void setSelectedSubstances(Integer[] selectedSubstances)
    {
        this.selectedSubstances =
            selectedSubstances;
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

    public void reset()
    {
        id =
            null;
        substanceId =
            null;
        sourceId =
            null;
        ratio =
            null;
        sourceDisplayName =
            null;
        selectedSubstances =
            null;
        natGasEmFactor =
            null;
        natGasEfUnit =
            null;
        bulkLiqEmFactor =
            null;
        bulkLiqEfUnit =
            null;
        srcCodeType =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
