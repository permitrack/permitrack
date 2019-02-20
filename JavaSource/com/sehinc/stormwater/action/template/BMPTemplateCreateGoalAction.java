package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.db.plan.GoalData;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPTemplateCreateGoalAction
    extends BMPTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPTemplateCreateGoalAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        GoalTemplateForm
            goalTemplateForm =
            (GoalTemplateForm) form;
        LOG.info("In BMPTemplateCreateGoalAction");
        UserValue
            userValue =
            getUserValue(request);
        BMPValue
            bmpValue = getBMPTemplate(request);
        GoalData
            goalData =
            new GoalData();
        goalData.setBmpId(bmpValue.getId());
        goalData.setName(goalTemplateForm.getName());
        goalData.setNumber(goalTemplateForm.getNumber());
        goalData.setDescription(goalTemplateForm.getDescription());
        goalData.setGoalActivityFormId(goalTemplateForm.getGoalActivityFormId());
        goalData.setGoalActivityFrequencyId(goalTemplateForm.getGoalActivityFrequencyId());
        goalData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        goalData.save(userValue);
        return mapping.findForward("continue");
    }
}