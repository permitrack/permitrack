package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EcInspectionAction
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionAction.class);
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        displaySequence;

    public EcInspectionAction()
    {
    }

    public EcInspectionAction(Integer id)
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

    public Integer getDisplaySequence()
    {
        return this.displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence)
    {
        this.displaySequence =
            displaySequence;
    }

    public static List findAllSortedByDisplaySequence()
    {
        Object
            parameters
            [
            ] =
            {};
        String
            queryString =
            "select data from EcInspectionAction as data order by data.displaySequence asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}