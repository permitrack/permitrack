package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.erosioncontrol.db.bmpdb.EcBmpCategoryDb;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpDb;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BMPLibraryEditAction
    extends BMPDBBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPLibraryEditAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        BMPLibraryEditForm
            bmpLibraryEditForm =
            (BMPLibraryEditForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("bmp.library.db.library.edit.cancel"),
                       request);
            return mapping.findForward("continue");
        }
        else
        {
            Integer[]
                bmpDeletes =
                bmpLibraryEditForm.getBmpDeletes();
            if (bmpDeletes
                != null)
            {
                for (Object o : bmpDeletes)
                {
                    Integer
                        bmpDbId =
                        (Integer) o;
                    try
                    {
                        EcBmpDb
                            bmp =
                            new EcBmpDb(bmpDbId);
                        bmp.load();
                        Integer
                            dbCategoryId =
                            bmp.getCategory()
                                .getId();
                        bmp.delete();
                        List
                            otherBmps =
                            BmpDbService.getBmpDbListByDbCategoryId(dbCategoryId);
                        if (otherBmps.size()
                            == 0)
                        {
                            EcBmpCategoryDb
                                category =
                                new EcBmpCategoryDb(dbCategoryId);
                            category.load();
                            category.delete();
                        }
                    }
                    catch (Exception e)
                    {
                        Object[]
                            parameters =
                            {bmpDbId};
                        LOG.error(ApplicationResources.getProperty("bmp.library.db.library.edit.failed",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("bmp.library.db.library.edit.failed",
                                                   parameters),
                                 request);
                        return mapping.findForward("continue");
                    }
                }
            }
        }
        addMessage(new ActionMessage("bmp.library.db.library.edit.success"),
                   request);
        return mapping.findForward("continue");
    }
}