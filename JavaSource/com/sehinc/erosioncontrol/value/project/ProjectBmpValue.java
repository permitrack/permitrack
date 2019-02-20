package com.sehinc.erosioncontrol.value.project;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplateBmp;
import com.sehinc.erosioncontrol.db.project.EcProjectBmp;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpConditionValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionBmpStatusValue;
import org.apache.log4j.Logger;

public class ProjectBmpValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectBmpValue.class);
    private
    Integer
        projectBmpId;
    private
    Integer
        projectId;
    private
    String
        bmpCategoryName;
    private
    String
        bmpName;
    private
    String
        bmpDescription;
    private
    boolean
        isRequired =
        false;
    private
    boolean
        isDeleted =
        false;
    private
    InspectionBmpStatusValue
        bmpStatus;
    private
    InspectionBmpConditionValue
        bmpCondition;

    public ProjectBmpValue()
    {
    }

    public ProjectBmpValue(EcInspectionTemplateBmp inspectionTemplateBmp)
    {
        this.bmpCategoryName =
            inspectionTemplateBmp.getBmp()
                .getCategory()
                .getName();
        this.bmpName =
            inspectionTemplateBmp.getBmp()
                .getName();
        this.bmpDescription =
            inspectionTemplateBmp.getDescription();
        this.isRequired =
            inspectionTemplateBmp.getIsRequired()
                .booleanValue();
    }

    public ProjectBmpValue(EcProjectBmp projectBmp)
    {
        this.projectBmpId =
            projectBmp.getId();
        this.projectId =
            projectBmp.getProjectId();
        this.bmpCategoryName =
            projectBmp.getCategoryName();
        this.bmpName =
            projectBmp.getBmpName();
        this.bmpDescription =
            projectBmp.getDescription();
        this.isRequired =
            projectBmp.getIsRequired()
                .booleanValue();
        if (projectBmp.getInspectionBmpStatus()
            != null)
        {
            this.bmpStatus =
                new InspectionBmpStatusValue(projectBmp.getInspectionBmpStatus());
        }
        if (projectBmp.getInspectionBmpCondition()
            != null)
        {
            this.bmpCondition =
                new InspectionBmpConditionValue(projectBmp.getInspectionBmpCondition());
        }
    }

    public Integer getProjectBmpId()
    {
        return this.projectBmpId;
    }

    public void setProjectBmpId(Integer projectBmpId)
    {
        this.projectBmpId =
            projectBmpId;
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

    public String getBmpCategoryName()
    {
        return this.bmpCategoryName;
    }

    public void setBmpCategoryName(String bmpCategoryName)
    {
        this.bmpCategoryName =
            bmpCategoryName;
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

    public String getBmpDescription()
    {
        return this.bmpDescription;
    }

    public void setBmpDescription(String bmpDescription)
    {
        this.bmpDescription =
            bmpDescription;
    }

    public boolean getIsRequired()
    {
        return this.isRequired;
    }

    public void setIsRequired(boolean isRequired)
    {
        this.isRequired =
            isRequired;
    }

    public String getIsRequiredText()
    {
        if (isRequired)
        {
            return "true";
        }
        return "false";
    }

    public String getIsRequiredYesNoText()
    {
        if (isRequired)
        {
            return "Yes";
        }
        return "No";
    }

    public void setIsRequiredText(String isRequiredText)
    {
        if (isRequiredText
            != null
            && isRequiredText
               != ""
            && isRequiredText.equalsIgnoreCase("true"))
        {
            this.isRequired =
                true;
        }
        else
        {
            this.isRequired =
                false;
        }
    }

    public boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public String getIsDeletedText()
    {
        if (isDeleted)
        {
            return "true";
        }
        return "false";
    }

    public void setIsDeletedText(String isDeleted)
    {
        if (isDeleted
            != null
            && isDeleted
               != ""
            && isDeleted.equalsIgnoreCase("true"))
        {
            this.isDeleted =
                true;
        }
        else
        {
            this.isDeleted =
                false;
        }
    }

    public InspectionBmpStatusValue getBmpStatus()
    {
        return this.bmpStatus;
    }

    public void setBmpStatus(InspectionBmpStatusValue bmpStatus)
    {
        this.bmpStatus =
            bmpStatus;
    }

    public InspectionBmpConditionValue getBmpCondition()
    {
        return this.bmpCondition;
    }

    public void setBmpCondition(InspectionBmpConditionValue bmpCondition)
    {
        this.bmpCondition =
            bmpCondition;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nprojectBmpId="
                      + projectBmpId);
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\nbmpCategoryName="
                      + bmpCategoryName);
        buffer.append("\nbmpName="
                      + bmpName);
        buffer.append("\nbmpDescription="
                      + bmpDescription);
        buffer.append("\nisRequired="
                      + isRequired);
        buffer.append("\nisRequiredText="
                      + getIsRequiredText());
        buffer.append("\nisDeleted="
                      + isDeleted);
        buffer.append("\n\n");
        return buffer.toString();
    }
}