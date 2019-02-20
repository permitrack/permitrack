package com.sehinc.environment.action.asset;

import com.sehinc.environment.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class AssetListItem
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
        assetListItems;
    private final
    String
        image;
    public static final
    AssetListItem
        ASSET_NUMBER =
        new AssetListItem("ASSET_NUMBER",
                          "Number",
                          RequestKeys.ASSET_LIST_SORT_BY_ASSET_NUMBER,
                          1,
                          "number",
                          null,
                          null);
    public static final
    AssetListItem
        ASSET_NAME =
        new AssetListItem("ASSET_NAME",
                          "Name",
                          RequestKeys.ASSET_LIST_SORT_BY_ASSET_NAME,
                          2,
                          "name",
                          null,
                          null);
    public static final
    AssetListItem
        ASSET_TYPE =
        new AssetListItem("ASSET_TYPE",
                          "Type",
                          RequestKeys.ASSET_LIST_SORT_BY_TYPE,
                          3,
                          "assetTypes",
                          null,
                          null);
    public static final
    AssetListItem
        METER =
        new AssetListItem("METER",
                          "Meter",
                          RequestKeys.ASSET_LIST_SORT_BY_METER,
                          4,
                          "meterNumberName",
                          null,
                          null);

    static
    {
        assetListItems =
            new HashMap();
        assetListItems.put(ASSET_NUMBER.getKey(),
                           ASSET_NUMBER);
        assetListItems.put(ASSET_NAME.getKey(),
                           ASSET_NAME);
        assetListItems.put(ASSET_TYPE.getKey(),
                           ASSET_TYPE);
        assetListItems.put(METER.getKey(),
                           METER);
    }

    protected AssetListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String image)
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

    public static AssetListItem getAssetListItem(String key)
    {
        if (assetListItems
            != null)
        {
            return (AssetListItem) assetListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (assetListItems
            != null)
        {
            return new TreeSet(assetListItems.values());
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
        if (obj instanceof AssetListItem)
        {
            AssetListItem
                assetListItem =
                (AssetListItem) obj;
            return getSortOrder().compareTo(assetListItem.getSortOrder());
        }
        return 0;
    }
}

