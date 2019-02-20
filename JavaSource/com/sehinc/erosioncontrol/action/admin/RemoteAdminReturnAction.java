package com.sehinc.erosioncontrol.action.admin;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RemoteAdminReturnAction
    extends AdminBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RemoteAdminReturnAction.class);

    public ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        /*
                setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                    NavigationService.getPrimaryMenu(request),
                                    request);
        */
        return mapping.findForward("continue");
    }
}