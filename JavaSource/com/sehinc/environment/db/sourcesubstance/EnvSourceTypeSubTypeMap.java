package com.sehinc.environment.db.sourcesubstance;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

import java.util.List;

public class EnvSourceTypeSubTypeMap
    extends HibernateData
{
    public static
    String
        FIND_BY_SUBSTANCE_TYPE_CD =
        "EnvSourceTypeSubTypeMap.findBySubstanceTypeCd";
    public static
    String
        FIND_BY_SOURCE_TYPE_CD =
        "EnvSourceTypeSubTypeMap.findBySourceTypeCd";
    private
    Integer
        sourceTypeCd;
    private
    Integer
        substanceTypeCd;

    public EnvSourceTypeSubTypeMap()
    {
    }

    public EnvSourceTypeSubTypeMap(Integer id)
    {
        setId(id);
    }

    public Integer getSubstanceTypeCd()
    {
        return substanceTypeCd;
    }

    public void setSubstanceTypeCd(Integer substanceTypeCd)
    {
        this.substanceTypeCd =
            substanceTypeCd;
    }

    public Integer getSourceTypeCd()
    {
        return sourceTypeCd;
    }

    public void setSourceTypeCd(Integer sourceTypeCd)
    {
        this.sourceTypeCd =
            sourceTypeCd;
    }

    public static List findSourceTypesBySubstanceTypeCd(Integer substanceTypeCd)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "substanceTypeCd",
                    substanceTypeCd}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SUBSTANCE_TYPE_CD,
                                              parameters);
    }

    public static List findSubstanceTypesBySourceTypeCd(Integer sourceTypeCd)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "sourceTypeCd",
                    sourceTypeCd}};
        return HibernateUtil.findByNamedQuery(FIND_BY_SOURCE_TYPE_CD,
                                              parameters);
    }
}