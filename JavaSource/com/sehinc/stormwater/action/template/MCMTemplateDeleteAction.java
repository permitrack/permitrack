package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMTemplateDeleteAction
    extends MCMTemplateBaseAction
{
    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue =
            getMCMTemplate(request);
        if (mcmValue
            == null)
        {
            addError(new ActionMessage("mcm.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        MCMData
            mcmData =
            PlanService.getMCM(mcmValue.getId());
        if (mcmData
            == null)
        {
            addError(new ActionMessage("mcm.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        Integer
            planId =
            mcmData.getPlanId();
        mcmData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        mcmData.save(userValue);
        setSessionAttribute(SessionKeys.MCM_TEMPLATE,
                            null, request);
        PlanData
            planData =
            PlanService.getPlan(planId);
        if (planData
            != null)
        {
            setPlanTemplateSessionKeys(SessionKeys.PLAN_TEMPLATE,
                                       planData.getId(), request);
            return new ActionForward("/plantemplateviewaction.do?"
                                     + "id"
                                     + "="
                                     + planData.getId(),
                                     true);
        }
        return mapping.findForward("continue");
    }
}