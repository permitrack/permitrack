package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.erosioncontrol.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class InspectionListItem
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
        inspectionListItems;
    private final
    String
        image;
    public static final
    InspectionListItem
        INSPECTION_DATE =
        new InspectionListItem("INSPECTION_DATE",
                               "Inspection Date",
                               RequestKeys.EC_INSPECTION_LIST_SORT_BY_INSPECTION_DATE,
                               new Integer(1),
                               "inspectionDateString",
                               null,
                               null);
    public static final
    InspectionListItem
        INSPECTION_FAILED =
        new InspectionListItem("INSPECTION_FAILED",
                               "BMP Status",
                               null,
                               new Integer(2),
                               "bmpStatus",
                               null,
                               "bmpStatusImage");
    public static final
    InspectionListItem
        INSPECTION_ENTERED_DATE =
        new InspectionListItem("INSPECTION_ENTERED_DATE",
                               "Entered Date",
                               RequestKeys.EC_INSPECTION_LIST_SORT_BY_ENTERED_DATE,
                               new Integer(3),
                               "enteredDateString",
                               null,
                               null);
    public static final
    InspectionListItem
        INSPECTION_INSPECTOR =
        new InspectionListItem("INSPECTION_INSPECTOR",
                               "Inspected By",
                               RequestKeys.EC_INSPECTION_LIST_SORT_BY_INSPECTOR_NAME,
                               new Integer(4),
                               "inspectorNameString",
                               null,
                               null);
    public static final
    InspectionListItem
        INSPECTION_REASON =
        new InspectionListItem("INSPECTION_REASON",
                               "Reason",
                               RequestKeys.EC_INSPECTION_LIST_SORT_BY_REASON,
                               new Integer(5),
                               "inspectionReasonString",
                               null,
                               null);
    public static final
    InspectionListItem
        INSPECTION_STATUS =
        new InspectionListItem("INSPECTION_STATUS",
                               "Status",
                               RequestKeys.EC_INSPECTION_LIST_SORT_BY_STATUS,
                               new Integer(6),
                               "status",
                               null,
                               null);

    static
    {
        inspectionListItems =
            new HashMap();
        inspectionListItems.put(INSPECTION_FAILED.getKey(),
                                INSPECTION_FAILED);
        inspectionListItems.put(INSPECTION_DATE.getKey(),
                                INSPECTION_DATE);
        inspectionListItems.put(INSPECTION_ENTERED_DATE.getKey(),
                                INSPECTION_ENTERED_DATE);
        inspectionListItems.put(INSPECTION_INSPECTOR.getKey(),
                                INSPECTION_INSPECTOR);
        inspectionListItems.put(INSPECTION_REASON.getKey(),
                                INSPECTION_REASON);
        inspectionListItems.put(INSPECTION_STATUS.getKey(),
                                INSPECTION_STATUS);
    }

    protected InspectionListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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
    }

    public static InspectionListItem getInspectionListItem(String key)
    {
        if (inspectionListItems
            != null)
        {
            return (InspectionListItem) inspectionListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (inspectionListItems
            != null)
        {
            return new TreeSet(inspectionListItems.values());
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

    public String getImage()
    {
        return image;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof InspectionListItem)
        {
            InspectionListItem
                inspectionListItem =
                (InspectionListItem) obj;
            return getSortOrder().compareTo(inspectionListItem.getSortOrder());
        }
        return 0;
    }
}
