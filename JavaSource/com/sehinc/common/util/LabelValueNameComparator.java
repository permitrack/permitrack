package com.sehinc.common.util;

import java.util.Comparator;

public class LabelValueNameComparator
    implements Comparator
{
    public LabelValueNameComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if ((obj1 instanceof LabelValueBean)
            && (obj2 instanceof LabelValueBean))
        {
            return ((LabelValueBean) obj1).getLabel()
                .compareTo(((LabelValueBean) obj2).getLabel());
        }
        return 0;
    }

    public boolean equals(Object obj)
    {
        return true;
    }
}
