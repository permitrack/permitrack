package com.sehinc.common.db.base;

import java.math.BigDecimal;

public class DecimalDataField
    extends DataField
{
    public DecimalDataField(String columnName)
    {
        setColumnName(columnName);
    }

    public void setSqlObject(BigDecimal obj)
    {
        sqlObject =
            obj;
    }

    public void setSqlObject(String num)
    {
        if (num
            != null)
        {
            sqlObject =
                new BigDecimal(num);
        }
    }

    public void setSqlObject(Object obj)
    {
        if (obj
            == null)
        {
            return;
        }
        ;
        try
        {
            sqlObject =
                new BigDecimal((String) obj);
        }
        catch (ClassCastException ex)
        {
            sqlObject =
                (BigDecimal) obj;
        }
    }

    public void setSqlObject(double i)
    {
        sqlObject =
            new BigDecimal(i);
    }

    public Object getSqlObject()
    {
        if (sqlObject
            == null)
        {
            return new BigDecimal(0f);
        }
        return (BigDecimal) sqlObject;
    }

    public int getInt()
    {
        return ((BigDecimal) getSqlObject()).intValue();
    }

    public String getString()
    {
        if (getSqlObject()
            == null)
        {
            return "0.0";
        }
        return getSqlObject().toString();
    }

    public String asSqlString()
    {
        if (getSqlObject()
            == null)
        {
            return "0.0";
        }
        return getSqlObject().toString();
    }

    public BigDecimal getBigDecimal()
    {
        return (BigDecimal) getSqlObject();
    }
}

