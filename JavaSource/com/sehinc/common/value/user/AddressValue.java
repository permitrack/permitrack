package com.sehinc.common.value.user;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.security.action.contact.ContactForm;

public class AddressValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name1;
    private
    String
        name2;
    private
    String
        name3;
    private
    String
        line1;
    private
    String
        line2;
    private
    String
        line3;
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
        country;

    public AddressValue()
    {
    }

    public AddressValue(AddressData address)
    {
        if (address
            != null)
        {
            this.id =
                address.getId();
            this.name1 =
                address.getName1();
            this.name2 =
                address.getName2();
            this.name3 =
                address.getName3();
            this.line1 =
                address.getLine1();
            this.line2 =
                address.getLine2();
            this.line3 =
                address.getLine3();
            this.city =
                address.getCity();
            this.state =
                address.getState();
            this.postalCode =
                address.getPostalCode();
            this.country =
                address.getCountry();
        }
    }

    public AddressValue(Integer intAddressId)
        throws Exception
    {
        try
        {
            AddressData
                address =
                new AddressData(intAddressId);
            if (address.load())
            {
                this.id =
                    address.getId();
                this.name1 =
                    address.getName1();
                this.name2 =
                    address.getName2();
                this.name3 =
                    address.getName3();
                this.line1 =
                    address.getLine1();
                this.line2 =
                    address.getLine2();
                this.line3 =
                    address.getLine3();
                this.city =
                    address.getCity();
                this.state =
                    address.getState();
                this.postalCode =
                    address.getPostalCode();
                this.country =
                    address.getCountry();
            }
            else
            {
                throw new Exception("Could not find Address Id "
                                    + intAddressId);
            }
        }
        catch (Exception e)
        {
            throw new Exception("Failed to load Address ID "
                                + intAddressId,
                                e);
        }
    }

    public AddressValue(ContactForm objCF)
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

    public String getName1()
    {
        return name1;
    }

    public void setName1(String name1)
    {
        this.name1 =
            name1;
    }

    public String getName2()
    {
        return name2;
    }

    public void setName2(String name2)
    {
        this.name2 =
            name2;
    }

    public String getName3()
    {
        return name3;
    }

    public void setName3(String name3)
    {
        this.name3 =
            name3;
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

    public String getLine3()
    {
        return line3;
    }

    public void setLine3(String line3)
    {
        this.line3 =
            line3;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country =
            country;
    }

    public boolean equals(Object o)
    {
        if (o instanceof AddressValue)
        {
            AddressValue
                other =
                (AddressValue) o;
            return other.getId()
                .equals(id);
        }
        return false;
    }
}
