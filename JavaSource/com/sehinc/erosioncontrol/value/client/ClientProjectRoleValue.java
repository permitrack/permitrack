package com.sehinc.erosioncontrol.value.client;

import com.sehinc.erosioncontrol.db.client.EcClientProjectRole;

public class ClientProjectRoleValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        code;
    private
    String
        description;

    public ClientProjectRoleValue()
    {
    }

    public ClientProjectRoleValue(EcClientProjectRole ecClientProjectRole)
    {
        if (ecClientProjectRole
            != null)
        {
            this.id =
                ecClientProjectRole.getId();
            this.code =
                ecClientProjectRole.getCode();
            this.description =
                ecClientProjectRole.getDescription();
        }
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

    public String getCode()
    {
        return code;
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

    public boolean equals(Object o)
    {
        if (o
            != null
            && o instanceof ClientProjectRoleValue)
        {
            ClientProjectRoleValue
                other =
                (ClientProjectRoleValue) o;
            return (this.getId()
                        .equals(other.getId()));
        }
        return false;
    }

    public String toString()
    {
        return "id="
               + getId()
               +
               "\ndescription="
               + getDescription()
               +
               "\ncode="
               + getCode();
    }
}
