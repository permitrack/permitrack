package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityDeleteReasonData;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class GoalActivityDeleteAction
    extends GoalActivityBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityDeleteAction.class);
*/

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            goalActivityDeleteForm =
            (PlanDeleteForm) form;
//        LOG.info("In GoalActivityDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
        GoalActivityValue
            goalActivityValue =
            getGoalActivity(request);
        GoalActivityData
            goalActivityData =
            new GoalActivityData();
        goalActivityData.setId(goalActivityValue.getId());
        goalActivityData.retrieveByPrimaryKeys();
        GoalActivityFileLocationData
            aLocation =
            new GoalActivityFileLocationData();
        aLocation.setGoalActivityId(goalActivityData.getId());
        List
            aFileList =
            GoalActivityFileLocationData.findByGoalActivityId(goalActivityData.getId());
        Iterator
            aFileListIterator =
            aFileList.iterator();
        while (aFileListIterator.hasNext())
        {
            GoalActivityFileLocationData
                lLocation =
                (GoalActivityFileLocationData) aFileListIterator.next();
            File
                aFile =
                new File(lLocation.getLocation()
                         + lLocation.getName());
            aFile.delete();
            lLocation.delete();
        }
        Integer
            goalId =
            goalActivityData.getGoalId();
        goalActivityData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        goalActivityData.save(userValue);
        GoalActivityDeleteReasonData
            goalActivityDeleteReasonData =
            new GoalActivityDeleteReasonData();
        goalActivityDeleteReasonData.setGoalActivityId(goalActivityValue.getId());
        goalActivityDeleteReasonData.setDeleteReason(goalActivityDeleteForm.getDeleteReason());
        goalActivityDeleteReasonData.save(userValue);
        removeSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                            request);
        GoalData
            goalData =
            new GoalData();
        goalData.setId(goalId);
        boolean
            foundGoalData =
            goalData.retrieveByPrimaryKeys();
        if (foundGoalData)
        {
/*
            setSessionAttribute(SessionKeys.GOAL,
                                new GoalValue(goalData));
*/
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "g"
                                     + goalData.getId(),
                                     true);
        }
        else
        {
            return mapping.findForward("plan.list.action");
        }
    }
}