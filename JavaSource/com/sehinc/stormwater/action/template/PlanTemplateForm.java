package com.sehinc.stormwater.action.template;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class PlanTemplateForm
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
        permitTypeId;
    private
    String
        permitTypeName;

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getViewDescription()
    {
        return viewString(description);
    }

    public String getDescription()
    {
        return description;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getPermitTypeId()
    {
        return permitTypeId;
    }

    public void setPermitTypeId(Integer permitTypeId)
    {
        this.permitTypeId =
            permitTypeId;
    }

    public String getPermitTypeName()
    {
        return permitTypeName;
    }

    public void setPermitTypeName(String permitTypeName)
    {
        this.permitTypeName =
            permitTypeName;
    }

    public void reset()
    {
        this.id =
            null;
        this.name =
            null;
        this.description =
            null;
        this.permitTypeId =
            null;
        this.permitTypeName =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Plan Name"));
        }
    }
}
