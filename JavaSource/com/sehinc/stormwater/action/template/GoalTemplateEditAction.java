package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalTemplateEditAction
    extends GoalTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalTemplateEditAction.class);

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalTemplateForm
            goalTemplateForm =
            (GoalTemplateForm) form;
        UserValue
            userValue =
            getUserValue(request);
        GoalValue
            goalValue =
            (GoalValue) getSessionAttribute(SessionKeys.GOAL_TEMPLATE, request);
        GoalData
            goalData =
            new GoalData();
        goalData.setId(goalValue.getId());
        goalData.retrieveByPrimaryKeys();
        goalData.setName(goalTemplateForm.getName());
        goalData.setNumber(goalTemplateForm.getNumber());
        goalData.setDescription(goalTemplateForm.getDescription());
        goalData.setGoalActivityFormId(goalTemplateForm.getGoalActivityFormId());
        goalData.setGoalActivityFrequencyId(goalTemplateForm.getGoalActivityFrequencyId());
        goalData.save(userValue);
        goalValue.setName(goalTemplateForm.getName());
        return mapping.findForward("continue");
    }
}