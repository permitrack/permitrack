package com.sehinc.environment.action.scc;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SccForm
    extends BaseValidatorForm
{
    private
    Integer
        id;
    private
    String
        number;
    private
    String
        description;
    private
    Integer
        clientId;
    private
    String
        majIndustrialGroup;
    private
    String
        rawMaterial;
    private
    String
        emittingProcess;

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getId()
    {
        return id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number =
            number;
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

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Integer getClientId()
    {
        return clientId;
    }

    public String getMajIndustrialGroup()
    {
        return majIndustrialGroup;
    }

    public void setMajIndustrialGroup(String majIndustrialGroup)
    {
        this.majIndustrialGroup =
            majIndustrialGroup;
    }

    public String getRawMaterial()
    {
        return rawMaterial;
    }

    public void setRawMaterial(String rawMaterial)
    {
        this.rawMaterial =
            rawMaterial;
    }

    public String getEmittingProcess()
    {
        return emittingProcess;
    }

    public void setEmittingProcess(String emittingProcess)
    {
        this.emittingProcess =
            emittingProcess;
    }

    public void reset()
    {
        id =
            null;
        number =
            null;
        description =
            null;
        clientId =
            null;
        majIndustrialGroup =
            null;
        rawMaterial =
            null;
        emittingProcess =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
