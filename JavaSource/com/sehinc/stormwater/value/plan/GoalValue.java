package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.GoalData;

public class GoalValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    Integer
        number;
    private
    Integer
        bmpId;

    public GoalValue()
    {
    }

    public GoalValue(Integer id, String name, Integer number)
    {
        this.id =
            id;
        this.name =
            name;
        this.number =
            number;
    }

    public GoalValue(GoalData goalData)
    {
        this.id =
            goalData.getId();
        this.name =
            goalData.getName();
        this.number =
            goalData.getNumber();
        this.bmpId =
            goalData.getBmpId();
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

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public Integer getNumber()
    {
        return number;
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
}
