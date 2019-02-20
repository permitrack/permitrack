package com.sehinc.common.db.base;

import java.util.Hashtable;

public class DataCache
    extends Object
{
    private
    Hashtable
        cache =
        new Hashtable(100);
    private static
    DataCache
        singleton =
        null;

    public DataCache()
    {
    }

    public static DataCache singleton()
    {
        if (singleton
            == null)
        {
            singleton =
                new DataCache();
        }
        return singleton;
    }

    public Object getCacheForID(String cacheID)
    {
        return cache.get(cacheID);
    }

    public void insertCacheForID(Object newCache, String id)
    {
        cache.put(id,
                  newCache);
    }
}

