package com.sehinc.common.db.sql;

import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper
{
    private static
    Logger
        LOG =
        Logger.getLogger(SQLHelper.class);

    public static List retrieveValueListNoClose(SQLHelperStatement statement, SQLValueHelper populator)
    {
        try
        {
            List
                list =
                new ArrayList();
            ResultSet
                rs =
                statement.executeQuery();
            int
                counter =
                0;
            while (rs.next())
            {
                list.add(populator.populate(rs));
                ++counter;
            }
            rs.close();
            return list;
        }
        catch (SQLException e)
        {
            statement.close();
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            statement.close();
            throw new DataAccessException(e);
        }
    }

    public static List retrieveValueList(SQLHelperStatement statement, SQLValueHelper populator)
    {
        List
            list =
            null;
        try
        {
            list =
                retrieveValueListNoClose(statement,
                                         populator);
        }
        finally
        {
            if (statement
                != null)
            {
                statement.close();
            }
        }
        return list;
    }

    public static Object retrieveValueNoClose(SQLHelperStatement statement, SQLValueHelper populator, boolean oneRequired)
    {
        try
        {
            Object
                value =
                null;
            ResultSet
                rs =
                statement.executeQuery();
            if (rs.next())
            {
                value =
                    populator.populate(rs);
            }
            else if (oneRequired)
            {
                throw new DataAccessException("Expected 1 record, received 0.");
            }
            if (rs.next())
            {
                throw new DataAccessException("Expected 1 record, received 2 or more.");
            }
            rs.close();
            return value;
        }
        catch (SQLException e)
        {
            statement.close();
            LOG.error("SQL Exception thrown by prepared statement: "
                      + statement.getQuery(),
                      e);
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            statement.close();
            LOG.error(e.getMessage());
            throw new DataAccessException(e);
        }
    }

    public static Object retrieveValue(SQLHelperStatement statement, SQLValueHelper populator)
    {
        Object
            value =
            null;
        try
        {
            value =
                retrieveValueNoClose(statement,
                                     populator,
                                     true);
        }
        finally
        {
            statement.close();
        }
        return value;
    }

    public static Object retrieveZeroOrOneValue(SQLHelperStatement statement, SQLValueHelper populator)
    {
        Object
            value =
            null;
        try
        {
            value =
                retrieveValueNoClose(statement,
                                     populator,
                                     false);
        }
        finally
        {
            statement.close();
        }
        return value;
    }

    public static String prepareArgument(String arg)
    {
        if (arg
            != null)
        {
            StringBuffer
                buffer =
                new StringBuffer();
            buffer.append('\'');
            for (
                int
                    i =
                    0; i
                       < arg.length(); i++)
            {
                char
                    ch =
                    arg.charAt(i);
                if (ch
                    == '\'')
                {
                    buffer.append('\'');
                }
                buffer.append(ch);
            }
            buffer.append('\'');
            return buffer.toString();
        }
        else
        {
            return "null";
        }
    }

    public static int insertValue(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                insertValueNoClose(statement);
        }
        finally
        {
            statement.close();
        }
        return results;
    }

    public static int insertValueNoClose(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                statement.executeInsert();
        }
        catch (SQLException e)
        {
            statement.close();
            LOG.error("SQL Exception thrown by prepared statement: "
                      + statement.getQuery(),
                      e);
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            statement.close();
            throw new DataAccessException(e);
        }
        return results;
    }

    public static void updateValue(SQLHelperPreparedStatement statement)
    {
        try
        {
            updateValueNoClose(statement);
        }
        finally
        {
            statement.close();
        }
    }

    public static void updateValueNoClose(SQLHelperPreparedStatement statement)
    {
        int
            rowsAffected =
            updateValuesNoClose(statement);
        if (rowsAffected
            != 1)
        {
            throw new DataAccessException("One record was expected to be updated, "
                                          + rowsAffected
                                          + " records were actually updated.");
        }
    }

    public static int updateValues(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                updateValuesNoClose(statement);
        }
        finally
        {
            statement.close();
        }
        return results;
    }

    public static int updateValuesNoClose(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                statement.executeUpdate();
        }
        catch (SQLException e)
        {
            statement.close();
            LOG.error("SQL Exception thrown by prepared statement: "
                      + statement.getQuery(),
                      e);
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            statement.close();
            throw new DataAccessException(e);
        }
        return results;
    }

    public static int deleteValues(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                deleteValueNoClose(statement,
                                   false);
        }
        finally
        {
            statement.close();
        }
        return results;
    }

    public static int deleteValue(SQLHelperPreparedStatement statement)
    {
        int
            results;
        try
        {
            results =
                deleteValueNoClose(statement,
                                   true);
        }
        finally
        {
            statement.close();
        }
        return results;
    }

    public static int deleteValueNoClose(SQLHelperPreparedStatement statement, boolean requireSingle)
    {
        int
            results;
        try
        {
            results =
                statement.executeUpdate();
        }
        catch (SQLException e)
        {
            statement.close();
            LOG.error("SQL Exception thrown by prepared statement: "
                      + statement.getQuery(),
                      e);
            throw new DataAccessException(e);
        }
        catch (Exception e)
        {
            statement.close();
            throw new DataAccessException(e);
        }
        if (requireSingle
            && results
               != 1)
        {
            statement.close();
            throw new DataAccessException("One record was expected to be deleted, "
                                          + results
                                          + " records were actually deleted.");
        }
        return results;
    }
}
