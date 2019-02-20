package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplateBmp;
import org.apache.log4j.Logger;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class InspectionTemplateBmpValue
    implements java.io.Serializable
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionTemplateBmpValue.class);
    private
    Integer
        id;
    private
    Integer
        inspectionTemplateId;
    private
    Integer
        bmpId;
    private
    Integer
        categoryId;
    private
    String
        categoryName;
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

    public InspectionTemplateBmpValue()
    {
    }

    public InspectionTemplateBmpValue(EcInspectionTemplateBmp inspectionTemplateBmp)
    {
        this.id =
            inspectionTemplateBmp.getId();
        this.inspectionTemplateId =
            inspectionTemplateBmp.getInspectionTemplateId();
        this.bmpId =
            inspectionTemplateBmp.getBmp()
                .getId();
        this.categoryId =
            inspectionTemplateBmp.getBmp()
                .getCategory()
                .getId();
        this.categoryName =
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

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public Integer getBmpId()
    {
        return this.bmpId;
    }

    public void setBmpId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
    }

    public Integer getCategoryId()
    {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId)
    {
        this.categoryId =
            categoryId;
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
            &&
            (isRequiredText.equalsIgnoreCase("true")
             || isRequiredText.equalsIgnoreCase("Yes")))
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

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + id);
        buffer.append("\ninspectionTemplateId="
                      + inspectionTemplateId);
        buffer.append("\nbmpId="
                      + bmpId);
        buffer.append("\ncategoryId="
                      + categoryId);
        buffer.append("\ncategoryName="
                      + categoryName);
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