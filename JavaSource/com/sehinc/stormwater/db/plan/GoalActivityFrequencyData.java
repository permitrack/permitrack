package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.ReadOnlyData;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GoalActivityFrequencyData
    extends ReadOnlyData
    implements Comparable, Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(GoalActivityFrequencyData.class);
    public final static
    GoalActivityFrequencyData
        WEEKLY =
        new GoalActivityFrequencyData(0);
    public final static
    GoalActivityFrequencyData
        MONTHLY =
        new GoalActivityFrequencyData(1);
    public final static
    GoalActivityFrequencyData
        QUARTERLY =
        new GoalActivityFrequencyData(2);
    public final static
    GoalActivityFrequencyData
        ANNUALLY =
        new GoalActivityFrequencyData(3);
    public final static
    GoalActivityFrequencyData
        DATE =
        new GoalActivityFrequencyData(4);
    private static final
    Map
        INSTANCES =
        new HashMap();

    static
    {
        INSTANCES.put(WEEKLY.getId(),
                      WEEKLY);
        INSTANCES.put(MONTHLY.getId(),
                      MONTHLY);
        INSTANCES.put(QUARTERLY.getId(),
                      QUARTERLY);
        INSTANCES.put(ANNUALLY.getId(),
                      ANNUALLY);
        INSTANCES.put(DATE.getId(),
                      DATE);
    }

    private
    String
        name;
    private
    String
        description;
    private
    int
        days;
    private
    int
        sequence;

    public GoalActivityFrequencyData()
    {
    }

    public GoalActivityFrequencyData(Integer id)
    {
        setId(new Integer(id));
        load();
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

    public int getDays()
    {
        return days;
    }

    public void setDays(int days)
    {
        this.days =
            days;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence =
            sequence;
    }

    public int compareTo(Object obj)
    {
        int
            numberCompare;
        GoalActivityFrequencyData
            goalActivityFrequencyData;
        if (obj instanceof GoalActivityFrequencyData)
        {
            goalActivityFrequencyData =
                (GoalActivityFrequencyData) obj;
            numberCompare =
                (new Integer(getSequence())).compareTo(new Integer(goalActivityFrequencyData.getSequence()));
        }
        else
        {
            return 0;
        }
        if (numberCompare
            == 0)
        {
            return getName().compareTo(goalActivityFrequencyData.getName());
        }
        return numberCompare;
    }

    public static GoalActivityFrequencyData getInstance(Integer id)
    {
        return (GoalActivityFrequencyData) INSTANCES.get(id);
    }

    public static Collection getAll()
    {
        return INSTANCES.values();
    }

    public String toString()
    {
        return "\nid="
               + getId()
               + "\nname="
               + getName()
               + "\ndescription="
               + getDescription()
               + "\ndays="
               + getDays()
               + "\nsequence="
               + getSequence();
    }
}
