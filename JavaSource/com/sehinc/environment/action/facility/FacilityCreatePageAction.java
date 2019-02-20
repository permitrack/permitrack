package com.sehinc.environment.action.facility;

import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.environment.action.base.SessionKeys;
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
import java.util.List;

public class FacilityCreatePageAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityCreatePageAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "FacilityCreatePageAction. ";
        String
            strLog =
            strMod
            + "facilityAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new facility.");
            addError(new ActionMessage("facility.create.unauthorized"), request);
            return mapping.findForward("facility.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create a new facility.");
        try
        {
            FacilityForm
                facilityForm =
                (FacilityForm) form;
            facilityForm.reset();
            List
                lstC =
                CapState.findNonArmedForcesStates();
            setSessionAttribute(SessionKeys.STATE_LIST,
                                lstC,
                                request);
            setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                null,
                                request);
            setSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                                null,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("facility.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("facility.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        super.setSecondaryMenu(request);
        getSecondaryMenu(request).setCurrentItem(SecondaryMenu.FACILITY_CREATE_MENU_ITEM);
    }
}