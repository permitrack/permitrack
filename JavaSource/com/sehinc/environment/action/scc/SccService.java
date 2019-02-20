package com.sehinc.environment.action.scc;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.value.SccValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class SccService
{
    public static
    String
        SCC_LIST_SORT_BY_NUMBER_ASC =
        "com.sehinc.environment.db.scc.EnvSccInfo.sccListSortByNumberAsc";
    public static
    String
        SCC_LIST_SORT_BY_NUMBER_DESC =
        "com.sehinc.environment.db.scc.EnvSccInfo.sccListSortByNumberDesc";
    public static
    String
        SCC_LIST_SORT_BY_DESCRIPTION_ASC =
        "com.sehinc.environment.db.scc.EnvSccInfo.sccListSortByDescriptionAsc";
    public static
    String
        SCC_LIST_SORT_BY_DESCRIPTION_DESC =
        "com.sehinc.environment.db.scc.EnvSccInfo.sccListSortByDescriptionDesc";

    public SccService()
    {
    }

    public static List<SccValue> getSccList(String query, Integer clientId)
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
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("select a.id, a.scc_number, a.scc_description, "
                   +
                   "a.maj_industrial_group, a.raw_material, a.emitting_process "
                   +
                   "from env_scc_info as a "
                   +
                   "where a.client_id = ")
            .append(clientId.toString());
        if (query.equals(SCC_LIST_SORT_BY_NUMBER_ASC))
        {
            sql.append(" order by a.scc_number asc");
        }
        else if (query.equals(SCC_LIST_SORT_BY_NUMBER_DESC))
        {
            sql.append(" order by a.scc_number desc");
        }
        else if (query.equals(SCC_LIST_SORT_BY_DESCRIPTION_ASC))
        {
            sql.append(" order by a.scc_description asc");
        }
        else if (query.equals(SCC_LIST_SORT_BY_DESCRIPTION_DESC))
        {
            sql.append(" order by a.scc_description desc");
        }
        else
        {
            sql.append(" order by a.scc_number asc");
        }
        return sql.toString();
    }

    private static List<SccValue> convertList(List list)
    {
        ArrayList<SccValue>
            returnList =
            new ArrayList<SccValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static SccValue convert(Object[] o)
    {
        SccValue
            data =
            new SccValue();
        data.setId(new Integer(o[0].toString()));
        data.setNumber(o[1].toString());
        String
            descAndName =
            StringUtil.nullSafeToString(o[2])
            + " "
            + StringUtil.nullSafeToString(o[3])
            + " "
            + StringUtil.nullSafeToString(o[4])
            + " "
            + StringUtil.nullSafeToString(o[5]);
        data.setDescriptionAndName(descAndName);
        return data;
    }
}

