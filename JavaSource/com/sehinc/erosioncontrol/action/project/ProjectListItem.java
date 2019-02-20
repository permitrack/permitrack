package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.project.EcProjectSearchData;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class ProjectListItem
    implements Comparable
{
    private final
    String
        key;
    private final
    String
        name;
    private final
    String
        sortQueryKey;
    private final
    Integer
        sortOrder;
    private final
    String
        property;
    private final
    String
        property2;
    private static final
    HashMap
        projectListItems;
    private final
    String
        columnName;
    private final
    String
        image;
    public static final
    ProjectListItem
        PROJECT_NAME =
        new ProjectListItem("PROJECT_NAME",
                            "Project Name",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_PROJECT_NAME,
                            1,
                            "name",
                            null,
                            EcProjectSearchData.PROJECT_NAME,
                            null);
    public static final
    ProjectListItem
        PROJECT_TYPE =
        new ProjectListItem("PROJECT_TYPE",
                            "Type",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_PROJECT_TYPE,
                            2,
                            "projectType",
                            null,
                            EcProjectSearchData.PROJECT_TYPE,
                            null);
    public static final
    ProjectListItem
        PROJECT_ZONE =
        new ProjectListItem("PROJECT_ZONE",
                            "Group",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_ZONE,
                            3,
                            "zone",
                            null,
                            EcProjectSearchData.ZONE_NAME,
                            null);
    public static final
    ProjectListItem
        PERMIT_NUMBER =
        new ProjectListItem("PERMIT_NUMBER",
                            "Permit #",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_PERMIT_NUMBER,
                            4,
                            "permitNumber",
                            null,
                            EcProjectSearchData.PERMIT_NUMBER,
                            null);
    public static final
    ProjectListItem
        PERMIT_AUTHORITY =
        new ProjectListItem("PERMIT_AUTHORITY",
                            "Permit Authority",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_PERMIT_AUTHORITY,
                            5,
                            "permitAuthorityClientName",
                            null,
                            EcProjectSearchData.PERMIT_AUTH_NAME,
                            null);
    public static final
    ProjectListItem
        PERMITTED =
        new ProjectListItem("PERMITTED",
                            "Permittee",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_PERMITTED,
                            6,
                            "permittedClientName",
                            null,
                            EcProjectSearchData.PERMITEE_NAME,
                            null);
    public static final
    ProjectListItem
        AUTHORIZED_INSPECTOR =
        new ProjectListItem("AUTHORIZED_INSPECTOR",
                            "Authorized Inspector",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_AUTHORIZED_INSPECTOR,
                            7,
                            "authorizedInspectorClientName",
                            null,
                            EcProjectSearchData.INSPECTOR_NAME,
                            null);
    public static final
    ProjectListItem
        PROJECT_ADDRESS =
        new ProjectListItem("PROJECT_ADDRESS",
                            "Address",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_ADDRESS,
                            8,
                            "address",
                            "cityStateZip",
                            EcProjectSearchData.ADDRESS,
                            null);
    public static final
    ProjectListItem
        LAST_INSPECTION_DATE =
        new ProjectListItem("LAST_INSPECTION_DATE",
                            "Last Final Inspection",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_LAST_INSPECTION_DATE,
                            9,
                            "lastInspectionDate",
                            null,
                            EcProjectSearchData.LAST_INSPECTION_DATE,
                            "bmpStatusImage");
    public static final
    ProjectListItem
        PROJECT_START_DATE =
        new ProjectListItem("PROJECT_START_DATE",
                            "Start Date",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_START_DATE,
                            10,
                            "startDate",
                            null,
                            EcProjectSearchData.START_DATE,
                            null);
    public static final
    ProjectListItem
        PROJECT_EFFECTIVE_DATE =
        new ProjectListItem("PROJECT_EFFECTIVE_DATE",
                            "Effective Date",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_EFFECTIVE_DATE,
                            11,
                            "effectiveDate",
                            null,
                            EcProjectSearchData.EFFECTIVE_DATE,
                            null);
    public static final
    ProjectListItem
        PROJECT_SEED_DATE =
        new ProjectListItem("PROJECT_SEED_DATE",
                            "Seed Date",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_SEED_DATE,
                            12,
                            "seedDate",
                            null,
                            EcProjectSearchData.SEED_DATE,
                            null);
    public static final
    ProjectListItem
        PROJECT_TOTAL_SITE_AREA =
        new ProjectListItem("PROJECT_TOTAL_SITE_AREA",
                            "Total Area",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_TOTAL_SITE_AREA,
                            13,
                            "totalSiteArea",
                            null,
                            EcProjectSearchData.TOTAL_SITE_AREA,
                            null);
    public static final
    ProjectListItem
        PROJECT_NEW_IMPERVIOUS_AREA =
        new ProjectListItem("PROJECT_NEW_IMPERVIOUS_AREA",
                            "New Impervious Area",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_NEW_IMPERVIOUS_AREA,
                            14,
                            "newImperviousArea",
                            null,
                            EcProjectSearchData.NEW_IMPERVIOUS_AREA,
                            null);
    public static final
    ProjectListItem
        PROJECT_DISTURBED_AREA =
        new ProjectListItem("PROJECT_DISTURBED_AREA",
                            "Disturbed Area",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_DISTURBED_AREA,
                            15,
                            "disturbedArea",
                            null,
                            EcProjectSearchData.DISTURBED_AREA,
                            null);
    public static final
    ProjectListItem
        PROJECT_STATUS =
        new ProjectListItem("PROJECT_STATUS",
                            "Status",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_STATUS,
                            16,
                            "projectStatusDescription",
                            null,
                            EcProjectSearchData.PROJECT_STATUS_DESCRIPTION,
                            null);
    public static final
    ProjectListItem
        PROJECT_CITY =
        new ProjectListItem("PROJECT_CITY",
                            "City",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_CITY,
                            17,
                            "city",
                            null,
                            EcProjectSearchData.CITY,
                            null);
    public static final
    ProjectListItem
        PROJECT_STATE =
        new ProjectListItem("PROJECT_STATE",
                            "State",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_STATE,
                            18,
                            "state",
                            null,
                            EcProjectSearchData.STATE,
                            null);
    public static final
    ProjectListItem
        PROJECT_ZIP =
        new ProjectListItem("PROJECT_ZIP",
                            "Zip",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_ZIP,
                            19,
                            "zip",
                            null,
                            EcProjectSearchData.ZIP,
                            null);
    public static final
    ProjectListItem
        PROJECT_RFA_NUMBER =
        new ProjectListItem("PROJECT_RFA_NUMBER",
                            "RFA Number",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_RFA,
                            20,
                            "rfaNumber",
                            null,
                            EcProjectSearchData.RFA_NUMBER,
                            null);
    public static final
    ProjectListItem
        PROJECT_BLOCK_NUMBER =
        new ProjectListItem("PROJECT_BLOCK_NUMBER",
                            "Block",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_BLOCK,
                            21,
                            "blockNumber",
                            null,
                            EcProjectSearchData.BLOCK_NUMBER,
                            null);
    public static final
    ProjectListItem
        PROJECT_LOT_NUMBER =
        new ProjectListItem("PROJECT_LOT_NUMBER",
                            "Lot",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_LOT,
                            22,
                            "lotNumber",
                            null,
                            EcProjectSearchData.LOT_NUMBER,
                            null);
    public static final
    ProjectListItem
        PROJECT_INSPECTION_FREQUENCY =
        new ProjectListItem("PROJECT_INSPECTION_FREQUENCY",
                            "Inspection Frequency",
                            RequestKeys.EC_PROJECT_LIST_SORT_BY_INSPECTION_FREQUENCY,
                            23,
                            "inspectionFrequency",
                            null,
                            EcProjectSearchData.INSPECTION_FREQUENCY,
                            null);

    static
    {
        projectListItems =
            new HashMap();
        projectListItems.put(PROJECT_NAME.getKey(),
                             PROJECT_NAME);
        projectListItems.put(PROJECT_TYPE.getKey(),
                             PROJECT_TYPE);
        projectListItems.put(PROJECT_ZONE.getKey(),
                             PROJECT_ZONE);
        projectListItems.put(PERMIT_NUMBER.getKey(),
                             PERMIT_NUMBER);
        projectListItems.put(PERMIT_AUTHORITY.getKey(),
                             PERMIT_AUTHORITY);
        projectListItems.put(PERMITTED.getKey(),
                             PERMITTED);
        projectListItems.put(AUTHORIZED_INSPECTOR.getKey(),
                             AUTHORIZED_INSPECTOR);
        projectListItems.put(PROJECT_ADDRESS.getKey(),
                             PROJECT_ADDRESS);
        projectListItems.put(LAST_INSPECTION_DATE.getKey(),
                             LAST_INSPECTION_DATE);
        projectListItems.put(PROJECT_START_DATE.getKey(),
                             PROJECT_START_DATE);
        projectListItems.put(PROJECT_EFFECTIVE_DATE.getKey(),
                             PROJECT_EFFECTIVE_DATE);
        projectListItems.put(PROJECT_SEED_DATE.getKey(),
                             PROJECT_SEED_DATE);
        projectListItems.put(PROJECT_TOTAL_SITE_AREA.getKey(),
                             PROJECT_TOTAL_SITE_AREA);
        projectListItems.put(PROJECT_NEW_IMPERVIOUS_AREA.getKey(),
                             PROJECT_NEW_IMPERVIOUS_AREA);
        projectListItems.put(PROJECT_DISTURBED_AREA.getKey(),
                             PROJECT_DISTURBED_AREA);
        projectListItems.put(PROJECT_STATUS.getKey(),
                             PROJECT_STATUS);
        projectListItems.put(PROJECT_CITY.getKey(),
                             PROJECT_CITY);
        projectListItems.put(PROJECT_STATE.getKey(),
                             PROJECT_STATE);
        projectListItems.put(PROJECT_ZIP.getKey(),
                             PROJECT_ZIP);
        projectListItems.put(PROJECT_RFA_NUMBER.getKey(),
                             PROJECT_RFA_NUMBER);
        projectListItems.put(PROJECT_BLOCK_NUMBER.getKey(),
                             PROJECT_BLOCK_NUMBER);
        projectListItems.put(PROJECT_LOT_NUMBER.getKey(),
                             PROJECT_LOT_NUMBER);
        projectListItems.put(PROJECT_INSPECTION_FREQUENCY.getKey(),
                             PROJECT_INSPECTION_FREQUENCY);
    }

    protected ProjectListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String columnName, String image)
    {
        this.key =
            key;
        this.name =
            name;
        this.sortQueryKey =
            sortQueryKey;
        this.sortOrder =
            sortOrder;
        this.property =
            property;
        this.property2 =
            property2;
        this.image =
            image;
        this.columnName =
            columnName;
    }

    public static ProjectListItem getProjectListItem(String key)
    {
        if (projectListItems
            != null)
        {
            return (ProjectListItem) projectListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (projectListItems
            != null)
        {
            return new TreeSet(projectListItems.values());
        }
        return null;
    }

    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getName()
    {
        return name;
    }

    public String getSortQueryKey()
    {
        return sortQueryKey;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public String getProperty()
    {
        return property;
    }

    public String getProperty2()
    {
        return property2;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public String getImage()
    {
        return image;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof ProjectListItem)
        {
            ProjectListItem
                projectListItem =
                (ProjectListItem) obj;
            return getSortOrder().compareTo(projectListItem.getSortOrder());
        }
        return 0;
    }
}
