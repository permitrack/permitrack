package com.sehinc.security.action.role;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.user.CapUserRole;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.security.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleEVDeleteAction
    extends RoleBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleEVDeleteAction.class);

    public ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.debug("In roleAction");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("cancel.action"), request);
            return mapping.findForward("role.list.page");
        }
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.getIsClientAdministrator())
        {
            addError(new ActionMessage("delete.role.unauthorized"), request);
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
        Integer
            mintRoleId =
            getRoleIdFromRequestOrSession(request);
        if (mintRoleId
            == null
            || mintRoleId
               == 0)
        {
            LOG.error("Could not find Role ID on request or session");
            addError(new ActionMessage("error.role.not.found.in.session"), request);
            return mapping.findForward("role.list.page");
        }
        CapRole
            role =
            new CapRole();
        if (mintRoleId
            != null)
        {
            role.setId(mintRoleId);
            try
            {
                role.load();
            }
            catch (Exception e)
            {
                LOG.error("Failed to load role ID "
                          + mintRoleId);
                addError(new ActionMessage("error.load.role.failed",
                                           mintRoleId), request);
                return mapping.findForward("role.list.page");
            }
        }
        if (role.getCode()
            .equals(CommonConstants.ROLE_DEFAULT_CODE))
        {
            LOG.error("Cannot delete the DEFAULT role.");
            addError(new ActionMessage("error.delete.default.role",
                                       role.getName()), request);
            return mapping.findForward("role.list.page");
        }
        Long
            roleUserCount =
            CapUserRole.countByRoleId(role.getId());
        LOG.debug("User Role Count = "
                  + roleUserCount);
        if (roleUserCount
            != null
            && roleUserCount.intValue()
               > 0)
        {
            CapRole
                defaultRole =
                CapRole.findByCodeAndModule(CommonConstants.ROLE_DEFAULT_CODE,
                                            role.getModuleId(),
                                            mintClientId);
            if (defaultRole
                == null)
            {
                LOG.error("Failed to delete role ID "
                          + role.getId()
                          + ".  Could not locate DEFAULT role for module ID = "
                          + role.getModuleId());
                addError(new ActionMessage("error.delete.default.role.not.found",
                                           role.getName(),
                                           role.getModuleId()), request);
                return mapping.findForward("role.list.page");
            }
            try
            {
                for (Object o : CapUserRole.findByRoleId(role.getId()))
                {
                    CapUserRole
                        userRole =
                        (CapUserRole) o;
                    userRole.setRoleId(defaultRole.getId());
                    userRole.save();
                }
            }
            catch (Exception e)
            {
                LOG.error("Failed to delete role ID "
                          + role.getId()
                          + ".  Could not update user role with module DEFAULT role ID "
                          + defaultRole.getId());
                addError(new ActionMessage("error.delete.update.default.role.failed",
                                           role.getName(),
                                           defaultRole.getId()), request);
                return mapping.findForward("role.list.page");
            }
        }
        securityManager.deleteAllSecureObjectPermissionsFromRole(role.getId());
        String
            roleName =
            role.getName();
        try
        {
            role.delete();
        }
        catch (Exception e)
        {
            LOG.warn("Failed to delete CapRole ID "
                     + mintRoleId);
        }
        removeSessionAttribute(SessionKeys.ROLE_ID, request);
        setSessionAttribute(SessionKeys.ROLE_LIST_EC_ACTIVE,
                            CapRole.findByModuleCode(mintClientId,
                                                     CommonConstants.EROSION_CONTROL_MODULE), request);
        addMessage(new ActionMessage("delete.role.success",
                                     roleName), request);
        return mapping.findForward("continue");
    }
}