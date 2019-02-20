package com.sehinc.erosioncontrol.db.bmp;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class EcBmpCategory
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcBmpCategory.class);
    private
    Integer
        clientId;
    private
    String
        name;

    public EcBmpCategory()
    {
    }

    public EcBmpCategory(Integer id)
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

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid="
                      + getId());
        buffer.append("\nclientId="
                      + clientId);
        buffer.append("\nname="
                      + name);
        buffer.append("\nstatus="
                      + this.getStatus()
            .getDescription());
        buffer.append("\n\n");
        return buffer.toString();
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
            "select data from EcBmpCategory as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }
}