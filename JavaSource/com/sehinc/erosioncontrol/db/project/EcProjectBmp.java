package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpCondition;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class EcProjectBmp
    extends UserUpdatableData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectBmp.class);
    private
    Integer
        projectId;
    private
    String
        bmpName;
    private
    String
        categoryName;
    private
    String
        description;
    private
    Boolean
        isRequired;
    private
    EcInspectionBmpStatus
        inspectionBmpStatus;
    private
    EcInspectionBmpCondition
        inspectionBmpCondition;

    public EcProjectBmp()
    {
    }

    public EcProjectBmp(Integer id)
    {
        setId(id);
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public String getBmpName()
    {
        return this.bmpName;
    }

    public void setBmpName(String bmpName)
    {
        this.bmpName =
            bmpName;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName =
            categoryName;
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

    public EcInspectionBmpStatus getInspectionBmpStatus()
    {
        return this.inspectionBmpStatus;
    }

    public void setInspectionBmpStatus(EcInspectionBmpStatus inspectionBmpStatus)
    {
        this.inspectionBmpStatus =
            inspectionBmpStatus;
    }

    public EcInspectionBmpCondition getInspectionBmpCondition()
    {
        return this.inspectionBmpCondition;
    }

    public void setInspectionBmpCondition(EcInspectionBmpCondition inspectionBmpCondition)
    {
        this.inspectionBmpCondition =
            inspectionBmpCondition;
    }

    public static List findByProjectId(Integer projectId)
    {
        Object
            parameters
            [
            ] =
            {projectId};
        String
            queryString =
            "select data from EcProjectBmp as data where data.projectId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + getId());
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\nbmpName="
                      + bmpName);
        buffer.append("\ncategoryName="
                      + categoryName);
        buffer.append("\ndescription="
                      + description);
        buffer.append("\nisRequired="
                      + isRequired);
        buffer.append("\n\n");
        return buffer.toString();
    }
}