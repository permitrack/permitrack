package com.sehinc.common.value.user;

import com.sehinc.common.db.user.CapState;

public class StateValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        code;
    private
    String
        name;
    private
    String
        countryCode;
    private
    String
        countryName;

    public StateValue()
    {
    }

    public StateValue(CapState state)
    {
        this.id =
            state.getId();
        this.code =
            state.getCode();
        this.name =
            state.getName();
        this.countryCode =
            state.getCountry()
                .getCode();
        this.countryName =
            state.getCountry()
                .getName();
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

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getCode()
    {
        return code;
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

    public void setCountryCode(String countryCode)
    {
        this.countryCode =
            countryCode;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryName(String countryName)
    {
        this.countryName =
            countryName;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public boolean equals(Object o)
    {
        if (o instanceof StateValue)
        {
            StateValue
                other =
                (StateValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
