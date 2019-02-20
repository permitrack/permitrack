package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmp;
import org.apache.log4j.Logger;

import java.util.Date;

public class InspectionBmpValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionBmpValue.class);
    private
    Integer
        id;
    private
    Integer
        projectBmpId;
    private
    Integer
        inspectionId;
    private
    String
        bmpCategoryName;
    private
    String
        name;
    private
    String
        description;
    private
    String
        comment;
    private
    String
        location;
    private
    Date
        inspectionDate;
    private
    boolean
        isRequired =
        false;
    private
    boolean
        isDeleted =
        false;
    private
    Integer
        isInspected;
    private
    InspectionBmpStatusValue
        bmpStatus =
        new InspectionBmpStatusValue();
    private
    InspectionBmpConditionValue
        bmpCondition =
        new InspectionBmpConditionValue();
    private
    InspectionBmpDocumentValue
        bmpDocument =
        new InspectionBmpDocumentValue();

    public InspectionBmpValue()
    {
    }

    public InspectionBmpValue(EcInspectionBmp inspectionBmp)
    {
        this.id =
            inspectionBmp.getId();
        this.projectBmpId =
            inspectionBmp.getProjectBmpId();
        this.inspectionId =
            inspectionBmp.getInspectionId();
        this.bmpCategoryName =
            inspectionBmp.getBmpCategoryName();
        this.name =
            inspectionBmp.getBmpName();
        this.description =
            inspectionBmp.getDescription();
        this.comment =
            inspectionBmp.getComment();
        this.location =
            inspectionBmp.getLocation();
        this.isRequired =
            inspectionBmp.getIsRequired()
                .booleanValue();
        this.isInspected =
            inspectionBmp.getIsInspected();
        if (inspectionBmp.getInspectionBmpStatus()
            != null)
        {
            this.bmpStatus =
                new InspectionBmpStatusValue(inspectionBmp.getInspectionBmpStatus());
        }
        if (inspectionBmp.getInspectionBmpCondition()
            != null)
        {
            this.bmpCondition =
                new InspectionBmpConditionValue(inspectionBmp.getInspectionBmpCondition());
        }
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

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId)
    {
        this.inspectionId =
            inspectionId;
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

    public String getBmpCategoryName()
    {
        return this.bmpCategoryName;
    }

    public void setBmpCategoryName(String bmpCategoryName)
    {
        this.bmpCategoryName =
            bmpCategoryName;
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

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
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

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Date getInspectionDate()
    {
        return this.inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate =
            inspectionDate;
    }

    public String getInspectionDateString()
    {
        return DateUtil.mdyDate(this.inspectionDate);
    }

    public void setInspectionDateString(String inspectionDate)
    {
        this.inspectionDate =
            DateUtil.parseDate(inspectionDate);
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

    public Integer getIsInspected()
    {
        return this.isInspected;
    }

    public void setIsInspected(Integer isInspected)
    {
        this.isInspected =
            isInspected;
    }

    public String getIsInspectedText()
    {
        if (isInspected
            == null)
        {
            return "";
        }
        else if (isInspected.intValue()
                 == 0)
        {
            return "false";
        }
        return "true";
    }

    public String getIsInspectedYesNoText()
    {
        if (isInspected
            == null)
        {
            return "";
        }
        else if (isInspected.intValue()
                 == 0)
        {
            return "No";
        }
        return "Yes";
    }

    public void setIsInspectedText(String isInspected)
    {
        if (isInspected
            == null
            || isInspected
               == "")
        {
            this.isInspected =
                null;
        }
        else if (isInspected.equalsIgnoreCase("false"))
        {
            this.isInspected =
                new Integer(0);
        }
        else
        {
            this.isInspected =
                new Integer(1);
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

    public InspectionBmpDocumentValue getBmpDocument()
    {
        return this.bmpDocument;
    }

    public void setBmpDocument(InspectionBmpDocumentValue bmpDocument)
    {
        this.bmpDocument =
            bmpDocument;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + id);
        buffer.append("\ninspectionId="
                      + inspectionId);
        buffer.append("\nprojectBmpId="
                      + projectBmpId);
        buffer.append("\nbmpCategoryName="
                      + bmpCategoryName);
        buffer.append("\nname="
                      + name);
        buffer.append("\ndescription="
                      + description);
        buffer.append("\nlocation="
                      + location);
        buffer.append("\nisRequired="
                      + isRequired);
        buffer.append("\nisRequiredText="
                      + getIsRequiredText());
        buffer.append("\nisDeleted="
                      + isDeleted);
        buffer.append("\nisInspected="
                      + isInspected);
        buffer.append("\n\n");
        return buffer.toString();
    }
}