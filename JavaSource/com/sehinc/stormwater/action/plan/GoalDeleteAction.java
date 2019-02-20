package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.db.plan.GoalDeleteReasonData;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalDeleteAction
    extends GoalBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(GoalDeleteAction.class);
*/

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            goalDeleteForm =
            (PlanDeleteForm) form;
//        LOG.info("In GoalDeleteAction");
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
        Integer
            bmpId =
            goalData.getBmpId();
        goalData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        goalData.save(userValue);
        GoalDeleteReasonData
            goalDeleteReasonData =
            new GoalDeleteReasonData();
        goalDeleteReasonData.setGoalId(goalValue.getId());
        goalDeleteReasonData.setDeleteReason(goalDeleteForm.getDeleteReason());
        goalDeleteReasonData.save(userValue);
        removeSessionAttribute(SessionKeys.GOAL,
                            request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpId);
        boolean
            foundBmpData =
            bmpData.retrieveByPrimaryKeys();
        if (foundBmpData)
        {
/*
            setSessionAttribute(SessionKeys.BMP,
                                new BMPValue(bmpData));
*/
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "b"
                                     + bmpData.getId(),
                                     true);
        }
        else
        {
            return mapping.findForward("plan.list.action");
        }
    }
}