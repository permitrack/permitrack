package com.sehinc.security.action.client;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.security.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ClientECViewAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientECViewAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientECViewAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("view.ec.unauthorized"));
            addError(new ActionMessage("view.ec.unauthorized"), request);
            return mapping.findForward("client.view.page");
        }
/*
        ClientECForm
            objEC =
            (ClientECForm) form;
*/
        return mapping.findForward("continue");
    }
}