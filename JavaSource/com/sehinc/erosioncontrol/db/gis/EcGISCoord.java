package com.sehinc.erosioncontrol.db.gis;

import com.sehinc.common.db.hibernate.HibernateData;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.value.client.ClientValue;
import org.apache.log4j.Logger;

import java.util.List;

@SuppressWarnings("unused")
public class EcGISCoord
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(EcGISCoord.class);
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

    public EcGISCoord()
    {
    }

    public EcGISCoord(Integer id)
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

    public static List findByParcelNumber(ClientValue clientValue, String parcelNumber)
    {
/*
        EcGISCoord
            gisCoord;
*/
        Object
            parameters
            [
            ] =
            {
                clientValue.getId(),
                "%"
                + parcelNumber
                + "%"};
        String
            queryString =
            "select gisCoord from EcGISCoord as gisCoord where gisCoord.clientId = ? and gisCoord.parcelNumber like ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static EcGISCoord findUniqueByParcelNumber(ClientValue clientValue, String parcelNumber)
    {
/*
        EcGISCoord
            gisCoord;
*/
        Object
            parameters
            [
            ] =
            {
                clientValue.getId(),
                parcelNumber};
        String
            queryString =
            "select gisCoord from EcGISCoord as gisCoord where gisCoord.clientId = ? and gisCoord.parcelNumber = ?";
        List
            results =
            HibernateUtil.findTop(queryString,
                                  parameters,
                                  1);
        if (results
            == null
            || results.size()
               == 0)
        {
            return null;
        }
        return (EcGISCoord) results.get(0);
    }

    public static Integer count(ClientValue clientValue)
    {
/*
        EcGISCoord
            gisCoord;
*/
        Object
            parameters
            [
            ] =
            {clientValue.getId()};
        String
            queryString =
            "select count(gisCoord) from EcGISCoord as gisCoord where gisCoord.clientId = ?";
        return (Integer) HibernateUtil.findUnique(queryString,
                                                  parameters);
    }

    public static List findAllByClient(ClientValue clientValue)
    {
        Object
            parameters
            [
            ] =
            {clientValue.getId()};
        String
            queryString =
            "select gisCoord from EcGISCoord as gisCoord where gisCoord.clientId = ?";
        return HibernateUtil.find(queryString,
                                  parameters);
    }

    public static int deleteAllByClient(ClientValue clientValue)
    {
        Object
            parameters
            [
            ] =
            {clientValue.getId()};
        String
            queryString =
            "delete from EcGISCoord where clientId = ?";
        return HibernateUtil.update(queryString,
                                    parameters);
    }
}