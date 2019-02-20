package com.sehinc.erosioncontrol.db.event;

import java.util.Comparator;

public class EventDateComparator
    implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof EcEvent
            && o2 instanceof EcEvent)
        {
            return ((EcEvent) o1).getEventDate()
                .compareTo(((EcEvent) o2).getEventDate());
        }
        return 0;
    }
}
