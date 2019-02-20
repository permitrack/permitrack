package com.sehinc.common.db.base;

import com.sehinc.common.db.sql.SQLHelperPreparedStatement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomJoinQuery
{
    public
    AbstractData
        retrievalObject;
    public
    AbstractData
        joinObject;
    public
    ColumnData
        retrievalColumn;
    public
    ColumnData
        retrievalColumn2 =
        null;
    public
    ColumnData
        joinColumn;
    public
    ColumnData
        joinColumn2 =
        null;
    public
    ColumnData
        orderByColumn;
    public
    ColumnData
        identityColumn;
    public
    String
        identityValue;

    public CustomJoinQuery()
    {
    }

    public CustomJoinQuery(AbstractData retrievalObject, AbstractData joinObject, ColumnData retrievalColumn, ColumnData joinColumn, ColumnData identityColumn, String identityValue, ColumnData orderByColumn)
    {
        setRetrievalObject(retrievalObject);
        setJoinObject(joinObject);
        setRetrievalColumn(retrievalColumn);
        setJoinColumn(joinColumn);
        setIdentityColumn(identityColumn);
        setIdentityValue(identityValue);
        setOrderByColumn(orderByColumn);
    }

    public CustomJoinQuery(AbstractData retrievalObject, AbstractData joinObject, ColumnData retrievalColumn, ColumnData joinColumn, ColumnData retrievalColumn2, ColumnData joinColumn2, ColumnData identityColumn, String identityValue, ColumnData orderByColumn)
    {
        setRetrievalObject(retrievalObject);
        setJoinObject(joinObject);
        setRetrievalColumn(retrievalColumn);
        setJoinColumn(joinColumn);
        setRetrievalColumn2(retrievalColumn2);
        setJoinColumn2(joinColumn2);
        setIdentityColumn(identityColumn);
        setIdentityValue(identityValue);
        setOrderByColumn(orderByColumn);
    }

    public void setRetrievalColumn2(ColumnData retrievalColumn2)
    {
        this.retrievalColumn2 =
            retrievalColumn2;
    }

    public void setJoinColumn2(ColumnData joinColumn2)
    {
        this.joinColumn2 =
            joinColumn2;
    }

    public void setIdentityValue(String identityValue)
    {
        this.identityValue =
            identityValue;
    }

    public void setIdentityColumn(ColumnData identityColumn)
    {
        this.identityColumn =
            identityColumn;
    }

    public void setOrderByColumn(ColumnData orderByColumn)
    {
        this.orderByColumn =
            orderByColumn;
    }

    public void setRetrievalObject(AbstractData retrievalObject)
    {
        this.retrievalObject =
            retrievalObject;
    }

    public void setJoinObject(AbstractData joinObject)
    {
        this.joinObject =
            joinObject;
    }

    public void setRetrievalColumn(ColumnData retrievalColumn)
    {
        this.retrievalColumn =
            retrievalColumn;
    }

    public void setJoinColumn(ColumnData joinColumn)
    {
        this.joinColumn =
            joinColumn;
    }

    public ColumnData getRetrievalColumn2()
    {
        return retrievalColumn2;
    }

    public ColumnData getJoinColumn2()
    {
        return joinColumn2;
    }

    public String getIdentityValue()
    {
        return identityValue;
    }

    public ColumnData getIdentityColumn()
    {
        return identityColumn;
    }

    public ColumnData getOrderByColumn()
    {
        return orderByColumn;
    }

    public AbstractData getRetrievalObject()
    {
        return retrievalObject;
    }

    public AbstractData getJoinObject()
    {
        return joinObject;
    }

    public ColumnData getRetrievalColumn()
    {
        return retrievalColumn;
    }

    public ColumnData getJoinColumn()
    {
        return joinColumn;
    }

    public List retrieve(List equalityList, ColumnData groupEqualityColumn, ColumnData compareColumn)
    {
        if ((equalityList
             == null)
            || (equalityList.isEmpty()))
        {
            throw new CustomQueryException("INVALID EQUALITY LIST");
        }
        if (groupEqualityColumn
            == null)
        {
            throw new CustomQueryException("INVALID EQUALITY COLUMN");
        }
        if ((getRetrievalObject()
             == null)
            || (getJoinObject()
                == null)
            || (getRetrievalColumn()
                == null)
            || (getJoinColumn()
                == null)
            || (getIdentityColumn()
                == null)
            || (getIdentityValue()
                == null))
        {
            throw new CustomQueryException("INCOMPLETE DATA FOR CUSTOM JOIN ACTION");
        }
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        sqlBuffer.append("SELECT ");
        sqlBuffer.append(getRetrievalObject().asCommaSeparated(getRetrievalObject().getColumns(),
                                                               new ArrayList()));
        sqlBuffer.append(" FROM ");
        sqlBuffer.append(getRetrievalObject().tableNamesString());
        sqlBuffer.append(" , ");
        sqlBuffer.append(getJoinObject().tableNamesString());
        sqlBuffer.append(" WHERE ");
        sqlBuffer.append(getRetrievalColumn().getColumnName());
        sqlBuffer.append(" = ");
        sqlBuffer.append(getJoinColumn().getColumnName());
        sqlBuffer.append(" AND ");
        sqlBuffer.append(getIdentityColumn().getColumnName());
        sqlBuffer.append(" = ");
        sqlBuffer.append(getIdentityValue());
        sqlBuffer.append(" AND ");
        sqlBuffer.append(compareColumn.getColumnName());
        sqlBuffer.append(" IN ( ");
        sqlBuffer.append(equalityListString(equalityList,
                                            groupEqualityColumn));
        sqlBuffer.append(" ) ");
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

    public List retrieve()
    {
        if ((getRetrievalObject()
             == null)
            || (getJoinObject()
                == null)
            || (getRetrievalColumn()
                == null)
            || (getJoinColumn()
                == null)
            || (getIdentityColumn()
                == null)
            || (getIdentityValue()
                == null))
        {
            throw new CustomQueryException("INCOMPLETE DATA FOR CUSTOM JOIN ACTION");
        }
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        sqlBuffer.append("SELECT ");
        sqlBuffer.append(getRetrievalObject().asCommaSeparated(getRetrievalObject().getColumns(),
                                                               new ArrayList()));
        sqlBuffer.append(" FROM ");
        sqlBuffer.append(getRetrievalObject().tableNamesString());
        sqlBuffer.append(" , ");
        sqlBuffer.append(getJoinObject().tableNamesString());
        sqlBuffer.append(" WHERE ");
        sqlBuffer.append(getRetrievalColumn().getColumnName());
        sqlBuffer.append(" = ");
        sqlBuffer.append(getJoinColumn().getColumnName());
        if ((getRetrievalColumn2()
             != null)
            && (getJoinColumn2()
                != null))
        {
            sqlBuffer.append(" AND ");
            sqlBuffer.append(getRetrievalColumn2().getColumnName());
            sqlBuffer.append(" = ");
            sqlBuffer.append(getJoinColumn2().getColumnName());
        }
        sqlBuffer.append(" AND ");
        sqlBuffer.append(getIdentityColumn().getColumnName());
        sqlBuffer.append(" = ");
        sqlBuffer.append(getIdentityValue());
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

    protected StringBuffer equalityListString(List equalityList, ColumnData equalityColumn)
    {
        StringBuffer
            sqlBuffer =
            new StringBuffer();
        Iterator
            iter =
            equalityList.iterator();
        boolean
            notFirst =
            false;
        while (iter.hasNext())
        {
            AbstractData
                data =
                (AbstractData) iter.next();
            if (notFirst)
            {
                sqlBuffer.append(" , ");
            }
            else
            {
                notFirst =
                    true;
            }
            sqlBuffer.append(data.asSqlString(equalityColumn));
        }
        return sqlBuffer;
    }

    public boolean isKindOf(Object obj, String type)
    {
        try
        {
            Class
                c =
                obj.getClass();
            Class
                tClass =
                Class.forName(type);
            while (c
                   != null)
            {
                if (c
                    == tClass)
                {
                    return true;
                }
                c =
                    c.getSuperclass();
            }
            return false;
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}

