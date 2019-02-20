package com.sehinc.common.db.sql;

import java.math.BigDecimal;
import java.util.Date;

public interface SQLHelperParameterizedStatement
    extends SQLHelperStatement
{
    void setInt(int index, int value);

    void setString(int index, String value);

    void setBigDecimal(int index, BigDecimal value);

    void setDate(int index, Date value);
}
