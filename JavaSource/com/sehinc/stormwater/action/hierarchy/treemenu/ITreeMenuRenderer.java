package com.sehinc.stormwater.action.hierarchy.treemenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITreeMenuRenderer
{
    public String render(TreeMenuLeaf treeMenuLeaf, HttpServletRequest request, HttpServletResponse response)
        throws Exception;
}
