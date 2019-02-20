package com.sehinc.environment.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class SecondaryMenuBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(com.sehinc.environment.action.navigation.SecondaryMenuBean.class);

    public SecondaryMenuBean()
    {
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
    {
        SecondaryMenu
            secondaryMenu =
            (SecondaryMenu) request.getSession()
                .getAttribute(SessionKeys.EV_SECONDARY_MENU);
        if (secondaryMenu
            == null)
        {
            return "";
        }
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
        StringBuilder
            buffer =
            new StringBuilder();
        buffer.append("<ul class='nav nav-pills'>");
        Iterator
            iter =
            secondaryMenu.getMenuItems()
                .iterator();
        int
            securityLevel =
            SessionService.getSecurityLevel(request);
        while (iter.hasNext())
        {
            SecondaryMenuItem
                secondaryMenuItem =
                (SecondaryMenuItem) iter.next();
            if (securityLevel
                >= secondaryMenuItem.getMinSecurityLevel())
            {
                if (secondaryMenuItem.getSecureObjectPermission()
                    == null)
                {
                    buffer.append(buildMenuItem(secondaryMenu,
                                                secondaryMenuItem,
                                                request,
                                                response));
                }
                else
                {
                    if (securityManager
                        != null
                        && securityManager.getIsClientAdministrator())
                    {
                        buffer.append(buildMenuItem(secondaryMenu,
                                                    secondaryMenuItem,
                                                    request,
                                                    response));
                    }
                    else
                    {
                        if (securityManager
                            != null
                            && securityManager.HasECPermission(secondaryMenuItem.getSecureObjectPermission()))
                        {
                            buffer.append(buildMenuItem(secondaryMenu,
                                                        secondaryMenuItem,
                                                        request,
                                                        response));
                        }
                        else
                        {
                            LOG.debug(String.format("ENV: SecondaryMenuBean.render: User does not have permission for this menu item. Menu=%s",
                                                    secondaryMenuItem.getName()));
                        }
                    }
                }
            }
        }
        buffer.append("</ul>");
        return buffer.toString();
    }

    private String buildMenuItem(SecondaryMenu secondaryMenu, SecondaryMenuItem secondaryMenuItem, HttpServletRequest request, HttpServletResponse response)
    {
        StringBuilder
            buffer =
            new StringBuilder();
        if (secondaryMenuItem.getName()
            .equals(secondaryMenu.getCurrentItem()
                        .getName()))
        {
            buffer.append("<li class='active'><a href='");
        }
        else
        {
            buffer.append("<li><a href='");
        }
        buffer.append(PortalUtils.addContextPrefixAndEncode(secondaryMenuItem.getModule()
                                                            + secondaryMenuItem.getLocation(),
                                                            request,
                                                            response));
        buffer.append("'><span>");
        buffer.append(secondaryMenuItem.getDescription());
        buffer.append("</span></a>");
        buffer.append("</li>");
        return buffer.toString();
    }
}
