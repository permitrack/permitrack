package com.sehinc.stormwater.action.hierarchy.treemenu;

import java.util.List;

public interface TreeMenuBranch
    extends TreeMenuLeaf
{
    public List getChildren();

    public boolean isExpanded();

    public void expand();

    public void collapse();
}
