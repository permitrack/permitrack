package com.sehinc.security.action.user;

import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserListPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            setUserIdInSession(getUserValue(request).getId(), request);
            return mapping.findForward("user.view.page");
        }
        Integer
            intClientId =
            getClientIdFromRequestOrSession(request);
        if (intClientId
            == null
            || intClientId
               == 0)
        {
            LOG.error("Could not find Client ID on request or session");
            setUserIdInSession(getUserValue(request).getId(), request);
            return mapping.findForward("user.view.page");
        }
        setSessionAttribute(SessionKeys.USER_LIST_ACTIVE,
                            UserData.findByClientIdAndStatusCode(intClientId,
                                                                 StatusCodeData.STATUS_CODE_ACTIVE), request);
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
            SecondaryMenu.getInstance(SecondaryMenu.USER_LIST_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.SECURITY_USER_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}