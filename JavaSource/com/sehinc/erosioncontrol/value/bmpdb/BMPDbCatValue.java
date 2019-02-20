package com.sehinc.erosioncontrol.value.bmpdb;

import com.sehinc.erosioncontrol.db.bmpdb.EcBmpCategoryDb;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.List;

public class BMPDbCatValue
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
        clientId;
    private
    String
        statusCode;
    private
    List<BMPDbValue>
        bmpList;

    public BMPDbCatValue()
    {
    }

    public BMPDbCatValue(EcBmpCategoryDb cat)
    {
        this.id =
            cat.getId();
        this.name =
            cat.getName();
        this.clientId =
            cat.getClientId();
        this.statusCode =
            cat.getStatus()
                .getCode();
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

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
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

    public final String getStatusCode()
    {
        return statusCode;
    }

    public final void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public boolean isActive()
    {
        return StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode);
    }

    public List<BMPDbValue> getBmpList()
    {
        return bmpList;
    }

    public void setBmpList(List<BMPDbValue> bmpList)
    {
        this.bmpList =
            bmpList;
    }

    public boolean equals(Object o)
    {
        if (o instanceof BMPDbCatValue)
        {
            BMPDbCatValue
                obj =
                (BMPDbCatValue) o;
            return obj.getId()
                .equals(id);
        }
        return false;
    }
}
