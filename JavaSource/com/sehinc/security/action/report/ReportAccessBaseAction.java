package com.sehinc.security.action.report;

import com.sehinc.security.action.client.ClientBaseAction;
import com.sehinc.security.action.navigation.PrimaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ReportAccessBaseAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportAccessBaseAction.class);
    private static
    String
        strMod =
        new String("In class: com.sehinc.security.action.report.ReportAccessBaseAction.");

    public abstract ActionForward reportAccessAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return reportAccessAction(mapping,
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
        primaryMenu.setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }
}
