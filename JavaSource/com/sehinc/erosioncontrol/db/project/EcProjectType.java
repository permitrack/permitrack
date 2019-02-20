package com.sehinc.erosioncontrol.db.project;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.List;

public class EcProjectType
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcProjectType.class);
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
    EcEndPointType
        endPointType;
    private
    Boolean
        swmp;
    private
    Integer
        extendedOnlineAccessMonths;
    private
    Integer
        monthsFromStart;

    public EcProjectType()
    {
    }

    public EcProjectType(Integer id)
    {
        setId(id);
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public EcEndPointType getEndPointType()
    {
        return this.endPointType;
    }

    public void setEndPointType(EcEndPointType endPointType)
    {
        this.endPointType =
            endPointType;
    }

    public Boolean getSwmp()
    {
        return this.swmp;
    }

    public void setSwmp(Boolean swmp)
    {
        this.swmp =
            swmp;
    }

    public String getSwmpYesNoText()
    {
        if (swmp)
        {
            return "Yes";
        }
        return "No";
    }

    public Integer getExtendedOnlineAccessMonths()
    {
        return this.extendedOnlineAccessMonths;
    }

    public void setExtendedOnlineAccessMonths(Integer extendedOnlineAccessMonths)
    {
        this.extendedOnlineAccessMonths =
            extendedOnlineAccessMonths;
    }

    public Integer getMonthsFromStart()
    {
        return this.monthsFromStart;
    }

    public void setMonthsFromStart(Integer monthsFromStart)
    {
        this.monthsFromStart =
            monthsFromStart;
    }

    public static EcProjectType find(Integer projectTypeId)
    {
        Object
            parameters
            [
            ] =
            {
                projectTypeId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from EcProjectType as data where data.id in (?) and data.status.code = ?";
        return (EcProjectType) HibernateUtil.findUnique(queryString,
                                                        parameters);
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                StatusCodeData.STATUS_CODE_ACTIVE};
        String
            queryString =
            "select data from EcProjectType as data where data.clientId in (?) and data.status.code = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findAllByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcProjectType as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}