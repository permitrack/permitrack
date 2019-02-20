package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPTemplateNewGoalAction
    extends BMPTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPTemplateNewGoalAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        GoalTemplateForm
            goalTemplateForm =
            (GoalTemplateForm) form;
        goalTemplateForm.reset();
        LOG.info("In BMPTemplateNewGoalAction");
        BMPValue
            bmpValue = getBMPTemplate(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        if (!bmpData.retrieveByPrimaryKeysAlternate())
        {
            return mapping.findForward("plan.template.list.action");
        }
        goalTemplateForm.setNumber(new Integer(PlanService.getGoalCount(bmpData.getId())
                                               + 1));
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.NONE_MENU,
                                                 0),
                        request);
    }
}