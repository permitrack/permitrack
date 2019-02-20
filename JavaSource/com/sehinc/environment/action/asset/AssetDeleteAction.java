package com.sehinc.environment.action.asset;

import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.permitasset.EnvPermitAsset;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AssetDeleteAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetDeleteAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "AssetDeleteAction. ";
        String
            strLog =
            strMod
            + "assetAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("asset.delete.cancel.action"), request);
            return mapping.findForward("asset.list.page");
        }
        else
        {
            AssetForm
                assetForm =
                (AssetForm) form;
            UserValue
                userValue =
                getUserValue(request);
            Integer
                assetId =
                assetForm.getId();
            List<EnvPermitAsset>
                permitAssets =
                EnvPermitAsset.findByAssetId(assetId);
            try
            {
                for (EnvPermitAsset permAss : permitAssets)
                {
                    permAss.delete();
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetId};
                LOG.error(ApplicationResources.getProperty("permit.asset.delete.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("permit.asset.delete.failed",
                                           parameters), request);
                return mapping.findForward("asset.list.page");
            }
            List<EnvProcessAsset>
                processAssets =
                EnvProcessAsset.findActiveByAssetId(assetId);
            try
            {
                for (EnvProcessAsset procAss : processAssets)
                {
                    procAss.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                    procAss.save(userValue);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetId};
                LOG.error(ApplicationResources.getProperty("process.asset.in.asset.delete.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("process.asset.in.asset.delete.failed",
                                           parameters), request);
                return mapping.findForward("asset.list.page");
            }
            Integer
                facilityId =
                (Integer) getFacility(request);
            try
            {
                AssetService.deleteFacilityAsset(facilityId,
                                                 assetId);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetId};
                LOG.error(ApplicationResources.getProperty("facility.asset.delete.error",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("facility.asset.delete.error",
                                           parameters), request);
                return mapping.findForward("asset.list.page");
            }
            EnvAsset
                envAsset =
                new EnvAsset(assetId);
            try
            {
                envAsset.load();
                envAsset.setStatusCode(EnvStatusCodeData.STATUS_CODE_DELETED);
                envAsset.save(userValue);
                List
                    childAssets =
                    EnvAsset.findAllByParentId(envAsset.getId());
                for (Object o : childAssets)
                {
                    EnvAsset
                        child =
                        (EnvAsset) o;
                    child.setParentAssetId(null);
                    child.save(userValue);
                }
                List
                    meteredAssets =
                    EnvAsset.findAllByMeterId(envAsset.getId());
                for (Object o : meteredAssets)
                {
                    EnvAsset
                        child =
                        (EnvAsset) o;
                    child.setBelongsToMeter(null);
                    child.save(userValue);
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {assetId};
                LOG.error(ApplicationResources.getProperty("asset.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.delete.page.failed",
                                           parameters), request);
                return mapping.findForward("asset.list.page");
            }
            addMessage(new ActionMessage("asset.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}