package com.sehinc.erosioncontrol.action.project;

import com.sehinc.erosioncontrol.value.project.ProjectContactValue;
import org.apache.log4j.Logger;

import java.util.Comparator;

public class ProjectContactValueComparator
    implements Comparator
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectContactValueComparator.class);

    public ProjectContactValueComparator()
    {
    }

    public int compare(Object obj1, Object obj2)
    {
        if (obj1
            != null
            && obj2
               != null)
        {
            if (obj1 instanceof ProjectContactValue
                && obj2 instanceof ProjectContactValue)
            {
                ProjectContactValue
                    p1 =
                    (ProjectContactValue) obj1;
                ProjectContactValue
                    p2 =
                    (ProjectContactValue) obj2;
                if (p1.getContactTypeSequence()
                    .equals(p2.getContactTypeSequence()))
                {
                    if (p1.getLastName()
                        .toUpperCase()
                        .equals(p2.getLastName()
                                    .toUpperCase()))
                    {
                        return p1.getId()
                            .compareTo(p2.getId());
                    }
                    else
                    {
                        return p1.getLastName()
                            .toUpperCase()
                            .compareTo(p2.getLastName()
                                           .toUpperCase());
                    }
                }
                else
                {
                    return p1.getContactTypeSequence()
                        .compareTo(p2.getContactTypeSequence());
                }
            }
        }
        return 0;
    }
}
