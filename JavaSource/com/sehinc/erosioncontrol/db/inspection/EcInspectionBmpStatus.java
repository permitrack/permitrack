package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EcInspectionBmpStatus
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionBmpStatus.class);
    public final static
    Integer
        BMP_STATUS_ACTIVE =
        new Integer(1);
    public final static
    Integer
        BMP_STATUS_INACTIVE =
        new Integer(2);
    public final static
    Integer
        BMP_STATUS_COMPLETED =
        new Integer(3);
    private
    String
        name;
    private
    String
        description;

    public EcInspectionBmpStatus()
    {
    }

    public EcInspectionBmpStatus(Integer id)
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

    public static List findAllSortedByName()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from EcInspectionBmpStatus as data where data.id > 0 order by data.name asc";
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
            "select data from EcInspectionBmpStatus as data where data.id > 0 order by data.id asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}