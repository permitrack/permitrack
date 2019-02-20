package com.sehinc.common.value;

public class KeyValue
{
    private
    String
        primaryKey;
    private
    String
        value;

    public String getPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey)
    {
        this.primaryKey =
            primaryKey;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value =
            value;
    }

    public String getID()
    {
        return getPrimaryKey();
    }

    public String getName()
    {
        return getValue();
    }
}
