package com.sehinc.portal.action.base;

import com.sehinc.common.action.base.BaseActionUnsecure;
import com.sehinc.portal.PortalUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatchAction
    extends BaseActionUnsecure
{
    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return mapping.findForward(PortalUtils.getUserMarkup(request));
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
    }
}