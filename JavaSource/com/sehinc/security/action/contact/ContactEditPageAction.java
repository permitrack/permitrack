package com.sehinc.security.action.contact;

import com.sehinc.common.db.code.ContactTypeData;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ContactEditPageAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactEditPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "com.sehinc.security.action.user.ContactEditPageAction. ";
        String
            strLog =
            new String(strMod
                       + "userAction() ");
        String
            strError =
            new String(strLog);
        LOG.info(strLog
                 + "in action");
        try
        {
            Integer
                intClientId =
                getClientIdFromRequestOrSession(request);
            Integer
                intContactId =
                getContactId(request);
            if (intContactId
                == null)
            {
                Throw(strLog,
                      "Contact Id ["
                      + intContactId
                      + "] does not exist in the Cap Contact database table.");
            }
            List
                lstC =
                CapContactOrganization.findAllActiveByClientId(intClientId);
            setSessionAttribute(SessionKeys.CONTACT_ORGANIZATION_LIST_ACTIVE,
                                lstC, request);
            try
            {
                lstC =
                    CapState.findNonArmedForcesStates();
                setSessionAttribute(SessionKeys.STATE_LIST,
                                    lstC, request);
            }
            catch (Exception e)
            {
                Throw(strLog,
                      "Unable to get list of states for the Edit Contact page.",
                      e);
            }
            try
            {
                lstC =
                    CapContactOrganization.findByClientIdWithAddress(intClientId);
                setSessionAttribute(SessionKeys.CONTACT_ORGANIZATION_ADDRESSES_LIST_ACTIVE,
                                    lstC, request);
            }
            catch (Exception e)
            {
                Throw(strLog,
                      "Unable to get list of contact organization addresses for the Edit Contact page.",
                      e);
            }
            try
            {
                ContactForm
                    objCF =
                    (ContactForm) form;
                ContactService.getContactForm(objCF,
                                              intClientId,
                                              intContactId);
                this.setContactIdInSession(intContactId, request);
            }
            catch (Exception c)
            {
                Throw(strLog,
                      "Error getting contact form by contact and client id.",
                      c);
            }
        }
        catch (Exception eActive)
        {
            strError =
                strError
                + "Error.  Message: "
                + eActive.getMessage();
            LOG.debug(strError);
            return mapping.findForward("contact.list.page");
        }
        request.setAttribute(com.sehinc.security.action.base.SessionKeys.CONTACT_TYPE_LIST,
                             SpringServiceLocator.getLookupService()
                                 .fetchCodesDisplayable(ContactTypeData.class));
        this.setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
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
            SecondaryMenu.getInstance(SecondaryMenu.CONTACT_VIEW_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CONTACT_EDIT_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}