package com.sehinc.erosioncontrol.value.project;

import com.sehinc.common.util.StringUtil;
import com.sehinc.erosioncontrol.db.project.EcProjectContact;

public class ProjectContactValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        projectId;
    private
    Integer
        contactId;
    private
    Integer
        contactTypeId;
    private
    String
        contactTypeName;
    private
    String
        contactTypeCode;
    private
    Integer
        contactTypeSequence;
    private
    boolean
        contactTypeIsInternal;
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
    boolean
        isDeleted =
        false;

    public ProjectContactValue()
    {
    }

    public ProjectContactValue(EcProjectContact projectContact)
    {
        this.id =
            projectContact.getId();
        this.projectId =
            projectContact.getProjectId();
        if (projectContact.getContactType()
            != null)
        {
            this.contactTypeId =
                projectContact.getContactType()
                    .getId();
            this.contactTypeName =
                projectContact.getContactType()
                    .getName();
            this.contactTypeCode =
                projectContact.getContactType()
                    .getCode();
            this.contactTypeSequence =
                projectContact.getContactType()
                    .getSequence();
            this.contactTypeIsInternal =
                projectContact.getContactType()
                    .getIsInternal();
        }
        if (projectContact.getContact()
            != null)
        {
            this.contactId =
                projectContact.getContact()
                    .getId();
            this.firstName =
                projectContact.getContact()
                    .getFirstName();
            this.lastName =
                projectContact.getContact()
                    .getLastName();
            this.mi =
                projectContact.getContact()
                    .getMi();
            this.primaryPhone =
                projectContact.getContact()
                    .getPrimaryPhone();
            this.secondaryPhone =
                projectContact.getContact()
                    .getSecondaryPhone();
            this.faxPhone =
                projectContact.getContact()
                    .getFaxPhone();
            this.email =
                projectContact.getContact()
                    .getEmail();
            this.statusCode =
                projectContact.getContact()
                    .getStatus()
                    .getCode();
            if (projectContact.getContact()
                    .getOrganization()
                != null)
            {
                this.organizationId =
                    projectContact.getContact()
                        .getOrganization()
                        .getId();
                this.orgRefClientId =
                    projectContact.getContact()
                        .getOrganization()
                        .getRefClientId();
                this.organizationName =
                    projectContact.getContact()
                        .getOrganization()
                        .getName();
            }
            else
            {
                this.organizationName =
                    projectContact.getContact()
                        .getOrganizationName();
            }
            if (projectContact.getContact()
                    .getAddressData()
                != null)
            {
                this.addressId =
                    projectContact.getContact()
                        .getAddressData()
                        .getId();
                this.address =
                    projectContact.getContact()
                        .getAddressData()
                        .getLine1();
                this.address2 =
                    projectContact.getContact()
                        .getAddressData()
                        .getLine2();
                this.city =
                    projectContact.getContact()
                        .getAddressData()
                        .getCity();
                this.stateCode =
                    projectContact.getContact()
                        .getAddressData()
                        .getState();
                this.zip =
                    projectContact.getContact()
                        .getAddressData()
                        .getPostalCode();
            }
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

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getContactId()
    {
        return this.contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
    }

    public Integer getContactTypeId()
    {
        return this.contactTypeId;
    }

    public void setContactTypeId(Integer contactTypeId)
    {
        this.contactTypeId =
            contactTypeId;
    }

    public String getContactTypeName()
    {
        return this.contactTypeName;
    }

    public void setContactTypeName(String contactTypeName)
    {
        this.contactTypeName =
            contactTypeName;
    }

    public String getContactTypeCode()
    {
        return this.contactTypeCode;
    }

    public void setContactTypeCode(String contactTypeCode)
    {
        this.contactTypeCode =
            contactTypeCode;
    }

    public boolean getContactTypeIsInternal()
    {
        return this.contactTypeIsInternal;
    }

    public void setContactTypeIsInternal(boolean contactTypeIsInternal)
    {
        this.contactTypeIsInternal =
            contactTypeIsInternal;
    }

    public String getContactTypeIsInternalText()
    {
        if (contactTypeIsInternal)
        {
            return "true";
        }
        return "false";
    }

    public void setContactTypeIsInternalText(String contactTypeIsInternalText)
    {
        if (contactTypeIsInternalText
            != null
            && contactTypeIsInternalText.equalsIgnoreCase("true"))
        {
            this.contactTypeIsInternal =
                true;
        }
        this.contactTypeIsInternal =
            false;
    }

    public Integer getContactTypeSequence()
    {
        return this.contactTypeSequence;
    }

    public void setContactTypeSequence(Integer contactTypeSequence)
    {
        this.contactTypeSequence =
            contactTypeSequence;
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

    public String getViewAddress()
    {
        return this.address
               + (StringUtil.isEmpty(this.city)
                      ? ""
                      : ", "
                        + this.city)
               + (StringUtil.isEmpty(this.stateCode)
                      ? ""
                      : " "
                        + this.stateCode)
               + (StringUtil.isEmpty(this.zip)
                      ? ""
                      : " "
                        + this.zip);
    }

    public void setViewAddress(String address)
    {
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

    public boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(boolean isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public String getIsDeletedText()
    {
        if (isDeleted)
        {
            return "true";
        }
        return "false";
    }

    public void setIsDeletedText(String isDeleted)
    {
        if (isDeleted
            != null
            && isDeleted
               != ""
            && isDeleted.equalsIgnoreCase("true"))
        {
            this.isDeleted =
                true;
        }
        else
        {
            this.isDeleted =
                false;
        }
    }

    public boolean equals(Object o)
    {
        if (o instanceof ProjectContactValue)
        {
            ProjectContactValue
                other =
                (ProjectContactValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("projectContactId="
                      + id);
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\ncontactId="
                      + contactId);
        buffer.append("\ncontactTypeId="
                      + contactTypeId);
        buffer.append("\ncontactTypeName="
                      + contactTypeName);
        buffer.append("\ncontactTypeCode="
                      + contactTypeCode);
        buffer.append("\ncontactTypeIsInternal="
                      + contactTypeIsInternal);
        buffer.append("\norganizationId="
                      + organizationId);
        buffer.append("\norgRefClientId="
                      + orgRefClientId);
        buffer.append("\norganizationName="
                      + organizationName);
        buffer.append("\nfirstName="
                      + firstName);
        buffer.append("\nlastName="
                      + lastName);
        buffer.append("\naddressId="
                      + addressId);
        buffer.append("\naddress="
                      + address);
        buffer.append("\naddress2="
                      + address2);
        buffer.append("\ncity="
                      + city);
        buffer.append("\nzip="
                      + zip);
        buffer.append("\nprimaryPhone="
                      + primaryPhone);
        buffer.append("\nsecondaryPhone="
                      + secondaryPhone);
        buffer.append("\nfaxPhone="
                      + faxPhone);
        buffer.append("\nemail="
                      + email);
        buffer.append("\nisDeleted="
                      + isDeleted);
        buffer.append("\n\n");
        return buffer.toString();
    }
}

