package com.sehinc.environment.action.admin;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.environment.action.base.BaseAction;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class AdminBaseAction
    extends BaseAction
{
    public abstract ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return adminAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           primaryMenu,
                                           request);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SYSTEM_ADMIN_MENU_NAME);
        secondaryMenu.setCurrentItem(SecondaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}