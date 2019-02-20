package com.sehinc.erosioncontrol.value.client;

import com.sehinc.erosioncontrol.db.client.EcClientRelationship;

public class ClientRelationshipValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    Integer
        relatedClientId;
    private
    Integer
        relatedClientUserId;
    private
    Integer
        clientRelationshipTypeId;
    private
    String
        clientRelationshipTypeName;

    public ClientRelationshipValue()
    {
    }

    public ClientRelationshipValue(EcClientRelationship ecClientRelationship)
    {
        this.id =
            ecClientRelationship.getId();
        this.clientId =
            ecClientRelationship.getClientId();
        this.relatedClientId =
            ecClientRelationship.getRelatedClientId();
        this.relatedClientUserId =
            ecClientRelationship.getRelatedClientUserId();
        this.clientRelationshipTypeId =
            ecClientRelationship.getClientRelationshipType()
                .getId();
        this.clientRelationshipTypeName =
            ecClientRelationship.getClientRelationshipType()
                .getName();
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

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setRelatedClientId(Integer relatedClientId)
    {
        this.relatedClientId =
            relatedClientId;
    }

    public Integer getRelatedClientId()
    {
        return relatedClientId;
    }

    public void setRelatedClientUserId(Integer relatedClientUserId)
    {
        this.relatedClientUserId =
            relatedClientUserId;
    }

    public Integer getRelatedClientUserId()
    {
        return relatedClientUserId;
    }

    public void setClientRelationshipTypeId(Integer clientRelationshipTypeId)
    {
        this.clientRelationshipTypeId =
            clientRelationshipTypeId;
    }

    public Integer getClientRelationshipTypeId()
    {
        return clientRelationshipTypeId;
    }

    public void setClientRelationshipTypeName(String clientRelationshipTypeName)
    {
        this.clientRelationshipTypeName =
            clientRelationshipTypeName;
    }

    public String getClientRelationshipTypeName()
    {
        return clientRelationshipTypeName;
    }

    public boolean equals(Object o)
    {
        if (o instanceof EcClientRelationship)
        {
            EcClientRelationship
                other =
                (EcClientRelationship) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
