package com.sehinc.stormwater.db.plan;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Date;
import java.util.List;

public class PlanPublishData
    extends StatusData
{
    private
    Integer
        clientId;
    private
    Integer
        planId;
    private
    Date
        startDate;
    private
    Date
        endDate;

    public PlanPublishData()
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

    public Integer getPlanId()
    {
        return planId;
    }

    public void setPlanId(Integer planId)
    {
        this.planId =
            planId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate =
            startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate =
            endDate;
    }

    public static List findActiveByClientAndPlan(Integer clientId, Integer planId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                planId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from PlanPublishData as data where data.clientId =? and data.planId =? and data.status.code =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}