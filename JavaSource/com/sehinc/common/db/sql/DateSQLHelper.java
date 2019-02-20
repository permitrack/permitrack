package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DateSQLHelper
    implements SQLValueHelper
{
    private static
    DateSQLHelper
        instance =
        new DateSQLHelper();

    private DateSQLHelper()
    {
    }

    public static DateSQLHelper getInstance()
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
        return rs.getTimestamp(1);
    }
}
