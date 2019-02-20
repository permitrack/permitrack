package com.sehinc.common.db.base;

public class DateColumn
    extends ColumnData
{
    public DateColumn(int index, String name)
    {
        setColumnIndex(index);
        setColumnName(name);
    }

    public DataField getFieldInstance(String name)
    {
        return new DateDataField(name);
    }
}
