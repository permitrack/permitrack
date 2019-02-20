package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

@SuppressWarnings(value = {
    "serial",
    "unused",
    "unchecked"})
public class DomainValueData
    extends HibernateData
{
    private static
    Logger
        LOG =
        Logger.getLogger(DomainValueData.class);
    private
    String
        domainValue;
    private
    Integer
        orderNum;
    private
    Integer
        domainId;

    public DomainValueData()
    {
    }

    public DomainValueData(Integer id)
    {
        setId(id);
    }

    public String toString()
    {
        return "DOMAIN_VALUE.DOMAIN_VALUE_ID: "
               + getId().toString()
               +
               "\nDOMAIN_VALUE.DOMAIN_VALUE: "
               + domainValue
               +
               "\nDOMAIN_VALUE.ORDER_NUM: "
               + orderNum.toString()
               +
               "\nELEMENT_VALUE.DOMAIN_ID: "
               + domainId.toString();
    }

    public Integer getDomainId()
    {
        return domainId;
    }

    public String getDomainValue()
    {
        return domainValue;
    }

    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setDomainId(Integer integer)
    {
        domainId =
            integer;
    }

    public void setDomainValue(String string)
    {
        domainValue =
            string;
    }

    public void setOrderNum(Integer integer)
    {
        orderNum =
            integer;
    }
}