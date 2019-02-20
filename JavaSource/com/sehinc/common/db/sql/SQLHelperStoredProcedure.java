package com.sehinc.common.db.sql;

import com.sehinc.common.db.base.DBConnectionPool;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLHelperStoredProcedure
    implements SQLHelperParameterizedStatement
{
    private final static
    int
        MAX_RESULTS =
        10;
    static
    Logger
        LOG =
        Logger.getLogger(SQLHelperStoredProcedure.class);
    private
    CallableStatement
        statement;
    private
    String
        query =
        null;
    private
    Connection
        connection =
        null;

    public SQLHelperStoredProcedure(String query)
    {
        this.query =
            query;
        try
        {
            LOG.debug("SQLHelperQuery: "
                      + query);
            connection =
                DBConnectionPool.getConnection();
            statement =
                connection.prepareCall(query);
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when creating stored procedure");
        }
    }

    public ResultSet executeQuery()
        throws SQLException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.info("Executing stored procedure:\n"
                     + query);
        }
        boolean
            hasResults =
            statement.execute();
        if (!hasResults)
        {
            int
                iterations;
            for (
                iterations =
                    0; iterations
                       < MAX_RESULTS; ++iterations)
            {
                hasResults =
                    statement.getMoreResults();
                if (hasResults)
                {
                    break;
                }
            }
            LOG.warn("Stored procedure required "
                     + (iterations
                        - 1)
                     + " iteration(s) to obtain a result set: "
                     + query);
        }
        if (hasResults)
        {
            return statement.getResultSet();
        }
        else
        {
            return null;
        }
    }

    public String getQuery()
    {
        return query;
    }

    public void setInt(int index, int value)
    {
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.info("Setting stored procedure index "
                         + index
                         + " to int value "
                         + value
                         + ".");
            }
            statement.setInt(index,
                             value);
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when setting int"
                            + " parameter of index "
                            + index
                            + " to value "
                            + value);
        }
    }

    public void setString(int index, String value)
    {
        String
            spValue =
            value.trim();
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.info("Setting stored procedure index "
                         + index
                         + " to String value \""
                         + spValue
                         + "\".");
            }
            statement.setString(index,
                                spValue);
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when setting String"
                            + " parameter of index "
                            + index
                            + " to value "
                            + spValue);
        }
    }

    public void setBigDecimal(int index, BigDecimal value)
    {
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.info("Setting stored procedure index "
                         + index
                         + " to BigDecimal value "
                         + value
                         + ".");
            }
            statement.setBigDecimal(index,
                                    value);
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when setting String"
                            + " parameter of index "
                            + index
                            + " to value "
                            + value);
        }
    }

    public void setDate(int index, Date value)
    {
        try
        {
            String
                stringValue =
                null;
            if (value
                != null)
            {
                stringValue =
                    SQL_DATE_FORMATTER.format(value);
            }
            if (LOG.isDebugEnabled())
            {
                LOG.info("Setting stored procedure index "
                         + index
                         + " to Date value "
                         + stringValue
                         + ".");
            }
            statement.setString(index,
                                stringValue);
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when setting Date"
                            + " parameter of index "
                            + index
                            + " to value "
                            + SQL_DATE_FORMATTER.format(value));
        }
    }

    private void handleException(Exception e, String message)
    {
        LOG.error(message
                  + ": "
                  + query);
        close();
        throw new DataAccessException(e);
    }

    public void close()
    {
        try
        {
            if (statement
                != null)
            {
                try
                {
                    statement.close();
                }
                catch (Exception e)
                {
                }
            }
        }
        finally
        {
            if (connection
                != null)
            {
                try
                {
                    connection.close();
                }
                catch (Exception e)
                {
                }
            }
        }
    }

    public void registerOutParameter(int index, int type)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.info("Registering output parameter index "
                     + index
                     + ".");
        }
        try
        {
            statement.registerOutParameter(index,
                                           type);
        }
        catch (SQLException e)
        {
            handleException(e,
                            "\nSQL Exception thrown when registering output parameter index "
                            + index
                            + ".");
        }
    }
}
