package com.sehinc.security.action.contact;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ContactListPageAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactListPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward;
        try
        {
            ContactListForm
                contactListForm =
                (ContactListForm) form;
            Integer
                intClientId =
                getClientIdFromRequestOrSession(request);
            if (intClientId
                == null)
            {
                intClientId =
                    0;
            }
            List
                contacts =
                getClientContactList(intClientId,
                                     StatusCodeData.STATUS_CODE_ACTIVE,
                                     contactListForm);
            request.getSession()
                .setAttribute(SessionKeys.CONTACT_LIST_ACTIVE,
                              contacts);
            setDisplayColumns(request);
            this.setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
            strForward =
                "continue";
        }
        catch (Exception eActive)
        {
            LOG.debug(eActive.getMessage());
            strForward =
                "error";
        }
        return mapping.findForward(strForward);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        PrimaryMenu
            primaryMenu =
            (PrimaryMenu) request.getSession()
                .getAttribute(SessionKeys.PRIMARY_MENU);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_CONTACT_MENU_ITEM_NAME);
        SecondaryMenu
            sec =
            SecondaryMenu.getInstance(SecondaryMenu.CONTACT_LIST_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CONTACT_LIST_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }

    public static List getClientContactList(Integer intClientId, String statusCode, ContactListForm form)
        throws Exception
    {
        List
            lstC;
        try
        {
            lstC =
                getContactList(intClientId,
                               statusCode,
                               form);
            Iterator
                ci =
                lstC.iterator();
            if (!ci.hasNext())
            {
                LOG.debug("Client "
                          + intClientId.toString()
                          + " does not have contacts associated with it of status "
                          + statusCode);
            }
        }
        catch (Exception e)
        {
            String
                strMessage =
                "Error getting contact list.  ClientId: "
                + intClientId.toString()
                + " statusCode: "
                + statusCode;
            LOG.debug(strMessage
                      + e.getMessage());
            throw new Exception(strMessage
                                + e.getMessage());
        }
        return lstC;
    }

    private static List getContactList(Integer intClientId, String statusCode, ContactListForm form)
    {
        String
            sortColumn =
            form.getSortColumn();
        String
            sortDirection =
            form.getSortDirection();
        List
            contactList;
        if (sortColumn.equals(CapContact.CONTACT_NAME))
        {
            if (sortDirection.equals(CapContact.DESCENDING))
            {
                contactList =
                    CapContact.findByNameDesc(intClientId,
                                              statusCode);
            }
            else
            {
                contactList =
                    CapContact.findByNameAsc(intClientId,
                                             statusCode);
            }
        }
        else if (sortColumn.equals(CapContact.ORGANIZATION_NAME))
        {
            if (sortDirection.equals(CapContact.DESCENDING))
            {
                contactList =
                    CapContact.findByOrgDesc(intClientId,
                                             statusCode);
            }
            else
            {
                contactList =
                    CapContact.findByOrgAsc(intClientId,
                                            statusCode);
            }
        }
        else
        {
            contactList =
                CapContact.findByClientContactListAndStatus(intClientId,
                                                            statusCode);
        }
        return contactList;
    }

    private void setDisplayColumns(HttpServletRequest request)
    {
        TreeSet
            contactListItems =
            new TreeSet();
        contactListItems.add(ContactListItem.CONTACT_NAME);
        contactListItems.add(ContactListItem.ORGANIZATION_NAME);
        request.getSession()
            .setAttribute(SessionKeys.CONTACT_LIST_ITEMS,
                          contactListItems);
    }
}