package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

public class ElementTypeData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(ElementTypeData.class);
    private
    String
        htmlType;
    private
    String
        name;

    public ElementTypeData()
    {
    }

    public ElementTypeData(Integer id)
    {
        setId(id);
    }

    public String getHtmlType()
    {
        return htmlType;
    }

    public String getName()
    {
        return name;
    }

    public void setHtmlType(String string)
    {
        htmlType =
            string;
    }

    public void setName(String string)
    {
        name =
            string;
    }
}
