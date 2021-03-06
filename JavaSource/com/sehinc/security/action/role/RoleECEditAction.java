package com.sehinc.security.action.role;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.security.CapRoleSecureObjectPermission;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.security.SecureObjectPermissionData;
import com.sehinc.security.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RoleECEditAction
    extends RoleBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleECEditAction.class);

    public ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
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
            addError(new ActionMessage("update.role.unauthorized"), request);
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
        Integer
            mintRoleId =
            getRoleIdFromRequestOrSession(request);
        if (mintRoleId
            == null
            || mintRoleId.intValue()
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
        RoleECForm
            roleECForm =
            (RoleECForm) form;
        role.setName(roleECForm.getName()
                         .trim());
        role.setDescription(roleECForm.getDescription()
                                .trim());
        role.setCode(roleECForm.getCode()
                         .trim());
        role.setClientId(mintClientId);
        role.setModuleId(new Integer(1));
        role.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
        try
        {
            role.save(getUserValue(request));
        }
        catch (Exception e)
        {
            LOG.error("Failed to save role ID "
                      + roleECForm.getId());
            addError(new ActionMessage("error.update.role.failed",
                                       roleECForm.getName()), request);
            return mapping.findForward("role.list.page");
        }
        securityManager.deleteAllSecureObjectPermissionsFromRole(role.getId());
        String[]
            selectedSOPArray =
            request.getParameterValues("selectedSOP");
        if (selectedSOPArray
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < selectedSOPArray.length; i++)
            {
                Integer
                    sopId =
                    new Integer(Integer.parseInt(selectedSOPArray[i]));
                SecureObjectPermissionData
                    secureObjectPermission =
                    SecureObjectPermissionData.getInstance(sopId);
                CapRoleSecureObjectPermission
                    roleSOP =
                    new CapRoleSecureObjectPermission();
                roleSOP.setRoleId(mintRoleId);
                roleSOP.setPermissionId(secureObjectPermission.getPermissionID());
                roleSOP.setSecureObjectId(secureObjectPermission.getSecureObjectID());
                try
                {
                    roleSOP.save();
                }
                catch (Exception e)
                {
                    LOG.error("Failed to save secure object permission ID "
                              + secureObjectPermission.getSecureObjectID());
                    addError(new ActionMessage("error.save.secure.object.permission.failed",
                                               secureObjectPermission.getSecureObjectID()), request);
                    return mapping.findForward("role.list.page");
                }
            }
        }
        setRoleIdInSession(role.getId(), request);
        setSessionAttribute(SessionKeys.ROLE_LIST_EC_ACTIVE,
                            CapRole.findByModuleCode(mintClientId,
                                                     CommonConstants.EROSION_CONTROL_MODULE), request);
        addMessage(new ActionMessage("update.role.success",
                                     role.getName()), request);
        return mapping.findForward("continue");
    }
}