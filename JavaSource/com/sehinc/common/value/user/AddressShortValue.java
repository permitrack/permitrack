package com.sehinc.common.value.user;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.security.action.contact.ContactForm;

public class AddressShortValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        line1;
    private
    String
        line2;
    private
    String
        city;
    private
    String
        state;
    private
    String
        postalCode;

    public AddressShortValue()
    {
    }

    public AddressShortValue(AddressData address)
    {
        this.id =
            address.getId();
        this.line1 =
            address.getLine1();
        this.line2 =
            address.getLine2();
        this.city =
            address.getCity();
        this.state =
            address.getState();
        this.postalCode =
            address.getPostalCode();
    }

    public AddressShortValue(Integer intAddressId)
        throws Exception
    {
        AddressData
            objA =
            new AddressData();
        try
        {
            objA.setId(intAddressId);
            if (objA.load())
            {
                this.id =
                    objA.getId();
                this.line1 =
                    objA.getLine1();
                this.line2 =
                    objA.getLine2();
                this.city =
                    objA.getCity();
                this.state =
                    objA.getState();
                this.postalCode =
                    objA.getPostalCode();
            }
            else
            {
                throw new Exception("Address Id does not exist withing the Address table.");
            }
        }
        catch (Exception e)
        {
            throw new Exception("Unable to get AddressValue from addressId <br>Message:<br>"
                                + e.getMessage());
        }
    }

    public AddressShortValue(ContactForm objCF)
    {
        if (objCF
            != null)
        {
            this.id =
                objCF.getAddressId();
            this.line1 =
                objCF.getAddress();
            this.line2 =
                objCF.getAddress2();
            this.city =
                objCF.getCity();
            this.state =
                objCF.getStateCode();
            this.postalCode =
                objCF.getZip();
        }
    }

    public void setByContactId(Integer intContactId)
        throws Exception
    {
        try
        {
            CapContact
                contact =
                new CapContact(intContactId);
            if (contact.load())
            {
                if (contact.getAddressData()
                    != null)
                {
                    this.id =
                        contact.getAddressData()
                            .getId();
                    this.line1 =
                        contact.getAddressData()
                            .getLine1();
                    this.line2 =
                        contact.getAddressData()
                            .getLine2();
                    this.city =
                        contact.getAddressData()
                            .getCity();
                    this.state =
                        contact.getAddressData()
                            .getState();
                    this.postalCode =
                        contact.getAddressData()
                            .getPostalCode();
                }
                else
                {
                    throw new Exception("Could not address for contact ID "
                                        + contact.getId());
                }
            }
            else
            {
                throw new Exception("Failed to load contact ID "
                                    + intContactId);
            }
        }
        catch (Exception e)
        {
            throw new Exception("Failed to set AddressShortValue by contact Id "
                                + intContactId,
                                e);
        }
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public String getLine1()
    {
        return line1;
    }

    public void setLine1(String line1)
    {
        this.line1 =
            line1;
    }

    public String getLine2()
    {
        return line2;
    }

    public void setLine2(String line2)
    {
        this.line2 =
            line2;
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

    public boolean equals(Object o)
    {
        if (o instanceof AddressShortValue)
        {
            AddressShortValue
                other =
                (AddressShortValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
