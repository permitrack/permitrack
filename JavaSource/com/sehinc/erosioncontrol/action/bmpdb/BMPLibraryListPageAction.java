package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BMPLibraryListPageAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPLibraryListPageAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        BMPLibraryDBListForm
            bmpLibraryDBListForm =
            (BMPLibraryDBListForm) form;
        int
            bmpLibraryId =
            bmpLibraryDBListForm.getBmpDbLibraryId();
        bmpLibraryDBListForm.setBmpDbLibraryList(BmpDbService.getBmpDbLibraryLabelValueList(bmpLibraryId));
        List
            bmpCategoryIdList =
            BmpDbService.getCategoryIdsByLibrary(bmpLibraryId);
        List
            categoryList =
            new ArrayList<BMPDbValue>();
        if (!bmpCategoryIdList.isEmpty())
        {
            for (Object o : bmpCategoryIdList)
            {
                Integer
                    categoryId =
                    (Integer) o;
                categoryList.addAll(BmpDbService.getBmpDbLabelValueListByCategory(categoryId));
            }
            bmpLibraryDBListForm.setBmpDbList(categoryList);
        }
        return mapping.findForward("continue");
    }

    private boolean isEditLibrary(BMPLibraryDBListForm bmpLibraryDBListForm)
    {
        return bmpLibraryDBListForm.getSubmitButton()
               != null
               && bmpLibraryDBListForm.getSubmitButton()
            .equalsIgnoreCase("Edit");
    }
}
