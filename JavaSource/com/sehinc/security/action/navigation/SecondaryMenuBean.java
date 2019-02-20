package com.sehinc.security.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.PillMenuBeanBase;
import com.sehinc.security.action.base.SessionKeys;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class SecondaryMenuBean
    extends PillMenuBeanBase
{
    private static
    Logger
        LOG =
        Logger.getLogger(SecondaryMenuBean.class);

    public SecondaryMenuBean()
    {
    }

    public String render(HttpServletRequest request, HttpServletResponse response)
    {
        return this.render(request,
                           response,
                           (SecondaryMenu) request.getSession()
                               .getAttribute(SessionKeys.SECONDARY_MENU),
                           false);
    }

    public String render(HttpServletRequest request, HttpServletResponse response, INavMenu secondaryMenu, Boolean abc)
    {
        if (secondaryMenu
            == null)
        {
            return "";
        }
        StringBuilder
            buffer =
            new StringBuilder();
        if (secondaryMenu.getItemCount()
            > 0
            && !secondaryMenu.getName()
            .equals(SecondaryMenu.NONE_MENU_NAME))
        {
            if (!abc)
            {
                buffer.append(getContainerStart());
            }
            Iterator
                iter =
                secondaryMenu.getMenuItems()
                    .iterator();
            if (iter.hasNext())
            {
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
                        if (secondaryMenuItem.getCapModuleCode()
                            == null)
                        {
                            buffer.append(buildMenuItem(secondaryMenu,
                                                        secondaryMenuItem,
                                                        request,
                                                        response));
                        }
                        else
                        {
                            assert securityManager
                                   != null;
                            if (securityManager.getClientCanAccessModule(secondaryMenuItem.getCapModuleCode()))
                            {
                                if (securityManager.getUserCanAccessModule(secondaryMenuItem.getCapModuleCode())
                                    || securityManager.getIsClientAdministrator())
                                {
                                    buffer.append(buildMenuItem(secondaryMenu,
                                                                secondaryMenuItem,
                                                                request,
                                                                response));
                                }
                            }
                        }
                    }
                }
            }
            if (!abc)
            {
                buffer.append(getContainerEnd());
            }
        }
        return buffer.toString();
    }
}