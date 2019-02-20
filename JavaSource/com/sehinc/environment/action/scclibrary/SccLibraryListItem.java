package com.sehinc.environment.action.scclibrary;

import com.sehinc.environment.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class SccLibraryListItem
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
    private static final
    HashMap
        sccListItems;
    private final
    String
        image;
    public static final
    SccLibraryListItem
        SCC_NUMBER =
        new SccLibraryListItem("SCC_NUMBER",
                               "Number",
                               RequestKeys.SCC_LIBRARY_LIST_SORT_BY_SCC_NUMBER,
                               1,
                               "number",
                               null,
                               null);
    public static final
    SccLibraryListItem
        SCC_DESCRIPTION =
        new SccLibraryListItem("SCC_DESCRIPTION",
                               "Description",
                               RequestKeys.SCC_LIBRARY_LIST_SORT_BY_SCC_DESCRIPTION,
                               2,
                               "descriptionAndName",
                               null,
                               null);

    static
    {
        sccListItems =
            new HashMap();
        sccListItems.put(SCC_NUMBER.getKey(),
                         SCC_NUMBER);
        sccListItems.put(SCC_DESCRIPTION.getKey(),
                         SCC_DESCRIPTION);
    }

    protected SccLibraryListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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
        this.image =
            image;
    }

    public static SccLibraryListItem getSccListItem(String key)
    {
        if (sccListItems
            != null)
        {
            return (SccLibraryListItem) sccListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (sccListItems
            != null)
        {
            return new TreeSet(sccListItems.values());
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

    public String getImage()
    {
        return this.image;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof SccLibraryListItem)
        {
            SccLibraryListItem
                sccListItem =
                (SccLibraryListItem) obj;
            return getSortOrder().compareTo(sccListItem.getSortOrder());
        }
        return 0;
    }
}


