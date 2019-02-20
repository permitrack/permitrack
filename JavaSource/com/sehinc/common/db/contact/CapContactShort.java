package com.sehinc.common.db.contact;

import com.sehinc.common.db.user.StatusData;

public class CapContactShort
    extends StatusData
{
    private
    Integer
        organizationId;
    private
    String
        organizationName;
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
    String
        title;
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

    public CapContactShort()
    {
    }

    public CapContactShort(Integer id)
    {
        setId(id);
    }

    public Integer getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId)
    {
        this.organizationId =
            organizationId;
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

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title =
            title;
    }

    public Integer getAddressId()
    {
        return addressId;
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
}