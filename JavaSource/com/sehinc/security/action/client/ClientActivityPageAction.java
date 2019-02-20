package com.sehinc.security.action.client;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.security.action.base.BaseAction;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientActivityPageAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientActivityPageAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        /*
                ClientForm
                    objViewClient =
                    (ClientForm) form;
        */
        SecurityManager
            securityManager =
            getSecurityManager(request);
        try
        {
            if (securityManager.getIsSystemAdministrator())
            {
                Integer
                    clientId =
                    getClientIdFromRequestOrSession(request);
                setRequestAttribute(SessionKeys.CLIENT_ACTIVITY,
                                    ClientService.getActivity(0,
                                                              clientId
                                                              != null
                                                                  ? clientId
                                                                  : 0,
                                                              null,
                                                              null,
                                                              0),
                                    request);
                //LOG.debug(ApplicationResources.getProperty("view.client.unauthorized"));
                //return mapping.findForward("client.list.page");
            }
            else
            //if (securityManager.getIsClientAdministrator())
            {
                setRequestAttribute(SessionKeys.CLIENT_ACTIVITY,
                                    ClientService.getActivity(0,
                                                              securityManager.getClientID(),
                                                              null,
                                                              null,
                                                              0),
                                    request);
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
        SecurityManager
            securityManager =
            getSecurityManager(request);
        Integer
            clientId =
            getClientIdFromRequestOrSession(request);
        if (securityManager.getIsSystemAdministrator()
            && clientId
               == null)
        {
            request.getSession()
                .setAttribute(SessionKeys.PRIMARY_MENU,
                              PrimaryMenu.getInstanceNone());
        }
        else
        {
            setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME,
                           request);
        }
        PrimaryMenu
            primaryMenu =
            (PrimaryMenu) request.getSession()
                .getAttribute(SessionKeys.PRIMARY_MENU);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_ACTIVITY_MENU_ITEM_NAME);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_ACTIVITY_MENU_NAME,
                                      getClientIdFromRequestOrSession(request));
        String
            visualType =
            request.getParameter("type");
        if (visualType.equals("graph"))
        {
            sec.setCurrentItem(SecondaryMenu.X_CLIENT_ACTIVITY_GRAPH_MENU_NAME);
        }
        else if (visualType.equals("grid"))
        {
            sec.setCurrentItem(SecondaryMenu.X_CLIENT_ACTIVITY_GRID_MENU_NAME);
        }
        else
        {
            sec.setCurrentItem(SecondaryMenu.X_CLIENT_ACTIVITY_BUBBLES_MENU_NAME);
        }
        setSecondaryMenu(sec,
                         request);
    }
}