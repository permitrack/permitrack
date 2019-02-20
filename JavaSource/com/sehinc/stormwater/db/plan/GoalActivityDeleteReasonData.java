package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.user.UserUpdatableData;

public class GoalActivityDeleteReasonData
    extends UserUpdatableData
{
    private
    Integer
        goalActivityId;
    private
    String
        deleteReason;

    public GoalActivityDeleteReasonData()
    {
    }

    public Integer getGoalActivityId()
    {
        return goalActivityId;
    }

    public void setGoalActivityId(Integer goalActivityId)
    {
        this.goalActivityId =
            goalActivityId;
    }

    public String getDeleteReason()
    {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason)
    {
        this.deleteReason =
            deleteReason;
    }
}
