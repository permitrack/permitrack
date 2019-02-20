package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class CapRole
    extends StatusData
{
    private
    Integer
        clientId;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        moduleId;
    private static final
    String
        FIND_BY_CODE =
        "com.sehinc.common.db.security.CapRole.byCode";
    private static final
    String
        FIND_BY_CODE_AND_MODULE =
        "com.sehinc.common.db.security.CapRole.byCodeAndModule";
    private static final
    String
        FIND_BY_MODULE =
        "com.sehinc.common.db.security.CapRole.byModule";
    private static final
    String
        FIND_BY_MODULE_AND_NAME =
        "com.sehinc.common.db.security.CapRole.byModuleAndName";
    private static final
    String
        FIND_BY_CLIENT_OR_SEH =
        "com.sehinc.common.db.security.CapRole.byClientIdAndSEH";
    private static final
    String
        FIND_ACTIVE_BY_CLIENT_ID =
        "com.sehinc.common.db.security.CapRole.byClientIdAndStatus";
    private static final
    String
        FIND_BY_USER_ID =
        "com.sehinc.common.db.security.CapRole.byUserId";
    private static final
    String
        FIND_BY_USER_ID_AND_MODULE =
        "com.sehinc.common.db.security.CapRole.byUserIdAndModule";
    private static final
    String
        FIND_BY_CLIENT_ID =
        "com.sehinc.common.db.security.CapRole.byClientId";
    private static final
    String
        FIND_BY_MODULE_CODE =
        "com.sehinc.common.db.security.CapRole.byModuleCode";

    public CapRole()
    {
    }

    public CapRole(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getModuleId()
    {
        return this.moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public static CapRole findByCode(String code, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "code",
                    code},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        List
            results =
            HibernateUtil.findByNamedQuery(FIND_BY_CODE,
                                           parameters);
        if (results
            != null
            && results.size()
               > 0)
        {
            return (CapRole) results.get(0);
        }
        return null;
    }

    public static CapRole findByCodeAndModule(String code, Integer moduleId, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "code",
                    code},
                {
                    "moduleId",
                    moduleId},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        List
            results =
            HibernateUtil.findByNamedQuery(FIND_BY_CODE_AND_MODULE,
                                           parameters);
        if (results
            != null
            && results.size()
               > 0)
        {
            return (CapRole) results.get(0);
        }
        return null;
    }

    public static List findByModule(Integer moduleId, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
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
        return HibernateUtil.findByNamedQuery(FIND_BY_MODULE,
                                              parameters);
    }

    public static List findByModuleCode(Integer clientId, String moduleCode)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "moduleCode",
                    moduleCode},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_MODULE_CODE,
                                              parameters);
    }

    public static List findByUserId(Integer userId, String statusCode, String moduleCode)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "userId",
                    userId},
                {
                    "moduleCode",
                    moduleCode},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_USER_ID_AND_MODULE,
                                              parameters);
    }

    public static List findByUserId(Integer userId, String statusCode)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "userId",
                    userId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_USER_ID,
                                              parameters);
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }
}