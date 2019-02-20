package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.common.service.user.UserService;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
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
import java.util.Iterator;
import java.util.List;

public class UserCreatePageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserCreatePageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strUsers =
            "";
        UserForm
            objNewUserForm =
            (UserForm) form;
        LOG.info("In userAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            addError(new ActionMessage("create.user.unauthorized"), request);
            return mapping.findForward("user.list.page");
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
        setSessionAttribute(SessionKeys.STATE_LIST,
                            HibernateUtil.findAll(CapState.class)
                        , request);
        boolean
            excludeSystemAdmin =
            true;
        if (securityManager.getIsSystemAdministrator()
            && CommonConstants.SEH_CLIENT_ID
            .equals(mintClientId))
        {
            excludeSystemAdmin =
                false;
        }
        setSessionAttribute(SessionKeys.USER_GROUP_LIST,
                            GroupData.getListBySecurityLevelId(GroupService.getGroupSecurityLevelId(getUserValue(request).getGroupId()),
                                                               excludeSystemAdmin), request);
        List
            lstUsers =
            UserService.getUserValueListByStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        Iterator
            itUsers =
            lstUsers.iterator();
        while (itUsers.hasNext())
        {
            UserValue
                userValue =
                (UserValue) itUsers.next();
            strUsers =
                strUsers
                + userValue.getUsername()
                + ",";
        }
        setSessionAttribute(SessionKeys.USER_USER_LIST,
                            strUsers, request);
        AddressData
            clientAddress =
            ClientService.getClientAddress(mintClientId);
        objNewUserForm.setState(clientAddress.getState());
        ClientModule
            clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.EROSION_CONTROL_MODULE);
        if (clientModule
            != null)
        {
            LOG.debug("userAction(): ec module found for EC module for client "
                      + mintClientId);
            setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                true, request);
            setSessionAttribute(SessionKeys.USER_ROLE_LIST_EC,
                                CapRole.findByModule(clientModule.getModuleId(),
                                                     mintClientId), request);
            objNewUserForm.setAccessEC(true);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                false, request);
            removeSessionAttribute(SessionKeys.USER_ROLE_LIST_EC, request);
        }
        clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.STORM_WATER_MODULE);
        if (clientModule
            != null)
        {
            LOG.debug("userAction(): sw module found for SW module for client "
                      + mintClientId);
            setSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION,
                                true, request);
            objNewUserForm.setAccessSW(true);
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
            LOG.debug("userAction(): dv module found for DV module for client "
                      + mintClientId);
            setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                true, request);
            setSessionAttribute(SessionKeys.USER_ROLE_LIST_DV,
                                CapRole.findByModule(clientModule.getModuleId(),
                                                     mintClientId), request);
            objNewUserForm.setAccessDV(true);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                false, request);
            removeSessionAttribute(SessionKeys.USER_ROLE_LIST_DV, request);
        }
        clientModule =
            ClientModule.findClientModule(mintClientId,
                                          CommonConstants.ENVIRONMENT_MODULE);
        if (clientModule
            != null)
        {
            LOG.debug("userAction(): ev module found for EV module for client "
                      + mintClientId);
            setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                true, request);
            setSessionAttribute(SessionKeys.USER_ROLE_LIST_EV,
                                CapRole.findByModule(clientModule.getModuleId(),
                                                     mintClientId), request);
            objNewUserForm.setAccessDV(true);
        }
        else
        {
            setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                false, request);
            removeSessionAttribute(SessionKeys.USER_ROLE_LIST_EV, request);
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
            SecondaryMenu.getInstance(SecondaryMenu.USER_LIST_MENU_NAME);
        sec.setCurrentItem(SecondaryMenu.X_USER_CREATE_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}