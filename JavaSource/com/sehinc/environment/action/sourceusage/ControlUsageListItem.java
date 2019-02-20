package com.sehinc.environment.action.sourceusage;

import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.value.SourceUsageValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class ControlUsageListItem
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
        sourceUsageListItems;
    private final
    String
        image;
    public static final
    ControlUsageListItem
        SOURCE_NAME =
        new ControlUsageListItem("SOURCE_NAME",
                                 "Control",
                                 RequestKeys.SOURCE_USAGE_LIST_SORT_BY_SOURCE,
                                 1,
                                 "sourceName",
                                 null,
                                 null);
    public static final
    ControlUsageListItem
        USAGE_START_DATE =
        new ControlUsageListItem("USAGE_START_DATE",
                                 "Start Date",
                                 RequestKeys.SOURCE_USAGE_LIST_SORT_BY_START_DATE,
                                 3,
                                 "periodStartTsString",
                                 null,
                                 null);
    public static final
    ControlUsageListItem
        USAGE_END_DATE =
        new ControlUsageListItem("USAGE_END_DATE",
                                 "End Date",
                                 RequestKeys.SOURCE_USAGE_LIST_SORT_BY_END_DATE,
                                 4,
                                 "periodEndTsString",
                                 null,
                                 null);

    static
    {
        sourceUsageListItems =
            new HashMap();
        sourceUsageListItems.put(SOURCE_NAME.getKey(),
                                 SOURCE_NAME);
        sourceUsageListItems.put(USAGE_START_DATE.getKey(),
                                 USAGE_START_DATE);
        sourceUsageListItems.put(USAGE_END_DATE.getKey(),
                                 USAGE_END_DATE);
    }

    private
    String
        sortColumn =
        null;
    private
    String
        sortDirection =
        null;
    public static final
    String
        SORT_COLUMN =
        "sortColumn";
    public static final
    String
        SORT_DIRECTION =
        "sortDirection";

    public String getSortColumn()
    {
        return sortColumn
               == null
            ? SourceUsageValue.SOURCE_USAGE_NAME
            : sortColumn;
    }

    public void setSortColumn(String sortColumn)
    {
        this.sortColumn =
            sortColumn;
    }

    public String getSortDirection()
    {
        return sortDirection
               == null
            ? SourceUsageValue.ASCENDING
            : sortDirection;
    }

    public void setSortDirection(String sortDirection)
    {
        this.sortDirection =
            sortDirection;
    }

    protected ControlUsageListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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

    public static ControlUsageListItem getSourceUsageListItem(String key)
    {
        if (sourceUsageListItems
            != null)
        {
            return (ControlUsageListItem) sourceUsageListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (sourceUsageListItems
            != null)
        {
            return new TreeSet(sourceUsageListItems.values());
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
        if (obj instanceof ControlUsageListItem)
        {
            ControlUsageListItem
                sourceUsageListItem =
                (ControlUsageListItem) obj;
            return getSortOrder().compareTo(sourceUsageListItem.getSortOrder());
        }
        return 0;
    }
}