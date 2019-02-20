package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.action.base.BaseActionForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class ReportForm
    extends BaseActionForm
{
    private
    String
        reportFormat =
        null;
    private
    String
        title =
        null;
    private
    Integer
        userId =
        null;
    private
    Integer
        clientId =
        null;
    private
    Date
        startDate =
        null;
    private
    Date
        endDate =
        null;
    private
    Date
        inspStartDate =
        null;
    private
    Date
        inspEndDate =
        null;
    private
    String
        reportURL =
        null;
    private
    String
        runReport =
        null;

    public String getReportFormat()
    {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat)
    {
        this.reportFormat =
            reportFormat;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title =
            title;
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

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId =
            userId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public String getStartDateString()
    {
        return DateUtil.mdyDate(startDate);
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getEndDateString()
    {
        return DateUtil.mdyDate(endDate);
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public void setStartDateString(String startDate)
    {
        this.startDate =
            DateUtil.parseDate(startDate);
    }

    public void setEndDate(Date endDate)
    {
        this.endDate =
            endDate;
    }

    public void setEndDateString(String endDate)
    {
        this.endDate =
            DateUtil.parseDate(endDate);
    }

    public Date getInspStartDate()
    {
        return inspStartDate;
    }

    public String getInspStartDateString()
    {
        return DateUtil.mdyDate(inspStartDate);
    }

    public Date getInspEndDate()
    {
        return inspEndDate;
    }

    public String getInspEndDateString()
    {
        return DateUtil.mdyDate(inspEndDate);
    }

    public void setInspStartDate(Date inspStartDate)
    {
        this.inspStartDate =
            inspStartDate;
    }

    public void setInspStartDateString(String inspStartDate)
    {
        this.inspStartDate =
            DateUtil.parseDate(inspStartDate);
    }

    public void setInspEndDate(Date inspEndDate)
    {
        this.inspEndDate =
            inspEndDate;
    }

    public void setInspEndDateString(String inspEndDate)
    {
        this.inspEndDate =
            DateUtil.parseDate(inspEndDate);
    }

    public String getReportURL()
    {
        return reportURL;
    }

    public void setReportURL(String reportURL)
    {
        this.reportURL =
            reportURL;
    }

    public String getRunReport()
    {
        return runReport;
    }

    public void setRunReport(String runReport)
    {
        this.runReport =
            runReport;
    }

    public void reset()
    {
        reportFormat =
            null;
        title =
            null;
        userId =
            null;
        clientId =
            null;
        startDate =
            null;
        endDate =
            null;
        reportURL =
            null;
        runReport =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
