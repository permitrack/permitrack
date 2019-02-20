package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BigDecimalSQLHelper
    implements SQLValueHelper
{
    public String getValueSubQuery()
    {
        return null;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        return rs.getBigDecimal(1);
    }
}
