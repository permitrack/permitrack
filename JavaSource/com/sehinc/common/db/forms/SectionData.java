package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

import java.util.Collection;

public class SectionData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(SectionData.class);
    private
    String
        description;
    private
    Integer
        formId;
    private
    String
        title;
    private
    Integer
        orderNum;
    private
    Integer
        columns;
    private
    Collection
        subSections;

    public SectionData()
    {
    }

    public SectionData(Integer id)
    {
        setId(id);
    }

    public String toString()
    {
        return formId
               == null
            ? ""
            : "SECTION.SECTION_ID: "
              + getId().toString()
              +
              "\nSECTION.DESC: "
              + description
              +
              "\nSECTION.TITLE: "
              + title
              +
              "\nSECTION.FORM_ID: "
              + formId.toString()
              +
              "\nSECTION.ORDERNUM: "
              + orderNum.toString()
              +
              "\nSECTION.COLUMNS: "
              + columns.toString();
    }

    public Integer getColumns()
    {
        return columns;
    }

    public String getDescription()
    {
        return description;
    }

    public Collection getSubSections()
    {
        return subSections;
    }

    public Integer getFormId()
    {
        return formId;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public String getTitle()
    {
        return title;
    }

    public void setColumns(Integer integer)
    {
        columns =
            integer;
    }

    public void setDescription(String string)
    {
        description =
            string;
    }

    public void setSubSections(Collection collection)
    {
        subSections =
            collection;
    }

    public void setFormId(Integer integer)
    {
        formId =
            integer;
    }

    public void setOrderNum(Integer integer)
    {
        orderNum =
            integer;
    }

    public void setTitle(String string)
    {
        title =
            string;
    }
}
