package com.sehinc.stormwater.action.base;

import com.sehinc.stormwater.action.navigation.PrimaryMenu;
import com.sehinc.stormwater.action.navigation.PrimaryMenuItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultAction
    extends BaseAction
{
    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        PrimaryMenuItem
            primaryMenuItem =
            (PrimaryMenuItem) primaryMenu.getCurrentItem();
        return primaryMenuItem.getForward(true);
    }
}