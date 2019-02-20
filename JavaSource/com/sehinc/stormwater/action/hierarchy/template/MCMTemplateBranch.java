package com.sehinc.stormwater.action.hierarchy.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.plan.MCMBranch;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.MCMValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MCMTemplateBranch
    extends MCMBranch
    implements Comparable
{
    public MCMTemplateBranch(MCMValue mcm)
    {
        super(mcm);
        setType("mcm_temp");
        setUrl("/ms4/template/mcmtemplateviewaction.do?"
               + RequestKeys.MCM_ID
               + "="
               + getMCMId());
    }

    public List getBranchList()
    {
        ArrayList
            branches =
            new ArrayList();
        List
            bmpList =
            PlanService.getBMPValues(new MCMValue(getMCMId(),
                                                  getName(),
                                                  getNumber()));
        Iterator
            i =
            bmpList.iterator();
        while (i.hasNext())
        {
            BMPValue
                value =
                (BMPValue) i.next();
            boolean
                valueFound =
                false;
            Iterator
                j =
                children.iterator();
            while (j.hasNext())
            {
                BMPTemplateBranch
                    branchNode =
                    (BMPTemplateBranch) j.next();
                if (value.getId()
                    .equals(branchNode.getBMPId()))
                {
                    branchNode.setName(value.getName());
                    branches.add(branchNode);
                    valueFound =
                        true;
                    break;
                }
            }
            if (!valueFound)
            {
                branches.add(new BMPTemplateBranch(value));
            }
        }
        children =
            branches;
        return children;
    }
}
