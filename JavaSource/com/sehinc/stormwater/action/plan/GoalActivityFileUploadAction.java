package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.value.plan.GoalActivityValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoalActivityFileUploadAction
    extends GoalActivityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityFileUploadAction.class);

    public ActionForward goalActivityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
//        LOG.info("In GoalActivityFileUploadAction");
//        LOG.debug("In GoalActivityFileUploadAction");
        GoalActivityForm
            goalActivityForm =
            (GoalActivityForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        UserValue
            userValue =
            getUserValue(request);
        GoalActivityValue
            goalActivityValue =
            getGoalActivity(request);
        FormFile
            aFormFile1 =
            goalActivityForm.getAttachFile1();
        FormFile
            aFormFile2 =
            goalActivityForm.getAttachFile2();
        FormFile
            aFormFile3 =
            goalActivityForm.getAttachFile3();
        try
        {
            if (aFormFile1
                != null
                && !aFormFile1.getFileName()
                .equals(""))
            {
                GoalCreateGoalActivityAction.processFormFile(aFormFile1,
                                                             clientValue.getId(),
                                                             planValue.getId(),
                                                             goalActivityValue.getId(),
                                                             userValue);
                aFormFile1.destroy();
            }
            if (aFormFile2
                != null
                && !aFormFile2.getFileName()
                .equals(""))
            {
                GoalCreateGoalActivityAction.processFormFile(aFormFile2,
                                                             clientValue.getId(),
                                                             planValue.getId(),
                                                             goalActivityValue.getId(),
                                                             userValue);
                aFormFile2.destroy();
            }
            if (aFormFile3
                != null
                && !aFormFile3.getFileName()
                .equals(""))
            {
                GoalCreateGoalActivityAction.processFormFile(aFormFile3,
                                                             clientValue.getId(),
                                                             planValue.getId(),
                                                             goalActivityValue.getId(),
                                                             userValue);
                aFormFile3.destroy();
            }
        }
        catch (Exception e)
        {
            LOG.error("Error processing files",
                      e);
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "a"
                                 + goalActivityValue.getId(),
                                 true);
    }
}