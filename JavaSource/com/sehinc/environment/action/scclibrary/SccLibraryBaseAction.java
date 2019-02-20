package com.sehinc.environment.action.scclibrary;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.environment.action.base.BaseAction;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SccLibraryBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SccLibraryBaseAction.class);

    public abstract ActionForward sccLibraryAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        LOG.info("In SccLibraryBaseAction");
        return sccLibraryAction(mapping,
                                form,
                                request,
                                response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_SCC_MENU_ITEM_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SCC_LIBRARY_LIST_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.SCC_LIBRARY_LIST_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}