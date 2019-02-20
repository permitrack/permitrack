package com.sehinc.environment.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.server.navigation.NavigationService;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.resources.PortalResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAction
    extends com.sehinc.common.action.base.BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(com.sehinc.environment.action.base.BaseAction.class);

    public static List getFinalList(List pList)
    {
        List
            finalList =
            new ArrayList();
        for (Object o : pList)
        {
            EnvAsset
                asset =
                (EnvAsset) ((Object[]) o)[0];
            if (asset
                != null)
            {
                asset.setUsageCount(Integer.parseInt(((Object[]) o)[1].toString()));
                finalList.add(asset);
            }
        }
        return finalList;
    }

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

/*
    public ClientValue getClientValue()
    {
        return getClientValue(request);
    }
*/

    public ClientValue getClientValue(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.ENVIRONMENT_MODULE);
        if (clientValue
            == null)
        {
            clientValue =
                getClientValueDefault(CommonConstants.ENVIRONMENT_MODULE, request);
            setSessionAttribute(SessionKeys.ENV_CLIENT,
                                clientValue,
                                request);
        }
        return clientValue;
    }

    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            NavigationService.getPrimaryMenu(request);
        return primaryMenu;
    }

    public SecondaryMenu getSecondaryMenu(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            (SecondaryMenu) getSessionAttribute(SessionKeys.EV_SECONDARY_MENU,
                                                request);
        return secondaryMenu;
    }

    public TertiaryMenu getTertiaryMenu(HttpServletRequest request)
    {
        TertiaryMenu
            tertiaryMenu =
            (TertiaryMenu) getSessionAttribute(SessionKeys.TERTIARY_MENU,
                                               request);
        return tertiaryMenu;
    }

    public void setSecondaryMenu(SecondaryMenu secondaryMenu, HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.EV_SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    public void setTertiaryMenu(TertiaryMenu tertiaryMenu, HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.TERTIARY_MENU,
                            tertiaryMenu,
                            request);
    }

    public boolean isDeleted(HttpServletRequest request)
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
        portalMenu.setCurrentItemByCode(CommonConstants.ENVIRONMENT_MODULE);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
    }

    protected abstract void setSecondaryMenu(HttpServletRequest request)
        throws ServletException;

    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        TertiaryMenu
            tertiaryMenu =
            TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU);
        setTertiaryMenu(tertiaryMenu,
                        request);
    }

    protected Object getFacility(HttpServletRequest request)
    {
        return getSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                   request);
    }

    protected Object getPermit(HttpServletRequest request)
    {
        return getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                   request);
    }

    protected boolean hasPermission(SecureObjectPermissionData permission, HttpServletRequest request)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(permission))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                this.getClass()
                                    .getName(),
                                request);
            return false;
        }
        return true;
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
                                   params), request);
*/
        return mapping.findForward("environment");
    }
}