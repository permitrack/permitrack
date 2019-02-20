package com.sehinc.common.value.contact;

import com.sehinc.common.db.contact.CapContact;

public class ContactValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        organizationId;
    private
    Integer
        orgRefClientId;
    private
    String
        organizationName;
    private
    String
        title;
    private
    String
        firstName;
    private
    String
        lastName;
    private
    Character
        mi;
    private
    Integer
        addressId;
    private
    String
        address;
    private
    String
        address2;
    private
    String
        city;
    private
    String
        stateCode;
    private
    String
        zip;
    private
    String
        primaryPhone;
    private
    String
        secondaryPhone;
    private
    String
        faxPhone;
    private
    String
        email;
    private
    String
        statusCode;
    private
    Integer
        i =
        new Integer(0);
    private
    String
        s =
        new String("");
    private
    Character
        c =
        new Character(new String(" ").charAt(0));

    public ContactValue()
    {
    }

    public ContactValue(CapContact contact)
    {
        this.id =
            contact.getId();
        this.firstName =
            contact.getFirstName();
        this.lastName =
            contact.getLastName();
        this.mi =
            contact.getMi();
        this.primaryPhone =
            contact.getPrimaryPhone();
        this.secondaryPhone =
            contact.getSecondaryPhone();
        this.faxPhone =
            contact.getFaxPhone();
        this.email =
            contact.getEmail();
        if (contact.getOrganization()
            != null)
        {
            this.organizationId =
                contact.getOrganization()
                    .getId();
            this.orgRefClientId =
                contact.getOrganization()
                    .getRefClientId();
            this.organizationName =
                contact.getOrganization()
                    .getName();
        }
        if (contact.getAddressData()
            != null)
        {
            this.addressId =
                contact.getAddressData()
                    .getId();
            this.address =
                contact.getAddressData()
                    .getLine1();
            this.address2 =
                contact.getAddressData()
                    .getLine2();
            this.city =
                contact.getAddressData()
                    .getCity();
            this.stateCode =
                contact.getAddressData()
                    .getState();
            this.zip =
                contact.getAddressData()
                    .getPostalCode();
        }
        this.statusCode =
            contact.getStatus()
                .getCode();
    }

    public ContactValue(Integer intContactId)
        throws Exception
    {
        String
            strLog =
            new String("ContactValue(intContactId)");
        try
        {
            CapContact
                contact =
                new CapContact(intContactId);
            if (!contact.load())
            {
                throw new Exception("ContactId "
                                    + intContactId
                                    + " does not exist within the CapContact table.");
            }
            this.firstName =
                contact.getFirstName();
            this.lastName =
                contact.getLastName();
            this.mi =
                contact.getMi();
            this.title =
                contact.getTitle();
            this.email =
                contact.getEmail();
            this.primaryPhone =
                contact.getPrimaryPhone();
            this.secondaryPhone =
                contact.getSecondaryPhone();
            this.faxPhone =
                contact.getFaxPhone();
            this.statusCode =
                contact.getStatus()
                    .getCode();
            if (contact.getOrganization()
                != null)
            {
                this.organizationId =
                    contact.getOrganization()
                        .getId();
                this.orgRefClientId =
                    contact.getOrganization()
                        .getRefClientId();
                this.organizationName =
                    contact.getOrganization()
                        .getName();
            }
            if (contact.getAddressData()
                != null)
            {
                this.addressId =
                    contact.getAddressData()
                        .getId();
                this.address =
                    contact.getAddressData()
                        .getLine1();
                this.address2 =
                    contact.getAddressData()
                        .getLine2();
                this.city =
                    contact.getAddressData()
                        .getCity();
                this.stateCode =
                    contact.getAddressData()
                        .getState();
                this.zip =
                    contact.getAddressData()
                        .getPostalCode();
            }
        }
        catch (Exception e)
        {
            throw new Exception(strLog
                                + "Error creating contact value given the contact id ["
                                + intContactId.toString()
                                + "].  <br>Message:<br>"
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

    public Integer getOrganizationId()
    {
        return this.organizationId;
    }

    public void setOrganizationId(Integer organizationId)
    {
        this.organizationId =
            organizationId;
    }

    public Integer getOrgRefClientId()
    {
        return this.orgRefClientId;
    }

    public void setOrgRefClientId(Integer orgRefClientId)
    {
        this.orgRefClientId =
            orgRefClientId;
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

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
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

    public Character getMi()
    {
        return this.mi;
    }

    public void setMi(Character mi)
    {
        this.mi =
            mi;
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId =
            addressId;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address =
            address;
    }

    public String getAddress2()
    {
        return this.address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 =
            address2;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city =
            city;
    }

    public String getStateCode()
    {
        return this.stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode =
            stateCode;
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setZip(String zip)
    {
        this.zip =
            zip;
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

    public String getPrimaryPhone()
    {
        return this.primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone)
    {
        this.primaryPhone =
            primaryPhone;
    }

    public String getSecondaryPhone()
    {
        return this.secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone)
    {
        this.secondaryPhone =
            secondaryPhone;
    }

    public String getFaxPhone()
    {
        return this.faxPhone;
    }

    public void setFaxPhone(String faxPhone)
    {
        this.faxPhone =
            faxPhone;
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public boolean equals(Object o)
    {
        if (o instanceof ContactValue)
        {
            ContactValue
                other =
                (ContactValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }

    public String toString()
    {
        StringBuffer
            output =
            new StringBuffer();
        output.append("organizationId="
                      + this.organizationId
                      + "\n");
        output.append("orgRefClientId="
                      + this.orgRefClientId
                      + "\n");
        output.append("organizationName="
                      + this.organizationName
                      + "\n");
        return output.toString();
    }

    public void reset()
    {
        this.id =
            i;
        this.organizationId =
            i;
        this.organizationName =
            s;
        this.firstName =
            s;
        this.lastName =
            s;
        this.mi =
            c;
        this.title =
            s;
        this.addressId =
            i;
        this.address =
            s;
        this.address2 =
            s;
        this.city =
            s;
        this.stateCode =
            s;
        this.zip =
            s;
        this.primaryPhone =
            s;
        this.secondaryPhone =
            s;
        this.faxPhone =
            s;
        this.email =
            s;
        this.statusCode =
            s;
    }

    public void DefaultNullValues()
    {
        if (id
            == null)
        {
            id =
                i;
        }
        if (organizationId
            == null)
        {
            organizationId =
                i;
        }
        if (organizationName
            == null)
        {
            organizationName =
                s;
        }
        if (firstName
            == null)
        {
            firstName =
                s;
        }
        if (lastName
            == null)
        {
            lastName =
                s;
        }
        if (title
            == null)
        {
            title =
                s;
        }
        if (addressId
            == null)
        {
            mi =
                c;
        }
        if (addressId
            == null)
        {
            addressId =
                i;
        }
        if (address
            == null)
        {
            address =
                s;
        }
        if (address2
            == null)
        {
            address2 =
                s;
        }
        if (city
            == null)
        {
            city =
                s;
        }
        if (stateCode
            == null)
        {
            stateCode =
                s;
        }
        if (zip
            == null)
        {
            zip =
                s;
        }
        if (primaryPhone
            == null)
        {
            primaryPhone =
                s;
        }
        if (secondaryPhone
            == null)
        {
            secondaryPhone =
                s;
        }
        if (faxPhone
            == null)
        {
            faxPhone =
                s;
        }
        if (email
            == null)
        {
            email =
                s;
        }
        if (statusCode
            == null)
        {
            statusCode =
                s;
        }
    }
}
