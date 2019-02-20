package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.SQLHelperPreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class CustomTableQuery
    extends Object
{
    public
    AbstractData
        retrievalObject;
    public
    List
        equalityColumns;
    public
    List
        likeColumns;

    public CustomTableQuery()
    {
    }

    public CustomTableQuery(AbstractData objectToRetrieve, List equalityColumns, List likeColumns)
    {
        setRetrievalObject(objectToRetrieve);
        setEqualityColumns(equalityColumns);
        setLikeColumns(likeColumns);
    }

    public void setEqualityColumns(List equalityColumns)
    {
        this.equalityColumns =
            equalityColumns;
    }

    public void setLikeColumns(List likeColumns)
    {
        this.likeColumns =
            likeColumns;
    }

    public void setRetrievalObject(AbstractData retrievalObject)
    {
        this.retrievalObject =
            retrievalObject;
    }

    public List getEqualityColumns()
    {
        return equalityColumns;
    }

    public List getLikeColumns()
    {
        return likeColumns;
    }

    public AbstractData getRetrievalObject()
    {
        return retrievalObject;
    }

    public List retrieve()
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        if (getRetrievalObject()
            == null)
        {
            throw new CustomQueryException("RETRIEVAL OBJECT IS NULL");
        }
        if ((getEqualityColumns().size()
             == 0)
            && (getLikeColumns().size()
                == 0))
        {
            throw new CustomQueryException("NO KEY COLUMNS (LIKE OR EQUALITY) HAVE BEEN SPECIFIED");
        }
        sqlBuffer.append("SELECT ");
        sqlBuffer.append(getRetrievalObject().asCommaSeparated(getRetrievalObject().getColumns(),
                                                               new ArrayList()));
        sqlBuffer.append(" FROM ");
        sqlBuffer.append(getRetrievalObject().tableNamesString());
        sqlBuffer.append(" WHERE ");
        if (getEqualityColumns().size()
            != 0)
        {
            sqlBuffer.append(buildEqualityColumns());
            if (getLikeColumns()
                != null
                && getLikeColumns().size()
                   != 0)
            {
                sqlBuffer.append(" AND ");
            }
        }
        if (getLikeColumns()
            != null
            && getLikeColumns().size()
               != 0)
        {
            sqlBuffer.append(buildLikeColumns());
        }
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(sqlBuffer.toString());
        return getRetrievalObject().retrieveMultiple(statement);
    }

    public IData createNew()
    {
        return null;
    }

    protected StringBuffer buildEqualityColumns()
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        sqlBuffer.append(getRetrievalObject().asAndEquivalents(getEqualityColumns()));
        return sqlBuffer;
    }

    protected StringBuffer buildLikeColumns()
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        sqlBuffer.append(asAndLikes(getLikeColumns(),
                                    getRetrievalObject()));
        return sqlBuffer;
    }

    protected StringBuffer asAndLikes(List columnList, IData target)
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
            sqlBuffer.append(" LIKE ");
            sqlBuffer.append(target.asSqlString((ColumnData) (columnList.get(i))));
        }
        return sqlBuffer;
    }
}

