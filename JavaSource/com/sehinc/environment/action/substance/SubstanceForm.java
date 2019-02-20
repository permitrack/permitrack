package com.sehinc.environment.action.substance;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SubstanceForm
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
        partNum;
    private
    Integer
        clientId;
    private
    String
        statusCode;
    private
    Integer
        substanceTypeCode;
    private
    String
        substanceTypeDesc;

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

    public String getPartNum()
    {
        return partNum;
    }

    public void setPartNum(String partNum)
    {
        this.partNum =
            partNum;
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

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public Integer getSubstanceTypeCode()
    {
        return substanceTypeCode;
    }

    public void setSubstanceTypeCode(Integer substanceTypeCode)
    {
        this.substanceTypeCode =
            substanceTypeCode;
    }

    public String getSubstanceTypeDesc()
    {
        return substanceTypeDesc;
    }

    public void setSubstanceTypeDesc(String substanceTypeDesc)
    {
        this.substanceTypeDesc =
            substanceTypeDesc;
    }

    public void reset()
    {
        id =
            null;
        clientId =
            null;
        name =
            null;
        partNum =
            null;
        statusCode =
            null;
        substanceTypeCode =
            null;
        substanceTypeDesc =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}