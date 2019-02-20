package com.sehinc.environment.action.processasset;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class ProcessAssetDeleteAction
    extends ProcessAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessAssetDeleteAction.class);

    public ActionForward processAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessAssetDeleteAction. ";
        strLog =
            strMod
            + "processAssetAction() ";
        LOG.info(strLog
                 + "in action");
        UserValue
            userValue =
            getUserValue(request);
        com.sehinc.common.security.SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PROCESS_ASSET_DELETE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "delete"};
            LOG.info(ApplicationResources.getProperty("process.asset.delete.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("process.asset.delete.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("process.view.page");
        }
        Integer
            processAssetId;
        LOG.debug("processAssetId="
                  + request.getParameter(RequestKeys.EV_PROCESS_ASSET_ID));
        if (request.getParameter(RequestKeys.EV_PROCESS_ASSET_ID)
            != null)
        {
            processAssetId =
                new Integer(request.getParameter(RequestKeys.EV_PROCESS_ASSET_ID));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("process.asset.error.no.process.asset.on.request"));
            addError(new ActionMessage("process.asset.error.no.process.asset.on.request"), request);
            return mapping.findForward("process.view.page");
        }
        EnvProcessAsset
            envProcessAsset =
            new EnvProcessAsset(processAssetId);
        Integer
            assetId;
        try
        {
            envProcessAsset.load();
            assetId =
                envProcessAsset.getAssetId();
            envProcessAsset.setInactiveTs(new Date());
            envProcessAsset.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
            envProcessAsset.save(userValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {processAssetId};
            LOG.error(ApplicationResources.getProperty("process.asset.delete.page.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("process.asset.delete.page.failed",
                                       parameters), request);
            return mapping.findForward("process.view.page");
        }
        try
        {
            EnvAsset
                asset =
                new EnvAsset(assetId);
            asset.load();
            asset.setProcess(false);
            asset.save(userValue);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {assetId};
            LOG.error(ApplicationResources.getProperty("asset.set.process.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("asset.set.process.failed",
                                       parameters), request);
            return mapping.findForward("process.view.page");
        }
        addMessage(new ActionMessage("process.asset.delete.success"), request);
        return mapping.findForward("continue");
    }
}
