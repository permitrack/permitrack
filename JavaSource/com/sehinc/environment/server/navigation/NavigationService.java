package com.sehinc.environment.server.navigation;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.portal.PortalUtils;

import javax.servlet.http.HttpServletRequest;

public class NavigationService
{
    public NavigationService()
    {
    }

    private static PrimaryMenu getPrimaryMenu(UserValue user, Integer id, String name)
    {
        if (id
            == null
            && PortalUtils.userHasMultipleClients(user,
                                                  CommonConstants.ENVIRONMENT_MODULE))
        {
            return PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME);
        }
        else
        {
            return PrimaryMenu.getInstance(PrimaryMenu.CLIENT_MENU_NAME,
                                           name);
        }
    }

    public static PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.ENVIRONMENT_MODULE);
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(SessionService.getUserValue(request),
                           clientValue
                           != null
                               ? clientValue.getId()
                               : null,
                           clientValue
                           != null
                               ? clientValue.getName()
                               : null);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
        return primaryMenu;
    }

    private static SecondaryMenu getSecondaryMenu(UserValue user, int id)
    {
        if (id
            == 0
            && PortalUtils.userHasMultipleClients(user,
                                                  CommonConstants.ENVIRONMENT_MODULE))
        {
            return SecondaryMenu.getInstance(SecondaryMenu.SYSTEM_ADMIN_MENU_NAME);
        }
        else
        {
            return SecondaryMenu.getInstance(SecondaryMenu.FACILITY_LIST_MENU);
        }
    }

    public static SecondaryMenu getSecondaryMenu(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.ENVIRONMENT_MODULE);
        SecondaryMenu
            secondaryMenu =
            getSecondaryMenu(SessionService.getUserValue(request),
                             clientValue
                             != null
                                 ? clientValue.getId()
                                 : 0);
        SessionService.setSessionAttribute(SessionKeys.EV_SECONDARY_MENU,
                                           secondaryMenu,
                                           request);
        return secondaryMenu;
    }
}