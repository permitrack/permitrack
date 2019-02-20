package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalActivityEditAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityEditAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalActivityForm
            goalActivityForm =
            (GoalActivityForm) form;
        GoalActivityValue
            goalActivityValue =
            getGoalActivity(request);
        GoalActivityData
            goalActivityData =
            new GoalActivityData();
        UserValue
            userValue =
            getUserValue(request);
        if (request.getParameter("attach")
            == null)
        {
            goalActivityData.setId(goalActivityValue.getId());
            goalActivityData.retrieveByPrimaryKeys();
            goalActivityData.setName(goalActivityForm.getName());
            goalActivityData.setDescription(goalActivityForm.getDescription());
            goalActivityData.setGoalActivityFormId(GoalActivityFormData.DEFAULT);
            goalActivityData.setSubmitterUserId(goalActivityForm.getSubmitterUserId());
            goalActivityData.setOwnerId(goalActivityForm.getOwnerId());
            goalActivityData.setActivityDate(goalActivityForm.getActivityDate());
            goalActivityData.save(userValue);
            goalActivityValue.setName(goalActivityForm.getName());
        }
        else
        {
            return mapping.findForward("continue");
        }
        String
            documentAttach =
            getResources(request).getMessage("goal.activity.file.upload.button");
        LOG.debug("documentAttach="
                  + documentAttach);
        String
            submit =
            request.getParameter("submit");
        LOG.debug("submit="
                  + submit);
        if (submit
            != null
            && submit.equals(documentAttach))
        {
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "a"
                                     + goalActivityData.getId()
                                     + "?attach",
                                     true);
        }
        else
        {
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "a"
                                     + goalActivityData.getId(),
                                     true);
        }
    }
}