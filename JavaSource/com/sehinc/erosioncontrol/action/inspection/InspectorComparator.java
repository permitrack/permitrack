package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.db.contact.CapContact;

import java.util.Comparator;

public class InspectorComparator
    implements Comparator
{
    public int compare(Object o1, Object o2)
    {
        if (o1 instanceof CapContact
            && o2 instanceof CapContact)
        {
            return ((CapContact) o1).getLastName()
                .compareTo(((CapContact) o2).getLastName());
        }
        return 0;
    }
}
