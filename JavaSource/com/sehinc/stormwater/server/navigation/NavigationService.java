package com.sehinc.stormwater.server.navigation;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.PortalUtils;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;

import javax.servlet.http.HttpServletRequest;

public class NavigationService
{
    public NavigationService()
    {
    }

    private static PrimaryMenu getPrimaryMenu(UserValue user, int id, String name)
    {
        if (id
            == 0
            && PortalUtils.userHasMultipleClients(user,
                                                  CommonConstants.STORM_WATER_MODULE))
        {
            return PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME,
                                           id);
        }
        else
        {
            return PrimaryMenu.getInstance(PrimaryMenu.CLIENT_MENU_NAME,
                                           id,
                                           name);
        }
    }

    public static PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(SessionService.getUserValue(request),
                           clientValue
                           != null
                               ? clientValue.getId()
                               : 0,
                           clientValue
                           != null
                               ? clientValue.getName()
                               : "");
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
        return primaryMenu;
    }
}