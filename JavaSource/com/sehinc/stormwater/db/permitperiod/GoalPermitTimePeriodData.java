package com.sehinc.stormwater.db.permitperiod;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;

import java.util.List;

public class GoalPermitTimePeriodData
    extends UserUpdatableData
{
    private
    Integer
        goalId;
    private
    Integer
        permitTimePeriodId;
    private
    boolean
        complete;

    public GoalPermitTimePeriodData()
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

    public Integer getPermitTimePeriodId()
    {
        return permitTimePeriodId;
    }

    public void setPermitTimePeriodId(Integer permitTimePeriodId)
    {
        this.permitTimePeriodId =
            permitTimePeriodId;
    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete =
            complete;
    }

    public boolean hasPermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue)
    {
        return permitTimePeriodId
               != null
               && permitTimePeriodId.equals(permitTimePeriodValue.getId());
    }

    public static List findByPermitTimePeriodId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from GoalPermitTimePeriodData as data where data.permitTimePeriodId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByPermitTimePeriodIdAndGoalId(Integer periodId, Integer goalId)
    {
        Object
            parameters
            [
            ] =
            {
                periodId,
                goalId};
        String
            queryString =
            "select data from GoalPermitTimePeriodData as data where data.permitTimePeriodId =? and data.goalId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findGoalId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from GoalPermitTimePeriodData as data where data.goalId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
