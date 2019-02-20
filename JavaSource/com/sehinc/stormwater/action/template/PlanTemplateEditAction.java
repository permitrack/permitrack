package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanTemplateEditAction
    extends PlanTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateEditAction.class);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanTemplateForm
            planTemplateForm =
            (PlanTemplateForm) form;
        UserValue
            userValue =
            getUserValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        PlanData
            planData =
            new PlanData();
        planData.setId(planValue.getId());
        planData.load();
/*
        if (!planData.getName()
            .equals(planTemplateForm.getName()))
        {
            setSessionAttribute(SessionKeys.PLAN_TEMPLATE_HIERARCHY,
                                null);
        }
*/
        planData.setName(planTemplateForm.getName());
        planData.setDescription(planTemplateForm.getDescription());
        planData.setPermitTypeId(planTemplateForm.getPermitTypeId());
        planData.save(userValue);
        setSessionAttribute(SessionKeys.PLAN_TEMPLATE,
                            new PlanValue(planData), request);
        return mapping.findForward("continue");
    }
}