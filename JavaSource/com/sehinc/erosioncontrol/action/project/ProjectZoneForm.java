package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

@SuppressWarnings("serial")
public class ProjectZoneForm
    extends BaseValidatorForm
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectZoneForm.class);
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

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
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

    public void reset()
    {
        this.id =
            null;
        this.clientId =
            null;
        this.name =
            null;
        this.description =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
