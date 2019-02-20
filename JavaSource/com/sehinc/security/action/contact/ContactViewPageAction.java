package com.sehinc.security.action.contact;

import com.sehinc.common.db.code.ContactTypeData;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.service.spring.SpringServiceLocator;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ContactViewPageAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactViewPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strForward;
        String
            strMod =
            "com.sehinc.security.action.contact.ContactViewPageAction. ";
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
            this.setContactIdInSession(intContactId, request);
            ContactForm
                objCF =
                (ContactForm) form;
            ContactService.getContactForm(objCF,
                                          intClientId,
                                          intContactId);
            objCF.checkForHTML();
            setSessionAttribute(SessionKeys.CONTACT_FORM,
                                objCF, request);
            request.setAttribute(SessionKeys.CONTACT_TYPE_LIST,
                                 SpringServiceLocator.getLookupService()
                                     .fetchCodesDisplayable(ContactTypeData.class));
            strForward =
                "continue";
            this.setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        }
        catch (Exception eActive)
        {
            strError =
                strError
                + "Error.  Message: "
                + eActive.getMessage();
            LOG.debug(strError);
            strForward =
                "error";
        }
        LOG.debug(strLog
                  + "strForward = "
                  + strForward);
        return mapping.findForward(strForward);
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
        sec.setCurrentItem(SecondaryMenu.X_CONTACT_VIEW_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}