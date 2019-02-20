package com.sehinc.stormwater.db.permitperiod;

import com.sehinc.common.db.user.UserUpdatableData;

import java.util.Date;

public class PermitTimePeriodData
    extends UserUpdatableData
{
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

    public PermitTimePeriodData()
    {
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate =
            endDate;
    }
}
