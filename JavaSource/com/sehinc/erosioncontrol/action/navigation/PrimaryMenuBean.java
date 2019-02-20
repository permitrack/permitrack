package com.sehinc.erosioncontrol.action.navigation;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.portal.action.navigation.INavMenu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrimaryMenuBean
    extends com.sehinc.portal.action.navigation.PrimaryMenuBean
{
    INavMenu
        sec;

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
        throws Exception
    {
        return new SecondaryMenuBean().render(request,
                                              response,
                                              sec,
                                              true);
    }
}
