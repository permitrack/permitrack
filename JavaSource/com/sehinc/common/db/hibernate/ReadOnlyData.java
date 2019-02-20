package com.sehinc.common.db.hibernate;

import com.sehinc.common.db.base.DataCache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadOnlyData
    extends HibernateData
{
    public int delete()
    {
        throw new RuntimeException("delete not allowed for ReadOnlyData");
    }

    public Integer insert()
    {
        throw new RuntimeException("insert not allowed for ReadOnlyData");
    }

    public Integer save()
    {
        throw new RuntimeException("save not allowed for ReadOnlyData");
    }

    public void update()
    {
        throw new RuntimeException("update not allowed for ReadOnlyData");
    }

    protected List getCache()
    {
        String
            cacheKey =
            getCacheKey();
        List
            cache =
            (List) DataCache.singleton()
                .getCacheForID(cacheKey);
        if (cache
            == null
            || cache.isEmpty())
        {
            cache =
                new ArrayList();
        }
        if (cache.isEmpty())
        {
            List
                list =
                findAll(this.getClass());
            Iterator
                iter =
                list.iterator();
            while (iter.hasNext())
            {
                ReadOnlyData
                    dataElement =
                    (ReadOnlyData) iter.next();
                cache.add(dataElement);
            }
            setCache(cache,
                     cacheKey);
        }
        return cache;
    }

    private String getCacheKey()
    {
        return getClass().getName();
    }

    public List retrieveAll()
    {
        return getCache();
    }

    protected void setCache(Object newCache, String cacheKey)
    {
        DataCache.singleton()
            .insertCacheForID(newCache,
                              cacheKey);
    }
}
