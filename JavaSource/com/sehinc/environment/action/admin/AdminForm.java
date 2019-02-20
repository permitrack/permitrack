package com.sehinc.environment.action.admin;

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
    String
        submit;

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

    public void reset()
    {
        this.clientId =
            null;
        this.submit =
            null;
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