package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMTemplateAction
    extends MCMTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMTemplateAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        MCMTemplateForm
            mcmTemplateForm =
            (MCMTemplateForm) form;
        mcmTemplateForm.reset();
        Integer
            mcmId =
            getMcmId(request);
        if (mcmId
            == null)
        {
            addError(new ActionMessage("mcm.error.not.found.in.session"), request);
            return mapping.findForward("plan.template.list.action");
        }
        MCMData
            mcmData =
            PlanService.getMCM(mcmId);
        if (mcmData
            != null)
        {
            LOG.info("Setting values on mcmTemplateForm");
            mcmTemplateForm.setId(mcmData.getId());
            mcmTemplateForm.setPlanId(mcmData.getPlanId());
            mcmTemplateForm.setName(mcmData.getName());
            mcmTemplateForm.setNumber(mcmData.getNumber());
            mcmTemplateForm.setRequiredDescription(mcmData.getRequiredDescription());
            PlanPermitType
                planPermiType =
                PlanService.getPlanPermitTypeByPlanId(mcmTemplateForm.getPlanId());
            request.setAttribute("permitTypeId",
                                 planPermiType
                                 != null
                                     ? planPermiType.getValue()
                                     : "1");
        }
        else
        {
            addError(new ActionMessage("mcm.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        setPlanTemplateSessionKeys(SessionKeys.MCM_TEMPLATE,
                                   mcmData.getId(), request);
        return mapping.findForward("continue");
    }

    protected Integer getMcmId(HttpServletRequest request)
    {
        Integer
            mcmId =
            null;
        if (request.getParameter(RequestKeys.MCM_ID)
            != null)
        {
            mcmId =
                new Integer(request.getParameter(RequestKeys.MCM_ID));
        }
        else
        {
            MCMValue
                mcmValue =
                getMCMTemplate(request);
            if (mcmValue
                != null)
            {
                mcmId =
                    mcmValue.getId();
            }
        }
        return mcmId;
    }
}