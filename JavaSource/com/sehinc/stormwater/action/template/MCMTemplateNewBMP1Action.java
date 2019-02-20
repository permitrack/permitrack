package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.plan.MCMData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.plan.MCMValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMTemplateNewBMP1Action
    extends MCMTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMTemplateNewBMP1Action.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPTemplateForm
            bmpTemplateForm =
            (BMPTemplateForm) form;
        bmpTemplateForm.reset();
        LOG.info("In MCMTemplateNewBMP1Action");
        MCMValue
            mcmValue =
            getMCMTemplate(request);
        MCMData
            mcmData =
            new MCMData();
        mcmData.setId(mcmValue.getId());
        if (!mcmData.load())
        {
            return mapping.findForward("plan.template.list.action");
        }
        bmpTemplateForm.setMcmNumber(mcmData.getNumber());
        bmpTemplateForm.setUseSection(true);
        bmpTemplateForm.setNumber(new Integer(PlanService.getBMPCount(mcmData.getId())
                                              + 1));
        bmpTemplateForm.initializeMPCAForm1BMP();
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