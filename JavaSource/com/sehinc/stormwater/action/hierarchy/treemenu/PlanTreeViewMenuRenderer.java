package com.sehinc.stormwater.action.hierarchy.treemenu;

import com.sehinc.portal.PortalUtils;

public class PlanTreeViewMenuRenderer
    extends TreeViewMenuRenderer
{
    public void getHref(StringBuilder buffer, TreeMenuLeaf child, boolean only)
    {
        buffer.append(PortalUtils.addContextAndMarkupPrefixAndEncode(getSubNodeViewURI()
                                                                     + getSubNodeViewParameters(child),
                                                                     request,
                                                                     response)
                      + (only
                             ? "?root"
                             : ""));
    }
}
