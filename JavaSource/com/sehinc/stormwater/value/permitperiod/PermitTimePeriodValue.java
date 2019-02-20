package com.sehinc.stormwater.value.permitperiod;

import com.sehinc.common.util.DateUtil;
import com.sehinc.stormwater.db.permitperiod.PermitTimePeriodData;

import java.util.Date;

public class PermitTimePeriodValue
    implements Comparable, java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        permitPeriodId;
    private
    String
        name;
    private
    Date
        startDate;
    private
    Date
        endDate;

    public PermitTimePeriodValue()
    {
    }

    public PermitTimePeriodValue(PermitTimePeriodData permitTimePeriodData)
    {
        this.id =
            permitTimePeriodData.getId();
        this.name =
            permitTimePeriodData.getName();
        this.permitPeriodId =
            permitTimePeriodData.getPermitPeriodId();
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

    public Integer getPermitPeriodId()
    {
        return permitPeriodId;
    }

    public void setPermitPeriodId(Integer permitPeriodId)
    {
        this.permitPeriodId =
            permitPeriodId;
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

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
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
        if (obj instanceof PermitTimePeriodValue)
        {
            result =
                getStartDate().compareTo(((PermitTimePeriodValue) obj).getStartDate());
            if (result
                == 0)
            {
                result =
                    getEndDate().compareTo(((PermitTimePeriodValue) obj).getEndDate());
                if (result
                    == 0)
                {
                    result =
                        getId().compareTo(((PermitTimePeriodValue) obj).getId());
                }
            }
        }
        return result;
    }
}