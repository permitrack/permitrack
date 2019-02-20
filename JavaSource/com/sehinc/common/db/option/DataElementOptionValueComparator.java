package com.sehinc.common.db.option;

import java.util.Comparator;

public class DataElementOptionValueComparator
    implements Comparator
{
    public DataElementOptionValueComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if (obj1
            != null
            && obj2
               != null)
        {
            if (obj1 instanceof DataElementOptionValue
                && obj2 instanceof DataElementOptionValue)
            {
                return ((DataElementOptionValue) obj1).getSequence()
                    .compareTo(((DataElementOptionValue) obj2).getSequence());
            }
        }
        return 0;
    }
}
