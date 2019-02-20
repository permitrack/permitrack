package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Date;
import java.util.List;

public class GoalData
    extends StatusData
{
    private
    Integer
        bmpId;
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
        goalActivityFormId;
    private
    boolean
        allowAttachments;
    private
    Integer
        goalActivityFrequencyId;
    private
    boolean
        notify;
    private
    int
        notifyDaysInAdvance;
    private
    Integer
        number;
    private
    Date
        goalDate;

    public GoalData()
    {
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
    }

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public void setGoalActivityFormId(Integer goalActivityFormId)
    {
        this.goalActivityFormId =
            goalActivityFormId;
    }

    public boolean isAllowAttachments()
    {
        return allowAttachments;
    }

    public void setAllowAttachments(boolean allowAttachments)
    {
        this.allowAttachments =
            allowAttachments;
    }

    public Integer getGoalActivityFrequencyId()
    {
        return goalActivityFrequencyId;
    }

    public void setGoalActivityFrequencyId(Integer goalActivityFrequencyId)
    {
        this.goalActivityFrequencyId =
            goalActivityFrequencyId;
    }

    public boolean isNotify()
    {
        return notify;
    }

    public void setNotify(boolean notify)
    {
        this.notify =
            notify;
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

    public Date getGoalDate()
    {
        return goalDate;
    }

    public void setGoalDate(Date goalDate)
    {
        this.goalDate =
            goalDate;
    }

    public String getGoalActivityFrequency()
    {
        String
            returnValue;
        GoalActivityFrequencyData
            frequency =
            GoalActivityFrequencyData.getInstance(goalActivityFrequencyId);
        if (frequency.equals(GoalActivityFrequencyData.ANNUALLY))
        {
            returnValue =
                "Annually";
        }
        else
        {
            if (frequency.equals(GoalActivityFrequencyData.MONTHLY))
            {
                returnValue =
                    "Monthly";
            }
            else
            {
                if (frequency.equals(GoalActivityFrequencyData.QUARTERLY))
                {
                    returnValue =
                        "Quarterly";
                }
                else
                {
                    if (frequency.equals(GoalActivityFrequencyData.WEEKLY))
                    {
                        returnValue =
                            "Weekly";
                    }
                    else
                    {
                        if (frequency.equals(GoalActivityFrequencyData.DATE))
                        {
                            returnValue =
                                DateUtil.mmdyDate(getGoalDate());
                        }
                        else
                        {
                            returnValue =
                                "Error returning Goal Activity Frequency";
                        }
                    }
                }
            }
        }
        return returnValue;
    }

    public static List findActiveByBmpId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from GoalData as data where data.bmpId =? and data.status.code = ? order by data.number asc, data.name asc, data.id asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
