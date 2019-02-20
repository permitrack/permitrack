package com.sehinc.stormwater.action.navigation;

import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.portal.PortalUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class SecondaryMenuBean
{
    public SecondaryMenuBean()
    {
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
    {
        SecondaryMenu
            secondaryMenu =
            (SecondaryMenu) SessionService.getSessionAttribute(SessionKeys.SECONDARY_MENU,
                                                               request);
        if (secondaryMenu
            == null)
        {
            return "";
        }
        StringBuilder
            buffer =
            new StringBuilder();
        if (!secondaryMenu.getMenuItems()
            .isEmpty())
        {
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
                }
            }
            buffer.append("</ul>");
        }
        return buffer.toString();
    }
}
