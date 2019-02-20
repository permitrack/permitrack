package com.sehinc.common.service.option;

import com.sehinc.common.util.LabelValueBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataElementOptionValueSQLHelper
{
    private static
    DataElementOptionValueSQLHelper
        instance =
        new DataElementOptionValueSQLHelper();

    private DataElementOptionValueSQLHelper()
    {
    }

    public static DataElementOptionValueSQLHelper getInstance()
    {
        return instance;
    }

    public Object populate(ResultSet rs)
        throws SQLException
    {
        LabelValueBean
            labelValue =
            new LabelValueBean(rs.getString(1),
                               rs.getString(2));
        return labelValue;
    }

    public String getValueSubQuery()
    {
        String
            VALUE_SUB_QUERY =
            "DATA_ELEMENT_OPTION_VALUE.[VALUE], DATA_ELEMENT_OPTION_VALUE.[VALUE]";
        return VALUE_SUB_QUERY;
    }
}