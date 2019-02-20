package com.sehinc.stormwater.action.plan;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.user.User;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.GoalActivityData;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import com.sehinc.stormwater.value.plan.GoalValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class GoalCreateGoalActivityAction
    extends GoalBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(GoalCreateGoalActivityAction.class);
*/

    public ActionForward goalAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalActivityForm
            goalActivityForm =
            (GoalActivityForm) form;
/*
        LOG.info("In GoalCreateGoalActivityAction");
        ClientValue
            clientValue =
            getClientValue(request);
        PlanValue
            planValue =
            getPlanValue();
*/
        UserValue
            userValue =
            getUserValue(request);
        GoalValue
            goalValue = getGoal(request);
        GoalActivityData
            goalActivityData =
            new GoalActivityData();
        goalActivityData.setGoalId(goalValue.getId());
        goalActivityData.setName(goalActivityForm.getName());
        goalActivityData.setDescription(goalActivityForm.getDescription());
        goalActivityData.setSubmitterUserId(goalActivityForm.getSubmitterUserId());
        goalActivityData.setOwnerId(goalActivityForm.getOwnerId());
        goalActivityData.setActivityDate(goalActivityForm.getActivityDate());
        goalActivityData.setGoalActivityFormId(GoalActivityFormData.DEFAULT);
        goalActivityData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        goalActivityData.save(userValue);
        setSessionAttribute(SessionKeys.GOAL_ACTIVITY,
                            new GoalActivityValue(goalActivityData), request);
        String
            documentAttach =
            getResources(request).getMessage("goal.activity.file.upload.button");
        String
            submit =
            request.getParameter("submit");
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
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "a"
                                 + goalActivityData.getId(),
                                 true);
    }

    protected static Integer processFormFile(FormFile pFile, Integer pClientId, Integer pPlanId, Integer pGoalActivityId, User userValue)
        throws Exception
    {
        InputStream
            stream =
            pFile.getInputStream();
        String
            aFileLocation =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + pClientId
            + "/"
            + ApplicationProperties.getProperty("application.stormwater")
            + "/plan"
            + pPlanId
            + "/activity"
            + pGoalActivityId;
        File
            aOutputDir =
            new File(aFileLocation);
        aOutputDir.mkdirs();
        File
            aNewFile =
            new File(aFileLocation,
                     pFile.getFileName());
        OutputStream
            bos =
            new FileOutputStream(aNewFile);
        int
            bytesRead;
        byte[]
            buffer =
            new byte[8192];
        while ((
                   bytesRead =
                       stream.read(buffer,
                                   0,
                                   8192))
               != -1)
        {
            bos.write(buffer,
                      0,
                      bytesRead);
        }
        bos.close();
        stream.close();
        GoalActivityFileLocationData
            aLocation =
            new GoalActivityFileLocationData();
        aLocation.setGoalActivityId(pGoalActivityId);
        aLocation.setLocation(aNewFile.getParent()
                              + System.getProperty("file.separator"));
        aLocation.setName(aNewFile.getName());
        aLocation.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        return aLocation.save(userValue);
    }
}