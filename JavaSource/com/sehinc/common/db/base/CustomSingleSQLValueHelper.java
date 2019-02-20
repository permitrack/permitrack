package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.DataAccessException;
import com.sehinc.common.db.sql.SQLValueHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomSingleSQLValueHelper
    implements SQLValueHelper
{
    private
    AbstractData
        data;

    public CustomSingleSQLValueHelper(AbstractData data)
    {
        this.data =
            data;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        List
            columns =
            data.getColumns();
        int
            i =
            0;
        while (i
               < columns.size())
        {
            ColumnData
                column =
                (ColumnData) columns.get(i);
            ++i;
            try
            {
                if (column instanceof IntColumn)
                {
                    data.set(column,
                             rs.getInt(i));
                }
                else if (column instanceof StringColumn)
                {
                    data.set(column,
                             rs.getString(i));
                }
                else if (column instanceof DecimalColumn)
                {
                    data.set(column,
                             rs.getBigDecimal(i));
                }
                else if (column instanceof DateColumn)
                {
                    data.set(column,
                             rs.getTimestamp(i));
                }
                else
                {
                    throw new DataAccessException("Column type "
                                                  + column.getClass()
                        .getName()
                                                  + " is unknown.");
                }
            }
            catch (ClassCastException e)
            {
                throw new DataAccessException("Column "
                                              + i
                                              + " ("
                                              + column.getColumnName()
                                              + ") is of the incorrect type (result set value is a "
                                              + e.getMessage()
                                              + ".)");
            }
        }
        data.setIsPersistent(true);
        data.initChangedStatus();
        return data;
    }

    public String getSelectQuery()
    {
        return QueryGenerator.getSelectByPrimaryKey(data);
    }

    public String getSelectQuery(List columns)
    {
        return QueryGenerator.getSelectByColumnsQuery(data,
                                                      columns);
    }

    public String getValueSubQuery()
    {
        throw new UnsupportedOperationException();
    }
}
