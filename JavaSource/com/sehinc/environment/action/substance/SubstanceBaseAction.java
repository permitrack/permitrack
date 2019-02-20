package com.sehinc.environment.action.substance;

import com.sehinc.environment.action.base.ClientBaseAction;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SubstanceBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward substanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return substanceAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_SUBSTANCE_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.SUBSTANCE_LIST_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.SUBSTANCE_LIST_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}