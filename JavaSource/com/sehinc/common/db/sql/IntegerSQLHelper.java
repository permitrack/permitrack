package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerSQLHelper
    implements SQLValueHelper
{
    private static
    IntegerSQLHelper
        instance =
        new IntegerSQLHelper();

    private IntegerSQLHelper()
    {
    }

    public static IntegerSQLHelper getInstance()
    {
        return instance;
    }

    public String getValueSubQuery()
    {
        return null;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        return new Integer(rs.getInt(1));
    }
}
