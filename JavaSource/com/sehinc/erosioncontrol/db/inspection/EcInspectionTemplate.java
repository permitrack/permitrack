package com.sehinc.erosioncontrol.db.inspection;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings("unused")
public class EcInspectionTemplate
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcInspectionTemplate.class);
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        description;

    public EcInspectionTemplate()
    {
    }

    public EcInspectionTemplate(Integer id)
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

    public static EcInspectionTemplate findUniqueByInspectionTemplateId(Integer id)
    {
        Object
            parameters
            [
            ] =
            {id};
        String
            queryString =
            "select data from EcInspectionTemplate as data where data.id =?";
        return (EcInspectionTemplate) HibernateUtil.findUnique(queryString,
                                                               parameters);
    }

    public static List findActiveByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from EcInspectionTemplate as data where data.clientId = ? and data.status.code in ("
            + StatusCodeData.STATUS_CODE_ACTIVE
            + ") order by data.name asc";
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
            "select data from EcInspectionTemplate as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}