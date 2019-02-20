package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanCreateAction
    extends PlanBaseAction
{
    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanForm
            planForm =
            (PlanForm) form;

        String
            planType =
            request.getParameter("type");
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        PlanData
            planData =
            new PlanData();
        planData.setClientId(clientValue.getId());
        planData.setName(planForm.getName());
        planData.setDescription(planForm.getDescription());
        planData.setPermitNumber(planForm.getPermitNumber());
        planData.setPermitPeriodId(planForm.getPermitPeriodId());
        planData.setPermitTypeId(planForm.getPermitTypeId());
        planData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        planData.setTemplate(false);
        planData.setCmomProgram(false);
        Integer
            newPlanId =
            planData.insert(userValue);
        if (planType.equals("default"))
        {
            PlanService.copyPlan(PlanData.DEFAULT_PLAN_ID,
                                 newPlanId,
                                 userValue);
        }
        else if (planType.equals("template"))
        {
            PlanService.copyPlan(planForm.getId(),
                                 newPlanId,
                                 userValue);
        }
        setSessionAttribute(SessionKeys.PLAN,
                            new PlanValue(planData), request);

        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "p"
                                 + planData.getId(),
                                 true);

//        return mapping.findForward("continue");
    }
}