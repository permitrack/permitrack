package com.sehinc.erosioncontrol.server.navigation;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.portal.PortalUtils;

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
                                                  CommonConstants.EROSION_CONTROL_MODULE))
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
                                          CommonConstants.EROSION_CONTROL_MODULE);
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