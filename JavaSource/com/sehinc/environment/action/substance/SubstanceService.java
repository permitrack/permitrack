package com.sehinc.environment.action.substance;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.value.SubstanceValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class SubstanceService
{
    public static
    String
        SUBSTANCE_LIST_SORT_BY_PART_NUMBER_ASC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortByPartNumberAsc";
    public static
    String
        SUBSTANCE_LIST_SORT_BY_PART_NUMBER_DESC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortByPartNumberDesc";
    public static
    String
        SUBSTANCE_LIST_SORT_BY_SUBSTANCE_NAME_ASC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortBySubstanceNameAsc";
    public static
    String
        SUBSTANCE_LIST_SORT_BY_SUBSTANCE_NAME_DESC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortBySubstanceNameDesc";
    public static
    String
        SUBSTANCE_LIST_SORT_BY_SUBSTANCE_TYPE_ASC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortBySubstanceTypeAsc";
    public static
    String
        SUBSTANCE_LIST_SORT_BY_SUBSTANCE_TYPE_DESC =
        "com.sehinc.environment.db.substance.EnvSubstance.substanceListSortBySubstanceTypeDesc";

    public SubstanceService()
    {
    }

    public static List getSubstanceList(String query, Integer clientId)
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
        sql.append("select a.id, a.name, a.part_num, "
                   +
                   "a.substance_type_cd "
                   +
                   "from env_substance as a "
                   +
                   "left join lookup_env_substance_type l "
                   +
                   "on a.substance_type_cd = l.code "
                   +
                   "where a.status_cd = "
                   + statusCode
                   +
                   " and a.client_id = ")
            .append(clientId.toString());
        if (query.equals(SUBSTANCE_LIST_SORT_BY_PART_NUMBER_ASC))
        {
            sql.append(" order by a.part_num asc");
        }
        else if (query.equals(SUBSTANCE_LIST_SORT_BY_PART_NUMBER_DESC))
        {
            sql.append(" order by a.part_num desc");
        }
        else if (query.equals(SUBSTANCE_LIST_SORT_BY_SUBSTANCE_NAME_ASC))
        {
            sql.append(" order by a.name asc");
        }
        else if (query.equals(SUBSTANCE_LIST_SORT_BY_SUBSTANCE_NAME_DESC))
        {
            sql.append(" order by a.name desc");
        }
        else if (query.equals(SUBSTANCE_LIST_SORT_BY_SUBSTANCE_TYPE_ASC))
        {
            sql.append(" order by l.description asc");
        }
        else if (query.equals(SUBSTANCE_LIST_SORT_BY_SUBSTANCE_TYPE_DESC))
        {
            sql.append(" order by l.description desc");
        }
        else
        {
            sql.append(" order by a.name asc");
        }
        return sql.toString();
    }

    private static List<SubstanceValue> convertList(List list)
    {
        ArrayList<SubstanceValue>
            returnList =
            new ArrayList<SubstanceValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static SubstanceValue convert(Object[] o)
    {
        SubstanceValue
            data =
            new SubstanceValue();
        data.setId(new Integer(o[0].toString()));
        data.setName(StringUtil.nullSafeToString(o[1]));
        data.setPartNum(StringUtil.nullSafeToString(o[2]));
        data.setTypeCd(new Integer(o[3].toString()));
        if (data.getTypeCd()
            != null)
        {
            data.setTypeData(EnvSubstanceTypeData.findByTypeCode(data.getTypeCd()));
            data.setTypeDesc(data.getTypeData()
                                 .getDescription());
        }
        return data;
    }
}

