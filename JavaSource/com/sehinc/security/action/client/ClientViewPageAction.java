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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientViewPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientViewPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ClientForm
            objViewClient =
            (ClientForm) form;
        SecurityManager
            securityManager =
            getSecurityManager(request);
        try
        {
            ClientService.getClientFormById(objViewClient,
                                            getClientIdFromRequestOrSession(request),
                                            getUserValue(request));
            setSessionAttribute(SessionKeys.CLIENT_FORM,
                                objViewClient, request);
            if (!securityManager.getIsClientAdministrator())
            {
                LOG.debug(ApplicationResources.getProperty("view.client.unauthorized"));
                return mapping.findForward("user.view.page");
            }
        }
        catch (Exception e)
        {
            LOG.debug(e.toString());
            return mapping.findForward("client.list.page");
        }
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        setPrimaryMenuItem(request);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_VIEW_MENU_NAME,
                                      getClientIdFromRequestOrSession(request));
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_VIEW_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}