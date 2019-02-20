package com.sehinc.stormwater.action.plan;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class PlanTemplateCreateAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateCreateAction.class);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {

        UserValue
            userValue =
            getUserValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            == null)
        {
            addError(new ActionMessage("error.plan.not.selected"), request);
            return mapping.findForward("plan.template.list.action");
        }
        Integer
            planTemplateId =
            PlanService.savePlanAsTemplate(planValue.getId(),
                                           userValue);
        if (planTemplateId
            == 0)
        {
            addError(new ActionMessage("template.save.fail"), request);
            return mapping.findForward("plan.template.list.action");
        }
        request.setAttribute("id",
                             planTemplateId);
        addMessage(new ActionMessage("template.save.success"), request);
        return new ActionForward("templateview",
                                 "/plantemplateviewaction.do?"
                                 + "id"
                                 + "="
                                 + planTemplateId,
                                 true,
                                 "/html/ms4/template");
    }
}