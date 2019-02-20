package com.sehinc.portal.action.base;

import com.sehinc.common.action.base.BaseActionUnsecure;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagePermissionDenied
    extends BaseActionUnsecure
{
    private static
    Logger
        LOG =
        Logger.getLogger(PagePermissionDenied.class);

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        String
            strLog =
            "PagePermissionDenied.doAction ";
        LOG.debug(strLog
                  + "in action");
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }
}