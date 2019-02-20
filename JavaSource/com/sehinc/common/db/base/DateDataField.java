package com.sehinc.common.db.base;

import java.sql.Timestamp;

public class DateDataField
    extends DataField
{
    public DateDataField(String columnName)
    {
        setColumnName(columnName);
    }

    public DateDataField(String columnName, Timestamp value)
    {
        setColumnName(columnName);
        setSqlObject(value);
    }

    public void setSqlObject(java.math.BigDecimal dec)
    {
    }

    ;

    public void setSqlObject(String obj)
    {
    }

    ;

    public void setSqlObject(Object obj)
    {
        if (obj
            == null)
        {
            sqlObject =
                obj;
        }
        else
        {
            sqlObject =
                new Timestamp(((java.util.Date) obj).getTime());
        }
    }

    public String asSqlString()
    {
        StringBuffer
            retBuffer =
            new StringBuffer();
        if (getSqlObject()
            == null)
        {
            return "NULL";
        }
        else
        {
            retBuffer.append("'");
            retBuffer.append(getSqlObject().toString());
            retBuffer.append("'");
            return (retBuffer.toString());
        }
    }

    public Object getSqlObject()
    {
        return sqlObject;
    }

    public Timestamp getDate()
    {
        return (Timestamp) sqlObject;
    }

    public String getString()
    {
        return (sqlObject
                != null)
            ? getSqlObject().toString()
            : "";
    }
}

