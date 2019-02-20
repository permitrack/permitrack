package com.sehinc.common.db.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class ClientData
    extends StatusData
    implements Comparable
{
    private
    String
        shortName;
    private
    String
        name;
    private
    Integer
        addressId;
    private
    String
        phone;
    private
    Integer
        contactId;
    private
    String
        contactName;
    private
    String
        contactPhone;
    private
    String
        contactEmail;
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
        logoLocation;
    private
    String
        mapLogoLocation;
    private
    String
        webPage;
    private
    String
        comment;
    private
    Integer
        defaultPlanId;
    private
    Integer
        defaultProgramId;
    private
    ClientAdminSettingsData
        clientAdminSettings;

    public ClientData()
    {
    }

    public ClientData(Integer id)
    {
        setId(id);
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName =
            shortName;
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

    public Integer getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Integer addressId)
    {
        this.addressId =
            addressId;
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

    public Integer getContactId()
    {
        return contactId;
    }

    public void setContactId(Integer contactId)
    {
        this.contactId =
            contactId;
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

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail =
            contactEmail;
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

    public String getLogoLocation()
    {
        return logoLocation;
    }

    public void setLogoLocation(String logoLocation)
    {
        this.logoLocation =
            logoLocation;
    }

    public String getMapLogoLocation()
    {
        return mapLogoLocation;
    }

    public void setMapLogoLocation(String mapLogoLocation)
    {
        this.mapLogoLocation =
            mapLogoLocation;
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

    public Integer getDefaultPlanId()
    {
        return defaultPlanId;
    }

    public void setDefaultPlanId(Integer defaultPlanId)
    {
        this.defaultPlanId =
            defaultPlanId;
    }

    public Integer getDefaultProgramId()
    {
        return defaultProgramId;
    }

    public void setDefaultProgramId(Integer defaultProgramId)
    {
        this.defaultProgramId =
            defaultProgramId;
    }

    public static List findActive()
    {
        return findByStatus(StatusCodeData.STATUS_CODE_ACTIVE);
    }

    public static List findNonActive()
    {
        Object
            parameters
            [
            ] =
            {StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from ClientData as data where data.status.code <> ? order by data.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByStatus(String status)
    {
        Object
            parameters
            [
            ] =
            {status};
        String
            queryString =
            "select data from ClientData as data where data.status.code = ? order by data.name asc";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findWithDefaultPlan(Integer planId)
    {
        Object
            parameters
            [
            ] =
            {planId};
        String
            queryString =
            "select data from ClientData as data where data.defaultPlanId =? ";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findById(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "SELECT data FROM ClientData as data WHERE id = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static ClientData findUniqueById(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "SELECT data FROM ClientData as data WHERE id = ?";
        return (ClientData) HibernateUtil.findUnique(queryString,
                                                     parameters);
    }

    public static ClientData findSEHClient()
    {
        Object
            parameters
            [
            ] =
            {CommonConstants.SEH_CLIENT_ID};
        String
            queryString =
            "SELECT data FROM ClientData as data WHERE id = ?";
        ClientData
            clientData =
            (ClientData) HibernateUtil.findUnique(queryString,
                                                  parameters);
        if (clientData
            == null)
        {
            Object
                params
                [
                ] =
                {CommonConstants.SEH_CLIENT_SHORT_NAME};
            queryString =
                "select c from ClientData as c where c.shortName = ?";
            return (ClientData) HibernateUtil.findUnique(queryString,
                                                         params);
        }
        return clientData;
    }

    public static ClientData findByShortName(String shortName)
    {
        Object
            parameters
            [
            ] =
            {shortName};
        String
            queryString =
            "select c from ClientData as c where c.shortName = ?";
        return (ClientData) HibernateUtil.findUnique(queryString,
                                                     parameters);
    }

    public static ClientData findByShortNameNotId(String shortName, Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                shortName,
                clientId};
        String
            queryString =
            "select c from ClientData as c where c.shortName = ? and c.id <> ?";
        return (ClientData) HibernateUtil.findUnique(queryString,
                                                     parameters);
    }

    public static List findAll()
    {
        String
            queryString =
            "SELECT c FROM ClientData AS c";
        return HibernateUtil.find(queryString,
                                  new Object[0]);
    }

    public int compareTo(Object obj)
    {
        if (obj instanceof ClientData)
        {
            return getId().compareTo(((ClientData) obj).getId());
        }
        return 0;
    }

    public ClientAdminSettingsData getClientAdminSettings()
    {
        return clientAdminSettings;
    }

    public void setClientAdminSettings(ClientAdminSettingsData clientAdminSettings)
    {
        this.clientAdminSettings =
            clientAdminSettings;
    }
}
