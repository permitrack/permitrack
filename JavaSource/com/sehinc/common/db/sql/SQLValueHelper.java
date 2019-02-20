package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLValueHelper
{
    public Object populate(ResultSet rs)
        throws SQLException;

    public String getValueSubQuery();
}
