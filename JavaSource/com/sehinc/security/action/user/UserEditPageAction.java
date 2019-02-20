package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientModule;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.CapState;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.group.GroupService;
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
import java.util.List;

public class UserEditPageAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserEditPageAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In userEditPageAction");
        try
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
                return mapping.findForward("user.view.page");
            }
            UserData
                editUser =
                UserService.getUser(mintUserId);
            if (editUser
                == null)
            {
                LOG.error("Could not load user ID "
                          + mintUserId.toString());
                addError(new ActionMessage("error.load.user.failed",
                                           mintUserId.toString()), request);
                return mapping.findForward("user.view.page");
            }
            Integer
                clientId =
                getClientIdFromRequestOrSession(request);
            if (clientId
                == null
                || clientId
                   == 0)
            {
                LOG.error("Could not find Client ID on request or session");
                return mapping.findForward("user.view.page");
            }
            if (!securityManager.getIsClientAdministrator()
                && !editUser.getId()
                .equals(getUserValue(request).getId()))
            {
                LOG.error("User ID "
                          + getUserValue(request).getId()
                          + " is not authorized to edit User ID "
                          + editUser.getId());
                addError(new ActionMessage("update.user.unauthorized",
                                           editUser.getUsername()), request);
                return mapping.findForward("user.view.page");
            }
            getUserForm(userForm,
                        editUser.getId(),
                        clientId, request);
            userForm.setClientId(clientId);
            if (securityManager.getIsClientAdministrator())
            {
                setSessionAttribute(SessionKeys.ADMIN_LOGGED_IN,
                                    true, request);
            }
            else
            {
                setSessionAttribute(SessionKeys.ADMIN_LOGGED_IN,
                                    false, request);
            }
            //The logged in user can only edit another user of the same security
            //level or lower
            if (userForm.getGroupId()
                < securityManager.getSecurityLevelId())
            {
                LOG.error("User ID "
                          + getUserValue(request).getId()
                          + " is not authorized to edit User ID "
                          + editUser.getId());
                addError(new ActionMessage("update.user.unauthorized",
                                           editUser.getUsername()), request);
                return mapping.findForward("user.view.page");
            }
            setSessionAttribute(SessionKeys.USER_CREATE_REDIRECT,
                                "false", request);
            setUserIdInSession(editUser.getId(), request);
            setSessionAttribute(SessionKeys.STATE_LIST,
                                HibernateUtil.findAll(CapState.class), request);
            boolean
                excludeSystemAdmin =
                true;
            if (securityManager.getIsSystemAdministrator()
                && CommonConstants.SEH_CLIENT_ID
                .equals(clientId))
            {
                excludeSystemAdmin =
                    false;
            }
            Integer
                groupSecurityLevel =
                GroupService.getGroupSecurityLevelId(getUserValue(request).getGroupId());
            List
                groupList =
                GroupData.getListBySecurityLevelId(groupSecurityLevel,
                                                   excludeSystemAdmin);
            setSessionAttribute(SessionKeys.USER_GROUP_LIST,
                                groupList, request);
            ClientModule
                clientModule =
                ClientModule.findClientModule(clientId,
                                              CommonConstants.EROSION_CONTROL_MODULE);
            if (clientModule
                != null)
            {
                LOG.debug("userAction: Displaying ESC user access options.");
                setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                    true, request);
                setSessionAttribute(SessionKeys.USER_ROLE_LIST_EC,
                                    CapRole.findByModule(clientModule.getModuleId(),
                                                         clientId), request);
                CapUserModule
                    userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          clientModule.getModuleId(),
                                                          clientId);
                if (userModule
                    != null)
                {
                    userForm.setAccessEC(true);
                }
            }
            else
            {
                LOG.debug("userAction: NOT displaying ESC user access options.");
                setSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION,
                                    false, request);
                removeSessionAttribute(SessionKeys.USER_ROLE_LIST_EC, request);
            }
            clientModule =
                ClientModule.findClientModule(clientId,
                                              CommonConstants.DATA_VIEW_MODULE);
            if (clientModule
                != null)
            {
                LOG.debug("userAction: Displaying DVO user access options.");
                setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                    true, request);
                setSessionAttribute(SessionKeys.USER_ROLE_LIST_DV,
                                    CapRole.findByModule(clientModule.getModuleId(),
                                                         clientId), request);
                CapUserModule
                    userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          clientModule.getModuleId(),
                                                          clientId);
                if (userModule
                    != null)
                {
                    userForm.setAccessDV(true);
                }
            }
            else
            {
                LOG.debug("userAction: NOT displaying DVO user access options.");
                setSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION,
                                    false, request);
                removeSessionAttribute(SessionKeys.USER_ROLE_LIST_DV, request);
            }
            clientModule =
                ClientModule.findClientModule(clientId,
                                              CommonConstants.STORM_WATER_MODULE);
            if (clientModule
                != null)
            {
                LOG.debug("userAction: Displaying MS4 user access options.");
                setSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION,
                                    true, request);
                setSessionAttribute(SessionKeys.USER_ROLE_LIST_SW,
                                    CapRole.findByModule(clientModule.getModuleId(),
                                                         clientId), request);
                CapUserModule
                    userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          clientModule.getModuleId(),
                                                          clientId);
                if (userModule
                    != null)
                {
                    userForm.setAccessSW(true);
                }
            }
            else
            {
                LOG.debug("userAction: NOT displaying MS4 user access options.");
                setSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION,
                                    false, request);
                removeSessionAttribute(SessionKeys.USER_ROLE_LIST_SW, request);
            }
            clientModule =
                ClientModule.findClientModule(clientId,
                                              CommonConstants.ENVIRONMENT_MODULE);
            if (clientModule
                != null)
            {
                LOG.debug("userAction: Displaying ENV user access options.");
                setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                    true, request);
                setSessionAttribute(SessionKeys.USER_ROLE_LIST_EV,
                                    CapRole.findByModule(clientModule.getModuleId(),
                                                         clientId), request);
                CapUserModule
                    userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          clientModule.getModuleId(),
                                                          clientId);
                if (userModule
                    != null)
                {
                    userForm.setAccessEV(true);
                }
            }
            else
            {
                LOG.debug("userAction: NOT displaying ENV user access options.");
                setSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION,
                                    false, request);
                removeSessionAttribute(SessionKeys.USER_ROLE_LIST_EV, request);
            }
            return mapping.findForward("continue");
        }
        catch (Exception e)
        {
            String
                msg =
                "userAction() method failed. Exception: "
                + e.getLocalizedMessage();
            LOG.error(msg);
            addError(new ActionMessage("error.user.edit.action",
                                       msg), request);
            throw new Exception(msg);
        }
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
        sec.setCurrentItem(SecondaryMenu.X_USER_EDIT_MENU_ITEM_NAME);
        setSecondaryMenu(sec, request);
    }
}