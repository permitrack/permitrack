package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class ProjectZoneDeleteForm
    extends BaseValidatorForm
{
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
        projectZoneReplaceId;
    private
    String
        deleteOption;

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

    public Integer getProjectZoneReplaceId()
    {
        return this.projectZoneReplaceId;
    }

    public void setProjectZoneReplaceId(Integer projectZoneReplaceId)
    {
        this.projectZoneReplaceId =
            projectZoneReplaceId;
    }

    public String getDeleteOption()
    {
        return this.deleteOption;
    }

    public void setDeleteOption(String deleteOption)
    {
        this.deleteOption =
            deleteOption;
    }

    public void reset()
    {
        this.id =
            null;
        this.name =
            null;
        this.description =
            null;
        this.projectZoneReplaceId =
            null;
        this.deleteOption =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
