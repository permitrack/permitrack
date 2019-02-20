package com.sehinc.security.action.contact;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.value.user.AddressValue;
import com.sehinc.security.action.base.BaseValidatorForm;
import com.sehinc.security.action.user.UserForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessages;

import java.util.Set;

public class ContactForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ContactForm.class);
    private
    Integer
        id;
    private
    Integer
        organizationId;
    private
    String
        organizationName;
    private
    String
        organizationAddress;
    private
    String
        organizationAddress2;
    private
    String
        organizationCity;
    private
    String
        organizationState;
    private
    String
        organizationZip;
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
    private
    String
        statusCode;
    private
    Set
        allContactTypes;
    private
    String[]
        selectedTypes;
    private
    Integer
        contactTypeEC1;
    private
    Integer
        contactTypeEC2;
    private
    Integer
        contactTypeEC3;
    private
    Integer
        contactTypeEC4;
    private
    Integer
        contactTypeEC5;
    private
    Integer
        contactTypeEC6;
    private
    Integer
        contactTypeEC7;
    private
    Integer
        contactTypeEC8;
    private
    Integer
        contactTypeEC9;
    private
    Integer
        contactTypeSW1;
    private
    Integer
        contactTypeSW2;
    private
    Integer
        contactTypeSW3;
    private
    Integer
        contactTypeSW4;
    private
    Integer
        contactTypeSW5;
    private
    Integer
        contactTypeSW6;
    private
    Integer
        contactTypeSW7;
    private
    Integer
        contactTypeSW8;
    private
    Integer
        contactTypeSW9;
    private
    Integer
        contactTypeDV1;
    private
    Integer
        contactTypeDV2;
    private
    Integer
        contactTypeDV3;
    private
    Integer
        contactTypeDV4;
    private
    Integer
        contactTypeDV5;
    private
    Integer
        contactTypeDV6;
    private
    Integer
        contactTypeDV7;
    private
    Integer
        contactTypeDV8;
    private
    Integer
        contactTypeDV9;
    private
    Boolean
        canViewESC;

    public Boolean getCanViewESC()
    {
        return canViewESC;
    }

    public void setCanViewESC(Boolean canViewESC)
    {
        this.canViewESC =
            canViewESC;
    }

    public String[] getSelectedTypes()
    {
        return this.selectedTypes;
    }

    public void setSelectedTypes(String[] selected)
    {
        this.selectedTypes =
            selected;
    }

    public Set getAllContactTypes()
    {
        return allContactTypes;
    }

    public void setAllContactTypes(Set allContactTypes)
    {
        this.allContactTypes =
            allContactTypes;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer v)
    {
        this.id =
            v;
    }

    public Integer getOrganizationId()
    {
        return this.organizationId;
    }

    public void setOrganizationId(Integer v)
    {
        this.organizationId =
            v;
    }

    public String getOrganizationName()
    {
        return this.organizationName;
    }

    public void setOrganizationName(String v)
    {
        this.organizationName =
            lt(v);
    }

    public String getOrganizationAddress()
    {
        return this.organizationAddress;
    }

    public void setOrganizationAddress(String v)
    {
        this.organizationAddress =
            lt(v);
    }

    public String getOrganizationAddress2()
    {
        return this.organizationAddress2;
    }

    public void setOrganizationAddress2(String v)
    {
        this.organizationAddress2 =
            lt(v);
    }

    public String getOrganizationCity()
    {
        return this.organizationCity;
    }

    public void setOrganizationCity(String v)
    {
        this.organizationCity =
            v;
    }

    public String getOrganizationState()
    {
        return this.organizationState;
    }

    public void setOrganizationState(String v)
    {
        this.organizationState =
            v;
    }

    public String getOrganizationZip()
    {
        return this.organizationZip;
    }

    public void setOrganizationZip(String v)
    {
        this.organizationZip =
            v;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String v)
    {
        this.firstName =
            lt(v);
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String v)
    {
        this.lastName =
            lt(v);
    }

    public Character getMi()
    {
        return this.mi;
    }

    public void setMi(Character v)
    {
        this.mi =
            v;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String v)
    {
        this.title =
            lt(v);
    }

    public Integer getAddressId()
    {
        return this.addressId;
    }

    public void setAddressId(Integer v)
    {
        this.addressId =
            v;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String v)
    {
        this.address =
            lt(v);
    }

    public String getAddress2()
    {
        return this.address2;
    }

    public void setAddress2(String v)
    {
        this.address2 =
            lt(v);
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String v)
    {
        this.city =
            v;
    }

    public String getStateCode()
    {
        return this.stateCode;
    }

    public void setStateCode(String v)
    {
        this.stateCode =
            v;
    }

    public String getZip()
    {
        return this.zip;
    }

    public void setZip(String v)
    {
        this.zip =
            v;
    }

    public String getPrimaryPhone()
    {
        return this.primaryPhone;
    }

    public void setPrimaryPhone(String v)
    {
        this.primaryPhone =
            lt(v);
    }

    public String getSecondaryPhone()
    {
        return this.secondaryPhone;
    }

    public void setSecondaryPhone(String v)
    {
        this.secondaryPhone =
            lt(v);
    }

    public String getFaxPhone()
    {
        return this.faxPhone;
    }

    public void setFaxPhone(String v)
    {
        this.faxPhone =
            lt(v);
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String v)
    {
        this.email =
            lt(v);
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public void setStatusCode(String v)
    {
        this.statusCode =
            v;
    }

    public String getStatusCodeText()
    {
        String
            strStatus =
            "Unknown";
        if (this.statusCode
            .equals(new Integer(1)))
        {
            strStatus =
                "Active";
        }
        if (this.statusCode
            .equals(new Integer(2)))
        {
            strStatus =
                "In-Active";
        }
        if (this.statusCode
            .equals(new Integer(3)))
        {
            strStatus =
                "Deleted";
        }
        return strStatus;
    }

    public Integer getContactTypeEC1()
    {
        return this.contactTypeEC1;
    }

    public void setContactTypeEC1(Integer v)
    {
        this.contactTypeEC1 =
            v;
    }

    public Integer getContactTypeEC2()
    {
        return this.contactTypeEC2;
    }

    public void setContactTypeEC2(Integer v)
    {
        this.contactTypeEC2 =
            v;
    }

    public Integer getContactTypeEC3()
    {
        return this.contactTypeEC3;
    }

    public void setContactTypeEC3(Integer v)
    {
        this.contactTypeEC3 =
            v;
    }

    public Integer getContactTypeEC4()
    {
        return this.contactTypeEC4;
    }

    public void setContactTypeEC4(Integer v)
    {
        this.contactTypeEC4 =
            v;
    }

    public Integer getContactTypeEC5()
    {
        return this.contactTypeEC5;
    }

    public void setContactTypeEC5(Integer v)
    {
        this.contactTypeEC5 =
            v;
    }

    public Integer getContactTypeEC6()
    {
        return this.contactTypeEC6;
    }

    public void setContactTypeEC6(Integer v)
    {
        this.contactTypeEC6 =
            v;
    }

    public Integer getContactTypeEC7()
    {
        return this.contactTypeEC7;
    }

    public void setContactTypeEC7(Integer v)
    {
        this.contactTypeEC7 =
            v;
    }

    public Integer getContactTypeEC8()
    {
        return this.contactTypeEC8;
    }

    public void setContactTypeEC8(Integer v)
    {
        this.contactTypeEC8 =
            v;
    }

    public Integer getContactTypeEC9()
    {
        return this.contactTypeEC9;
    }

    public void setContactTypeEC9(Integer v)
    {
        this.contactTypeEC9 =
            v;
    }

    public Integer getContactTypeSW1()
    {
        return this.contactTypeSW1;
    }

    public void setContactTypeSW1(Integer v)
    {
        this.contactTypeSW1 =
            v;
    }

    public Integer getContactTypeSW2()
    {
        return this.contactTypeSW2;
    }

    public void setContactTypeSW2(Integer v)
    {
        this.contactTypeSW2 =
            v;
    }

    public Integer getContactTypeSW3()
    {
        return this.contactTypeSW3;
    }

    public void setContactTypeSW3(Integer v)
    {
        this.contactTypeSW3 =
            v;
    }

    public Integer getContactTypeSW4()
    {
        return this.contactTypeSW4;
    }

    public void setContactTypeSW4(Integer v)
    {
        this.contactTypeSW4 =
            v;
    }

    public Integer getContactTypeSW5()
    {
        return this.contactTypeSW5;
    }

    public void setContactTypeSW5(Integer v)
    {
        this.contactTypeSW5 =
            v;
    }

    public Integer getContactTypeSW6()
    {
        return this.contactTypeSW6;
    }

    public void setContactTypeSW6(Integer v)
    {
        this.contactTypeSW6 =
            v;
    }

    public Integer getContactTypeSW7()
    {
        return this.contactTypeSW7;
    }

    public void setContactTypeSW7(Integer v)
    {
        this.contactTypeSW7 =
            v;
    }

    public Integer getContactTypeSW8()
    {
        return this.contactTypeSW8;
    }

    public void setContactTypeSW8(Integer v)
    {
        this.contactTypeSW8 =
            v;
    }

    public Integer getContactTypeSW9()
    {
        return this.contactTypeSW9;
    }

    public void setContactTypeSW9(Integer v)
    {
        this.contactTypeSW9 =
            v;
    }

    public Integer getContactTypeDV1()
    {
        return this.contactTypeDV1;
    }

    public void setContactTypeDV1(Integer v)
    {
        this.contactTypeDV1 =
            v;
    }

    public Integer getContactTypeDV2()
    {
        return this.contactTypeDV2;
    }

    public void setContactTypeDV2(Integer v)
    {
        this.contactTypeDV2 =
            v;
    }

    public Integer getContactTypeDV3()
    {
        return this.contactTypeDV3;
    }

    public void setContactTypeDV3(Integer v)
    {
        this.contactTypeDV3 =
            v;
    }

    public Integer getContactTypeDV4()
    {
        return this.contactTypeDV4;
    }

    public void setContactTypeDV4(Integer v)
    {
        this.contactTypeDV4 =
            v;
    }

    public Integer getContactTypeDV5()
    {
        return this.contactTypeDV5;
    }

    public void setContactTypeDV5(Integer v)
    {
        this.contactTypeDV5 =
            v;
    }

    public Integer getContactTypeDV6()
    {
        return this.contactTypeDV6;
    }

    public void setContactTypeDV6(Integer v)
    {
        this.contactTypeDV6 =
            v;
    }

    public Integer getContactTypeDV7()
    {
        return this.contactTypeDV7;
    }

    public void setContactTypeDV7(Integer v)
    {
        this.contactTypeDV7 =
            v;
    }

    public Integer getContactTypeDV8()
    {
        return this.contactTypeDV8;
    }

    public void setContactTypeDV8(Integer v)
    {
        this.contactTypeDV8 =
            v;
    }

    public Integer getContactTypeDV9()
    {
        return this.contactTypeDV9;
    }

    public void setContactTypeDV9(Integer v)
    {
        this.contactTypeDV9 =
            v;
    }

    public void setAddressValuesContactId()
        throws Exception
    {
        AddressValue
            objA =
            new AddressValue();
        objA.setByContactId(this.id);
        setAddressValues(objA);
    }

    public void setAddressValues(AddressValue objA)
    {
        this.setAddressId(objA.getId());
        this.setAddress(objA.getLine1());
        this.setAddress2(objA.getLine2());
        this.setCity(objA.getCity());
        this.setStateCode(objA.getState());
        this.setZip(objA.getPostalCode());
    }

    public void reset()
    {
        this.id =
            null;
        this.organizationId =
            null;
        this.organizationName =
            null;
        this.organizationAddress =
            null;
        this.organizationAddress2 =
            null;
        this.organizationCity =
            null;
        this.organizationState =
            null;
        this.organizationZip =
            null;
        this.firstName =
            null;
        this.lastName =
            null;
        this.mi =
            null;
        this.title =
            null;
        this.addressId =
            null;
        this.address =
            null;
        this.address2 =
            null;
        this.city =
            null;
        this.stateCode =
            null;
        this.zip =
            null;
        this.primaryPhone =
            null;
        this.secondaryPhone =
            null;
        this.faxPhone =
            null;
        this.email =
            null;
        this.statusCode =
            null;
        this.selectedTypes =
            null;
        this.allContactTypes =
            null;
        this.canViewESC =
            false;
        this.contactTypeEC1 =
            null;
        this.contactTypeEC2 =
            null;
        this.contactTypeEC3 =
            null;
        this.contactTypeEC4 =
            null;
        this.contactTypeEC5 =
            null;
        this.contactTypeEC6 =
            null;
        this.contactTypeEC7 =
            null;
        this.contactTypeEC8 =
            null;
        this.contactTypeEC9 =
            null;
        this.contactTypeSW1 =
            null;
        this.contactTypeSW2 =
            null;
        this.contactTypeSW3 =
            null;
        this.contactTypeSW4 =
            null;
        this.contactTypeSW5 =
            null;
        this.contactTypeSW6 =
            null;
        this.contactTypeSW7 =
            null;
        this.contactTypeSW8 =
            null;
        this.contactTypeSW9 =
            null;
        this.contactTypeDV1 =
            null;
        this.contactTypeDV2 =
            null;
        this.contactTypeDV3 =
            null;
        this.contactTypeDV4 =
            null;
        this.contactTypeDV5 =
            null;
        this.contactTypeDV6 =
            null;
        this.contactTypeDV7 =
            null;
        this.contactTypeDV8 =
            null;
        this.contactTypeDV9 =
            null;
    }

    public void setValuesFromUserFormData(UserForm objUF)
    {
        String
            strClientName =
            new String("");
        this.setLastName(objUF.getLastName());
        this.setFirstName(objUF.getFirstName());
        if (objUF.getMiddleName()
            != null
            && objUF.getMiddleName()
                   .length()
               > 0)
        {
            this.mi =
                new Character(objUF.getMiddleName()
                                  .charAt(0));
        }
        this.setTitle(objUF.getTitle());
        this.setAddress(objUF.getAddressLine1());
        this.setAddress2(objUF.getAddressLine2());
        this.setCity(objUF.getCity());
        this.setStateCode(objUF.getState());
        this.setZip(objUF.getPostalCode());
        this.setAddressId(objUF.getAddressId());
        this.setEmail(objUF.getEmailAddress());
        this.setFaxPhone(objUF.getFaxPhone());
        this.setPrimaryPhone(objUF.getPrimaryPhone());
        this.setSecondaryPhone(objUF.getSecondaryPhone());
        if (objUF.getClientId()
            != null)
        {
            try
            {
                ClientData
                    clientData =
                    new ClientData(objUF.getClientId());
                clientData.load();
                strClientName =
                    clientData.getName();
            }
            catch (Exception e)
            {
                LOG.error("Failed to ClientData ID "
                          + objUF.getClientId());
            }
        }
        this.setOrganizationName(strClientName);
    }

    public void checkForHTML()
    {
        organizationName =
            html(organizationName);
        organizationAddress =
            html(organizationAddress);
        organizationAddress2 =
            html(organizationAddress2);
        organizationCity =
            html(organizationCity);
        firstName =
            html(firstName);
        lastName =
            html(lastName);
        title =
            html(title);
        address =
            html(address);
        address2 =
            html(address2);
        city =
            html(city);
        primaryPhone =
            html(primaryPhone);
        secondaryPhone =
            html(secondaryPhone);
        faxPhone =
            html(faxPhone);
        email =
            html(email);
    }

    public void validateForm(ActionMessages errors)
    {
    }
}
