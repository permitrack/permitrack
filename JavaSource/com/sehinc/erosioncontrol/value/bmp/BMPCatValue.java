package com.sehinc.erosioncontrol.value.bmp;

import com.sehinc.erosioncontrol.db.bmp.EcBmpCategory;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

public class BMPCatValue
    implements java.io.Serializable
{
    private
    Integer
        id;
    private
    Integer
        clientId;
    private
    String
        name;
    private
    String
        statusCode;

    public BMPCatValue()
    {
    }

    public BMPCatValue(EcBmpCategory bmp)
    {
        this.id =
            bmp.getId();
        this.name =
            bmp.getName();
        this.clientId =
            bmp.getClientId();
        this.statusCode =
            bmp.getStatus()
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

    public boolean equals(Object o)
    {
        if (o instanceof BMPCatValue)
        {
            BMPCatValue
                obj =
                (BMPCatValue) o;
            return obj.getId()
                .equals(id);
        }
        return false;
    }
}
