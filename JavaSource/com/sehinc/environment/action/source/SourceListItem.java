package com.sehinc.environment.action.source;

import com.sehinc.environment.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class SourceListItem
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
        sourceListItems;
    private final
    String
        image;
    public static final
    SourceListItem
        DISPLAY_LABEL =
        new SourceListItem("DISPLAY_LABEL",
                           "Source",
                           RequestKeys.SOURCE_LIST_SORT_BY_DISPLAY_LABEL,
                           1,
                           "displayName",
                           null,
                           "displayColorCd");
    public static final
    SourceListItem
        SOURCE_TYPE =
        new SourceListItem("SOURCE_TYPE",
                           "Type",
                           RequestKeys.SOURCE_LIST_SORT_BY_TYPE,
                           2,
                           "sourceTypeDesc",
                           null,
                           null);

    static
    {
        sourceListItems =
            new HashMap();
        sourceListItems.put(DISPLAY_LABEL.getKey(),
                            DISPLAY_LABEL);
        sourceListItems.put(SOURCE_TYPE.getKey(),
                            SOURCE_TYPE);
    }

    protected SourceListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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

    public static SourceListItem getSourceListItem(String key)
    {
        if (sourceListItems
            != null)
        {
            return (SourceListItem) sourceListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (sourceListItems
            != null)
        {
            return new TreeSet(sourceListItems.values());
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
        return this.image;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof SourceListItem)
        {
            SourceListItem
                sourceListItem =
                (SourceListItem) obj;
            return getSortOrder().compareTo(sourceListItem.getSortOrder());
        }
        return 0;
    }
}
