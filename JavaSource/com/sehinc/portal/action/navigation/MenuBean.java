package com.sehinc.portal.action.navigation;

import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class MenuBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(com.sehinc.environment.action.navigation.TertiaryMenuBean.class);

    protected String getContainerStart()
    {
        return "";
    }

    protected String getContainerEnd()
    {
        return "";
    }

    protected String buildMenuItem(INavMenu INavMenu, MenuItem tertiaryMenuItem, HttpServletRequest request, HttpServletResponse response)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        String
            css =
            "";
        if (tertiaryMenuItem.getDescription()
            .contains("Delete"))
        {
            css =
                " btn-danger warn-delete";
        }
        buffer.append("<a class='btn btn-large"
                      + css
                      + "' href=\"");
        buffer.append(PortalUtils.addContextPrefixAndEncode(String.format("%s%s",
                                                                          tertiaryMenuItem.getModule(),
                                                                          tertiaryMenuItem.getLocation()),
                                                            request,
                                                            response));
        buffer.append("\">");
        buffer.append(tertiaryMenuItem.getDescription());
        buffer.append("</a>");
        return buffer.toString();
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        INavMenu
            INavMenu =
            (INavMenu) request.getSession()
                .getAttribute(SessionKeys.TERTIARY_MENU);
        StringBuilder
            buffer =
            new StringBuilder();
        buffer.append(getContainerStart());
        SecurityManager
            securityManager =
            null;
        try
        {
            securityManager =
                SecurityManager.getSecurityManager(request);
        }
        catch (Exception e)
        {
            LOG.error("Failed to get SecurityManager instance from the session");
        }
        if (INavMenu
            != null
            && INavMenu.getItemCount()
               > 0
            && !INavMenu.getName()
            .equals(INavMenu.NONE_MENU))
        {
            Iterator
                iter =
                INavMenu.getMenuItems()
                    .iterator();
            int
                securityLevel =
                SessionService.getSecurityLevel(request);
            while (iter.hasNext())
            {
                MenuItem
                    tertiaryMenuItem =
                    (MenuItem) iter.next();
                if (tertiaryMenuItem.getLocation()
                        .length()
                    > 0)
                {
                    if (securityLevel
                        >= tertiaryMenuItem.getMinSecurityLevel())
                    {
                        if (tertiaryMenuItem.getSecureObjectPermission()
                            == null)
                        {
                            buffer.append(buildMenuItem(INavMenu,
                                                        tertiaryMenuItem,
                                                        request,
                                                        response));
                        }
                        else
                        {
                            if (securityManager
                                != null
                                && securityManager.HasECPermission(tertiaryMenuItem.getSecureObjectPermission()))
                            {
                                buffer.append(buildMenuItem(INavMenu,
                                                            tertiaryMenuItem,
                                                            request,
                                                            response));
                            }
                            else
                            {
                                LOG.debug(String.format("render(): User does not have permission for this menu item. Menu=%s",
                                                        tertiaryMenuItem.getName()));
                            }
                        }
                    }
                }
            }
        }
        else
        {
            LOG.warn("TertiaryMenu is NULL or has no items.");
        }
        buffer.append(getContainerEnd());
        return buffer.toString();
    }
}
