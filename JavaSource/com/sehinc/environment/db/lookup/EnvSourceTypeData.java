package com.sehinc.environment.db.lookup;

import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvSourceTypeData
    extends LookupData
{
    private
    Integer
        clientId;
    private final static
    String
        FIND_ALL_BY_CLIENT_ID =
        "EnvSourceTypeData.findAllByClientId";
    public final static
    String
        SOURCE_TYPE_PAINT_RELATED =
        "1";
    public final static
    String
        SOURCE_TYPE_NATURAL_GAS =
        "2";
    public final static
    String
        SOURCE_TYPE_BULK_LIQUID =
        "3";

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

    public static String getSourceTypeName(String sourceType)
    {
        String
            name;
        try
        {
            switch (Integer.parseInt(sourceType))
            {
                case 1:
                    name =
                        "Paint related";
                    break;
                case 2:
                    name =
                        "Natural gas";
                    break;
                case 3:
                    name =
                        "Bulk liquid";
                    break;
                default:
                    name =
                        "Unknown";
            }
            return name;
        }
        catch (NumberFormatException e)
        {
            return "Unknown";
        }
    }
}