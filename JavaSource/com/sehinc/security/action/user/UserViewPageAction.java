package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientModule;
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

public class UserViewPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserViewPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        UserForm
            userForm =
            (UserForm) form;
        SecurityManager
            securityManager =
            getSecurityManager(request);
        Integer
            mintUserId =
            getUserId(request);
        if (mintUserId
            == null
            || mintUserId
               == 0)
        {
            LOG.error("User not found on request or session");
            addError(new ActionMessage("error.user.not.found.in.session"), request);
            return getErrorForward();
        }
        UserData
            viewUser =
            UserService.getUser(mintUserId);
        if (viewUser
            == null)
        {
            LOG.error("Could not load user ID "
                      + mintUserId.toString());
            addError(new ActionMessage("error.load.user.failed",
                                       mintUserId.toString()), request);
            return getErrorForward();
        }
        Integer
            mintClientId =
            getClientIdFromRequestOrSession(request);
        if (!securityManager.getIsClientAdministrator()
            && !viewUser.getId()
            .equals(getUserValue(request).getId()))
        {
            LOG.warn("User "
                     + getUserValue(request).getUsername()
                     + " is not authorized to view user "
                     + viewUser.getUsername());
            addError(new ActionMessage("view.user.unauthorized",
                                       viewUser.getUsername()), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "Page denied", request);
            return mapping.findForward("page.permission.denied");
        }
        ClientModule
            clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.EROSION_CONTROL_MODULE);
        if (clientModule
            != null)
        {
            setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                true, request);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                false, request);
        }
        clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.STORM_WATER_MODULE);
        if (clientModule
            != null)
        {
            setSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION,
                                true, request);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION,
                                false, request);
        }
        clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.DATA_VIEW_MODULE);
        if (clientModule
            != null)
        {
            setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                true, request);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                false, request);
        }
        clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.ENVIRONMENT_MODULE);
        if (clientModule
            != null)
        {
            setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                true, request);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                false, request);
        }
        setUserIdInSession(viewUser.getId(), request);
        getUserForm(userForm,
                    viewUser.getId(),
                    mintClientId, request);
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
        sec.setCurrentItem(SecondaryMenu.X_USER_VIEW_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}