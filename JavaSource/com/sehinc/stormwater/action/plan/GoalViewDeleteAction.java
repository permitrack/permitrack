package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalViewDeleteAction
    extends GoalBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalViewDeleteAction.class);

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            goalDeleteForm =
            (PlanDeleteForm) form;
        goalDeleteForm.reset();
        LOG.info("In GoalViewDeleteAction");
        GoalValue
            goalValue =
            getGoal(request);
        GoalData
            goalData =
            new GoalData();
        goalData.setId(goalValue.getId());
        goalData.retrieveByPrimaryKeys();
        return mapping.findForward("continue");
    }
}