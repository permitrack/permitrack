package com.sehinc.environment.action.sourcesubstance;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.environment.value.SourceSubstanceValue;
import org.apache.struts.action.ActionErrors;

import java.util.List;

public class SourceSubstanceMultiForm
    extends BaseValidatorForm
{
    private
    Integer
        sourceId;
    private
    String
        sourceDisplayName;
    private
    List<SourceSubstanceValue>
        sourceSubstances;
    private
    String
        ratio;
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

    public Integer getSourceId()
    {
        return sourceId;
    }

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
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

    public List<SourceSubstanceValue> getSourceSubstances()
    {
        return sourceSubstances;
    }

    public void setSourceSubstances(List<SourceSubstanceValue> sourceSubstances)
    {
        this.sourceSubstances =
            sourceSubstances;
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
        sourceId =
            null;
        sourceDisplayName =
            null;
        sourceSubstances =
            null;
        ratio =
            null;
        natGasEmFactor =
            null;
        natGasEfUnit =
            null;
        bulkLiqEmFactor =
            null;
        bulkLiqEfUnit =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}