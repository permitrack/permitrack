package com.sehinc.common.db.base;

public class StringDataField
    extends DataField
    implements FieldInterface
{
    public StringDataField(String columnName)
    {
        setColumnName(columnName);
    }

    public StringDataField(String columnName, String value)
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
        sqlObject =
            obj;
    }

    ;

    public void setSqlObject(Object obj)
    {
        sqlObject =
            obj;
    }

    public Object getSqlObject()
    {
        if (sqlObject
            == null)
        {
            return "";
        }
        return sqlObject;
    }

    public String asSqlString()
    {
        if (sqlObject
            == null)
        {
            return "NULL";
        }
        StringBuffer
            retBuffer =
            new StringBuffer();
        StringBuffer
            tempBuffer =
            new StringBuffer();
        String
            tempString =
            (String) getSqlObject();
        int
            fromIdx =
            0;
        while (tempString.indexOf("'",
                                  fromIdx)
               != -1)
        {
            int
                idx =
                tempString.indexOf("'",
                                   fromIdx);
            tempBuffer.append(tempString.substring(fromIdx,
                                                   idx
                                                   + 1));
            tempBuffer.append("'");
            fromIdx =
                idx
                + 1;
        }
        tempBuffer.append(tempString.substring(fromIdx,
                                               tempString.length()));
        retBuffer.append("'");
        retBuffer.append(tempBuffer);
        retBuffer.append("'");
        return retBuffer.toString();
    }

    public String getString()
    {
        StringBuffer
            retBuffer =
            new StringBuffer();
        retBuffer.append((String) getSqlObject());
        return retBuffer.toString();
    }
}

