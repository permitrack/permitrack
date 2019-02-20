package com.sehinc.portal.db.info;

import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.db.user.StatusData;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class InfoTips
    extends StatusData
    implements java.io.Serializable
{
    public static
    String
        INFO_TIPS_BY_STATUS_CODE =
        "com.sehinc.portal.db.info.InfoTips.byStatusCode";
    private
    String
        message;

    public InfoTips()
    {
    }

    public InfoTips(Integer id)
    {
        setId(id);
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message =
            message;
    }

    public static List findActive()
    {
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        return HibernateUtil.findByNamedQuery(INFO_TIPS_BY_STATUS_CODE,
                                              parameters);
    }

    public static InfoTips findRandomActive()
    {
        InfoTips
            tip =
            new InfoTips();
        Object
            parameters
            [
            ]
            [
            ] =
            {
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE}};
        List
            tipsList =
            HibernateUtil.findByNamedQuery(INFO_TIPS_BY_STATUS_CODE,
                                           parameters);
        Collections.shuffle(tipsList);
        Iterator
            i =
            tipsList.iterator();
        if (i.hasNext())
        {
            tip =
                (InfoTips) i.next();
        }
        return tip;
    }

    public boolean equals(Object o)
    {
        if (o instanceof InfoTips)
        {
            InfoTips
                other =
                (InfoTips) o;
            return other.getId()
                .equals(this.getId());
        }
        return false;
    }
}