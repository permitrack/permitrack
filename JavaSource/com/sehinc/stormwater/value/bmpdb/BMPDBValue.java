package com.sehinc.stormwater.value.bmpdb;

import com.sehinc.stormwater.db.bmpdb.BMPDBData;

public class BMPDBValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    Integer
        formType;
    private
    Integer
        bmpDBCategoryId;

    public BMPDBValue()
    {
    }

    public BMPDBValue(Integer id, String name)
    {
        this.id =
            id;
        this.name =
            name;
    }

    public BMPDBValue(Integer id, String name, Integer formType, Integer bmpDBCategoryId)
    {
        this.id =
            id;
        this.name =
            name;
        this.formType =
            formType;
        this.bmpDBCategoryId =
            bmpDBCategoryId;
    }

    public BMPDBValue(BMPDBData bmpDbData)
    {
        this.id =
            bmpDbData.getId();
        this.name =
            bmpDbData.getName();
        this.formType =
            bmpDbData.getFormType();
        this.bmpDBCategoryId =
            bmpDbData.getBmpDBCategoryId();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setFormType(Integer formType)
    {
        this.formType =
            formType;
    }

    public Integer getFormType()
    {
        return formType;
    }

    public void setBmpDBCategoryId(Integer bmpDBCategoryId)
    {
        this.bmpDBCategoryId =
            bmpDBCategoryId;
    }

    public Integer getBmpDBCategoryId()
    {
        return bmpDBCategoryId;
    }
}
