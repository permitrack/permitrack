package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.DataAccessException;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class QueryGenerator
{
    private static
    Logger
        LOG =
        Logger.getLogger(QueryGenerator.class);

    private QueryGenerator()
    {
    }

    public static String getSelectByPrimaryKey(AbstractData data)
    {
        return getSelectByColumnsQuery(data,
                                       data.getKeyColumnList());
    }

    public static StringBuffer getSelectQueryWithoutWhere(AbstractData data)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("SELECT ");
        appendColumnNames(buffer,
                          data.getFieldList());
        buffer.append(" FROM ");
        appendTableNames(buffer,
                         data);
        return buffer;
    }

    public static String getSelectByColumnsQuery(AbstractData data, List keyList)
    {
        StringBuffer
            buffer =
            getSelectQueryWithoutWhere(data);
        if (keyList
            != null
            && keyList.size()
               > 0)
        {
            buffer.append(" WHERE ");
            appendColumnIdentities(buffer,
                                   keyList,
                                   true);
        }
        return buffer.toString();
    }

    public static void setStatementParameters(AbstractData data, List parameters, SQLHelperPreparedStatement statement)
    {
        setStatementParameters(data,
                               parameters,
                               null,
                               statement);
    }

    public static void setStatementParameters(AbstractData data, List parameters, List parameters2, SQLHelperPreparedStatement statement)
    {
        int
            i =
            0;
        int
            totalSize =
            parameters.size();
        if (parameters2
            != null)
        {
            totalSize +=
                parameters2.size();
        }
        while (i
               < totalSize)
        {
            Object
                field;
            if (i
                >= parameters.size())
            {
                field =
                    parameters2.get(i
                                    - parameters.size());
            }
            else
            {
                field =
                    parameters.get(i);
            }
            if (field instanceof ColumnData)
            {
                int
                    index =
                    ((ColumnData) field).getColumnIndex()
                    - 1;
                field =
                    data.getFieldList()
                        .get(index);
            }
            ++i;
            LOG.debug("Setting paramters for "
                      + i
                      + " to "
                      + field);
            if (field instanceof IntDataField)
            {
                statement.setInt(i,
                                 ((IntDataField) field).getInt());
            }
            else if (field instanceof StringDataField)
            {
                statement.setString(i,
                                    ((StringDataField) field).getString());
            }
            else if (field instanceof DecimalDataField)
            {
                statement.setBigDecimal(i,
                                        ((DecimalDataField) field).getBigDecimal());
            }
            else if (field instanceof DateDataField)
            {
                statement.setDate(i,
                                  ((DateDataField) field).getDate());
            }
            else
            {
                throw new DataAccessException("Field of type "
                                              + field.getClass()
                    .getName()
                                              + " is unknown.");
            }
        }
    }

    public static String getUpdateQuery(DataAccess data)
    {
        return getUpdateByColumnsQuery(data,
                                       data.getKeyColumnList());
    }

    public static String getUpdateByColumnsQuery(DataAccess data, List whereList)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("UPDATE ");
        appendTableNames(buffer,
                         data);
        buffer.append(" SET ");
        appendColumnIdentities(buffer,
                               data.getUpdateColumns(),
                               false);
        buffer.append(" WHERE ");
        appendColumnIdentities(buffer,
                               whereList,
                               true);
        return buffer.toString();
    }

    public static String getInsertQuery(DataAccess data)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("INSERT INTO ");
        appendTableNames(buffer,
                         data);
        buffer.append(" (");
        List
            columns =
            data.getUpdateColumns();
        appendColumnNames(buffer,
                          columns);
        buffer.append(") VALUES (");
        for (
            int
                i =
                0; i
                   < columns.size(); i++)
        {
            if (i
                > 0)
            {
                buffer.append(',');
            }
            buffer.append('?');
        }
        buffer.append(')');
        return buffer.toString();
    }

    public static String getDeleteQuery(DataAccess data)
    {
        return getDeleteByColumnsQuery(data,
                                       data.getKeyColumnList());
    }

    public static String getDeleteByColumnsQuery(DataAccess data, List whereList)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("DELETE FROM ");
        appendTableNames(buffer,
                         data);
        buffer.append(" WHERE ");
        appendColumnIdentities(buffer,
                               whereList,
                               true);
        return buffer.toString();
    }

    private static void appendTableNames(StringBuffer buffer, AbstractData data)
    {
        Iterator
            tables =
            data.getTableNameList()
                .iterator();
        while (tables.hasNext())
        {
            buffer.append((String) tables.next());
            if (tables.hasNext())
            {
                buffer.append(',');
            }
        }
    }

    private static void appendColumnNames(StringBuffer buffer, List columnList)
    {
        Iterator
            columns =
            columnList.iterator();
        while (columns.hasNext())
        {
            String
                value;
            Object
                obj =
                columns.next();
            if (obj instanceof ColumnData)
            {
                ColumnData
                    column =
                    (ColumnData) obj;
                value =
                    column.getColumnName();
            }
            else
            {
                DataField
                    field =
                    (DataField) obj;
                value =
                    field.getColumnName();
            }
            buffer.append(value);
            if (columns.hasNext())
            {
                buffer.append(',');
            }
        }
    }

    private static void appendColumnIdentities(StringBuffer buffer, List list, boolean displayAnd)
    {
        Iterator
            columns =
            list.iterator();
        while (columns.hasNext())
        {
            ColumnData
                column =
                (ColumnData) columns.next();
            buffer.append(column.getColumnName());
            buffer.append("=?");
            if (columns.hasNext())
            {
                if (displayAnd)
                {
                    buffer.append(" AND ");
                }
                else
                {
                    buffer.append(',');
                }
            }
        }
    }
}
