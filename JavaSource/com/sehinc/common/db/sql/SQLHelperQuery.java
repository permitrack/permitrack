package com.sehinc.common.db.sql;

import com.sehinc.common.db.base.DBConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLHelperQuery
    implements SQLHelperStatement
{
    static
    Logger
        LOG =
        Logger.getLogger(SQLHelperQuery.class);
    private
    Statement
        statement;
    private
    String
        query =
        null;
    private
    Connection
        connection =
        null;

    public SQLHelperQuery(String query)
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
                connection.createStatement();
        }
        catch (Exception e)
        {
            handleException(e,
                            "\nSQL Exception thrown when creating query");
        }
    }

    public ResultSet executeQuery()
        throws SQLException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.info("Executing query:\n"
                     + query);
        }
        return statement.executeQuery(query);
    }

    public String getQuery()
    {
        return query;
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

    public int executeInsert()
        throws SQLException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.info("Executing prepared statement insert:\n"
                     + query);
        }
        int
            rowsAffected =
            statement.executeUpdate(query);
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
        if (LOG.isDebugEnabled())
        {
            LOG.info("Executing prepared statement update:\n"
                     + query);
        }
        return statement.executeUpdate(query);
    }
}
