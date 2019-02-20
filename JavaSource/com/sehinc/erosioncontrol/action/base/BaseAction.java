package com.sehinc.erosioncontrol.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.navigation.NavigationService;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAction
    extends com.sehinc.common.action.base.BaseAction
{
    protected static
    Logger
        LOG =
        Logger.getLogger(BaseAction.class);

    public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return super.execute(mapping,
                             form,
                             request,
                             response);
    }

    public UserValue getUserValue(HttpServletRequest request)
    {
        return SessionService.getUserValue(request);
    }

    public ClientValue getClientValue(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.EROSION_CONTROL_MODULE);
        if (clientValue
            == null)
        {
            clientValue =
                getClientValueDefault(CommonConstants.EROSION_CONTROL_MODULE,
                                      request);
            setSessionAttribute(SessionKeys.ESC_CLIENT,
                                clientValue,
                                request);
        }
        return clientValue;
    }

    protected PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        return NavigationService.getPrimaryMenu(request);
    }

    protected SecondaryMenu getSecondaryMenu(HttpServletRequest request)
    {
        return
        (SecondaryMenu) getSessionAttribute(SessionKeys.SECONDARY_MENU,
                                            request);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
    }

    protected void setSecondaryMenu(SecondaryMenu secondaryMenu, HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
    }

    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
    }

    protected boolean isDeleted(HttpServletRequest request)
    {
        String
            submitString =
            (String) request.getAttribute("submit");
        LOG.debug("submitString = "
                  + submitString);
        return submitString
               != null
               && submitString.equalsIgnoreCase("Delete");
    }

    protected void setPortalMenu(HttpServletRequest request)
    {
        PortalMenu
            portalMenu =
            (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU,
                                             request);
        portalMenu.setCurrentItemByCode(CommonConstants.EROSION_CONTROL_MODULE);
    }

    protected void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPortalMenu(request);
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setSecondaryMenuItem(request);
    }

    protected ActionForward handleError(ActionMapping mapping, String messageKey, HttpServletRequest request, Object... params)
    {
        String
            message =
            ApplicationResources.getProperty(messageKey,
                                             params);
        if (message
            == null
            || message.isEmpty())
        {
            message =
                PortalResources.getProperty(messageKey,
                                            params);
        }
        LOG.error(message);
/*
        addError(new ActionMessage(messageKey,
                                   params),
                 request);
*/
        return mapping.findForward("erosioncontrol");
    }
}
