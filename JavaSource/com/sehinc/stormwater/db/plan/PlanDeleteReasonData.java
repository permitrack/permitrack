package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.user.UserUpdatableData;

public class PlanDeleteReasonData
    extends UserUpdatableData
{
    private
    Integer
        planId;
    private
    String
        deleteReason;

    public PlanDeleteReasonData()
    {
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
