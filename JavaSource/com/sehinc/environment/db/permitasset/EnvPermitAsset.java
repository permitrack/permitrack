package com.sehinc.environment.db.permitasset;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvPermitAsset
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_PERMIT_DETAIL_ID =
        "EnvPermitAsset.findByPermitDetailId";
    public static
    String
        FIND_BY_PERMIT_DETAIL_AND_ASSET_ID =
        "EnvPermitAsset.findByPermitDetailAndAssetId";
    public static
    String
        FIND_BY_ASSET_ID =
        "EnvPermitAsset.findByAssetId";
    private
    Integer
        permitDetailId;
    private
    Integer
        assetId;
    private
    String
        name;
    private
    String
        description;
    private
    String
        number;

    public EnvPermitAsset()
    {
    }

    public EnvPermitAsset(Integer id)
    {
        setId(id);
    }

    public Integer getPermitDetailId()
    {
        return permitDetailId;
    }

    public void setPermitDetailId(Integer permitDetailId)
    {
        this.permitDetailId =
            permitDetailId;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
    }

    public String getNumberAndName()
    {
        return this.number
               + " - "
               + this.name;
    }

    public static List findByPermitDetailId(Integer permitDetailId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "permitDetailId",
                    permitDetailId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_PERMIT_DETAIL_ID,
                                              parameters);
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

    public static EnvPermitAsset findByPermitDetailAndAssetId(Integer permitDetailId, Integer assetId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "permitDetailId",
                    permitDetailId},
                {
                    "assetId",
                    assetId}};
        return (EnvPermitAsset) HibernateUtil.findUniqueByNamedQuery(FIND_BY_PERMIT_DETAIL_AND_ASSET_ID,
                                                                     parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\npermitDetailId=")
            .append(permitDetailId);
        buffer.append("\nassetId=")
            .append(assetId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}