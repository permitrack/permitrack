package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureData;
import com.sehinc.environment.value.SourceUsageValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SourceUsageService
{
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_SOURCE_ASC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortBySourceAsc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_SOURCE_DESC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortBySourceDesc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_METER_READING_ASC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByMeterReadingAsc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_METER_READING_DESC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByMeterReadingDesc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_START_DATE_ASC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByStartDateAsc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_START_DATE_DESC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByStartDateDesc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_END_DATE_ASC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByEndDateAsc";
    public static
    String
        SOURCE_USAGE_LIST_SORT_BY_END_DATE_DESC =
        "com.sehinc.environment.db.sourceusage.EnvSourceUsage.sourceUsageListSortByEndDateDesc";

    public SourceUsageService()
    {
    }

    public static List getSourceUsageList(String query, Integer assetId, Date startDate, Date endDate)
    {
        SQLQuery
            completedSql =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQuery(assetId,
                                            query,
                                            startDate,
                                            endDate));
        return convertList(completedSql.list());
    }

    private static String createQuery(Integer assetId, String query, Date startDate, Date endDate)
    {
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("select u.id, s.display_name, u.meter_reading, "
                   +
                   "u.unit_of_measure_cd, u.period_start_ts, u.period_end_ts, "
                   +
                   "a.id, s.id, s.display_color_cd, u.transfer_rate "
                   +
                   "from env_asset_source as a "
                   +
                   "inner join env_source_usage as u on u.asset_source_id = a.id "
                   +
                   "inner join env_source as s on s.id = a.source_id "
                   +
                   "where u.period_start_ts >= '"
                   + DateUtil.ymdDate(startDate)
                   + "' "
                   +
                   "and u.period_end_ts <= '"
                   + DateUtil.ymdDate(endDate)
                   + "' "
                   +
                   "and a.asset_id = ")
            .append(assetId.toString());
        if (query.equals(SOURCE_USAGE_LIST_SORT_BY_SOURCE_ASC))
        {
            sql.append(" order by s.display_name asc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_SOURCE_DESC))
        {
            sql.append(" order by s.display_name desc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_METER_READING_ASC))
        {
            sql.append(" order by u.meter_reading asc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_METER_READING_DESC))
        {
            sql.append(" order by u.meter_reading desc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_START_DATE_ASC))
        {
            sql.append(" order by u.period_start_ts asc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_START_DATE_DESC))
        {
            sql.append(" order by u.period_start_ts desc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_END_DATE_ASC))
        {
            sql.append(" order by u.period_end_ts asc");
        }
        else if (query.equals(SOURCE_USAGE_LIST_SORT_BY_END_DATE_DESC))
        {
            sql.append(" order by u.period_end_ts desc");
        }
        else
        {
            sql.append(" order by s.display_name asc");
        }
        return sql.toString();
    }

    private static List<SourceUsageValue> convertList(List list)
    {
        ArrayList<SourceUsageValue>
            returnList =
            new ArrayList<SourceUsageValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static SourceUsageValue convert(Object[] o)
    {
        SourceUsageValue
            data =
            new SourceUsageValue();
        data.setSourceUsageId(new Integer(o[0].toString()));
        data.setSourceName(StringUtil.nullSafeToString(o[1]));
        data.setMeterReading(StringUtil.nullSafeToString(o[2]));
        data.setUnitOfMeasureCd(new Integer(o[3].toString()));
        Date
            startDate =
            (Date) o[4];
        Date
            endDate =
            (Date) o[5];
        String
            sdStr =
            DateUtil.mdyDate(startDate);
        String
            edStr =
            DateUtil.mdyDate(endDate);
        data.setPeriodStartTs(DateUtil.parseDate(sdStr));
        data.setPeriodEndTs(DateUtil.parseDate(edStr));
        data.setAssetSourceId(new Integer(o[6].toString()));
        data.setSourceId(new Integer(o[7].toString()));
        data.setDisplayColorCd(StringUtil.
            nullSafeToString(o[8]));
        if (data.getUnitOfMeasureCd()
            != null)
        {
            data.setMeasureType(EnvUnitOfMeasureData.findByTypeCode(data.getUnitOfMeasureCd()));
            data.setUnitDisplayName(data.getMeasureType()
                                        .getDescription());
        }
        data.setTransferRate(StringUtil.
            nullSafeToString(o[9]));
        return data;
    }
}

