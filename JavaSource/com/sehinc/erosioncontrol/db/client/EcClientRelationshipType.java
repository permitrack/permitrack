package com.sehinc.erosioncontrol.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class EcClientRelationshipType
    extends HibernateData
{
    public static
    String
        CODE_PREFERRED =
        "PREFERRED";
    public static
    String
        CODE_STANDARD =
        "STANDARD";
    private static
    Logger
        LOG =
        Logger.getLogger(EcClientRelationshipType.class);
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private static final
    String
        MIN_RELATIONSHIP_TYPE_BY_RELATED_CLIENT_ID =
        "com.sehinc.erosioncontrol.db.client.EcClientRelationshipType.minRelationshipTypeIdByRelatedClientId";

    public EcClientRelationshipType()
    {
    }

    public EcClientRelationshipType(Integer id)
    {
        setId(id);
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public static Integer minRelationshipTypeIdByRelatedClientId(Integer relatedClientId)
    {
        Object[][]
            parameters =
            {
                {
                    "relatedClientId",
                    relatedClientId}};
        return (Integer) HibernateUtil.findUniqueByNamedQuery(MIN_RELATIONSHIP_TYPE_BY_RELATED_CLIENT_ID,
                                                              parameters);
    }
}