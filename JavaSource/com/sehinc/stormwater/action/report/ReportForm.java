package com.sehinc.stormwater.action.report;

import com.sehinc.common.action.base.BaseActionForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.Date;
import java.util.List;

public class ReportForm
    extends BaseActionForm
{
    private
    String
        planName =
        null;
    private
    String
        title =
        null;
    private
    Integer
        planId =
        null;
    private
    Integer
        mcmId =
        null;
    private
    Integer
        bmpId =
        null;
    private
    Integer
        userId =
        null;
    private
    String
        reportType =
        null;
    private
    String
        userType =
        null;
    private
    String
        dateType =
        null;
    private
    String
        goalStatus =
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
    List
        mcmList =
        null;
    private
    List
        bmpList =
        null;
    private
    List
        userList =
        null;
    private
    String
        reportURL =
        null;
    private
    boolean
        displayMPCAReportOption =
        false;

    public String getPlanName()
    {
        return planName;
    }

    public void setPlanName(String planName)
    {
        this.planName =
            planName;
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

    public Integer getPlanId()
    {
        return planId;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
    }

    public Integer getMCMId()
    {
        return mcmId;
    }

    public void setMCMId(Integer mcmId)
    {
        this.mcmId =
            mcmId;
    }

    public Integer getBMPId()
    {
        return bmpId;
    }

    public void setBMPId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
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

    public String getReportType()
    {
        return reportType;
    }

    public void setReportType(String reportType)
    {
        this.reportType =
            reportType;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType =
            userType;
    }

    public String getDateType()
    {
        return dateType;
    }

    public void setDateType(String dateType)
    {
        this.dateType =
            dateType;
    }

    public String getGoalStatus()
    {
        return goalStatus;
    }

    public void setGoalStatus(String goalStatus)
    {
        this.goalStatus =
            goalStatus;
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

    public List getMcmList()
    {
        return mcmList;
    }

    public void setMcmList(List mcmList)
    {
        this.mcmList =
            mcmList;
    }

    public List getBmpList()
    {
        return bmpList;
    }

    public void setBmpList(List bmpList)
    {
        this.bmpList =
            bmpList;
    }

    public List getUserList()
    {
        return userList;
    }

    public void setUserList(List userList)
    {
        this.userList =
            userList;
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

    public boolean getDisplayMPCAReportOption()
    {
        return displayMPCAReportOption;
    }

    public void setDisplayMPCAReportOption(boolean displayMPCAReportOption)
    {
        this.displayMPCAReportOption =
            displayMPCAReportOption;
    }

    public void reset()
    {
        planName =
            null;
        title =
            null;
        planId =
            null;
        mcmId =
            null;
        bmpId =
            null;
        userId =
            null;
        reportType =
            "all.mcm";
        userType =
            "all.users";
        dateType =
            "all.dates";
        goalStatus =
            "all.status";
        startDate =
            null;
        endDate =
            null;
        mcmList =
            null;
        bmpList =
            null;
        userList =
            null;
        reportURL =
            null;
        displayMPCAReportOption =
            false;
    }

    public void validateForm(ActionErrors errors)
    {
        if (getPlanId()
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("error.plan.not.selected"));
        }
    }
}
