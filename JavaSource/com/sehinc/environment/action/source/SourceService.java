package com.sehinc.environment.action.source;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvSourceTypeData;
import com.sehinc.environment.value.SourceValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class SourceService
{
    public static
    String
        SOURCE_LIST_SORT_BY_DISPLAY_LABEL_ASC =
        "com.sehinc.environment.db.source.EnvSource.sourceListSortByDisplayLabelAsc";
    public static
    String
        SOURCE_LIST_SORT_BY_DISPLAY_LABEL_DESC =
        "com.sehinc.environment.db.source.EnvSource.sourceListSortByDisplayLabelDesc";
    public static
    String
        SOURCE_LIST_SORT_BY_TYPE_ASC =
        "com.sehinc.environment.db.source.EnvSource.sourceListSortByTypeAsc";
    public static
    String
        SOURCE_LIST_SORT_BY_TYPE_DESC =
        "com.sehinc.environment.db.source.EnvSource.sourceListSortByTypeDesc";

    public SourceService()
    {
    }

    public static List getSourceList(String query, Integer clientId)
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
        sql.append("select a.id, a.display_color_cd, a.display_name, "
                   +
                   "a.source_type_cd "
                   +
                   "from env_source as a "
                   +
                   "left join lookup_env_source_type l "
                   +
                   "on a.source_type_cd = l.code "
                   +
                   "where a.status_cd = "
                   + statusCode
                   +
                   " and a.client_id = ")
            .append(clientId.toString());
        if (query.equals(SOURCE_LIST_SORT_BY_DISPLAY_LABEL_ASC))
        {
            sql.append(" order by a.display_name asc");
        }
        else if (query.equals(SOURCE_LIST_SORT_BY_DISPLAY_LABEL_DESC))
        {
            sql.append(" order by a.display_name desc");
        }
        else if (query.equals(SOURCE_LIST_SORT_BY_TYPE_ASC))
        {
            sql.append(" order by l.description asc");
        }
        else if (query.equals(SOURCE_LIST_SORT_BY_TYPE_DESC))
        {
            sql.append(" order by l.description desc");
        }
        else
        {
            sql.append(" order by a.display_name asc");
        }
        return sql.toString();
    }

    private static List<SourceValue> convertList(List list)
    {
        ArrayList<SourceValue>
            returnList =
            new ArrayList<SourceValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static SourceValue convert(Object[] o)
    {
        SourceValue
            data =
            new SourceValue();
        data.setId(new Integer(o[0].toString()));
        data.setDisplayColorCd(StringUtil.nullSafeToString(o[1]));
        data.setDisplayName(StringUtil.nullSafeToString(o[2]));
        data.setSourceTypeCd(new Integer(o[3].toString()));
        if (data.getSourceTypeCd()
            != null)
        {
            String
                cd =
                data.getSourceTypeCd()
                    .toString();
            data.setSourceTypeDesc(EnvSourceTypeData.getSourceTypeName(cd));
        }
        return data;
    }
}

