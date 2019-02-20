package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapSecureObject
    extends HibernateData
{
    private
    Integer
        id;
    private
    Integer
        moduleId;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private static final
    String
        FIND_BY_MODULE_ID =
        "com.sehinc.common.db.security.CapSecureObject.byModuleId";
    private static final
    String
        FIND_BY_SO_ID =
        "com.sehinc.common.db.security.CapSecureObject.bySoId";

    public CapSecureObject()
    {
    }

    public CapSecureObject(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public static List findByModuleId(Integer moduleId)
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
                    moduleId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_MODULE_ID,
                                              parameters);
    }

    public static List findBySecureObjectId(Integer secureObjectId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "secureObjectId",
                    secureObjectId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SO_ID,
                                              parameters);
    }
}