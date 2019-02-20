package com.sehinc.security.action.contact;

import com.sehinc.common.db.contact.CapContact;
import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;

public class ContactListForm
    extends ActionForm
{
    private
    List<CapContact>
        contactList =
        new ArrayList<CapContact>();
    private
    String
        sortMethod =
        null;
    private
    String
        sortColumn =
        null;
    private
    String
        sortDirection =
        null;
    private
    String
        contactName =
        null;
    private
    String
        organizationName =
        null;
    private
    int
        id;
    public static final
    String
        SORT_COLUMN =
        "sortColumn";
    public static final
    String
        SORT_DIRECTION =
        "sortDirection";

    public List<CapContact> getContactList()
    {
        return contactList;
    }

    public void setProjectList(List<CapContact> contactList)
    {
        this.contactList =
            contactList;
    }

    public String getSortMethod()
    {
        return sortMethod;
    }

    public void setSortMethod(String sortMethod)
    {
        this.sortMethod =
            sortMethod;
    }

    public void reset()
    {
        this.contactList
            .clear();
        contactName =
            null;
        organizationName =
            null;
        sortMethod =
            null;
    }

    public String getSortColumn()
    {
        return sortColumn
               == null
            ? CapContact.CONTACT_NAME
            : sortColumn;
    }

    public void setSortColumn(String sortColumn)
    {
        this.sortColumn =
            sortColumn;
    }

    public String getSortDirection()
    {
        return sortDirection
               == null
            ? CapContact.ASCENDING
            : sortDirection;
    }

    public void setSortDirection(String sortDirection)
    {
        this.sortDirection =
            sortDirection;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id =
            id;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName =
            contactName;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName =
            organizationName;
    }
}