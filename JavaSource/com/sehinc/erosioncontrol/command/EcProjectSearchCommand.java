package com.sehinc.erosioncontrol.command;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;
import com.sehinc.erosioncontrol.server.project.ProjectService;
import org.hibernate.SQLQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EcProjectSearchCommand
    implements Command<EcProjectSearchCommandContext>
{
    EcProjectSearchCommandContext
        context;

    public EcProjectSearchCommandContext execute(EcProjectSearchCommandContext context)
    {
        this.context =
            context;
        SQLQuery
            query =
            HibernateUtil.getNewSession()
                .createSQLQuery(createQuery(context));
        List
            list =
            query.list();
        context.setResults(convertList(list.subList(0,
                                                    list.size()
                                                    - 1)));
        context.setTotalPages(Integer.parseInt(((Object[]) list.get(list.size()
                                                                    - 1))[0].toString()));
        return context;
    }

    private String createQuery(EcProjectSearchCommandContext context)
    {
        StringBuilder
            builder =
            new StringBuilder();
        builder.append("exec sp_project_search ");
        builder.append(formatStrToSql(context.getProjectName(),
                                      true));
        builder.append(formatStrToSql(context.getAddress(),
                                      true));
        builder.append(formatStrToSql(context.getCity(),
                                      true));
        builder.append(formatStrToSql(context.getState(),
                                      true));
        builder.append(formatStrToSql(context.getZip(),
                                      true));
        builder.append(formatStrToSql(convertListToCommaSeperatedString(context.getProjectStatuses()),
                                      true));
        builder.append(formatStrToSql(convertListToCommaSeperatedString(context.getProjectTypes()),
                                      true));
        builder.append(formatStrToSql(convertListToCommaSeperatedString(context.getZones()),
                                      true));
        builder.append(formatStrToSql(context.getClient()
                                          .getId(),
                                      true));
        builder.append(formatStrToSql(convertListToCommaSeperatedString(context.getOrderColumns()),
                                      true));
        builder.append(formatStrToSql(context.getPermitNumber(),
                                      true));
        builder.append(formatStrToSql(context.getAreaSizeMin(),
                                      true));
        builder.append(formatStrToSql(context.getAreaSizeMax(),
                                      true));
        builder.append(formatStrToSql(context.getImpAreaSizeMin(),
                                      true));
        builder.append(formatStrToSql(context.getImpAreaSizeMax(),
                                      true));
        builder.append(formatStrToSql(context.getTotalAreaSizeMin(),
                                      true));
        builder.append(formatStrToSql(context.getTotalAreaSizeMax(),
                                      true));
        builder.append(formatStrToSql(context.getStartDateA(),
                                      true));
        builder.append(formatStrToSql(context.getStartDateB(),
                                      true));
        builder.append(formatStrToSql(context.getEffDateA(),
                                      true));
        builder.append(formatStrToSql(context.getEffDateB(),
                                      true));
        builder.append(formatStrToSql(context.getSeedDateA(),
                                      true));
        builder.append(formatStrToSql(context.getSeedDateB(),
                                      true));
        builder.append(formatStrToSql(context.getPermitAuthName(),
                                      true));
        builder.append(formatStrToSql(context.getPermiteeName(),
                                      true));
        builder.append(formatStrToSql(context.getInspectorName(),
                                      true));
        builder.append(formatStrToSql(context.getCurrentPage(),
                                      true));
        builder.append(formatStrToSql(context.getProjectsPerPage(),
                                      false));
        return builder.toString();
    }

    public String formatStrToSql(Object string, boolean appendComma)
    {
        StringBuilder
            returnString =
            new StringBuilder();
        if (string
            == null
            || string.toString()
                   .trim()
                   .length()
               < 1)
        {
            returnString.append("null");
        }
        else
        {
            string =
                StringUtil.replace(string.toString(),
                                   "'",
                                   "");
            returnString.append("'")
                .append(string)
                .append("'");
        }
        if (appendComma)
        {
            returnString.append(",");
        }
        return returnString.toString();
    }

    private String convertListToCommaSeperatedString(List items)
    {
        StringBuilder
            builder =
            new StringBuilder();
        if (items
            != null)
        {
            for (Object o : items)
            {
                builder.append(o.toString());
                builder.append(", ");
            }
            int
                i =
                builder.lastIndexOf(", ");
            return builder.substring(0,
                                     i);
        }
        return null;
    }

    private List<EcProjectSearchData> convertList(List list)
    {
        ArrayList<EcProjectSearchData>
            returnList =
            new ArrayList<EcProjectSearchData>(list.size());
        for (Object obj : list)
        {
            returnList.add(convert((Object[]) obj));
        }
        return returnList;
    }

    private EcProjectSearchData convert(Object[] o)
    {
        EcProjectSearchData
            data =
            new EcProjectSearchData();
        data.setProjectId(o[0].toString());
        data.setName(StringUtil.nullSafeToString(o[1]));
        data.setPermitNumber(StringUtil.nullSafeToString(o[2]));
        data.setPermitAuthorityClientName(StringUtil.nullSafeToString(o[3]));
        data.setPermittedClientName(StringUtil.nullSafeToString(o[4]));
        data.setLastInspectionDate(StringUtil.nullSafeToString(o[5]));
        data.setDisturbedArea(StringUtil.nullSafeToString(o[6]));
        data.setDisturbedAreaUnits(StringUtil.nullSafeToString(o[7]));
        data.setProjectStatusDescription(StringUtil.nullSafeToString(o[8]));
        data.setZone(StringUtil.nullSafeToString(o[9]));
        data.setProjectType(StringUtil.nullSafeToString(o[10]));
        data.setAddress(StringUtil.nullSafeToString(o[11]));
        data.setCity(StringUtil.nullSafeToString(o[12]));
        data.setState(StringUtil.nullSafeToString(o[13]));
        data.setZip(StringUtil.nullSafeToString(o[14]));
        data.setStartDate(DateUtil.mdyDate((Date) (o[15])));
        data.setEffectiveDate(DateUtil.mdyDate((Date) (o[16])));
        data.setSeedDate(DateUtil.mdyDate((Date) (o[17])));
        data.setTotalSiteArea(StringUtil.nullSafeToString(o[18]));
        data.setTotalSiteAreaUnits(StringUtil.nullSafeToString(o[19]));
        data.setNewImperviousArea(StringUtil.nullSafeToString(o[20]));
        data.setNewImperviousAreaUnits(StringUtil.nullSafeToString(o[21]));
        data.setAuthorizedInspectorClientName(StringUtil.nullSafeToString(o[22]));
        data.setOwnerName(StringUtil.nullSafeToString(o[23]));
        data.setProjectSecurityPermissionValue(ProjectService.getProjectSecurityPermissionValue(context.getSecurityManager(),
                                                                                                data));
        data.setInspectionSecurityPermissionValue(ProjectService.getInspectionSecurityPermissionValue(context.getSecurityManager(),
                                                                                                      data));
        data.setReportUrl(ProjectService.generateReportUrl(context.getClient(),
                                                           context.getUser(),
                                                           data));
        data.setInspectionFormUrl(ProjectService.generateInspectionFormUrl(context.getClient(),
                                                                           context.getUser(),
                                                                           data));
        data.setLastInspectionBmpStatus(StringUtil.nullSafeToString(o[24]));
        data.setRfaNumber(StringUtil.nullSafeToString(o[25]));
        data.setBlockNumber(StringUtil.nullSafeToString(o[26]));
        data.setLotNumber(StringUtil.nullSafeToString(o[27]));
        data.setInspectionFrequency(StringUtil.nullSafeToString(o[28]));
        data.setInspectionCount(StringUtil.nullSafeToString(o[29]));
        return data;
    }
}
