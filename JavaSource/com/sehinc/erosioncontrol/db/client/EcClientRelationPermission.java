package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.client.CapClientTypeInfo;
import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.security.CapPermission;
import com.sehinc.common.db.security.CapSecureObject;
import com.sehinc.common.util.StringUtil;

import java.util.List;

public class EcClientRelationPermission
    extends HibernateData
{
    private
    CapClientTypeInfo
        clientType =
        new CapClientTypeInfo();
    private
    EcClientRelationshipType
        clientRelationshipType =
        new EcClientRelationshipType();
    private
    CapSecureObject
        secureObject =
        new CapSecureObject();
    private
    CapPermission
        permission =
        new CapPermission();
    private
    String
        description =
        new String("");
    private static final
    String
        FIND_BY_CLIENT_TYPE_AND_RELATIONSHIP_TYPE =
        "com.sehinc.erosioncontrol.db.client.EcClientRelationPermission.findByClientTypeIdAndRelationshipTypeId";

    public EcClientRelationPermission()
    {
    }

    public EcClientRelationshipType getClientRelationshipType()
    {
        return clientRelationshipType;
    }

    public CapClientTypeInfo getClientType()
    {
        return clientType;
    }

    public String getDescription()
    {
        return description;
    }

    public CapPermission getPermission()
    {
        return permission;
    }

    public CapSecureObject getSecureObject()
    {
        return secureObject;
    }

    public void setClientRelationshipType(EcClientRelationshipType value)
    {
        clientRelationshipType =
            value;
    }

    public void setClientType(CapClientTypeInfo value)
    {
        clientType =
            value;
    }

    public void setDescription(String string)
    {
        description =
            StringUtil.maxLength(string,
                                 100);
    }

    public void setPermission(CapPermission value)
    {
        permission =
            value;
    }

    public void setSecureObject(CapSecureObject value)
    {
        secureObject =
            value;
    }

    public static List findByClientTypeAndRelationshipType(Integer clientTypeId, Integer clientRelationshipTypeId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientTypeId",
                    clientTypeId},
                {
                    "clientRelationshipTypeId",
                    clientRelationshipTypeId}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_TYPE_AND_RELATIONSHIP_TYPE,
                                              parameters);
    }
}
