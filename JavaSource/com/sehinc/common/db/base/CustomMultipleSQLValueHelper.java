package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.DataAccessException;
import com.sehinc.common.db.sql.SQLValueHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomMultipleSQLValueHelper
    implements SQLValueHelper
{
    private
    AbstractData
        dataRoot;

    public CustomMultipleSQLValueHelper(AbstractData dataRoot)
    {
        this.dataRoot =
            dataRoot;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        AbstractData
            data;
        try
        {
            data =
                dataRoot.getClass()
                    .newInstance();
        }
        catch (Exception e)
        {
            throw new DataAccessException(e);
        }
        List
            columns =
            dataRoot.getColumns();
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
        data.setIsPersistent(true);
        return data;
    }

    public String getSelectQuery()
    {
        return QueryGenerator.getSelectByPrimaryKey(dataRoot);
    }

    public String getSelectQuery(List columns)
    {
        return QueryGenerator.getSelectByColumnsQuery(dataRoot,
                                                      columns);
    }

    public String getValueSubQuery()
    {
        throw new UnsupportedOperationException();
    }
}
