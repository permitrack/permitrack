package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.BMPData;

public class BMPValue
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
        number;
    private
    Integer
        mcmId;
    private
    String
        section;

    public BMPValue()
    {
    }

    public BMPValue(Integer id, String name, Integer number, Integer mcmId)
    {
        this.id =
            id;
        this.name =
            name;
        this.number =
            number;
        this.mcmId =
            mcmId;
    }

    public BMPValue(Integer id, String name, Integer number, String section, Integer mcmId)
    {
        this.id =
            id;
        this.name =
            name;
        this.number =
            number;
        this.mcmId =
            mcmId;
        this.section =
            section;
    }

    public BMPValue(BMPData bmpData)
    {
        this.id =
            bmpData.getId();
        this.name =
            bmpData.getName();
        this.number =
            bmpData.getNumber();
        this.mcmId =
            bmpData.getMcmId();
        this.section =
            bmpData.getSection();
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

    public String getSection()
    {
        return section;
    }

    public void setSection(String section)
    {
        this.section =
            section;
    }

    public String getName()
    {
        return name;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public Integer getNumber()
    {
        return number;
    }

    public Integer getMcmId()
    {
        return mcmId;
    }

    public void setMcmId(Integer mcmId)
    {
        this.mcmId =
            mcmId;
    }
}
