package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.ClientBaseAction;
import com.sehinc.environment.action.navigation.PrimaryMenu;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SourceUsageBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return sourceUsageAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.CLIENT_READINGS_MENU_ITEM);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_READINGS_MENU_NAME);
        if (request.getAttribute("type")
            != null
            && request.getAttribute("type")
            .equals("control"))
        {
            secondaryMenu.setCurrentItem(SecondaryMenu.CLIENT_CONTROL_USAGE_MENU_ITEM_NAME);
        }
        else
        {
            secondaryMenu.setCurrentItem(SecondaryMenu.CLIENT_SOURCE_USAGE_MENU_ITEM_NAME);
        }
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}
