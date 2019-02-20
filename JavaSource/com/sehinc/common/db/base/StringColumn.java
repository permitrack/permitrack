package com.sehinc.common.db.base;

public class StringColumn
    extends ColumnData
{
    public StringColumn(int index, String name)
    {
        setColumnIndex(index);
        setColumnName(name);
    }

    public DataField getFieldInstance(String name)
    {
        return new StringDataField(name);
    }
}
