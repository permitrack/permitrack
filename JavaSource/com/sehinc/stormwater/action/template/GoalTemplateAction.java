package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.GoalValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalTemplateAction
    extends GoalTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalTemplateAction.class);

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalTemplateForm
            goalTemplateForm =
            (GoalTemplateForm) form;
        goalTemplateForm.reset();

        PlanValue
            planValue =
            getPlanValue(request);
        PlanData
            planData =
            PlanService.getPlan(planValue);
        Integer
            goalId =
            null;
        if (request.getParameter(RequestKeys.GOAL_ID)
            != null)
        {
            goalId =
                new Integer(request.getParameter(RequestKeys.GOAL_ID));
        }
        else
        {
            GoalValue
                goalValue = getGoalTemplate(request);
            if (goalValue
                != null)
            {
                goalId =
                    goalValue.getId();
            }
        }
        if (goalId
            == null)
        {
            addError(new ActionMessage("goal.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        GoalData
            goalData =
            PlanService.getGoal(goalId);
        if (goalData
            != null)
        {
            BMPData
                bmpData =
                PlanService.getBMP(goalData.getBmpId());
            if (bmpData
                != null)
            {
                goalTemplateForm.setBmpIdentifier(bmpData.getIdentifier());
                MCMData
                    mcmData =
                    PlanService.getMCM(bmpData.getMcmId());
                if (mcmData
                    != null)
                {
                    goalTemplateForm.setMcmNumber(mcmData.getNumber());
                }
                goalTemplateForm.setBmpIdentifier(planData.getPermitType()
                                                      .getBMPFormatter()
                                                      .formatBMPLongIdentifier(planData,
                                                                               mcmData,
                                                                               bmpData));
            }
            goalTemplateForm.setId(goalData.getId());
            goalTemplateForm.setBMPId(goalData.getBmpId());
            goalTemplateForm.setName(goalData.getName());
            goalTemplateForm.setNumber(goalData.getNumber());
            goalTemplateForm.setDescription(goalData.getDescription());
            goalTemplateForm.setGoalActivityFormId(goalData.getGoalActivityFormId());
            goalTemplateForm.setGoalActivityFrequencyId(goalData.getGoalActivityFrequencyId());
        }
        else
        {
            addError(new ActionMessage("goal.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        setPlanTemplateSessionKeys(SessionKeys.GOAL_TEMPLATE,
                                   goalData.getId(), request);
        return mapping.findForward("continue");
    }
}