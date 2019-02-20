package com.sehinc.stormwater.action.template;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class MCMTemplateForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    Integer
        planId;
    private
    String
        name;
    private
    Integer
        number;
    private
    String
        requiredDescription;
    private
    String
        necessaryDescription;

    public Integer getId()
    {
        return id;
    }

    public Integer getPlanId()
    {
        return planId;
    }

    public String getName()
    {
        return viewString(name);
    }

    public String getRequiredDescription()
    {
        return requiredDescription;
    }

    public String getViewRequiredDescription()
    {
        return viewString(requiredDescription);
    }

    public String getNecessaryDescription()
    {
        return necessaryDescription;
    }

    public String getViewNecessaryDescription()
    {
        return viewString(necessaryDescription);
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public void setRequiredDescription(String requiredDescription)
    {
        this.requiredDescription =
            requiredDescription;
    }

    public void setNecessaryDescription(String necessaryDescription)
    {
        this.necessaryDescription =
            necessaryDescription;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public void reset()
    {
        this.id =
            null;
        this.planId =
            null;
        this.name =
            null;
        this.number =
            null;
        this.requiredDescription =
            null;
        this.necessaryDescription =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "MCM Name"));
        }
    }
}
