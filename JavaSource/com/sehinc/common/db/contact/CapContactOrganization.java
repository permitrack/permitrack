package com.sehinc.common.db.contact;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class CapContactOrganization
    extends StatusData
{
    private
    Integer
        clientId;
    private
    Integer
        refClientId;
    private
    String
        name;
    private
    AddressData
        address;
    private
    Integer
        addressId;
    private
    Boolean
        isClient;
    private
    String
        s =
        new String("");
    private
    Integer
        i =
        new Integer(0);

    public CapContactOrganization()
    {
    }

    public CapContactOrganization(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Boolean getIsClient()
    {
        return this.isClient;
    }

    public void setIsClient(Boolean isClient)
    {
        if (isClient
            == null)
        {
            this.isClient =
                false;
        }
        else
        {
            this.isClient =
                isClient;
        }
    }

    public Integer getRefClientId()
    {
        return this.refClientId;
    }

    public void setRefClientId(Integer refClientId)
    {
        this.refClientId =
            refClientId;
    }

    public String getName()
    {
        if (this.name
            == null)
        {
            return s;
        }
        else
        {
            return this.name;
        }
    }

    public void setName(String name)
    {
        if (name
            == null)
        {
            this.name =
                s;
        }
        else
        {
            this.name =
                name;
        }
    }

    public AddressData getAddress()
    {
        if (this.address
            == null)
        {
            return new AddressData();
        }
        else
        {
            return this.address;
        }
    }

    public void setAddress(AddressData address)
    {
        if (address
            == null)
        {
            this.address =
                new AddressData();
        }
        else
        {
            this.address =
                address;
        }
    }

    public Integer getAddressId()
    {
        Integer
            intReturn =
            new Integer(0);
        if (this.addressId
            == null)
        {
            if (address
                != null)
            {
                if (address.getId()
                    != null)
                {
                    intReturn =
                        address.getId();
                }
            }
        }
        else
        {
            intReturn =
                addressId;
        }
        return intReturn;
    }

    public void setAddressId(Integer addressId)
        throws Exception
    {
        if (addressId
            != null)
        {
            address =
                new AddressData(addressId);
            if (address.load())
            {
                this.addressId =
                    address.getId();
            }
        }
    }

    public void DefaultNullValues()
    {
        if (clientId
            == null)
        {
            clientId =
                i;
        }
        if (name
            == null)
        {
            name =
                s;
        }
        address.defaultNullData();
        if (addressId
            == null)
        {
            addressId =
                i;
        }
        if (isClient
            == null)
        {
            isClient =
                false;
        }
    }

    public static CapContactOrganization findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from CapContactOrganization as data where data.clientId = ? and data.isClient = 1";
        return (CapContactOrganization) HibernateUtil.findUnique(queryString,
                                                                 parameters);
    }

    public static CapContactOrganization findByRefClientId(Integer clientId, Integer refClientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                refClientId};
        String
            queryString =
            "select data from CapContactOrganization as data where data.clientId = ? and data.refClientId = ?";
        return (CapContactOrganization) HibernateUtil.findUnique(queryString,
                                                                 parameters);
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from CapContactOrganization as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllActiveByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from CapContactOrganization as data where data.clientId =? and data.status.code=? order by data.name";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllByRefClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from CapContactOrganization as data where data.refClientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByClientIdWithAddress(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select a from AddressShortValue as a, CapContactOrganization as c "
            + "where c.address.id = a.id and c.clientId =? and c.status.code =? ";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}