package com.sehinc.stormwater.value.permitperiod;

import com.sehinc.common.util.DateUtil;
import com.sehinc.stormwater.db.permitperiod.GoalPermitTimePeriodData;
import com.sehinc.stormwater.db.permitperiod.PermitTimePeriodData;

import java.util.Date;

public class GoalPermitTimePeriodValue
    implements Comparable, java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        permitPeriodId;
    private
    Integer
        permitTimePeriodId;
    private
    String
        name;
    private
    boolean
        isComplete;
    private
    Date
        startDate;
    private
    Date
        endDate;

    public GoalPermitTimePeriodValue()
    {
    }

    public GoalPermitTimePeriodValue(GoalPermitTimePeriodData goalPermitTimePeriodData, PermitTimePeriodData permitTimePeriodData)
    {
        this.id =
            goalPermitTimePeriodData.getId();
        this.name =
            permitTimePeriodData.getName();
        this.permitPeriodId =
            permitTimePeriodData.getPermitPeriodId();
        this.permitTimePeriodId =
            permitTimePeriodData.getId();
        this.isComplete =
            goalPermitTimePeriodData.isComplete();
        this.startDate =
            permitTimePeriodData.getStartDate();
        this.endDate =
            permitTimePeriodData.getEndDate();
    }

    public Integer getId()
    {
        return id;
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

    public String getName()
    {
        return name;
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

    public Integer getPermitTimePeriodId()
    {
        return permitTimePeriodId;
    }

    public void setPermitTimePeriodId(Integer permitTimePeriodId)
    {
        this.permitTimePeriodId =
            permitTimePeriodId;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public boolean isComplete()
    {
        return isComplete;
    }

    public String getCompleteString()
    {
        if (isComplete)
        {
            return "Yes";
        }
        return "No";
    }

    public void setComplete(boolean isComplete)
    {
        this.isComplete =
            isComplete;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDateString(String startDate)
    {
        this.startDate =
            DateUtil.parseDate(startDate);
    }

    public String getStartDateString()
    {
        return DateUtil.mdyDate(startDate);
    }

    public void setEndDate(Date endDate)
    {
        this.endDate =
            endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDateString(String endDate)
    {
        this.endDate =
            DateUtil.parseDate(endDate);
    }

    public String getEndDateString()
    {
        return DateUtil.mdyDate(endDate);
    }

    public int compareTo(Object obj)
    {
        int
            result =
            0;
        if (obj instanceof GoalPermitTimePeriodValue)
        {
            result =
                getStartDate().compareTo(((GoalPermitTimePeriodValue) obj).getStartDate());
            if (result
                == 0)
            {
                result =
                    getEndDate().compareTo(((GoalPermitTimePeriodValue) obj).getEndDate());
            }
        }
        return result;
    }
}