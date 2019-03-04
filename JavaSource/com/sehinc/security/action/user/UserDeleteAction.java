package com.sehinc.security.action.user;

import com.sehinc.common.db.client.ClientUserData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.portal.PortalUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDeleteAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserDeleteAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            LOG.info("User not authorized to delete.");
            addError(new ActionMessage("delete.user.unauthorized"),
                     request);
            return mapping.findForward("user.list.page");
        }
        Integer
            mintUserId =
            getUserId(request);
        if (mintUserId
            == null
            || mintUserId
               == 0)
        {
            LOG.error("User not found on request or session");
            addError(new ActionMessage("error.user.not.found.in.session"),
                     request);
            return mapping.findForward("user.list.page");
        }
        if (mintUserId == 4) {
            LOG.error("Cannot delete user ID "
                    + mintUserId);
            addError(new ActionMessage("error.load.user.failed",
                    mintUserId),
                    request);
            return mapping.findForward("user.list.page");
        }
        try
        {
            UserData
                userData =
                new UserData(mintUserId);
            if (!userData.load())
            {
                LOG.error("Failed to load user ID "
                          + mintUserId);
                addError(new ActionMessage("error.load.user.failed",
                                           mintUserId),
                         request);
                return mapping.findForward("user.list.page");
            }
            if (PortalUtils.userHasMultipleClients(new UserValue(userData)))
            {
                for (Object clientUser : ClientUserData.findByClientIdAndUserId(getClientIdFromRequestOrSession(request)
                                                                                /*userForm.getClientId()*/,
                                                                                mintUserId))
                {
                    ClientUserData
                        clientUserData =
                        (ClientUserData) clientUser;
                    clientUserData.delete();
                }
            }
            else
            {
                userData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                userData.save(getUserValue(request));
                LOG.info("User "
                         + userData.getUsername()
                         + " status changed to deleted.");
                addMessage(new ActionMessage("delete.user.soft",
                                             userData.getUsername()),
                           request);
                CapContact
                    deleteContact =
                    new CapContact(userData.getContactId());
                if (!deleteContact.load())
                {
                    LOG.error("Failed to load contact ID "
                              + userData.getContactId());
                    addError(new ActionMessage("error.load.contact.failed",
                                               userData.getContactId()),
                             request);
                }
                else
                {
                    deleteContact.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                    deleteContact.save(getUserValue(request));
                    LOG.info("Contact ID "
                             + deleteContact.getId()
                             + " status changed to deleted.");
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete user ID "
                      + mintUserId);
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("delete.user.failed",
                                       mintUserId),
                     request);
            return mapping.findForward("user.list.page");
        }
        return mapping.findForward("continue");
    }
}