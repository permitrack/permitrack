package com.sehinc.common.db.sql;

import com.sehinc.common.value.KeyValue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KeyValueSQLHelper
    implements SQLValueHelper
{
    private static
    KeyValueSQLHelper
        instance =
        new KeyValueSQLHelper();

    private KeyValueSQLHelper()
    {
    }

    public static KeyValueSQLHelper getInstance()
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
        value.setValue(rs.getString(2));
        return value;
    }

    public String getValueSubQuery()
    {
        throw new UnsupportedOperationException();
    }
}