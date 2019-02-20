package com.sehinc.common.db.sql;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings(value = {
    "serial",
    "unused"})
public class CountSQLHelper
    implements SQLValueHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(CountSQLHelper.class);

    public CountSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        Integer
            count =
            new Integer(rs.getInt(1));
        return count;
    }

    public String getValueSubQuery()
    {
        String
            VALUE_SUB_QUERY =
            "COUNT(*)";
        return VALUE_SUB_QUERY;
    }
}