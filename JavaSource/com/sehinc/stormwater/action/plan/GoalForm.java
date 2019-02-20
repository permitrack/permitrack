package com.sehinc.stormwater.action.plan;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoalForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalForm.class);

    private
    Integer
        id;
    private
    Integer
        bmpId;
    private
    String
        bmpIdentifier;
    private
    Integer
        mcmNumber;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        ownerId;
    private
    Integer
        number;
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
    private
    boolean
        isNotify;
    private
    int
        notifyDaysInAdvance;
    private
    String
        ownerName;
    private
    List
        goalPermitTimePeriods;
    private
    Date
        goalDate;

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

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public String getOwner()
    {
        return ownerName;
    }

    public String getGoalActivityForm()
    {
        return goalActivityForm;
    }

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public String getGoalActivityFrequency()
    {
        return goalActivityFrequency;
    }

    public Integer getGoalActivityFrequencyId()
    {
        return goalActivityFrequencyId;
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

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
    }

    public void setOwner(String ownerName)
    {
        this.ownerName =
            ownerName;
    }

    public List getGoalPermitTimePeriods()
    {
        if (goalPermitTimePeriods
            == null)
        {
            return new ArrayList();
        }
        return goalPermitTimePeriods;
    }

    public void setGoalPermitTimePeriods(List goalPermitTimePeriods)
    {
        this.goalPermitTimePeriods =
            goalPermitTimePeriods;
    }

    public int getGoalPermitTimePeriodCount()
    {
        if (goalPermitTimePeriods
            == null)
        {
            return 0;
        }
        return goalPermitTimePeriods.size();
    }

    public boolean isNotify()
    {
        return isNotify;
    }

    public void setNotify(boolean isNotify)
    {
        this.isNotify =
            isNotify;
    }

    public String getNotifyString()
    {
        if (isNotify)
        {
            return "Yes";
        }
        return "No";
    }

    public void setNotifyString(String value)
    {
        if (value.equals("Yes"))
        {
            this.isNotify =
                true;
        }
        else
        {
            this.isNotify =
                false;
        }
    }

    public int getNotifyDaysInAdvance()
    {
        return notifyDaysInAdvance;
    }

    public void setNotifyDaysInAdvance(int notifyDaysInAdvance)
    {
        this.notifyDaysInAdvance =
            notifyDaysInAdvance;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public String getBmpIdentifier()
    {
        return bmpIdentifier;
    }

    public void setBmpIdentifier(String bmpIdentifier)
    {
        this.bmpIdentifier =
            bmpIdentifier;
    }

    public Integer getMcmNumber()
    {
        return mcmNumber;
    }

    public void setMcmNumber(Integer mcmNumber)
    {
        this.mcmNumber =
            mcmNumber;
    }

    public void setGoalDate(Date goalDate)
    {
        this.goalDate =
            goalDate;
    }

    public void setGoalDateString(String goalDate)
    {
        this.goalDate =
            DateUtil.parseDate(goalDate);
    }

    public Date getGoalDate()
    {
        return goalDate;
    }

    public String getGoalDateString()
    {
        return DateUtil.mdyDate(goalDate);
    }

    public String getGoalDateMMDYString()
    {
        return DateUtil.mmdyDate(goalDate);
    }

    public String getGoalActivityFrequencyDateType()
    {
        return GoalActivityFrequencyData.DATE
            .getId()
            .toString();
    }

    public void reset()
    {
        this.id =
            null;
        this.bmpId =
            null;
        this.bmpIdentifier =
            null;
        this.mcmNumber =
            null;
        this.name =
            null;
        this.description =
            null;
        this.ownerId =
            null;
        this.number =
            null;
        this.goalActivityForm =
            null;
        this.goalActivityFormId =
            null;
        this.goalActivityFrequency =
            null;
        this.goalActivityFrequencyId =
            null;
        this.isNotify =
            false;
        this.notifyDaysInAdvance =
            0;
        this.ownerName =
            null;
        this.goalPermitTimePeriods =
            null;
        this.goalDate =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (ownerId
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "Owner"));
        }
    }
}
