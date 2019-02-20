package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class EcInspectionDocument
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionDocument.class);
    private
    Integer
        inspectionId;
    private
    String
        name;
    private
    String
        location;
    private
    String
        comment;

    public EcInspectionDocument()
    {
    }

    public EcInspectionDocument(Integer id)
    {
        setId(id);
    }

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId)
    {
        this.inspectionId =
            inspectionId;
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

    public static List findByInspectionId(Integer inspectionId)
    {
        Object[][]
            parameters =
            {
                {
                    "inspectionId",
                    inspectionId}};
        String
            FIND_BY_INSPECTION_ID =
            "com.sehinc.erosioncontrol.db.inspection.EcInspectionDocument.byInspectionId";
        return HibernateUtil.findByNamedQuery(FIND_BY_INSPECTION_ID,
                                              parameters);
    }
}