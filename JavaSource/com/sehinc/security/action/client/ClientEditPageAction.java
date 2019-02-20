package com.sehinc.security.action.client;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
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
import java.sql.SQLException;
import java.util.List;

import static com.sehinc.security.action.navigation.PrimaryMenu.SECURITY_MENU_NAME;

public class ClientEditPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientEditPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientEditPageAction. ";
        String
            strLog =
            strMod
            + "clientAction ";
        ClientForm
            objEditClient =
            (ClientForm) form;
        Integer
            intClientId;
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("update.client.unauthorized"));
            addError(new ActionMessage("update.client.unauthorized"), request);
            return mapping.findForward("client.view.page");
        }
        intClientId =
            getClientIdFromRequestOrSession(request);
        if (intClientId
            != 0)
        {
            this.setClientInSession(intClientId, request);
            try
            {
                ClientService.getClientFormById(objEditClient,
                                                intClientId,
                                                getUserValue(request));
                objEditClient.checkForHTML();
                int
                    secLev =
                    SessionService.getSecurityLevel(request);
                if (secLev
                    >= 100)
                {
                    objEditClient.setBlnSystemAdmin(true);
                }
                else
                {
                    objEditClient.setBlnSystemAdmin(false);
                }
                try
                {
                    List
                        stateList =
                        CapState.findNonArmedForcesStates();
                    LOG.debug(strLog
                              + "state list count "
                              + stateList.size());
                    setSessionAttribute(SessionKeys.STATE_LIST,
                                        stateList, request);
                }
                catch (Exception s)
                {
                    LOG.debug(ApplicationResources.getProperty("create.client.state.list.failed"));
                    addError(new ActionMessage("create.client.state.list.failed"), request);
                    return mapping.findForward("client.view.page");
                }
                List
                    lstC;
                try
                {
                    lstC =
                        CapContact.findByClientContactListAndStatus(intClientId,
                                                                    StatusCodeData.STATUS_CODE_ACTIVE);
                    LOG.debug(strLog
                              + "contact list count "
                              + lstC.size());
                    setSessionAttribute(SessionKeys.CONTACT_LIST_ACTIVE,
                                        lstC, request);
                }
                catch (Exception contactEx)
                {
                    LOG.debug(ApplicationResources.getProperty("create.contact.list.failed"));
                    addError(new ActionMessage("create.contact.list.failed"), request);
                    return mapping.findForward("client.view.page");
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

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenu(SECURITY_MENU_NAME, request);
        setPrimaryMenuItem(request);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.CLIENT_VIEW_MENU_NAME,
                                      getClientIdFromRequestOrSession(request));
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_EDIT_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}