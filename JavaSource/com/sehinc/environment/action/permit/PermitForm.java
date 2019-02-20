package com.sehinc.environment.action.permit;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.util.DateUtil;
import org.apache.struts.action.ActionErrors;

import java.util.Date;

public class PermitForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    String
        name;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    Date
        activeTs;
    private
    Date
        inactiveTs;
    private
    String
        statusCode;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getActiveTs()
    {
        return activeTs;
    }

    public void setActiveTs(Date activeTs)
    {
        this.activeTs =
            activeTs;
    }

    public String getActiveTsString()
    {
        return DateUtil.mdyDate(activeTs);
    }

    public void setActiveTsString(String activeTs)
    {
        this.activeTs =
            DateUtil.parseDate(activeTs);
    }

    public Date getInactiveTs()
    {
        return inactiveTs;
    }

    public String getInactiveTsString()
    {
        return DateUtil.mdyDate(inactiveTs);
    }

    public void setInactiveTs(Date inactiveTs)
    {
        this.inactiveTs =
            inactiveTs;
    }

    public void setInactiveTsString(String inactiveTs)
    {
        this.inactiveTs =
            DateUtil.parseDate(inactiveTs);
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        name =
            null;
        description =
            null;
        statusCode =
            null;
        activeTs =
            null;
        inactiveTs =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}