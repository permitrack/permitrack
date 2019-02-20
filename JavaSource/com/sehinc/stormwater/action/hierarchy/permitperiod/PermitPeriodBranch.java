package com.sehinc.stormwater.action.hierarchy.permitperiod;

import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuBranch;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;

import java.util.ArrayList;
import java.util.List;

public class PermitPeriodBranch
    extends TreeViewMenuBranch
    implements Comparable
{
    private
    List
        children =
        null;

    public PermitPeriodBranch()
    {
        setName("Permit Periods");
        setType("permitPeriodBranch");
        setTypeId(0);
        setUrl("/ms4/permitperiod/permitperiodviewaction.do");
    }

    public List getChildren()
    {
        if (children
            == null)
        {
            children =
                new ArrayList();
            getBranchList();
        }
        return children;
    }

    private List getBranchList()
    {
        children =
            PermitPeriodService.getPermitPeriodsAsLeafList();
        return children;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof PermitPeriodBranch)
        {
            return getName().compareTo(((PermitPeriodBranch) obj).getName());
        }
        return 0;
    }
}
