package com.sehinc.erosioncontrol.action.admin;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AdminForm
    extends BaseValidatorForm
{
    private
    Integer
        clientId;
    private
    Integer
        secondaryId;
    private
    String
        submit;
    private
    String
        clientType;

    public String getClientType()
    {
        return clientType;
    }

    public void setClientType(String clientType)
    {
        this.clientType =
            clientType;
    }

    public String getSubmit()
    {
        return submit;
    }

    public void setSubmit(String submit)
    {
        this.submit =
            submit;
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

    public Integer getSecondaryId()
    {
        return secondaryId;
    }

    public void setSecondaryId(Integer secondaryId)
    {
        this.secondaryId =
            secondaryId;
    }

    public void reset()
    {
        this.clientId =
            null;
        this.secondaryId =
            null;
        this.submit =
            null;
        this.clientType =
            "primary";
    }

    public void validateForm(ActionErrors errors)
    {
        if (clientId
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("errors.no.client.selected",
                                         "Client"));
        }
    }
}
