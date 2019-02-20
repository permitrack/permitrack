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

@SuppressWarnings("unused")
public class ClientDVViewAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientDVViewAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientDVViewAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
/*
        LOG.info(strLog
                 + "in action");
*/
/*
        ClientDVForm
            objDV =
            (ClientDVForm) form;
*/
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("view.dataview.unauthorized"));
            addError(new ActionMessage("view.dataview.unauthorized"), request);
            return mapping.findForward("client.view.action");
        }
        return mapping.findForward("continue");
    }
}