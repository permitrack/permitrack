package com.sehinc.stormwater.action.template;

import com.sehinc.common.util.MathUtil;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanPermitType;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlanTemplateViewAction
    extends PlanTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanTemplateViewAction.class);

    public ActionForward templateAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        PlanTemplateForm
            planTemplateForm =
            (PlanTemplateForm) form;
        planTemplateForm.reset();
        LOG.info("In PlanTemplateViewAction");
        PlanValue
            planValue =
            getPlanValue(request);
        ActionForward
            f =
            redirect(planValue.getId(),
                     "/", request);
        if (f
            != null)
        {
            return f;
        }
        clearPlanTemplateSessionKeys(request);
        PlanData
            planData =
            PlanService.getPlan(planValue.getId());
        if (planData
            != null)
        {
            planTemplateForm.setId(planData.getId());
            planTemplateForm.setName(planData.getName());
            planTemplateForm.setDescription(planData.getDescription());
            planTemplateForm.setPermitTypeId(planData.getPermitTypeId());
            PlanPermitType
                type =
                PlanPermitType.getById(planData.getPermitTypeId());
            String
                label =
                type.getLabel();
            planTemplateForm.setPermitTypeName(label);
        }
        else
        {
            addError(new ActionMessage("plan.error.load.failed"), request);
            return mapping.findForward("plan.template.list.action");
        }
        setSessionAttribute(SessionKeys.PLAN_TEMPLATE,
                            planValue,
                            request);
/*
        setSessionAttribute(SessionKeys.PLAN_TEMPLATE_HIERARCHY,
                            null);
*/
        response.setHeader("Set-Cookie",
                           "jstree_select=%23p"
                           + planData.getId());
        return mapping.findForward("continue");
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
                if (value.contains("mcm_temp")
                    && MathUtil.isInteger(value.substring("mcm_temp".length())))
                {
                    savedNode =
                        "mcmtemplateviewaction.do?mcm_id="
                        + value.substring("mcm_temp".length());
                }
                else if (value.contains("bmp_temp")
                         && MathUtil.isInteger(value.substring("bmp_temp".length())))
                {
                    savedNode =
                        "bmptemplateviewaction.do?bmp_id="
                        + value.substring("bmp_temp".length());
                }
                else if (value.contains("goal_temp")
                         && MathUtil.isInteger(value.substring("goal_temp".length())))
                {
                    savedNode =
                        "goaltemplateviewaction.do?goal_id="
                        + value.substring("goal_temp".length());
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