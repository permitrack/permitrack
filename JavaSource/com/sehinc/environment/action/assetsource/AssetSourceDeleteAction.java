package com.sehinc.environment.action.assetsource;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AssetSourceDeleteAction
    extends AssetSourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSourceDeleteAction.class);

    public ActionForward assetSourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSourceDeleteAction. ";
        strLog =
            strMod
            + "assetSourceAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_ASSET_SOURCE_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("asset.source.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("asset.source.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("asset.view.page");
        }
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.source.delete.cancel.action"), request);
            return mapping.findForward("asset.view.page");
        }
        else
        {
            Integer
                assetSourceId;
            LOG.debug("assetSourceId="
                      + request.getParameter(RequestKeys.EV_ASSET_SOURCE_ID));
            if (request.getParameter(RequestKeys.EV_ASSET_SOURCE_ID)
                != null)
            {
                assetSourceId =
                    new Integer(request.getParameter(RequestKeys.EV_ASSET_SOURCE_ID));
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("asset.source.error.no.asset.source.on.request"));
                addError(new ActionMessage("asset.source.error.no.asset.source.on.request"), request);
                return mapping.findForward("asset.view.page");
            }
            EnvAssetSource
                envAssetSource =
                new EnvAssetSource(assetSourceId);
            try
            {
                envAssetSource.load();
                envAssetSource.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envAssetSource.save(userValue);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetSourceId};
                LOG.error(ApplicationResources.getProperty("asset.source.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.source.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("asset.view.page");
            }
            addMessage(new ActionMessage("asset.source.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}
