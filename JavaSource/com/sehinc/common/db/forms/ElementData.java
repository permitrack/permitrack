package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

@SuppressWarnings("unused")
public class ElementData
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(ElementData.class);
    private
    String
        title;
    private
    ElementTypeData
        elementType;
    private
    Integer
        childElementId;
    private
    String
        name;
    private
    String
        dataType;
    private
    Integer
        subSectionId;
    private
    Integer
        orderNum;
    private
    String
        required;
    private
    String
        allowOther;
    private
    String
        isChild;
    private
    String
        displayTitle;
    private
    Integer
        size;
    private
    Integer
        maxLength;

    public ElementData()
    {
    }

    public ElementData(Integer id)
    {
        setId(id);
    }

    public String getAllowOther()
    {
        return allowOther;
    }

    public Integer getChildElementId()
    {
        return childElementId;
    }

    public String getDataType()
    {
        return dataType;
    }

    public ElementTypeData getElementType()
    {
        return elementType;
    }

    public String getName()
    {
        return name;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public String getRequired()
    {
        return required;
    }

    public Integer getSubSectionId()
    {
        return subSectionId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAllowOther(String string)
    {
        allowOther =
            string;
    }

    public void setChildElementId(Integer integer)
    {
        childElementId =
            integer;
    }

    public void setDataType(String string)
    {
        dataType =
            string;
    }

    public void setElementType(ElementTypeData data)
    {
        elementType =
            data;
    }

    public void setName(String string)
    {
        name =
            string;
    }

    public void setOrderNum(Integer integer)
    {
        orderNum =
            integer;
    }

    public void setRequired(String string)
    {
        required =
            string;
    }

    public void setSubSectionId(Integer integer)
    {
        subSectionId =
            integer;
    }

    public void setTitle(String string)
    {
        title =
            string;
    }

    public String getIsChild()
    {
        return isChild;
    }

    public void setIsChild(String string)
    {
        isChild =
            string;
    }

    public String getDisplayTitle()
    {
        return displayTitle;
    }

    public void setDisplayTitle(String string)
    {
        displayTitle =
            string;
    }

    public Integer getMaxLength()
    {
        return maxLength;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setMaxLength(Integer integer)
    {
        maxLength =
            integer;
    }

    public void setSize(Integer integer)
    {
        size =
            integer;
    }
}
