package com.sehinc.stormwater.action.hierarchy.permitperiod;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuLeaf;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;

public class PermitPeriodLeaf
    extends TreeViewMenuLeaf
    implements Comparable
{
    private
    PermitPeriodValue
        permitPeriod =
        null;

    public PermitPeriodLeaf(PermitPeriodValue permitPeriod)
    {
        this.permitPeriod =
            permitPeriod;
        setName(permitPeriod.getName());
        setType("permitPeriodLeaf");
        setTypeId(permitPeriod.getId()
                      .intValue());
        setUrl("/ms4/permitperiod/permitperiodviewaction.do?"
               + RequestKeys.PERMIT_PERIOD_ID
               + "="
               + permitPeriod.getId());
    }

    public Integer getPermitPeriodId()
    {
        return permitPeriod.getId();
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof PermitPeriodLeaf)
        {
            return getName().compareTo(((PermitPeriodLeaf) obj).getName());
        }
        return 0;
    }
}
