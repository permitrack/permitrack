package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.server.plan.GoalService;
import com.sehinc.stormwater.value.plan.BMPValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPCreateGoalAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPCreateGoalAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalForm
            goalForm =
            (GoalForm) form;
        LOG.info("In BMPCreateGoalAction");
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue = getBMP(request);
        GoalData
            goalData =
            new GoalData();
        goalData.setBmpId(bmpValue.getId());
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
        goalData.setNotify(goalForm.isNotify());
        goalData.setNotifyDaysInAdvance(goalForm.getNotifyDaysInAdvance());
        goalData.setOwnerId(goalForm.getOwnerId());
        goalData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        goalData.save(userValue);
        GoalValue
            goalValue =
            new GoalValue(goalData);
        GoalService.processPermitPeriodCheckbox(request,
                                                goalValue);
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "g"
                                 + goalData.getId(),
                                 true);
//        return mapping.findForward("continue");
    }
}