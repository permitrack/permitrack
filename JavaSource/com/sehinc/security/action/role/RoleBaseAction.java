package com.sehinc.security.action.role;

import com.sehinc.security.action.client.ClientBaseAction;
import com.sehinc.security.action.navigation.PrimaryMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RoleBaseAction
    extends ClientBaseAction
{
/*
    private static
    Logger
        LOG =
        Logger.getLogger(RoleBaseAction.class);
*/
/*
    private static
    String
        strMod =
        "com.sehinc.security.action.role.RoleBaseAction.";
*/

    public abstract ActionForward roleAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception;

    public ActionForward clientAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        return roleAction(mapping,
                          form,
                          request,
                          response);
    }

/*
    protected Integer getCapRoleIdFromRequestOrSession(HttpServletRequest request)
    {
        String
            strLog =
            strMod
            + "getCapRoleIdFromRequestOrSession ";
        CapRoleValue
            objRole;
        Integer
            i =
            0;
        if (request.getParameter(RequestKeys.ROLE_ID)
            != null)
        {
            i =
                new Integer(request.getParameter(RequestKeys.ROLE_ID));
        }
        else if (getSessionAttribute(SessionKeys.CAP_ROLE_VALUE)
                 != null)
        {
            if (getSessionAttribute(SessionKeys.CAP_ROLE_VALUE) instanceof CapRoleValue)
            {
                objRole =
                    (CapRoleValue) getSessionAttribute(SessionKeys.CAP_ROLE_VALUE);
                i =
                    objRole.getId();
            }
            else
            {
                LOG.debug(strLog
                          + "Session cap role value is not of type CapRoleValue.");
            }
        }
        else
        {
            LOG.debug(strLog
                      + "Unable to get role id from request or the session.");
        }
        LOG.debug(strLog
                  + "Cap Role Id = "
                  + i.toString());
        return i;
    }
*/

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws Exception
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.SECURITY_ROLE_MENU_ITEM_NAME);
    }
}