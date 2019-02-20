package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Date;
import java.util.List;

public class GoalActivityData
    extends StatusData
{
    private
    Integer
        goalId;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        goalActivityFormId;
    private
    Integer
        submitterUserId;
    private
    Integer
        ownerId;
    private
    Date
        activityDate;

    public GoalActivityData()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public void setGoalActivityFormId(Integer goalActivityFormId)
    {
        this.goalActivityFormId =
            goalActivityFormId;
    }

    public Integer getSubmitterUserId()
    {
        return submitterUserId;
    }

    public void setSubmitterUserId(Integer submitterUserId)
    {
        this.submitterUserId =
            submitterUserId;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
    }

    public Date getActivityDate()
    {
        return activityDate;
    }

    public void setActivityDate(Date activityDate)
    {
        this.activityDate =
            activityDate;
    }

    public static List findActiveByGoalId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from GoalActivityData as data where data.goalId =? and data.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static GoalActivityData findActiveByFormInstanceId(Integer formInstanceId)
    {
        Object
            parameters
            [
            ] =
            {
                formInstanceId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select goalActivity from GoalActivityData as goalActivity, GoalActivityFormInstanceData as goalActivityFormInstance where goalActivity.id = goalActivityFormInstance.goalActivityId and goalActivityFormInstance.formInstance.id = ? and goalActivity.status.code = ?";
        return (GoalActivityData) HibernateUtil.findUnique(queryString,
                                                           parameters);
    }
}
