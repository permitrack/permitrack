package com.sehinc.environment.action.assetsubstance;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class AssetSubstanceForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        assetId;
    private
    Integer
        substanceTypeCd;
    private
    Integer
        efficiencyFactor;
    private
    String
        additionalInfo;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public void reset()
    {
        id =
            null;
        assetId =
            null;
        substanceTypeCd =
            null;
        efficiencyFactor =
            null;
        additionalInfo =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}