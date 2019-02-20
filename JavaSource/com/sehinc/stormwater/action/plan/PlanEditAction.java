package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanEditAction
    extends PlanBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(PlanEditAction.class);
*/

    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanForm
            planForm =
            (PlanForm) form;
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
/*
        if (!planData.getName()
            .equals(planForm.getName()))
        {
            request.getSession()
                .setAttribute(SessionKeys.PLAN_HIERARCHY,
                              null);
        }
*/
        planData.setName(planForm.getName());
        planData.setDescription(planForm.getDescription());
        planData.setPermitNumber(planForm.getPermitNumber());
        planData.save(userValue);
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