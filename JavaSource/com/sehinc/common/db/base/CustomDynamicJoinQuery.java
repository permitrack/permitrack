package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.SQLHelperPreparedStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomDynamicJoinQuery
    extends CustomJoinQuery
{
    List
        joinObjects;
    List
        retrievalColumns;
    List
        joinOperators;
    List
        joinColumns;
    List
        identityColumns;
    List
        identityOperators;
    List
        identityValues;
    List
        likeValues;
    List
        likeColumns;

    public CustomDynamicJoinQuery(AbstractData retrievalObject, List joinObjects, List retrievalColumns, List joinColumns, List likeColumns, List likeValues, List identityColumns, List identityValues, ColumnData orderByColumn)
    {
        setRetrievalObject(retrievalObject);
        setJoinObjects(joinObjects);
        setRetrievalColumns(retrievalColumns);
        setJoinColumns(joinColumns);
        setLikeValues(likeValues);
        setLikeColumns(likeColumns);
        setIdentityColumns(identityColumns);
        setIdentityValues(identityValues);
        setOrderByColumn(orderByColumn);
    }

    public CustomDynamicJoinQuery(AbstractData retrievalObject, List joinObjects, List retrievalColumns, List joinOperators, List joinColumns, List likeColumns, List likeValues, List identityColumns, List identityOperators, List identityValues, ColumnData orderByColumn)
    {
        this(retrievalObject,
             joinObjects,
             retrievalColumns,
             joinColumns,
             likeColumns,
             likeValues,
             identityColumns,
             identityValues,
             orderByColumn);
        setJoinOperators(joinOperators);
        setIdentityOperators(identityOperators);
    }

    public void setIdentityColumns(List identityColumns)
    {
        this.identityColumns =
            identityColumns;
    }

    public void setIdentityValues(List identityValues)
    {
        this.identityValues =
            identityValues;
    }

    public void setRetrievalColumns(List retrievalColumns)
    {
        this.retrievalColumns =
            retrievalColumns;
    }

    public void setJoinObjects(List joinObjects)
    {
        this.joinObjects =
            joinObjects;
    }

    public void setJoinColumns(List joinColumns)
    {
        this.joinColumns =
            joinColumns;
    }

    public void setLikeValues(List likeValues)
    {
        this.likeValues =
            likeValues;
    }

    public void setLikeColumns(List likeColumns)
    {
        this.likeColumns =
            likeColumns;
    }

    public void setIdentityOperators(List identityOperators)
    {
        this.identityOperators =
            identityOperators;
    }

    public void setJoinOperators(List joinOperators)
    {
        this.joinOperators =
            joinOperators;
    }

    public List getIdentityColumns()
    {
        return identityColumns;
    }

    public List getIdentityValues()
    {
        return identityValues;
    }

    public List getRetrievalColumns()
    {
        return retrievalColumns;
    }

    public List getJoinObjects()
    {
        return joinObjects;
    }

    public List getJoinColumns()
    {
        return joinColumns;
    }

    public List getLikeValues()
    {
        return likeValues;
    }

    public List getLikeColumns()
    {
        return likeColumns;
    }

    public List getIdentityOperators()
    {
        if (identityOperators
            == null)
        {
            return new ArrayList();
        }
        return identityOperators;
    }

    public List getJoinOperators()
    {
        if (joinOperators
            == null)
        {
            return new ArrayList();
        }
        return joinOperators;
    }

    public List retrieve()
    {
        if (getJoinColumns()
            == null)
        {
            throw new CustomQueryException("JOIN COLUMNS ARE NULL");
        }
        if (getJoinObjects()
            == null)
        {
            throw new CustomQueryException("JOIN OBJECTS ARE NULL");
        }
        if (getRetrievalColumns()
            == null)
        {
            throw new CustomQueryException("RETRIEVAL COLUMNS ARE NULL");
        }
        if ((getRetrievalObject()
             == null)
            || (getJoinObjects().size()
                == 0)
            || (getRetrievalColumns().size()
                == 0)
            || (getJoinColumns().size()
                == 0))
        {
            throw new CustomQueryException("INCOMPLETE DATA FOR CUSTOM DYNAMIC JOIN ACTION");
        }
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        sqlBuffer.append("SELECT ");
        sqlBuffer.append(getRetrievalObject().asCommaSeparated(getRetrievalObject().getColumns(),
                                                               new ArrayList()));
        sqlBuffer.append(" FROM ");
        if (getRetrievalObject().getTableNames()
                .size()
            != 0)
        {
            sqlBuffer.append(getRetrievalObject().tableNamesString());
            sqlBuffer.append(" , ");
        }
        int
            i =
            0;
        Iterator
            iter =
            getJoinObjects().iterator();
        while (iter.hasNext())
        {
            if (i
                != 0)
            {
                sqlBuffer.append(" , ");
            }
            i++;
            sqlBuffer.append(((AbstractData) iter.next()).tableNamesString());
        }
        sqlBuffer.append(" WHERE ");
        iter =
            getRetrievalColumns().iterator();
        Iterator
            iter1 =
            getJoinOperators().iterator();
        Iterator
            iter2 =
            getJoinColumns().iterator();
        int
            c =
            0;
        while (iter.hasNext())
        {
            if (c
                != 0)
            {
                sqlBuffer.append(" AND ");
            }
            c++;
            sqlBuffer.append(((ColumnData) iter.next()).getColumnName());
            if (iter1.hasNext())
            {
                sqlBuffer.append(" "
                                 + iter1.next()
                                 + " ");
            }
            else
            {
                sqlBuffer.append(" = ");
            }
            sqlBuffer.append(((ColumnData) iter2.next()).getColumnName());
        }
        iter =
            getIdentityColumns().iterator();
        iter1 =
            getIdentityOperators().iterator();
        iter2 =
            getIdentityValues().iterator();
        while (iter.hasNext())
        {
            sqlBuffer.append(" AND ");
            sqlBuffer.append(((ColumnData) iter.next()).getColumnName());
            if (iter1.hasNext())
            {
                sqlBuffer.append(" "
                                 + iter1.next()
                                 + " ");
            }
            else
            {
                sqlBuffer.append(" = ");
            }
            sqlBuffer.append((String) iter2.next());
        }
        if ((getLikeColumns()
             != null)
            && (getLikeColumns().size()
                != 0))
        {
            iter =
                getLikeColumns().iterator();
            iter2 =
                getLikeValues().iterator();
            while (iter.hasNext())
            {
                sqlBuffer.append(" AND ");
                sqlBuffer.append(((ColumnData) iter.next()).getColumnName());
                sqlBuffer.append(" LIKE ");
                sqlBuffer.append((String) iter2.next());
            }
        }
        if (getOrderByColumn()
            != null)
        {
            sqlBuffer.append(" ORDER BY ");
            sqlBuffer.append(getOrderByColumn().getColumnName());
        }
        SQLHelperPreparedStatement
            statement =
            new SQLHelperPreparedStatement(sqlBuffer.toString());
        return getRetrievalObject().retrieveMultiple(statement);
    }
}

