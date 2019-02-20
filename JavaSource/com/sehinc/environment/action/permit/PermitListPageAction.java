package com.sehinc.environment.action.permit;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.permit.EnvPermit;
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

public class PermitListPageAction
    extends PermitBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitListPageAction.class);

    public ActionForward permitAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In PermitListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Permits",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.PERMIT_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.PERMIT_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PERMIT_DELETE),
                            request);
        ClientValue
            clientValue =
            getClientValue(request);
        List
            permitList =
            EnvPermit.findByClientId(clientValue.getId());
        setSessionAttribute(SessionKeys.EV_PERMIT_ACTIVE_LIST_BY_CLIENT,
                            permitList,
                            request);
        setSessionAttribute(SessionKeys.EV_PERMIT_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }
}