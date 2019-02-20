package com.sehinc.security.action.client;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
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

public class ClientDVEditPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientDVEditPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientDVEditPageAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        ClientDVForm
            objDV =
            (ClientDVForm) form;
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("edit.dataview.unauthorized"));
            addError(new ActionMessage("edit.dataview.unauthorized"), request);
            return mapping.findForward("client.dv.view.page");
        }
        try
        {
            ClientService.getClientDVForm(objDV,
                                          getClientIdFromRequestOrSession(request));
        }
        catch (Exception e)
        {
            LOG.debug(ApplicationResources.getProperty("error.view.dataview"));
            addError(new ActionMessage("error.view.dataview"), request);
            return mapping.findForward("client.dv.view.page");
        }
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
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_DV_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_DV_EDIT_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}