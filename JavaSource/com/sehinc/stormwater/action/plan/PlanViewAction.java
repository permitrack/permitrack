package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.MathUtil;
import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanViewAction
    extends PlanBaseAction
{
    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanValue
            planValue =
            getPlanValue(request);
        ActionForward
            f =
            redirect(planValue.getId(),
                     "/subnodeviewaction.do?"
                     + UrlUtil.subNodeString
                     + "=", request);
        if (f
            != null)
        {
            return f;
        }
        PlanForm
            planForm =
            (PlanForm) form;
        planForm.reset();
        PlanData
            planData =
            new PlanData();
        planData.setId(planValue.getId());

        if(planData.retrieveByPrimaryKeys())
        {
            if(setPlanSessionKeys(SessionKeys.PLAN,
                            planValue.getId(), request))
            {
                planForm.setId(planData.getId());
                planForm.setClientId(planData.getClientId());
                planForm.setName(planData.getName());
                planForm.setDescription(planData.getDescription());
                planForm.setPermitNumber(planData.getPermitNumber());
                PermitPeriodValue
                    permitPeriodValue =
                    PermitPeriodService.getPermitPeriodValue(planData.getPermitPeriodId());
                if (permitPeriodValue
                    != null)
                {
                    planForm.setPermitPeriodName(permitPeriodValue.getName());
                    planForm.setPermitPeriodId(permitPeriodValue.getId());
                }
                planForm.setPermitTypeId(planData.getPermitTypeId());
                PlanPermitType
                    planPermitType =
                    PlanPermitType.getById(planData.getPermitTypeId());
                if (planPermitType
                    != null)
                {
                    planForm.setPermitTypeName(planPermitType.getLabel());
                }
                return mapping.findForward("continue");
            }
        }

        return mapping.findForward("stormwater");
    }

    @Override
    protected String getSavedNode(Integer id, HttpServletRequest request)
    {
        String
            savedNode =
            null;
        Integer
            plan =
            0;
        Cookie[]
            cookies =
            request.getCookies();
        for (Cookie cookie : cookies)
        {
            if (cookie.getName()
                    .equals("jstree_select")
                && cookie.getValue()
                   != null)
            {
                String
                    value =
                    cookie.getValue()
                        .replaceAll("%23",
                                    "");
                if ((value.substring(0,
                                     1)
                         .equals("m")
                     || value.substring(0,
                                        1)
                    .equals("b")
                     || value.substring(0,
                                        1)
                    .equals("g")
                     || value.substring(0,
                                        1)
                    .equals("a"))
                    && MathUtil.isInteger(value.substring(1)))
                {
                    savedNode =
                        value;
                }
            }
            else if (cookie.getName()
                         .equals("jstree_open")
                     && cookie.getValue()
                        != null)
            {
                String
                    value =
                    cookie.getValue()
                        .split("%2C")[0].replaceAll("%23",
                                                    "");
                if (value.substring(0,
                                    1)
                        .equals("p")
                    && MathUtil.isInteger(value.substring(1)))
                {
                    plan =
                        Integer.parseInt(value.substring(1));
                }
            }
        }
        if (plan.equals(id)
            && savedNode
               != null)
        {
            return savedNode;
        }
        return null;
    }
}