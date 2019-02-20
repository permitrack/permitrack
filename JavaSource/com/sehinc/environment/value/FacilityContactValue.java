package com.sehinc.environment.value;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.environment.db.facility.EnvFacilityContact;

public class FacilityContactValue
    implements java.io.Serializable
{
    private
    Integer
        facilityContactId;
    private
    Integer
        facilityId;
    private
    Integer
        roleCd;
    private
    String
        roleDesc;
    private
    Integer
        contactId;
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

    public FacilityContactValue()
    {
    }

    public FacilityContactValue(EnvFacilityContact fContact)
    {
        this.facilityContactId =
            fContact.getId();
        this.facilityId =
            fContact.getFacilityId();
        this.roleCd =
            fContact.getRoleCd();
        CapContact
            contact =
            new CapContact(fContact.getContactId());
        contact.load();
        this.contactId =
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

    public Integer getFacilityContactId()
    {
        return facilityContactId;
    }

    public void setFacilityContactId(Integer facilityContactId)
    {
        this.facilityContactId =
            facilityContactId;
    }

    public Integer getFacilityId()
    {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId)
    {
        this.facilityId =
            facilityId;
    }

    public Integer getRoleCd()
    {
        return roleCd;
    }

    public void setRoleCd(Integer roleCd)
    {
        this.roleCd =
            roleCd;
    }

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
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

    public Integer getOrgRefClientId()
    {
        return orgRefClientId;
    }

    public void setOrgRefClientId(Integer orgRefClientId)
    {
        this.orgRefClientId =
            orgRefClientId;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title =
            title;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName =
            firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName =
            lastName;
    }

    public Character getMi()
    {
        return mi;
    }

    public void setMi(Character mi)
    {
        this.mi =
            mi;
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
        return address;
    }

    public void setAddress(String address)
    {
        this.address =
            address;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 =
            address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city =
            city;
    }

    public String getStateCode()
    {
        return stateCode;
    }

    public void setStateCode(String stateCode)
    {
        this.stateCode =
            stateCode;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip =
            zip;
    }

    public String getPrimaryPhone()
    {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone)
    {
        this.primaryPhone =
            primaryPhone;
    }

    public String getSecondaryPhone()
    {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone)
    {
        this.secondaryPhone =
            secondaryPhone;
    }

    public String getFaxPhone()
    {
        return faxPhone;
    }

    public void setFaxPhone(String faxPhone)
    {
        this.faxPhone =
            faxPhone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email =
            email;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public String getRoleDesc()
    {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc)
    {
        this.roleDesc =
            roleDesc;
    }

    public boolean equals(Object o)
    {
        if (o instanceof FacilityContactValue)
        {
            FacilityContactValue
                other =
                (FacilityContactValue) o;
            return other.getFacilityContactId()
                .equals(facilityContactId);
        }
        return false;
    }
}