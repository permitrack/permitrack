package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanTemplateDeleteAction
    extends PlanTemplateBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateDeleteAction.class);
*/

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
//        LOG.info("In PlanTemplateDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            == null)
        {
            addError(new ActionMessage("error.plan.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        if (planData
            == null)
        {
            addError(new ActionMessage("plan.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        planData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        planData.save(userValue);
        setSessionAttribute(SessionKeys.PLAN_TEMPLATE,
                            null, request);
/*
        setSessionAttribute(SessionKeys.PLAN_TEMPLATE_HIERARCHY,
                            null);
*/
        return mapping.findForward("continue");
    }
}