package com.sehinc.common.value.client;

import com.sehinc.common.db.client.ClientData;

public class ClientValue
    implements java.io.Serializable, Comparable
{
    private
    Integer
        id;
    private
    String
        name;

    public ClientValue()
    {
    }

    public ClientValue(ClientData clientData)
    {
        this.id =
            clientData.getId();
        this.name =
            clientData.getName();
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

    public int compareTo(Object obj)
    {
        if (obj instanceof ClientValue)
        {
            return getName().compareTo(((ClientValue) obj).getName());
        }
        return 0;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ClientValue)
        {
            ClientValue
                other =
                (ClientValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
