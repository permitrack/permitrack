package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalTemplateDeleteAction
    extends GoalTemplateBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(GoalTemplateDeleteAction.class);
*/

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
//        LOG.info("In GoalTemplateDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
        GoalValue
            goalValue = getGoalTemplate(request);
        if (goalValue
            == null)
        {
            addError(new ActionMessage("goal.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        GoalData
            goalData =
            PlanService.getGoal(goalValue.getId());
        if (goalData
            == null)
        {
            addError(new ActionMessage("goal.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        Integer
            bmpId =
            goalData.getBmpId();
        goalData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        goalData.save(userValue);
        setSessionAttribute(SessionKeys.GOAL_TEMPLATE,
                            null, request);
        BMPData
            bmpData =
            PlanService.getBMP(bmpId);
        if (bmpData
            != null)
        {
            setPlanTemplateSessionKeys(SessionKeys.BMP_TEMPLATE,
                                       bmpData.getId(), request);
            return new ActionForward("/bmptemplateviewaction.do?"
                                     + "id"
                                     + "="
                                     + bmpData.getId(),
                                     true);
        }
        return mapping.findForward("continue");
    }
}