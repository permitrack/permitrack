package com.sehinc.erosioncontrol.value.client;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.UserData;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.client.EcClientRelationship;
import org.apache.log4j.Logger;

public class PartnerValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerValue.class);
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        addressName1;
    private
    String
        addressLine1;
    private
    String
        addressLine2;
    private
    String
        city;
    private
    String
        state;
    private
    String
        postalCode;
    private
    String
        contactFirstName;
    private
    String
        contactLastName;
    private
    String
        contactPhone;
    private
    String
        contactEmail;
    private
    Integer
        clientRelationshipTypeId;
    private
    String
        clientRelationshipTypeName;
    private
    Integer
        relatedClientUserId;

    public PartnerValue()
    {
    }

    public void setClientInfo(ClientData clientData)
    {
        if (clientData
            != null)
        {
            this.id =
                clientData.getId();
            this.name =
                clientData.getName();
            if (clientData.getAddressId()
                != null
                && clientData.getAddressId()
                       .intValue()
                   > 0)
            {
                AddressData
                    clientAddress =
                    new AddressData();
                clientAddress.setId(clientData.getAddressId());
                try
                {
                    if (clientAddress.load())
                    {
                        this.addressLine1 =
                            clientAddress.getLine1();
                        this.addressLine2 =
                            clientAddress.getLine2();
                        this.city =
                            clientAddress.getCity();
                        this.state =
                            clientAddress.getState();
                        this.postalCode =
                            clientAddress.getPostalCode();
                    }
                }
                catch (Exception e)
                {
                    LOG.warn("setClientInfo(ClientData clientData): could not access AddressId = "
                             + clientData.getAddressId());
                }
            }
        }
    }

    public void setUserInfo(UserData userData)
    {
        if (userData
            != null)
        {
            this.relatedClientUserId =
                userData.getId();
            this.contactFirstName =
                userData.getFirstName();
            this.contactLastName =
                userData.getLastName();
            this.contactPhone =
                userData.getPrimaryPhone();
            this.contactEmail =
                userData.getEmailAddress();
        }
    }

    public void setUserInfo(UserValue userValue)
    {
        if (userValue
            != null)
        {
            this.relatedClientUserId =
                userValue.getId();
            this.contactFirstName =
                userValue.getFirstName();
            this.contactLastName =
                userValue.getLastName();
            this.contactPhone =
                userValue.getPrimaryPhone();
            this.contactEmail =
                userValue.getEmailAddress();
        }
    }

    public void setClientRelationshipInfo(EcClientRelationship ecClientRelationship)
    {
        if (ecClientRelationship
            != null)
        {
            this.relatedClientUserId =
                ecClientRelationship.getRelatedClientUserId();
            this.clientRelationshipTypeId =
                ecClientRelationship.getClientRelationshipType()
                    .getId();
            this.clientRelationshipTypeName =
                ecClientRelationship.getClientRelationshipType()
                    .getName();
        }
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getContactFirstName()
    {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName)
    {
        this.contactFirstName =
            contactFirstName;
    }

    public String getContactLastName()
    {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName)
    {
        this.contactLastName =
            contactLastName;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone)
    {
        this.contactPhone =
            contactPhone;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail =
            contactEmail;
    }

    public String getAddressName1()
    {
        return addressName1;
    }

    public void setAddressName1(String addressName1)
    {
        this.addressName1 =
            addressName1;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 =
            addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 =
            addressLine2;
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

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state =
            state;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode =
            postalCode;
    }

    public Integer getClientRelationshipTypeId()
    {
        return clientRelationshipTypeId;
    }

    public void setClientRelationshipTypeId(Integer clientRelationshipTypeId)
    {
        this.clientRelationshipTypeId =
            clientRelationshipTypeId;
    }

    public String getClientRelationshipTypeName()
    {
        return clientRelationshipTypeName;
    }

    public void setClientRelationshipTypeName(String clientRelationshipTypeName)
    {
        this.clientRelationshipTypeName =
            clientRelationshipTypeName;
    }

    public Integer getRelatedClientUserId()
    {
        return relatedClientUserId;
    }

    public void setRelatedClientUserId(Integer relatedClientUserId)
    {
        this.relatedClientUserId =
            relatedClientUserId;
    }

    public String toString()
    {
        return "id = "
               + getId()
               +
               "name = "
               + getName()
               +
               "addressLine1 = "
               + getAddressLine1()
               +
               "addressLine2 = "
               + getAddressLine2()
               +
               "city = "
               + getCity()
               +
               "state = "
               + getState()
               +
               "postalCode = "
               + getPostalCode()
               +
               "contactFirstName = "
               + getContactFirstName()
               +
               "contactLastName = "
               + getContactLastName()
               +
               "contactPhone = "
               + getContactPhone()
               +
               "contactEmail = "
               + getContactEmail()
               +
               "clientRelationshipTypeId = "
               + getClientRelationshipTypeId()
               +
               "clientRelationshipTypeName = "
               + getClientRelationshipTypeName()
               +
               "relatedClientUserId = "
               + getRelatedClientUserId();
    }
}
