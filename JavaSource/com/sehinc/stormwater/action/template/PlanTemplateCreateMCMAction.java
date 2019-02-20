package com.sehinc.stormwater.action.template;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanTemplateCreateMCMAction
    extends PlanTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateCreateMCMAction.class);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        MCMTemplateForm
            mcmTemplateForm =
            (MCMTemplateForm) form;
        LOG.info("In PlanTemplateCreateMCMAction");
        UserValue
            userValue =
            getUserValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setPlanId(planValue.getId());
        mcmData.setName(mcmTemplateForm.getName());
        mcmData.setNumber(mcmTemplateForm.getNumber());
        mcmData.setRequiredDescription(mcmTemplateForm.getRequiredDescription());
        mcmData.setNecessaryDescription(mcmTemplateForm.getNecessaryDescription());
        mcmData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        mcmData.insert(userValue);
        return mapping.findForward("continue");
    }
}