package com.sehinc.stormwater.action.report;

import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportOptionsAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportOptionsAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        ReportForm
            reportForm =
            (ReportForm) form;
        reportForm.reset();
        PlanValue
            planValue = getPlanValue(request);
/*
            (PlanValue) SessionService.getSessionAttribute(SessionKeys.PLAN,
                                                           request);
*/
        if (planValue
            == null)
        {
            LOG.info("reportPlanValue is null. Forwarding to: "
                     + mapping.findForward("stormwater")
                .toString());
            return mapping.findForward("stormwater");
        }
        ClientValue
            clientValue =
            getClientValue(request);
        reportForm.setPlanName(planValue.getName());
        reportForm.setMcmList(PlanService.getMCMReportLabelValueList(planValue));
        reportForm.setBmpList(PlanService.getBMPReportLabelValueList(planValue));
        reportForm.setUserList(UserService.getUserBeanList(clientValue));
        if (planValue.getPermitType()
            .equals(PlanPermitType.MPCA))
        {
            reportForm.setDisplayMPCAReportOption(true);
        }
        else
        {
            reportForm.setDisplayMPCAReportOption(false);
        }
        PermitTimePeriodValue
            permitTimePeriodValue =
            PermitPeriodService.getCurrentPermitTimePeriodValue(planValue.getPermitPeriodId());
        if (permitTimePeriodValue
            != null)
        {
            reportForm.setStartDate(permitTimePeriodValue.getStartDate());
            reportForm.setEndDate(permitTimePeriodValue.getEndDate());
        }
        return mapping.findForward("continue");
    }
}