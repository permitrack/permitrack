package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class GoalActivityFileLocationData
    extends StatusData
{
    private
    Integer
        goalActivityId;
    private
    String
        name;
    private
    String
        location;

    public GoalActivityFileLocationData()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public static List findActiveByGoalActivityId(Integer id)
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
            "select data from GoalActivityFileLocationData as data where data.goalActivityId =? and data.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByGoalActivityId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from GoalActivityFileLocationData as data where data.goalActivityId =? ";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static GoalActivityFileLocationData findUniqueByGoalActivityIdAndName(Integer id, String name)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                name};
        String
            queryString =
            "select data from GoalActivityFileLocationData as data where data.goalActivityId =? and data.name = ?";
        return (GoalActivityFileLocationData) HibernateUtil.findUnique(queryString,
                                                                       parameters);
    }
}
