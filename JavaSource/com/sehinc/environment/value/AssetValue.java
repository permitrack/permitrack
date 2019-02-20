package com.sehinc.environment.value;

import com.sehinc.environment.db.asset.EnvAsset;

import java.util.List;
import java.util.Set;

public class AssetValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        number;
    private
    String
        name;
    private
    String
        description;
    private
    String
        facilityName;
    private
    String
        facilityNumber;
    private
    List<AssetValue>
        subAssets;
    private
    Set
        assetTypes;
    private
    String
        meterNumberName;
    private
    Boolean
        meter;
    private
    Integer
        belongsToMeter;
    private
    String[]
        selectedTypeCodes;

    public AssetValue()
    {
    }

    public AssetValue(EnvAsset asst)
    {
        this.id =
            asst.getId();
        this.number =
            asst.getNumber();
        this.name =
            asst.getName();
        this.description =
            asst.getDescription();
        this.meter =
            asst.getMeter();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public Set getAssetTypes()
    {
        return assetTypes;
    }

    public void setAssetTypes(Set assetTypes)
    {
        this.assetTypes =
            assetTypes;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
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

    public String getFacilityName()
    {
        return facilityName;
    }

    public void setFacilityName(String facilityName)
    {
        this.facilityName =
            facilityName;
    }

    public String getFacilityNumber()
    {
        return facilityNumber;
    }

    public void setFacilityNumber(String facilityNumber)
    {
        this.facilityNumber =
            facilityNumber;
    }

    public List<AssetValue> getSubAssets()
    {
        return subAssets;
    }

    public void setSubAssets(List<AssetValue> subAssets)
    {
        this.subAssets =
            subAssets;
    }

    public String getMeterNumberName()
    {
        return meterNumberName;
    }

    public void setMeterNumberName(String meterNumberName)
    {
        this.meterNumberName =
            meterNumberName;
    }

    public Boolean getMeter()
    {
        return meter;
    }

    public void setMeter(Boolean meter)
    {
        this.meter =
            meter;
    }

    public void setBelongsToMeter(Integer belongsToMeter)
    {
        this.belongsToMeter =
            belongsToMeter;
    }

    public Integer getBelongsToMeter()
    {
        return belongsToMeter;
    }

    public String[] getSelectedTypeCodes()
    {
        return selectedTypeCodes;
    }

    public void setSelectedTypeCodes(String[] selectedTypeCodes)
    {
        this.selectedTypeCodes =
            selectedTypeCodes;
    }

    public boolean equals(Object o)
    {
        if (o instanceof AssetValue)
        {
            AssetValue
                other =
                (AssetValue) o;
            return other.getNumber()
                .equals(number);
        }
        return false;
    }
}

