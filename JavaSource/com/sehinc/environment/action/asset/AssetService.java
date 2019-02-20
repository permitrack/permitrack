package com.sehinc.environment.action.asset;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.asset.EnvAssetAttr;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.db.lookup.EnvAssetTypeData;
import com.sehinc.environment.db.permit.EnvPermitDetail;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.AssetValue;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AssetService
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetService.class);
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_NUMBER_ASC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetNumberAsc";
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_NUMBER_DESC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetNumberDesc";
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_NAME_ASC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetNameAsc";
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_NAME_DESC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetNameDesc";
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_TYPE_ASC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetTypeAsc";
    public static
    String
        ASSET_LIST_SORT_BY_ASSET_TYPE_DESC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByAssetTypeDesc";
    public static
    String
        ASSET_LIST_SORT_BY_METER_ASC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByMeterAsc";
    public static
    String
        ASSET_LIST_SORT_BY_METER_DESC =
        "com.sehinc.environment.db.asset.EnvAsset.assetListSortByMeterDesc";

    public AssetService()
    {
    }

    public static AssetForm populateAssetForm(AssetForm assetForm, EnvAsset envAsset)
        throws Exception
    {
        List
            permitAssets =
            EnvPermitAsset.findByAssetId(envAsset.getId());
        ArrayList
            permitDetailNames =
            new ArrayList();
        for (Object o : permitAssets)
        {
            EnvPermitAsset
                holmes =
                (EnvPermitAsset) o;
            EnvPermitDetail
                watson =
                new EnvPermitDetail(holmes.getPermitDetailId());
            watson.load();
            if (watson
                != null)
            {
                permitDetailNames.add(watson);
            }
        }
        assetForm.setId(envAsset.getId());
        assetForm.setName(envAsset.getName());
        assetForm.setNumber(envAsset.getNumber());
        assetForm.setDescription(envAsset.getDescription());
        assetForm.setPermitDetailNames(permitDetailNames);
        assetForm.setSelectedTypeCodes(getSelectedTypeCodes(envAsset));
        assetForm.setClientId(envAsset.getClientId());
        assetForm.setActiveTs(envAsset.getActiveTs());
        assetForm.setInactiveTs(envAsset.getInactiveTs());
        assetForm.setLocation(envAsset.getLocation());
        if (envAsset.getBuildingHeat()
            != null
            && envAsset.getBuildingHeat())
        {
            assetForm.setAssetTypeSelection("buildingHeat");
        }
        else if (envAsset.getProcess()
                 != null
                 && envAsset.getProcess())
        {
            assetForm.setAssetTypeSelection("process");
        }
        assetForm.setBuildingHeat(envAsset.getBuildingHeat());
        assetForm.setProcess(envAsset.getProcess());
        if (envAsset.getPoint()
            != null
            && envAsset.getPoint())
        {
            assetForm.setAssetDesignation("point");
        }
        else if (envAsset.getMeter()
                 != null
                 && envAsset.getMeter())
        {
            assetForm.setAssetDesignation("meter");
        }
        else if (envAsset.getStorageTank()
                 != null
                 && envAsset.getStorageTank())
        {
            assetForm.setAssetDesignation("storageTank");
        }
        assetForm.setStorageTank(envAsset.getStorageTank());
        assetForm.setPoint(envAsset.getPoint());
        assetForm.setMeter(envAsset.getMeter());
        if (envAsset.getBelongsToMeter()
            != null)
        {
            EnvAsset
                meter =
                new EnvAsset(envAsset.getBelongsToMeter());
            meter.load();
            assetForm.setMeterId(meter.getId());
            assetForm.setMeterName(meter.getName());
            assetForm.setMeterNumber(meter.getNumber());
        }
        if (envAsset.getParentAssetId()
            != null)
        {
            EnvAsset
                parentAsset =
                new EnvAsset(envAsset.getParentAssetId());
            parentAsset.load();
            assetForm.setParentAssetId(parentAsset.getId());
            assetForm.setParentAssetName(parentAsset.getName());
            assetForm.setParentAssetNumber(parentAsset.getNumber());
        }
        EnvAssetAttr
            attr =
            getAttributes(envAsset.getAssetAttributes());
        if (attr
            != null)
        {
            assetForm.setEpCapacityMmbtu(attr.getEpCapacityMmbtuString());
            assetForm.setEpRatedMmbtu(attr.getEpRatedMmbtuString());
            assetForm.setTransferRate(attr.getTransferRateString());
            assetForm.setStackDiameter(attr.getStackDiameter());
            assetForm.setStackExitFlowRate(attr.getStackExitFlowRate());
            assetForm.setStackExitTemp(attr.getStackExitTemp());
            assetForm.setStackExitVelocity(attr.getStackExitVelocity());
            assetForm.setStackHeight(attr.getStackHeight());
            assetForm.setStackLatitude(attr.getStackLatitude());
            assetForm.setStackLongitude(attr.getStackLongitude());
            assetForm.setTankCapacity(attr.getTankCapacityString());
            assetForm.setTankCarrier(attr.getTankCarrier());
            assetForm.setTankContentsDesc(attr.getTankContentsDesc());
            assetForm.setTankDiameter(attr.getTankDiameter());
            assetForm.setTankHeight(attr.getTankHeight());
            assetForm.setTankLoadingMethod(attr.getTankLoadingMethod());
            assetForm.setTankTypeDesc(attr.getTankTypeDesc());
            assetForm.setTankVaporRecovery(attr.getTankVaporRecovery());
            assetForm.setStackDesc(attr.getStackDesc());
        }
        return assetForm;
    }

    private static EnvAssetAttr getAttributes(Set<EnvAssetAttr> attrSet)
    {
        EnvAssetAttr
            attrData =
            new EnvAssetAttr();
        for (Object attr : attrSet)
        {
            attrData =
                (EnvAssetAttr) attr;
        }
        return attrData;
    }

    private static String[] getSelectedTypeCodes(EnvAsset asset)
    {
        if (asset.getAssetTypes()
            == null)
        {
            return new String[] {};
        }
        else
        {
            ArrayList<String>
                selectedCodes =
                new ArrayList<String>();
            for (Object o : asset.getAssetTypes())
            {
                EnvAssetTypeData
                    type =
                    (EnvAssetTypeData) o;
                selectedCodes.add(type.getCode()
                                      .toString());
            }
            return selectedCodes.toArray(new String[selectedCodes.size()]);
        }
    }

    public static Integer saveFacilityAsset(Integer facilityId, Integer assetId, UserValue objUser)
    {
        Integer
            facilityAssetId =
            null;
        EnvFacility
            facility =
            new EnvFacility(facilityId);
        try
        {
            facility.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            LOG.error(ApplicationResources.getProperty("facility.error.load.failed",
                                                       parameters));
        }
        EnvFacilityAsset
            fa =
            new EnvFacilityAsset();
        fa.setAssetId(assetId);
        fa.setFacilityId(facilityId);
        fa.setDescription(facility.getDescription());
        fa.setName(facility.getName());
        fa.setNumber(facility.getNumber());
        try
        {
            facilityAssetId =
                fa.save(objUser);
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("facility.asset.create.error"));
        }
        return facilityAssetId;
    }

    public static Integer deleteFacilityAsset(Integer facilityId, Integer assetId)
    {
        Integer
            result =
            null;
        try
        {
            EnvFacilityAsset
                fa =
                EnvFacilityAsset.findByFacilityAndAssetId(facilityId,
                                                          assetId);
            result =
                fa.delete();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {facilityId};
            LOG.error(ApplicationResources.getProperty(("facility.asset.delete.page.failed"),
                                                       parameters));
        }
        return result;
    }

    public static Integer saveAssetInformation(AssetForm form, UserValue objUser, ClientValue objClient)
        throws Exception
    {
        return saveAssetInformation(form,
                                    null,
                                    objUser,
                                    objClient);
    }

    public static Integer saveAssetInformation(AssetForm form, EnvAsset assetData, UserValue objUser, ClientValue objClient)
        throws Exception
    {
        if (assetData
            == null)
        {
            assetData =
                new EnvAsset();
        }
        assetData.setClientId(objClient.getId());
        assetData.setName(form.getName());
        assetData.setNumber(form.getNumber());
        assetData.setDescription(form.getDescription());
        assetData.setActiveTs(DateUtil.parseDate(form.getActiveTsString()));
        assetData.setInactiveTs(DateUtil.parseDate(form.getInactiveTsString()));
        assetData.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
        assetData.setAssetTypes(getSelectedAssetTypes(form));
        assetData.setLocation(form.getLocation());
        if (form.getAssetTypeSelection()
            != null
            && form.getAssetTypeSelection()
            .equalsIgnoreCase("buildingHeat"))
        {
            assetData.setBuildingHeat(true);
            assetData.setProcess(false);
        }
        else
        {
            assetData.setBuildingHeat(false);
            // TODO: relationship between building heat and process
            //assetData.setProcess(null);
        }
        // Save meter information
        // If no meter asset was selected, save null in the database
        if (form.getMeterId()
            == 0)
        {
            form.setMeterId(null);
        }
        assetData.setBelongsToMeter(form.getMeterId());
        if (form.getAssetDesignation()
            != null
            && form.getAssetDesignation()
            .equalsIgnoreCase("point"))
        {
            assetData.setPoint(true);
            assetData.setMeter(false);
            assetData.setStorageTank(false);
        }
        else if (form.getAssetDesignation()
                 != null
                 && form.getAssetDesignation()
            .equalsIgnoreCase("meter"))
        {
            assetData.setPoint(false);
            assetData.setMeter(true);
            assetData.setStorageTank(false);
        }
        else if (form.getAssetDesignation()
                 != null
                 && form.getAssetDesignation()
            .equalsIgnoreCase("storageTank"))
        {
            assetData.setPoint(false);
            assetData.setMeter(false);
            assetData.setStorageTank(true);
        }
        else
        {
            assetData.setPoint(null);
            assetData.setMeter(null);
            assetData.setStorageTank(null);
        }
        // If no parent asset was selected, save null in the database
        if (form.getParentAssetId()
            == 0)
        {
            form.setParentAssetId(null);
        }
        assetData.setParentAssetId(form.getParentAssetId());
        Integer
            intAssetId =
            assetData.save(objUser);
        if (intAssetId
            > 0)
        {
            EnvAssetAttr
                attributes =
                EnvAssetAttr.findByAssetId(intAssetId);
            if (attributes
                == null)
            {
                attributes =
                    new EnvAssetAttr();
                attributes.setAssetId(intAssetId);
            }
            attributes.setEpCapacityMmbtuString(form.getEpCapacityMmbtu());
            attributes.setEpRatedMmbtuString(form.getEpRatedMmbtu());
            attributes.setTransferRateString(form.getTransferRate());
            attributes.setStackDiameter(form.getStackDiameter());
            attributes.setStackExitFlowRate(form.getStackExitFlowRate());
            attributes.setStackExitTemp(form.getStackExitTemp());
            attributes.setStackExitVelocity(form.getStackExitVelocity());
            attributes.setStackHeight(form.getStackHeight());
            attributes.setStackLatitude(form.getStackLatitude());
            attributes.setStackLongitude(form.getStackLongitude());
            attributes.setTankCapacityString(form.getTankCapacity());
            attributes.setTankCarrier(form.getTankCarrier());
            attributes.setTankContentsDesc(form.getTankContentsDesc());
            attributes.setTankDiameter(form.getTankDiameter());
            attributes.setTankHeight(form.getTankHeight());
            attributes.setTankLoadingMethod(form.getTankLoadingMethod());
            attributes.setTankTypeDesc(form.getTankTypeDesc());
            attributes.setTankVaporRecovery(form.getTankVaporRecovery());
            attributes.setStackDesc(form.getStackDesc());
            attributes.save();
        }
        return intAssetId;
    }

    private static Set getSelectedAssetTypes(AssetForm objA)
    {
        String[]
            selectedTypesFromPage =
            objA.getSelectedTypeCodes();
        HashSet<EnvAssetTypeData>
            assetSelectedTypes =
            new HashSet<EnvAssetTypeData>();
        if (selectedTypesFromPage
            != null)
        {
            for (String aType : selectedTypesFromPage)
            {
                EnvAssetTypeData
                    assetType =
                    EnvAssetTypeData.findByTypeCode(new Integer(aType));
                assetSelectedTypes.add(assetType);
            }
        }
        return assetSelectedTypes;
    }

    public static List getAssetList(String query, Integer clientId)
    {
        SQLQuery
            completedSql =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQuery(clientId,
                                            query));
        return convertList(completedSql.list());
    }

    private static String createQuery(Integer clientId, String query)
    {
        String
            statusCode =
            EnvStatusCodeData.STATUS_CODE_ACTIVE;
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("SELECT A.ID, A.NUMBER, A.NAME, A.BELONGS_TO_METER, MIN(L.CODE) AS [DESCRIPTION] "
                   +
                   "FROM ENV_ASSET AS A "
                   +
                   "LEFT JOIN ENV_ASSET_TYPE T "
                   +
                   " ON T.ASSET_ID = A.ID "
                   +
                   "LEFT JOIN LOOKUP_ENV_ASSET_TYPE L "
                   +
                   " ON L.CODE = T.ASSET_TYPE_CD "
                   +
                   "WHERE A.CLIENT_ID = "
                   + clientId.toString()
                   +
                   " AND STATUS_CD = "
                   + statusCode
                   +
                   " GROUP BY A.ID, A.NUMBER, A.NAME, A.BELONGS_TO_METER");
        if (query.equals(ASSET_LIST_SORT_BY_ASSET_NUMBER_ASC))
        {
            sql.append(" order by a.number asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NUMBER_DESC))
        {
            sql.append(" order by a.number desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NAME_ASC))
        {
            sql.append(" order by a.name asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NAME_DESC))
        {
            sql.append(" order by a.name desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_TYPE_ASC))
        {
            sql.append(" order by l.description asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_TYPE_DESC))
        {
            sql.append(" order by l.description desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_METER_ASC))
        {
            sql.append(" order by a.belongs_to_meter asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_METER_DESC))
        {
            sql.append(" order by a.belongs_to_meter desc");
        }
        else
        {
            sql.append(" order by a.number asc");
        }
        return sql.toString();
    }

    public static List getAssetListByFacilityId(String query, Integer facilityId)
    {
        SQLQuery
            completedSql =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQueryByFacilityId(facilityId,
                                                        query));
        return convertList(completedSql.list());
    }

    private static String createQueryByFacilityId(Integer facilityId, String query)
    {
        String
            statusCode =
            EnvStatusCodeData.STATUS_CODE_ACTIVE;
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("SELECT A.ID, A.NUMBER, A.NAME, A.BELONGS_TO_METER, MIN(L.CODE) AS [DESCRIPTION] "
                   +
                   "FROM ENV_ASSET AS A "
                   +
                   "LEFT JOIN ENV_ASSET_TYPE T "
                   +
                   " ON T.ASSET_ID = A.ID "
                   +
                   "LEFT JOIN LOOKUP_ENV_ASSET_TYPE L "
                   +
                   " ON L.CODE = T.ASSET_TYPE_CD "
                   +
                   "WHERE A.ID in (SELECT ASSET_ID FROM ENV_FACILITY_ASSET WHERE FACILITY_ID = "
                   + facilityId
                   + ") "
                   +
                   " AND STATUS_CD = "
                   + statusCode
                   +
                   " GROUP BY A.ID, A.NUMBER, A.NAME, A.BELONGS_TO_METER");
        if (query.equals(ASSET_LIST_SORT_BY_ASSET_NUMBER_ASC))
        {
            sql.append(" order by a.number asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NUMBER_DESC))
        {
            sql.append(" order by a.number desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NAME_ASC))
        {
            sql.append(" order by a.name asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_NAME_DESC))
        {
            sql.append(" order by a.name desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_TYPE_ASC))
        {
            sql.append(" order by l.description asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_ASSET_TYPE_DESC))
        {
            sql.append(" order by l.description desc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_METER_ASC))
        {
            sql.append(" order by a.belongs_to_meter asc");
        }
        else if (query.equals(ASSET_LIST_SORT_BY_METER_DESC))
        {
            sql.append(" order by a.belongs_to_meter desc");
        }
        else
        {
            sql.append(" order by a.number asc");
        }
        return sql.toString();
    }

    private static List<AssetValue> convertList(List list)
    {
        ArrayList<AssetValue>
            returnList =
            new ArrayList<AssetValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static AssetValue convert(Object[] o)
    {
        AssetValue
            data =
            new AssetValue();
        Integer
            id =
            new Integer(o[0].toString());
        EnvAsset
            asset =
            new EnvAsset(id);
        asset.load();
        data.setId(id);
        data.setNumber(o[1].toString());
        data.setName(o[2].toString());
        data.setSelectedTypeCodes(getSelectedTypeCodes(asset));
        if (asset.getAssetTypes()
            != null)
        {
            data.setAssetTypes(asset.getAssetTypes());
        }
        String
            meterInfo =
            StringUtil.nullSafeToString(o[3]);
        if (!StringUtil.isEmpty(meterInfo))
        {
            data.setBelongsToMeter(new Integer(meterInfo));
            EnvAsset
                meterAsset =
                new EnvAsset(data.getBelongsToMeter());
            meterAsset.load();
            StringBuffer
                s =
                new StringBuffer();
            s.append(meterAsset.getNumber());
            s.append(" ");
            s.append(meterAsset.getName());
            data.setMeterNumberName(s.toString());
        }
        return data;
    }
}