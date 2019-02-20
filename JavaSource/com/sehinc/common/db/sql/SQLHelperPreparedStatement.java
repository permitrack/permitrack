package com.sehinc.common.db.sql;

import com.sehinc.common.db.base.DBConnectionPool;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLHelperPreparedStatement
    implements SQLHelperParameterizedStatement
{
    private static
    Logger
        LOG =
        Logger.getLogger(SQLHelperPreparedStatement.class);
    private
    PreparedStatement
        statement;
    private
    String
        query =
        null;
    private
    Connection
        connection =
        null;

    public SQLHelperPreparedStatement(String query)
    {
        this.query =
            query;
        try
        {
            connection =
                DBConnectionPool.getConnection();
            statement =
                connection.prepareStatement(query);
        }
        catch (Exception e)
        {
            LOG.info("Error in PreparedStatement "
                     + e.getMessage(),
                     e);
            LOG.info("Query was: "
                     + query);
        }
    }

    public ResultSet executeQuery()
        throws SQLException
    {
        return statement.executeQuery();
    }

    public String getQuery()
    {
        return query;
    }

    public void setInt(int index, int value)
    {
        try
        {
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
        try
        {
            statement.setString(index,
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

    public void setBigDecimal(int index, BigDecimal value)
    {
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.info("Setting prepared statement index "
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
                            "\nSQL Exception thrown when setting BigDecimal"
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
                            + value);
        }
    }

    private void handleException(Exception e, String message)
    {
        LOG.error(message
                  + ": "
                  + query);
        LOG.debug("Stacktrace",
                  e);
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

    public int executeInsert()
        throws SQLException
    {
        int
            rowsAffected =
            statement.executeUpdate();
        if (rowsAffected
            != 1)
        {
            throw new DataAccessException("Insert was expected to affect 1 row, "
                                          + rowsAffected
                                          + " were actually affected.");
        }
        statement.close();
        int
            id =
            0;
        PreparedStatement
            idStatement =
            null;
        try
        {
            idStatement =
                connection.prepareStatement("SELECT @@IDENTITY");
            ResultSet
                results =
                idStatement.executeQuery();
            if (results.next())
            {
                id =
                    results.getInt(1);
            }
            else
            {
                throw new DataAccessException("@@IDENTITY query had no results!");
            }
            if (results.next())
            {
                throw new SQLException("@@IDENTITY query had more than one result!");
            }
        }
        catch (SQLException e)
        {
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            close();
            throw new DataAccessException(e);
        }
        finally
        {
            if (idStatement
                != null)
            {
                try
                {
                    idStatement.close();
                }
                catch (SQLException e)
                {
                }
            }
        }
        return id;
    }

    public int executeUpdate()
        throws SQLException
    {
        return statement.executeUpdate();
    }
}
