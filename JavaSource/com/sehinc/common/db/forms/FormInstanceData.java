package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

public class FormInstanceData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(FormInstanceData.class);
    private
    FormData
        form;

    public FormInstanceData()
    {
    }

    public FormInstanceData(Integer id)
    {
        setId(id);
    }

    public FormData getForm()
    {
        return form;
    }

    public void setForm(FormData data)
    {
        form =
            data;
    }
}
