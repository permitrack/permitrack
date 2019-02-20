package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserCreateAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In userAction");
        UserForm
            objNewUserForm =
            (UserForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("cancel.action"), request);
            return mapping.findForward("user.list.page");
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            addError(new ActionMessage("create.user.unauthorized"), request);
            return mapping.findForward("user.list.page");
        }
        Integer
            mintClientId =
            getClientIdFromRequestOrSession(request);
        if (mintClientId
            == null
            || mintClientId
               == 0)
        {
            LOG.error("Could not find Client ID on request or session");
            return mapping.findForward("user.list.page");
        }
        if ((!mintClientId.equals(CommonConstants.SEH_CLIENT_ID))
            && (objNewUserForm.getGroupId()
                != null
                && objNewUserForm.getGroupId()
            .equals(SecurityManager.SYSTEM_ADMINISTRATOR_SECURITY_LEVEL_ID)))
        {
            addError(new ActionMessage("create.user.type.unauthorized",
                                       "System Administrator"), request);
            return mapping.findForward("user.list.page");
        }
        UserData
            newUser;
        try
        {
            if (objNewUserForm.getId()
                != null
                && objNewUserForm.getId()
                   > 0)
            {
                newUser =
                    UserData.findById(objNewUserForm.getId());
                associateUserToClient(mintClientId,
                                      newUser.getContactId(),
                                      newUser);
                ClientModule
                    clientModule =
                    ClientModule.findClientModule(mintClientId,
                                                  CommonConstants.EROSION_CONTROL_MODULE);
                if (clientModule
                    != null)
                {
                    CapRole
                        role =
                        CapRole.findByCodeAndModule("DEFAULT",
                                                    clientModule.getModuleId(),
                                                    mintClientId);
                    createUserRole(role.getId(),
                                   newUser);
                }
                clientModule =
                    ClientModule.findClientModule(mintClientId,
                                                  CommonConstants.ENVIRONMENT_MODULE);
                if (clientModule
                    != null)
                {
                    CapRole
                        role =
                        CapRole.findByCodeAndModule("DEFAULT",
                                                    clientModule.getModuleId(),
                                                    mintClientId);
                    createUserRole(role.getId(),
                                   newUser);
                }
            }
            else
            {
                newUser =
                    createUser(objNewUserForm,
                               mintClientId,
                               getUserValue(request));
            }
        }
        catch (Exception e)
        {
            LOG.error("Failed to create new user");
            LOG.error(e.getStackTrace());
            addError(new ActionMessage("error.create.user.failed"), request);
            return mapping.findForward("user.list.page");
        }
        addMessage(new ActionMessage("create.user.success",
                                     newUser.getUsername()), request);
        try
        {
            sendNewUserEmail(newUser,
                             mintClientId);
        }
        catch (Exception e)
        {
            LOG.error("Failed to send new user email");
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.new.user.email.failed",
                                       newUser.getEmailAddress()), request);
        }
        setUserIdInSession(newUser.getId(), request);
        return mapping.findForward("continue");
    }
}