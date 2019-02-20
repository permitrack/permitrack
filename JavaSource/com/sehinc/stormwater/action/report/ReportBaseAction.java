package com.sehinc.stormwater.action.report;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.plan.PlanBaseAction;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReportBaseAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportBaseAction.class);

    protected abstract ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        return reportAction(mapping,
                            form,
                            request,
                            response);
    }

    private void setSelectedMenuItem(SecondaryMenu secondaryMenu)
    {
        try
        {
            secondaryMenu.setCurrentItem(SecondaryMenu.CLIENT_REPORT_MENU_ITEM_NAME);
        }
        catch (Exception e)
        {
            LOG.error(e.getLocalizedMessage());
        }
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        PlanValue
            planValue =
            getPlanValue(request);
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.PLAN_MENU_NAME,
                                      planValue.getId());
        setSelectedMenuItem(secondaryMenu);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu, request);
    }
}