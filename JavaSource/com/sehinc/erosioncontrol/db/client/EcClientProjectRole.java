package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;

import java.util.List;

public class EcClientProjectRole
    extends StatusData
{
    private
    String
        code;
    private
    String
        description;

    public EcClientProjectRole()
    {
    }

    public EcClientProjectRole(Integer id)
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public static List findAll()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from EcClientProjectRole as data";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}