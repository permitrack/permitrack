package com.sehinc.security.action.contact;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.code.ContactTypeData;
import com.sehinc.common.db.contact.CapContactOrganization;
import com.sehinc.common.db.user.CapState;
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

public class ContactCreatePageAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactCreatePageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strForward;
        String
            strLog;
        String
            strError;
/*
        strForward =
            "continue";
*/
        String
            strMod =
            "com.sehinc.security.action.contact.ContactCreatePageAction. ";
        strLog =
            strMod
            + "userAction() ";
        strError =
            strLog;
        LOG.info(strLog
                 + "in action");
        LOG.debug(strLog
                  + "create new contact page is begin redirected to.");
        ContactForm
            objC =
            (ContactForm) form;
        objC.reset();
        try
        {
            Integer
                intClientId =
                getClientIdFromRequestOrSession(request);
            List
                lstC =
                CapContactOrganization.findAllActiveByClientId(intClientId);
            setSessionAttribute(SessionKeys.CONTACT_ORGANIZATION_LIST_ACTIVE,
                                lstC, request);
            ClientModule
                escModule =
                ClientModule.findClientModule(intClientId,
                                              CommonConstants.EROSION_CONTROL_MODULE);
            if (escModule
                != null)
            {
                objC.setCanViewESC(true);
            }
            else
            {
                objC.setCanViewESC(false);
            }
            setSessionAttribute(SessionKeys.CONTACT_FORM,
                                objC, request);
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
                      "Unable to get list of states for the Create Contact page.",
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
                      "Unable to get list of contact organization addresses for the Create Contact page.",
                      e);
            }
            request.setAttribute(com.sehinc.security.action.base.SessionKeys.CONTACT_TYPE_LIST,
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
            SecondaryMenu.getInstance(SecondaryMenu.CONTACT_LIST_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_CONTACT_CREATE_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}