package com.sehinc.portal.action.navigation;

import com.sehinc.portal.PortalUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PillMenuBeanBase
    extends MenuBean
{
    @Override
    protected String getContainerStart()
    {
        return "<ul class='nav nav-pills light'>";
    }

    @Override
    protected String getContainerEnd()
    {
        return "</ul>";
    }

    @Override
    protected String buildMenuItem(INavMenu INavMenu, MenuItem menuItem, HttpServletRequest request, HttpServletResponse response)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        String
            css =
            "";
        if (isCommand(menuItem))
        {
            if (menuItem.getDescription()
                .contains("Delete"))
            {
                css =
                    " btn-danger warn-delete";
            }
            else
            {
                css =
                    " btn-success";
            }
        }
        if (INavMenu.getCurrentItem()
            != null
            && menuItem.getName()
            .equals(INavMenu.getCurrentItem()
                        .getName()))
        {
            if (isCommand(menuItem))
            {
                buffer.append("<li><a class='btn btn-mini disabled"
                              + css
                              + "' href='");
            }
            else
            {
                buffer.append("<li class='active'><a href='");
            }
        }
        else
        {
            if (isCommand(menuItem))
            {
                buffer.append("<li><a class='btn btn-mini"
                              + css
                              + "' href='");
            }
            else
            {
                buffer.append("<li><a href='");
            }
        }
        buffer.append(PortalUtils.addContextPrefixAndEncode(String.format("%s%s",
                                                                          menuItem.getModule(),
                                                                          menuItem.getLocation()),
                                                            request,
                                                            response));
        buffer.append("'>");
        buffer.append(menuItem.getDescription());
        buffer.append("</a></li>");
        return buffer.toString();
    }

    // TODO
    private boolean isCommand(MenuItem tertiaryMenuItem)
    {
        return tertiaryMenuItem.getDescription()
                   .startsWith("Add")
               || tertiaryMenuItem.getDescription()
            .startsWith("Edit")
               || tertiaryMenuItem.getDescription()
            .startsWith("Delete")
               || tertiaryMenuItem.getDescription()
            .startsWith("Connect")
               || tertiaryMenuItem.getDescription()
            .startsWith("+")
               || tertiaryMenuItem.getDescription()
            .startsWith("-");
    }
}
