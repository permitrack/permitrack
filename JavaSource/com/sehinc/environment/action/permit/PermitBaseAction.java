package com.sehinc.environment.action.permit;

import com.sehinc.environment.action.base.ClientBaseAction;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PermitBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitBaseAction.class);

    public abstract ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return permitAction(mapping,
                            form,
                            request,
                            response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_PERMIT_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu;
        if (getPermit(request)
            != null)
        {
            secondaryMenu =
                SecondaryMenu.getInstance(SecondaryMenu.PERMIT_VIEW_MENU);
            secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_VIEW_MENU_ITEM);
        }
        else
        {
            secondaryMenu =
                SecondaryMenu.getInstance(SecondaryMenu.PERMIT_LIST_MENU);
            secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_LIST_MENU_ITEM);
        }
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}