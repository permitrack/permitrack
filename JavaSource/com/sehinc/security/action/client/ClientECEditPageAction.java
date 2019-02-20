package com.sehinc.security.action.client;

import com.sehinc.common.db.client.CapClientTypeInfo;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.value.contact.ContactShortValue;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class ClientECEditPageAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientECEditPageAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.client.ClientECEditPageAction. ";
        String
            strLog =
            new String(strMod
                       + "clientAction ");
        Integer
            intClientId;
        List
            lstT;
        List
            lstCapShortContact =
            new ArrayList();
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.debug(ApplicationResources.getProperty("edit.ec.unauthorized"));
            addError(new ActionMessage("edit.ec.unauthorized"), request);
            return mapping.findForward("client.ec.view.page");
        }
        intClientId =
            getClientIdFromRequestOrSession(request);
        Iterator
            contactIter =
            CapContact.getAllActiveClientUserContacts(intClientId)
                .iterator();
        while (contactIter.hasNext())
        {
            CapContact
                contact =
                (CapContact) contactIter.next();
            lstCapShortContact.add(new ContactShortValue(contact));
        }
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_CONTACT_LIST,
                            lstCapShortContact, request);
        lstT =
            CapClientTypeInfo.findAll();
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_CLIENT_TYPE_LIST,
                            lstT, request);
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_PUBLIC_REPORT_URL,
                            getPublicMapUrl(intClientId, request), request);
        setSessionAttribute(SessionKeys.CLIENT_EC_FORM_PRIVATE_REPORT_URL,
                            getPrivateMapUrl(intClientId, 0, request), request);
        ClientECForm
            objEC =
            (ClientECForm) form;
        ClientService.getClientECForm(objEC,
                                      getClientIdFromRequestOrSession(request));
        objEC.setBlnSystemAdmin(securityManager.getIsSystemAdministrator());
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
        sec.setCurrentItem(SecondaryMenu.X_CLIENT_EC_EDIT_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}