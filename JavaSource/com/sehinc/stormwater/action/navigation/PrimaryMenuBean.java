package com.sehinc.stormwater.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.stormwater.action.base.SessionKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class PrimaryMenuBean
    extends com.sehinc.portal.action.navigation.PrimaryMenuBean
{
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
/*
        PrimaryMenu
            primaryMenu =
            (PrimaryMenu) request.getSession()
                .getAttribute(SessionKeys.PRIMARY_MENU);
*/
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
                >= primaryMenuItem.getMinSecurityLevel())
            {
                buffer.append(buildMenuItem(primaryMenu,
                                            primaryMenuItem,
                                            request,
                                            response));

                /*
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
                                buffer.append("'><span>");
                                buffer.append(primaryMenuItem.getDescription());
                                buffer.append("</span></a></li>");
                */
            }
        }
        buffer.append("</ul>");
        return buffer.toString();
    }
}
