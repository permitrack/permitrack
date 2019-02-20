package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.erosioncontrol.db.bmp.EcBmp;
import org.apache.log4j.Logger;

import java.util.List;

public class EcInspectionTemplateBmp
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionTemplateBmp.class);
    private
    Integer
        inspectionTemplateId;
    private
    String
        description;
    private
    Boolean
        isRequired;
    private
    EcBmp
        bmp;

    public EcInspectionTemplateBmp()
    {
    }

    public EcInspectionTemplateBmp(Integer id)
    {
        setId(id);
    }

    public Integer getInspectionTemplateId()
    {
        return this.inspectionTemplateId;
    }

    public void setInspectionTemplateId(Integer inspectionTemplateId)
    {
        this.inspectionTemplateId =
            inspectionTemplateId;
    }

    public EcBmp getBmp()
    {
        return this.bmp;
    }

    public void setBmp(EcBmp bmp)
    {
        this.bmp =
            bmp;
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

    public Boolean getIsRequired()
    {
        return this.isRequired;
    }

    public void setIsRequired(Boolean isRequired)
    {
        this.isRequired =
            isRequired;
    }

    public static List findByInspectionTemplateId(Integer id, Integer client_id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                client_id};
        String
            queryString =
            "select data from EcInspectionTemplateBmp as data "
            +
            "where data.inspectionTemplateId = ? and "
            +
            "data.bmp.status.code = 1 and "
            +
            "data.bmp.clientId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}