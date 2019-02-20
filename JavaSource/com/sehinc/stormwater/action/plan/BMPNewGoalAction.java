package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPNewGoalAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanCreateAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        GoalForm
            goalForm =
            (GoalForm) form;
        goalForm.reset();
        LOG.info("In BMPNewGoalAction");
        BMPValue
            bmpValue =
            getBMP(request);
        BMPData
            bmpData =
            new BMPData();
        bmpData.setId(bmpValue.getId());
        if (!bmpData.retrieveByPrimaryKeysAlternate())
        {
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "m"
                                     + getMCMValue(request).getId(),
                                     true);
        }
        goalForm.setOwnerId(bmpData.getOwnerId());
        goalForm.setNumber(new Integer(PlanService.getGoalCount(bmpData.getId())
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