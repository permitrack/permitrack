package com.sehinc.stormwater.action.template;

import com.sehinc.stormwater.action.bmpdb.BMPDBListForm;
import com.sehinc.stormwater.server.bmpdb.BMPDBService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MCMTemplateViewBMPDBAction
    extends MCMTemplateBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(MCMTemplateViewBMPDBAction.class);

    public ActionForward mcmAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBListForm
            bmpDbListForm =
            (BMPDBListForm) form;
        LOG.info("In MCMTemplateViewBMPDBAction");
        int
            bmpDbCategoryId =
            bmpDbListForm.getBmpDbCategoryId();
        bmpDbListForm.setBmpDbCategoryId(bmpDbCategoryId);
        bmpDbListForm.setBmpDbList(BMPDBService.getBMPDBLabelValueListByCategory(bmpDbCategoryId));
        bmpDbListForm.setBmpDbCategoryList(BMPDBService.getBmpDbCategoryLabelValueList());
        request.setAttribute("bmpDbListForm",
                             bmpDbListForm);
        return mapping.findForward("continue");
    }
}