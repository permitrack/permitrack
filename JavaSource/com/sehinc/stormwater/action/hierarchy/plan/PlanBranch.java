package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PlanBranch
    extends PlanMenuBranch
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanBranch.class);
    private
    List
        children =
        null;
    private
    Integer
        planId =
        null;

    public PlanBranch(PlanValue plan)
    {
        this.planId =
            plan.getId();
        setName(plan.getName()
                + (this.getChildren()
                   != null
                       ? "&nbsp;("
                         + this.getChildren()
            .size()
                         + ")"
                       : ""));
        setType(BranchConstants.PLAN_TYPE);
        setTypeId(planId);
        setUrl(getUrlString()
               + RequestKeys.NODE_ID
               + "="
               + getID());
    }

    public String getUrlString()
    {
        return BranchConstants.SUBNODE_URL
               + "?";
    }

    public Integer getPlanId()
    {
        return planId;
    }

    public List getChildren()
    {
        if (children
            == null)
        {
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
        }
        return children;
    }

    private List getBranchList()
    {
        TreeSet
            branches =
            new TreeSet();
        List
            mcmList =
            PlanService.getMCMValuesList(new PlanValue(planId,
                                                       getName()));
        Iterator
            i =
            mcmList.iterator();
        while (i.hasNext())
        {
            MCMValue
                value =
                (MCMValue) i.next();
            branches.add(createMCMBranch(value));
        }
        children =
            new ArrayList(branches);
        return children;
    }

    public MCMBranch createMCMBranch(MCMValue value)
    {
        return new MCMBranch(value);
    }

    public String getParentType()
    {
        return null;
    }
}
