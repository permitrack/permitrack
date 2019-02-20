package com.sehinc.stormwater.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.navigation.NavigationService;
import com.sehinc.stormwater.value.plan.PlanValue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAction
    extends com.sehinc.common.action.base.BaseAction
{
/*
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return super.execute(mapping,
                             form,
                             request,
                             response);
    }
*/

/*
    public UserValue getUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request);
    }
*/

    protected static UserValue getUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request);
    }

/*
    public PrimaryMenu getPrimaryMenu()
    {
        return getPrimaryMenu(this.request);
    }
*/

    protected static PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        return NavigationService.getPrimaryMenu(request);
    }

    protected void setTertiaryMenu(TertiaryMenu tertiaryMenu, HttpServletRequest request)
    {
        setSessionAttribute(SessionKeys.TERTIARY_MENU,
                            tertiaryMenu,
                            request);
    }

    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }

    protected void setTreeMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
    }

    protected PlanPermitType getPermitType(HttpServletRequest request)
    {
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            != null
            && planValue.getPermitType()
            .equals(PlanPermitType.OTHER))
        {
            return PlanPermitType.OTHER;
        }
        else
        {
            return PlanPermitType.MPCA;
        }
    }

/*
    protected PlanValue getPlanValue()
    {
        return getPlanValue(request);
    }
*/

    protected PlanValue getPlanValue(HttpServletRequest request)
    {
        return (PlanValue) getSessionAttribute(SessionKeys.PLAN,
                                               request);
    }

    protected void setPortalMenu(HttpServletRequest request)
    {
        PortalMenu
            portalMenu =
            (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU,
                                             request);
        portalMenu.setCurrentItemByCode(CommonConstants.STORM_WATER_MODULE);
    }

    protected ClientValue getClientValue(HttpServletRequest request)
    {
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.STORM_WATER_MODULE);
        return clientValue;
    }

/*
    protected ClientValue getClientValue()
    {
        return getClientValue(request);
    }
*/

/*
    protected SecurityManager getSecurityManager()
        throws Exception
    {
        return this.getSecurityManager(request);
    }
*/

/*
    protected void clearClientSessionKeys()
    {
        setSessionAttribute(SessionKeys.PLAN,
                            null);
        setSessionAttribute(SessionKeys.MCM,
                            null);
        setSessionAttribute(SessionKeys.BMP,
                            null);
        setSessionAttribute(SessionKeys.GOAL,
                            null);
        setSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                            null);
        setSessionAttribute(SessionKeys.PLAN_HIERARCHY,
                            null);
        setSessionAttribute(SessionKeys.USER_HIERARCHY,
                            null);
        setSessionAttribute(SessionKeys.REPORT_PLAN,
                            null);
        setSessionAttribute(SessionKeys.REPORT_QUERY,
                            null);
        setSessionAttribute(SessionKeys.REPORT_SHOW_ACTIVITY,
                            null);
        setSessionAttribute(SessionKeys.REPORT_MCM_NUMBER,
                            null);
    }
*/

    protected SecondaryMenu getSecondaryMenu(HttpServletRequest request)
    {
        SecondaryMenu
            secondaryMenu =
            (SecondaryMenu) getSessionAttribute(SessionKeys.SECONDARY_MENU,
                                                request);
        return secondaryMenu;
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
    }

    @Override
    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPortalMenu(request);
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }
}
