package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapPermission
    extends HibernateData
{
    public static
    String
        CREATE_PERMISSION =
        "CREATE";
    public static
    String
        READ_PERMISSION =
        "READ";
    public static
    String
        UPDATE_PERMISSION =
        "UPDATE";
    public static
    String
        DELETE_PERMISSION =
        "DELETE";
    public static
    String
        STATUS_PERMISSION =
        "STATUS";
    public static
    String
        ACTION_PERMISSION =
        "ACTION";
    private
    Integer
        id;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;

    public CapPermission()
    {
    }

    public CapPermission(Integer id)
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

    public static CapPermission findByCode(String code)
    {
        Object
            parameters
            [
            ] =
            {code};
        String
            queryString =
            "select data from CapPermission as data where data.code = ?";
        return (CapPermission) HibernateUtil.findUnique(queryString,
                                                        parameters);
    }

    public static List findCRUD()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from CapPermission as data where data.id in (1,2,3,4) order by data.id";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}