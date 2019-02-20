package com.sehinc.common.db.security;

import org.apache.log4j.Logger;

import java.util.Comparator;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class CapModuleComparator
    implements Comparator
{
    private static
    Logger
        LOG =
        Logger.getLogger(CapModuleComparator.class);

    public CapModuleComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if (obj1
            != null
            && obj2
               != null)
        {
            if (obj1 instanceof CapModule
                && obj2 instanceof CapModule)
            {
                CapModule
                    c1 =
                    (CapModule) obj1;
                CapModule
                    c2 =
                    (CapModule) obj2;
                if (c1.getOrderNum()
                    .equals(c2.getOrderNum()))
                {
                    return c1.getName()
                        .toUpperCase()
                        .compareTo(c2.getName()
                                       .toUpperCase());
                }
                else
                {
                    return c1.getOrderNum()
                        .compareTo(c2.getOrderNum());
                }
            }
        }
        return 0;
    }
}
