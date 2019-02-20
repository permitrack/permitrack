package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.bmp.BMPBaseAction;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpCategoryDb;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpDb;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpLibraryDb;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import com.sehinc.erosioncontrol.value.bmp.BMPValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BMPAddLibraryAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPLoadLibraryAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In BMPAddLibraryAction");
        BMPLibraryDBListForm
            bmpLibraryDBListForm =
            (BMPLibraryDBListForm) form;
        List<BMPValue>
            bmpList;
        List<EcBmpCategory>
            categoryList;
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            objUser =
            getUserValue(request);
        if (isCreateLibrary(bmpLibraryDBListForm))
        {
            String
                bmpLibraryName =
                bmpLibraryDBListForm.getBmpDbLibraryName();
            EcBmpLibraryDb
                library =
                new EcBmpLibraryDb();
            library.setName(bmpLibraryName);
            int
                newLibraryId;
            try
            {
                newLibraryId =
                    library.save(objUser);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {e.getLocalizedMessage()};
                LOG.error(ApplicationResources.getProperty("bmp.library.db.library.create.error",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("bmp.library.db.library.create.error",
                                           parameters),
                         request);
                return mapping.findForward("continue");
            }
            categoryList =
                BmpDbService.getBMPCategoryList(clientValue);
            for (EcBmpCategory cat : categoryList)
            {
                EcBmpCategoryDb
                    categorydb =
                    new EcBmpCategoryDb();
                categorydb.setClientId(cat.getClientId());
                categorydb.setName(cat.getName());
                categorydb.setLibraryDbId(newLibraryId);
                categorydb.setStatusCode(cat.getStatus()
                                             .getCode());
                try
                {
                    categorydb.save(objUser);
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {e.getLocalizedMessage()};
                    LOG.error(ApplicationResources.getProperty("bmp.library.db.category.save.fail",
                                                               parameters));
                    LOG.error(e.getMessage());
                    addError(new ActionMessage("bmp.library.db.category.save.fail",
                                               parameters),
                             request);
                    return mapping.findForward("continue");
                }
                bmpList =
                    BmpDbService.getBMPList(cat.getId(),
                                            clientValue);
                for (BMPValue value : bmpList)
                {
                    EcBmpDb
                        bmpdb =
                        new EcBmpDb();
                    bmpdb.setClientId(value.getClientId());
                    bmpdb.setCategory(categorydb);
                    bmpdb.setName(value.getName());
                    bmpdb.setDescription(value.getDescription());
                    bmpdb.setWeblink(value.getWeblink());
                    bmpdb.setStatusCode(value.getStatusCode());
                    try
                    {
                        bmpdb.save(objUser);
                    }
                    catch (Exception e)
                    {
                        Object[]
                            parameters =
                            {e.getLocalizedMessage()};
                        LOG.error(ApplicationResources.getProperty("bmp.db.library.create.failed",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("bmp.db.library.create.failed",
                                                   parameters),
                                 request);
                        return mapping.findForward("continue");
                    }
                }
            }
            addMessage(new ActionMessage("bmp.library.db.library.added.success"),
                       request);
            return mapping.findForward("return");
        }
        else if (isCancelled(request))
        {
            addMessage(new ActionMessage("bmp.library.db.library.add.cancel"),
                       request);
            return mapping.findForward("cancel");
        }
        bmpList =
            BmpDbService.getBMPList(clientValue);
        setSessionAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT,
                            bmpList,
                            request);
        return mapping.findForward("continue");
    }

    private boolean isCreateLibrary(BMPLibraryDBListForm bmpLibraryDBListForm)
    {
        return bmpLibraryDBListForm.getSubmitButton()
               != null
               && bmpLibraryDBListForm.getSubmitButton()
            .equalsIgnoreCase("Save");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.BMP_LIST_MENU_NAME),
                         request);
    }

    @Override
    protected void setSecondaryMenuItem(HttpServletRequest request)
    {
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.BMP_ADD_MENU_ITEM_NAME);
    }
}

