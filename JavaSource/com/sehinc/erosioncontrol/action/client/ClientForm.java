package com.sehinc.erosioncontrol.action.client;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

public class ClientForm
    extends BaseValidatorForm
{
    private
    String
        name;
    private
    String
        phone;
    private
    String
        contactName;
    private
    String
        contactPhone;
    private
    String
        federalTaxId;
    private
    String
        stateTaxId;
    private
    String
        majorWatershed;
    private
    String
        webPage;
    private
    String
        comment;
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
        country;
    private
    String
        logoLocation;
    private
    FormFile
        logoFile;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone =
            phone;
    }

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName =
            contactName;
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

    public String getFederalTaxId()
    {
        return federalTaxId;
    }

    public void setFederalTaxId(String federalTaxId)
    {
        this.federalTaxId =
            federalTaxId;
    }

    public String getStateTaxId()
    {
        return stateTaxId;
    }

    public void setStateTaxId(String stateTaxId)
    {
        this.stateTaxId =
            stateTaxId;
    }

    public String getMajorWatershed()
    {
        return majorWatershed;
    }

    public void setMajorWatershed(String majorWatershed)
    {
        this.majorWatershed =
            majorWatershed;
    }

    public String getWebPage()
    {
        return webPage;
    }

    public void setWebPage(String webPage)
    {
        this.webPage =
            webPage;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
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

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country =
            country;
    }

    public FormFile getLogoFile()
    {
        return logoFile;
    }

    public void setLogoFile(FormFile logoFile)
    {
        this.logoFile =
            logoFile;
    }

    public void reset()
    {
        this.name =
            null;
        this.phone =
            null;
        this.contactName =
            null;
        this.contactPhone =
            null;
        this.federalTaxId =
            null;
        this.stateTaxId =
            null;
        this.majorWatershed =
            null;
        this.webPage =
            null;
        this.comment =
            null;
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
        this.country =
            null;
        this.logoLocation =
            null;
        this.logoFile =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }

    public String getLogoLocation()
    {
        return logoLocation;
    }

    public void setLogoLocation(String string)
    {
        logoLocation =
            string;
    }
}
