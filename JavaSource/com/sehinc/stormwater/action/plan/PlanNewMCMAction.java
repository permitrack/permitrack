package com.sehinc.stormwater.action.plan;

import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class PlanNewMCMAction
    extends PlanBaseAction
{
    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        MCMForm
            mcmForm =
            (MCMForm) form;
        if (request.getParameter("valid") != null && request.getParameter("valid")
            .equals("false"))
        {
        }
        else
        {
            mcmForm.reset();
        }
        PlanValue
            planValue =
            getPlanValue(request);
        PlanData
            planData =
            new PlanData();
        planData.setId(planValue.getId());
        if (!planData.retrieveByPrimaryKeysAlternate())
        {
            return mapping.findForward("plan.list.action");
        }
        UserValue
            user =
            getUser(request);
        mcmForm.setNumber(PlanService.getMCMCount(planData.getId())
                          + 1);
        try
        {
            Iterator
                userValueList =
                UserService.getUserValueList(getClientValue(request))
                    .iterator();
            /* Default 'Owner' to current user only if current user is part of Client Users */
            while (userValueList.hasNext())
            {
                UserValue
                    userValue =
                    (UserValue) userValueList.next();
                if (userValue.getId()
                    .equals(user.getId()))
                {
                    mcmForm.setOwnerId(user.getId());
                    break;
                }
            }
        }
        catch (Exception e)
        {
        }
        removeSessionAttribute(SessionKeys.MCM,
                               request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.PLAN_VIEW_MENU_ITEM_NAME);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }
}