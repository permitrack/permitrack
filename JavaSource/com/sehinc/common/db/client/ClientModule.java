package com.sehinc.common.db.client;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings(value = {"unused"})
public class ClientModule
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientModule.class);
    private
    Integer
        clientId;
    private
    Integer
        moduleId;

    public ClientModule()
    {
    }

    public ClientModule(Integer id)
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

    public Integer getModuleId()
    {
        return this.moduleId;
    }

    public void setModuleId(Integer moduleId)
    {
        this.moduleId =
            moduleId;
    }

    public static ClientModule findClientModule(Integer clientId, String moduleCode)
    {
        Object
            parameters
            [
            ] =
            {
                clientId,
                moduleCode};
        String
            queryString =
            "select clientModule from com.sehinc.common.db.client.ClientModule as clientModule, com.sehinc.common.db.security.CapModule as module where clientModule.clientId = ? and clientModule.moduleId = module.id and module.code = ?";
        return (ClientModule) HibernateUtil.findUnique(queryString,
                                                       parameters);
    }

    public static List findAllClientModulesByClientId(Integer clientId)
    {
        Object
            parameters
            [
            ] =
            {clientId};
        String
            queryString =
            "select clientModule from com.sehinc.common.db.client.ClientModule as clientModule where clientModule.clientId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}