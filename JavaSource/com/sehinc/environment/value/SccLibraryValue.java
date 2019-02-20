package com.sehinc.environment.value;

import com.sehinc.environment.db.scc.EnvSccInfoLibrary;

public class SccLibraryValue
{
    private
    Integer
        id;
    private
    String
        number;
    private
    String
        descriptionAndName;

    public SccLibraryValue()
    {
    }

    public SccLibraryValue(EnvSccInfoLibrary scc)
    {
        this.id =
            scc.getId();
        this.number =
            scc.getNumber();
        this.descriptionAndName =
            scc.getDescription()
            + " "
            + scc.getMajIndustrialGroup()
            + " "
            + scc.getRawMaterial()
            + " "
            + scc.getEmittingProcess();
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

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
    }

    public String getDescriptionAndName()
    {
        return descriptionAndName;
    }

    public void setDescriptionAndName(String descriptionAndName)
    {
        this.descriptionAndName =
            descriptionAndName;
    }

    public boolean equals(Object o)
    {
        if (o instanceof SccLibraryValue)
        {
            SccLibraryValue
                other =
                (SccLibraryValue) o;
            return other.getNumber()
                .equals(number);
        }
        return false;
    }
}
