package com.sehinc.portal.action.auth;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.common.action.base.SessionKeys;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.portal.PortalUtils;
import com.sehinc.portal.action.navigation.PortalMenu;
import com.sehinc.portal.action.navigation.PortalMenuItem;
import com.sehinc.portal.resources.PortalResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class LoginAction
    extends BaseActionUnsecure
{
    private static
    Logger
        LOG =
        Logger.getLogger(LoginAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LoginForm
            loginForm =
            (LoginForm) form;
        String
            url =
            loginForm.getRedirect();
        request.getSession()
            .invalidate();
        String
            username =
            loginForm.getUserid();
        String
            password =
            loginForm.getPassword();
        request.setAttribute("rememberme",
                             loginForm.getRememberme());
        ActionForward
            forward =
            loginUser(username,
                      password,
                      request,
                      response,
                      url,
                      null);
        if (forward
            == null
            && SessionService.getSessionAttribute(SessionKeys.USER,
                                                  request)
               == null)
        {
            addError(new ActionMessage("errors.login.failed"),
                     request);
            return mapping.findForward("fail");
        }
        else if (forward
                 != null)
        {
            LOG.debug("forwarding to module: "
                      + forward.getModule()
                      + " and name "
                      + forward.getName());
        }
        return forward;
    }

    public static ActionForward loginUser(String username, String password, HttpServletRequest request, HttpServletResponse response, String url, String token)
        throws IOException
    {
        UserData
            user =
            UserData.findByUsername(username, password);
        if (user
            == null
            || user.getUsername()
               == null)
        {
            LOG.warn(PortalResources.getProperty("errors.login.username.not.found"));
            return null;
        }
        if (token
            == null
            && !password.equals(user.getPassword()))
        {
            LOG.warn(PortalResources.getProperty("errors.login.invalid.password"));
            return null;
        }
        //Update the user's last login date
        user.setLastLoginDate(new Date());
        user.save(user);
        SessionService.setSessionAttribute(SessionKeys.USER,
                                           new UserValue(user),
                                           request);
        String
            markup =
            PortalUtils.getUserMarkup(request);
        PortalMenu
            portalMenu =
            PortalUtils.getPortalMenu(new UserValue(user),
                                      markup);
        SessionService.setSessionAttribute(SessionKeys.PORTAL_MENU,
                                           portalMenu,
                                           request);
        ActionForward
            forward =
            new ActionForward("/pt",
                              true);
        if (url
            != null
            && !url.isEmpty())
        {
            response.sendRedirect(url);
            return null;
        }
        else
        {
            //Retrieve the first module and forward to its location.
            //If there are no modules, forward to the "no applications" page
            if (!portalMenu.isEmpty()
                && portalMenu.size()
                   <= 2)
            {
                Iterator
                    portalMenuIter =
                    portalMenu.getMenuItems()
                        .iterator();
                PortalMenuItem
                    defaultModule =
                    (PortalMenuItem) portalMenuIter.next();
                forward =
                    new ActionForward(defaultModule.getPath(),
                                      true);
                forward.setModule(PortalUtils.addMarkupPrefix(defaultModule.getModulePath(),
                                                              request));
            }
        }
        return forward;
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }
}
