package com.sehinc.stormwater.db.bmpdb;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class BMPDBGoalData
    extends UserUpdatableData
{
    private
    Integer
        bmpDBId;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        goalActivityFormId;
    private
    Integer
        goalActivityFrequencyId;
    private
    Integer
        number;

    public BMPDBGoalData()
    {
    }

    public Integer getBmpDBId()
    {
        return bmpDBId;
    }

    public void setBmpDBId(Integer bmpDBId)
    {
        this.bmpDBId =
            bmpDBId;
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

    public Integer getGoalActivityFormId()
    {
        return goalActivityFormId;
    }

    public void setGoalActivityFormId(Integer goalActivityFormId)
    {
        this.goalActivityFormId =
            goalActivityFormId;
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

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public static List findByBmpDBId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from BMPDBGoalData as data where data.bmpDBId =? ";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
