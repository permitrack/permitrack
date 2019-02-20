package com.sehinc.erosioncontrol.action.base;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings(value = {
    "serial",
    "unused"})
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
        return mapping.findForward("continue");
    }
}