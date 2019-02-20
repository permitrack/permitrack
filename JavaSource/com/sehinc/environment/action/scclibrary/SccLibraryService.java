package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.environment.db.scc.EnvSccInfo;
import com.sehinc.environment.value.SccLibraryValue;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.List;

public class SccLibraryService
{
    public static
    String
        SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC =
        "com.sehinc.environment.db.scc.EnvSccInfoLibrary.sccLibraryListSortByNumberAsc";
    public static
    String
        SCC_LIBRARY_LIST_SORT_BY_NUMBER_DESC =
        "com.sehinc.environment.db.scc.EnvSccInfoLibrary.sccLibraryListSortByNumberDesc";
    public static
    String
        SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_ASC =
        "com.sehinc.environment.db.scc.EnvSccInfoLibrary.sccLibraryListSortByDescriptionAsc";
    public static
    String
        SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_DESC =
        "com.sehinc.environment.db.scc.EnvSccInfoLibrary.sccLibraryListSortByDescriptionDesc";

    public SccLibraryService()
    {
    }

    public static List filterSccLibraryListByClient(String sortMethod, Integer clientId)
    {
        List<EnvSccInfo>
            clientSccs =
            EnvSccInfo.findByClientId(clientId);
        List<SccLibraryValue>
            librarySccs =
            getSccLibraryList(sortMethod);
        List<SccLibraryValue>
            resultSccList =
            new ArrayList<SccLibraryValue>();
        resultSccList.addAll(librarySccs);
        for (SccLibraryValue libraryScc : librarySccs)
        {
            for (EnvSccInfo clientScc : clientSccs)
            {
                String
                    clientDescAndName =
                    clientScc.getDescription()
                    + " "
                    + clientScc.getMajIndustrialGroup()
                    + " "
                    + clientScc.getRawMaterial()
                    + " "
                    + clientScc.getEmittingProcess();
                if (libraryScc.getNumber()
                        .equalsIgnoreCase(clientScc.getNumber())
                    && libraryScc.getDescriptionAndName()
                    .equalsIgnoreCase(clientDescAndName))
                {
                    resultSccList.remove(libraryScc);
                    break;
                }
            }
        }
        return resultSccList;
    }

    public static List getSccLibraryList(String query)
    {
        SQLQuery
            completedSql =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQuery(query));
        return convertList(completedSql.list());
    }

    private static String createQuery(String query)
    {
        StringBuilder
            sql =
            new StringBuilder();
        sql.append("select a.id, a.scc_number, a.scc_description, "
                   +
                   "a.maj_industrial_group, a.raw_material, a.emitting_process "
                   +
                   "from env_scc_info_library as a ");
        if (query.equals(SCC_LIBRARY_LIST_SORT_BY_NUMBER_ASC))
        {
            sql.append(" order by a.scc_number asc");
        }
        else if (query.equals(SCC_LIBRARY_LIST_SORT_BY_NUMBER_DESC))
        {
            sql.append(" order by a.scc_number desc");
        }
        else if (query.equals(SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_ASC))
        {
            sql.append(" order by a.scc_description asc");
        }
        else if (query.equals(SCC_LIBRARY_LIST_SORT_BY_DESCRIPTION_DESC))
        {
            sql.append(" order by a.scc_description desc");
        }
        else
        {
            sql.append(" order by a.scc_number asc");
        }
        return sql.toString();
    }

    private static List<SccLibraryValue> convertList(List list)
    {
        ArrayList<SccLibraryValue>
            returnList =
            new ArrayList<SccLibraryValue>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private static SccLibraryValue convert(Object[] o)
    {
        SccLibraryValue
            data =
            new SccLibraryValue();
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

