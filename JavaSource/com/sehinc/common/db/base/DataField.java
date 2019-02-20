package com.sehinc.common.db.base;

import java.io.Serializable;

public abstract class DataField
    extends Object
    implements Serializable
{
    public
    String
        columnName;
    public
    Object
        sqlObject;

    public DataField()
    {
    }

    public void setColumnName(String newName)
    {
        columnName =
            newName;
    }

    public abstract void setSqlObject(java.math.BigDecimal dec);

    public abstract void setSqlObject(String obj);

    public abstract void setSqlObject(Object obj);

    public String getColumnName()
    {
        if (columnName
            == null)
        {
            return new String();
        }
        return columnName;
    }

    public abstract String asSqlString();

    public abstract Object getSqlObject();

    public abstract String getString();

    public String toString()
    {
        return getColumnName()
               + ":"
               + getString();
    }
}

