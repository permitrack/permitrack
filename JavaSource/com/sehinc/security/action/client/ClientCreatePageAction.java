package com.sehinc.security.action.client;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.BaseAction;
import com.sehinc.security.action.base.SessionKeys;
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
import java.util.Iterator;
import java.util.List;

public class ClientCreatePageAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientCreatePageAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientCreatePageAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        String
            strError =
            new String(strLog);
        List
            lstState;
        List
            lstClient;
        Iterator
            itClient;
        ClientData
            objClient;
        String
            strClient =
            new String("");
        LOG.info(strLog
                 + "in action");
        ClientForm
            clientForm =
            (ClientForm) form;
        clientForm.reset();
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("create.client.unauthorized"));
            addError(new ActionMessage("create.client.unauthorized"), request);
            return mapping.findForward("client.list.page");
        }
        try
        {
            lstState =
                CapState.findNonArmedForcesStates();
            LOG.debug(strLog
                      + "state list count "
                      + lstState.size());
            setSessionAttribute(SessionKeys.STATE_LIST,
                                lstState, request);
        }
        catch (Exception e)
        {
            strError =
                strError
                + ApplicationResources.getProperty("create.client.state.list.failed")
                + ". Message: "
                + e.getMessage();
            LOG.debug(strError);
            addError(new ActionMessage("create.client.state.list.failed"), request);
            return mapping.findForward("client.list.page");
        }
        // Get a list of all of the CAP users and create a string of the user names.  This
        // string of user names is used by the html page java script to check if the new
        // user name is a duplicate user name.
        lstClient =
            ClientData.findAll();
        if (lstClient
            != null)
        {
            if (!lstClient.isEmpty())
            {
                LOG.debug(strLog
                          + "Role List All Count active and inactive: "
                          + lstClient.size());
                itClient =
                    lstClient.iterator();
                while (itClient.hasNext())
                {
                    objClient =
                        (ClientData) itClient.next();
                    try
                    {
                        if (objClient.getStatus()
                            != null)
                        {
                            if (!objClient.getStatus()
                                .getCode()
                                .equals(StatusCodeData.STATUS_CODE_DELETED))
                            {
                                strClient =
                                    strClient
                                    + objClient.getShortName()
                                    + ",";
                            }
                        }
                    }
                    catch (Exception s)
                    {
                        strError =
                            strError
                            + ApplicationResources.getProperty("create.client.client.list.failed")
                            + ". Message: "
                            + s.getMessage();
                        LOG.debug(strError);
                        addError(new ActionMessage("create.client.client.list.failed"), request);
                        return mapping.findForward("client.list.page");
                    }
                }
            }
        }
        LOG.debug(strLog
                  + "Client List String: "
                  + strClient);
        setSessionAttribute(SessionKeys.CLIENT_CLIENT_LIST,
                            strClient, request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_LIST_MENU_NAME);
        setSecondaryMenu(secondaryMenu, request);
        secondaryMenu.setCurrentItem(SecondaryMenu.X_CLIENT_CREATE_MENU_ITEM_NAME);
    }
}