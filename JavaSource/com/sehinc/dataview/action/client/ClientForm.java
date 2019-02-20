/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: ClientForm.java,v $
 *
 */

package com.sehinc.dataview.action.client;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @author clawler
 *
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ClientForm
    extends BaseValidatorForm
{
    private
    Integer
        clientId;

    /**
     * Returns CLIENT_ID.
     *
     * @return clientId
     */
    public Integer getClientId()
    {
        return clientId;
    }

    /**
     * Sets CLIENT_ID.
     *
     * @param clientId The clientId to set
     */
    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public void reset()
    {
        this.clientId =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
        if (clientId
            == null)
        {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                       new ActionMessage("error.no.client.selected"));
        }
    }
}
