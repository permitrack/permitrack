package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.server.bmpdb.BMPDBService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class BMPDBListAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBListAction.class);

    public ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        BMPDBListForm
            bmpDbListForm =
            (BMPDBListForm) form;
        int
            bmpDbCategoryId =
            bmpDbListForm.getBmpDbCategoryId();
        try
        {
            if (request.getAttribute(SessionKeys.BMP_CATEGORY_DB)
                != null)
            {
                bmpDbCategoryId =
                    Integer.parseInt(request.getAttribute(SessionKeys.BMP_CATEGORY_DB)
                                         .toString());
            }
        }
        catch (Error e)
        {
            LOG.error("Cannot retrieve bmpDbCategoryId from request");
        }
        bmpDbListForm.setBmpDbList(BMPDBService.getBMPDBLabelValueListByCategory(bmpDbCategoryId));
/*
        request.getSession()
            .removeAttribute(SessionKeys.BMP_DB);
*/
        return mapping.findForward("continue");
    }
}