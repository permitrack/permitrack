package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.RequestKeys;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.hierarchy.bmpdb.BMPDBBranch;
import com.sehinc.stormwater.action.hierarchy.treemenu.ITreeMenu;
import com.sehinc.stormwater.action.hierarchy.treemenu.TreeMenuFactory;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import com.sehinc.stormwater.action.navigation.TertiaryMenu;
import com.sehinc.stormwater.db.bmpdb.BMPDBData;
import com.sehinc.stormwater.value.bmpdb.BMPDBValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BMPDBBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBBaseAction.class);

    protected abstract ActionForward bmpdbAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        if (request.getParameter(RequestKeys.BMP_DB_ID)
            != null)
        {
            Integer
                bmpdbId =
                new Integer(request.getParameter(RequestKeys.BMP_DB_ID));
            BMPDBData
                bmpdbData =
                new BMPDBData();
            bmpdbData.setId(bmpdbId);
            if (bmpdbData.load())
            {
                setSessionAttribute(SessionKeys.BMP_DB,
                                    new BMPDBValue(bmpdbData),
                                    request);
            }
        }

        return bmpdbAction(mapping,
                           form,
                           request,
                           response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME,
                                    0);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_BMPDB_MENU_ITEM_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
    }

    private void setSelectedMenuItem(SecondaryMenu secondaryMenu, String selectedMenuItem)
    {
        try
        {
            if (selectedMenuItem
                == null
                || selectedMenuItem.equals("view"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.BMPDB_VIEW_MENU_ITEM_NAME);
            }
            else if (selectedMenuItem.equals("list"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.BMPDB_SELECT_MENU_ITEM_NAME);
            }
            else if (selectedMenuItem.equals("create"))
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.BMPDB_NEW_MENU_ITEM_NAME);
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        BMPDBValue
            bmpDbValue = getBMPDB(request);
        String
            selectedMenuItem =
            (String) request.getAttribute(RequestKeys.SECONDARY_MENU_ITEM);
        SecondaryMenu
            secondaryMenu =
            bmpDbValue
            == null
                ? SecondaryMenu.getInstance(SecondaryMenu.DEFAULT_BMPDB_MENU_NAME,
                                            0)
                : SecondaryMenu.getInstance(SecondaryMenu.BMPDB_MENU_NAME,
                                            bmpDbValue.getId());
        setSelectedMenuItem(secondaryMenu,
                            selectedMenuItem);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    @Override
    protected void setTreeMenu(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.setTreeMenu(request, response);
        BMPDBValue
            bmpDbValue =
            getBMPDB(request);
        if (bmpDbValue
            != null)
        {
            TreeMenuFactory
                treeMenuFactory =
                TreeMenuFactory.getInstance();
            BMPDBBranch
                treeMenuLeaf =
                new BMPDBBranch(bmpDbValue);
            ITreeMenu
                treeMenu =
                treeMenuFactory.getTreeMenu(treeMenuLeaf);
            request.getSession()
                .setAttribute(SessionKeys.BMP_DB_HIERARCHY,
                              treeMenu);
            response.setHeader("Set-Cookie",
                               "jstree_select=%23bmpDb"
                               + bmpDbValue.getId());
        }
    }

    protected BMPDBValue getBMPDB(HttpServletRequest request)
    {
        return (BMPDBValue) getSessionAttribute(SessionKeys.BMP_DB, request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
    {
        super.setTertiaryMenu(request);
        if (getBMPDB(request)
            != null)
        {
            setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.BMPDB_MENU_NAME,
                                                     getBMPDB(request).getId()),
                            request);
        }
    }

    @Override
    public void finalizeAction(HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.finalizeAction(request, response);

        setTreeMenu(request, response);
    }
}