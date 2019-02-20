package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class CapState
    extends HibernateData
{
    private
    Integer
        id;
    private
    CapCountry
        country;
    private
    String
        code;
    private
    String
        name;

    public CapState()
    {
    }

    public CapState(Integer id)
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

    public CapCountry getCountry()
    {
        return this.country;
    }

    public void setCountry(CapCountry country)
    {
        this.country =
            country;
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

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public static CapState findByCode(String code)
    {
        Object
            parameters
            [
            ] =
            {code};
        String
            queryString =
            "select data from CapState as data where data.code = ?";
        return (CapState) HibernateUtil.findUnique(queryString,
                                                   parameters);
    }

    public static List findByCountryCode(String code)
    {
        Object
            parameters
            [
            ] =
            {code};
        String
            queryString =
            "select data from CapState as data where data.country.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findNonArmedForcesStates()
    {
        String
            queryString =
            "select s from CapState as s where s.name not like 'armed%' order by s.code";
        return HibernateUtil.find(queryString,
                                  new Object[0]);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + id);
        buffer.append("\nname="
                      + name);
        buffer.append("\ncode="
                      + code);
        buffer.append("\ncountry="
                      + country);
        buffer.append("\n\n");
        return buffer.toString();
    }
}