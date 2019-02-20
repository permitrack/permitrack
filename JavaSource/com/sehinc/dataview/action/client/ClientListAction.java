/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: ClientListAction.java,v $
 * Revision 1.2  2005/10/07 20:54:55  clawler
 * Added CVS logging
 *
 *
 */

package com.sehinc.dataview.action.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.dataview.action.base.SessionKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ClientListAction
    extends ClientBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientListAction.class);

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        ClientForm
            clientForm =
            (ClientForm) form;
        clientForm.reset();
        //Get the DataView Online client list
        setSessionAttribute(SessionKeys.DV_ADMIN_CLIENT_SELECT_LIST,
                            ClientService.getActiveClientsByModule(CapModule.findByCode(CommonConstants.DATA_VIEW_MODULE)), request);
        LOG.info("In ClientListAction");
        return mapping.findForward("continue");
    }
}