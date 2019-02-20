package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class BMPData
    extends StatusData
{
    private
    Integer
        mcmId;
    private
    String
        name;
    private
    Integer
        ownerId;
    private
    boolean
        required;
    private
    Integer
        number;
    private
    String
        section;
    private
    String
        identifier;
    private
    boolean
        nameLocked;
    private
    Integer
        formType;
    private
    String
        field_value1;
    private
    Integer
        field_type1;
    private
    String
        field_value2;
    private
    Integer
        field_type2;
    private
    String
        field_value3;
    private
    Integer
        field_type3;
    private
    String
        field_value4;
    private
    Integer
        field_type4;
    private
    String
        field_value5;
    private
    Integer
        field_type5;

    public BMPData()
    {
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
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

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier =
            identifier;
    }

    public boolean isNameLocked()
    {
        return nameLocked;
    }

    public void setNameLocked(boolean nameLocked)
    {
        this.nameLocked =
            nameLocked;
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

    public String getField_value1()
    {
        return field_value1;
    }

    public void setField_value1(String field_value1)
    {
        this.field_value1 =
            field_value1;
    }

    public Integer getField_type1()
    {
        return field_type1;
    }

    public void setField_type1(Integer field_type1)
    {
        this.field_type1 =
            field_type1;
    }

    public String getField_value2()
    {
        return field_value2;
    }

    public void setField_value2(String field_value2)
    {
        this.field_value2 =
            field_value2;
    }

    public Integer getField_type2()
    {
        return field_type2;
    }

    public void setField_type2(Integer field_type2)
    {
        this.field_type2 =
            field_type2;
    }

    public String getField_value3()
    {
        return field_value3;
    }

    public void setField_value3(String field_value3)
    {
        this.field_value3 =
            field_value3;
    }

    public Integer getField_type3()
    {
        return field_type3;
    }

    public void setField_type3(Integer field_type3)
    {
        this.field_type3 =
            field_type3;
    }

    public String getField_value4()
    {
        return field_value4;
    }

    public void setField_value4(String field_value4)
    {
        this.field_value4 =
            field_value4;
    }

    public Integer getField_type4()
    {
        return field_type4;
    }

    public void setField_type4(Integer field_type4)
    {
        this.field_type4 =
            field_type4;
    }

    public String getField_value5()
    {
        return field_value5;
    }

    public void setField_value5(String field_value5)
    {
        this.field_value5 =
            field_value5;
    }

    public Integer getField_type5()
    {
        return field_type5;
    }

    public void setField_type5(Integer field_type5)
    {
        this.field_type5 =
            field_type5;
    }

    public static List findActiveByMcmId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from BMPData as data where data.mcmId = ? and data.status.code = ? "
            + "order by case when data.section is null or data.section is empty then '' else data.section end asc, data.number asc, data.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
