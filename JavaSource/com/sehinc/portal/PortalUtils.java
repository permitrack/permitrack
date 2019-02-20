package com.sehinc.portal;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.action.navigation.PortalMenuItem;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PortalUtils
{
    private static
    Logger
        LOG =
        Logger.getLogger(PortalUtils.class);

    public static String addMarkupPrefix(String url, HttpServletRequest request)
    {
        return "/"
               + getUserMarkup(request)
               + url;
    }

    public static String addContextPrefix(String url, HttpServletRequest request)
    {
        return request.getContextPath()
               + url;
    }

    public static String addContextPrefixAndEncode(String url, HttpServletRequest request, HttpServletResponse response)
    {
        return response.encodeURL(request.getContextPath()
                                  + response.encodeURL(url));
    }

    public static PortalMenu getPortalMenu(UserValue user, String markup)
    {
        PortalMenu
            portalMenu =
            new PortalMenu();
        for (Object m : CapModule.findByUserAndMarkup(user,
                                                      markup))
        {
            CapModule
                module =
                (CapModule) m;
            PortalMenuItem
                portalMenuItem =
                new PortalMenuItem(module);
            portalMenuItem.setImage("/sehsvc/img/icons/"
                                    + module.getCode()
                .toLowerCase()
                                    + ".png");
            portalMenu.add(portalMenuItem);
        }
        PortalMenuItem
            portalMenuItemA =
            new PortalMenuItem();
        portalMenuItemA.setName("Activity");
        portalMenuItemA.setModulePath("/sec");
        portalMenuItemA.setPath("/client/clientactivitypageaction.do?type=timeline");
        portalMenuItemA.setCode("AC");
        portalMenuItemA.setDescription("View user activity");
        portalMenuItemA.setSequence(8);
        portalMenuItemA.setImage("/sehsvc/img/icons/se.png");
        portalMenu.add(portalMenuItemA);
        return portalMenu;
    }

    public static String getUserMarkup(HttpServletRequest request)
    {
        String
            markup =
            "html";
        return markup;
    }

    public static String addContextAndMarkupPrefixAndEncode(String url, HttpServletRequest request, HttpServletResponse response)
    {
        url =
            PortalUtils.addMarkupPrefix(url,
                                        request);
        return PortalUtils.addContextPrefixAndEncode(url,
                                                     request,
                                                     response);
    }

    public static void forward(String destination, HttpServletRequest aRequest, HttpServletResponse aResponse)
        throws ServletException, IOException
    {
        RequestDispatcher
            dispatcher =
            aRequest.getRequestDispatcher(destination);
        dispatcher.forward(aRequest,
                           aResponse);
    }

    public static boolean userHasMultipleClients(UserValue user)
    {
        return (UserService.getSecurityLevel(user.getGroupId())
                == com.sehinc.common.security.SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL
                || ClientService.getClients(user)
                       .size()
                   > 1);
    }

    public static boolean userHasMultipleClients(UserValue user, String module)
    {
        return (UserService.getSecurityLevel(user.getGroupId())
                == com.sehinc.common.security.SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL
                || ClientService.getClientsByUserAndModule(user,
                                                           module)
                       .size()
                   > 1);
    }

    public static boolean userHasClient(UserValue user, ClientData client)
    {
        return (user
                == null
                    ? false
                    : UserService.getSecurityLevel(user.getGroupId())
                      == SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL
                      || ClientService.getClients(user)
                        .contains(client));
    }
}
