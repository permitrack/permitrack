package com.sehinc.stormwater.action.plan;

import com.sehinc.common.db.forms.FormInstanceData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.GoalActivityFormInstanceData;
import com.sehinc.stormwater.server.plan.GoalService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.GoalActivityFileLocationValue;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoalActivityAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalActivityForm
            goalActivityForm =
            (GoalActivityForm) form;
        goalActivityForm.reset();
        Integer
            goalActivityId =
            0;
        if (request.getParameter(RequestKeys.ACTIVITY_ID)
            != null)
        {
            goalActivityId =
                new Integer(request.getParameter(RequestKeys.ACTIVITY_ID));
        }
        else
        {
            GoalActivityValue
                goalActivityValue =
                getGoalActivity(request);
            if (goalActivityValue
                != null)
            {
                goalActivityId =
                    goalActivityValue.getId();
            }
        }
        if (goalActivityId
            > 0)
        {
            GoalActivityData
                goalActivityData =
                PlanService.getGoalActivity(goalActivityId);
            if (goalActivityData
                != null)
            {
                if (setPlanSessionKeys(SessionKeys.GOAL_ACTIVITY,
                                       goalActivityId, request))
                {
                    request.setAttribute(RequestKeys.PLAN_ID,
                                         getPlanValue(request).getId());
                    request.setAttribute(RequestKeys.CLIENT_ID,
                                         getClientValue(request).getId());
                    List
                        goalActivityFormInstList =
                        GoalService.getGoalActivityFormInstance(goalActivityId);
                    Iterator
                        i =
                        goalActivityFormInstList.iterator();
                    if (i.hasNext())
                    {
                        GoalActivityFormInstanceData
                            goalActivityFormInst =
                            (GoalActivityFormInstanceData) i.next();
                        FormInstanceData
                            formInstance =
                            goalActivityFormInst.getFormInstance();
                        Integer
                            formInstanceId =
                            formInstance.getId();
                        if (formInstanceId
                            != null)
                        {
                            /*
                                                LOG.debug("Forwarding to custom. PlanId: "
                                                          + planValue.getId()
                                                    .toString()
                                                          +
                                                          " ClientId: "
                                                          + clientValue.getId()
                                                    .toString()
                                                          +
                                                          " ActivityId: "
                                                          + goalActivityId.toString());
                            */
                            request.setAttribute(RequestKeys.FORM_INSTANCE_ID,
                                                 formInstanceId);
                            request.setAttribute(RequestKeys.ACTIVITY_ID,
                                                 goalActivityId);
                            return mapping.findForward("custom");
                        }
                    }
                    Integer
                        ownerId =
                        goalActivityData.getOwnerId();
                    if (ownerId
                        != null
                        && ownerId
                           != 0)
                    {
                        UserData
                            userData =
                            new UserData();
                        userData.setId(goalActivityData.getOwnerId());
                        userData.load();
                        goalActivityForm.setOwner(userData.getName());
                    }
                    goalActivityForm.setId(goalActivityData.getId());
                    goalActivityForm.setGoalId(goalActivityData.getGoalId());
                    goalActivityForm.setName(goalActivityData.getName());
                    goalActivityForm.setDescription(goalActivityData.getDescription());
                    goalActivityForm.setSubmitterUserId(goalActivityData.getSubmitterUserId());
                    goalActivityForm.setActivityDate(goalActivityData.getActivityDate());
                    try
                    {
                        Iterator
                            fileListIter =
                            GoalActivityFileLocationData.findActiveByGoalActivityId(goalActivityData.getId())
                                .iterator();
                        List
                            aFileList =
                            new ArrayList();
                        ClientValue
                            clientValue =
                            getClientValue(request);
                        UserValue
                            userValue =
                            getUserValue(request);
                        while (fileListIter.hasNext())
                        {
                            GoalActivityFileLocationValue
                                locationValue =
                                new GoalActivityFileLocationValue((GoalActivityFileLocationData) fileListIter.next());
                            locationValue.setClientId(clientValue.getId());
                            locationValue.setUsername(userValue.getUsername());
                            locationValue.setDownloadURL(locationValue.getDownloadURL(request));
                            aFileList.add(locationValue);
                        }
                        setRequestAttribute(SessionKeys.FILE_LIST,
                                            aFileList,
                                            request);
                    }
                    catch (Exception e)
                    {
                        LOG.error("Error creating file locations for GoalActivityData id: "
                                  + goalActivityData.getId(),
                                  e);
                    }
                    return mapping.findForward("default");
                }
            }
        }
        addError(new ActionMessage("goalactivity.error"), request);
        if(getGoal(request) == null)
        {
            return mapping.findForward("plan.list.action");
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "g"
                                 + getGoal(request).getId(),
                                 true);
    }
}