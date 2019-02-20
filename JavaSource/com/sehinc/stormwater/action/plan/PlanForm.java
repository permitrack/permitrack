package com.sehinc.stormwater.action.plan;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class PlanForm
    extends BaseValidatorForm
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
    private
    String
        permitNumber;
    private
    String
        type =
        "default";
    private
    String
        permitPeriodName;
    private
    Integer
        permitPeriodId;
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

    public Integer getClientId()
    {
        return clientId;
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

    public String getPermitNumber()
    {
        return permitNumber;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
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

    public void setPermitNumber(String permitNumber)
    {
        this.permitNumber =
            permitNumber;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type =
            type;
    }

    public String getPermitPeriodName()
    {
        return permitPeriodName;
    }

    public void setPermitPeriodName(String permitPeriodName)
    {
        this.permitPeriodName =
            permitPeriodName;
    }

    public Integer getPermitPeriodId()
    {
        return permitPeriodId;
    }

    public void setPermitPeriodId(Integer permitPeriodId)
    {
        this.permitPeriodId =
            permitPeriodId;
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
        this.clientId =
            null;
        this.name =
            null;
        this.description =
            null;
        this.permitNumber =
            null;
        this.type =
            "default";
        this.permitPeriodName =
            null;
        this.permitPeriodId =
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
