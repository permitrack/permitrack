package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

import java.util.Collection;

public class SubSectionData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(SubSectionData.class);
    private
    String
        description;
    private
    Integer
        sectionId;
    private
    String
        title;
    private
    Integer
        orderNum;
    private
    Collection
        elements;

    public SubSectionData()
    {
    }

    public SubSectionData(Integer id)
    {
        setId(id);
    }

    public String getDescription()
    {
        return description;
    }

    public Collection getElements()
    {
        return elements;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public Integer getSectionId()
    {
        return sectionId;
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

    public void setElements(Collection collection)
    {
        elements =
            collection;
    }

    public void setOrderNum(Integer integer)
    {
        orderNum =
            integer;
    }

    public void setSectionId(Integer integer)
    {
        sectionId =
            integer;
    }

    public void setTitle(String string)
    {
        title =
            string;
    }
}
