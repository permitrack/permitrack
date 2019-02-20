package com.sehinc.erosioncontrol.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
import com.sehinc.portal.action.navigation.INavMenu;
import com.sehinc.portal.action.navigation.PillMenuBeanBase;
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
        throws Exception
    {
        return this.render(request,
                           response,
                           (SecondaryMenu) request.getSession()
                               .getAttribute(SessionKeys.SECONDARY_MENU),
                           false);
    }

    public String render(HttpServletRequest request, HttpServletResponse response, INavMenu secondaryMenu, Boolean abc)
        throws Exception
    {
        if (secondaryMenu
            == null)
        {
            return "";
        }
        SecurityManager
            securityManager =
            SecurityManager.getSecurityManager(request);
        StringBuilder
            buffer =
            new StringBuilder();
        Iterator
            iter =
            secondaryMenu.getMenuItems()
                .iterator();
        if (iter.hasNext())
        {
            int
                securityLevel =
                SessionService.getSecurityLevel(request);
            if (!abc)
            {
                buffer.append(getContainerStart());
            }
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
                        if (secondaryMenuItem.getIsProjectSecure())
                        {
                            ProjectValue
                                projectValue =
                                (ProjectValue) request.getSession()
                                    .getAttribute(SessionKeys.EC_PROJECT);
                            if (projectValue
                                != null)
                            {
                                if (securityManager
                                    != null
                                    && securityManager.HasECPermission(secondaryMenuItem.getSecureObjectPermission(),
                                                                       projectValue.getId()))
                                {
                                    buffer.append(buildMenuItem(secondaryMenu,
                                                                secondaryMenuItem,
                                                                request,
                                                                response));
                                }
                                else
                                {
                                    LOG.debug(String.format("SecondaryMenuBean.render: User does not have permission for this menu item. Menu=%s",
                                                            secondaryMenuItem.getName()));
                                }
                            }
                        }
                        else
                        {
                            assert securityManager
                                   != null;
                            if (securityManager.HasECPermission(secondaryMenuItem.getSecureObjectPermission()))
                            {
                                buffer.append(buildMenuItem(secondaryMenu,
                                                            secondaryMenuItem,
                                                            request,
                                                            response));
                            }
                            else
                            {
                                LOG.debug(String.format("SecondaryMenuBean.render: User does not have permission for this menu item. Menu=%s",
                                                        secondaryMenuItem.getName()));
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