package com.sehinc.environment.action.facility;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class FacilityForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
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
        permitId;
    private
    String
        permitName;
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
    private
    String
        physAddr1;
    private
    String
        physAddr2;
    private
    String
        physAddr3;
    private
    String
        physAddrCity;
    private
    String
        physAddrState;
    private
    String
        physAddrZip;
    private
    String
        mailAddr1;
    private
    String
        mailAddr2;
    private
    String
        mailAddrCity;
    private
    String
        mailAddrState;
    private
    String
        mailAddrZip;

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getPermitId()
    {
        return permitId;
    }

    public void setPermitId(Integer permitId)
    {
        this.permitId =
            permitId;
    }

    public void setPermitName(String permitName)
    {
        this.permitName =
            permitName;
    }

    public String getPermitName()
    {
        return permitName;
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

    public void setActiveTsText(String activeTs)
    {
        this.activeTs =
            DateUtil.parseDate(activeTs);
    }

    public void setInactiveTsText(String inactiveTs)
    {
        this.inactiveTs =
            DateUtil.parseDate(inactiveTs);
    }

    public String getPhysAddr1()
    {
        return physAddr1;
    }

    public void setPhysAddr1(String physAddr1)
    {
        this.physAddr1 =
            physAddr1;
    }

    public String getPhysAddr2()
    {
        return physAddr2;
    }

    public void setPhysAddr2(String physAddr2)
    {
        this.physAddr2 =
            physAddr2;
    }

    public void setPhysAddr3(String physAddr3)
    {
        this.physAddr3 =
            physAddr3;
    }

    public String getPhysAddr3()
    {
        return physAddr3;
    }

    public String getPhysAddrCity()
    {
        return physAddrCity;
    }

    public void setPhysAddrCity(String physAddrCity)
    {
        this.physAddrCity =
            physAddrCity;
    }

    public String getPhysAddrState()
    {
        return physAddrState;
    }

    public void setPhysAddrState(String physAddrState)
    {
        this.physAddrState =
            physAddrState;
    }

    public String getPhysAddrZip()
    {
        return physAddrZip;
    }

    public void setPhysAddrZip(String physAddrZip)
    {
        this.physAddrZip =
            physAddrZip;
    }

    public String getMailAddr1()
    {
        return mailAddr1;
    }

    public void setMailAddr1(String mailAddr1)
    {
        this.mailAddr1 =
            mailAddr1;
    }

    public String getMailAddr2()
    {
        return mailAddr2;
    }

    public void setMailAddr2(String mailAddr2)
    {
        this.mailAddr2 =
            mailAddr2;
    }

    public String getMailAddrCity()
    {
        return mailAddrCity;
    }

    public void setMailAddrCity(String mailAddrCity)
    {
        this.mailAddrCity =
            mailAddrCity;
    }

    public String getMailAddrState()
    {
        return mailAddrState;
    }

    public void setMailAddrState(String mailAddrState)
    {
        this.mailAddrState =
            mailAddrState;
    }

    public String getMailAddrZip()
    {
        return mailAddrZip;
    }

    public void setMailAddrZip(String mailAddrZip)
    {
        this.mailAddrZip =
            mailAddrZip;
    }

    public void reset()
    {
        id =
            null;
        name =
            null;
        number =
            null;
        description =
            null;
        permitId =
            null;
        permitName =
            null;
        activeTs =
            null;
        inactiveTs =
            null;
        phone =
            null;
        fax =
            null;
        countyName =
            null;
        sicCode =
            null;
        classDesc =
            null;
        dailyHrsOp =
            null;
        daysOpWeek =
            null;
        weeksOpYear =
            null;
        businessHrs =
            null;
        physAddr1 =
            null;
        physAddr2 =
            null;
        physAddr3 =
            null;
        physAddrCity =
            null;
        physAddrState =
            null;
        physAddrZip =
            null;
        mailAddr1 =
            null;
        mailAddr2 =
            null;
        mailAddrCity =
            null;
        mailAddrState =
            null;
        mailAddrZip =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
