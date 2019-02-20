package com.sehinc.common.value.group;

import com.sehinc.common.db.group.GroupData;

public class GroupValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    Integer
        securityLevelId;

    public GroupValue()
    {
    }

    public GroupValue(GroupData groupData)
    {
        this.id =
            groupData.getId();
        this.name =
            groupData.getName();
        this.securityLevelId =
            groupData.getSecurityLevelId();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setSecurityLevelId(Integer securityLevelId)
    {
        this.securityLevelId =
            securityLevelId;
    }

    public Integer getSecurityLevelId()
    {
        return securityLevelId;
    }
}
