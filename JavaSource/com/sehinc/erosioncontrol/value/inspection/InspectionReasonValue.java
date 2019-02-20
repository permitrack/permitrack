package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionReason;
import org.apache.log4j.Logger;

public class InspectionReasonValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionReasonValue.class);
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        description;

    public InspectionReasonValue()
    {
    }

    public InspectionReasonValue(EcInspectionReason inspectionReason)
    {
        this.id =
            inspectionReason.getId();
        this.name =
            inspectionReason.getName();
        this.description =
            inspectionReason.getDescription();
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
        buffer.append("\n\n");
        return buffer.toString();
    }
}