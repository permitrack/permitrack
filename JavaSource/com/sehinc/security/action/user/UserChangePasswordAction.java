package com.sehinc.security.action.user;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.security.action.base.SessionKeys;
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

public class UserChangePasswordAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserChangePasswordAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        UserForm
            userForm =
            (UserForm) form;
        LOG.info("UserChangePasswordAction: in userAction()");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        setSessionAttribute(SessionKeys.USER_CREATE_REDIRECT,
                            "false", request);
        if (isCancelled(request))
        {
            LOG.info("Request was CANCELED");
            return mapping.findForward("user.view.page");
        }
        UserData
            user =
            null;
        UserValue
            loggedInUserValue;
        try
        {
            user =
                new UserData(getUserId(request));
            if (!user.load())
            {
                LOG.error("Error loading userId = "
                          + user.getId());
                throw new Exception("Error loading userId = "
                                    + user.getId());
            }
            loggedInUserValue =
                getUserValue(request);
        }
        catch (Exception t)
        {
            LOG.error("Error loading userId = "
                      + user.getId());
            throw t;
        }
        if (!securityManager.getIsClientAdministrator()
            && !user.getId()
            .equals(loggedInUserValue.getId()))
        {
            LOG.debug(ApplicationResources.getProperty("error.security.unauthorized.password.change"));
            addError(new ActionMessage("error.security.unauthorized.password.change"), request);
            return mapping.findForward("user.view.page");
        }
        String
            newPassword =
            userForm.getNewPassword();
        String
            oldPassword =
            user.getPassword();
        if (newPassword.equals(oldPassword))
        {
            LOG.debug(ApplicationResources.getProperty("error.security.password.change.identical"));
            addError(new ActionMessage("error.security.password.change.identical"), request);
            return mapping.findForward("user.view.page");
        }
        try
        {
            user.setPassword(userForm.getNewPassword());
            user.update(loggedInUserValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {user.getId()};
            LOG.error(ApplicationResources.getProperty("error.security.password.change.failed",
                                                       parameters));
            LOG.error(e);
            addError(new ActionMessage("error.security.password.change.failed",
                                       parameters), request);
            return mapping.findForward("user.view.page");
        }
        try
        {
            sendChangePasswordUserEmail(user,
                                        getClientValue(request).getId());
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {user.getEmailAddress()};
            LOG.error(ApplicationResources.getProperty("error.security.password.change.email.failed",
                                                       parameters));
            LOG.error(e);
            addError(new ActionMessage("error.security.password.change.email.failed",
                                       parameters), request);
            return mapping.findForward("user.view.page");
        }
        return mapping.findForward("continue");
    }
}