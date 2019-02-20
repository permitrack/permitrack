package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpLibraryDb;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbCatValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BMPLibraryEditPageAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPLibraryEditPageAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In BMPLibraryEditPageAction");
        BMPLibraryEditForm
            bmpEditForm =
            (BMPLibraryEditForm) form;
        Integer
            bmpLibraryId =
            getParamInt(RequestKeys.EC_BMP_LIBRARY_ID,
                        request);
        EcBmpLibraryDb
            library =
            new EcBmpLibraryDb(bmpLibraryId);
        try
        {
            library.load();
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("bmp.library.db.load.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("bmp.library.db.load.failed"),
                     request);
            return mapping.findForward("continue");
        }
        bmpEditForm.setLibraryId(library.getId());
        bmpEditForm.setLibraryName(library.getName());
        List
            categoryIds =
            BmpDbService.getCategoryIdsByLibrary(bmpLibraryId);
        List
            categoryList =
            new ArrayList<BMPDbCatValue>();
        if (!categoryIds.isEmpty())
        {
            for (Object o : categoryIds)
            {
                Integer
                    categoryId =
                    (Integer) o;
                BMPDbCatValue
                    categoryVal =
                    BmpDbService.getBmpDbCategoryAndBmps(categoryId);
                categoryList.add(categoryVal);
            }
        }
        bmpEditForm.setBmpCategories(categoryList);
        return mapping.findForward("continue");
    }
}
