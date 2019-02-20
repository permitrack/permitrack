package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvDisplayColorData
    extends StringLookupData
{
    private final static
    String
        FIND_ALL =
        "EnvDisplayColorData.findAllInOrder";
    private final static
    String
        FIND_BY_CODE =
        "EnvDisplayColorData.findByCode";

    public static List findAllInOrder()
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {};
        return HibernateUtil.findByNamedQuery(FIND_ALL,
                                              parameters);
    }

    public static EnvDisplayColorData findByTypeCode(String code)
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
        return (EnvDisplayColorData) HibernateUtil.findUniqueByNamedQuery(FIND_BY_CODE,
                                                                          parameters);
    }
}