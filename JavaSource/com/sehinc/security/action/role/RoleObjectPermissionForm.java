package com.sehinc.security.action.role;

import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

public class RoleObjectPermissionForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        roleId;
    private
    Integer
        secureObjectId;
    private
    Integer
        permissionId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId =
            roleId;
    }

    public Integer getSecureObjectId()
    {
        return secureObjectId;
    }

    public void setSecureObjectId(Integer secureObjectId)
    {
        this.secureObjectId =
            secureObjectId;
    }

    public Integer getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId)
    {
        this.permissionId =
            permissionId;
    }

    public void reset()
    {
        this.id =
            null;
        this.roleId =
            null;
        this.secureObjectId =
            null;
        this.permissionId =
            null;
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
