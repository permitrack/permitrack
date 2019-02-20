package com.sehinc.stormwater.db.bmpdb;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.UserUpdatableData;

import java.util.List;

public class BMPDBData
    extends UserUpdatableData
    implements Comparable
{
    private
    Integer
        clientId;
    private
    Integer
        bmpDBCategoryId;
    private
    String
        name;
    private
    Integer
        number;
    private
    String
        section;
    private
    boolean
        required;
    private
    Integer
        formType;
    private
    String
        fieldValue1;
    private
    Integer
        fieldType1;
    private
    String
        fieldValue2;
    private
    Integer
        fieldType2;
    private
    String
        fieldValue3;
    private
    Integer
        fieldType3;
    private
    String
        fieldValue4;
    private
    Integer
        fieldType4;
    private
    String
        fieldValue5;
    private
    Integer
        fieldType5;

    public BMPDBData()
    {
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getBmpDBCategoryId()
    {
        return bmpDBCategoryId;
    }

    public void setBmpDBCategoryId(Integer bmpDBCategoryId)
    {
        this.bmpDBCategoryId =
            bmpDBCategoryId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section =
            section;
    }

    public boolean isRequired()
    {
        return required;
    }

    public void setRequired(boolean required)
    {
        this.required =
            required;
    }

    public Integer getFormType()
    {
        return formType;
    }

    public void setFormType(Integer formType)
    {
        this.formType =
            formType;
    }

    public String getFieldValue1()
    {
        return fieldValue1;
    }

    public void setFieldValue1(String fieldValue1)
    {
        this.fieldValue1 =
            fieldValue1;
    }

    public Integer getFieldType1()
    {
        return fieldType1;
    }

    public void setFieldType1(Integer fieldType1)
    {
        this.fieldType1 =
            fieldType1;
    }

    public String getFieldValue2()
    {
        return fieldValue2;
    }

    public void setFieldValue2(String fieldValue2)
    {
        this.fieldValue2 =
            fieldValue2;
    }

    public Integer getFieldType2()
    {
        return fieldType2;
    }

    public void setFieldType2(Integer fieldType2)
    {
        this.fieldType2 =
            fieldType2;
    }

    public String getFieldValue3()
    {
        return fieldValue3;
    }

    public void setFieldValue3(String fieldValue3)
    {
        this.fieldValue3 =
            fieldValue3;
    }

    public Integer getFieldType3()
    {
        return fieldType3;
    }

    public void setFieldType3(Integer fieldType3)
    {
        this.fieldType3 =
            fieldType3;
    }

    public String getFieldValue4()
    {
        return fieldValue4;
    }

    public void setFieldValue4(String fieldValue4)
    {
        this.fieldValue4 =
            fieldValue4;
    }

    public Integer getFieldType4()
    {
        return fieldType4;
    }

    public void setFieldType4(Integer fieldType4)
    {
        this.fieldType4 =
            fieldType4;
    }

    public String getFieldValue5()
    {
        return fieldValue5;
    }

    public void setFieldValue5(String fieldValue5)
    {
        this.fieldValue5 =
            fieldValue5;
    }

    public Integer getFieldType5()
    {
        return fieldType5;
    }

    public void setFieldType5(Integer fieldType5)
    {
        this.fieldType5 =
            fieldType5;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof BMPDBData)
        {
            BMPDBData
                bmpDbData =
                (BMPDBData) obj;
            return getName().compareTo(bmpDbData.getName());
        }
        return -1;
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from BMPDBData as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
