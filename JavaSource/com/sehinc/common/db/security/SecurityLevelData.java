package com.sehinc.common.db.security;

import com.sehinc.common.db.hibernate.HibernateData;

public class SecurityLevelData
    extends HibernateData
{
    private
    String
        name;
    private
    Integer
        level;

    public SecurityLevelData()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level =
            level;
    }
}
