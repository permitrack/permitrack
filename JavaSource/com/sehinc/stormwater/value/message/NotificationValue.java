package com.sehinc.stormwater.value.message;

import com.sehinc.stormwater.db.plan.GoalActivityFrequencyData;

import java.util.Date;

public class NotificationValue
    implements java.io.Serializable
{
    private
    Integer
        planId;
    private
    String
        planName;
    private
    Integer
        permitTypeId;
    private
    Integer
        mcmId;
    private
    Integer
        mcmNumber;
    private
    Integer
        bmpId;
    private
    Integer
        bmpNumber;
    private
    String
        bmpSection;
    private
    Integer
        goalId;
    private
    Integer
        goalNumber;
    private
    String
        mcmOwnerFirstName;
    private
    String
        mcmOwnerLastName;
    private
    String
        mcmOwnerEmailAddress;
    private
    String
        bmpOwnerFirstName;
    private
    String
        bmpOwnerLastName;
    private
    String
        bmpOwnerEmailAddress;
    private
    String
        goalOwnerFirstName;
    private
    String
        goalOwnerLastName;
    private
    String
        goalOwnerEmailAddress;
    private
    String
        goalName;
    private
    Integer
        permitTimePeriodId;
    private
    String
        permitTimePeriodName;
    private
    Integer
        goalActivityCount;
    private
    Date
        goalDate;
    private
    GoalActivityFrequencyData
        goalActivityFrequency;
    private
    Integer
        notifyDaysInAdvance;

    public NotificationValue()
    {
    }

    public String getPlanName()
    {
        return planName;
    }

    public void setPlanName(String planName)
    {
        this.planName =
            planName;
    }

    public Integer getPlanId()
    {
        return planId;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
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

    public Integer getMcmId()
    {
        return mcmId;
    }

    public void setMcmId(Integer mcmId)
    {
        this.mcmId =
            mcmId;
    }

    public Integer getMcmNumber()
    {
        if (mcmNumber
            == null)
        {
            mcmNumber =
                new Integer(0);
        }
        return mcmNumber;
    }

    public void setMcmNumber(Integer mcmNumber)
    {
        this.mcmNumber =
            mcmNumber;
    }

    public Integer getBmpId()
    {
        return bmpId;
    }

    public void setBmpId(Integer bmpId)
    {
        this.bmpId =
            bmpId;
    }

    public Integer getBmpNumber()
    {
        if (bmpNumber
            == null)
        {
            bmpNumber =
                new Integer(0);
        }
        return bmpNumber;
    }

    public void setBmpNumber(Integer bmpNumber)
    {
        this.bmpNumber =
            bmpNumber;
    }

    public String getBmpSection()
    {
        return bmpSection;
    }

    public void setBmpSection(String bmpSection)
    {
        this.bmpSection =
            bmpSection;
    }

    public Integer getGoalId()
    {
        return goalId;
    }

    public void setGoalId(Integer goalId)
    {
        this.goalId =
            goalId;
    }

    public Integer getGoalNumber()
    {
        if (goalNumber
            == null)
        {
            goalNumber =
                new Integer(0);
        }
        return goalNumber;
    }

    public void setGoalNumber(Integer goalNumber)
    {
        this.goalNumber =
            goalNumber;
    }

    public String getMcmOwnerFirstName()
    {
        return mcmOwnerFirstName;
    }

    public void setMcmOwnerFirstName(String mcmOwnerFirstName)
    {
        this.mcmOwnerFirstName =
            mcmOwnerFirstName;
    }

    public String getMcmOwnerLastName()
    {
        return mcmOwnerLastName;
    }

    public void setMcmOwnerLastName(String mcmOwnerLastName)
    {
        this.mcmOwnerLastName =
            mcmOwnerLastName;
    }

    public String getMcmOwnerEmailAddress()
    {
        return mcmOwnerEmailAddress;
    }

    public void setMcmOwnerEmailAddress(String mcmOwnerEmailAddress)
    {
        this.mcmOwnerEmailAddress =
            mcmOwnerEmailAddress;
    }

    public String getBmpOwnerFirstName()
    {
        return bmpOwnerFirstName;
    }

    public void setBmpOwnerFirstName(String bmpOwnerFirstName)
    {
        this.bmpOwnerFirstName =
            bmpOwnerFirstName;
    }

    public String getBmpOwnerLastName()
    {
        return bmpOwnerLastName;
    }

    public void setBmpOwnerLastName(String bmpOwnerLastName)
    {
        this.bmpOwnerLastName =
            bmpOwnerLastName;
    }

    public String getBmpOwnerEmailAddress()
    {
        return bmpOwnerEmailAddress;
    }

    public void setBmpOwnerEmailAddress(String bmpOwnerEmailAddress)
    {
        this.bmpOwnerEmailAddress =
            bmpOwnerEmailAddress;
    }

    public String getGoalOwnerFirstName()
    {
        return goalOwnerFirstName;
    }

    public void setGoalOwnerFirstName(String goalOwnerFirstName)
    {
        this.goalOwnerFirstName =
            goalOwnerFirstName;
    }

    public String getGoalOwnerLastName()
    {
        return goalOwnerLastName;
    }

    public void setGoalOwnerLastName(String goalOwnerLastName)
    {
        this.goalOwnerLastName =
            goalOwnerLastName;
    }

    public String getGoalOwnerEmailAddress()
    {
        return goalOwnerEmailAddress;
    }

    public void setGoalOwnerEmailAddress(String goalOwnerEmailAddress)
    {
        this.goalOwnerEmailAddress =
            goalOwnerEmailAddress;
    }

    public String getOwnerFirstName()
    {
        if (goalOwnerEmailAddress
            == null)
        {
            if (bmpOwnerEmailAddress
                == null)
            {
                if (mcmOwnerEmailAddress
                    == null)
                {
                    return null;
                }
                return mcmOwnerFirstName;
            }
            return bmpOwnerFirstName;
        }
        return goalOwnerFirstName;
    }

    public String getOwnerLastName()
    {
        if (goalOwnerEmailAddress
            == null)
        {
            if (bmpOwnerEmailAddress
                == null)
            {
                if (mcmOwnerEmailAddress
                    == null)
                {
                    return null;
                }
                return mcmOwnerLastName;
            }
            return bmpOwnerLastName;
        }
        return goalOwnerLastName;
    }

    public String getOwnerEmailAddress()
    {
        if (goalOwnerEmailAddress
            == null)
        {
            if (bmpOwnerEmailAddress
                == null)
            {
                if (mcmOwnerEmailAddress
                    == null)
                {
                    return null;
                }
                return mcmOwnerEmailAddress;
            }
            return bmpOwnerEmailAddress;
        }
        return goalOwnerEmailAddress;
    }

    public String getGoalName()
    {
        return goalName;
    }

    public void setGoalName(String goalName)
    {
        this.goalName =
            goalName;
    }

    public Integer getPermitTimePeriodId()
    {
        return permitTimePeriodId;
    }

    public void setPermitTimePeriodId(Integer permitTimePeriodId)
    {
        this.permitTimePeriodId =
            permitTimePeriodId;
    }

    public String getPermitTimePeriodName()
    {
        return permitTimePeriodName;
    }

    public void setPermitTimePeriodName(String permitTimePeriodName)
    {
        this.permitTimePeriodName =
            permitTimePeriodName;
    }

    public Integer getGoalActivityCount()
    {
        if (goalActivityCount
            == null)
        {
            goalActivityCount =
                new Integer(0);
        }
        return goalActivityCount;
    }

    public void setGoalActivityCount(Integer goalActivityCount)
    {
        this.goalActivityCount =
            goalActivityCount;
    }

    public GoalActivityFrequencyData getGoalActivityFrequency()
    {
        return goalActivityFrequency;
    }

    public void setGoalActivityFrequency(Integer goalActivityFrequency)
    {
        this.goalActivityFrequency =
            GoalActivityFrequencyData.getInstance(goalActivityFrequency);
    }

    public String getGoalActivityFrequencyName()
    {
        if (goalActivityFrequency
            != null)
        {
            return goalActivityFrequency.getName();
        }
        return "";
    }

    public Integer getNotifyDaysInAdvance()
    {
        if (notifyDaysInAdvance
            == null)
        {
            notifyDaysInAdvance =
                new Integer(0);
        }
        return notifyDaysInAdvance;
    }

    public void setNotifyDaysInAdvance(Integer notifyDaysInAdvance)
    {
        this.notifyDaysInAdvance =
            notifyDaysInAdvance;
    }

    public Date getGoalDate()
    {
        return goalDate;
    }

    public void setGoalDate(Date goalDate)
    {
        this.goalDate =
            goalDate;
    }
}
