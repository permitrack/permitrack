package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.common.value.user.UserValue;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class CapModule
    extends StatusData
{
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
    String
        path;
    private
    String
        modulePath;
    private
    Integer
        orderNum;
    private static final
    String
        FIND_BY_CODE =
        "com.sehinc.common.db.security.CapModule.byCode";
    private static final
    String
        ALL_MODULES_BY_USER_AND_MARKUP =
        "com.sehinc.common.db.security.CapModule.allModulesByUserAndMarkup";

    public CapModule()
    {
    }

    public CapModule(Integer id)
    {
        setId(id);
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

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path =
            path;
    }

    public String getModulePath()
    {
        return this.modulePath;
    }

    public void setModulePath(String modulePath)
    {
        this.modulePath =
            modulePath;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum =
            orderNum;
    }

    public static CapModule findByCode(String code)
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
                    code}};
        return (CapModule) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                parameters);
    }

    public static List findAll()
    {
        return HibernateUtil.findAll(CapModule.class);
    }

    public static List findByUserAndMarkup(UserValue user, String markup)
    {
        TreeSet
            capUserModules =
            new TreeSet(new CapModuleComparator());
        Object[][]
            parameters =
            {
                {
                    "userId",
                    user.getId()},
                {
                    "markup",
                    markup}};
        capUserModules.addAll(HibernateUtil.findByNamedQuery(ALL_MODULES_BY_USER_AND_MARKUP,
                                                             parameters));
        return new ArrayList(capUserModules);
    }
}