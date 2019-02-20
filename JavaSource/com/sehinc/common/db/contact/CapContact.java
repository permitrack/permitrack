package com.sehinc.common.db.contact;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;
import java.util.Set;

public class CapContact
    extends StatusData
{
    static
    String
        FIND_ALL_USER_CONTACTS_BY_CLIENT =
        "com.sehinc.common.db.contact.getAllUserContactsByClientId";
    static
    String
        FIND_ALL_ADMINS_BY_CLIENT =
        "com.sehinc.common.db.contact.getAllAdminsByClientId";
    static
    String
        FIND_ALL_CLIENT_CONTACT_INFO =
        "com.sehinc.common.db.contact.getAllClientContacts";
    static
    String
        FIND_BY_NAME_ASC =
        "com.sehinc.common.db.contact.getAllByNameAsc";
    static
    String
        FIND_BY_NAME_DESC =
        "com.sehinc.common.db.contact.getAllByNameDesc";
    static
    String
        FIND_BY_ORG_ASC =
        "com.sehinc.common.db.contact.getAllByOrgAsc";
    static
    String
        FIND_BY_ORG_DESC =
        "com.sehinc.common.db.contact.getAllByOrgDesc";
    private
    CapContactOrganization
        organization;
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
    AddressData
        addressData;
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
    Set
        contactTypes;
    public static final
    String
        ASCENDING =
        "Asc";
    public static final
    String
        DESCENDING =
        "Desc";
    public static final
    String
        CONTACT_NAME =
        "contact_name";
    public static final
    String
        ORGANIZATION_NAME =
        "organization_name";

    public CapContact()
    {
    }

    public CapContact(Integer id)
    {
        setId(id);
    }

    public CapContactOrganization getOrganization()
    {
        return this.organization;
    }

    public void setOrganization(CapContactOrganization organization)
    {
        this.organization =
            organization;
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

    public String getFullName()
    {
        return this.firstName
               + " "
               + this.lastName;
    }

    public void setFullName(String fullName)
    {
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

    public AddressData getAddressData()
    {
        return this.addressData;
    }

    public void setAddressData(AddressData addressData)
    {
        this.addressData =
            addressData;
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

    public static CapContact getActiveById(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select contact from CapContact as contact where contact.id = ? and contact.status.code = ?";
        return (CapContact) HibernateUtil.findUnique(queryString,
                                                     parameters);
    }

    public static CapContact getActiveByNameAndOrg(Integer organizationId, String firstName, String lastName)
    {
        Object
            parameters
            [
            ] =
            {
                organizationId,
                firstName.toUpperCase(),
                lastName.toUpperCase(),
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select contact from CapContact as contact where contact.organization.id = ? and upper(contact.firstName) = ? and upper(contact.lastName) = ? and contact.status.code = ?";
        List
            result =
            HibernateUtil.find(queryString,
                               parameters);
        if (!result.isEmpty())
        {
            return (CapContact) result.get(0);
        }
        return null;
    }

    public static List getAllActiveClientUserContacts(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_ALL_USER_CONTACTS_BY_CLIENT,
                                              parameters);
    }

    public static List getAllActiveClientAdminContacts(Integer clientId)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_ALL_ADMINS_BY_CLIENT,
                                              parameters);
    }

    public static List findByClientContactListAndStatus(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_ALL_CLIENT_CONTACT_INFO,
                                              parameters);
    }

    public static List findByNameAsc(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_NAME_ASC,
                                              parameters);
    }

    public static List findByNameDesc(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_NAME_DESC,
                                              parameters);
    }

    public static List findByOrgAsc(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ORG_ASC,
                                              parameters);
    }

    public static List findByOrgDesc(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_ORG_DESC,
                                              parameters);
    }

    public Set getContactTypes()
    {
        return contactTypes;
    }

    public void setContactTypes(Set contactTypes)
    {
        this.contactTypes =
            contactTypes;
    }

    public void defaultNullValues()
    {
        organization.DefaultNullValues();
        String
            s =
            "";
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
        if (addressData
            == null)
        {
            addressData =
                new AddressData();
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
    }

    public boolean equals(Object o)
    {
        if (o
            == this)
        {
            return true;
        }
        if (!(o instanceof CapContact))
        {
            return false;
        }
        CapContact
            contactTwo =
            (CapContact) o;
        if (this
            == null
            || contactTwo
               == null)
        {
            return false;
        }
        if (this.getId()
            == null
            || contactTwo.getId()
               == null)
        {
            return false;
        }
        if (contactTwo.getId()
                .intValue()
            == this.getId()
            .intValue())
        {
            return true;
        }
        return false;
    }

    public int hashCode()
    {
        int
            result =
            17;
        result =
            37
            * result
            + this.getId();
        return result;
    }

    public String toString()
    {
        return "id: "
               + this.getId()
               + " First Name: "
               + this.getFirstName()
               + " Last Name: "
               + this.getLastName();
    }
}