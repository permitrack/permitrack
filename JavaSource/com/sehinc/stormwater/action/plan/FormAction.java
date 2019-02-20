package com.sehinc.stormwater.action.plan;

import com.sehinc.stormwater.action.base.BaseAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormAction
    extends BaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(FormAction.class);
*/

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
//        LOG.info("In FormAction doAction");
        return mapping.findForward("success");
    }

}
