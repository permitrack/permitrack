package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings(value = {"unused"})
public class ClientUserData
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientUserData.class);
    private static final
    String
        FIND_BY_CLIENT_ID_AND_STATUS_CODE =
        "com.sehinc.common.db.client.ClientUserData.byClientIdAndStatus";
    private
    Integer
        clientId;
    private
    Integer
        userId;

    public ClientUserData()
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

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId =
            userId;
    }

    public static List findByUserId(Integer userId)
    {
        Object
            parameters
            [
            ] =
            {
                userId};
        String
            queryString =
            "select data from ClientUserData as data where data.userId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select data from ClientUserData as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findByClientIdAndUserId(Integer clientId, Integer userId)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                userId};
        String
            queryString =
            "select data from ClientUserData as data where data.clientId =? and data.userId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static List findActiveByClientId(Integer clientId)
    {
        return findByClientIdAndStatusCode(clientId,
                                           StatusCodeData.STATUS_CODE_ACTIVE);
    }

    public static List findByClientIdAndStatusCode(Integer clientId, String statusCode)
    {
        Object[][]
            parameters =
            {
                {
                    "clientId",
                    clientId},
                {
                    "statusCode",
                    statusCode}};
        return HibernateUtil.findByNamedQuery(FIND_BY_CLIENT_ID_AND_STATUS_CODE,
                                              parameters);
    }
}
