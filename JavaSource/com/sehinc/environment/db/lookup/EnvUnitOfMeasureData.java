package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvUnitOfMeasureData
    extends LookupData
{
    private
    Integer
        clientId;
    private final static
    String
        FIND_ALL_BY_CLIENT_ID =
        "EnvUnitOfMeasureData.findAllByClientId";
    private final static
    String
        FIND_BY_CODE =
        "EnvUnitOfMeasureData.findByCode";
    public final static
    String
        UNIT_OF_MEASURE_GALLONS =
        "1";
    public final static
    String
        UNIT_OF_MEASURE_OUNCES =
        "2";
    public final static
    String
        UNIT_OF_MEASURE_LITERS =
        "3";
    public final static
    String
        UNIT_OF_MEASURE_TONS =
        "4";
    public final static
    String
        UNIT_OF_MEASURE_MMCF =
        "5";
    public final static
    String
        UNIT_OF_MEASURE_LBS_PER_GAL =
        "6";
    public final static
    String
        UNIT_OF_MEASURE_LBS_PER_1000_GAL =
        "7";
    public final static
    String
        UNIT_OF_MEASURE_LBS_PER_MMCF =
        "8";

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

    public static EnvUnitOfMeasureData findByTypeCode(Integer code)
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
        return (EnvUnitOfMeasureData) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                           parameters);
    }
}
