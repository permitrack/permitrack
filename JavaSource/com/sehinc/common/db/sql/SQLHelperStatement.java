package com.sehinc.common.db.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public interface SQLHelperStatement
{
    public
    SimpleDateFormat
        SQL_DATE_FORMATTER =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    ResultSet executeQuery()
        throws SQLException;

    String getQuery();

    void close();
}
