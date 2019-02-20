package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class GoalBranch
    extends PlanMenuBranch
    implements Comparable
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalBranch.class);
    private
    List
        children =
        null;
    private
    Integer
        goalId =
        null;
    private
    Integer
        goalNumber =
        null;

    public GoalBranch(GoalValue goal)
    {
        this.goalId =
            goal.getId();
        this.goalNumber =
            goal.getNumber();
        setName(goal.getNumber()
                + ". "
                + goal.getName()
                + (this.getChildren()
                   != null
                       ? "&nbsp;("
                         + this.getChildren()
            .size()
                         + ")"
                       : ""));
        setType(BranchConstants.GOAL_TYPE);
        setTypeId(goalId);
        setUrl(BranchConstants.SUBNODE_URL
               + "?"
               + RequestKeys.NODE_ID
               + "="
               + getID());
        setParentId(goal.getBmpId()
                        .toString());
    }

    public Integer getGoalId()
    {
        return goalId;
    }

    public List getChildren()
    {
        if (children
            == null)
        {
            /*
                    long
                        start =
                        System.currentTimeMillis();
            */
            children =
                new ArrayList();
            /*
                    List
                        branches =
                        null;
            */
            try
            {
                //branches =
                getBranchList();
            }
            catch (Exception ex)
            {
                LOG.debug("Exception occured",
                          ex);
            }
            /*
                    long
                        end =
                        System.currentTimeMillis();
            */
            /*
                    LOG.debug(" getChildren() took  "
                              + (end
                                 - start)
                                / 1000.0
                              + " seconds");
            */
        }
        return children;
    }

    public Integer getNumber()
    {
        return goalNumber;
    }

    public void setNumber(Integer number)
    {
        goalNumber =
            number;
    }

    public List getBranchList()
    {
        TreeSet
            branches =
            new TreeSet();
        List
            goalActivityList =
            PlanService.getGoalActivityValues(new GoalValue(goalId,
                                                            getName(),
                                                            goalNumber));
        Iterator
            i =
            goalActivityList.iterator();
        while (i.hasNext())
        {
            GoalActivityValue
                value =
                (GoalActivityValue) i.next();
            branches.add(new GoalActivityLeaf(value));
        }
        children =
            new ArrayList(branches);
        return children;
    }

    public int compareTo(Object obj)
    {
        int
            numberCompare =
            0;
        GoalBranch
            branch;
        if (obj
            != null
            && obj instanceof GoalBranch)
        {
            branch =
                (GoalBranch) obj;
            numberCompare =
                (getNumber()
                 != null)
                    ? getNumber().compareTo(branch.getNumber())
                    : numberCompare;
            if (numberCompare
                == 0)
            {
                numberCompare =
                    (getName()
                     != null)
                        ? getName().compareTo(branch.getName())
                        : numberCompare;
            }
        }
        return numberCompare;
    }

    public String getParentType()
    {
        return BranchConstants.BMP_TYPE;
    }
}
