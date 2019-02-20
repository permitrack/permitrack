package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class PlanData
    extends StatusData
{
    public static final
    Integer
        DEFAULT_PLAN_ID =
        1;
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;
    private
    String
        permitNumber;
    private
    String
        startDate;
    private
    String
        endDate;
    private
    boolean
        template;
    private
    Integer
        permitPeriodId;
    private
    Integer
        permitTypeId;
    private
    boolean
        cmomProgram;

    public PlanData()
    {
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

    public boolean getCmomProgram()
    {
        return cmomProgram;
    }

    public void setCmomProgram(boolean cmomProgram)
    {
        this.cmomProgram =
            cmomProgram;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public String getPermitNumber()
    {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber)
    {
        this.permitNumber =
            permitNumber;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate =
            startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate =
            endDate;
    }

    public boolean isTemplate()
    {
        return template;
    }

    public void setTemplate(boolean template)
    {
        this.template =
            template;
    }

    public Integer getPermitPeriodId()
    {
        return permitPeriodId;
    }

    public void setPermitPeriodId(Integer permitPeriodId)
    {
        this.permitPeriodId =
            permitPeriodId;
    }

    public Integer getPermitTypeId()
    {
        return permitTypeId;
    }

    public void setPermitTypeId(Integer permitTypeId)
    {
        this.permitTypeId =
            permitTypeId;
    }

    public PlanPermitType getPermitType()
    {
        return PlanPermitType.getById(permitTypeId);
    }

    public boolean getAddBMPtoDBbyId(Integer id)
    {
        return PlanPermitType.getAddBMPtoDBbyId(id);
    }

    public static List findActiveByPermitPeriodId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {
                id,
                StatusCodeData.STATUS_CODE_ACTIVE,
                Boolean.FALSE};
        String
            queryString =
            "select data from PlanData as data where data.permitPeriodId =? and data.status.code = ? and data.cmomProgram =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findActiveNonTemplatesByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                StatusCodeData.STATUS_CODE_ACTIVE,
                Boolean.FALSE,
                Boolean.FALSE};
        String
            queryString =
            "select data from PlanData as data where data.clientId =? and data.status.code = ? and data.template =? and data.cmomProgram =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                Boolean.FALSE};
        String
            queryString =
            "select data from PlanData as data where data.clientId =? and data.cmomProgram =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
