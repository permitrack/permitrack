package com.sehinc.environment.action.permit;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permit.EnvPermit;
import com.sehinc.environment.db.permit.EnvPermitDetail;
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
import java.util.ArrayList;
import java.util.List;

public class PermitViewPageAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitViewPageAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitViewPageAction. ";
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
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("permit.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.view.page.not.allowed",
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
        else if (getPermit(request)
                 != null)
        {
            permitId =
                (Integer) getPermit(request);
            LOG.debug("Getting the permitId="
                      + permitId
                      + " from the session.");
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
        List
            permitDetailList =
            EnvPermitDetail.findByPermitId(permitId);
        setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ACTIVE_LIST_BY_PERMIT_ID,
                            permitDetailList,
                            request);
        setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                            envPermit.getId(),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_DETAIL_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_DETAIL_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_DETAIL_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_DETAIL_DELETE),
                            request);
        setSessionAttribute(SessionKeys.EV_PERMIT_DETAIL_ID,
                            null,
                            request);
        ArrayList
            pmtFacilityList =
            new ArrayList();
        try
        {
            pmtFacilityList =
                PermitService.findPermittedFacilities(envPermit.getId());
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("permit.facility.error.load.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("permit.facility.error.load.failed"), request);
        }
        setSessionAttribute(SessionKeys.EV_PERMIT_FACILITY_ACTIVE_LIST,
                            pmtFacilityList,
                            request);
        ArrayList
            pmtSubstanceTypeList =
            new ArrayList();
        try
        {
            pmtSubstanceTypeList =
                PermitService.findPermittedSubstances(envPermit.getId());
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("permit.detail.substance.error.load.failed"));
            LOG.error(e.getMessage());
            addError(new ActionMessage("permit.substance.error.load.failed"), request);
        }
        setSessionAttribute(SessionKeys.EV_PERMIT_SUBSTANCE_ACTIVE_LIST,
                            pmtSubstanceTypeList,
                            request);
        setSessionAttribute(SessionKeys.EV_FACILITY_ID,
                            null,
                            request);
        setSessionAttribute(SessionKeys.PERMIT_FACILITY_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_FACILITY_CREATE),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_FACILITY_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_FACILITY_DELETE),
                            request);
        setSessionAttribute(SessionKeys.EV_SUBSTANCE,
                            null,
                            request);
        setSessionAttribute(SessionKeys.PERMIT_SUBSTANCE_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_SUBSTANCE_CREATE),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_SUBSTANCE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_SUBSTANCE_DELETE),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PERMIT_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(null);
    }
}
