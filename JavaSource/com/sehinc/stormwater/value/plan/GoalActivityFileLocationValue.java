package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;

import java.io.File;

@SuppressWarnings("UnusedDeclaration")
public class GoalActivityFileLocationValue
    extends com.sehinc.common.util.html.FileDownloadWrapper
    implements java.io.Serializable
{

    private
    Integer
        id;
    private
    Integer
        goalActivityId;
    private
    String
        statusCode;

    public GoalActivityFileLocationValue(GoalActivityFileLocationData goalActivityFileLocationData)
    {
        this.id =
            goalActivityFileLocationData.getId();
        this.goalActivityId =
            goalActivityFileLocationData.getGoalActivityId();
        this.file =
            new File(goalActivityFileLocationData.getLocation(),
                     goalActivityFileLocationData.getName());
        this.statusCode =
            goalActivityFileLocationData.getStatus()
                .getCode();
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

    public void setGoalActivityId(Integer goalActivityId)
    {
        this.goalActivityId =
            goalActivityId;
    }

    public Integer getGoalActivityId()
    {
        return goalActivityId;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public String getStatusCode()
    {
        return statusCode;
    }
}
