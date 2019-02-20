package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class CapUserRole
    extends HibernateData
{
    private
    Integer
        userId;
    private
    Integer
        roleId;
    private static final
    String
        FIND_BY_MODULE_AND_CLIENT =
        "com.sehinc.common.db.user.CapUserRole.findByModuleAndClient";
    private static final
    String
        FIND_BY_MODULE_AND_USER =
        "com.sehinc.common.db.user.CapUserRole.findByModuleAndUser";
    private static final
    String
        COUNT_BY_ROLE_ID =
        "com.sehinc.common.db.user.CapUserRole.countByRoleId";
    private static final
    String
        FIND_BY_ROLE_ID =
        "com.sehinc.common.db.user.CapUserRole.findByRoleId";
    private static final
    String
        FIND_BY_USER_ID =
        "com.sehinc.common.db.user.CapUserRole.findByUserId";
    private static final
    String
        FIND_BY_USER_ID_AND_ROLE_ID =
        "com.sehinc.common.db.user.CapUserRole.findByUserIdAndRoleId";

    public CapUserRole()
    {
    }

    public CapUserRole(Integer id)
    {
        setId(id);
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId =
            userId;
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

    public static Long countByRoleId(Integer roleId)
    {
        Object[][]
            parameters =
            {
                {
                    "roleId",
                    roleId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return (Long) HibernateUtil.findUniqueByNamedQuery(COUNT_BY_ROLE_ID,
                                                           parameters);
    }

    public static List findByModuleAndClient(Integer moduleId, Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "moduleId",
                    moduleId},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_MODULE_AND_CLIENT,
                                              parameters);
    }

    public static List findByModuleAndUser(Integer moduleId, Integer userId)
    {
        Object[][]
            parameters =
            {
                {
                    "moduleId",
                    moduleId},
                {
                    "userId",
                    userId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_MODULE_AND_USER,
                                              parameters);
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

    public static List findByUserId(Integer userId)
    {
        Object[][]
            parameters =
            {
                {
                    "userId",
                    userId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_USER_ID,
                                              parameters);
    }

    public static CapUserRole findByUserIdAndRoleId(Integer userId, Integer roleId)
    {
        Object[][]
            parameters =
            {
                {
                    "userId",
                    userId},
                {
                    "roleId",
                    roleId}};
        return (CapUserRole) HibernateUtil.findUniqueByNamedQuery(FIND_BY_USER_ID_AND_ROLE_ID,
                                                                  parameters);
    }
}