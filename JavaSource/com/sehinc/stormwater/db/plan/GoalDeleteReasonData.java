package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.user.UserUpdatableData;

public class GoalDeleteReasonData
    extends UserUpdatableData
{
    private
    Integer
        goalId;
    private
    String
        deleteReason;

    public GoalDeleteReasonData()
    {
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
