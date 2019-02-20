package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.erosioncontrol.db.inspection.EcInspectionTemplate;

public class InspectionTemplateValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;

    public InspectionTemplateValue()
    {
    }

    public InspectionTemplateValue(EcInspectionTemplate inspectionTemplate)
    {
        this.id =
            inspectionTemplate.getId();
        this.clientId =
            inspectionTemplate.getClientId();
        this.name =
            inspectionTemplate.getName();
        this.description =
            inspectionTemplate.getDescription();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean equals(Object o)
    {
        if (o instanceof InspectionTemplateValue)
        {
            InspectionTemplateValue
                other =
                (InspectionTemplateValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
