package com.sehinc.stormwater.value.permitperiod;

import com.sehinc.stormwater.db.permitperiod.PermitPeriodData;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PermitPeriodValue
    implements java.io.Serializable
{
    private static final
    Logger
        LOG =
        Logger.getLogger(PermitPeriodValue.class);
    private
    Integer
        id;
    private
    String
        name;
    private
    TreeSet
        permitTimePeriods;

    public PermitPeriodValue()
    {
    }

    public PermitPeriodValue(PermitPeriodData permitPeriodData)
    {
        this.id =
            permitPeriodData.getId();
        this.name =
            permitPeriodData.getName();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public List getPermitTimePeriods()
    {
        if (permitTimePeriods
            == null)
        {
            return new ArrayList();
        }
        return new ArrayList(permitTimePeriods);
    }

    public void setPermitTimePeriods(List permitTimePeriods)
    {
        this.permitTimePeriods =
            new TreeSet(permitTimePeriods);
    }

    public PermitTimePeriodValue getPermitTimePeriod(int permitTimePeriodId)
    {
        LOG.debug("permitTimePeriods = "
                  + permitTimePeriods.size());
        LOG.debug("permitTimePeriodId = "
                  + permitTimePeriodId);
        if (permitTimePeriods
            != null)
        {
            Iterator
                iterator =
                permitTimePeriods.iterator();
            while (iterator.hasNext())
            {
                PermitTimePeriodValue
                    permitTimePeriodValue =
                    (PermitTimePeriodValue) iterator.next();
                if (permitTimePeriodValue
                    == null)
                {
                    LOG.debug("permitTimePeriodValue = null");
                }
                else
                {
                    LOG.debug("permitTimePeriodValueId = "
                              + permitTimePeriodValue.getId());
                    if (permitTimePeriodValue.getId()
                            .intValue()
                        == permitTimePeriodId)
                    {
                        LOG.debug("found a match");
                        return permitTimePeriodValue;
                    }
                }
            }
        }
        return null;
    }

    public void addPermitTimePeriod(PermitTimePeriodValue permitTimePeriodValue)
    {
        if (permitTimePeriods
            == null)
        {
            permitTimePeriods =
                new TreeSet();
        }
        permitTimePeriods.add(permitTimePeriodValue);
    }
}
