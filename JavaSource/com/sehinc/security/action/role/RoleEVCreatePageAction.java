package com.sehinc.security.action.role;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.security.CapPermission;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.security.action.base.SessionKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import com.sehinc.security.server.role.RoleService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoleEVCreatePageAction
    extends RoleBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleEVCreatePageAction.class);

    public ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In roleAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            addError(new ActionMessage("create.role.unauthorized"), request);
            return mapping.findForward("role.list.page");
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
            return mapping.findForward("role.list.page");
        }
        RoleEVForm
            roleEVForm =
            (RoleEVForm) form;
        CapModule
            evModule =
            CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
        roleEVForm.setClientId(mintClientId);
        roleEVForm.setModuleId(evModule.getId());
        setSessionAttribute(SessionKeys.ROLE_NAME_LIST,
                            CapRole.findByModule(evModule.getId(),
                                                 mintClientId), request);
        ArrayList
            all_rows =
            RoleService.getOrderedPermissionData(evModule.getId());
        setSessionAttribute(SessionKeys.EV_SECURE_OBJECT_PERMISSION_LIST,
                            all_rows, request);
        List
            capPermissionList =
            CapPermission.findCRUD();
        Iterator
            cpi =
            capPermissionList.iterator();
        ArrayList
            headers =
            new ArrayList();
        while (cpi.hasNext())
        {
            CapPermission
                permission =
                (CapPermission) cpi.next();
            headers.add(permission.getName());
        }
        setSessionAttribute(SessionKeys.EV_SECURE_OBJECT_HEADERS_LIST,
                            headers, request);
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
        sec.setCurrentItem(SecondaryMenu.X_ROLE_EV_CREATE_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}