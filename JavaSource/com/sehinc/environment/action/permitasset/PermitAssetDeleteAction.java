package com.sehinc.environment.action.permitasset;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermitAssetDeleteAction
    extends PermitAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PermitAssetDeleteAction.class);

    public ActionForward permitAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "PermitAssetDeleteAction. ";
        strLog =
            strMod
            + "permitAssetAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PERMIT_ASSET_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("permit.asset.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("permit.asset.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("permit.detail.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("permit.asset.delete.cancel.action"), request);
            return mapping.findForward("permit.detail.view.page");
        }
        else
        {
            Integer
                permitAssetId;
            LOG.debug("permitAssetId="
                      + request.getParameter(RequestKeys.EV_PERMIT_ASSET_ID));
            if (request.getParameter(RequestKeys.EV_PERMIT_ASSET_ID)
                != null)
            {
                permitAssetId =
                    new Integer(request.getParameter(RequestKeys.EV_PERMIT_ASSET_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("permit.asset.error.no.permit.asset.on.request"));
                addError(new ActionMessage("permit.asset.error.no.permit.asset.on.request"), request);
                return mapping.findForward("permit.detail.view.page");
            }
            EnvPermitAsset
                envPermitAsset =
                new EnvPermitAsset(permitAssetId);
            try
            {
                envPermitAsset.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {permitAssetId};
                LOG.error(ApplicationResources.getProperty("permit.asset.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.asset.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("permit.detail.view.page");
            }
            addMessage(new ActionMessage("permit.asset.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}