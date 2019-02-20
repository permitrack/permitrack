package com.sehinc.erosioncontrol.server.security;

import com.sehinc.common.db.security.CapRole;
import com.sehinc.common.db.security.CapRoleSecureObjectPermission;
import com.sehinc.common.db.user.CapUserRole;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class RoleService
{
    private static
    Logger
        LOG =
        Logger.getLogger(RoleService.class);

    public RoleService()
    {
    }

    public static Integer copyRole(Integer roleId, Integer toClientId, UserValue userValue)
        throws Exception
    {
        CapRole
            newCapRole =
            new CapRole();
        if (roleId
            != null
            && toClientId
               != null
            && userValue
               != null)
        {
            CapRole
                capRole =
                new CapRole(roleId);
            try
            {
                capRole.load();
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                throw new Exception("Unable to load role with role ID = "
                                    + roleId);
            }
            try
            {
                newCapRole.setClientId(toClientId);
                newCapRole.setCode(capRole.getCode());
                newCapRole.setDescription(capRole.getDescription());
                newCapRole.setName(capRole.getName());
                newCapRole.setModuleId(capRole.getModuleId());
                newCapRole.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                newCapRole.insert(userValue);
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                throw new Exception("Unable to create new role from role ID = "
                                    + roleId);
            }
            List
                lstSOP;
            try
            {
                lstSOP =
                    CapRoleSecureObjectPermission.findByRoleId(capRole.getId());
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                throw new Exception("Error getting all of the permsissions for role ID = "
                                    + roleId);
            }
            if (lstSOP
                == null)
            {
                LOG.warn("Role ID = "
                         + roleId
                         + " has no permissions.");
            }
            Iterator
                iterSOP =
                lstSOP.iterator();
            while (iterSOP.hasNext())
            {
                CapRoleSecureObjectPermission
                    capPermission =
                    (CapRoleSecureObjectPermission) iterSOP.next();
                CapRoleSecureObjectPermission
                    newCapPermission =
                    new CapRoleSecureObjectPermission();
                newCapPermission.setRoleId(newCapRole.getId());
                newCapPermission.setPermissionId(capPermission.getPermissionId());
                newCapPermission.setSecureObjectId(capPermission.getSecureObjectId());
                newCapPermission.insert();
            }
        }
        else
        {
            LOG.error("Null input value copyRole(roleId = "
                      + roleId
                      + ", toClientId = "
                      + toClientId
                      + ", userValue = "
                      + userValue);
            throw new Exception("Null input value passed to copyRole()");
        }
        return newCapRole.getId();
    }

    public static void deleteUserRoleByModule(Integer userId, Integer moduleId)
        throws Exception
    {
        List
            userRoleList =
            CapUserRole.findByModuleAndUser(moduleId,
                                            userId);
        if (userRoleList
            != null
            && userRoleList.size()
               > 0)
        {
            Iterator
                userRoleIter =
                userRoleList.iterator();
            while (userRoleIter.hasNext())
            {
                CapUserRole
                    userRole =
                    (CapUserRole) userRoleIter.next();
                userRole.delete();
            }
        }
    }
}
