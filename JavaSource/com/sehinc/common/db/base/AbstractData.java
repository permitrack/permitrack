package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.SQLHelper;
import com.sehinc.common.db.sql.SQLHelperParameterizedStatement;
import com.sehinc.common.db.sql.SQLHelperPreparedStatement;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings(value = {
    "unused",
    "unchecked",
    "serial"})
public abstract class AbstractData
    implements IData
{
    private static
    Logger
        LOG =
        Logger.getLogger(AbstractData.class);
    protected
    ArrayList
        excludeFromInsert =
        new ArrayList();
    protected
    ArrayList
        keyColumns =
        new ArrayList();
    protected
    ArrayList
        fieldCollection;
    protected
    ArrayList
        tableNames =
        new ArrayList();
    protected
    int
        maxFields;
    protected
    boolean
        hasChanged =
        false;
    protected
    List
        changedColumns =
        new ArrayList();
    protected final
    String
        KEY_SEPERATOR =
        ".";
    protected
    boolean
        isPersistent =
        false;
    protected
    ArrayList
        columns;

    public AbstractData()
    {
    }

    public List getTableNames()
    {
        return tableNames;
    }

    public List getKeys()
    {
        return keyColumns;
    }

    public List getColumns()
    {
        return columns;
    }

    public Object get(ColumnData column)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            return field.getSqlObject();
        }
    }

    public int getInt(ColumnData column)
    {
        IntDataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            return 0;
        }
        else
        {
            field =
                (IntDataField) fieldCollection.get(idx);
            return field.getInt();
        }
    }

    public BigDecimal getBigDecimal(ColumnData column)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            return new BigDecimal(0f);
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            return (BigDecimal) field.getSqlObject();
        }
    }

    public Date getDate(ColumnData column)
    {
        DateDataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            return null;
        }
        else
        {
            Date
                date =
                null;
            field =
                (DateDataField) fieldCollection.get(idx);
            java.sql.Timestamp
                sqlDate =
                field.getDate();
            if (sqlDate
                != null)
            {
                date =
                    new Date(sqlDate.getTime());
            }
            return date;
        }
    }

    public String getString(ColumnData column)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            return null;
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            String
                returnString =
                field.getString();
            if (returnString
                == null)
            {
                return null;
            }
            return returnString.trim();
        }
    }

    public String asSqlString(ColumnData column)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            return "NULL";
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            return field.asSqlString();
        }
    }

    public void initFieldCollection()
    {
        maxFields =
            columns.size();
        fieldCollection =
            new ArrayList();
        ListIterator
            myenum =
            columns.listIterator();
        while (myenum.hasNext())
        {
            ColumnData
                column =
                (ColumnData) myenum.next();
            fieldCollection.add(column.getFieldInstance(column.getColumnName()));
        }
    }

    public boolean retrieveSingleByColumn(ColumnData column)
    {
        ArrayList
            list =
            new ArrayList();
        list.add(column);
        return retrieveSingleByColumns(list);
    }

    public boolean retrieveSingleByColumnAlternate(ColumnData column)
    {
        ArrayList
            list =
            new ArrayList(1);
        list.add(column);
        return retrieveSingleByColumns(list,
                                       false);
    }

    public boolean retrieveByPrimaryKeys()
    {
        return retrieveSingleByColumns(getKeys());
    }

    public boolean retrieveByPrimaryKeysAlternate()
    {
        return retrieveSingleByColumns(getKeys(),
                                       false);
    }

    public boolean retrieveSingleByColumns(List retrieveColumns)
    {
        return this.retrieveSingleByColumns(retrieveColumns,
                                            true);
    }

    public boolean retrieveSingleByColumnsAlternate(List columns)
    {
        return retrieveSingleByColumns(columns,
                                       false);
    }

    private boolean retrieveSingleByColumns(List retrieveColumns, boolean requireResultsFlag)
    {
        CustomSingleSQLValueHelper
            helper =
            new CustomSingleSQLValueHelper(this);
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(helper.getSelectQuery(retrieveColumns));
        QueryGenerator.setStatementParameters(this,
                                              retrieveColumns,
                                              statement);
        if (requireResultsFlag)
        {
            SQLHelper.retrieveValue(statement,
                                    helper);
            return true;
        }
        else
        {
            Object
                value =
                SQLHelper.retrieveZeroOrOneValue(statement,
                                                 helper);
            return (value
                    != null);
        }
    }

    public List retrieveMultipleByColumn(ColumnData column)
    {
        ArrayList
            list =
            new ArrayList();
        list.add(column);
        return retrieveMultipleByColumns(list);
    }

    public List retrieveMultipleByColumns(List retrieveColumns)
    {
        CustomMultipleSQLValueHelper
            helper =
            new CustomMultipleSQLValueHelper(this);
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(helper.getSelectQuery(retrieveColumns));
        QueryGenerator.setStatementParameters(this,
                                              retrieveColumns,
                                              statement);
        return SQLHelper.retrieveValueList(statement,
                                           helper);
    }

    public StringBuffer tableNamesString()
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        for (
            int
                i =
                0; i
                   < tableNames.size(); i++)
        {
            if (i
                != 0)
            {
                sqlBuffer.append(", ");
            }
            sqlBuffer.append(tableNames.get(i));
        }
        return sqlBuffer;
    }

    protected StringBuffer asColumnNameString(ListIterator list)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        int
            i =
            0;
        while (list.hasNext())
        {
            if (i
                != 0)
            {
                sqlBuffer.append(", ");
            }
            i +=
                1;
            ColumnData
                elem =
                (ColumnData) list.next();
            sqlBuffer.append(elem.getColumnName());
            sqlBuffer.append(' ');
        }
        return sqlBuffer;
    }

    protected StringBuffer asEquivalents(List columnList, List exclude)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        int
            c =
            0;
        for (
            int
                i =
                0; i
                   < columnList.size(); i++)
        {
            if (!exclude.contains(columnList.get(i)))
            {
                if (c
                    != 0)
                {
                    sqlBuffer.append(" , ");
                }
                c++;
                sqlBuffer.append(((ColumnData) (columnList.get(i))).getColumnName());
                sqlBuffer.append(" = ");
                sqlBuffer.append(asSqlString((ColumnData) (columnList.get(i))));
            }
        }
        return sqlBuffer;
    }

    protected StringBuffer asCommaSeparated(List columnList, List exclude)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        int
            c =
            0;
        for (
            int
                i =
                0; i
                   < columnList.size(); i++)
        {
            if (!exclude.contains(columnList.get(i)))
            {
                if (c
                    != 0)
                {
                    sqlBuffer.append(" , ");
                }
                c++;
                sqlBuffer.append(((ColumnData) (columnList.get(i))).getColumnName());
            }
        }
        return sqlBuffer;
    }

    protected StringBuffer asCommaSeparatedValues(List columnList, List exclude)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        int
            c =
            0;
        for (
            int
                i =
                0; i
                   < columnList.size(); i++)
        {
            if (!exclude.contains(columnList.get(i)))
            {
                if (c
                    != 0)
                {
                    sqlBuffer.append(" , ");
                }
                c++;
                sqlBuffer.append(asSqlString(((ColumnData) (columnList.get(i)))));
            }
        }
        return sqlBuffer;
    }

    protected StringBuffer asAndEquivalents(List columnList)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        for (
            int
                i =
                0; i
                   < columnList.size(); i++)
        {
            if (i
                != 0)
            {
                sqlBuffer.append(" AND ");
            }
            sqlBuffer.append(((ColumnData) (columnList.get(i))).getColumnName());
            sqlBuffer.append(" = ");
            sqlBuffer.append(asSqlString((ColumnData) (columnList.get(i))));
        }
        return sqlBuffer;
    }

    protected StringBuffer asAndEquivalents(List columnList, List excludeList)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        int
            c =
            0;
        for (
            int
                i =
                0; i
                   < columnList.size(); i++)
        {
            if (!excludeList.contains(columnList.get(i)))
            {
                if (c
                    != 0)
                {
                    sqlBuffer.append(" AND ");
                }
                c++;
                sqlBuffer.append(((ColumnData) (columnList.get(i))).getColumnName());
                sqlBuffer.append(" = ");
                sqlBuffer.append(asSqlString((ColumnData) (columnList.get(i))));
            }
        }
        return sqlBuffer;
    }

    public static String prepareString(String str)
    {
        return SQLHelper.prepareArgument(str);
    }

    protected final void setIsPersistent(boolean newFlag)
    {
        isPersistent =
            newFlag;
    }

    protected final boolean isPersistent()
    {
        return isPersistent;
    }

    protected void initChangedStatus()
    {
        hasChanged =
            false;
        changedColumns.clear();
    }

    public List getExcludeFromInsertList()
    {
        return excludeFromInsert;
    }

    public List getKeyColumnList()
    {
        return keyColumns;
    }

    public List getFieldList()
    {
        return fieldCollection;
    }

    public List getTableNameList()
    {
        return tableNames;
    }

    public boolean getHasChanged()
    {
        return hasChanged;
    }

    public List getChangedColumnSet()
    {
        return changedColumns;
    }

    public void set(ColumnData column, int fieldValue)
    {
        IntDataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (IntDataField) fieldCollection.get(idx);
            field.setSqlObject(fieldValue);
            hasChanged =
                true;
        }
        addChangedColumn(column);
    }

    public void set(ColumnData column, BigDecimal fieldValue)
    {
        DecimalDataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (DecimalDataField) fieldCollection.get(idx);
            field.setSqlObject(fieldValue);
            hasChanged =
                true;
        }
        addChangedColumn(column);
    }

    public void set(ColumnData column, Date fieldValue)
    {
        DateDataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (DateDataField) fieldCollection.get(idx);
            field.setSqlObject(fieldValue);
            hasChanged =
                true;
        }
        addChangedColumn(column);
    }

    public void set(ColumnData column, String fieldValue)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            field.setSqlObject(fieldValue);
            hasChanged =
                true;
        }
        addChangedColumn(column);
    }

    public void set(ColumnData column, Object fieldValue)
    {
        DataField
            field;
        int
            idx =
            column.getColumnIndex()
            - 1;
        if ((idx
             > maxFields)
            || (idx
                < 0))
        {
            throw new InvalidFieldIndexException();
        }
        else
        {
            field =
                (DataField) fieldCollection.get(idx);
            field.setSqlObject(fieldValue);
            hasChanged =
                true;
        }
        addChangedColumn(column);
    }

    protected void addChangedColumn(ColumnData column)
    {
        if (!changedColumns.contains(column))
        {
            changedColumns.add(column);
        }
    }

    public void reset()
    {
        this.initChangedStatus();
    }

    protected List retrieveMultiple(SQLHelperParameterizedStatement statement)
    {
        CustomMultipleSQLValueHelper
            helper =
            new CustomMultipleSQLValueHelper(this);
        return SQLHelper.retrieveValueList(statement,
                                           helper);
    }
}
