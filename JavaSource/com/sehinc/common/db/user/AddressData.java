package com.sehinc.common.db.user;

public class AddressData
    extends UserUpdatableData
{
    String
        name1;
    String
        name2;
    String
        name3;
    String
        line1;
    String
        line2;
    String
        line3;
    String
        city;
    String
        state;
    String
        postalCode;
    String
        country;

    public AddressData()
    {
    }

    public AddressData(Integer id)
    {
        setId(id);
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

    public static AddressData copyAddress(AddressData address)
    {
        AddressData
            newAddress =
            null;
        if (address
            != null)
        {
            newAddress =
                new AddressData();
            newAddress.setLine1(address.getLine1());
            newAddress.setLine2(address.getLine2());
            newAddress.setCity(address.getCity());
            newAddress.setState(address.getState());
            newAddress.setPostalCode(address.getPostalCode());
            newAddress.setCountry(address.getCountry());
        }
        return newAddress;
    }

    public static AddressData copyAddress(Integer addressId)
    {
        AddressData
            fromAddress =
            new AddressData(addressId);
        AddressData
            toAddress =
            null;
        if (addressId
            != null
            && fromAddress.load())
        {
            toAddress =
                new AddressData();
            toAddress.setLine1(fromAddress.getLine1());
            toAddress.setLine2(fromAddress.getLine2());
            toAddress.setCity(fromAddress.getCity());
            toAddress.setState(fromAddress.getState());
            toAddress.setPostalCode(fromAddress.getPostalCode());
            toAddress.setCountry(fromAddress.getCountry());
        }
        return toAddress;
    }

    public void defaultNullData()
    {
        String
            s =
            new String("");
        if (this.name1
            == null)
        {
            this.name1 =
                s;
        }
        if (this.name2
            == null)
        {
            this.name2 =
                s;
        }
        if (this.name3
            == null)
        {
            this.name3 =
                s;
        }
        if (this.line1
            == null)
        {
            this.line1 =
                s;
        }
        if (this.line2
            == null)
        {
            this.line2 =
                s;
        }
        if (this.line3
            == null)
        {
            this.line3 =
                s;
        }
        if (this.city
            == null)
        {
            this.city =
                s;
        }
        if (this.state
            == null)
        {
            this.state =
                s;
        }
        if (this.postalCode
            == null)
        {
            this.postalCode =
                s;
        }
        if (this.country
            == null)
        {
            this.country =
                s;
        }
    }
}
