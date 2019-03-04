package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings("unused")
public class EcInspectionBmpCondition
    extends HibernateData
{
    public final static
    Integer
        BMP_CONDITION_NOT_APPLICABLE =
        new Integer(1);
    public final static
    Integer
        BMP_CONDITION_CORRECT =
        new Integer(2);
    public final static
    Integer
        BMP_CONDITION_NOT_APPLIED =
        new Integer(3);
    public final static
    Integer
        BMP_CONDITION_INEFFECTIVE =
        new Integer(4);
    public final static
    Integer
        BMP_CONDITION_ROUTINE =
        new Integer(5);
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionBmpCondition.class);
    private
    String
        name;
    private
    String
        description;
    private
    Boolean
        isPassCondition;
    private
    Boolean
        isWarnCondition;

    public EcInspectionBmpCondition()
    {
    }

    public EcInspectionBmpCondition(Integer id)
    {
        setId(id);
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

    public Boolean getIsPassCondition()
    {
        return this.isPassCondition;
    }

    public void setIsPassCondition(Boolean isPassCondition)
    {
        this.isPassCondition =
            isPassCondition;
    }

    public Boolean getIsWarnCondition()
    {
        return this.isWarnCondition;
    }

    public void setIsWarnCondition(Boolean isWarnCondition)
    {
        this.isWarnCondition =
                isWarnCondition;
    }

    public static List findAllSortedByName()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from EcInspectionBmpCondition as data where order by data.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllSortedById()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from EcInspectionBmpCondition as data order by data.id asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}