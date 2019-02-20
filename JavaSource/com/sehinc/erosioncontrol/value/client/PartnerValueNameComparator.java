package com.sehinc.erosioncontrol.value.client;

import org.apache.log4j.Logger;

import java.util.Comparator;

public class PartnerValueNameComparator
    implements Comparator
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerValueNameComparator.class);

    public PartnerValueNameComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if (obj1
            != null
            && obj2
               != null)
        {
            if (obj1 instanceof PartnerValue
                && obj2 instanceof PartnerValue)
            {
                PartnerValue
                    p1 =
                    (PartnerValue) obj1;
                PartnerValue
                    p2 =
                    (PartnerValue) obj2;
                if (p1.getName()
                    .toUpperCase()
                    .equals(p2.getName()
                                .toUpperCase()))
                {
                    return p1.getId()
                        .compareTo(p2.getId());
                }
                else
                {
                    return p1.getName()
                        .toUpperCase()
                        .compareTo(p2.getName()
                                       .toUpperCase());
                }
            }
        }
        return 0;
    }
}
