package com.sehinc.environment.action.scc;

import com.sehinc.environment.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class SccListItem
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
    SccListItem
        SCC_NUMBER =
        new SccListItem("SCC_NUMBER",
                        "Number",
                        RequestKeys.SCC_LIST_SORT_BY_SCC_NUMBER,
                        1,
                        "number",
                        null,
                        null);
    public static final
    SccListItem
        SCC_DESCRIPTION =
        new SccListItem("SCC_DESCRIPTION",
                        "Description",
                        RequestKeys.SCC_LIST_SORT_BY_SCC_DESCRIPTION,
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

    protected SccListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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

    public static SccListItem getSccListItem(String key)
    {
        if (sccListItems
            != null)
        {
            return (SccListItem) sccListItems.get(key);
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
        if (obj instanceof SccListItem)
        {
            SccListItem
                sccListItem =
                (SccListItem) obj;
            return getSortOrder().compareTo(sccListItem.getSortOrder());
        }
        return 0;
    }
}


