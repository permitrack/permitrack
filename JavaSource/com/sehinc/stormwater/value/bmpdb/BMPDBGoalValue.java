package com.sehinc.stormwater.value.bmpdb;

import com.sehinc.stormwater.db.bmpdb.BMPDBGoalData;

public class BMPDBGoalValue
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

    public BMPDBGoalValue(Integer id, String name, Integer number)
    {
        this.id =
            id;
        this.name =
            name;
        this.number =
            number;
    }

    public BMPDBGoalValue(BMPDBGoalData bmpDbGoalData)
    {
        this.id =
            bmpDbGoalData.getId();
        this.name =
            bmpDbGoalData.getName();
        this.number =
            bmpDbGoalData.getNumber();
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
}
