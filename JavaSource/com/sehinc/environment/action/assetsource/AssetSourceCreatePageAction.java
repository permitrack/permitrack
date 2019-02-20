package com.sehinc.environment.action.assetsource;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.source.EnvSource;
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

public class AssetSourceCreatePageAction
    extends AssetSourceBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetSourceCreatePageAction.class);

    public ActionForward assetSourceAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "AssetSourceCreatePageAction. ";
        strLog =
            strMod
            + "assetSourceAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SOURCE_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new asset source.");
            addError(new ActionMessage("asset.source.create.unauthorized"), request);
            return mapping.findForward("asset.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new asset source page.");
        try
        {
            AssetSourceForm
                ssForm =
                (AssetSourceForm) form;
            ssForm.reset();
            Integer
                assetId =
                (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                              request);
            ssForm.setAssetId(assetId);
            ClientValue
                clientValue =
                getClientValue(request);
            List
                sources =
                EnvSource.findSourceByClientId(clientValue.getId());
            setSessionAttribute(SessionKeys.EV_SOURCE_ACTIVE_LIST_BY_CLIENT,
                                sources,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("asset.source.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("asset.source.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }
}
