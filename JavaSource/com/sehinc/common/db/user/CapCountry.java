package com.sehinc.common.db.user;

import com.sehinc.common.db.hibernate.HibernateData;

public class CapCountry
    extends HibernateData
{
    private
    Integer
        id;
    private
    String
        code;
    private
    String
        name;

    public CapCountry()
    {
    }

    public CapCountry(Integer id)
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

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + id);
        buffer.append("\ncode="
                      + code);
        buffer.append("\nname="
                      + name);
        buffer.append("\n\n");
        return buffer.toString();
    }
}