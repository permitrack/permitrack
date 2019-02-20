package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionBmpCondition;
import org.apache.log4j.Logger;

public class InspectionBmpConditionValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionBmpStatusValue.class);
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        description;
    private
    boolean
        isPassCondition;

    public InspectionBmpConditionValue()
    {
    }

    public InspectionBmpConditionValue(EcInspectionBmpCondition inspectionBmpCondition)
    {
        this.id =
            inspectionBmpCondition.getId();
        this.name =
            inspectionBmpCondition.getName();
        this.description =
            inspectionBmpCondition.getDescription();
        this.isPassCondition =
            inspectionBmpCondition.getIsPassCondition()
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

    public String getName()
    {
        if (this.name
            == null)
        {
            return "none";
        }
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        if (this.description
            == null)
        {
            return "none";
        }
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public boolean getIsPassCondition()
    {
        return this.isPassCondition;
    }

    public void setIsPassCondition(boolean isPassCondition)
    {
        this.isPassCondition =
            isPassCondition;
    }

    public String getIsPassConditionText()
    {
        if (isPassCondition)
        {
            return "true";
        }
        return "false";
    }

    public void setIsPassConditionText(String isPassConditionText)
    {
        if (isPassConditionText
            != null
            && isPassConditionText
               != ""
            && isPassConditionText.equalsIgnoreCase("true"))
        {
            this.isPassCondition =
                true;
        }
        else
        {
            this.isPassCondition =
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
        buffer.append("\nname="
                      + name);
        buffer.append("\ndescription="
                      + description);
        buffer.append("\nisPassCondition="
                      + isPassCondition);
        buffer.append("\n\n");
        return buffer.toString();
    }
}