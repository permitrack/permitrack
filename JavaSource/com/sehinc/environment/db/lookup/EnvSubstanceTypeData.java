package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvSubstanceTypeData
    extends LookupData
{
    private
    Integer
        clientId;
    private final static
    String
        FIND_ALL_BY_CLIENT_ID =
        "EnvSubstanceTypeData.findAllByClientId";
    private final static
    String
        FIND_BY_CODE =
        "EnvSubstanceTypeData.findByCode";
    public final static
    String
        SUBSTANCE_TYPE_VOC =
        "1";
    public final static
    String
        SUBSTANCE_TYPE_HAP =
        "2";
    public final static
    String
        SUBSTANCE_TYPE_ALL_VOCS =
        "3";
    public final static
    String
        SUBSTANCE_TYPE_CO =
        "4";
    public final static
    String
        SUBSTANCE_TYPE_NH3 =
        "5";
    public final static
    String
        SUBSTANCE_TYPE_NOX =
        "6";
    public final static
    String
        SUBSTANCE_TYPE_LEAD =
        "7";
    public final static
    String
        SUBSTANCE_TYPE_PM10 =
        "8";
    public final static
    String
        SUBSTANCE_TYPE_PM25 =
        "9";
    public final static
    String
        SUBSTANCE_TYPE_SOX =
        "10";

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

    public static EnvSubstanceTypeData findByTypeCode(Integer code)
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
        return (EnvSubstanceTypeData) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                           parameters);
    }
}