package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.bmpdb.BMPDBListForm;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.server.bmpdb.BMPDBService;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MCMViewBMPDBAction
    extends MCMBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMViewBMPDBAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBListForm
            bmpDbListForm =
            (BMPDBListForm) form;
        LOG.info("In MCMSelectBMPAction");
        PlanValue
            planValue =
            getPlanValue(request);
        int
            bmpDbCategoryId =
            bmpDbListForm.getBmpDbCategoryId();
        bmpDbListForm.setBmpDbCategoryId(bmpDbCategoryId);
        bmpDbListForm.setBmpDbList(BMPDBService.getBMPDBLabelValueListByCategory(bmpDbCategoryId,
                                                                                 planValue));
        bmpDbListForm.setBmpDbCategoryList(BMPDBService.getBmpDbCategoryLabelValueList(planValue));
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
