package com.sehinc.environment.action.assetsubstance;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssetSubstanceDeleteAction
    extends AssetSubstanceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSubstanceDeleteAction.class);

    public ActionForward assetSubstanceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSubstanceDeleteAction. ";
        strLog =
            strMod
            + "assetSubstanceAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SUBSTANCE_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("asset.substance.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("asset.substance.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("asset.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.substance.delete.cancel.action"), request);
            return mapping.findForward("asset.view.page");
        }
        else
        {
            Integer
                assetSubstanceId;
            LOG.debug("assetSubstanceId="
                      + request.getParameter(RequestKeys.EV_ASSET_SUBSTANCE_ID));
            if (request.getParameter(RequestKeys.EV_ASSET_SUBSTANCE_ID)
                != null)
            {
                assetSubstanceId =
                    new Integer(request.getParameter(RequestKeys.EV_ASSET_SUBSTANCE_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("asset.substance.error.no.asset.substance.on.request"));
                addError(new ActionMessage("asset.substance.error.no.asset.substance.on.request"), request);
                return mapping.findForward("asset.view.page");
            }
            EnvAssetSubstance
                envAssetSubstance =
                new EnvAssetSubstance(assetSubstanceId);
            try
            {
                envAssetSubstance.load();
                envAssetSubstance.delete();
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetSubstanceId};
                LOG.error(ApplicationResources.getProperty("asset.substance.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.substance.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("asset.view.page");
            }
            addMessage(new ActionMessage("asset.substance.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}