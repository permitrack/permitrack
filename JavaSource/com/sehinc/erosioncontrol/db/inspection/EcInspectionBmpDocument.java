package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

public class EcInspectionBmpDocument
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionBmpDocument.class);
    private
    Integer
        inspectionBmpId;
    private
    String
        name;
    private
    String
        location;
    private
    String
        comment;

    public EcInspectionBmpDocument()
    {
    }

    public EcInspectionBmpDocument(Integer id)
    {
        setId(id);
    }

    public Integer getInspectionBmpId()
    {
        return this.inspectionBmpId;
    }

    public void setInspectionBmpId(Integer inspectionBmpId)
    {
        this.inspectionBmpId =
            inspectionBmpId;
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

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location =
            location;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
    }

    public static EcInspectionBmpDocument findByInspectionBmpId(Integer inspectionBmpId)
    {
        Object
            parameters
            [
            ] =
            {inspectionBmpId};
        String
            queryString =
            "select data from EcInspectionBmpDocument as data where data.inspectionBmpId = ?";
        return (EcInspectionBmpDocument) HibernateUtil.findUnique(queryString,
                                                                  parameters);
    }
}