package com.sehinc.erosioncontrol.util;

import com.sehinc.erosioncontrol.db.code.CodeData;

import java.util.Comparator;

public class CodeComparator
    implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof CodeData
            && o2 instanceof CodeData)
        {
            Integer
                codeA =
                ((CodeData) o1).getDisplayOrder();
            Integer
                codeB =
                ((CodeData) o2).getDisplayOrder();
            return (codeA.compareTo(codeB));
        }
        return 0;
    }
}
