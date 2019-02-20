package com.sehinc.stormwater.value.plan;

import com.sehinc.stormwater.db.plan.PlanData;
import com.sehinc.stormwater.db.plan.PlanPermitType;

public class PlanValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    String
        name;
    private
    Integer
        permitPeriodId;
    private
    String
        statusCode;
    private
    boolean
        template;
    private
    Integer
        permitTypeId;
    private
    boolean
        cmomProgram;
    private
    Integer
        clientId;

    public PlanValue()
    {
    }

    public PlanValue(Integer id, String name)
    {
        this.name =
            name;
        this.id =
            id;
        this.cmomProgram =
            false;
    }

    public PlanValue(Integer id, String name, Integer permitPeriodId)
    {
        this.name =
            name;
        this.id =
            id;
        this.permitPeriodId =
            permitPeriodId;
        this.cmomProgram =
            false;
    }

    public PlanValue(PlanData planData)
    {
        this.id =
            planData.getId();
        this.name =
            planData.getName();
        this.permitPeriodId =
            planData.getPermitPeriodId();
        this.permitTypeId =
            planData.getPermitTypeId();
        this.cmomProgram =
            false;
        this.clientId =
            planData.getClientId();
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getName()
    {
        return name;
    }

    public void setPermitPeriodId(Integer permitPeriodId)
    {
        this.permitPeriodId =
            permitPeriodId;
    }

    public Integer getPermitPeriodId()
    {
        return permitPeriodId;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
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

    public boolean getCmomProgram()
    {
        return cmomProgram;
    }

    public void setCmomProgram(boolean cmomProgram)
    {
        this.cmomProgram =
            cmomProgram;
    }

    public Integer getClientId()
    {
        return clientId;
    }
}
