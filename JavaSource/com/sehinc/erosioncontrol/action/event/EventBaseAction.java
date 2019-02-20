package com.sehinc.erosioncontrol.action.event;

import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.client.ClientBaseAction;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class EventBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward eventAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return eventAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.EVENT_LIST_MENU_ITEM_NAME);
    }

    public void setPageSetup(String strTitle, String strDescription, HttpServletRequest request)
    {
        if (strTitle
            == null)
        {
            strTitle =
                new String("");
        }
        if (strDescription
            == null)
        {
            strDescription =
                new String("");
        }
        setSessionAttribute(SessionKeys.EC_PAGE_TITLE,
                            strTitle,
                            request);
        setSessionAttribute(SessionKeys.EC_PAGE_DESCRIPTION,
                            strDescription,
                            request);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.EVENT_LIST_MENU_NAME),
                         request);
    }
}