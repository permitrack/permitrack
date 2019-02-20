package com.sehinc.stormwater.db.permitperiod;

import com.sehinc.common.db.user.StatusData;

public class PermitPeriodData
    extends StatusData
{
    private
    String
        name;

    public PermitPeriodData()
    {
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
}
