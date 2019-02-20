package com.sehinc.erosioncontrol.action.admin;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.erosioncontrol.action.base.BaseAction;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.PrimaryMenu;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class AdminBaseAction
    extends BaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AdminBaseAction.class);

    public abstract ActionForward adminAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception;

    public ActionForward doAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, Exception
    {
        return adminAction(mapping,
                           form,
                           request,
                           response);
    }

    private void setSelectedMenuItem(SecondaryMenu secondaryMenu, HttpServletRequest request)
    {
        try
        {
            if (request.getParameter("partners")
                != null)
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.ADMIN_PARTNER_SELECT_MENU_ITEM_NAME);
            }
            else
            {
                secondaryMenu.setCurrentItem(SecondaryMenu.ADMIN_CLIENT_SELECT_MENU_ITEM_NAME);
            }
        }
        catch (Exception e)
        {
            LOG.debug("Could not set SecondaryMenu. Error: "
                      + e.getMessage());
        }
    }

    protected void setPrimaryMenuItem(HttpServletRequest request)
        throws ServletException
    {
        PrimaryMenu
            primaryMenu =
            getPrimaryMenu(request);
        primaryMenu.setCurrentItem(PrimaryMenu.SYSTEM_ADMIN_ADMIN_MENU_ITEM_NAME);
    }

    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu((String) null,
                         request);
    }

    protected void setSecondaryMenu(String selectedMenuItem, HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.ADMIN_MENU_NAME);
        setSelectedMenuItem(secondaryMenu,
                            request);
        setSessionAttribute(SessionKeys.SECONDARY_MENU,
                            secondaryMenu,
                            request);
    }

    @Override
    public PrimaryMenu getPrimaryMenu(HttpServletRequest request)
    {
        PrimaryMenu
            menu =
            PrimaryMenu.getInstance(PrimaryMenu.SYSTEM_ADMIN_MENU_NAME);
        SessionService.setSessionAttribute(SessionKeys.PRIMARY_MENU,
                                           menu,
                                           request);
        return (PrimaryMenu) SessionService.getSessionAttribute(SessionKeys.PRIMARY_MENU,
                                                                request);
    }
    /*
        @Override
        public void finalizeAction(HttpServletRequest request)
            throws Exception
        {
            super.finalizeAction(request);
            setPrimaryMenuItem(request);
            setSecondaryMenu(request);
        }
    */
}