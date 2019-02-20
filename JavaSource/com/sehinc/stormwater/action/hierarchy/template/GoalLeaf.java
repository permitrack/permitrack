package com.sehinc.stormwater.action.hierarchy.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuLeaf;
import com.sehinc.stormwater.value.plan.GoalValue;

public class GoalLeaf
    extends TreeViewMenuLeaf
{
    private
    Integer
        goalId =
        null;

    public GoalLeaf(GoalValue goal)
    {
        this.goalId =
            goal.getId();
        setName(goal.getName());
        setType("goal_temp");
        setTypeId(goalId);
        setUrl("/ms4/template/goaltemplateviewaction.do?"
               + RequestKeys.GOAL_ID
               + "="
               + goalId);
    }

    public Integer getGoalId()
    {
        return goalId;
    }
}
