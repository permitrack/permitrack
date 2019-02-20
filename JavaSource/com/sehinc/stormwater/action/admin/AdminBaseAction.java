package com.sehinc.stormwater.action.admin;

import com.sehinc.stormwater.action.base.BaseAction;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AdminBaseAction
    extends BaseAction
{
    protected abstract ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return adminAction(mapping,
                           form,
                           request,
                           response);
    }


    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.ADMIN_ADMIN_MENU_NAME,
                                      0);
        secondaryMenu.setCurrentItem(SecondaryMenu.ADMIN_CLIENT_SELECT_MENU_ITEM_NAME);

        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }
}