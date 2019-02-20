package com.sehinc.common.value.security;

import com.sehinc.common.db.security.CapRole;

public class CapRoleValue
    implements java.io.Serializable
{
    private
    Integer
        id;
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
    String
        statusCode;
    private
    Integer
        moduleId;

    public CapRoleValue()
    {
    }

    public CapRoleValue(CapRole capRole)
    {
        this.id =
            capRole.getId();
        this.clientId =
            capRole.getClientId();
        this.code =
            capRole.getCode();
        this.name =
            capRole.getName();
        this.description =
            capRole.getDescription();
        this.moduleId =
            capRole.getModuleId();
        this.statusCode =
            capRole.getStatus()
                .getCode();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getModuleId()
    {
        return moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public boolean equals(Object o)
    {
        if (o
            == null)
        {
            return false;
        }
        if (o instanceof CapRole)
        {
            CapRole
                other =
                (CapRole) o;
            return this.id
                .equals(other.getId());
        }
        return false;
    }
}
