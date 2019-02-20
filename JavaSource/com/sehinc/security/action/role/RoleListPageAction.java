package com.sehinc.security.action.role;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleListPageAction
    extends RoleBaseAction
{
    public ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        setSessionAttribute(SessionKeys.SECURITY_GROUP_ID,
                            securityManager.getGroupId(), request);
        Integer
            mintClientId =
            getClientIdFromRequestOrSession(request);
        if (mintClientId
            == null
            || mintClientId
               == 0)
        {
            setSessionAttribute(SessionKeys.ROLE_LIST_EC_ACTIVE,
                                CapRole.findByUserId(securityManager.getUserID(),
                                                     StatusCodeData.STATUS_CODE_ACTIVE,
                                                     CommonConstants.EROSION_CONTROL_MODULE), request);
            setSessionAttribute(SessionKeys.ROLE_LIST_EV_ACTIVE,
                                CapRole.findByUserId(securityManager.getUserID(),
                                                     StatusCodeData.STATUS_CODE_ACTIVE,
                                                     CommonConstants.ENVIRONMENT_MODULE), request);
        }
        else
        {
            setSessionAttribute(SessionKeys.ROLE_LIST_EC_ACTIVE,
                                CapRole.findByModuleCode(mintClientId,
                                                         CommonConstants.EROSION_CONTROL_MODULE), request);
            setSessionAttribute(SessionKeys.ROLE_LIST_EV_ACTIVE,
                                CapRole.findByModuleCode(mintClientId,
                                                         CommonConstants.ENVIRONMENT_MODULE), request);
        }
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
            SecondaryMenu.getInstance(SecondaryMenu.ROLE_LIST_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_ROLE_LIST_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}