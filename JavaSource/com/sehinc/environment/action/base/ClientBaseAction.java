package com.sehinc.environment.action.base;

import com.sehinc.common.value.client.ClientValue;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ClientBaseAction
    extends BaseAction
{
    public abstract ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        ClientValue
            clientValue =
            getClientValue(request);
        if (clientValue
            == null)
        {
            ActionForward
                errorHandler =
                handleError(mapping,
                            "error.client.not.found.in.session",
                            request);
            if (errorHandler
                != null)
            {
                return errorHandler;
            }
        }
        return clientAction(mapping,
                            form,
                            request,
                            response);
    }
}
