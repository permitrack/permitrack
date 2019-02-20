package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings("unused")
public class EcInspectionReason
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionReason.class);
    private
    String
        name;
    private
    String
        description;

    public EcInspectionReason()
    {
    }

    public EcInspectionReason(Integer id)
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
            "select data from EcInspectionReason as data where data.id > 0 order by data.name asc";
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
            "select data from EcInspectionReason as data where data.id > 0 order by data.id asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}