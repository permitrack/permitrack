package com.sehinc.environment.action.substance;

import com.sehinc.environment.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class SubstanceListItem
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
        substanceListItems;
    private final
    String
        image;
    public static final
    SubstanceListItem
        PART_NUMBER =
        new SubstanceListItem("PART_NUMBER",
                              "Cas/Part Number",
                              RequestKeys.SUBSTANCE_LIST_SORT_BY_PART_NUMBER,
                              1,
                              "partNum",
                              null,
                              null);
    public static final
    SubstanceListItem
        SUBSTANCE_NAME =
        new SubstanceListItem("SUBSTANCE_NAME",
                              "Name",
                              RequestKeys.SUBSTANCE_LIST_SORT_BY_SUBSTANCE_NAME,
                              2,
                              "name",
                              null,
                              null);
    public static final
    SubstanceListItem
        SUBSTANCE_TYPE =
        new SubstanceListItem("SUBSTANCE_TYPE",
                              "Type",
                              RequestKeys.SUBSTANCE_LIST_SORT_BY_TYPE,
                              3,
                              "typeDesc",
                              null,
                              null);

    static
    {
        substanceListItems =
            new HashMap();
        substanceListItems.put(PART_NUMBER.getKey(),
                               PART_NUMBER);
        substanceListItems.put(SUBSTANCE_NAME.getKey(),
                               SUBSTANCE_NAME);
        substanceListItems.put(SUBSTANCE_TYPE.getKey(),
                               SUBSTANCE_TYPE);
    }

    protected SubstanceListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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

    public static SubstanceListItem getSubstanceListItem(String key)
    {
        if (substanceListItems
            != null)
        {
            return (SubstanceListItem) substanceListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (substanceListItems
            != null)
        {
            return new TreeSet(substanceListItems.values());
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
        return image;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof SubstanceListItem)
        {
            SubstanceListItem
                substanceListItem =
                (SubstanceListItem) obj;
            return getSortOrder().compareTo(substanceListItem.getSortOrder());
        }
        return 0;
    }
}
