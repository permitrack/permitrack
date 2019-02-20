package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvFacilityContactTypeData
    extends LookupData
{
    private
    Integer
        clientId;
    private final static
    String
        FIND_ALL_BY_CLIENT_ID =
        "EnvFacilityContactTypeData.findAllByClientId";
    private final static
    String
        FIND_BY_CODE =
        "EnvFacilityContactTypeData.findByCode";
    public final static
    String
        FACILITY_TYPE_FACILITY_CONTACT =
        "1";
    public final static
    String
        FACILITY_TYPE_PLANT_MANAGER =
        "2";

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

    public static EnvFacilityContactTypeData findByTypeCode(Integer code)
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
        return (EnvFacilityContactTypeData) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                                 parameters);
    }
}

