package com.sehinc.environment.action.permit;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitCreatePageAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitCreatePageAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "PermitCreatePageAction. ";
        String
            strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new permit.");
            addError(new ActionMessage("permit.create.unauthorized"), request);
            return mapping.findForward("permit.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new permit page.");
        try
        {
            PermitForm
                objP =
                (PermitForm) form;
            objP.reset();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("permit.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("permit.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.PERMIT_LIST_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_CREATE_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}