package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class MCMData
    extends StatusData
{
    private
    Integer
        planId;
    private
    String
        name;
    private
    String
        requiredDescription;
    private
    String
        necessaryDescription;
    private
    Integer
        number;
    private
    Integer
        ownerId;

    public MCMData()
    {
    }

    public Integer getPlanId()
    {
        return planId;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
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

    public String getRequiredDescription()
    {
        return requiredDescription;
    }

    public void setRequiredDescription(String requiredDescription)
    {
        this.requiredDescription =
            requiredDescription;
    }

    public String getNecessaryDescription()
    {
        return necessaryDescription;
    }

    public void setNecessaryDescription(String necessaryDescription)
    {
        this.necessaryDescription =
            necessaryDescription;
    }

    public Integer getNumber()
    {
        return number;
    }

    public void setNumber(Integer number)
    {
        this.number =
            number;
    }

    public Integer getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId)
    {
        this.ownerId =
            ownerId;
    }

    public static List findActiveByPlanId(Integer planId)
    {
        Object
            parameters
            [
            ] =
            {
                planId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from MCMData as data where data.planId =? and data.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}
