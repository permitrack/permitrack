package com.sehinc.security.action.contact;

import com.sehinc.security.action.client.ClientBaseAction;
import com.sehinc.security.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ContactBaseAction
    extends ClientBaseAction
{
    public abstract ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return userAction(mapping,
                          form,
                          request,
                          response);
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws Exception
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_CONTACT_MENU_ITEM_NAME);
    }
}