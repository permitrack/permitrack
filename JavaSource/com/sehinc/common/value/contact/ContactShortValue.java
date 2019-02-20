package com.sehinc.common.value.contact;

import com.sehinc.common.db.contact.CapContact;

public class ContactShortValue
    implements java.io.Serializable
{
    private
    Integer
        i =
        new Integer(0);
    private
    String
        s =
        new String("");

    private
    Integer
        id =
        i;
    private
    String
        firstName =
        s;
    private
    String
        lastName =
        s;
    private
    String
        email =
        s;
    private
    String
        organizationName =
        s;

    public ContactShortValue()
    {
    }

    public ContactShortValue(CapContact contact)
    {
        this.id =
            contact.getId();
        this.firstName =
            contact.getFirstName();
        this.lastName =
            contact.getLastName();
        this.email =
            contact.getEmail();
        this.organizationName =
            contact.getOrganizationName();
    }

    public ContactShortValue(Integer intContactId)
        throws Exception
    {
        try
        {
            CapContact
                contact =
                new CapContact(intContactId);
            if (!contact.load())
            {
                throw new Exception("ContactId "
                                    + intContactId
                                    + " does not exist withing the CapContact table.");
            }
            this.id =
                contact.getId();
            this.firstName =
                contact.getFirstName();
            this.lastName =
                contact.getLastName();
            this.email =
                contact.getEmail();
            this.organizationName =
                contact.getOrganizationName();
        }
        catch (Exception e)
        {
            throw new Exception("com.sehinc.common.value.contact.ContactShortValue("
                                + intContactId.toString()
                                + ")  Unable to get ContactShortValue from contactId <br>Message:<br>"
                                + e.getMessage());
        }
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
    }

    public String getOrganizationName()
    {
        return this.organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName =
            organizationName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName =
            lastName;
    }

    public String getFullName()
    {
        String
            fullName =
            this.lastName
            + ", "
            + this.firstName;
        return fullName;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email =
            email;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ContactShortValue)
        {
            ContactShortValue
                other =
                (ContactShortValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
