package com.sehinc.environment.action.facility;

import com.sehinc.common.db.user.CapState;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.SecondaryMenu;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityEditPageAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityEditPageAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "FacilityEditPageAction. ";
        String
            strLog =
            strMod
            + "facilityAction() ";
        LOG.info(strLog
                 + "in action");
        FacilityForm
            facilityForm =
            (FacilityForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_FACILITY_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("facility.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("facility.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("facility.list.page");
        }
        Integer
            facilityId;
        LOG.debug("facilityId="
                  + request.getParameter(RequestKeys.EV_FACILITY_ID));
        if (request.getParameter(RequestKeys.EV_FACILITY_ID)
            != null)
        {
            facilityId =
                new Integer(request.getParameter(RequestKeys.EV_FACILITY_ID));
        }
        else if (getFacility(request)
                 != null)
        {
            facilityId =
                (Integer) getFacility(request);
            LOG.debug("Getting the facilityId="
                      + facilityId
                      + " from the session.");
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("facility.error.no.facility.on.request"));
            addError(new ActionMessage("facility.error.no.facility.on.request"), request);
            return mapping.findForward("facility.list.page");
        }
        EnvFacility
            envFacility =
            new EnvFacility(facilityId);
        try
        {
            envFacility.load();
            FacilityService.populateFacilityForm(facilityForm,
                                                 envFacility);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {facilityId};
            LOG.error(ApplicationResources.getProperty("facility.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("facility.error.load.failed",
                                       parameters), request);
            return mapping.findForward("facility.list.page");
        }
        List
            lstC =
            CapState.findNonArmedForcesStates();
        setSessionAttribute(SessionKeys.STATE_LIST,
                            lstC,
                            request);
        setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                            envFacility.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        SecondaryMenu
            secondaryMenu =
            SecondaryMenu.getInstance(SecondaryMenu.FACILITY_VIEW_MENU);
        secondaryMenu.setCurrentItem(SecondaryMenu.FACILITY_VIEW_MENU_ITEM);
        setSecondaryMenu(secondaryMenu,
                         request);
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.FACILITY_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.FACILITY_EDIT_MENU_ITEM);
    }
}