package com.sehinc.environment.action.facility;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.facility.EnvFacility;
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

public class FacilityViewPageAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityViewPageAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "FacilityViewPageAction. ";
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
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("facility.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("facility.view.page.not.allowed",
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
            setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                                envFacility.getId(),
                                request);
            setSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                                envFacility.getName(),
                                request);
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
        try
        {
            List
                facilityContactList =
                FacilityService.getFacilityContacts(facilityId);
            request.setAttribute(RequestKeys.EV_FACILITY_CONTACT_LIST,
                                 facilityContactList);
            setSessionAttribute(SessionKeys.FACILITY_CONTACT_CAN_CREATE,
                                securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_CONTACT_CREATE),
                                request);
            setSessionAttribute(SessionKeys.FACILITY_CONTACT_CAN_DELETE,
                                securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_CONTACT_DELETE),
                                request);
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("facility.error.contact.load.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("facility.error.contact.load.failed"), request);
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.FACILITY_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(null);
    }
}