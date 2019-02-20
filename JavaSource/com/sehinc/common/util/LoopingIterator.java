package com.sehinc.common.util;

import java.util.Iterator;
import java.util.List;

public class LoopingIterator
    implements Iterator
{
    private
    List
        list;
    private
    int
        listSize =
        0;
    private
    int
        index =
        0;

    public LoopingIterator(List list)
    {
        this.list =
            list;
        listSize =
            list.size();
    }

    public boolean hasNext()
    {
        return (listSize
                > 0);
    }

    public Object next()
    {
        Object
            o =
            list.get(index);
        index++;
        if (index
            == listSize)
        {
            index =
                0;
        }
        return o;
    }

    public void remove()
    {
        list.remove(index);
    }
}
