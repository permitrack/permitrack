/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: ClientBaseAction.java,v $
 *
 */

package com.sehinc.dataview.action.client;

import com.sehinc.dataview.action.base.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class ClientBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ClientBaseAction.class);

    public abstract ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        LOG.info("In ClientBaseAction");
        return clientAction(mapping,
                            form,
                            request,
                            response);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }

/*
    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }
*/
}