package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.value.ControlUsageValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlUsageService
{
    static List<ControlUsageValue> convertList2(List list)
    {
        ArrayList<ControlUsageValue>
            returnList =
            new ArrayList<ControlUsageValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert2((Object[]) obj));
        }
        return returnList;
    }

    private static ControlUsageValue convert2(Object[] o)
    {
        ControlUsageValue
            data =
            new ControlUsageValue();
        data.setSourceUsageId(new Integer(o[0].toString()));
        data.setDescription(StringUtil.nullSafeToString(o[1]));
        Date
            startDate =
            (Date) o[2];
        Date
            endDate =
            (Date) o[3];
        String
            sdStr =
            DateUtil.mdyDate(startDate);
        String
            edStr =
            DateUtil.mdyDate(endDate);
        data.setPeriodStartTs(DateUtil.parseDate(sdStr));
        data.setPeriodEndTs(DateUtil.parseDate(edStr));
        data.setAssetControlId(new Integer(o[4].toString()));
        data.setEfficiencyFactor(new Integer(o[5].toString()));
        data.setAdditionalInfo(StringUtil.nullSafeToString(o[6]));
        data.setSourceName(data.getDescription()
                           + " - "
                           + data.getEfficiencyFactor()
                           + "%"
                           + " - "
                           + data.getAdditionalInfo());
        return data;
    }

    static String createQueryControl(Integer assetId, String query, Date startDate, Date endDate)
    {
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("select u.id, s.description, "
                   +
                   "u.period_start_ts, u.period_end_ts, "
                   +
                   "a.id, a.efficiency_factor, a.ADDL_INFO "
                   +
                   "from ENV_ASSET_EMITTED_SUBSTANCE_TYPE as a "
                   +
                   "inner join env_control_usage as u on u.ASSET_EMITTED_SUBSTANCE_TYPE_ID = a.id "
                   +
                   "inner join lookup_env_substance_type as s on s.code = a.substance_type_cd "
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
        if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_ASC))
        {
            sql.append(" order by s.description asc");
        }
        else if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_SOURCE_DESC))
        {
            sql.append(" order by s.description desc");
        }
        else if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_START_DATE_ASC))
        {
            sql.append(" order by u.period_start_ts asc");
        }
        else if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_START_DATE_DESC))
        {
            sql.append(" order by u.period_start_ts desc");
        }
        else if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_END_DATE_ASC))
        {
            sql.append(" order by u.period_end_ts asc");
        }
        else if (query.equals(SourceUsageService.SOURCE_USAGE_LIST_SORT_BY_END_DATE_DESC))
        {
            sql.append(" order by u.period_end_ts desc");
        }
        else
        {
            sql.append(" order by s.display_name asc");
        }
        return sql.toString();
    }

    public static List getControlUsageList(String query, Integer assetId, Date startDate, Date endDate)
    {
        SQLQuery
            completedSql =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQueryControl(assetId,
                                                   query,
                                                   startDate,
                                                   endDate));
        return convertList2(completedSql.list());
    }
}
