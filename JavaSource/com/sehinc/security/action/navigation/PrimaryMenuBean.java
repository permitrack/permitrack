package com.sehinc.security.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.security.action.base.SessionKeys;
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
                >= primaryMenuItem.getMinSecurityLevel()
                && primaryMenuItem.getLocation()
                       .length()
                   > 0)
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
        buffer.append("</ul>");
        return buffer.toString();
    }

    INavMenu
        sec;

    protected INavMenu getSubMenu()
    {
        if (sec
            == null)
        {
            sec =
                SecondaryMenu.getInstance(SecondaryMenu.OPTIONS_LIST_MENU);
        }
        return sec;
    }

    protected INavMenu getSecondaryMenu(HttpServletRequest request)
    {
        return (SecondaryMenu) SessionService.getSessionAttribute(SessionKeys.SECONDARY_MENU,
                                                                  request);
    }

    protected String getMenuBean(HttpServletRequest request, HttpServletResponse response, INavMenu sec)
    {
        return new SecondaryMenuBean().render(request,
                                              response,
                                              sec,
                                              true);
    }
}
