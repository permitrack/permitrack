package com.sehinc.stormwater.action.plan;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalNewGoalActivityAction
    extends GoalBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalNewGoalActivityAction.class);

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalActivityForm
            goalActivityForm =
            (GoalActivityForm) form;
        goalActivityForm.reset();
        LOG.info("In GoalNewGoalActivityAction");
        GoalValue
            goalValue =
            getGoal(request);
        PlanValue
            planValue =
            getPlanValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        GoalData
            goalData;
        goalData =
            new GoalData();
        goalData.setId(goalValue.getId());
        goalData.retrieveByPrimaryKeys();
        Integer
            goalActivityFormId =
            goalData.getGoalActivityFormId();
        GoalActivityFormData
            goalActivityFormData;
        goalActivityFormData =
            new GoalActivityFormData();
        goalActivityFormData.setId(goalActivityFormId);
        goalActivityFormData.retrieveByPrimaryKeys();
        request.setAttribute(RequestKeys.PLAN_ID,
                             planValue.getId());
        request.setAttribute(RequestKeys.CLIENT_ID,
                             clientValue.getId());
        if (goalActivityFormData.getCustomFormId()
            != null)
        {
            request.setAttribute(RequestKeys.FORM_ID,
                                 goalActivityFormData.getCustomFormId());
            request.setAttribute(RequestKeys.GOAL_ID,
                                 goalData.getId());
            request.setAttribute(RequestKeys.GOAL_ACTIVITY_FORM_ID,
                                 goalActivityFormData.getId());
            return mapping.findForward("custom");
        }
        return mapping.findForward("default");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }

}