package com.sehinc.erosioncontrol.value.client;

import com.sehinc.erosioncontrol.db.client.EcClientRelationshipType;

public class ClientRelationshipTypeValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        description;

    public ClientRelationshipTypeValue()
    {
    }

    public ClientRelationshipTypeValue(EcClientRelationshipType ecClientRelationshipType)
    {
        this.id =
            ecClientRelationshipType.getId();
        this.name =
            ecClientRelationshipType.getName();
        this.description =
            ecClientRelationshipType.getDescription();
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

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getdescription()
    {
        return description;
    }

    public boolean equals(Object o)
    {
        if (o instanceof EcClientRelationshipType)
        {
            EcClientRelationshipType
                other =
                (EcClientRelationshipType) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
