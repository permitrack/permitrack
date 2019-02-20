package com.sehinc.common.value.contact;

import java.util.Comparator;

public class ContactValueComparator
    implements Comparator
{
    public ContactValueComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if (obj1
            != null
            && obj2
               != null)
        {
            if (obj1 instanceof ContactValue
                && obj2 instanceof ContactValue)
            {
                ContactValue
                    c1 =
                    (ContactValue) obj1;
                ContactValue
                    c2 =
                    (ContactValue) obj2;
                if (c1.getLastName()
                    != null)
                {
                    if (c1.getLastName()
                        .equalsIgnoreCase(c2.getLastName()))
                    {
                        if (c1.getFirstName()
                            .equalsIgnoreCase(c2.getFirstName()))
                        {
                            return c1.getId()
                                .compareTo(c2.getId());
                        }
                        else
                        {
                            return c1.getFirstName()
                                .toUpperCase()
                                .compareTo(c2.getFirstName()
                                               .toUpperCase());
                        }
                    }
                    else
                    {
                        return c1.getLastName()
                            .toUpperCase()
                            .compareTo(c2.getLastName()
                                           .toUpperCase());
                    }
                }
                else
                {
                    return c1.getId()
                        .compareTo(c2.getId());
                }
            }
        }
        return 0;
    }
}
