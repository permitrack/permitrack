package com.sehinc.erosioncontrol.action.project;

public class ProjectField
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

    protected ProjectField(String name, String key, String valueMethod)
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

    public static ProjectField getByKey(String key)
    {
        if (key.equals(NAME.getKey()))
        {
            return NAME;
        }
        else if (key.equals(TYPE.getKey()))
        {
            return TYPE;
        }
        else if (key.equals(GROUP.getKey()))
        {
            return GROUP;
        }
        else if (key.equals(PERMIT_NUMBER.getKey()))
        {
            return PERMIT_NUMBER;
        }
        else if (key.equals(PERMIT_AUTHORITY.getKey()))
        {
            return PERMIT_AUTHORITY;
        }
        else if (key.equals(PERMITTEE.getKey()))
        {
            return PERMITTEE;
        }
        else if (key.equals(STATUS.getKey()))
        {
            return STATUS;
        }
        else if (key.equals(START_DATE.getKey()))
        {
            return START_DATE;
        }
        else if (key.equals(EFFECTIVE_DATE.getKey()))
        {
            return EFFECTIVE_DATE;
        }
        else if (key.equals(SEED_DATE.getKey()))
        {
            return SEED_DATE;
        }
        else if (key.equals(TOTAL_SITE_AREA.getKey()))
        {
            return TOTAL_SITE_AREA;
        }
        else if (key.equals(DISTURBED_AREA.getKey()))
        {
            return DISTURBED_AREA;
        }
        else if (key.equals(NEW_IMPERVIOUS_AREA.getKey()))
        {
            return NEW_IMPERVIOUS_AREA;
        }
        else if (key.equals(LAST_INSPECTION_DATE.getKey()))
        {
            return LAST_INSPECTION_DATE;
        }
        else
        {
            return null;
        }
    }

    public static final
    ProjectField
        NAME =
        new ProjectField("Project",
                         "EC_PROJECT_FIELD_NAME",
                         "name");
    public static final
    ProjectField
        TYPE =
        new ProjectField("Type",
                         "EC_PROJECT_FIELD_TYPE",
                         "projectType");
    public static final
    ProjectField
        GROUP =
        new ProjectField("Group",
                         "EC_PROJECT_FIELD_GROUP",
                         "zone");
    public static final
    ProjectField
        PERMIT_NUMBER =
        new ProjectField("Permit Number",
                         "EC_PROJECT_FIELD_PERMIT_NUMBER",
                         "permitNumber");
    public static final
    ProjectField
        PERMIT_AUTHORITY =
        new ProjectField("Permit Authority",
                         "EC_PROJECT_FIELD_PERMIT_AUTHORITY",
                         "permitAuthorityClientName");
    public static final
    ProjectField
        PERMITTEE =
        new ProjectField("Permittee",
                         "EC_PROJECT_FIELD_PERMITTEE",
                         "permittedClientName");
    public static final
    ProjectField
        STATUS =
        new ProjectField("Status",
                         "EC_PROJECT_FIELD_STATUS",
                         "status");
    public static final
    ProjectField
        START_DATE =
        new ProjectField("Start Date",
                         "EC_PROJECT_FIELD_START_DATE",
                         "startDate");
    public static final
    ProjectField
        EFFECTIVE_DATE =
        new ProjectField("Effective Date",
                         "EC_PROJECT_FIELD_EFFECTIVE_DATE",
                         "effectiveDate");
    public static final
    ProjectField
        SEED_DATE =
        new ProjectField("Seed Date",
                         "EC_PROJECT_FIELD_SEED_DATE",
                         "seedDate");
    public static final
    ProjectField
        TOTAL_SITE_AREA =
        new ProjectField("Total Area",
                         "EC_PROJECT_FIELD_TOTAL_SITE_AREA",
                         "totalSiteArea");
    public static final
    ProjectField
        DISTURBED_AREA =
        new ProjectField("Disturbed Area",
                         "EC_PROJECT_FIELD_DISTURBED_AREA",
                         "disturbedArea");
    public static final
    ProjectField
        NEW_IMPERVIOUS_AREA =
        new ProjectField("New Inpervious Area",
                         "EC_PROJECT_FIELD_NEW_IMPERVIOUS_AREA",
                         "newImperviousArea");
    public static final
    ProjectField
        LAST_INSPECTION_DATE =
        new ProjectField("Last Inspection Date",
                         "EC_PROJECT_FIELD_LAST_INSPECTION_DATE",
                         "lastInspectionDateString");
}
