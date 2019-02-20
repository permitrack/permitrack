package com.sehinc.common.db.base;

public class DecimalColumn
    extends ColumnData
{
    public DecimalColumn(int index, String name)
    {
        setColumnIndex(index);
        setColumnName(name);
    }

    public DataField getFieldInstance(String name)
    {
        return new DecimalDataField(name);
    }
}

