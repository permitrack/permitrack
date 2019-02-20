package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvAssetTypeData
    extends LookupData
{
    private
    Integer
        clientId;
    private final static
    String
        FIND_ALL_BY_CLIENT_ID =
        "EnvAssetTypeData.findAllByClientId";
    private final static
    String
        FIND_BY_CODE =
        "EnvAssetTypeData.findByCode";

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId}};
        return HibernateUtil.findByNamedQuery(FIND_ALL_BY_CLIENT_ID,
                                              parameters);
    }

    public static EnvAssetTypeData findByTypeCode(Integer code)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "code",
                    code}};
        return (EnvAssetTypeData) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                       parameters);
    }
}