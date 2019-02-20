package com.sehinc.security.action.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContactShort;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.BaseAction;
import com.sehinc.security.action.base.RequestKeys;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientListPageAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientListPageAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Integer
            clientId;
        Integer
            intUserId;
        HttpSession
            session =
            request.getSession();
        SecurityManager
            securityManager =
            getSecurityManager(request);
        intUserId =
            securityManager.getUserID();
        if (securityManager.getIsSystemAdministrator())
        {
            LOG.debug("User is a system administrator.");
            try
            {
                ActionForward
                    f =
                    redirect(request.getQueryString()
                             != null
                                 ? request.getQueryString()
                        .hashCode()
                                 : 0,
                             "/clientlistpageaction.do?",
                             request);
                if (f
                    != null)
                {
                    return f;
                }
                getList(request,
                        response,
                        session);
            }
            catch (Exception eActive)
            {
                LOG.error("Unable to get list of active clients for the Client List page. Message: "
                          + eActive.getMessage());
                return mapping.findForward(CommonConstants.FORWARD_ERROR);
            }
        }
        else if (securityManager.getIsClientAdministrator())
        {
            LOG.debug("User is of type Client Administrator");
            try
            {
                return getList(intUserId,
                               mapping,
                               session,
                               request);
            }
            catch (Exception e)
            {
                String
                    strError =
                    "Error getting client administrator information.  <br>Message:<br> "
                    + e.getMessage();
                LOG.debug(strError);
                return mapping.findForward(CommonConstants.FORWARD_ERROR);
            }
        }
        else
        {
            LOG.debug("User is of type User");
            try
            {
                intUserId =
                    securityManager.getUserID();
                this.setUserIdInSession(intUserId,
                                        request);
                try
                {
                    List
                        clients =
                        ClientService.getClients(intUserId);
                    if (clients.size()
                        > 1)
                    {
                        List
                            lstClient =
                            clients;
                        lstClient =
                            updateClientInfo(lstClient);
                        session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST,
                                             lstClient);
                        session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST_TITLE,
                                             "My Clients");
                        return mapping.findForward("nonAdmin");
                    }
                    else
                    {
                        ClientData
                            cUserData =
                            (ClientData) clients.get(0);
                        clientId =
                            cUserData.getId();
                        if (clientId
                            != null)
                        {
                            this.setClientInSession(clientId,
                                                    request);
                            if (!securityManager.getClientID()
                                .equals(clientId))
                            {
                                securityManager.setClientID(clientId);
                            }
                            return mapping.findForward("user.view.page");
                        }
                        else
                        {
                            Throw("User "
                                  + intUserId.toString()
                                  + " is not associated with any client.");
                        }
                    }
                }
                catch (Exception e)
                {
                    String
                        strError =
                        "Error getting client information.  <br>Message:<br> "
                        + e.getMessage();
                    LOG.debug(strError);
                    return mapping.findForward(CommonConstants.FORWARD_ERROR);
                }
            }
            catch (Exception e)
            {
                String
                    strError =
                    "Error getting client administrator information.  <br>Message:<br> "
                    + e.getMessage();
                LOG.debug(strError);
                return mapping.findForward(CommonConstants.FORWARD_ERROR);
            }
        }
        removeSessionAttribute(SessionKeys.SEC_CLIENT, request);
        return mapping.findForward("continue");
    }

    private void getList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
        throws Exception
    {
        Integer
            clientId;
        if (session.getAttribute(SessionKeys.MODULE_LIST)
            == null)
        {
            // This can be cached, modules almost never change
            List
                moduleList =
                new ArrayList();
            moduleList.add(CapModule.findByCode("SW"));
            moduleList.add(CapModule.findByCode("EC"));
            moduleList.add(CapModule.findByCode("DV"));
            moduleList.add(CapModule.findByCode("EV"));
            session.setAttribute(SessionKeys.MODULE_LIST,
                                 moduleList);
        }
        List
            lstClient =
            new ArrayList();
        CapModule
            moduleEC =
            CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
        // Always need ActivePrimary list (for TreeView)
        List
            lstClientPrimary =
            ClientService.getActiveClientsByModuleAndType(moduleEC,
                                                          CommonConstants.PRIMARY_CLIENT);
        String
            listType =
            "All Active Primary Clients";
        session.setAttribute(SessionKeys.ACTIVE_CLIENT_PRIMARY_LIST,
                             lstClientPrimary);
        // Always need ActiveSecondary list (for TreeView)
        List
            lstClientSecondary =
            ClientService.getActiveClientsByModuleAndType(moduleEC,
                                                          CommonConstants.SECONDARY_CLIENT);
        session.setAttribute(SessionKeys.ACTIVE_CLIENT_SECONDARY_LIST,
                             lstClientSecondary);
        String
            moduleCode =
            getModuleIdFromRequest(request);
        if (!moduleCode.equals(""))
        {
            CapModule
                module =
                CapModule.findByCode(moduleCode);
            if (!moduleCode.equalsIgnoreCase(CommonConstants.EROSION_CONTROL_MODULE))
            {
                lstClient =
                    ClientService.getActiveClientsByModule(module);
                listType =
                    "All Active Clients for "
                    + module.getName();
            }
            else
            {
                lstClient =
                    ClientService.getActiveClientsByModuleAndType(module,
                                                                  CommonConstants.PRIMARY_CLIENT);
                listType =
                    "All Active Primary Clients for "
                    + module.getName();
            }
            response.setHeader("Set-Cookie",
                               "jstree_select=%23module_"
                               + moduleCode);
        }
        else
        {
            clientId =
                getParamInt(RequestKeys.CLIENT_ID,
                            request);
            if (clientId.equals(-1))
            {
                lstClient =
                    ClientData.findActive();
                listType =
                    "All Active Clients";
            }
            else if (clientId
                     > 0)
            {
                if (request.getParameter("secondary")
                    != null)
                {
                    lstClient =
                        ClientService.getPrimaryClientsFromSecondaryClient(clientId);
                    listType =
                        "Related Primary Clients"
                        + (SessionService.getClientValue(request,
                                                         CommonConstants.SECURITY_MODULE)
                           != null
                               ? " for "
                                 + SessionService.getClientValue(request,
                                                                 CommonConstants.SECURITY_MODULE)
                            .getName()
                               : "");
                }
                else
                {
                    lstClient =
                        ClientService.getActiveSecondaryClients(clientId);
                    listType =
                        "Active Secondary Clients"
                        + (SessionService.getClientValue(request,
                                                         CommonConstants.SECURITY_MODULE)
                           != null
                               ? " for "
                                 + SessionService.getClientValue(request,
                                                                 CommonConstants.SECURITY_MODULE)
                            .getName()
                               : "");
                }
                response.setHeader("Set-Cookie",
                                   "jstree_select=%23client_"
                                   + clientId);
            }
            else if (request.getParameter("client_status")
                     != null)
            {
                if (request.getParameter("client_status")
                    .equals("nonactive"))
                {
                    lstClient =
                        ClientData.findNonActive();
                    listType =
                        "Non-Active Clients";
                    response.setHeader("Set-Cookie",
                                       "jstree_select=%23phtml_2_1");
                }
                else if (request.getParameter("client_status")
                    .equals("deleted"))
                {
                    lstClient =
                        ClientData.findByStatus(StatusCodeData.STATUS_CODE_DELETED);
                    listType =
                        "Deleted Clients";
                    response.setHeader("Set-Cookie",
                                       "jstree_select=%23phtml_2_2");
                }
            }
        }
        lstClient =
            updateClientInfo(lstClient);
        session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST,
                             lstClient);
        session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST_TITLE,
                             listType);
    }

    private ActionForward getList(Integer intUserId, ActionMapping mapping, HttpSession session, HttpServletRequest request)
        throws Exception
    {
        List
            clients =
            ClientService.getClients(intUserId);
        if (clients.size()
            > 1)
        {
            List
                lstClient =
                clients;
            lstClient =
                updateClientInfo(lstClient);
            session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST,
                                 lstClient);
            session.setAttribute(RequestKeys.CURRENT_CLIENT_LIST_TITLE,
                                 "My Clients");
            return mapping.findForward("nonAdmin");
        }
        else
        {
            // The the client administrators client information
            ClientData
                cUserData =
                (ClientData) clients.get(0);
            Integer
                clientId =
                cUserData.getId();
            if (clientId
                != null)
            {
                this.setClientInSession(clientId,
                                        request);
                /*
                                if (!getSecurityManager().getClientID()
                                    .equals(clientId))
                                {
                */
                getSecurityManager(request).setClientID(clientId);
                //                }
                return mapping.findForward("client.view.page");
            }
            else
            {
                Throw("User "
                      + intUserId.toString()
                      + " is not associated with any client.");
            }
        }
        return null;
    }

    public static List updateClientInfo(List lstClient)
        throws Exception
    {
        for (
            int
                i =
                0; i
                   < lstClient.size(); i++)
        {
            ClientData
                clientData =
                (ClientData) lstClient.get(i);
            Integer
                clientId =
                clientData.getId();
            clientData.setShortName("Not Assigned");
            clientData.setContactEmail("");
            try
            {
                if (clientData.getContactId()
                    != null)
                {
                    CapContactShort
                        contact =
                        new CapContactShort(clientData.getContactId());
                    contact.load();
                    if (contact.getEmail()
                        != null
                        && !contact.getEmail()
                        .equalsIgnoreCase("null"))
                    {
                        clientData.setContactEmail(contact.getEmail());
                    }
                }
            }
            catch (Exception e)
            {
                LOG.error("Couldn't get contact for client id "
                          + clientId);
            }
            clientData.setComment("");
            try
            {
                UserData
                    userData =
                    UserData.findLastLoginByClientIdAndStatusCode(clientId,
                                                                  StatusCodeData.STATUS_CODE_ACTIVE);
                if (userData
                    != null
                    && userData.getLastLoginDate()
                       != null)
                {
                    clientData.setComment(DateFormat.getDateInstance()
                                              .format(userData.getLastLoginDate()) + " (" + userData.getFirstName() + " " + userData.getLastName() +  ")");
                }
            }
            catch (Exception e)
            {
                LOG.error("Couldn't get last login date for client id "
                          + clientId);
            }
            lstClient.set(i,
                          clientData);
        }
        return lstClient;
    }

    @Override
    protected String getSavedNode(Integer id, HttpServletRequest request)
    {
        String
            savedNode =
            null;
        Cookie[]
            cookies =
            request.getCookies();
        for (Cookie cookie : cookies)
        {
            if (cookie.getName()
                    .equals("jstree_select")
                && cookie.getValue()
                   != null)
            {
                String
                    value =
                    cookie.getValue()
                        .replaceAll("%23",
                                    "");
                if (value.equals("module_SW")
                    && (request.getParameter("module")
                        == null
                        || !request.getParameter("module")
                    .equals("SW")))
                {
                    savedNode =
                        "module=SW";
                }
                else if (value.equals("module_EC")
                         && (request.getParameter("module")
                             == null
                             || !request.getParameter("module")
                    .equals("EC")))
                {
                    savedNode =
                        "module=EC";
                }
                else if (value.equals("module_DV")
                         && (request.getParameter("module")
                             == null
                             || !request.getParameter("module")
                    .equals("DV")))
                {
                    savedNode =
                        "module=DV";
                }
                else if (value.equals("module_EV")
                         && (request.getParameter("module")
                             == null
                             || !request.getParameter("module")
                    .equals("EV")))
                {
                    savedNode =
                        "module=EV";
                }
                else if (value.startsWith("client_")
                         && (request.getParameter("client_id")
                             == null
                             || !request.getParameter("client_id")
                    .equals(value.substring("client_".length()))))
                {
                    savedNode =
                        "client_id="
                        + value.substring("client_".length());
                }
                else if (value.equals("phtml_2_1")
                         && (request.getParameter("client_status")
                             == null
                             || !request.getParameter("client_status")
                    .equals("nonactive")))
                {
                    savedNode =
                        "client_status=nonactive";
                }
                else if (value.equals("phtml_2_2")
                         && (request.getParameter("client_status")
                             == null
                             || !request.getParameter("client_status")
                    .equals("deleted")))
                {
                    savedNode =
                        "client_status=deleted";
                }
            }
        }
        if (savedNode
            != null
            && !(savedNode.hashCode()
                 == id))
        {
            return savedNode;
        }
        return null;
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        request.getSession()
            .setAttribute(SessionKeys.PRIMARY_MENU,
                          PrimaryMenu.getInstanceNone());
        PrimaryMenu
            primaryMenu =
            (PrimaryMenu) request.getSession()
                .getAttribute(SessionKeys.PRIMARY_MENU);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_CLIENT_MENU_ITEM_NAME);
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_LIST_MENU_NAME);
        request.getSession()
            .setAttribute(SessionKeys.SECONDARY_MENU,
                          secondaryMenu);
        secondaryMenu.setCurrentItem(SecondaryMenu.X_CLIENT_SELECT_MENU_ITEM_NAME);
    }
}
