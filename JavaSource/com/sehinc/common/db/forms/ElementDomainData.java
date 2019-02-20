package com.sehinc.common.db.forms;

import com.sehinc.common.db.hibernate.HibernateData;
import org.apache.log4j.Logger;

public class ElementDomainData
    extends HibernateData
{
    @SuppressWarnings("unused")
    private static
    Logger
        LOG =
        Logger.getLogger(ElementDomainData.class);
    private
    Integer
        elementId;
    private
    Integer
        domainId;
    private
    Integer
        defaultDomainValueId;
    private
    String
        allowSelect;

    public ElementDomainData()
    {
    }

    public ElementDomainData(Integer id)
    {
        setId(id);
    }

    public Integer getDefaultDomainValueId()
    {
        return defaultDomainValueId;
    }

    public Integer getDomainId()
    {
        return domainId;
    }

    public Integer getElementId()
    {
        return elementId;
    }

    public void setDefaultDomainValueId(Integer integer)
    {
        defaultDomainValueId =
            integer;
    }

    public void setDomainId(Integer integer)
    {
        domainId =
            integer;
    }

    public void setElementId(Integer integer)
    {
        elementId =
            integer;
    }

    public String getAllowSelect()
    {
        return allowSelect;
    }

    public void setAllowSelect(String string)
    {
        allowSelect =
            string;
    }
}
