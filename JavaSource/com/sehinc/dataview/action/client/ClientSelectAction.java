/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: ClientSelectAction.java,v $
 *
 */

package com.sehinc.dataview.action.client;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.dataview.action.base.RequestKeys;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ClientSelectAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientSelectAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        ClientForm
            clientForm =
            (ClientForm) form;
        LOG.info("In ClientSelectAction");
        //get the ID from the request
        Integer
            clientId;
        if (request.getParameter(RequestKeys.DV_CLIENT_ID)
            != null)
        {
            clientId =
                new Integer(request.getParameter(RequestKeys.DV_CLIENT_ID));
            LOG.debug("Got client ID from request "
                      + clientId);
        }
        else
        {
            //get the client from the form
            clientId =
                clientForm.getClientId();
            LOG.debug("Got client ID from form "
                      + clientId);
        }
        //If the client ID was not found, use the one in the session
        if (clientId
            == null
            || clientId
               == 0)
        {
            ClientValue
                loginClientValue =
                getClientValue(request);
            if (loginClientValue
                != null)
            {
                clientId =
                    loginClientValue.getId();
                LOG.debug("Got client ID from session "
                          + clientId);
            }
            else
            {
                LOG.error(PortalResources.getProperty("error.no.client.selected"));
                addError(new ActionMessage("error.no.client.selected"), request);
                return mapping.findForward("dataview");
            }
        }
        //Get the client data
        ClientData
            clientData =
            ClientService.getActiveClientById(clientId);
        if (clientData
            == null)
        {
            LOG.error(PortalResources.getProperty("error.client.not.exist"));
            addError(new ActionMessage("error.client.not.exist"), request);
            return mapping.findForward("dataview");
        }
        LOG.debug("Got client Data by ID "
                  + clientData.getId());
        /*
                ClientValue
                    clientValue =
                    new ClientValue(clientData);
        */
        // Get the user from the session
        UserValue
            userValue =
            getUserValue(request);
        UserData
            userData =
            UserData.findById(userValue.getId());
        if (userData
            == null
            || !userData.isActive())
        {
            Object[]
                parameters =
                {userValue.getId()};
            LOG.error(PortalResources.getProperty("error.invalid.user",
                                                  parameters));
            addError(new ActionMessage("error.invalid.user",
                                       parameters), request);
            return mapping.findForward("error");
        }
        LOG.debug("Got userData "
                  + userData.getId());
        // Clear the client-specific variables from the session
        /*
                clearClientSessionKeys(request);
        */
        // TODO
        // Set the Client in the request and forward the the DVO redirect page
        //        setCookie(clientId, response);
        request.setAttribute(RequestKeys.DV_CLIENT_ID,
                             clientId.toString());
        LOG.debug("Set dvClientId="
                  + clientId.toString()
                  + " in request");
        //setSessionAttribute(SessionKeys.CLIENT, clientValue, request);
        // Set the Remote Admin indicator in the session
        //        setSessionAttribute(SessionKeys.REMOTE_ADMIN, Boolean.TRUE, request);
        return mapping.findForward("continue");
    }
    /*
        private void setCookie(Integer clientId, HttpServletResponse response)
        {
            LOG.debug("In setCookie()");
            Cookie
                cookie =
                new Cookie(CookieKeys.DV_CLIENT_ID,
                           clientId.toString());
            LOG.debug("Adding cookie to response");
            response.addCookie(cookie);
        }
    */
    /*
        private void clearClientSessionKeys(HttpServletRequest request)
        {
            // Set the client-specific variables from the session
            //setSessionAttribute(SessionKeys.USER_ADMIN, null, request);
        }
    */

/*
    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
*/

/*
    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }
*/
}