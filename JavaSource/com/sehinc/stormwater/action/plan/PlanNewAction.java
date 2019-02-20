package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PlanNewAction
    extends ClientBaseAction
{
    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        PlanForm
            planForm =
            (PlanForm) form;
        planForm.reset();

        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.DEFAULT_PLAN_MENU_NAME,
                                      getClientValue(request).getId());
        secondaryMenu.setCurrentItem(SecondaryMenu.PLAN_NEW_MENU_ITEM_NAME);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu, request);
    }
}