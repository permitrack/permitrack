package com.sehinc.environment.action.report;

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
    String
        reportURL =
        null;
    private
    String
        runReport =
        null;
    private
    Integer
        year =
        null;
    private
    Integer
        semiAnnualYear =
        null;
    private
    String
        period =
        null;
    private
    Integer
        permitId =
        null;
    private
    Integer
        annualYear =
        null;
    private
    Integer
        annualPermitId =
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

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year =
            year;
    }

    public Integer getSemiAnnualYear()
    {
        return semiAnnualYear;
    }

    public void setSemiAnnualYear(Integer semiAnnualYear)
    {
        this.semiAnnualYear =
            semiAnnualYear;
    }

    public String getPeriod()
    {
        return period;
    }

    public void setPeriod(String period)
    {
        this.period =
            period;
    }

    public Integer getPermitId()
    {
        return permitId;
    }

    public void setPermitId(Integer permitId)
    {
        this.permitId =
            permitId;
    }

    public Integer getAnnualYear()
    {
        return annualYear;
    }

    public void setAnnualYear(Integer annualYear)
    {
        this.annualYear =
            annualYear;
    }

    public Integer getAnnualPermitId()
    {
        return annualPermitId;
    }

    public void setAnnualPermitId(Integer annualPermitId)
    {
        this.annualPermitId =
            annualPermitId;
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
        year =
            null;
        semiAnnualYear =
            null;
        period =
            null;
        permitId =
            null;
        annualYear =
            null;
        annualPermitId =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}