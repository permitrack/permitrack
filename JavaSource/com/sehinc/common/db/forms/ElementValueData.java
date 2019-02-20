package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class ElementValueData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(ElementValueData.class);
    private
    Integer
        formInstanceId;
    private
    Integer
        elementId;
    private
    String
        elementValue;
    public static
    String
        FIND_ELEMENT_VALUE_BY_FORM_INSTANCE_ID_AND_ELEMENT_TYPE =
        "com.sehinc.common.db.forms.ElementValueData.byFormInstanceIdAndElementType";
    public static
    String
        FIND_ELEMENT_VALUE_BY_FORM_INSTANCE_ID_AND_ELEMENT_ID =
        "com.sehinc.common.db.forms.ElementValueData.byFormInstanceIdAndElementId";

    public ElementValueData()
    {
    }

    public ElementValueData(Integer id)
    {
        setId(id);
    }

    public String toString()
    {
        return "ELEMENT_VALUE.ELEMENT_VALUE_ID: "
               + getId().toString()
               +
               "\nELEMENT_VALUE.FORM_INSTANCE_ID: "
               + formInstanceId.toString()
               +
               "\nELEMENT_VALUE.ELEMENT_ID: "
               + elementId.toString()
               +
               "\nELEMENT_VALUE.ELEMENT_VALUE: "
               + elementValue;
    }

    public Integer getElementId()
    {
        return elementId;
    }

    public String getElementValue()
    {
        return elementValue;
    }

    public Integer getFormInstanceId()
    {
        return formInstanceId;
    }

    public void setElementId(Integer integer)
    {
        elementId =
            integer;
    }

    public void setElementValue(String string)
    {
        elementValue =
            string;
    }

    public void setFormInstanceId(Integer integer)
    {
        formInstanceId =
            integer;
    }

    public static List getElementValueByElementId(Integer formInstanceId, Integer elementId)
    {
        Object[][]
            parameters =
            {
                {
                    "formInstanceId",
                    formInstanceId},
                {
                    "elementId",
                    elementId}};
        return HibernateUtil.findByNamedQuery(FIND_ELEMENT_VALUE_BY_FORM_INSTANCE_ID_AND_ELEMENT_ID,
                                              parameters);
    }

    public static List getElementValueByElementType(Integer formInstanceId, String htmlType)
    {
        Object[][]
            parameters =
            {
                {
                    "formInstanceId",
                    formInstanceId},
                {
                    "htmlType",
                    htmlType}};
        return HibernateUtil.findByNamedQuery(FIND_ELEMENT_VALUE_BY_FORM_INSTANCE_ID_AND_ELEMENT_TYPE,
                                              parameters);
    }
}




