/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: DispatchAction.java,v $
 * Revision 1.2  2005/10/07 20:55:01  clawler
 * Added CVS logging
 *
 *
 */

package com.sehinc.dataview.action.base;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(DispatchAction.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return mapping.findForward(mapping.getInput());
        //return (new ActionForward(mapping.getInput()));
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
}