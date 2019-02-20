package com.sehinc.environment.action.facility;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FacilityListPageAction
    extends FacilityBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(FacilityListPageAction.class);

    public ActionForward facilityAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Facilities",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.FACILITY_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.FACILITY_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_FACILITY_DELETE),
                            request);
        ClientValue
            clientValue =
            getClientValue(request);
        List
            facilityList =
            EnvFacility.findByClientId(clientValue.getId());
        setSessionAttribute(SessionKeys.EV_FACILITY_ACTIVE_LIST_BY_CLIENT,
                            facilityList,
                            request);
        setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                            null,
                            request);
        setSessionAttribute(SessionKeys.EV_FACILITY_NAME,
                            null,
                            request);
        return mapping.findForward("continue");
    }
}