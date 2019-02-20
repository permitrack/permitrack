package com.sehinc.stormwater.action.bmpdb;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.stormwater.action.report.BMPReportHelper;
import com.sehinc.stormwater.db.plan.BMPFieldType;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class BMPDBForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(BMPDBForm.class);

    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    Integer
        bmpDbCategoryId;
    private
    String
        bmpDbCategoryName;
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
        useSection;
    private
    boolean
        isRequired;
    private
    String
        isNewCategory =
        "false";
    private
    Integer
        formType;
    private
    String
        field_name1;
    private
    String
        field_value1;
    private
    String
        field_type1;
    private
    String
        input_type1;
    private
    String
        explanation_1;
    private
    String
        field_name2;
    private
    String
        field_value2;
    private
    String
        field_type2;
    private
    String
        input_type2;
    private
    String
        explanation_2;
    private
    String
        field_name3;
    private
    String
        field_value3;
    private
    String
        field_type3;
    private
    String
        input_type3;
    private
    String
        explanation_3;
    private
    String
        field_name4;
    private
    String
        field_value4;
    private
    String
        field_type4;
    private
    String
        input_type4;
    private
    String
        explanation_4;
    private
    String
        field_name5;
    private
    String
        field_value5;
    private
    String
        field_type5;
    private
    String
        input_type5;
    private
    String
        explanation_5;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public Integer getBmpDbCategoryId()
    {
        return bmpDbCategoryId;
    }

    public void setBmpDbCategoryId(Integer bmpDbCategoryId)
    {
        this.bmpDbCategoryId =
            bmpDbCategoryId;
    }

    public String getBmpDbCategoryName()
    {
        return bmpDbCategoryName;
    }

    public void setBmpDbCategoryName(String bmpDbCategoryName)
    {
        this.bmpDbCategoryName =
            bmpDbCategoryName;
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

    public boolean isUseSection()
    {
        return useSection;
    }

    public void setUseSection(boolean useSection)
    {
        this.useSection =
            useSection;
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

    public void setField_value1(String field_value1)
    {
        this.field_value1 =
            field_value1;
    }

    public String getField_value1()
    {
        return field_value1;
    }

    public void setInput_type1(String input_type1)
    {
        this.input_type1 =
            input_type1;
    }

    public String getInput_type1()
    {
        return input_type1;
    }

    public String getField_type1()
    {
        return field_type1;
    }

    public void setField_type1(String field_type1)
    {
        this.field_type1 =
            field_type1;
    }

    public String getField_name1()
    {
        return field_name1;
    }

    public void setField_name1(String field_name1)
    {
        this.field_name1 =
            field_name1;
    }

    public String getExplanation_1()
    {
        return explanation_1;
    }

    public void setExplanation_1(String explanation_1)
    {
        this.explanation_1 =
            explanation_1;
    }

    public void setField_value2(String field_value2)
    {
        this.field_value2 =
            field_value2;
    }

    public String getField_value2()
    {
        return field_value2;
    }

    public void setInput_type2(String input_type2)
    {
        this.input_type2 =
            input_type2;
    }

    public String getInput_type2()
    {
        return input_type2;
    }

    public String getField_type2()
    {
        return field_type2;
    }

    public void setField_type2(String field_type2)
    {
        this.field_type2 =
            field_type2;
    }

    public String getField_name2()
    {
        return field_name2;
    }

    public void setField_name2(String field_name2)
    {
        this.field_name2 =
            field_name2;
    }

    public String getExplanation_2()
    {
        return explanation_2;
    }

    public void setExplanation_2(String explanation_2)
    {
        this.explanation_2 =
            explanation_2;
    }

    public void setField_value3(String field_value3)
    {
        this.field_value3 =
            field_value3;
    }

    public String getField_value3()
    {
        return field_value3;
    }

    public String getField_type3()
    {
        return field_type3;
    }

    public void setField_type3(String field_type3)
    {
        this.field_type3 =
            field_type3;
    }

    public String getField_name3()
    {
        return field_name3;
    }

    public void setField_name3(String field_name3)
    {
        this.field_name3 =
            field_name3;
    }

    public void setInput_type3(String input_type3)
    {
        this.input_type3 =
            input_type3;
    }

    public String getInput_type3()
    {
        return input_type3;
    }

    public String getExplanation_3()
    {
        return explanation_3;
    }

    public void setExplanation_3(String explanation_3)
    {
        this.explanation_3 =
            explanation_3;
    }

    public void setField_value4(String field_value4)
    {
        this.field_value4 =
            field_value4;
    }

    public String getField_value4()
    {
        return field_value4;
    }

    public String getField_type4()
    {
        return field_type4;
    }

    public void setField_type4(String field_type4)
    {
        this.field_type4 =
            field_type4;
    }

    public String getField_name4()
    {
        return field_name4;
    }

    public void setField_name4(String field_name4)
    {
        this.field_name4 =
            field_name4;
    }

    public void setInput_type4(String input_type4)
    {
        this.input_type4 =
            input_type4;
    }

    public String getInput_type4()
    {
        return input_type4;
    }

    public String getExplanation_4()
    {
        return explanation_4;
    }

    public void setExplanation_4(String explanation_4)
    {
        this.explanation_4 =
            explanation_4;
    }

    public void setField_value5(String field_value5)
    {
        this.field_value5 =
            field_value5;
    }

    public String getField_value5()
    {
        return field_value5;
    }

    public String getField_type5()
    {
        return field_type5;
    }

    public void setField_type5(String field_type5)
    {
        this.field_type5 =
            field_type5;
    }

    public String getField_name5()
    {
        return field_name5;
    }

    public void setField_name5(String field_name5)
    {
        this.field_name5 =
            field_name5;
    }

    public void setInput_type5(String input_type5)
    {
        this.input_type5 =
            input_type5;
    }

    public String getInput_type5()
    {
        return input_type5;
    }

    public String getExplanation_5()
    {
        return explanation_5;
    }

    public void setExplanation_5(String explanation_5)
    {
        this.explanation_5 =
            explanation_5;
    }

    public boolean isRequired()
    {
        return isRequired;
    }

    public void setRequired(boolean isRequired)
    {
        this.isRequired =
            isRequired;
    }

    public String getRequiredString()
    {
        if (isRequired)
        {
            return "Yes";
        }
        return "No";
    }

    public void setRequiredString(String value)
    {
        LOG.info("in setRequiredString value = "
                 + value);
        if (value.equals("Yes"))
        {
            this.isRequired =
                true;
        }
        else
        {
            this.isRequired =
                false;
        }
    }

    public String getIsNewCategory()
    {
        return isNewCategory;
    }

    public void setIsNewCategory(String isNewCategory)
    {
        this.isNewCategory =
            isNewCategory;
    }

    public void reset()
    {
        this.id =
            null;
        this.clientId =
            null;
        this.bmpDbCategoryId =
            null;
        this.bmpDbCategoryName =
            null;
        this.name =
            null;
        this.number =
            null;
        this.section =
            null;
        this.useSection =
            false;
        this.isRequired =
            false;
        this.isNewCategory =
            "false";
        this.formType =
            null;
        this.field_name1 =
            null;
        this.field_value1 =
            null;
        this.field_type1 =
            null;
        this.input_type1 =
            null;
        this.explanation_1 =
            null;
        this.field_name2 =
            null;
        this.field_value2 =
            null;
        this.field_type2 =
            null;
        this.input_type2 =
            null;
        this.explanation_2 =
            null;
        this.field_name3 =
            null;
        this.field_value3 =
            null;
        this.field_type3 =
            null;
        this.input_type3 =
            null;
        this.explanation_3 =
            null;
        this.field_name4 =
            null;
        this.field_value4 =
            null;
        this.field_type4 =
            null;
        this.input_type4 =
            null;
        this.explanation_4 =
            null;
        this.field_name5 =
            null;
        this.field_value5 =
            null;
        this.field_type5 =
            null;
        this.input_type5 =
            null;
        this.explanation_5 =
            null;
    }

    public void initializeNonMPCABMP()
    {
        this.field_type1 =
            "1";
        this.field_type2 =
            "2";
        this.field_type3 =
            "3";
        this.field_type4 =
            "4";
        this.field_type5 =
            "0";
        this.explanation_1 =
            null;
        this.explanation_2 =
            null;
        this.explanation_3 =
            null;
        this.explanation_4 =
            null;
        this.explanation_5 =
            null;
        BMPFieldType
            type =
            BMPFieldType.getTypeById(new Integer(this.field_type1));
        this.setField_name1(type.getLabel());
        this.setInput_type1(type.getInputTypeName());
        this.setField_value1(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type2));
        this.setField_name2(type.getLabel());
        this.setInput_type2(type.getInputTypeName());
        this.setField_value2(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type3));
        this.setField_name3(type.getLabel());
        this.setInput_type3(type.getInputTypeName());
        this.setField_value3(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type4));
        ;
        this.setField_name4(type.getLabel());
        this.setInput_type4(type.getInputTypeName());
        this.setField_value4(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.setField_name5("");
        this.setInput_type5("OTHER");
    }

    public void initializeMPCAForm1BMP()
    {
        this.field_type1 =
            "1";
        this.field_type2 =
            "5";
        this.field_type3 =
            "6";
        this.field_type4 =
            "7";
        this.field_type5 =
            "0";
        BMPFieldType
            type =
            BMPFieldType.getTypeById(new Integer(this.field_type1));
        this.setField_name1(type.getLabel());
        this.setInput_type1(type.getInputTypeName());
        this.setField_value1(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_1 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type2));
        this.setField_name2(type.getLabel());
        this.setInput_type2(type.getInputTypeName());
        this.setField_value2(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_2 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type3));
        this.setField_name3(type.getLabel());
        this.setInput_type3(type.getInputTypeName());
        this.setField_value3(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_3 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type4));
        ;
        this.setField_name4(type.getLabel());
        this.setInput_type4(type.getInputTypeName());
        this.setField_value4(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_4 =
            type.getExplanation();
        this.setField_name5("");
        this.setInput_type5("OTHER");
        this.explanation_5 =
            null;
    }

    public void initializeMPCAForm2BMP()
    {
        this.field_type1 =
            "9";
        this.field_type2 =
            "10";
        this.field_type3 =
            "11";
        this.field_type4 =
            "12";
        this.field_type5 =
            "13";
        BMPFieldType
            type =
            BMPFieldType.getTypeById(new Integer(this.field_type1));
        this.setField_name1(type.getLabel());
        this.setInput_type1(type.getInputTypeName());
        this.setField_value1(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_1 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type2));
        this.setField_name2(type.getLabel());
        this.setInput_type2(type.getInputTypeName());
        this.setField_value2(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_2 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type3));
        this.setField_name3(type.getLabel());
        this.setInput_type3(type.getInputTypeName());
        this.setField_value3(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_3 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type4));
        ;
        this.setField_name4(type.getLabel());
        this.setInput_type4(type.getInputTypeName());
        this.setField_value4(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_4 =
            type.getExplanation();
        type =
            BMPFieldType.getTypeById(new Integer(this.field_type5));
        this.setField_name5(type.getLabel());
        this.setInput_type5(type.getInputTypeName());
        this.setField_value5(BMPReportHelper.formatBmpDbField(null,
                                                              type.getId(),
                                                              ""));
        this.explanation_5 =
            type.getExplanation();
    }

    public void validateForm(ActionErrors errors)
    {
        if (name
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.required",
                                         "BMP Name"));
        }
    }
}
