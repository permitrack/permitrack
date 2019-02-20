package com.sehinc.security.action.user;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.group.GroupData;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.CapUserModule;
import com.sehinc.common.db.user.CapUserRole;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.service.group.GroupService;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

public class UserEditAction
    extends UserBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(UserEditAction.class);

    public ActionForward userAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In userAction");
        UserForm
            userForm =
            (UserForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("cancel.action"), request);
            return mapping.findForward("user.list.page");
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
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
        if (!securityManager.getIsClientAdministrator()
            && (!getUserValue(request).getId()
            .equals(mintUserId)))
        {
            addError(new ActionMessage("update.user.unauthorized"), request);
            return mapping.findForward("user.view.page");
        }
        userForm.setClientId(mintClientId);
        UserData
            editUser =
            new UserData(mintUserId);
        if (!editUser.load())
        {
            LOG.error("Failed to load user ID "
                      + mintUserId);
            addError(new ActionMessage("error.load.user.failed",
                                       mintUserId), request);
            return mapping.findForward("user.view.page");
        }
        AddressData
            editAddress =
            new AddressData(editUser.getAddressId());
        if (!editAddress.load())
        {
            LOG.error("Failed to load address ID "
                      + editUser.getAddressId());
            addError(new ActionMessage("error.load.address.failed",
                                       editUser.getAddressId()), request);
        }
        CapContact
            editContact =
            new CapContact(editUser.getContactId());
        if (!editContact.load())
        {
            LOG.error("Failed to load contact ID "
                      + editUser.getContactId());
            editContact =
                new CapContact();
        }
        editContact.setAddressData(editAddress);
        editContact.setEmail(userForm.getEmailAddress());
        editContact.setFirstName(userForm.getFirstName());
        editContact.setLastName(userForm.getLastName());
        editContact.setMi((userForm.getMiddleName()
                           != null
                           && userForm.getMiddleName()
                                  .length()
                              > 0)
                              ? userForm.getMiddleName()
            .charAt(0)
                              : null);
        editContact.setTitle(userForm.getTitle());
        editContact.setAddress(editAddress.getLine1());
        editContact.setAddress2(editAddress.getLine2());
        editContact.setCity(editAddress.getCity());
        editContact.setStateCode(editAddress.getState());
        editContact.setZip(editAddress.getPostalCode());
        editContact.setPrimaryPhone(userForm.getPrimaryPhone());
        editContact.setSecondaryPhone(userForm.getSecondaryPhone());
        editContact.setFaxPhone(userForm.getFaxPhone());
        editContact.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        editContact.save(getUserValue(request));
        editUser.setFirstName(userForm.getFirstName());
        editUser.setLastName(userForm.getLastName());
        editUser.setTitle(userForm.getTitle());
        editUser.setAddressId(editAddress.getId());
        editUser.setContactId(editContact.getId());
        Integer
            groupId =
            userForm.getGroupId();
        if (groupId
            == null
            || groupId
               == 0)
        {
            GroupData
                group =
                GroupData.findUser();
            groupId =
                group.getId();
        }
        editUser.setGroupId(groupId);
        editUser.setPrimaryPhone(userForm.getPrimaryPhone());
        editUser.setSecondaryPhone(userForm.getSecondaryPhone());
        editUser.setFaxPhone(userForm.getFaxPhone());
        editUser.setEmailAddress(userForm.getEmailAddress());
        editUser.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        editUser.save(getUserValue(request));
        if (securityManager.getIsClientAdministrator())
        {
            CapModule
                module;
            CapUserModule
                userModule;
            Boolean
                ec =
                (Boolean) getSessionAttribute(SessionKeys.SHOW_EC_ACCESS_OPTION, request);
            if (ec)
            {
                module =
                    CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
                userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          module.getId(),
                                                          mintClientId);
                if (userModule
                    != null)
                {
                    LOG.debug("userAction: Removing ESC access for userId: "
                              + editUser.getId());
                    userModule.delete();
                }
                if (userForm.getAccessEC())
                {
                    LOG.debug("userAction: Inserting ESC access for userId: "
                              + editUser.getId());
                    userModule =
                        new CapUserModule();
                    userModule.setIsActive(true);
                    userModule.setModuleId(module.getId());
                    userModule.setUserId(editUser.getId());
                    userModule.setClientId(mintClientId);
                    userModule.insert();
                }
            }
            Boolean
                sw =
                (Boolean) getSessionAttribute(SessionKeys.SHOW_SW_ACCESS_OPTION, request);
            if (sw)
            {
                module =
                    CapModule.findByCode(CommonConstants.STORM_WATER_MODULE);
                userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          module.getId(),
                                                          mintClientId);
                if (userModule
                    != null)
                {
                    LOG.debug("userAction: Removing MS4 access for userId: "
                              + editUser.getId());
                    userModule.delete();
                }
                if (userForm.getAccessSW())
                {
                    LOG.debug("userAction: Inserting MS4 access for userId: "
                              + editUser.getId());
                    userModule =
                        new CapUserModule();
                    userModule.setIsActive(true);
                    userModule.setModuleId(module.getId());
                    userModule.setUserId(editUser.getId());
                    userModule.setClientId(mintClientId);
                    userModule.insert();
                }
            }
            Boolean
                dv =
                (Boolean) getSessionAttribute(SessionKeys.SHOW_DV_ACCESS_OPTION, request);
            if (dv)
            {
                module =
                    CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE);
                userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          module.getId(),
                                                          mintClientId);
                if (userModule
                    != null)
                {
                    LOG.debug("userAction: Removing DVO access for userId: "
                              + editUser.getId());
                    userModule.delete();
                }
                if (userForm.getAccessDV())
                {
                    LOG.debug("userAction: Inserting DVO access for userId: "
                              + editUser.getId());
                    userModule =
                        new CapUserModule();
                    userModule.setIsActive(true);
                    userModule.setModuleId(module.getId());
                    userModule.setUserId(editUser.getId());
                    userModule.setClientId(mintClientId);
                    userModule.insert();
                }
            }
            Boolean
                ev =
                (Boolean) getSessionAttribute(SessionKeys.SHOW_EV_ACCESS_OPTION, request);
            if (ev)
            {
                module =
                    CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
                userModule =
                    CapUserModule.findByUserIdAndModuleId(editUser.getId(),
                                                          module.getId(),
                                                          mintClientId);
                if (userModule
                    != null)
                {
                    LOG.debug("userAction: Removing ENV access for userId: "
                              + editUser.getId());
                    userModule.delete();
                }
                if (userForm.getAccessEV())
                {
                    LOG.debug("userAction: Inserting ENV access for userId: "
                              + editUser.getId());
                    userModule =
                        new CapUserModule();
                    userModule.setIsActive(true);
                    userModule.setModuleId(module.getId());
                    userModule.setUserId(editUser.getId());
                    userModule.setClientId(mintClientId);
                    userModule.insert();
                }
            }
/*
            PortalMenu
                portalMenu =
                (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU,
                                                 request);
            String
                currentItem =
                portalMenu.getCurrentItem()
                    .getName();
            request.getSession()
                .setAttribute(SessionKeys.PORTAL_MENU,
                              null);
            request.getSession()
                .setAttribute(SessionKeys.CURRENT_MENU_ITEM,
                              currentItem);
*/
/*
            request.getSession()
                .setAttribute(SessionKeys.PORTAL_MENU,
                              null);
*/
            // If the user is a CLIENT_ADMINISTRATOR or SYSTEM_ADMINISTRATOR
            // then they do no need any roles.
            // If the new user is of type USER, then they need a role for ESC,
            // if they have access to the ESC module
            // ROLES ARE CURRENTLY ONLY SUPPORTED IN ESC AND ENV, AND ONLY FOR USERS
            // OF TYPE 'USER'!!
            int
                securityLevel =
                GroupService.getGroupSecurityLevel(editUser.getGroupId());
            if (securityLevel
                == SecurityManager.USER_SECURITY_LEVEL
                && userForm.getAccessEC())
            {
                CapModule
                    escModule =
                    CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE);
                Iterator
                    userRoleIter =
                    CapUserRole.findByModuleAndUser(escModule.getId(),
                                                    editUser.getId())
                        .iterator();
                boolean
                    found =
                    false;
                while (userRoleIter.hasNext())
                {
                    CapUserRole
                        userRole =
                        (CapUserRole) userRoleIter.next();
                    if (userRole.getRoleId()
                        .equals(userForm.getRoleIdEC()))
                    {
                        found =
                            true;
                    }
                    else
                    {
                        userRole.delete();
                    }
                }
                if (!found)
                {
                    CapRole
                        ecRole =
                        new CapRole(userForm.getRoleIdEC());
                    if (ecRole.load())
                    {
                        CapUserRole
                            ecUserRole =
                            new CapUserRole();
                        ecUserRole.setRoleId(ecRole.getId());
                        ecUserRole.setUserId(editUser.getId());
                        ecUserRole.save();
                    }
                    else
                    {
                        LOG.error("Failed to load role ID "
                                  + userForm.getRoleIdEC());
                        addError(new ActionMessage("error.assign.role.failed",
                                                   userForm.getRoleNameEC()), request);
                    }
                }
            }
            // If the new user is of type USER, then they need a role for ENV,
            // if they have access to the ENV module
            if (securityLevel
                == SecurityManager.USER_SECURITY_LEVEL
                && userForm.getAccessEV())
            {
                CapModule
                    envModule =
                    CapModule.findByCode(CommonConstants.ENVIRONMENT_MODULE);
                Iterator
                    userRoleIter =
                    CapUserRole.findByModuleAndUser(envModule.getId(),
                                                    editUser.getId())
                        .iterator();
                boolean
                    found =
                    false;
                while (userRoleIter.hasNext())
                {
                    CapUserRole
                        userRole =
                        (CapUserRole) userRoleIter.next();
                    if (userRole.getRoleId()
                        .equals(userForm.getRoleIdEV()))
                    {
                        found =
                            true;
                    }
                    else
                    {
                        userRole.delete();
                    }
                }
                if (!found)
                {
                    CapRole
                        evRole =
                        new CapRole(userForm.getRoleIdEV());
                    if (evRole.load())
                    {
                        CapUserRole
                            evUserRole =
                            new CapUserRole();
                        evUserRole.setRoleId(evRole.getId());
                        evUserRole.setUserId(editUser.getId());
                        evUserRole.save();
                    }
                    else
                    {
                        LOG.error("Failed to load role ID "
                                  + userForm.getRoleIdEV());
                        addError(new ActionMessage("error.assign.role.failed",
                                                   userForm.getRoleNameEV()), request);
                    }
                }
            }
        }
        addMessage(new ActionMessage("edit.user.success",
                                     editUser.getUsername()), request);
        return mapping.findForward("continue");
    }
}