package com.sehinc.portal.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class PrimaryMenuBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(com.sehinc.erosioncontrol.action.navigation.PrimaryMenuBean.class);

    public String render(HttpServletRequest request, HttpServletResponse response, INavMenu primaryMenu)
        throws Exception
    {
        if (primaryMenu
            == null)
        {
            throw new NullPointerException("primary menu cannot be null");
        }
        com.sehinc.common.security.SecurityManager
            securityManager =
            SecurityManager.getSecurityManager(request);
        StringBuilder
            buffer =
            new StringBuilder();
        Iterator
            iter =
            primaryMenu.getMenuItems()
                .iterator();
        buffer.append("<ul class='nav nav-tabs'>");
        int
            securityLevel =
            SessionService.getSecurityLevel(request);
        while (iter.hasNext())
        {
            MenuItem
                primaryMenuItem =
                (MenuItem) iter.next();
            if (securityLevel
                >= primaryMenuItem.getMinSecurityLevel())
            {
                if (primaryMenuItem.getSecureObjectPermission()
                    == null)
                { // The primary menu item ignores secure object permissions
                    buffer.append(buildMenuItem(primaryMenu,
                                                primaryMenuItem,
                                                request,
                                                response));
                }
                else
                {
                    if (securityManager
                        != null
                        && securityManager.getIsClientAdministrator())
                    {
                        buffer.append(buildMenuItem(primaryMenu,
                                                    primaryMenuItem,
                                                    request,
                                                    response));
                    }
                    else
                    {
                        if (securityManager
                            != null
                            && securityManager.HasECPermission(primaryMenuItem.getSecureObjectPermission()))
                        {
                            buffer.append(buildMenuItem(primaryMenu,
                                                        primaryMenuItem,
                                                        request,
                                                        response));
                        }
                        else
                        {
                            LOG.debug(String.format("PrimaryMenuBean.render: User does not have permission for this menu item. Menu=%s",
                                                    primaryMenuItem.getName()));
                        }
                    }
                }
            }
        }
        buffer.append("</ul>");
        return buffer.toString();
    }

    protected INavMenu getSubMenu()
    {
        return null;
    }

    protected INavMenu getSecondaryMenu(HttpServletRequest request)
    {
        return null;
    }

    protected String getMenuBean(HttpServletRequest request, HttpServletResponse response, INavMenu sec)
        throws Exception
    {
        return null;
    }

    protected String buildMenuItem(INavMenu primaryMenu, MenuItem primaryMenuItem, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        StringBuilder
            buffer =
            new StringBuilder();
        String
            muted =
            "";
        if (primaryMenuItem.getName()
            .equals(INavMenu.EXIT_MENU_ITEM))
        {
            muted =
                " class='muted'";
        }
        if (primaryMenuItem.getName()
            .equals(INavMenu.OPTIONS_LIST_MENU_ITEM))
        {
            INavMenu
                subMenu =
                this.getSubMenu();
            if (subMenu
                != null
                && subMenu.getMenuItems()
                       .size()
                   > 0)
            {
                if (primaryMenuItem.getName()
                    .equals(primaryMenu.getCurrentItem()
                                .getName()))
                {
                    buffer.append("<li class='dropdown active'>");
                }
                else
                {
                    buffer.append("<li class='dropdown'>");
                }
                buffer.append("<a class='dropdown-toggle'");
                buffer.append(" data-toggle='dropdown'");
                buffer.append(" href='#'>");
                buffer.append("Settings");
                buffer.append("<b class='caret'></b>");
                buffer.append("</a>");
                buffer.append("<ul class='dropdown-menu'>");
                INavMenu
                    secondaryMenu =
                    this.getSecondaryMenu(request);
                if (primaryMenuItem.getName()
                        .equals(primaryMenu.getCurrentItem()
                                    .getName())
                    && secondaryMenu
                       != null
                    && secondaryMenu.getMenuItems()
                           .size()
                       > 0)
                {
                    subMenu.setCurrentItem(((MenuItem) secondaryMenu.getMenuItems()
                        .get(0)).getName());
                }
                else
                {
                    subMenu.setCurrentItem(null);
                }
                buffer.append(this.getMenuBean(request,
                                               response,
                                               subMenu));
                buffer.append("</ul>");
                buffer.append("</li>");
            }
        }
        else
        {
            if (primaryMenuItem.getName()
                .equals(primaryMenu.getCurrentItem()
                            .getName()))
            {
                buffer.append("<li class='active'><a href='");
            }
            else
            {
                buffer.append("<li><a href='");
            }
            buffer.append(PortalUtils.addContextPrefixAndEncode(primaryMenuItem.getModule()
                                                                + primaryMenuItem.getLocation(),
                                                                request,
                                                                response));
            buffer.append("'><span"
                          + muted
                          + ">");
            buffer.append(primaryMenuItem.getDescription());
            buffer.append("</span></a></li>");
        }
        return buffer.toString();
    }
}
