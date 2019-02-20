package com.sehinc.erosioncontrol.value.gis;

import com.sehinc.erosioncontrol.db.gis.EcGISCoord;
import org.apache.log4j.Logger;

public class GISCoordValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(GISCoordValue.class);
    private
    Integer
        clientId;
    private
    String
        parcelNumber;
    private
    String
        gisX;
    private
    String
        gisY;
    private
    Boolean
        isDeleted;

    public GISCoordValue()
    {
    }

    public GISCoordValue(EcGISCoord gisCoord)
    {
        clientId =
            gisCoord.getClientId();
        parcelNumber =
            gisCoord.getParcelNumber();
        gisX =
            gisCoord.getGisX();
        gisY =
            gisCoord.getGisY();
        isDeleted =
            false;
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

    public String getParcelNumber()
    {
        return this.parcelNumber;
    }

    public void setParcelNumber(String parcelNumber)
    {
        this.parcelNumber =
            parcelNumber;
    }

    public String getGisX()
    {
        return this.gisX;
    }

    public void setGisX(String gisX)
    {
        this.gisX =
            gisX;
    }

    public String getGisY()
    {
        return this.gisY;
    }

    public void setGisY(String gisY)
    {
        this.gisY =
            gisY;
    }

    public Boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted)
    {
        this.isDeleted =
            isDeleted;
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nparcelNumber="
                      + parcelNumber);
        buffer.append("\ngisX="
                      + gisX);
        buffer.append("\ngisY="
                      + gisY);
        buffer.append("\nisDeleted="
                      + isDeleted.toString());
        buffer.append("\n\n");
        return buffer.toString();
    }
}