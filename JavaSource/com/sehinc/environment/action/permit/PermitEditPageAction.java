package com.sehinc.environment.action.permit;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitEditPageAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitEditPageAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitEditPageAction. ";
        strLog =
            strMod
            + "permitAction() ";
        LOG.info(strLog
                 + "in action");
        PermitForm
            permitForm =
            (PermitForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("permit.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("permit.list.page");
        }
        Integer
            permitId;
        LOG.debug("permitId="
                  + request.getParameter(RequestKeys.EV_PERMIT_ID));
        if (request.getParameter(RequestKeys.EV_PERMIT_ID)
            != null)
        {
            permitId =
                new Integer(request.getParameter(RequestKeys.EV_PERMIT_ID));
        }
        else if (request.getAttribute(RequestKeys.EV_PERMIT_ID)
                 != null)
        {
            permitId =
                (Integer) request.getAttribute(RequestKeys.EV_PERMIT_ID);
        }
        else if (getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                     request)
                 != null)
        {
            permitId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                              request);
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("permit.error.no.permit.on.request"));
            addError(new ActionMessage("permit.error.no.permit.on.request"), request);
            return mapping.findForward("permit.list.page");
        }
        EnvPermit
            envPermit =
            new EnvPermit(permitId);
        try
        {
            envPermit.load();
            if (!envPermit.getStatus()
                .getCode()
                .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The requested Permit ID = "
                                    + permitId
                                    + " does not exist.");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {permitId};
            LOG.error(ApplicationResources.getProperty("permit.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("permit.error.load.failed",
                                       parameters), request);
            return mapping.findForward("permit.list.page");
        }
        permitForm.setId(envPermit.getId());
        permitForm.setName(envPermit.getName());
        permitForm.setDescription(envPermit.getDescription());
        permitForm.setClientId(envPermit.getClientId());
        permitForm.setActiveTs(envPermit.getActiveTs());
        permitForm.setInactiveTs(envPermit.getInactiveTs());
        permitForm.setStatusCode(envPermit.getStatus()
                                     .getCode());
        setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                            envPermit.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_EDIT_MENU_ITEM);
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.PERMIT_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.PERMIT_VIEW_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }
}
