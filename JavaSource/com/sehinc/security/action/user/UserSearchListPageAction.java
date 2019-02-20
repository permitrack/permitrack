package com.sehinc.security.action.user;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.user.UserService;
import com.sehinc.erosioncontrol.db.user.EcUserSearch;
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
import java.util.List;

public class UserSearchListPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserSearchListPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In UserSearchListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
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
        if (!securityManager.getIsClientAdministrator()
            && !editUserId.equals(getUserValue(request).getId()))
        {
            LOG.error("User ID "
                      + getUserValue(request).getId()
                      + " is not authorized to edit the Saved Search Filters of User ID "
                      + editUserId);
            addError(new ActionMessage("edit.user.unauthorized",
                                       editUser.getUsername()), request);
            return mapping.findForward("user.view.page");
        }
        setUserIdInSession(editUserId, request);
        List
            userSearchList =
            EcUserSearch.findByUserId(editUserId);
        LOG.debug("userSearchList.size()="
                  + userSearchList.size());
        setSessionAttribute(SessionKeys.USER_SEARCH_LIST,
                            userSearchList, request);
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
        sec.setCurrentItem(SecondaryMenu.X_USER_SEARCHES_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}