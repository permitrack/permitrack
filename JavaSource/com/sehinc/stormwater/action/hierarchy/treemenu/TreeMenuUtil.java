package com.sehinc.stormwater.action.hierarchy.treemenu;

import com.sehinc.common.util.StringUtil;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.hierarchy.plan.BranchConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TreeMenuUtil
{
    public static String getPortalStrutsUrl(TreeMenuLeaf treeLeaf, HttpServletRequest request, HttpServletResponse response)
    {
        if (treeLeaf.getUrl()
            == null)
        {
            return "";
        }
        return treeLeaf.getUrl();
    }

    public static String parseName(String name)
    {
        return StringUtil.replace(name,
                                  String.valueOf('\"'),
                                  "\\\"");
    }

    public static String getSubNodeViewParameters(TreeMenuLeaf treeLeaf, HttpServletRequest request, HttpServletResponse response)
    {
        if (treeLeaf.getUrl()
            == null)
        {
            return "";
        }
        return UrlUtil.getSubNodeParameters(treeLeaf.getUrl());
    }

    public static String getSubNodeViewURI(HttpServletRequest request, HttpServletResponse response)
    {
        return UrlUtil.getSubeNodePrefix(BranchConstants.SUBNODE_URL
                                         + "?"
                                         + RequestKeys.NODE_ID
                                         + "=12");
    }
}
