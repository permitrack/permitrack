package com.sehinc.environment.db.facility;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.util.DateUtil;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.status.StatusData;

import java.util.Date;
import java.util.List;

public class EnvFacility
    extends StatusData
{
    public static
    String
        FIND_BY_CLIENT_ID =
        "EnvFacility.findByClientId";
    public static
    String
        FIND_BY_FACILITY_NAME_AND_CLIENT_ID =
        "EnvFacility.findByFacilityNameAndClientId";
    private
    String
        name;
    private
    String
        number;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;
    private
    String
        phone;
    private
    String
        fax;
    private
    Integer
        mailingAddressId;
    private
    Integer
        physicalAddressId;
    private
    String
        countyName;
    private
    String
        sicCode;
    private
    String
        classDesc;
    private
    Integer
        dailyHrsOp;
    private
    Integer
        daysOpWeek;
    private
    Integer
        weeksOpYear;
    private
    String
        businessHrs;

    public EnvFacility()
    {
    }

    public EnvFacility(Integer id)
    {
        setId(id);
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

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
    }

    public String getNumberAndName()
    {
        return this.number
               + " - "
               + this.name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
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

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax =
            fax;
    }

    public Integer getMailingAddressId()
    {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId)
    {
        this.mailingAddressId =
            mailingAddressId;
    }

    public Integer getPhysicalAddressId()
    {
        return physicalAddressId;
    }

    public void setPhysicalAddressId(Integer physicalAddressId)
    {
        this.physicalAddressId =
            physicalAddressId;
    }

    public String getCountyName()
    {
        return countyName;
    }

    public void setCountyName(String countyName)
    {
        this.countyName =
            countyName;
    }

    public String getSicCode()
    {
        return sicCode;
    }

    public void setSicCode(String sicCode)
    {
        this.sicCode =
            sicCode;
    }

    public String getClassDesc()
    {
        return classDesc;
    }

    public void setClassDesc(String classDesc)
    {
        this.classDesc =
            classDesc;
    }

    public Integer getDailyHrsOp()
    {
        return dailyHrsOp;
    }

    public void setDailyHrsOp(Integer dailyHrsOp)
    {
        this.dailyHrsOp =
            dailyHrsOp;
    }

    public Integer getDaysOpWeek()
    {
        return daysOpWeek;
    }

    public void setDaysOpWeek(Integer daysOpWeek)
    {
        this.daysOpWeek =
            daysOpWeek;
    }

    public Integer getWeeksOpYear()
    {
        return weeksOpYear;
    }

    public void setWeeksOpYear(Integer weeksOpYear)
    {
        this.weeksOpYear =
            weeksOpYear;
    }

    public String getBusinessHrs()
    {
        return businessHrs;
    }

    public void setBusinessHrs(String businessHrs)
    {
        this.businessHrs =
            businessHrs;
    }

    public String getActiveTsText()
    {
        if (activeTs
            != null)
        {
            return DateUtil.mdyDate(activeTs);
        }
        else
        {
            return "";
        }
    }

    public String getInactiveTsText()
    {
        if (inactiveTs
            != null)
        {
            return DateUtil.mdyDate(inactiveTs);
        }
        else
        {
            return "";
        }
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID,
                                              parameters);
    }

    public static List findByFacilityNameAndClientId(String name, Integer clientId)
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "name",
                    name},
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    EnvStatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(FIND_BY_FACILITY_NAME_AND_CLIENT_ID,
                                              parameters);
    }
}
