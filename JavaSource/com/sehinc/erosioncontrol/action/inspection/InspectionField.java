package com.sehinc.erosioncontrol.action.inspection;

public class InspectionField
    implements java.io.Serializable
{
    private final
    String
        name;
    private final
    String
        key;
    private final
    String
        valueMethod;

    protected InspectionField(String name, String key, String valueMethod)
    {
        this.name =
            name;
        this.key =
            key;
        this.valueMethod =
            valueMethod;
    }

    public String toString()
    {
        return key
               + ";"
               + name;
    }

    public String getName()
    {
        return this.name;
    }

    public String getKey()
    {
        return this.key;
    }

    public String getValueMethod()
    {
        return this.valueMethod;
    }

    public static InspectionField getByKey(String key)
    {
        if (key.equals(INSPECTION_DATE.getKey()))
        {
            return INSPECTION_DATE;
        }
        else if (key.equals(ENTERED_DATE.getKey()))
        {
            return ENTERED_DATE;
        }
        else if (key.equals(INSPECTOR.getKey()))
        {
            return INSPECTOR;
        }
        else if (key.equals(REASON.getKey()))
        {
            return REASON;
        }
        else if (key.equals(STATUS.getKey()))
        {
            return STATUS;
        }
        else
        {
            return null;
        }
    }

    public static final
    InspectionField
        INSPECTION_DATE =
        new InspectionField("Inspection Date",
                            "EC_INSPECTION_DATE_FIELD",
                            "inspectionDate");
    public static final
    InspectionField
        ENTERED_DATE =
        new InspectionField("Entered Date",
                            "EC_INSPECTION_ENTERED_DATE_FIELD",
                            "enteredDate");
    public static final
    InspectionField
        INSPECTOR =
        new InspectionField("Inspector",
                            "EC_INSPECTION_INSPECTOR_FIELD",
                            "inspector");
    public static final
    InspectionField
        REASON =
        new InspectionField("Reason",
                            "EC_INSPECTION_REASON_FIELD",
                            "reason");
    public static final
    InspectionField
        STATUS =
        new InspectionField("Status",
                            "EC_INSPECTION_STATUS_FIELD",
                            "status");
}
