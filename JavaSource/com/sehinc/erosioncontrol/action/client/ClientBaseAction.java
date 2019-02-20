package com.sehinc.erosioncontrol.action.client;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.erosioncontrol.action.base.BaseAction;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
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
                            "error.no.client.in.session",
                            request,
                            CommonConstants.FORWARD_ERROR);
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

    @Override
    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        PrimaryMenu
            menu =
            PrimaryMenu.getInstance(PrimaryMenu.CLIENT_MENU_NAME,
                                    getClientValue(request)
                                    != null
                                        ? getClientValue(request).getName()
                                        : "");
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           menu,
                                           request);
        return (PrimaryMenu) SessionService.getSessionAttribute(SessionKeys.PRIMARY_MENU,
                                                                request);
    }
}
