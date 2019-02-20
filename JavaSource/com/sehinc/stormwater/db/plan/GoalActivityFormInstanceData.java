package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.forms.FormInstanceData;
import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.stormwater.server.plan.GoalService;

public class GoalActivityFormInstanceData
    extends HibernateData
{
    private
    FormInstanceData
        formInstance;
    private
    Integer
        goalActivityId;

    public FormInstanceData getFormInstance()
    {
        return formInstance;
    }

    public Integer getGoalActivityId()
    {
        return goalActivityId;
    }

    public void setFormInstance(FormInstanceData data)
    {
        formInstance =
            data;
    }

    public void setGoalActivityId(Integer integer)
    {
        goalActivityId =
            integer;
    }

    public static GoalActivityFormInstanceData getGoalActivityFormInstanceByGoalActivityId(Integer goalActivityId)
    {
        Object[][]
            parameters =
            {
                {
                    "goalActivityId",
                    goalActivityId}};
        return (GoalActivityFormInstanceData) HibernateUtil.findUniqueByNamedQuery(GoalService.FIND_GOALS_ACTIVITY_FORM_INSTANCE_BY_GOAL_ACTIVITY_ID,
                                                                                   parameters);
    }
}
