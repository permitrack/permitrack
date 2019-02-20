package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.server.plan.PlanService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlanTemplateListAction
    extends TemplateBaseAction
{
    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        List
            planTemplateList =
            PlanService.getPlanTemplates();
        request.setAttribute("plans",
                             planTemplateList);
        return mapping.findForward("continue");
    }
}