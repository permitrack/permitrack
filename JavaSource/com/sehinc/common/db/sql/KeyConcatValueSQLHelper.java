package com.sehinc.common.db.sql;

import com.sehinc.common.value.KeyValue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KeyConcatValueSQLHelper
    implements SQLValueHelper
{
    private static
    KeyConcatValueSQLHelper
        instance =
        new KeyConcatValueSQLHelper();

    private KeyConcatValueSQLHelper()
    {
    }

    public static KeyConcatValueSQLHelper getInstance()
    {
        return instance;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        KeyValue
            value =
            new KeyValue();
        value.setPrimaryKey(rs.getString(1));
        StringBuffer
            concatValue =
            new StringBuffer();
        concatValue.append(rs.getString(2));
        try
        {
            String
                temp =
                rs.getString(3);
            if (!temp.equals("NULL"))
            {
                concatValue.append(" ");
                concatValue.append(rs.getString(3));
            }
        }
        catch (Exception e)
        {
        }
        value.setValue(concatValue.toString());
        return value;
    }

    public String getValueSubQuery()
    {
        throw new UnsupportedOperationException();
    }
}