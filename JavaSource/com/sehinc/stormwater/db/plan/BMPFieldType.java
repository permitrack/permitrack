package com.sehinc.stormwater.db.plan;

import com.sehinc.common.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Iterator;

public class BMPFieldType
    extends LabelValueBean
{
    public static final
    String
        TEXT =
        "textarea";
    private static final
    String
        OTHER =
        "OTHER";
    private static final
    String
        AGGREGATE_TEXT =
        "This will be aggregated later";
    public static final
    BMPFieldType
        UNKNOWN =
        new BMPFieldType("Unknown",
                         0,
                         OTHER,
                         "");
    private static final
    BMPFieldType
        DESCRIPTION =
        new BMPFieldType("Description",
                         1,
                         TEXT,
                         "");
    private static final
    BMPFieldType
        JUSTIFICATION =
        new BMPFieldType("Justification",
                         2,
                         TEXT,
                         "");
    private static final
    BMPFieldType
        EDUCATION_PROGRAM =
        new BMPFieldType("Education Program / Public Outreach",
                         3,
                         TEXT,
                         "");
    private static final
    BMPFieldType
        ANNUAL_REPORTING_ITEMS =
        new BMPFieldType("Annual Reporting Items",
                         4,
                         TEXT,
                         "");
    public static final
    BMPFieldType
        MEASURABLE_GOALS =
        new BMPFieldType("Measurable Goals",
                         5,
                         OTHER,
                         "Define the milestones that are to be accomplished by the implementation of this BMP. "
                         + "Establish a baseline from which you will measure effectiveness, how the measurements are to be made, "
                         + "and how the success will be defined and quantified.");
    public static final
    BMPFieldType
        TIMELINE =
        new BMPFieldType("Timeline / Implementation Schedule",
                         6,
                         OTHER,
                         "Provide specific dates that milestones identified as measurable goals are to be met. "
                         + "Include when materials will be created, printed, and distributed.  "
                         + "The schedule should also outline dates when measurable goals will be evaluated to determine program effectiveness.");
    private static final
    BMPFieldType
        SPECIFIC_COMPONENTS =
        new BMPFieldType("Specific Components and Notes",
                         7,
                         TEXT,
                         "Include any additional notes relevant to the specific purpose of each BMP and how the BMPs "
                         + "for the minimum control measure have been modified from past practice based on experience and monitoring.");
    private static final
    BMPFieldType
        RESPONSIBLE_PARTY =
        new BMPFieldType("Responsible Party",
                         8,
                         TEXT,
                         "Indicate who specifically is responsible for the implementation and monitoring of this BMP. "
                         + "This should be the individual who is actively involved with the BMP and not simply a city official "
                         + "who is signing the application for permit coverage.");
    private static final
    BMPFieldType
        AUDIENCES_INVOLVED =
        new BMPFieldType("Audiences Involved",
                         9,
                         TEXT,
                         "Define the specific audience or audiences that will be the target of the "
                         + "education program for the minimum control measured addressed in this BMP.");
    private static final
    BMPFieldType
        EDUCATIONAL_GOALS =
        new BMPFieldType("Educational Goals for Each Audience",
                         10,
                         TEXT,
                         "Define the educational goal of the BMP and how they are associated with each audience.");
    public static final
    BMPFieldType
        ACTIVITIS_FOR_GOALS =
        new BMPFieldType("Activities to Reach Educational Goals",
                         11,
                         OTHER,
                         "Outline the specific activities that will be in place to ensure that the educational goals are met.");
    public static final
    BMPFieldType
        ACTIVITY_IMPLEMENTATION_PLAN =
        new BMPFieldType("Activity Implementation Plan",
                         12,
                         OTHER,
                         "Define how you will put each specified activity into place. "
                         + "Also indicate the specific timeline that you will follow. "
                         + "Include major milestones and the dates by which each will be implemented.");
    private static final
    BMPFieldType
        PERFORMANCE_MEASURES =
        new BMPFieldType("Performance Measures",
                         13,
                         TEXT,
                         "Outline how you will measure the success of this BMP. "
                         + "Determine a baseline from which the measurements will be made. "
                         + "Briefly describe how you will quantify the success of an increase in education.");
    private static final
    BMPFieldType
        ASSESSMENT =
        new BMPFieldType("Assessment",
                         14,
                         TEXT,
                         "");
    private static
    ArrayList
        ALL_TYPES =
        null;
    private
    Integer
        id;
    private
    String
        inputTypeName;
    private
    String
        explanation;

    public Integer getId()
    {
        return id;
    }

    public static Integer getId(BMPFieldType fieldType)
    {
        return fieldType.getId();
    }

    public BMPFieldType(String name, int value, String type)
    {
        super(name,
              String.valueOf(value));
        id =
            new Integer(value);
        this.inputTypeName =
            type;
    }

    private BMPFieldType(String name, int value, String type, String explanation)
    {
        super(name,
              String.valueOf(value));
        id =
            new Integer(value);
        this.inputTypeName =
            type;
        this.explanation =
            explanation;
    }

    public String getInputTypeName()
    {
        return this.inputTypeName;
    }

    public String getExplanation()
    {
        return this.explanation;
    }

    private static synchronized ArrayList getBMPFieldTypes()
    {
        if (ALL_TYPES
            == null)
        {
            ALL_TYPES =
                new ArrayList();
            ALL_TYPES.add(UNKNOWN);
            ALL_TYPES.add(DESCRIPTION);
            ALL_TYPES.add(JUSTIFICATION);
            ALL_TYPES.add(EDUCATION_PROGRAM);
            ALL_TYPES.add(ANNUAL_REPORTING_ITEMS);
            ALL_TYPES.add(MEASURABLE_GOALS);
            ALL_TYPES.add(TIMELINE);
            ALL_TYPES.add(SPECIFIC_COMPONENTS);
            ALL_TYPES.add(RESPONSIBLE_PARTY);
            ALL_TYPES.add(AUDIENCES_INVOLVED);
            ALL_TYPES.add(EDUCATIONAL_GOALS);
            ALL_TYPES.add(ACTIVITIS_FOR_GOALS);
            ALL_TYPES.add(ACTIVITY_IMPLEMENTATION_PLAN);
            ALL_TYPES.add(PERFORMANCE_MEASURES);
            ALL_TYPES.add(ASSESSMENT);
        }
        return ALL_TYPES;
    }

    public static BMPFieldType getTypeById(Integer id)
    {
        Iterator
            it =
            getBMPFieldTypes().iterator();
        while (it.hasNext())
        {
            BMPFieldType
                type =
                (BMPFieldType) it.next();
            if (type.id
                .equals(id))
            {
                return type;
            }
        }
        return UNKNOWN;
    }

    public static String getNameById(Integer id)
    {
        Iterator
            it =
            getBMPFieldTypes().iterator();
        while (it.hasNext())
        {
            BMPFieldType
                type =
                (BMPFieldType) it.next();
            if (type.id
                .equals(id))
            {
                return type.getLabel();
            }
        }
        return null;
    }

    public static String getInputTypeById(Integer id)
    {
        Iterator
            it =
            getBMPFieldTypes().iterator();
        while (it.hasNext())
        {
            BMPFieldType
                type =
                (BMPFieldType) it.next();
            if (type.id
                .equals(id))
            {
                return type.getInputTypeName();
            }
        }
        return null;
    }

    public static String getExplanationById(Integer id)
    {
        Iterator
            it =
            getBMPFieldTypes().iterator();
        while (it.hasNext())
        {
            BMPFieldType
                type =
                (BMPFieldType) it.next();
            if (type.id
                .equals(id))
            {
                return type.getExplanation();
            }
        }
        return null;
    }

/*
    public String getDisplayValue(BMPData data, String fieldValue)
    {
        if (inputTypeName.equals(OTHER))
        {
            return AGGREGATE_TEXT;
        }
        else
        {
            return fieldValue;
        }
    }
*/

    public boolean isFieldTypeText()
    {
        if (inputTypeName.equals(TEXT))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
