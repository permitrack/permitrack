package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapUserModule
    extends HibernateData
{
    private
    Integer
        userId;
    private
    Integer
        moduleId;
    private
    Boolean
        isActive;
    private static final
    String
        FIND_BY_USER_AND_MODULE =
        "com.sehinc.common.db.user.CapUserModule.findByUserAndModule";
    private static final
    String
        FIND_BY_USER =
        "com.sehinc.common.db.user.CapUserModule.findByUser";
    private static final
    String
        FIND_BY_USER_ID_AND_MODULE_CODE =
        "com.sehinc.common.db.user.CapUserModule.findByUserIdAndModuleCode";
    private
    int
        clientId;

    public int getClientId()
    {
        return clientId;
    }

    public void setClientId(int clientId)
    {
        this.clientId =
            clientId;
    }

    public CapUserModule()
    {
    }

    public CapUserModule(Integer id)
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

    public Integer getModuleId()
    {
        return this.moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public Boolean getIsActive()
    {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive)
    {
        this.isActive =
            isActive;
    }

    public static List findByUserId(Integer userId, Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "userId",
                    userId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_USER,
                                              parameters);
    }

    public static CapUserModule findByUserIdAndModuleId(Integer userId, Integer moduleId, Integer clientId)
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
                    "clientId",
                    clientId},
                {
                    "moduleId",
                    moduleId}};
        return (CapUserModule) HibernateUtil.findUniqueByNamedQuery(FIND_BY_USER_AND_MODULE,
                                                                    parameters);
    }

    public static CapUserModule findByUserIdAndModuleCode(Integer userId, String code, Integer clientId)
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
                    "clientId",
                    clientId},
                {
                    "code",
                    code}};
        return (CapUserModule) HibernateUtil.findUniqueByNamedQuery(FIND_BY_USER_ID_AND_MODULE_CODE,
                                                                    parameters);
    }
}