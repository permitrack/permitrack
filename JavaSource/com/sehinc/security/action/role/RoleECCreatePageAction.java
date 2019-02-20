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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoleECCreatePageAction
    extends RoleBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleECCreatePageAction.class);

    public ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
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
            || mintClientId.intValue()
               == 0)
        {
            LOG.error("Could not find Client ID on request or session");
            return mapping.findForward("role.list.page");
        }
        RoleECForm
            roleECForm =
            (RoleECForm) form;
        CapModule
            ecModule =
            CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
        roleECForm.setClientId(mintClientId);
        roleECForm.setModuleId(ecModule.getId());
        setSessionAttribute(SessionKeys.ROLE_NAME_LIST,
                            CapRole.findByModule(ecModule.getId(),
                                                 mintClientId), request);
        ArrayList
            all_rows =
            RoleService.getOrderedPermissionData(ecModule.getId());
        setSessionAttribute(SessionKeys.EC_SECURE_OBJECT_PERMISSION_LIST,
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
        setSessionAttribute(SessionKeys.EC_SECURE_OBJECT_HEADERS_LIST,
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
        sec.setCurrentItem(SecondaryMenu.X_ROLE_EC_CREATE_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}