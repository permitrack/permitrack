package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.MCMData;

public class MCMValue
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
    String
        statusCode;
    private
    Integer
        planId;

    public MCMValue()
    {
    }

    public MCMValue(Integer id, String name, Integer number)
    {
        this.id =
            id;
        this.name =
            name;
        this.number =
            number;
    }

    public MCMValue(MCMData mcmData)
    {
        this.id =
            mcmData.getId();
        this.name =
            mcmData.getName();
        this.number =
            mcmData.getNumber();
        this.planId =
            mcmData.getPlanId();
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
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

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
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
}
