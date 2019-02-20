package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.MCMDeleteReasonData;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMDeleteAction
    extends MCMBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(MCMDeleteAction.class);
*/

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        PlanDeleteForm
            mcmDeleteForm =
            (PlanDeleteForm) form;
//        LOG.info("In MCMDeleteAction");
        UserValue
            userValue =
            getUserValue(request);
        MCMValue
            mcmValue =
            getMCMValue(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        mcmData.load();
        Integer
            planId =
            mcmData.getPlanId();
        mcmData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        mcmData.save(userValue);
        MCMDeleteReasonData
            mcmDeleteReasonData =
            new MCMDeleteReasonData();
        mcmDeleteReasonData.setMcmId(mcmValue.getId());
        mcmDeleteReasonData.setDeleteReason(mcmDeleteForm.getDeleteReason());
        mcmDeleteReasonData.save(userValue);
        removeSessionAttribute(SessionKeys.MCM,
                            request);
        PlanData
            planData =
            new PlanData();
        planData.setId(planId);
        boolean
            foundPlanData =
            planData.retrieveByPrimaryKeys();
        if (foundPlanData)
        {
/*
            setSessionAttribute(SessionKeys.PLAN,
                                new PlanValue(planData));
*/
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "p"
                                     + planData.getId(),
                                     true);
        }
        else
        {
            return mapping.findForward("plan.list.action");
        }
    }
}