package com.sehinc.stormwater.action.hierarchy.treemenu;

import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class TreeViewMenuRenderer
    implements ITreeMenuRenderer
{
    private static
    Logger
        LOG =
        Logger.getLogger(TreeViewMenuRenderer.class);
    HttpServletRequest
        request;
    HttpServletResponse
        response;

    public String getJavaScript(TreeMenuLeaf root)
        throws Exception
    {
        LOG.info(getContextPath());
        StringBuilder
            buffer =
            new StringBuilder();
        try
        {
            buffer.append(buildTreeViewMenu(root));
        }
        catch (Exception ex)
        {
            LOG.error(ex);
            throw ex;
        }
        return buffer.toString();
    }

    public void getHref(StringBuilder buffer, TreeMenuLeaf child, boolean only)
    {
        buffer.append(PortalUtils.addContextAndMarkupPrefixAndEncode(getPortalStrutsUrl(child),
                                                                     request,
                                                                     response));
    }

    public TreeViewMenuRenderer()
    {
    }

    public String render(TreeMenuLeaf root, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        this.request =
            request;
        this.response =
            response;
        return getJavaScript(root);
    }

    public String buildTreeViewMenu(TreeMenuLeaf root)
        throws Exception
    {
        return buildTreeViewMenuNode(root,
                                     true,
                                     true,
                                     true);
    }

    private String buildTreeViewMenuNode(TreeMenuLeaf child, Boolean first, Boolean last, Boolean root)
        throws Exception
    {
        StringBuilder
            buffer =
            new StringBuilder();
        String
            stringToAdd;
        if (first)
        {
            buffer.append("<ul>");
        }
        stringToAdd =
            writeTreeViewMenuBranch(child,
                                    root);
        buffer.append(stringToAdd);
        if (child instanceof TreeMenuBranch)
        {
            boolean
                first1 =
                true;
            Iterator
                children =
                ((TreeMenuBranch) child).getChildren()
                    .iterator();
            while (children.hasNext())
            {
                TreeMenuLeaf
                    node =
                    (TreeMenuLeaf) children.next();
                stringToAdd =
                    buildTreeViewMenuNode(node,
                                          first1,
                                          !children.hasNext(),
                                          false);
                buffer.append(stringToAdd);
                first1 =
                    false;
            }
        }
        buffer.append("</li>");
        if (last)
        {
            buffer.append("</ul>");
        }
        return buffer.toString();
    }

    private String writeTreeViewMenuBranch(TreeMenuLeaf child, boolean only)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        buffer.append("<li id='")
            .append(child.getID())
            .append("' rel='")
            .append(child.getClass()
                        .getSimpleName())
            .append("'>");
        buffer.append("<a href='");
        getHref(buffer,
                child,
                only);
        buffer.append("'><span>");
        buffer.append(TreeMenuUtil.parseName(child.getName()));
        buffer.append("</span></a>");
        return buffer.toString();
    }

    public String getPortalStrutsUrl(TreeMenuLeaf child)
    {
        return TreeMenuUtil.getPortalStrutsUrl(child,
                                               request,
                                               response);
    }

    public String getSubNodeViewParameters(TreeMenuLeaf child)
    {
        return TreeMenuUtil.getSubNodeViewParameters(child,
                                                     request,
                                                     response);
    }

    public String getSubNodeViewURI()
    {
        return TreeMenuUtil.getSubNodeViewURI(request,
                                              response);
    }

    public String getContextPath()
    {
        return request.getContextPath();
    }
}
