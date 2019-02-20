package com.sehinc.common.db.sql;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public class BooleanSQLHelper
    implements SQLValueHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(BooleanSQLHelper.class);

    public BooleanSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        Boolean
            value =
            new Boolean(rs.getBoolean(1));
        return value;
    }

    public String getValueSubQuery()
    {
        String
            VALUE_SUB_QUERY =
            "'X'";
        return VALUE_SUB_QUERY;
    }
}