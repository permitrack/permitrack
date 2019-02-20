package com.sehinc.stormwater.action.plan;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanDeleteReasonData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanDeleteAction
    extends PlanBaseAction
{
    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanDeleteForm
            planDeleteForm =
            (PlanDeleteForm) form;
        UserValue
            userValue =
            getUserValue(request);
        PlanValue
            planValue =
            getPlanValue(request);
        PlanData
            planData =
            new PlanData();
        planData.setId(planValue.getId());
        planData.retrieveByPrimaryKeys();
        planData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
        planData.save(userValue);
        PlanDeleteReasonData
            planDeleteReasonData =
            new PlanDeleteReasonData();
        planDeleteReasonData.setPlanId(planValue.getId());
        planDeleteReasonData.setDeleteReason(planDeleteForm.getDeleteReason());
        planDeleteReasonData.save(userValue);
        removeSessionAttribute(SessionKeys.PLAN,
                            request);
/*
        setSessionAttribute(SessionKeys.PLAN_HIERARCHY,
                            null);
*/
        return mapping.findForward("plan.list.action");
    }
}