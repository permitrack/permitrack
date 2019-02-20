package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.server.plan.GoalService;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalEditAction
    extends GoalBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalEditAction.class);

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalForm
            goalForm =
            (GoalForm) form;
        UserValue
            userValue =
            getUserValue(request);
        GoalValue
            goalValue = getGoal(request);

        GoalData
            goalData =
            new GoalData();
        goalData.setId(goalValue.getId());
        goalData.retrieveByPrimaryKeys();
        goalData.setName(goalForm.getName());
        goalData.setNumber(goalForm.getNumber());
        goalData.setDescription(goalForm.getDescription());
        goalData.setGoalActivityFormId(goalForm.getGoalActivityFormId());
        goalData.setGoalActivityFrequencyId(goalForm.getGoalActivityFrequencyId());
        if (goalForm.getGoalActivityFrequencyId()
            .equals(GoalActivityFrequencyData.DATE
                        .getId()))
        {
            goalData.setGoalDate(goalForm.getGoalDate());
        }
        else
        {
            goalData.setGoalDate(null);
        }
        goalData.setOwnerId(goalForm.getOwnerId());
        goalData.setNotify(goalForm.isNotify());
        goalData.setNotifyDaysInAdvance(goalForm.getNotifyDaysInAdvance());
        goalData.save(userValue);
        GoalService.processPermitPeriodCheckbox(request,
                                                goalValue);
        goalValue.setName(goalForm.getName());
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "g"
                                 + goalData.getId(),
                                 true);
//        return mapping.findForward("continue");
    }
}