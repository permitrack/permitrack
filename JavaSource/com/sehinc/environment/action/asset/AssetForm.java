package com.sehinc.environment.action.asset;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class AssetForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
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
        assetTypeCode;
    private
    String
        assetTypeDesc;
    private
    Integer
        permitDetailId;
    private
    ArrayList
        permitDetailNames;
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
    String
        statusCode;
    private
    String[]
        selectedTypeCodes;
    private
    Set
        allAssetTypeCodes;
    private
    Boolean
        buildingHeat;
    private
    Boolean
        process;
    private
    String
        assetTypeSelection;
    private
    String
        assetDesignation;
    private
    Integer
        parentAssetId;
    private
    String
        parentAssetName;
    private
    String
        parentAssetNumber;
    private
    Integer
        meterId;
    private
    String
        meterName;
    private
    String
        meterNumber;
    private
    Boolean
        meter;
    private
    String
        tankCarrier;
    private
    String
        tankLoadingMethod;
    private
    Boolean
        tankVaporRecovery;
    private
    String
        tankTypeDesc;
    private
    String
        tankContentsDesc;
    private
    String
        tankDiameter;
    private
    String
        tankHeight;
    private
    String
        tankCapacity;
    private
    String
        stackLatitude;
    private
    String
        stackLongitude;
    private
    String
        stackHeight;
    private
    String
        stackDiameter;
    private
    String
        stackExitTemp;
    private
    String
        stackExitVelocity;
    private
    String
        stackExitFlowRate;
    private
    String
        stackDesc;
    private
    String
        epRatedMmbtu;
    private
    String
        epCapacityMmbtu;
    private
    String
        transferRate;
    private
    Integer
        assetsPerPage =
        null;
    private
    Boolean
        assetsPerPageChanged =
        false;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getAssetTypeCode()
    {
        return assetTypeCode;
    }

    public void setAssetTypeCode(Integer assetTypeCode)
    {
        this.assetTypeCode =
            assetTypeCode;
    }

    public String getAssetTypeDesc()
    {
        return assetTypeDesc;
    }

    public void setAssetTypeDesc(String assetTypeDesc)
    {
        this.assetTypeDesc =
            assetTypeDesc;
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

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public String getActiveTsString()
    {
        return DateUtil.mdyDate(activeTs);
    }

    public void setActiveTsString(String activeTs)
    {
        this.activeTs =
            DateUtil.parseDate(activeTs);
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public String getInactiveTsString()
    {
        return DateUtil.mdyDate(inactiveTs);
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public void setInactiveTsString(String inactiveTs)
    {
        this.inactiveTs =
            DateUtil.parseDate(inactiveTs);
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

    public Set getAllAssetTypeCodes()
    {
        return allAssetTypeCodes;
    }

    public void setAllAssetTypeCodes(Set allAssetTypeCodes)
    {
        this.allAssetTypeCodes =
            allAssetTypeCodes;
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

    public void setAssetTypeSelection(String assetTypeSelection)
    {
        this.assetTypeSelection =
            assetTypeSelection;
    }

    public String getAssetTypeSelection()
    {
        return assetTypeSelection;
    }

    public String getAssetDesignation()
    {
        return assetDesignation;
    }

    public void setAssetDesignation(String assetDesignation)
    {
        this.assetDesignation =
            assetDesignation;
    }

    public ArrayList getPermitDetailNames()
    {
        return permitDetailNames;
    }

    public void setPermitDetailNames(ArrayList permitDetailNames)
    {
        this.permitDetailNames =
            permitDetailNames;
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

    public String getEpRatedMmbtu()
    {
        return epRatedMmbtu;
    }

    public void setEpRatedMmbtu(String epRatedMmbtu)
    {
        this.epRatedMmbtu =
            epRatedMmbtu;
    }

    public String getEpCapacityMmbtu()
    {
        return epCapacityMmbtu;
    }

    public void setEpCapacityMmbtu(String epCapacityMmbtu)
    {
        this.epCapacityMmbtu =
            epCapacityMmbtu;
    }

    public String getTransferRate()
    {
        return transferRate;
    }

    public void setTransferRate(String transferRate)
    {
        this.transferRate =
            transferRate;
    }

    public String getTankCarrier()
    {
        return tankCarrier;
    }

    public void setTankCarrier(String tankCarrier)
    {
        this.tankCarrier =
            tankCarrier;
    }

    public String getTankLoadingMethod()
    {
        return tankLoadingMethod;
    }

    public void setTankLoadingMethod(String tankLoadingMethod)
    {
        this.tankLoadingMethod =
            tankLoadingMethod;
    }

    public Boolean getTankVaporRecovery()
    {
        return tankVaporRecovery;
    }

    public void setTankVaporRecovery(Boolean tankVaporRecovery)
    {
        this.tankVaporRecovery =
            tankVaporRecovery;
    }

    public String getTankTypeDesc()
    {
        return tankTypeDesc;
    }

    public void setTankTypeDesc(String tankTypeDesc)
    {
        this.tankTypeDesc =
            tankTypeDesc;
    }

    public String getTankContentsDesc()
    {
        return tankContentsDesc;
    }

    public void setTankContentsDesc(String tankContentsDesc)
    {
        this.tankContentsDesc =
            tankContentsDesc;
    }

    public String getTankDiameter()
    {
        return tankDiameter;
    }

    public void setTankDiameter(String tankDiameter)
    {
        this.tankDiameter =
            tankDiameter;
    }

    public String getTankHeight()
    {
        return tankHeight;
    }

    public void setTankHeight(String tankHeight)
    {
        this.tankHeight =
            tankHeight;
    }

    public String getTankCapacity()
    {
        return tankCapacity;
    }

    public void setTankCapacity(String tankCapacity)
    {
        this.tankCapacity =
            tankCapacity;
    }

    public String getStackLatitude()
    {
        return stackLatitude;
    }

    public void setStackLatitude(String stackLatitude)
    {
        this.stackLatitude =
            stackLatitude;
    }

    public String getStackLongitude()
    {
        return stackLongitude;
    }

    public void setStackLongitude(String stackLongitude)
    {
        this.stackLongitude =
            stackLongitude;
    }

    public String getStackHeight()
    {
        return stackHeight;
    }

    public void setStackHeight(String stackHeight)
    {
        this.stackHeight =
            stackHeight;
    }

    public String getStackDiameter()
    {
        return stackDiameter;
    }

    public void setStackDiameter(String stackDiameter)
    {
        this.stackDiameter =
            stackDiameter;
    }

    public String getStackExitTemp()
    {
        return stackExitTemp;
    }

    public void setStackExitTemp(String stackExitTemp)
    {
        this.stackExitTemp =
            stackExitTemp;
    }

    public String getStackExitVelocity()
    {
        return stackExitVelocity;
    }

    public void setStackExitVelocity(String stackExitVelocity)
    {
        this.stackExitVelocity =
            stackExitVelocity;
    }

    public String getStackExitFlowRate()
    {
        return stackExitFlowRate;
    }

    public void setStackExitFlowRate(String stackExitFlowRate)
    {
        this.stackExitFlowRate =
            stackExitFlowRate;
    }

    public void setStackDesc(String stackDesc)
    {
        this.stackDesc =
            stackDesc;
    }

    public String getStackDesc()
    {
        return stackDesc;
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

    public String getParentAssetName()
    {
        return parentAssetName;
    }

    public void setParentAssetName(String parentAssetName)
    {
        this.parentAssetName =
            parentAssetName;
    }

    public String getParentAssetNumber()
    {
        return parentAssetNumber;
    }

    public void setParentAssetNumber(String parentAssetNumber)
    {
        this.parentAssetNumber =
            parentAssetNumber;
    }

    public Integer getMeterId()
    {
        return meterId;
    }

    public void setMeterId(Integer meterId)
    {
        this.meterId =
            meterId;
    }

    public String getMeterName()
    {
        return meterName;
    }

    public void setMeterName(String meterName)
    {
        this.meterName =
            meterName;
    }

    public String getMeterNumber()
    {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber)
    {
        this.meterNumber =
            meterNumber;
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

    public Integer getAssetsPerPage()
    {
        return assetsPerPage;
    }

    public void setAssetsPerPage(Integer assetsPerPage)
    {
        this.assetsPerPage =
            assetsPerPage;
    }

    public Boolean getAssetsPerPageChanged()
    {
        return assetsPerPageChanged;
    }

    public void setAssetsPerPageChanged(Boolean assetsPerPageChanged)
    {
        this.assetsPerPageChanged =
            assetsPerPageChanged;
    }

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        name =
            null;
        number =
            null;
        description =
            null;
        statusCode =
            null;
        assetTypeCode =
            null;
        assetTypeDesc =
            null;
        activeTs =
            null;
        inactiveTs =
            null;
        allAssetTypeCodes =
            null;
        selectedTypeCodes =
            null;
        permitDetailId =
            null;
        location =
            null;
        storageTank =
            null;
        point =
            null;
        parentAssetId =
            null;
        parentAssetName =
            null;
        parentAssetNumber =
            null;
        tankCarrier =
            null;
        tankLoadingMethod =
            null;
        tankVaporRecovery =
            null;
        tankTypeDesc =
            null;
        tankContentsDesc =
            null;
        tankDiameter =
            null;
        tankHeight =
            null;
        tankCapacity =
            null;
        stackLatitude =
            null;
        stackLongitude =
            null;
        stackHeight =
            null;
        stackDiameter =
            null;
        stackExitTemp =
            null;
        stackExitVelocity =
            null;
        stackExitFlowRate =
            null;
        stackDesc =
            null;
        epRatedMmbtu =
            null;
        epCapacityMmbtu =
            null;
        buildingHeat =
            null;
        process =
            null;
        assetTypeSelection =
            null;
        assetDesignation =
            null;
        meterId =
            null;
        meterName =
            null;
        meterNumber =
            null;
        meter =
            null;
        assetsPerPage =
            null;
        assetsPerPageChanged =
            false;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}