package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordExistsSQLHelper
    implements SQLValueHelper
{
    public RecordExistsSQLHelper()
    {
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        String
            value =
            new String(rs.getString(1));
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