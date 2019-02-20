package com.sehinc.security.action.client;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
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

public class ClientECViewPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientECViewPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientECViewPageAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        Integer
            intClientId;
        ClientECForm
            objEC =
            (ClientECForm) form;
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
        intClientId =
            getClientIdFromRequestOrSession(request);
        ClientService.getClientECForm(objEC,
                                      intClientId);
        objEC.checkForHTML();
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL,
                            getPublicMapUrl(intClientId, request), request);
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL,
                            getPrivateMapUrl(intClientId, 0, request), request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws Exception, ServletException
    {
        getPrimaryMenu(request).setCurrentItem(PrimaryMenu.OPTIONS_LIST_MENU_ITEM);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        setPrimaryMenuItem(request);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_EC_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_EC_VIEW_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}