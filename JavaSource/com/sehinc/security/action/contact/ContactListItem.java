package com.sehinc.security.action.contact;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.erosioncontrol.action.base.RequestKeys;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class ContactListItem
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
        contactListItems;
    private final
    String
        columnName;
    public static final
    ContactListItem
        CONTACT_NAME =
        new ContactListItem("CONTACT_NAME",
                            "Name",
                            RequestKeys.CONTACT_LIST_SORT_BY_FULL_NAME,
                            1,
                            "contactName",
                            null,
                            CapContact.CONTACT_NAME);
    public static final
    ContactListItem
        ORGANIZATION_NAME =
        new ContactListItem("ORGANIZATION_NAME",
                            "Organization",
                            RequestKeys.CONTACT_LIST_SORT_BY_ORGANIZATION_NAME,
                            2,
                            "organizationName",
                            null,
                            CapContact.ORGANIZATION_NAME);

    static
    {
        contactListItems =
            new HashMap();
        contactListItems.put(CONTACT_NAME.getKey(),
                             CONTACT_NAME);
        contactListItems.put(ORGANIZATION_NAME.getKey(),
                             ORGANIZATION_NAME);
    }

    protected ContactListItem(String key, String name, String sortQueryKey, Integer sortOrder, String property, String property2, String columnName)
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

    public static ContactListItem getContactListItem(String key)
    {
        if (contactListItems
            != null)
        {
            return (ContactListItem) contactListItems.get(key);
        }
        return null;
    }

    public static Collection getSortedList()
    {
        if (contactListItems
            != null)
        {
            return new TreeSet(contactListItems.values());
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
        if (obj instanceof ContactListItem)
        {
            ContactListItem
                contactListItem =
                (ContactListItem) obj;
            return getSortOrder().compareTo(contactListItem.getSortOrder());
        }
        return 0;
    }
}