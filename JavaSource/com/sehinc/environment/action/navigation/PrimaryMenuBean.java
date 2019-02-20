package com.sehinc.environment.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.portal.action.navigation.INavMenu;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class PrimaryMenuBean
    extends com.sehinc.portal.action.navigation.PrimaryMenuBean
{
    private static
    Logger
        LOG =
        Logger.getLogger(PrimaryMenuBean.class);

    public PrimaryMenuBean()
    {
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        INavMenu
            primaryMenu =
            (INavMenu) request.getSession()
                .getAttribute(SessionKeys.PRIMARY_MENU);
        return this.render(request,
                           response,
                           primaryMenu);
    }

    public String render(HttpServletRequest request, HttpServletResponse response, INavMenu primaryMenu)
        throws Exception
    {
        if (primaryMenu
            == null)
        {
            throw new NullPointerException("primary menu cannot be null");
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
            PrimaryMenuItem
                primaryMenuItem =
                (PrimaryMenuItem) iter.next();
            if (securityLevel
                >= primaryMenuItem.getMinSecurityLevel())
            {
                if (primaryMenuItem.getSecureObjectPermission()
                    == null)
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
}
