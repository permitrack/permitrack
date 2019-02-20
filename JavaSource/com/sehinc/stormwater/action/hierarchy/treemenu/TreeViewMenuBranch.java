package com.sehinc.stormwater.action.hierarchy.treemenu;

import com.sehinc.stormwater.action.hierarchy.plan.BranchConstants;

public abstract class TreeViewMenuBranch
    extends AbstractTreeMenuBranch
{
    public String getID()
    {
        return BranchConstants.generateTreeId(getType(),
                                              getTypeId());
    }

    public void setID(String id)
    {
    }
}
