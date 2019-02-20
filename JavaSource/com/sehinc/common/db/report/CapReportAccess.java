package com.sehinc.common.db.report;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapReportAccess
    extends HibernateData
{
    private
    Integer
        id;
    private
    Integer
        reportId;
    private
    Integer
        clientId;
    private
    Boolean
        enabled;
    private static final
    String
        FIND_BY_REPORT_AND_CLIENT =
        "com.sehinc.common.db.report.CapReportAccess.findByReportAndClient";
    private static final
    String
        FIND_BY_CLIENT_ID =
        "com.sehinc.common.db.report.CapReportAccess.findByClientId";

    public CapReportAccess()
    {
    }

    public CapReportAccess(Integer id)
    {
        setId(id);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getReportId()
    {
        return reportId;
    }

    public void setReportId(Integer reportId)
    {
        this.reportId =
            reportId;
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

    public void setEnabled(Boolean enabled)
    {
        this.enabled =
            enabled;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public static CapReportAccess findByReportAndClient(Integer reportId, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "reportId",
                    reportId},
                {
                    "clientId",
                    clientId}};
        return (CapReportAccess) HibernateUtil.findUniqueByNamedQuery(FIND_BY_REPORT_AND_CLIENT,
                                                                      parameters);
    }

    public static List findByClientId(Integer clientId)
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
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nReportId=")
            .append(reportId);
        buffer.append("\nClientId=")
            .append(clientId);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
