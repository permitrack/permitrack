package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.resources.ApplicationResources;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BMPDBNewGoalAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBNewGoalAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBGoalForm
            bmpDbGoalForm =
            (BMPDBGoalForm) form;
        bmpDbGoalForm.reset();
        LOG.info("In BMPDBNewGoalAction");
        BMPDBValue
            bmpDbValue =
            getBMPDB(request);
        BMPDBData
            bmpDbData =
            new BMPDBData();
        bmpDbData.setId(bmpDbValue.getId());
        if (!bmpDbData.load())
        {
            LOG.error(ApplicationResources.getProperty("bmpdb.error.bmpdb.value.not.found"));
            addError(new ActionMessage("bmpdb.error.bmpdb.value.not.found"), request);
            return mapping.findForward("bmpdb.list.action");
        }
        bmpDbGoalForm.setNumber(new Integer(PlanService.getBMPDBGoalCount(bmpDbData.getId())
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