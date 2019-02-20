package com.sehinc.security.action.user;

import com.sehinc.common.service.user.UserService;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserAddPageAction
    extends UserListPageAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserListPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            setUserIdInSession(getUserValue(request).getId(), request);
            return mapping.findForward("user.view.page");
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
        UserForm
            objNewUserForm =
            (UserForm) form;
        setSessionAttribute(SessionKeys.USER_LIST_ACTIVE,
                            UserService.findByWildcard(mintClientId,
                                                       StatusCodeData.STATUS_CODE_ACTIVE,
                                                       objNewUserForm.getFirstName()), request);
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
        sec.setCurrentItem(SecondaryMenu.X_USER_ADD_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}
