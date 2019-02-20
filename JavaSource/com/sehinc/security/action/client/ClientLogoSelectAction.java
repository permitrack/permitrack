package com.sehinc.security.action.client;

import com.sehinc.common.action.base.SessionService;
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

public class ClientLogoSelectAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientLogoSelectAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        ClientForm
            clientForm =
            (ClientForm) form;
        clientForm.reset();
        LOG.info("In ClientLogoSelectAction");
        Integer
            intClientId =
            getClientIdFromRequestOrSession(request);
        if (intClientId.intValue()
            != 0)
        {
            try
            {
                ClientService.getClientFormById(clientForm,
                                                intClientId,
                                                getUserValue(request));
                clientForm.checkForHTML();
                int
                    secLev =
                    SessionService.getSecurityLevel(request);
                if (secLev
                    >= 100)
                {
                    clientForm.setBlnSystemAdmin(true);
                }
                else
                {
                    clientForm.setBlnSystemAdmin(false);
                }
            }
            catch (Exception c)
            {
                LOG.debug(ApplicationResources.getProperty("error.load.client.failed")
                          + c.getMessage());
                addError(new ActionMessage("error.load.client.failed"), request);
                return mapping.findForward("client.view.page");
            }
        }
        else
        {
            LOG.debug(ApplicationResources.getProperty("error.load.client.failed"));
            addError(new ActionMessage("error.load.client.failed"), request);
            return mapping.findForward("client.view.page");
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
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_LOGO_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_LOGO_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}