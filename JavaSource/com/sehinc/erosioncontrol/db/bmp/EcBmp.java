package com.sehinc.erosioncontrol.db.bmp;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import org.apache.log4j.Logger;

import java.util.List;

public class EcBmp
    extends StatusData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcBmp.class);
    private
    Integer
        clientId;
    private
    EcBmpCategory
        category;
    private
    String
        name;
    private
    String
        description;
    private
    String
        weblink;

    public EcBmp()
    {
    }

    public EcBmp(Integer id)
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

    public EcBmpCategory getCategory()
    {
        return this.category;
    }

    public void setCategory(EcBmpCategory category)
    {
        this.category =
            category;
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

    public String getWeblink()
    {
        return weblink;
    }

    public void setWeblink(String weblink)
    {
        this.weblink =
            weblink;
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
            "select data from EcBmp as data where data.category.clientId = ? and data.status.code = ?";
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
            "select data from EcBmp as data where data.clientId =?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nid=")
            .append(getId());
        buffer.append("\nclientId=")
            .append(clientId);
        buffer.append("\nname=")
            .append(name);
        buffer.append("\ndescription=")
            .append(description);
        buffer.append("\ncategory=")
            .append(category);
        buffer.append("\n\n");
        return buffer.toString();
    }
}