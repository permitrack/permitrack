package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionAction;
import org.apache.log4j.Logger;

public class InspectionActionValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionActionValue.class);
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
    Integer
        displaySequence;

    public InspectionActionValue()
    {
    }

    public InspectionActionValue(EcInspectionAction inspectionAction)
    {
        this.id =
            inspectionAction.getId();
        this.name =
            inspectionAction.getName();
        this.description =
            inspectionAction.getDescription();
        this.displaySequence =
            inspectionAction.getDisplaySequence();
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

    public Integer getDisplaySequence()
    {
        return this.displaySequence;
    }

    public void setDisplaySequence(Integer displaySequence)
    {
        this.displaySequence =
            displaySequence;
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
        buffer.append("\ndisplaySequence="
                      + displaySequence);
        buffer.append("\n\n");
        return buffer.toString();
    }
}