package com.sehinc.erosioncontrol.value.project;

import com.sehinc.erosioncontrol.db.project.EcZone;

public class ProjectZoneValue
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

    public ProjectZoneValue()
    {
    }

    public ProjectZoneValue(EcZone zone)
    {
        this.id =
            zone.getId();
        this.clientId =
            zone.getClientId();
        this.name =
            zone.getName();
        this.description =
            zone.getDescription();
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
}
