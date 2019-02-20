package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanCreateMCMAction
    extends PlanBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(PlanCreateMCMAction.class);
*/

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        MCMForm
            mcmForm =
            (MCMForm) form;
//        LOG.info("In PlanCreateMCMAction");
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
        mcmData.setName(mcmForm.getName());
        mcmData.setNumber(mcmForm.getNumber());
        mcmData.setOwnerId(mcmForm.getOwnerId());
        mcmData.setRequiredDescription(mcmForm.getRequiredDescription());
        mcmData.setNecessaryDescription(mcmForm.getNecessaryDescription());
        mcmData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        mcmData.insert(userValue);
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "m"
                                 + mcmData.getId(),
                                 true);

//        return mapping.findForward("continue");
    }
}