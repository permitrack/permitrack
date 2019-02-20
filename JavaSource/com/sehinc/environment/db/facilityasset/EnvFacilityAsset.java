package com.sehinc.environment.db.facilityasset;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class EnvFacilityAsset
    extends UserUpdatableData
{
    public static
    String
        FIND_BY_FACILITY_ID =
        "EnvFacilityAsset.findByFacilityId";
    public static
    String
        FIND_BY_FACILITY_AND_ASSET_ID =
        "EnvFacilityAsset.findByFacilityAndAssetId";
    public static
    String
        FIND_BY_ASSET_ID =
        "EnvFacilityAsset.findByAssetId";
    private
    Integer
        facilityId;
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

    public EnvFacilityAsset()
    {
    }

    public EnvFacilityAsset(Integer id)
    {
        setId(id);
    }

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
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

    public void setNumber(String number)
    {
        this.number =
            number;
    }

    public String getNumber()
    {
        return number;
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

    public static List<EnvFacilityAsset> findByFacilityId(Integer facilityId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "facilityId",
                    facilityId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_FACILITY_ID,
                                              parameters);
    }

    public static List<EnvFacilityAsset> findByAssetId(Integer assetId)
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

    public static EnvFacilityAsset findByFacilityAndAssetId(Integer facilityId, Integer assetId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "facilityId",
                    facilityId},
                {
                    "assetId",
                    assetId}};
        return (EnvFacilityAsset) HibernateUtil.findUniqueByNamedQuery(FIND_BY_FACILITY_AND_ASSET_ID,
                                                                       parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nfacilityId=")
            .append(facilityId);
        buffer.append("\nassetId=")
            .append(assetId);
        buffer.append("\nassetNumber=")
            .append(number);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
