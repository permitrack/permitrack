package com.sehinc.stormwater.action.plan;

import com.sehinc.common.util.UrlUtil;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMNewBMP0Action
    extends MCMBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMNewBMP0Action.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPForm
            bmpForm =
            (BMPForm) form;
        bmpForm.reset();
        LOG.info("In MCMNewBMP0Action");
        MCMValue
            mcmValue =
            getMCMValue(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        if (!mcmData.retrieveByPrimaryKeysAlternate())
        {
            addError(new ActionMessage("mcm.error.load.failed"), request);
            return new ActionForward("/subnodeviewaction.do?"
                                     + UrlUtil.subNodeString
                                     + "="
                                     + "p"
                                     + getPlanValue(request).getId(),
                                     true);
        }
        bmpForm.setMcmNumber(mcmData.getNumber());
        bmpForm.setUseSection(false);
        bmpForm.setOwnerId(mcmData.getOwnerId());
        bmpForm.setNumber(new Integer(PlanService.getBMPCount(mcmData.getId())
                                      + 1));
        bmpForm.initializeNonMPCABMP();
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