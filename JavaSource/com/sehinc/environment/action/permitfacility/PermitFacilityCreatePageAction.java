package com.sehinc.environment.action.permitfacility;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.permit.EnvPermit;
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
import java.util.List;

public class PermitFacilityCreatePageAction
    extends PermitFacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitFacilityCreatePageAction.class);

    public ActionForward permitFacilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitFacilityCreatePageAction. ";
        strLog =
            strMod
            + "permitFacilityAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_FACILITY_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new permitted facility.");
            addError(new ActionMessage("permit.facility.create.unauthorized"), request);
            return mapping.findForward("permit.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create a new permit facility page.");
        try
        {
            PermitFacilityForm
                pfForm =
                (PermitFacilityForm) form;
            pfForm.reset();
            Integer
                permitId =
                (Integer) getSessionAttribute(SessionKeys.EV_PERMIT_ID,
                                              request);
            EnvPermit
                permit =
                new EnvPermit(permitId);
            permit.load();
            pfForm.setPermitId(permitId);
            pfForm.setPermitName(permit.getName());
            pfForm.setPermitDesc(permit.getDescription());
            ClientValue
                clientValue =
                getClientValue(request);
            List
                facilities =
                EnvFacility.findByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_FACILITY_LIST,
                                facilities,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("permit.facility.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("permit.facility.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PERMIT_FACILITY_MENU_ITEM);
    }
}

