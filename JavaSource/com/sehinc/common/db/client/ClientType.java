package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;

public class ClientType
    extends HibernateData
{
    private
    Integer
        clientId;
    private
    Integer
        clientTypeId;

    public ClientType()
    {
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

    public Integer getClientTypeId()
    {
        return clientTypeId;
    }

    public void setClientTypeId(Integer clientTypeId)
    {
        this.clientTypeId =
            clientTypeId;
    }
}