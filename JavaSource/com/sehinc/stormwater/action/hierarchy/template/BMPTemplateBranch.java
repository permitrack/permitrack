package com.sehinc.stormwater.action.hierarchy.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.plan.BMPBranch;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BMPTemplateBranch
    extends BMPBranch
{
    public BMPTemplateBranch(BMPValue bmp)
    {
        super(bmp);
        setType("bmp_temp");
        setUrl("/ms4/template/bmptemplateviewaction.do?"
               + RequestKeys.BMP_ID
               + "="
               + getBMPId());
    }

    public List getBranchList()
    {
        ArrayList
            branches =
            new ArrayList();
        List
            goalList =
            PlanService.getGoalValues(new BMPValue(getBMPId(),
                                                   getName(),
                                                   getNumber(),
                                                   null));
        Iterator
            i =
            goalList.iterator();
        while (i.hasNext())
        {
            GoalValue
                value =
                (GoalValue) i.next();
            boolean
                valueFound =
                false;
            Iterator
                j =
                children.iterator();
            while (j.hasNext())
            {
                GoalLeaf
                    leafNode =
                    (GoalLeaf) j.next();
                if (value.getId()
                    .equals(leafNode.getGoalId()))
                {
                    leafNode.setName(value.getName());
                    branches.add(leafNode);
                    valueFound =
                        true;
                    break;
                }
            }
            if (!valueFound)
            {
                branches.add(new GoalLeaf(value));
            }
        }
        children =
            branches;
        return children;
    }
}
