package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

import java.util.Collection;

public class FormData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(FormData.class);
    private
    String
        description;
    private
    String
        title;
    private
    Collection
        sections;

    public FormData()
    {
    }

    public FormData(Integer id)
    {
        setId(id);
    }

    public String toString()
    {
        return "FORM.FORM_ID: "
               + getId().toString()
               + "\nFORM.DESC: "
               + description
               + "\nFORM.TITLE: "
               + title;
    }

    public String getDescription()
    {
        return description;
    }

    public Collection getSections()
    {
        return sections;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String string)
    {
        description =
            string;
    }

    public void setSections(Collection collection)
    {
        sections =
            collection;
    }

    public void setTitle(String string)
    {
        title =
            string;
    }
}
