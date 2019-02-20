package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class EnvUnitOfMeasureMap
    extends HibernateData
{
    public final static
    String
        SOURCE_TABLE =
        "ENV_SOURCE";
    public final static
    String
        SOURCE_SUBSTANCE_TABLE =
        "ENV_SOURCE_SUBSTANCE";
    public final static
    String
        SOURCE_USAGE_TABLE =
        "ENV_SOURCE_USAGE";
    public final static
    String
        PERMIT_DETAIL_TABLE =
        "ENV_PERMIT_DETAIL";
    public final static
    String
        SOURCE_LBS_VOC_COL =
        "LBS_VOC_UM";
    public final static
    String
        SOURCE_DENSITY_COL =
        "DENSITY_UM";
    public final static
    String
        SOURCE_LBS_HAPS_COL =
        "LBS_HAPS_UM";
    public final static
    String
        SOURCE_SUB_RATIO_COL =
        "UNIT_OF_MEASURE_CD";
    public final static
    String
        SOURCE_SUB_NG_COL =
        "NG_EF_UNIT";
    public final static
    String
        SOURCE_SUB_BL_COL =
        "BL_EF_UNIT";
    public final static
    String
        SOURCE_USG_READING_COL =
        "UNIT_OF_MEASURE_CD";
    public final static
    String
        PERM_DTL_AVG_PERIOD_COL =
        "AVG_PERIOD_UM";
    public final static
    String
        PERM_DTL_VOC_LIMIT_COL =
        "VOC_LIMIT_UM";
    public final static
    String
        PERM_DTL_HAPS_LIMIT_COL =
        "HAPS_LIMIT_UM";
    public final static
    String
        PERM_DTL_MMBTU_LIMIT_COL =
        "MMBTU_LIMIT_UM";
    private final static
    String
        FIND_BY_CLIENT_ID_TABLE_COLUMN =
        "EnvUnitOfMeasureMap.findByClientIdTableColumn";
    private
    Integer
        clientId;
    private
    String
        tableName;
    private
    String
        columnName;
    private
    EnvUnitOfMeasureData
        unitOfMeasureCd;

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName =
            tableName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName =
            columnName;
    }

    public EnvUnitOfMeasureData getUnitOfMeasureCd()
    {
        return unitOfMeasureCd;
    }

    public void setUnitOfMeasureCd(EnvUnitOfMeasureData unitOfMeasureCd)
    {
        this.unitOfMeasureCd =
            unitOfMeasureCd;
    }

    public void setUnitOfMeasureType(Integer unitOfMeasureId)
    {
        List<LookupData>
            list =
            this.findAll(EnvUnitOfMeasureData.class);
        for (LookupData code : list)
        {
            if (unitOfMeasureId.equals(code.getCode()))
            {
                setUnitOfMeasureCd((EnvUnitOfMeasureData) code);
                break;
            }
        }
    }

    public static List findByClientIdTableColumn(Integer clientId, String tableName, String columnName)
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
                    "tableName",
                    tableName},
                {
                    "columnName",
                    columnName}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_TABLE_COLUMN,
                                              parameters);
    }

    public static List getUnitOfMeasureList(Integer clientId, String tableName, String columnName)
    {
        List
            mappings =
            findByClientIdTableColumn(clientId,
                                      tableName,
                                      columnName);
        List
            umList =
            new ArrayList();
        for (Object o : mappings)
        {
            EnvUnitOfMeasureMap
                map =
                (EnvUnitOfMeasureMap) o;
            EnvUnitOfMeasureData
                um =
                EnvUnitOfMeasureData.findByTypeCode(map.getUnitOfMeasureCd()
                                                        .getCode());
            umList.add(um);
        }
        return umList;
    }
}