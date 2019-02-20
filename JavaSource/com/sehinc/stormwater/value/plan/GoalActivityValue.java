package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.GoalActivityData;

import java.util.Date;

public class GoalActivityValue
    implements java.io.Serializable
{

    private
    Integer
        id;
    private
    String
        name;
    private
    Date
        activityDate;
    private
    String
        statusCode;
    private
    Integer
        goalId;
    private
    String
        reportUrl;

    public GoalActivityValue()
    {
    }

    public GoalActivityValue(GoalActivityData goalActivityData)
    {
        this.id =
            goalActivityData.getId();
        this.name =
            goalActivityData.getName();
        this.activityDate =
            goalActivityData.getActivityDate();
        this.statusCode =
            goalActivityData.getStatus()
                .getCode();
        this.goalId =
            goalActivityData.getGoalId();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setActivityDate(Date activityDate)
    {
        this.activityDate =
            activityDate;
    }

    public Date getActivityDate()
    {
        return activityDate;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public Integer getGoalId()
    {
        return goalId;
    }

    public void setGoalId(Integer goalId)
    {
        this.goalId =
            goalId;
    }

/*
    public String getReportUrl()
    {
        return this.reportUrl;
    }

    public void setReportUrl(String reportUrl)
    {
        this.reportUrl =
            reportUrl;
    }
*/
}
