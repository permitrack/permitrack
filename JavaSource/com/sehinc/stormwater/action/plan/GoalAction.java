package com.sehinc.stormwater.action.plan;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.*;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalAction
    extends GoalBaseAction
{
    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalForm
            goalForm =
            (GoalForm) form;
        goalForm.reset();
        Integer
            goalId =
            0;
        if (request.getParameter(RequestKeys.GOAL_ID)
            != null)
        {
            goalId =
                new Integer(request.getParameter(RequestKeys.GOAL_ID));
        }
        else
        {
            GoalValue
                goalValue =
                getGoal(request);
            if (goalValue
                != null)
            {
                goalId =
                    goalValue.getId();
            }
        }
        if (goalId
            > 0)
        {
            GoalData
                goalData;
            goalData =
                new GoalData();
            goalData.setId(goalId);
            if (goalData.retrieveByPrimaryKeysAlternate())
            {
                if (setPlanSessionKeys(SessionKeys.GOAL,
                                       goalData.getId(), request))
                {
                    UserData
                        userData =
                        new UserData();
                    userData.setId(goalData.getOwnerId());
                    if (goalData.getOwnerId()
                        != null
                        && goalData.getOwnerId()
                           > 0
                        && userData.retrieveByPrimaryKeysAlternate())
                    {
                        goalForm.setOwner(userData.getName());
                        goalForm.setOwnerId(goalData.getOwnerId());
                    }
                    GoalActivityFrequencyData
                        goalActivityFrequencyData =
                        GoalActivityFrequencyData.getInstance(goalData.getGoalActivityFrequencyId());
                    if (goalActivityFrequencyData
                        != null)
                    {
                        goalForm.setGoalActivityFrequency(goalActivityFrequencyData.getName());
                    }
                    GoalActivityFormData
                        goalActivityFormData =
                        new GoalActivityFormData();
                    goalActivityFormData.setId(goalData.getGoalActivityFormId());
                    if (goalActivityFormData.retrieveByPrimaryKeys())
                    {
                        goalForm.setGoalActivityForm(goalActivityFormData.getName());
                    }
                    PlanData
                        planData =
                        getPlanData(request);
                    BMPData
                        bmpData =
                        PlanService.getBMP(goalData.getBmpId());
                    if (bmpData
                        != null)
                    {
                        goalForm.setBmpIdentifier(bmpData.getIdentifier());
                        MCMData
                            mcmData =
                            PlanService.getMCM(bmpData.getMcmId());
                        if (mcmData
                            != null)
                        {
                            goalForm.setMcmNumber(mcmData.getNumber());
                        }
                        goalForm.setBmpIdentifier(planData.getPermitType()
                                                      .getBMPFormatter()
                                                      .formatBMPLongIdentifier(planData,
                                                                               mcmData,
                                                                               bmpData));
                    }
                    goalForm.setId(goalData.getId());
                    goalForm.setBMPId(goalData.getBmpId());
                    goalForm.setName(goalData.getName());
                    goalForm.setNumber(goalData.getNumber());
                    goalForm.setDescription(goalData.getDescription());
                    goalForm.setGoalActivityFormId(goalData.getGoalActivityFormId());
                    goalForm.setGoalActivityFrequencyId(goalData.getGoalActivityFrequencyId());
                    goalForm.setGoalDate(goalData.getGoalDate());
                    goalForm.setNotify(goalData.isNotify());
                    goalForm.setNotifyDaysInAdvance(goalData.getNotifyDaysInAdvance());
                    goalForm.setGoalPermitTimePeriods(PermitPeriodService.getGoalPermitTimePeriodValues(goalData.getId()));
                    return mapping.findForward("continue");
                }
            }
        }
        addError(new ActionMessage("bmpdb.goal.edit.error.goalNotFoundInSession"), request);
        if(getBMP(request) == null)
        {
            return mapping.findForward("plan.list.action");
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "b"
                                 + getBMP(request).getId(),
                                 true);
    }
}