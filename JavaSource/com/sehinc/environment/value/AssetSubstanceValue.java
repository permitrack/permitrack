package com.sehinc.environment.value;

public class AssetSubstanceValue
{
    private
    Integer
        assetSubstanceId;
    private
    String
        substanceTypeName;
    private
    Integer
        efficiencyFactor;
    private
    String
        additionalInfo;

    public AssetSubstanceValue()
    {
    }

    public Integer getAssetSubstanceId()
    {
        return assetSubstanceId;
    }

    public void setAssetSubstanceId(Integer assetSubstanceId)
    {
        this.assetSubstanceId =
            assetSubstanceId;
    }

    public String getSubstanceTypeName()
    {
        return substanceTypeName;
    }

    public void setSubstanceTypeName(String substanceTypeName)
    {
        this.substanceTypeName =
            substanceTypeName;
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