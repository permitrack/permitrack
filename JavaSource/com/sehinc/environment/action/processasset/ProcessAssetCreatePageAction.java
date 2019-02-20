package com.sehinc.environment.action.processasset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProcessAssetCreatePageAction
    extends ProcessAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessAssetCreatePageAction.class);

    public ActionForward processAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessAssetCreatePageAction. ";
        strLog =
            strMod
            + "processAssetAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PROCESS_ASSET_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new process asset.");
            addError(new ActionMessage("process.asset.create.unauthorized"), request);
            return mapping.findForward("process.view.page");
        }
        LOG.debug(strLog
                  + "preparing to create new process asset page.");
        try
        {
            ProcessAssetForm
                paf =
                (ProcessAssetForm) form;
            paf.reset();
            Integer
                processId =
                (Integer) getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                              request);
            paf.setProcessId(processId);
            ClientValue
                clientValue =
                getClientValue(request);
            Integer
                facilityId =
                (Integer) getFacility(request);
            List<EnvAsset>
                assets =
                EnvAsset.findAssetsNotBuildingHeatByClient(clientValue.getId());
            List<EnvAsset>
                assetList =
                new ArrayList<EnvAsset>();
            for (EnvAsset asset : assets)
            {
                EnvFacilityAsset
                    fa =
                    EnvFacilityAsset.findByFacilityAndAssetId(facilityId,
                                                              asset.getId());
                if (fa
                    != null)
                {
                    assetList.add(asset);
                }
            }
            setSessionAttribute(SessionKeys.EV_ASSET_ACTIVE_LIST_BY_CLIENT,
                                assetList,
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
