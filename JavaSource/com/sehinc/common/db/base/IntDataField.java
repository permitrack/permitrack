package com.sehinc.common.db.base;

public class IntDataField
    extends DataField
{
    public IntDataField(String columnName)
    {
        setColumnName(columnName);
    }

    public IntDataField(String columnName, int value)
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
            if (obj.getClass()
                == String.class)
            {
                sqlObject =
                    new Integer((String) obj);
            }
            else
            {
                sqlObject =
                    obj;
            }
        }
    }

    public void setSqlObject(int i)
    {
        sqlObject =
            new Integer(i);
    }

    public Object getSqlObject()
    {
        return sqlObject;
    }

    public int getInt()
    {
        if (getSqlObject()
            == null)
        {
            return 0;
        }
        else
        {
            return ((Integer) getSqlObject()).intValue();
        }
    }

    public String getString()
    {
        return getSqlObject().toString();
    }

    public String asSqlString()
    {
        if (getSqlObject()
            == null)
        {
            return "NULL";
        }
        else
        {
            return (getSqlObject()).toString();
        }
    }
}

