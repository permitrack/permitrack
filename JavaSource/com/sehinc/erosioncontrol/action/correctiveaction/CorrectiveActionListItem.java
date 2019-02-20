package com.sehinc.erosioncontrol.action.correctiveaction;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.correctiveaction.EcCorrectiveAction;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class CorrectiveActionListItem
    implements Comparable
{
    private final
    String
        key;
    private final
    String
        name;
    private final
    String
        sortQueryKey;
    private final
    Integer
        sortOrder;
    private final
    String
        property;
    private final
    String
        property2;
    private static final
    HashMap
        correctiveActionListItems;
    private final
    String
        columnName;
    public static final
    CorrectiveActionListItem
        PROJECT_NAME =
        new CorrectiveActionListItem("PROJECT_NAME",
                                     "Project Name",
                                     RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_PROJECT_NAME,
                                     1,
                                     "projectName",
                                     null,
                                     EcCorrectiveAction.PROJECT_NAME);
    public static final
    CorrectiveActionListItem
        LAST_INSPECTION_DATE =
        new CorrectiveActionListItem("LAST_INSPECTION_DATE",
                                     "Inspection Date",
                                     RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_LAST_INSPECTION_DATE,
                                     2,
                                     "lastInspectionDate",
                                     null,
                                     EcCorrectiveAction.LAST_INSPECTION_DATE);
    public static final
    CorrectiveActionListItem
        BMP_NAME =
        new CorrectiveActionListItem("BMP_NAME",
                                     "BMP Name",
                                     RequestKeys.EC_CORRECTIVE_ACTION_LIST_SORT_BY_BMP_NAME,
                                     3,
                                     "bmpName",
                                     null,
                                     EcCorrectiveAction.BMP_NAME);

    static
    {
        correctiveActionListItems =
            new HashMap();
        correctiveActionListItems.put(BMP_NAME.getKey(),
                                      BMP_NAME);
        correctiveActionListItems.put(PROJECT_NAME.getKey(),
                                      PROJECT_NAME);
        correctiveActionListItems.put(LAST_INSPECTION_DATE.getKey(),
                                      LAST_INSPECTION_DATE);
    }

    protected CorrectiveActionListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String columnName)
    {
        this.key =
            key;
        this.name =
            name;
        this.sortQueryKey =
            sortQueryKey;
        this.sortOrder =
            sortOrder;
        this.property =
            property;
        this.property2 =
            property2;
        this.columnName =
            columnName;
    }

    public static CorrectiveActionListItem getCorrectiveActionListItem(String key)
    {
        if (correctiveActionListItems
            != null)
        {
            return (CorrectiveActionListItem) correctiveActionListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (correctiveActionListItems
            != null)
        {
            return new TreeSet(correctiveActionListItems.values());
        }
        return null;
    }

    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getName()
    {
        return name;
    }

    public String getSortQueryKey()
    {
        return sortQueryKey;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public String getProperty()
    {
        return property;
    }

    public String getProperty2()
    {
        return property2;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof CorrectiveActionListItem)
        {
            CorrectiveActionListItem
                correctiveActionListItem =
                (CorrectiveActionListItem) obj;
            return getSortOrder().compareTo(correctiveActionListItem.getSortOrder());
        }
        return 0;
    }
}