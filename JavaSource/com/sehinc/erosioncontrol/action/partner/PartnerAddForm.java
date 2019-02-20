package com.sehinc.erosioncontrol.action.partner;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.user.AddressData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class PartnerAddForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(PartnerAddForm.class);
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
    Integer
        selectedIndex;
    private
    Integer[]
        contactUserList;
    private
    String[]
        contactEmailList;
    private
    Integer
        clientRelationshipTypeId;
    private
    String
        contactEmail;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
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

    public Integer getSelectedIndex()
    {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex)
    {
        this.selectedIndex =
            selectedIndex;
    }

    public Integer[] getContactUserList()
    {
        return contactUserList;
    }

    public void setContactUserList(Integer[] contactUserList)
    {
        this.contactUserList =
            contactUserList;
    }

    public String[] getContactEmailList()
    {
        return contactEmailList;
    }

    public void setContactEmailList(String[] contactEmailList)
    {
        this.contactEmailList =
            contactEmailList;
    }

    public void setClientInfo(ClientData clientData)
    {
        if (clientData
            != null)
        {
            setId(clientData.getId());
            setName(clientData.getName());
            try
            {
                AddressData
                    addressData =
                    new AddressData();
                addressData.setId(clientData.getAddressId());
                if (addressData.load())
                {
                    setAddressName1(addressData.getName1());
                    setAddressLine1(addressData.getLine1());
                    setAddressLine2(addressData.getLine2());
                    setCity(addressData.getCity());
                    setState(addressData.getState());
                    setPostalCode(addressData.getPostalCode());
                }
            }
            catch (Exception e)
            {
                LOG.warn("WARN: failed to load address data ID "
                         + clientData.getAddressId()
                         + " client ID "
                         + clientData.getId());
            }
        }
        else
        {
            LOG.warn("WARN: clientData passed to setClientInfo is NULL");
        }
    }

    public void reset()
    {
        this.id =
            null;
        this.name =
            null;
        this.selectedIndex =
            null;
        this.contactUserList =
            new Integer[] {};
        this.contactEmailList =
            new String[] {};
        this.addressName1 =
            null;
        this.addressLine1 =
            null;
        this.addressLine2 =
            null;
        this.city =
            null;
        this.state =
            null;
        this.postalCode =
            null;
        this.clientRelationshipTypeId =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
