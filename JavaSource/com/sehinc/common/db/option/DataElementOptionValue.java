package com.sehinc.common.db.option;

import com.sehinc.common.db.hibernate.HibernateData;

public class DataElementOptionValue
    extends HibernateData
{
    private
    DataElement
        dataElement;
    private
    Integer
        sequence;
    private
    String
        value;

    public DataElementOptionValue()
    {
    }

    public DataElement getDataElement()
    {
        return dataElement;
    }

    public void setDataElement(DataElement dataElement)
    {
        this.dataElement =
            dataElement;
    }

    public Integer getSequence()
    {
        return sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence =
            sequence;
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
}
