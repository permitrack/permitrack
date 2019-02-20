package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuLeaf;
import com.sehinc.stormwater.value.plan.GoalActivityValue;

import java.util.Date;

public class GoalActivityLeaf
    extends TreeViewMenuLeaf
    implements Comparable, PlanMenuLeaf
{
    private
    Integer
        goalActivityId =
        null;
    private
    Date
        goalActivityDate =
        null;
    private
    String
        parentId =
        null;

    public GoalActivityLeaf(GoalActivityValue goalActivity)
    {
        this.goalActivityId =
            goalActivity.getId();
        this.goalActivityDate =
            goalActivity.getActivityDate();
        setName(goalActivity.getName());
        setType(BranchConstants.GOAL_ACTIVITY_TYPE);
        setTypeId(this.goalActivityId);
        setParentId(goalActivity.getGoalId()
                        .toString());
        setUrl(BranchConstants.SUBNODE_URL
               + "?"
               + RequestKeys.NODE_ID
               + "="
               + getID());
    }

    public String getParentId()
    {
        return BranchConstants.generateTreeId(getParentType(),
                                              parentId);
    }

    public void setParentId(String parentId)
    {
        this.parentId =
            parentId;
    }

    public Integer getGoalActivityId()
    {
        return goalActivityId;
    }

    public Date getGoalActivityDate()
    {
        return goalActivityDate;
    }

    public void setGoalActivityDate(Date goalActivityDate)
    {
        this.goalActivityDate =
            goalActivityDate;
    }

    public int compareTo(Object obj)
    {
        int
            numberCompare;
        GoalActivityLeaf
            branch;
        if (obj instanceof GoalActivityLeaf)
        {
            branch =
                (GoalActivityLeaf) obj;
            if (getGoalActivityDate()
                == null
                || branch.getGoalActivityDate()
                   == null)
            {
                numberCompare =
                    0;
            }
            else
            {
                numberCompare =
                    getGoalActivityDate().compareTo(branch.getGoalActivityDate());
            }
        }
        else
        {
            return 0;
        }
        if (numberCompare
            == 0)
        {
            return this.getID()
                .compareTo(branch.getID());
        }
        return numberCompare;
    }

    public String getParentType()
    {
        return BranchConstants.GOAL_TYPE;
    }
}
