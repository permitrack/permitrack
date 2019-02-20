package com.sehinc.environment.db.asset;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.status.StatusData;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class EnvAsset
    extends StatusData
{
    public static
    String
        FIND_BY_CLIENT_ID =
        "EnvAsset.assetListByClientId";
    public static
    String
        FIND_BY_NAME_AND_NUMBER =
        "EnvAsset.assetListByNameAndNumber";
    public static
    String
        FIND_BY_PARENT_ID =
        "EnvAsset.findAllByParentId";
    public static
    String
        FIND_BY_METER_ID =
        "EnvAsset.findAllByMeterId";
    public static
    String
        FIND_PARENT_BY_CLIENT_ID =
        "EnvAsset.assetParentListByClientId";
    public static
    String
        FIND_BY_CLIENT_ID_SORT_BY_NUMBER =
        "EnvAsset.assetListByClientIdSortByNumber";
    public static
    String
        FIND_METERS_BY_CLIENT_ID_SORT_BY_NUMBER =
        "EnvAsset.meterListByClientIdSortByNumber";
    public static
    String
        FIND_OTHER_ASSETS_BY_CLIENT_ID =
        "EnvAsset.OtherAssetListByClientId";
    public static
    String
        FIND_ASSETS_WITH_CONTROLS_BY_CLIENT_ID =
        "EnvAsset.AssetsWithControlsListByClientId";
    public static
    String
        FIND_PROCESS_ASSET_BY_CLIENT_ID =
        "EnvAsset.processAssetListByClientId";
    public static
    String
        FIND_ASSET_NOT_BUILIDING_HEAT_BY_CLIENT_ID =
        "EnvAsset.assetsNotBuildingHeatByClientId";
    private
    String
        name;
    private
    String
        number;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;
    private
    String
        location;
    private
    Boolean
        storageTank;
    private
    Boolean
        point;
    private
    Set
        assetTypes;
    private
    Set<EnvAssetAttr>
        assetAttributes;
    private
    Integer
        parentAssetId;
    private
    Boolean
        buildingHeat;
    private
    Boolean
        process;
    private
    Integer
        belongsToMeter;
    private
    Boolean
        meter;
    private
    long
        version;
    private
    Set<EnvAssetSubstance>
        assetSubstances;
    private
    int
        usageCount;

    public int getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(int usageCount)
    {
        this.usageCount =
            usageCount;
    }

    public Set<EnvAssetSubstance> getAssetSubstances()
    {
        return assetSubstances;
    }

    public void setAssetSubstances(Set<EnvAssetSubstance> assetSubstances)
    {
        this.assetSubstances =
            assetSubstances;
    }

    public EnvAsset()
    {
    }

    public EnvAsset(Integer id)
    {
        setId(id);
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

    public String getNameAndNumber()
    {
        return name
               + " "
               + number;
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

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public String getActiveTsString()
    {
        if (activeTs
            != null)
        {
            return DateUtil.mdyDate(activeTs);
        }
        else
        {
            return "";
        }
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public String getInactiveTsString()
    {
        if (inactiveTs
            != null)
        {
            return DateUtil.mdyDate(inactiveTs);
        }
        else
        {
            return "";
        }
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public String getNumberAndName()
    {
        return this.number
               + " - "
               + this.name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public Boolean getStorageTank()
    {
        return storageTank;
    }

    public void setStorageTank(Boolean storageTank)
    {
        this.storageTank =
            storageTank;
    }

    public Boolean getPoint()
    {
        return point;
    }

    public void setPoint(Boolean point)
    {
        this.point =
            point;
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

    public Set<EnvAssetAttr> getAssetAttributes()
    {
        return assetAttributes;
    }

    public void setAssetAttributes(Set<EnvAssetAttr> assetAttributes)
    {
        this.assetAttributes =
            assetAttributes;
    }

    public Integer getParentAssetId()
    {
        return parentAssetId;
    }

    public void setParentAssetId(Integer parentAssetId)
    {
        this.parentAssetId =
            parentAssetId;
    }

    public void setBuildingHeat(Boolean buildingHeat)
    {
        this.buildingHeat =
            buildingHeat;
    }

    public Boolean getBuildingHeat()
    {
        return buildingHeat;
    }

    public void setProcess(Boolean process)
    {
        this.process =
            process;
    }

    public Boolean getProcess()
    {
        return process;
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

    public Integer getBelongsToMeter()
    {
        return belongsToMeter;
    }

    public void setBelongsToMeter(Integer belongsToMeter)
    {
        this.belongsToMeter =
            belongsToMeter;
    }

    public void setVersion(long version)
    {
        this.version =
            version;
    }

    public long getVersion()
    {
        return version;
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findByClientIdSortByNumber(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_SORT_BY_NUMBER,
                                              parameters);
    }

    public static List findMetersByClientIdSortByNumber(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_METERS_BY_CLIENT_ID_SORT_BY_NUMBER,
                                              parameters);
    }

    public static List findByNameAndNumber(Integer clientId, String name, String number)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "name",
                    name},
                {
                    "number",
                    number},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_NAME_AND_NUMBER,
                                              parameters);
    }

    public static List findAllByParentId(Integer parentId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "parentId",
                    parentId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_PARENT_ID,
                                              parameters);
    }

    public static List findAllByMeterId(Integer meterId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "belongsToMeter",
                    meterId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_METER_ID,
                                              parameters);
    }

    public static List findOtherAssetsByClient(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_OTHER_ASSETS_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findAssetsWithControlsByClient(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_ASSETS_WITH_CONTROLS_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findAssetsNotBuildingHeatByClient(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_ASSET_NOT_BUILIDING_HEAT_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findProcessAssetsByClient(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_PROCESS_ASSET_BY_CLIENT_ID,
                                              parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\nname=")
            .append(name);
        buffer.append("\nnumber=")
            .append(number);
        buffer.append("\n\n");
        return buffer.toString();
    }
}