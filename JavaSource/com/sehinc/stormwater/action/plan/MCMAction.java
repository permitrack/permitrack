package com.sehinc.stormwater.action.plan;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMAction
    extends MCMBaseAction
{
    /*
        private static
        Logger
            LOG =
            Logger.getLogger(MCMAction.class);
    */

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        MCMForm
            mcmForm =
            (MCMForm) form;
        mcmForm.reset();
        //        LOG.info("In MCMAction");
        Integer
            mcmId = 0;
        if (request.getParameter(RequestKeys.MCM_ID)
            != null)
        {
            mcmId =
                new Integer(request.getParameter(RequestKeys.MCM_ID));
        }
        else
        {
            MCMValue
                mcmValue = getMCMValue(request);
            if(mcmValue != null)
            {
            mcmId =
                mcmValue.getId();
            }
        }

        if(mcmId > 0)
        {

            MCMData
                mcmData =
                new MCMData();
            mcmData.setId(mcmId);
            if (mcmData.retrieveByPrimaryKeys())
            {
                if (setPlanSessionKeys(SessionKeys.MCM,
                                       mcmId, request))
                {
                    UserData
                        userData =
                        new UserData();
                    userData.setId(mcmData.getOwnerId());
                    if (mcmData.getOwnerId()
                        != null
                        && mcmData.getOwnerId()
                           > 0
                        && userData.retrieveByPrimaryKeysAlternate())
                    {
                        mcmForm.setOwner(userData.getName());
                        mcmForm.setOwnerId(mcmData.getOwnerId());
                    }
                    mcmForm.setId(mcmData.getId());
                    mcmForm.setPlanId(mcmData.getPlanId());
                    mcmForm.setName(mcmData.getName());
                    mcmForm.setNumber(mcmData.getNumber());
                    mcmForm.setRequiredDescription(mcmData.getRequiredDescription());
                    PlanPermitType
                        planPermiType =
                        PlanService.getPlanPermitTypeByPlanId(mcmForm.getPlanId());
                    request.setAttribute("permitTypeId",
                                         planPermiType
                                         != null
                                             ? planPermiType.getValue()
                                             : "1");
                    return mapping.findForward("continue");
                }
            }
        }
        addError(new ActionMessage("mcm.error.load.failed"), request);
        if(getPlanValue(request) == null)
        {
            return mapping.findForward("plan.list.action");
        }
        return new ActionForward("/subnodeviewaction.do?"
                                 + UrlUtil.subNodeString
                                 + "="
                                 + "p"
                                 + getPlanValue(request).getId(),
                                 true);
    }
}