package com.sehinc.security.action.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class ClientForm
    extends BaseValidatorForm
{

    private
    String
        strMod =
        "com.sehinc.security.action.client.ClientForm.";
    private
    ActionMessages
        clientErrors =
        new ActionMessages();
    private
    Integer
        i =
        0;
    private
    String
        s =
        "";
    private
    Boolean
        b =
        false;
    private
    Integer
        id =
        i;
    private
    String
        name =
        s;
    private
    String
        shortName =
        s;
    private
    Integer
        addressId =
        i;
    private
    String
        line1 =
        s;
    private
    String
        line2 =
        s;
    private
    String
        city =
        s;
    private
    String
        state =
        s;
    private
    String
        postalCode =
        s;
    private
    String
        country =
        s;
    private
    String
        phone =
        s;
    private
    String
        contactName =
        s;
    private
    String
        contactPhone =
        s;
    private
    boolean
        blnSystemAdmin;
    private
    Integer
        contactId =
        i;
    private
    Boolean
        contactAsUser =
        false;
    private
    String
        contactFirstName =
        s;
    private
    String
        contactLastName =
        s;
    private
    String
        contactTitle =
        s;
    private
    Integer
        contactAddressId =
        i;
    private
    String
        contactAddress =
        s;
    private
    String
        contactAddress2 =
        s;
    private
    String
        contactCity =
        s;
    private
    String
        contactState =
        s;
    private
    String
        contactZip =
        s;
    private
    String
        contactPrimaryPhone =
        s;
    private
    String
        contactSecondaryPhone =
        s;
    private
    String
        contactFaxPhone =
        s;
    private
    String
        contactEMail =
        s;
    private
    Integer
        contactOrgId =
        i;
    private
    String
        federalTaxId =
        s;
    private
    String
        stateTaxId =
        s;
    private
    String
        majorWaterShed =
        s;
    private
    String
        webPage =
        s;
    private
    String
        comment =
        s;
    private
    String
        statusCode =
        s;
    private
    Integer
        defaultPlanId =
        i;
    private
    Boolean
        canAccessStormWater =
        b;
    private
    Boolean
        canAccessErosionControl =
        b;
    private
    Boolean
        canAccessDataView =
        b;
    private
    Boolean
        canAccessEnvironment =
        b;
    private
    String
        logoLocation =
        s;
    private
    FormFile
        logoFile;
    private
    String
        mapLogoLocation =
        s;
    private
    FormFile
        mapLogoFile;

    public FormFile getLogoFile()
    {
        return logoFile;
    }

    public void setLogoFile(FormFile logoFile)
    {
        this.logoFile =
            logoFile;
    }

    public FormFile getMapLogoFile()
    {
        return mapLogoFile;
    }

    public void setMapLogoFile(FormFile mapLogoFile)
    {
        this.mapLogoFile =
            mapLogoFile;
    }

    public Boolean getCanAccessStormWater()
    {
        if (this.canAccessStormWater
            == null)
        {
            return b;
        }
        else
        {
            return this.canAccessStormWater;
        }
    }

    public void setCanAccessStormWater(Boolean canAccessStormWater)
    {
        if (canAccessStormWater
            == null)
        {
            this.canAccessStormWater =
                b;
        }
        else
        {
            this.canAccessStormWater =
                canAccessStormWater;
        }
    }

    public Boolean getCanAccessErosionControl()
    {
        if (this.canAccessErosionControl
            == null)
        {
            return b;
        }
        else
        {
            return this.canAccessErosionControl;
        }
    }

    public void setCanAccessErosionControl(Boolean canAccessErosionControl)
    {
        if (canAccessErosionControl
            == null)
        {
            this.canAccessErosionControl =
                b;
        }
        else
        {
            this.canAccessErosionControl =
                canAccessErosionControl;
        }
    }

    public Boolean getCanAccessDataView()
    {
        if (this.canAccessDataView
            == null)
        {
            return b;
        }
        else
        {
            return this.canAccessDataView;
        }
    }

    public void setCanAccessDataView(Boolean canAccessDataView)
    {
        if (canAccessDataView
            == null)
        {
            this.canAccessDataView =
                b;
        }
        else
        {
            this.canAccessDataView =
                canAccessDataView;
        }
    }

    public Boolean getCanAccessEnvironment()
    {
        if (this.canAccessEnvironment
            == null)
        {
            return b;
        }
        else
        {
            return this.canAccessEnvironment;
        }
    }

    public void setCanAccessEnvironment(Boolean canAccessEnvironment)
    {
        if (canAccessEnvironment
            == null)
        {
            this.canAccessEnvironment =
                b;
        }
        else
        {
            this.canAccessEnvironment =
                canAccessEnvironment;
        }
    }

    public String getCanAccessStormWaterText()
    {
        return (this.canAccessStormWater)
            ? "Yes"
            : "No";
    }

    public String getCanAccessErosionControlText()
    {
        return (this.canAccessErosionControl)
            ? "Yes"
            : "No";
    }

    public String getCanAccessDataViewText()
    {
        return (this.canAccessDataView)
            ? "Yes"
            : "No";
    }

    public String getCanAccessEnvironmentText()
    {
        return (this.canAccessEnvironment)
            ? "Yes"
            : "No";
    }

    public String getCanAccessStormWaterChecked()
    {
        return (this.canAccessStormWater)
            ? "checked='checked'"
            : "";
    }

    public String getCanAccessErosionControlChecked()
    {
        return (this.canAccessErosionControl)
            ? "checked='checked'"
            : "";
    }

    public String getCanAccessDataViewChecked()
    {
        return (this.canAccessDataView)
            ? "checked='checked'"
            : "";
    }

    public String getCanAccessEnvironmentChecked()
    {
        return (this.canAccessEnvironment)
            ? "checked='checked'"
            : "";
    }

    public Integer getId()
    {
        if (this.id
            == null)
        {
            return i;
        }
        else
        {
            return this.id;
        }
    }

    public void setId(Integer id)
    {
        if (id
            == null)
        {
            this.id =
                i;
        }
        else
        {
            this.id =
                id;
        }
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

    public void setName(String v)
    {
        if (v
            == null)
        {
            this.name =
                s;
        }
        else
        {
            this.name =
                lt(v);
        }
    }

    public String getShortName()
    {
        if (this.shortName
            == null)
        {
            return s;
        }
        else
        {
            return this.shortName;
        }
    }

    public void setShortName(String v)
    {
        if (v
            == null)
        {
            this.shortName =
                s;
        }
        else
        {
            this.shortName =
                v;
        }
    }

    public Integer getAddressId()
    {
        if (this.addressId
            == null)
        {
            return i;
        }
        else
        {
            return this.addressId;
        }
    }

    public void setAddressId(Integer v)
    {
        if (v
            == null)
        {
            this.addressId =
                i;
        }
        else
        {
            this.addressId =
                v;
        }
    }

    public String getLine1()
    {
        if (this.line1
            == null)
        {
            return s;
        }
        else
        {
            return this.line1;
        }
    }

    public void setLine1(String v)
    {
        if (v
            == null)
        {
            this.line1 =
                s;
        }
        else
        {
            this.line1 =
                lt(v);
        }
    }

    public String getLine2()
    {
        if (this.line2
            == null)
        {
            return s;
        }
        else
        {
            return this.line2;
        }
    }

    public void setLine2(String v)
    {
        if (v
            == null)
        {
            this.line2 =
                s;
        }
        else
        {
            this.line2 =
                lt(v);
        }
    }

    public String getCity()
    {
        if (this.city
            == null)
        {
            return s;
        }
        else
        {
            return this.city;
        }
    }

    public void setCity(String v)
    {
        if (v
            == null)
        {
            this.city =
                s;
        }
        else
        {
            this.city =
                v;
        }
    }

    public String getState()
    {
        if (this.state
            == null)
        {
            return s;
        }
        else
        {
            return this.state;
        }
    }

    public void setState(String state)
    {
        if (state
            == null)
        {
            this.state =
                s;
        }
        else
        {
            this.state =
                state;
        }
    }

    public String getPostalCode()
    {
        if (this.postalCode
            == null)
        {
            return s;
        }
        else
        {
            return this.postalCode;
        }
    }

    public void setPostalCode(String postalCode)
    {
        if (postalCode
            == null)
        {
            this.postalCode =
                s;
        }
        else
        {
            this.postalCode =
                postalCode;
        }
    }

    public String getCountry()
    {
        if (this.country
            == null)
        {
            return s;
        }
        else
        {
            return this.country;
        }
    }

    public void setCountry(String country)
    {
        if (country
            == null)
        {
            this.country =
                null;
        }
        else
        {
            this.country =
                country;
        }
    }

    public String getPhone()
    {
        if (this.phone
            == null)
        {
            return s;
        }
        else
        {
            return this.phone;
        }
    }

    public void setPhone(String phone)
    {
        if (phone
            == null)
        {
            this.phone =
                s;
        }
        else
        {
            this.phone =
                phone;
        }
    }

    public String getContactName()
    {
        if (this.contactName
            == null)
        {
            return s;
        }
        else
        {
            return this.contactName;
        }
    }

    public void setContactName(String contactName)
    {
        if (contactName
            == null)
        {
            this.contactName =
                s;
        }
        else
        {
            this.contactName =
                lt(contactName);
        }
    }

    public String getContactPhone()
    {
        if (this.contactPhone
            == null)
        {
            return s;
        }
        else
        {
            return this.contactPhone;
        }
    }

    public void setContactPhone(String contactPhone)
    {
        if (contactPhone
            == null)
        {
            this.contactPhone =
                s;
        }
        else
        {
            this.contactPhone =
                lt(contactPhone);
        }
    }

    public Integer getContactId()
    {
        if (this.contactId
            == null)
        {
            return i;
        }
        else
        {
            return this.contactId;
        }
    }

    public void setContactId(Integer v)
    {
        if (v
            == null)
        {
            this.contactId =
                i;
        }
        else
        {
            this.contactId =
                v;
        }
    }

    public Boolean getContactAsUser()
    {
        if (this.contactAsUser
            == null)
        {
            return false;
        }
        else
        {
            return this.contactAsUser;
        }
    }

    public void setContactAsUser(Boolean v)
    {
        if (v
            == null)
        {
            this.contactAsUser =
                false;
        }
        else
        {
            this.contactAsUser =
                v;
        }
    }

    public String getContactFirstName()
    {
        if (this.contactFirstName
            == null)
        {
            return s;
        }
        else
        {
            return this.contactFirstName;
        }
    }

    public void setContactFirstName(String v)
    {
        if (v
            == null)
        {
            this.contactFirstName =
                s;
        }
        else
        {
            this.contactFirstName =
                lt(v);
        }
    }

    public String getContactLastName()
    {
        if (this.contactLastName
            == null)
        {
            return s;
        }
        else
        {
            return this.contactLastName;
        }
    }

    public void setContactLastName(String v)
    {
        if (v
            == null)
        {
            this.contactLastName =
                s;
        }
        else
        {
            this.contactLastName =
                lt(v);
        }
    }

    public String getContactTitle()
    {
        if (this.contactTitle
            == null)
        {
            return s;
        }
        else
        {
            return this.contactTitle;
        }
    }

    public void setContactTitle(String v)
    {
        if (v
            == null)
        {
            this.contactTitle =
                s;
        }
        else
        {
            this.contactTitle =
                lt(v);
        }
    }

    public Integer getContactAddressId()
    {
        if (this.contactAddressId
            == null)
        {
            return i;
        }
        else
        {
            return this.contactAddressId;
        }
    }

    public void setContactAddressId(Integer v)
    {
        if (v
            == null)
        {
            this.contactAddressId =
                i;
        }
        else
        {
            this.contactAddressId =
                v;
        }
    }

    public String getContactAddress()
    {
        if (this.contactAddress
            == null)
        {
            return s;
        }
        else
        {
            return this.contactAddress;
        }
    }

    public void setContactAddress(String v)
    {
        if (v
            == null)
        {
            this.contactAddress =
                s;
        }
        else
        {
            this.contactAddress =
                lt(v);
        }
    }

    public String getContactAddress2()
    {
        if (this.contactAddress2
            == null)
        {
            return s;
        }
        else
        {
            return this.contactAddress2;
        }
    }

    public void setContactAddress2(String v)
    {
        if (v
            == null)
        {
            this.contactAddress2 =
                s;
        }
        else
        {
            this.contactAddress2 =
                lt(v);
        }
    }

    public String getContactCity()
    {
        if (this.contactCity
            == null)
        {
            return s;
        }
        else
        {
            return this.contactCity;
        }
    }

    public void setContactCity(String v)
    {
        if (v
            == null)
        {
            this.contactCity =
                s;
        }
        else
        {
            this.contactCity =
                lt(v);
        }
    }

    public String getContactState()
    {
        if (this.contactState
            == null)
        {
            return s;
        }
        else
        {
            return this.contactState;
        }
    }

    public void setContactState(String v)
    {
        if (v
            == null)
        {
            this.contactState =
                s;
        }
        else
        {
            this.contactState =
                lt(v);
        }
    }

    public String getContactZip()
    {
        if (this.contactZip
            == null)
        {
            return s;
        }
        else
        {
            return this.contactZip;
        }
    }

    public void setContactZip(String v)
    {
        if (v
            == null)
        {
            this.contactZip =
                s;
        }
        else
        {
            this.contactZip =
                lt(v);
        }
    }

    public String getContactPrimaryPhone()
    {
        if (this.contactPrimaryPhone
            == null)
        {
            return s;
        }
        else
        {
            return this.contactPrimaryPhone;
        }
    }

    public void setContactPrimaryPhone(String v)
    {
        if (v
            == null)
        {
            this.contactPrimaryPhone =
                s;
        }
        else
        {
            this.contactPrimaryPhone =
                lt(v);
        }
    }

    public String getContactSecondaryPhone()
    {
        if (this.contactSecondaryPhone
            == null)
        {
            return s;
        }
        else
        {
            return this.contactSecondaryPhone;
        }
    }

    public void setContactSecondaryPhone(String v)
    {
        if (v
            == null)
        {
            this.contactSecondaryPhone =
                s;
        }
        else
        {
            this.contactSecondaryPhone =
                lt(v);
        }
    }

    public String getContactFaxPhone()
    {
        if (this.contactFaxPhone
            == null)
        {
            return s;
        }
        else
        {
            return this.contactFaxPhone;
        }
    }

    public void setContactFaxPhone(String v)
    {
        if (v
            == null)
        {
            this.contactFaxPhone =
                s;
        }
        else
        {
            this.contactFaxPhone =
                lt(v);
        }
    }

    public String getContactEMail()
    {
        if (this.contactEMail
            == null)
        {
            return s;
        }
        else
        {
            return this.contactEMail;
        }
    }

    public void setContactEMail(String v)
    {
        if (v
            == null)
        {
            this.contactEMail =
                s;
        }
        else
        {
            this.contactEMail =
                lt(v);
        }
    }

    public Integer getContactOrgId()
    {
        if (this.contactOrgId
            == null)
        {
            return i;
        }
        else
        {
            return this.contactOrgId;
        }
    }

    public void setContactOrgId(Integer v)
    {
        if (v
            == null)
        {
            this.contactOrgId =
                i;
        }
        else
        {
            this.contactOrgId =
                v;
        }
    }

    public String getFederalTaxId()
    {
        if (this.federalTaxId
            == null)
        {
            return s;
        }
        else
        {
            return this.federalTaxId;
        }
    }

    public void setFederalTaxId(String federalTaxId)
    {
        if (federalTaxId
            == null)
        {
            this.federalTaxId =
                s;
        }
        else
        {
            this.federalTaxId =
                lt(federalTaxId);
        }
    }

    public String getStateTaxId()
    {
        if (this.stateTaxId
            == null)
        {
            return s;
        }
        else
        {
            return this.stateTaxId;
        }
    }

    public void setStateTaxId(String stateTaxId)
    {
        if (stateTaxId
            == null)
        {
            this.stateTaxId =
                s;
        }
        else
        {
            this.stateTaxId =
                lt(stateTaxId);
        }
    }

    public String getMajorWaterShed()
    {
        if (this.majorWaterShed
            == null)
        {
            return s;
        }
        else
        {
            return this.majorWaterShed;
        }
    }

    public void setMajorWaterShed(String majorWaterShed)
    {
        if (majorWaterShed
            == null)
        {
            this.majorWaterShed =
                s;
        }
        else
        {
            this.majorWaterShed =
                lt(majorWaterShed);
        }
    }

    public String getLogoLocation()
    {
        if (this.logoLocation
            == null)
        {
            return s;
        }
        else
        {
            return this.logoLocation;
        }
    }

    public void setLogoLocation(String logoLocation)
    {
        if (logoLocation
            == null)
        {
            this.logoLocation =
                s;
        }
        else
        {
            this.logoLocation =
                logoLocation;
        }
    }

    public String getMapLogoLocation()
    {
        if (this.mapLogoLocation
            == null)
        {
            return s;
        }
        else
        {
            return this.mapLogoLocation;
        }
    }

    public void setMapLogoLocation(String mapLogoLocation)
    {
        if (mapLogoLocation
            == null)
        {
            this.mapLogoLocation =
                s;
        }
        else
        {
            this.mapLogoLocation =
                mapLogoLocation;
        }
    }

    public String getWebPage()
    {
        if (this.webPage
            == null)
        {
            return s;
        }
        else
        {
            return this.webPage;
        }
    }

    public void setWebPage(String webPage)
    {
        if (webPage
            == null)
        {
            this.webPage =
                s;
        }
        else
        {
            this.webPage =
                webPage;
        }
    }

    public String getComment()
    {
        if (this.comment
            == null)
        {
            return s;
        }
        else
        {
            if (this.comment
                    .length()
                > CommonConstants.MAX_CLIENT_COMMENT)
            {
                return this.comment
                    .substring(0,
                               CommonConstants.MAX_CLIENT_COMMENT
                               - 1);
            }
            else
            {
                return this.comment;
            }
        }
    }

    public void setComment(String comment)
    {
        if (comment
            == null)
        {
            this.comment =
                s;
        }
        else
        {
            if (comment.length()
                > CommonConstants.MAX_CLIENT_COMMENT)
            {
                this.comment =
                    lt(comment.substring(0,
                                         CommonConstants.MAX_CLIENT_COMMENT
                                         - 1));
            }
            else
            {
                this.comment =
                    lt(comment);
            }
        }
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        if (statusCode
            == null)
        {
            this.statusCode =
                s;
        }
        else
        {
            this.statusCode =
                statusCode;
        }
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

    public boolean getBlnSystemAdmin()
    {
        return blnSystemAdmin;
    }

    public void setBlnSystemAdmin(boolean isSystemAdmin)
    {
        this.blnSystemAdmin =
            isSystemAdmin;
    }

    public Integer getDefaultPlanId()
    {
        if (this.defaultPlanId
            == null)
        {
            return i;
        }
        else
        {
            return this.defaultPlanId;
        }
    }

    public void setDefaultPlanId(Integer defaultPlanId)
    {
        if (defaultPlanId
            == null)
        {
            this.defaultPlanId =
                i;
        }
        else
        {
            this.defaultPlanId =
                defaultPlanId;
        }
    }

    public ActionMessages getClientErrors()
    {
        return this.clientErrors;
    }

    public void reset()
    {
        clientErrors.clear();
        id =
            i;
        name =
            s;
        shortName =
            s;
        addressId =
            i;
        line1 =
            s;
        line2 =
            s;
        city =
            s;
        state =
            s;
        postalCode =
            s;
        country =
            s;
        phone =
            s;
        contactName =
            s;
        contactPhone =
            s;
        contactId =
            i;
        contactAsUser =
            false;
        contactFirstName =
            s;
        contactLastName =
            s;
        contactTitle =
            s;
        contactAddressId =
            i;
        contactAddress =
            s;
        contactAddress2 =
            s;
        contactCity =
            s;
        contactState =
            s;
        contactZip =
            s;
        contactPrimaryPhone =
            s;
        contactSecondaryPhone =
            s;
        contactFaxPhone =
            s;
        contactEMail =
            s;
        contactOrgId =
            i;
        federalTaxId =
            s;
        stateTaxId =
            s;
        majorWaterShed =
            s;
        logoLocation =
            s;
        mapLogoLocation =
            s;
        webPage =
            s;
        comment =
            s;
        statusCode =
            s;
        defaultPlanId =
            i;
        canAccessStormWater =
            b;
        canAccessErosionControl =
            b;
        canAccessDataView =
            b;
        canAccessEnvironment =
            b;
        blnSystemAdmin =
            false;
    }

    public void validateForm(ActionMessages errors)
    {
        Logger
            LOG =
            Logger.getLogger(ClientForm.class);
        String
            strLog =
            "com.sehinc.security.action.client.ClientForm.validateForm ";
        String
            strCurrentName;
        LOG.debug(strLog
                  + "in method");
        clientErrors.clear();
        if (errors
            != null)
        {
            if (!errors.isEmpty())
            {
                clientErrors =
                    errors;
            }
        }
        try
        {
            if (id
                == null)
            {
                id =
                    i;
            }
            ClientData
                client =
                new ClientData(id);
            client.load();
/*
            strCurrentName =
                client.getName();
*/
            strCurrentName =
                client.getShortName();
            if (strCurrentName
                == null)
            {
                ClientData
                    clientByShortName =
                    ClientData.findByShortName(q2(shortName));
                if (clientByShortName
                    != null)
                {
                    if (clientByShortName.getShortName()
                        .equals(shortName))
                    {
                        LOG.debug(strLog
                                  + "New short name: "
                                  + shortName
                                  + " is duplicate");
                        clientErrors.add(ActionMessages.GLOBAL_MESSAGE,
                                         new ActionMessage("Client short name "
                                                           + shortName
                                                           + " already exists"));
                    }
                }
            }
            else
            {
                if (strCurrentName
                    != shortName)
                {
                    ClientData
                        clientBySNnotId =
                        ClientData.findByShortNameNotId(q2(shortName),
                                                        id);
                    if (clientBySNnotId
                        != null)
                    {
                        if (clientBySNnotId.getShortName()
                            .equals(shortName))
                        {
                            LOG.debug(strLog
                                      + "Updated short name: "
                                      + shortName
                                      + " is duplicate");
                            clientErrors.add(ActionMessages.GLOBAL_MESSAGE,
                                             new ActionMessage("Client short name "
                                                               + shortName
                                                               + " already exists"));
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            clientErrors.add(ActionMessages.GLOBAL_MESSAGE,
                             new ActionMessage("errors.exception",
                                               "Error validating client form.  Message: "
                                               + e.getMessage()));
        }
/*
        errors =
            clientErrors;
*/
    }

    public void getClientData(ClientData objC)
        throws Exception
    {
        Logger
            LOG =
            Logger.getLogger(ClientForm.class);
        String
            strLog =
            new String(strMod
                       + "getClientData() by reference ");
        LOG.debug(strLog
                  + "in method");
        try
        {
            objC.setName(this.getName());
            objC.setAddressId(this.getAddressId());
            objC.setPhone(this.getPhone());
            objC.setContactName(this.getContactName());
            objC.setContactPhone(this.getContactPhone());
            objC.setFederalTaxId(this.getFederalTaxId());
            objC.setStateTaxId(this.getStateTaxId());
            objC.setMajorWatershed(this.getMajorWaterShed());
            objC.setLogoLocation(this.getLogoLocation());
            objC.setMapLogoLocation(this.getMapLogoLocation());
            objC.setWebPage(this.getWebPage());
            objC.setContactId(this.getContactId());
            if (this.getComment()
                    .length()
                > CommonConstants.MAX_CLIENT_COMMENT)
            {
                objC.setComment(this.getComment()
                                    .substring(0,
                                               CommonConstants.MAX_CLIENT_COMMENT
                                               - 1));
            }
            else
            {
                objC.setComment(this.getComment());
            }
            objC.setStatusCode(this.getStatusCode());
            objC.setDefaultPlanId(this.getDefaultPlanId());
        }
        catch (Exception e)
        {
            strLog =
                strLog
                + "Unable to assign client form values to client data object by reference.  Message: "
                + e.getMessage();
            LOG.debug(strLog);
            throw new Exception(strLog);
        }
    }

    public ClientData getClientData()
        throws Exception
    {
        ClientData
            objC =
            new ClientData();
        Logger
            LOG =
            Logger.getLogger(ClientForm.class);
        String
            strLog =
            new String(strMod
                       + "getClientData ");
        LOG.debug(strLog
                  + "in method");
        try
        {
            objC.setId(this.getId());
            objC.setName(this.getName());
            objC.setShortName(this.getShortName());
            objC.setAddressId(this.getAddressId());
            objC.setPhone(this.getPhone());
            objC.setContactId(this.contactId);
            objC.setContactName(this.getContactName());
            objC.setContactPhone(this.getContactPhone());
            objC.setFederalTaxId(this.getFederalTaxId());
            objC.setStateTaxId(this.getStateTaxId());
            objC.setMajorWatershed(this.getMajorWaterShed());
            objC.setLogoLocation(this.getLogoLocation());
            objC.setMapLogoLocation(this.getMapLogoLocation());
            objC.setWebPage(this.getWebPage());
            if (this.getComment()
                    .length()
                > CommonConstants.MAX_CLIENT_COMMENT)
            {
                objC.setComment(this.getComment()
                                    .substring(0,
                                               CommonConstants.MAX_CLIENT_COMMENT
                                               - 1));
            }
            else
            {
                objC.setComment(this.getComment());
            }
            objC.setStatusCode(this.getStatusCode());
            objC.setDefaultPlanId(this.getDefaultPlanId());
        }
        catch (Exception e)
        {
            strLog =
                strLog
                + "Unable to assign client form values to client data object.  Message: "
                + e.getMessage();
            LOG.debug(strLog);
            throw new Exception(strLog);
        }
        return objC;
    }

    public void setClientAddressData(AddressData objA)
    {
        this.setAddressId(objA.getId());
        this.setLine1(objA.getLine1());
        this.setLine2(objA.getLine2());
        this.setCity(objA.getCity());
        this.setState(objA.getState());
        this.setCountry(objA.getCountry());
        this.setPostalCode(objA.getPostalCode());
    }

    public void setClientData(ClientData objCD)
    {
        this.setId(objCD.getId());
        this.setName(objCD.getName());
        this.setShortName(objCD.getShortName());
        this.setAddressId(objCD.getAddressId());
        this.setPhone(objCD.getPhone());
        this.setContactId(objCD.getContactId());
        this.setContactName(objCD.getContactName());
        this.setContactPhone(objCD.getContactPhone());
        this.setFederalTaxId(objCD.getFederalTaxId());
        this.setStateTaxId(objCD.getStateTaxId());
        this.setMajorWaterShed(objCD.getMajorWatershed());
        this.setLogoLocation(objCD.getLogoLocation());
        this.setMapLogoLocation(objCD.getMapLogoLocation());
        this.setWebPage(objCD.getWebPage());
        if (objCD.getComment()
            != null)
        {
            if (objCD.getComment()
                    .length()
                > CommonConstants.MAX_CLIENT_COMMENT)
            {
                this.setComment(objCD.getComment()
                                    .substring(0,
                                               CommonConstants.MAX_CLIENT_COMMENT
                                               - 1));
            }
            else
            {
                this.setComment(objCD.getComment());
            }
        }
        this.setStatusCode(objCD.getStatus()
                               .getCode());
        this.setDefaultPlanId(objCD.getDefaultPlanId());
    }

    public void setCapContact(CapContact objC)
    {
        if (objC
            != null)
        {
            contactId =
                objC.getId();
            contactAsUser =
                false;
            contactFirstName =
                objC.getFirstName();
            contactLastName =
                objC.getLastName();
            contactTitle =
                objC.getTitle();
            if (objC.getAddressData()
                != null)
            {
                contactAddressId =
                    objC.getAddressData()
                        .getId();
            }
            contactAddress =
                objC.getAddress();
            contactAddress2 =
                objC.getAddress2();
            contactCity =
                objC.getCity();
            contactState =
                objC.getStateCode();
            contactZip =
                objC.getZip();
            contactPrimaryPhone =
                objC.getPrimaryPhone();
            contactSecondaryPhone =
                objC.getSecondaryPhone();
            contactFaxPhone =
                objC.getFaxPhone();
            contactEMail =
                objC.getEmail();
        }
        else
        {
            contactId =
                i;
            contactAsUser =
                false;
            contactFirstName =
                s;
            contactLastName =
                s;
            contactTitle =
                s;
            contactAddressId =
                i;
            contactAddress =
                s;
            contactAddress2 =
                s;
            contactCity =
                s;
            contactState =
                s;
            contactZip =
                s;
            contactPrimaryPhone =
                s;
            contactSecondaryPhone =
                s;
            contactFaxPhone =
                s;
            contactEMail =
                s;
        }
    }

    public void getAddressData(AddressData objA)
        throws Exception
    {
        Logger
            LOG =
            Logger.getLogger(ClientForm.class);
        String
            strLog =
            new String(strMod
                       + "getAddressData by Reference ");
        LOG.debug(strLog
                  + "in method");
        try
        {
            objA.setLine1(this.getLine1());
            objA.setLine2(this.getLine2());
            objA.setCity(this.getCity());
            objA.setState(this.getState());
            objA.setPostalCode(this.getPostalCode());
            objA.setCountry(this.getCountry());
        }
        catch (Exception e)
        {
            strLog =
                strLog
                + "Unable to assign client form values to address data object by reference.  Message: "
                + e.getMessage();
            LOG.debug(strLog);
            throw new Exception(strLog);
        }
    }

    public AddressData getAddressData()
        throws Exception
    {
        AddressData
            objA =
            new AddressData();
        Logger
            LOG =
            Logger.getLogger(ClientForm.class);
        String
            strLog =
            new String(strMod
                       + "getAddressData ");
        LOG.debug(strLog
                  + "in method");
        try
        {
            objA.setId(this.getAddressId());
            objA.setLine1(this.getLine1());
            objA.setLine2(this.getLine2());
            objA.setCity(this.getCity());
            objA.setState(this.getState());
            objA.setPostalCode(this.getPostalCode());
            objA.setCountry(this.getCountry());
        }
        catch (Exception e)
        {
            strLog =
                strLog
                + "Unable to assign client form values to address data object.  Message: "
                + e.getMessage();
            LOG.debug(strLog);
            throw new Exception(strLog);
        }
        return objA;
    }

    public void checkForHTML()
    {
        name =
            html(name);
        shortName =
            html(shortName);
        line1 =
            html(line1);
        line2 =
            html(line2);
        city =
            html(city);
        phone =
            html(phone);
        contactName =
            html(contactName);
        contactPhone =
            html(contactPhone);
        contactFirstName =
            html(contactFirstName);
        contactLastName =
            html(contactLastName);
        contactTitle =
            html(contactTitle);
        contactAddress =
            html(contactAddress);
        contactAddress2 =
            html(contactAddress2);
        contactCity =
            html(contactCity);
        contactState =
            html(contactState);
        contactZip =
            html(contactZip);
        contactPrimaryPhone =
            html(contactPrimaryPhone);
        contactSecondaryPhone =
            html(contactSecondaryPhone);
        contactFaxPhone =
            html(contactFaxPhone);
        contactEMail =
            html(contactEMail);
        federalTaxId =
            html(federalTaxId);
        stateTaxId =
            html(stateTaxId);
        majorWaterShed =
            html(majorWaterShed);
        webPage =
            html(webPage);
        if (comment.length()
            > CommonConstants.MAX_CLIENT_COMMENT)
        {
            comment =
                html(comment.substring(0,
                                       CommonConstants.MAX_CLIENT_COMMENT
                                       - 1));
        }
        else
        {
            comment =
                html(comment);
        }
    }

    public static String q2(String s)
    {
        String
            x =
            new String("");
        int
            a;
        char
            q =
            new String("'").charAt(0);
        if (s.indexOf("'")
            >= 0)
        {
            for (
                a =
                    0; a
                       < s.length(); a++)
            {
                if (s.charAt(a)
                    == q)
                {
                    x =
                        x
                        + "''";
                }
                else
                {
                    x =
                        x
                        + s.charAt(a);
                }
            }
        }
        else
        {
            x =
                s;
        }
        return x;
    }
}
