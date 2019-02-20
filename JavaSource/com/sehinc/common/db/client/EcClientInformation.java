package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;

public class EcClientInformation
    extends HibernateData
{
    private
    Integer
        clientId;
    private
    Integer
        eventContactId;
    private
    String
        eventEMail;
    private
    String
        eventFromEMail;
    private
    String
        publicCommentEMail;
    private
    Integer
        clientTypeId;
    private
    String
        generalReplyToEMail;

    public EcClientInformation()
    {
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

    public Integer getEventContactId()
    {
        return this.eventContactId;
    }

    public void setEventContactId(Integer eventContactId)
    {
        this.eventContactId =
            eventContactId;
    }

    public String getEventEMail()
    {
        return this.eventEMail;
    }

    public void setEventEMail(String eventEMail)
    {
        this.eventEMail =
            eventEMail;
    }

    public String getEventFromEMail()
    {
        return this.eventFromEMail;
    }

    public void setEventFromEMail(String eventFromEMail)
    {
        this.eventFromEMail =
            eventFromEMail;
    }

    public String getPublicCommentEMail()
    {
        return this.publicCommentEMail;
    }

    public void setPublicCommentEMail(String publicCommentEMail)
    {
        this.publicCommentEMail =
            publicCommentEMail;
    }

    public Integer getClientTypeId()
    {
        return this.clientTypeId;
    }

    public void setClientTypeId(Integer clientTypeId)
    {
        this.clientTypeId =
            clientTypeId;
    }

    public String getGeneralReplyToEMail()
    {
        return this.generalReplyToEMail;
    }

    public void setGeneralReplyToEMail(String generalReplyToEMail)
    {
        this.generalReplyToEMail =
            generalReplyToEMail;
    }

    public static EcClientInformation findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select info from EcClientInformation as info where info.clientId = ?";
        return (EcClientInformation) HibernateUtil.findUnique(queryString,
                                                              parameters);
    }

    public static String findGeneralReplyToByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select generalReplyToEMail FROM EcClientInformation WHERE clientId = ?";
        return (String) HibernateUtil.findUnique(queryString,
                                                 parameters);
    }
}
