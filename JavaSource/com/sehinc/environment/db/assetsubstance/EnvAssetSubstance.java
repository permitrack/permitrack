package com.sehinc.environment.db.assetsubstance;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.status.StatusData;

import java.util.List;

public class EnvAssetSubstance
    extends StatusData
{
    public static
    String
        FIND_BY_ASSET_ID =
        "EnvAssetSubstance.findByAssetId";
    public static
    String
        FIND_BY_ASSET_ID_AND_SUBSTANCE_TYPE_CD =
        "EnvAssetSubstance.findByAssetIdAndSubstanceTypeCd";
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
    private
    EnvAsset
        asset;

    public EnvAsset getAsset()
    {
        return asset;
    }

    public void setAsset(EnvAsset asset)
    {
        this.asset =
            asset;
    }

    public EnvAssetSubstance()
    {
    }

    public EnvAssetSubstance(Integer id)
    {
        setId(id);
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

    public static List findByAssetId(Integer assetId)
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
        return HibernateUtil.findByNamedQuery(FIND_BY_ASSET_ID,
                                              parameters);
    }

    public static List findByAssetIdAndSubstanceTypeCd(Integer assetId, Integer substanceTypeCd)
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
                    assetId},
                {
                    "substanceTypeCd",
                    substanceTypeCd}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ASSET_ID_AND_SUBSTANCE_TYPE_CD,
                                              parameters);
    }
}