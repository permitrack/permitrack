/*
 * Copyright (C) 2005 SEH Technology Solutions Inc.
 *
 * $Log: BaseAction.java,v $
 * Revision 1.5  2005/11/11 21:55:50  clawler
 * Bugfixes
 *
 * Revision 1.4  2005/10/28 16:52:44  clawler
 * Hibernate Conversion Updates
 *
 * Revision 1.3  2005/10/19 22:45:05  clawler
 * Modifications to use hibernate
 *
 * Revision 1.2  2005/10/07 20:55:04  clawler
 * Added CVS logging
 *
 *
 */

package com.sehinc.dataview.action.base;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.portal.action.navigation.PortalMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAction
    extends com.sehinc.common.action.base.BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(BaseAction.class);

    public final ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return super.execute(mapping,
                             form,
                             request,
                             response);
    }

    /**
     * Get the User.
     */
    public static UserValue getUser(HttpServletRequest request)
    {
        return SessionService.getUserValue(request);
        /*
         (UserValue) request.getSession()
            .getAttribute(SessionKeys.USER);
        */
    }

    /**
     * Get the Client Value
     */
    public ClientValue getClientValue(HttpServletRequest request)
    {
        /*
                return SessionService.getClientValue(request,
                                                     CommonConstants.DATA_VIEW_MODULE);
        */
        ClientValue
            clientValue =
            SessionService.getClientValue(request,
                                          CommonConstants.DATA_VIEW_MODULE);
        if (clientValue
            == null)
        {
            clientValue =
                getClientValueDefault(CommonConstants.DATA_VIEW_MODULE, request);
            setSessionAttribute(SessionKeys.DVO_CLIENT,
                                clientValue,
                                request);
        }
        return clientValue;
    }

    public void initializeAction(HttpServletRequest request)
        throws Exception
    {
    }

    public boolean isDeleted(HttpServletRequest request)
    {
        String
            submitString =
            (String) request.getAttribute("submit");
        LOG.debug("submitString = "
                  + submitString);
        return submitString
               != null
               && submitString.equalsIgnoreCase("Delete");
    }

    protected void setPortalMenu(HttpServletRequest request)
    {
        PortalMenu
            portalMenu =
            (PortalMenu) getSessionAttribute(SessionKeys.PORTAL_MENU, request);
        portalMenu.setCurrentItemByCode(CommonConstants.DATA_VIEW_MODULE);
    }

/*
    protected SecurityManager getSecurityManager()
        throws Exception
    {
        return this.getSecurityManager(request);
    }
*/

/*
    protected void setSessionAttribute(String name, Object obj, HttpServletRequest request)
    {
        request.getSession()
            .setAttribute(name,
                          obj);
    }
*/

/*
    protected Object getSessionAttribute(String name, HttpServletRequest request)
    {
        return request.getSession()
            .getAttribute(name);
    }
*/

/*
    protected void removeSessionAttribute(String name, HttpServletRequest request)
    {
        request.getSession()
            .removeAttribute(name);
    }
*/
}
