package com.sehinc.stormwater.action.template;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.stormwater.db.plan.GoalActivityFormData;
import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class GoalTemplateForm
    extends BaseValidatorForm
{

    private
    Integer
        id;
    private
    Integer
        bmpId;
    private
    String
        name;
    private
    Integer
        number;
    private
    Integer
        bmpNumber;
    private
    Integer
        mcmNumber;
    private
    String
        bmpIdentifier;
    private
    String
        description;
    private
    String
        goalActivityForm;
    private
    Integer
        goalActivityFormId;
    private
    String
        goalActivityFrequency;
    private
    Integer
        goalActivityFrequencyId;

    public Integer getId()
    {
        return id;
    }

    public Integer getBMPId()
    {
        return bmpId;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getViewDescription()
    {
        return viewString(description);
    }

    public String getGoalActivityForm()
    {
        GoalActivityFormData
            goalActivityFormData =
            new GoalActivityFormData();
        goalActivityFormData.setId(goalActivityFormId);
        goalActivityFormData.retrieveByPrimaryKeys();
        return goalActivityFormData.getName();
    }

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public String getGoalActivityFrequency()
    {
        return GoalActivityFrequencyData.getInstance(goalActivityFrequencyId)
            .getName();
    }

    public Integer getGoalActivityFrequencyId()
    {
        return goalActivityFrequencyId;
    }

    public Integer getNumber()
    {
        return number;
    }

    public Integer getBmpNumber()
    {
        return bmpNumber;
    }

    public Integer getMcmNumber()
    {
        return mcmNumber;
    }

    public String getBmpIdentifier()
    {
        return bmpIdentifier;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public void setBMPId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
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

    public void setGoalActivityForm(String goalActivityForm)
    {
        this.goalActivityForm =
            goalActivityForm;
    }

    public void setGoalActivityFormId(Integer goalActivityFormId)
    {
        this.goalActivityFormId =
            goalActivityFormId;
    }

    public void setGoalActivityFrequency(String goalActivityFrequency)
    {
        this.goalActivityFrequency =
            goalActivityFrequency;
    }

    public void setGoalActivityFrequencyId(Integer goalActivityFrequencyId)
    {
        this.goalActivityFrequencyId =
            goalActivityFrequencyId;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public void setBmpNumber(Integer bmpNumber)
    {
        this.bmpNumber =
            bmpNumber;
    }

    public void setMcmNumber(Integer mcmNumber)
    {
        this.mcmNumber =
            mcmNumber;
    }

    public void setBmpIdentifier(String bmpIdentifier)
    {
        this.bmpIdentifier =
            bmpIdentifier;
    }

    public void reset()
    {
        this.id =
            null;
        this.bmpId =
            null;
        this.name =
            null;
        this.number =
            null;
        this.bmpNumber =
            null;
        this.mcmNumber =
            null;
        this.bmpIdentifier =
            null;
        this.description =
            null;
        this.goalActivityForm =
            null;
        this.goalActivityFormId =
            null;
        this.goalActivityFrequency =
            null;
        this.goalActivityFrequencyId =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Goal Name"));
        }
    }
}
