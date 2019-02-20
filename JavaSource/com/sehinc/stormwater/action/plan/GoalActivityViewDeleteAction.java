package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalActivityViewDeleteAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityViewDeleteAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            goalActivityDeleteForm =
            (PlanDeleteForm) form;
        goalActivityDeleteForm.reset();
        LOG.info("In GoalActivityViewDeleteAction");
        GoalActivityValue
            goalActivityValue =
            getGoalActivity(request);
        GoalActivityData
            goalActivityData =
            new GoalActivityData();
        goalActivityData.setId(goalActivityValue.getId());
        goalActivityData.retrieveByPrimaryKeys();
        return mapping.findForward("continue");
    }
}