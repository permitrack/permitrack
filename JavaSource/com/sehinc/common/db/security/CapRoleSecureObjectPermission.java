package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapRoleSecureObjectPermission
    extends HibernateData
{
    private
    Integer
        roleId;
    private
    Integer
        secureObjectId;
    private
    Integer
        permissionId;
    private static final
    String
        FIND_BY_ROLE_ID =
        "com.sehinc.common.db.security.CapRoleSecureObjectPermission.byRoleId";

    public CapRoleSecureObjectPermission()
    {
    }

    public CapRoleSecureObjectPermission(Integer id)
    {
        setId(id);
    }

    public Integer getRoleId()
    {
        return this.roleId;
    }

    public void setRoleId(Integer roleId)
    {
        this.roleId =
            roleId;
    }

    public Integer getSecureObjectId()
    {
        return this.secureObjectId;
    }

    public void setSecureObjectId(Integer secureObjectId)
    {
        this.secureObjectId =
            secureObjectId;
    }

    public Integer getPermissionId()
    {
        return this.permissionId;
    }

    public void setPermissionId(Integer permissionId)
    {
        this.permissionId =
            permissionId;
    }

    public static List findByRoleId(Integer roleId)
    {
        Object[][]
            parameters =
            {
                {
                    "roleId",
                    roleId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ROLE_ID,
                                              parameters);
    }

    public static List findByUserRoleList(String userRoleList)
    {
        Object[]
            parameters =
            {
                "userRoleIdList",
                userRoleList};
        String
            queryString =
            "select data from CapRoleSecureObjectPermission as data where data.roleId in (?)";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}