package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public abstract class DataAccess
    extends AbstractData
{
    private static
    Logger
        LOG =
        Logger.getLogger(DataAccess.class);

    public DataAccess()
    {
    }

    public int delete()
    {
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(QueryGenerator.getDeleteQuery(this));
        QueryGenerator.setStatementParameters(this,
                                              this.getKeyColumnList(),
                                              statement);
        return SQLHelper.deleteValue(statement);
    }

    public int deleteAllForColumn(ColumnData column)
    {
        List
            deleteColumns =
            new ArrayList();
        deleteColumns.add(column);
        return deleteAllForColumns(deleteColumns);
    }

    public int deleteAllForColumns(List columns)
    {
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(QueryGenerator.getDeleteByColumnsQuery(this,
                                                                                  columns));
        QueryGenerator.setStatementParameters(this,
                                              columns,
                                              statement);
        return SQLHelper.deleteValues(statement);
    }

    public int insert()
    {
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(QueryGenerator.getInsertQuery(this));
        QueryGenerator.setStatementParameters(this,
                                              this.getUpdateColumns(),
                                              statement);
        int
            id =
            SQLHelper.insertValue(statement);
        if (id
            > 0
            && keyColumns.size()
               == 1)
        {
            ColumnData
                column =
                (ColumnData) keyColumns.get(0);
            if (column instanceof IntColumn)
            {
                set(column,
                    id);
            }
        }
        return id;
    }

    public void update()
    {
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(QueryGenerator.getUpdateQuery(this));
        QueryGenerator.setStatementParameters(this,
                                              this.getUpdateColumns(),
                                              this.getKeys(),
                                              statement);
        SQLHelper.updateValue(statement);
    }

    public List getUpdateColumns()
    {
        ArrayList
            updateList =
            new ArrayList();
        Iterator
            i =
            changedColumns.iterator();
        while (i.hasNext())
        {
            Object
                column =
                i.next();
            if (!excludeFromInsert.contains(column))
            {
                updateList.add(column);
            }
        }
        return updateList;
    }

    public int save()
    {
        int
            updateNumber =
            0;
        if (hasChanged)
        {
            updateNumber =
                1;
            if (isPersistent)
            {
                if (changedColumns.size()
                    != 0)
                {
                    update();
                    initChangedStatus();
                }
            }
            else
            {
                insert();
                setIsPersistent(true);
                initChangedStatus();
            }
        }
        return updateNumber;
    }
}

