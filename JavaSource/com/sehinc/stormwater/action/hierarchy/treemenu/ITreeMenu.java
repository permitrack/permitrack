package com.sehinc.stormwater.action.hierarchy.treemenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITreeMenu
{
    public String render()
        throws Exception;

    public String render(HttpServletRequest request, HttpServletResponse response)
        throws Exception;
}
