package com.sehinc.security.action.contact;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ContactDeleteAction
    extends ContactBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactDeleteAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        String
            strMod =
            "com.sehinc.security.action.contact.ContactDeleteAction. ";
        String
            strLog =
            new String(strMod
                       + "userAction() ");
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "Page denied", request);
            addError(new ActionMessage("delete.contact.unauthorized"), request);
            return mapping.findForward("contact.list.page");
        }
        try
        {
            ContactForm
                objCF =
                (ContactForm) form;
            UserValue
                objUser =
                getUserValue(request);
            CapContact
                contact =
                new CapContact(objCF.getId());
            UserData
                contactUser =
                UserData.findByContactId(objCF.getId());
            if (contactUser
                != null)
            {
                if (contactUser.isActive())
                {
                    String
                        fullName =
                        objCF.getLastName()
                        + ", "
                        + objCF.getFirstName();
                    addMessage(new ActionMessage("delete.contact.failed.user",
                                                 fullName), request);
                    return mapping.findForward("continue");
                }
            }
            if (contact.load())
            {
                contact.setStatusCode(StatusCodeData.STATUS_CODE_INACTIVE);
                contact.update(objUser);
            }
            String
                fullName =
                objCF.getLastName()
                + ", "
                + objCF.getFirstName();
            addMessage(new ActionMessage("delete.contact.success",
                                         fullName), request);
        }
        catch (Exception o)
        {
            Throw(strLog,
                  "Error deleting contact.",
                  o);
            return mapping.findForward("contact.view.page");
        }
        return mapping.findForward("continue");
    }
}