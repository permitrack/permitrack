package com.sehinc.erosioncontrol.action.bmpdb;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.bmp.BMPBaseAction;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpCategoryDb;
import com.sehinc.erosioncontrol.db.bmpdb.EcBmpDb;
import com.sehinc.erosioncontrol.resources.ApplicationResources;
import com.sehinc.erosioncontrol.server.bmpdb.BmpDbService;
import com.sehinc.erosioncontrol.value.bmpdb.BMPDbValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class BMPLoadLibraryAction
    extends BMPBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPLoadLibraryAction.class);

    public ActionForward bmpAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In BMPLoadLibraryAction");
        BMPLibraryDBListForm
            bmpLibraryDBListForm =
            (BMPLibraryDBListForm) form;
        List
            bmpList;
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            objUser =
            getUserValue(request);
        if (isConnect(bmpLibraryDBListForm))
        {
            int
                bmpLibraryId =
                bmpLibraryDBListForm.getBmpDbLibraryId();
            List
                bmpCategoryIdList =
                BmpDbService.getCategoryIdsByLibrary(bmpLibraryId);
            EcBmpCategory
                category;
            if (!bmpCategoryIdList.isEmpty())
            {
                for (Object o : bmpCategoryIdList)
                {
                    Integer
                        dbCategoryId =
                        (Integer) o;
                    EcBmpCategoryDb
                        categorydb =
                        new EcBmpCategoryDb(dbCategoryId);
                    try
                    {
                        categorydb.load();
                    }
                    catch (Exception e)
                    {
                        Object[]
                            parameters =
                            {dbCategoryId};
                        LOG.error(ApplicationResources.getProperty("bmp.library.db.load.category.fail",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("bmp.library.db.load.category.fail",
                                                   parameters),
                                 request);
                        return mapping.findForward("continue");
                    }
                    try
                    {
                        category =
                            new EcBmpCategory();
                        category.setClientId(clientValue.getId());
                        category.setName(categorydb.getName());
                        category.setStatus(categorydb.getStatus());
                        category.save(objUser);
                    }
                    catch (Exception e)
                    {
                        Object[]
                            parameters =
                            {e.getLocalizedMessage()};
                        LOG.error(ApplicationResources.getProperty("bmp.category.create.or.load.failed",
                                                                   parameters));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("bmp.category.create.or.load.failed",
                                                   parameters),
                                 request);
                        return mapping.findForward("continue");
                    }
                    List
                        bmpListByCatId =
                        BmpDbService.getBmpDbLabelValueListByCategory(dbCategoryId);
                    for (Object p : bmpListByCatId)
                    {
                        BMPDbValue
                            bmpdbval =
                            (BMPDbValue) p;
                        EcBmpDb
                            bmpdb =
                            new EcBmpDb(bmpdbval.getId());
                        try
                        {
                            bmpdb.load();
                        }
                        catch (Exception e)
                        {
                            Object[]
                                parameters =
                                {bmpdbval.getId()};
                            LOG.error(ApplicationResources.getProperty("bmp.library.db.load.bmp.fail",
                                                                       parameters));
                            LOG.error(e.getMessage());
                            addError(new ActionMessage("bmp.library.db.load.bmp.fail",
                                                       parameters),
                                     request);
                            return mapping.findForward("continue");
                        }
                        try
                        {
                            EcBmp
                                bmp =
                                new EcBmp();
                            bmp.setCategory(category);
                            bmp.setClientId(clientValue.getId());
                            bmp.setName(bmpdb.getName());
                            bmp.setDescription(bmpdb.getDescription());
                            bmp.setWeblink(bmpdb.getWeblink());
                            bmp.setStatus(bmpdb.getStatus());
                            bmp.save(objUser);
                        }
                        catch (Exception e)
                        {
                            Object[]
                                parameters =
                                {e.getLocalizedMessage()};
                            LOG.error(ApplicationResources.getProperty("bmp.create.failed",
                                                                       parameters));
                            LOG.error(e.getMessage());
                            addError(new ActionMessage("bmp.create.failed",
                                                       parameters),
                                     request);
                            return mapping.findForward("continue");
                        }
                    }
                }
            }
            bmpLibraryDBListForm.setBmpDbLibraryId(0);
            addMessage(new ActionMessage("bmp.library.db.library.load.success"),
                       request);
/*
            bmpList =
                BmpDbService.getBMPList(clientValue);
*/
            return mapping.findForward("return");
        }
        else if (isCancelled(request))
        {
            bmpLibraryDBListForm.setBmpDbLibraryId(0);
/*
            bmpList =
                BmpDbService.getBMPList(clientValue);
*/
            addMessage(new ActionMessage("bmp.library.db.library.load.cancel"),
                       request);
            return mapping.findForward("cancel");
        }
        else
        {
            bmpList =
                BmpDbService.getBMPList(clientValue);
            int
                bmpLibraryId =
                bmpLibraryDBListForm.getBmpDbLibraryId();
            if (bmpLibraryId
                != 0)
            {
                bmpLibraryDBListForm.setBmpDbLibraryList(BmpDbService.getBmpDbLibraryLabelValueList(bmpLibraryId));
            }
            else
            {
                bmpLibraryDBListForm.setBmpDbLibraryList(null);
            }
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
                        i =
                        (Integer) o;
                    categoryList.addAll(BmpDbService.getBmpDbLabelValueListByCategory(i));
                }
                bmpLibraryDBListForm.setBmpDbList(categoryList);
            }
            else
            {
                bmpLibraryDBListForm.setBmpDbList(null);
            }
        }
/*
        SecurityManager
            securityManager =
            getSecurityManager(request);
*/
        setSessionAttribute(SessionKeys.EC_BMP_ADMIN_LIST_BY_CLIENT,
                            bmpList,
                            request);
        return mapping.findForward("continue");
    }

    private boolean isConnect(BMPLibraryDBListForm bmpLibraryDBListForm)
    {
        return bmpLibraryDBListForm.getSubmitButton()
               != null
               && bmpLibraryDBListForm.getSubmitButton()
            .equalsIgnoreCase("Load");
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
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.BMP_EDIT_MENU_ITEM_NAME);
    }
}
