package com.sehinc.common.db.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IData
    extends Serializable
{
    Object get(ColumnData column);

    int getInt(ColumnData column);

    BigDecimal getBigDecimal(ColumnData column);

    Date getDate(ColumnData column);

    String getString(ColumnData column);

    String asSqlString(ColumnData column);

    boolean retrieveSingleByColumn(ColumnData column);

    boolean retrieveSingleByColumnAlternate(ColumnData column);

    boolean retrieveByPrimaryKeys();

    boolean retrieveByPrimaryKeysAlternate();

    boolean retrieveSingleByColumns(List retrieveColumns);

    boolean retrieveSingleByColumnsAlternate(List columns);

    List retrieveMultipleByColumn(ColumnData column);

    List retrieveMultipleByColumns(List retrieveColumns)
        throws InvalidFieldIndexException;

    void reset();
}
