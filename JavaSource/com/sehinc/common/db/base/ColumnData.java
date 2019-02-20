package com.sehinc.common.db.base;

public abstract class ColumnData
    implements java.io.Serializable
{
    public
    int
        columnIndex;
    public
    String
        columnName;

    public ColumnData()
    {
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public void setColumnIndex(int index)
    {
        columnIndex =
            index;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String name)
    {
        columnName =
            name;
    }

    public abstract DataField getFieldInstance(String name);
}
