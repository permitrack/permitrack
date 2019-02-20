package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class PlanViewDeleteAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanViewDeleteAction.class);

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanDeleteForm
            planDeleteForm =
            (PlanDeleteForm) form;
        planDeleteForm.reset();
        LOG.info("In PlanViewDeleteAction");
        PlanValue
            planValue =
            getPlanValue(request);
        LOG.info("after get plan value");
        PlanData
            planData =
            new PlanData();
        planData.setId(planValue.getId());
        planData.retrieveByPrimaryKeys();
        return mapping.findForward("continue");
    }
}