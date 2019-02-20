package com.sehinc.stormwater.action.hierarchy.plan;

import com.sehinc.stormwater.action.hierarchy.treemenu.TreeViewMenuBranch;

public abstract class PlanMenuBranch
    extends TreeViewMenuBranch
    implements PlanMenuLeaf
{
    private
    String
        parentId =
        null;

    public abstract String getParentType();

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
}
