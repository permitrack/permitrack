package com.sehinc.common.db.base;

public class IntColumn
    extends ColumnData
{
    public IntColumn(int index, String name)
    {
        setColumnIndex(index);
        setColumnName(name);
    }

    public DataField getFieldInstance(String name)
    {
        return new IntDataField(name);
    }
}
