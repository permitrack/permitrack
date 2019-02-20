package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EcClientRelationship
    extends HibernateData
{
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
    EcClientRelationshipType
        clientRelationshipType;

    public EcClientRelationship()
    {
    }

    public EcClientRelationship(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getRelatedClientUserId()
    {
        return this.relatedClientUserId;
    }

    public void setRelatedClientUserId(Integer relatedClientUserId)
    {
        this.relatedClientUserId =
            relatedClientUserId;
    }

    public Integer getRelatedClientId()
    {
        return this.relatedClientId;
    }

    public void setRelatedClientId(Integer relatedClientId)
    {
        this.relatedClientId =
            relatedClientId;
    }

    public EcClientRelationshipType getClientRelationshipType()
    {
        return this.clientRelationshipType;
    }

    public void setClientRelationshipType(EcClientRelationshipType clientRelationshipType)
    {
        this.clientRelationshipType =
            clientRelationshipType;
    }

    public static EcClientRelationship findByClientAndRelatedClient(Integer clientId, Integer relatedClientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                relatedClientId};
        String
            queryString =
            "select data from EcClientRelationship as data where data.clientId = ? and data.relatedClientId = ?";
        return (EcClientRelationship) HibernateUtil.findUnique(queryString,
                                                               parameters);
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcClientRelationship as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllByRelatedClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcClientRelationship as data where data.relatedClientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}