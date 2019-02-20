package com.sehinc.security.action.user;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.user.UserService;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserChangePasswordPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserChangePasswordPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In userAction");
        UserForm
            userForm =
            (UserForm) form;
        SecurityManager
            securityManager =
            getSecurityManager(request);
        setSessionAttribute(SessionKeys.USER_CREATE_REDIRECT,
                            "false", request);
        Integer
            editUserId =
            getUserId(request);
        if (editUserId
            == null
            || editUserId.intValue()
               == 0)
        {
            LOG.error("User not found on request or session");
            addError(new ActionMessage("error.user.not.found.in.session"), request);
            return mapping.findForward("user.view.page");
        }
        UserData
            editUser =
            UserService.getUser(editUserId);
        if (editUser
            == null)
        {
            LOG.error("Could not load user ID "
                      + editUserId.toString());
            addError(new ActionMessage("error.load.user.failed",
                                       editUserId.toString()), request);
            return mapping.findForward("user.view.page");
        }
        setUserIdInSession(editUserId, request);
        if (!securityManager.getIsClientAdministrator()
            && !editUserId.equals(getUserValue(request).getId()))
        {
            LOG.error("User ID "
                      + getUserValue(request).getId()
                      + " is not authorized to change the password of User ID "
                      + editUserId);
            addError(new ActionMessage("edit.user.unauthorized",
                                       editUser.getUsername()), request);
            return mapping.findForward("user.view.page");
        }
        userForm.setFirstName(editUser.getFirstName());
        userForm.setLastName(editUser.getLastName());
        userForm.setUsername(editUser.getUsername());
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
            SecondaryMenu.getInstance(SecondaryMenu.USER_VIEW_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_USER_PASSWORD_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}