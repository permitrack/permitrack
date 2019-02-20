package com.sehinc.stormwater.action.hierarchy.template;

import com.sehinc.stormwater.action.hierarchy.plan.MCMBranch;
import com.sehinc.stormwater.action.hierarchy.plan.PlanBranch;
import com.sehinc.stormwater.value.plan.MCMValue;
import com.sehinc.stormwater.value.plan.PlanValue;

public class PlanTemplateBranch
    extends PlanBranch
{
    public PlanTemplateBranch(PlanValue plan)
    {
        super(plan);
        setUrl(getUrlString()
               + "id"
               + "="
               + getTypeId());
    }

    public String getUrlString()
    {
        return "/ms4/template/plantemplateviewaction.do?";
    }

    public String getTypeString()
    {
        return "plan_temp";
    }

    public MCMBranch createMCMBranch(MCMValue value)
    {
        return new MCMTemplateBranch(value);
    }
}
