package com.sehinc.environment.action.asset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.assetsubstance.EnvAssetSubstance;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.lookup.EnvAssetTypeData;
import com.sehinc.environment.db.lookup.EnvSubstanceTypeData;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.AssetSourceValue;
import com.sehinc.environment.value.AssetSubstanceValue;
import com.sehinc.environment.value.SourceSubstanceValue;
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

public class AssetViewPageAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetViewPageAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "AssetViewPageAction. ";
        String
            strLog =
            strMod
            + "assetAction() ";
        LOG.info(strLog
                 + "in action");
        AssetForm
            assetForm =
            (AssetForm) form;
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("asset.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("asset.view.page.not.allowed",
                                       parameters),
                     request);
            return mapping.findForward("asset.list.page");
        }
        Integer
            assetId;
        LOG.debug("assetId="
                  + request.getParameter(RequestKeys.EV_ASSET_ID));
        if (request.getParameter(RequestKeys.EV_ASSET_ID)
            != null)
        {
            assetId =
                new Integer(request.getParameter(RequestKeys.EV_ASSET_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                     request)
                 != null)
        {
            assetId =
                (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                              request);
            LOG.debug("Getting the assetId="
                      + assetId
                      + " from the session.");
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("asset.error.no.asset.on.request"));
            addError(new ActionMessage("asset.error.no.asset.on.request"),
                     request);
            return mapping.findForward("asset.list.page");
        }
        EnvAsset
            envAsset =
            new EnvAsset(assetId);
        try
        {
            envAsset.load();
            if (!envAsset.getStatus()
                .getCode()
                .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
            {
                throw new Exception("The requested Asset ID = "
                                    + assetId
                                    + " does not exist.");
            }
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {assetId};
            LOG.error(ApplicationResources.getProperty("asset.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("asset.error.load.failed",
                                       parameters),
                     request);
            return mapping.findForward("asset.list.page");
        }
        AssetService.populateAssetForm(assetForm,
                                       envAsset);
        List
            assetTypes =
            EnvAssetTypeData.findAllByClientId(clientValue.getId());
        request.setAttribute(RequestKeys.EV_ASSET_TYPE_LIST,
                             assetTypes);
        setSessionAttribute(SessionKeys.EV_ASSET_ID,
                            envAsset.getId(),
                            request);
        List<EnvProcess>
            processes =
            new ArrayList<EnvProcess>();
        if (envAsset.getProcess()
            != null
            && envAsset.getProcess())
        {
            List<EnvProcessAsset>
                paList =
                EnvProcessAsset.findActiveByAssetId(assetId);
            for (EnvProcessAsset pa : paList)
            {
                EnvProcess
                    process =
                    new EnvProcess(pa.getProcessId());
                process.load();
                processes.add(process);
            }
        }
        setSessionAttribute(SessionKeys.EV_ACTIVE_PROCESS_LIST,
                            processes,
                            request);
        ArrayList<AssetSourceValue>
            assetSourceList =
            new ArrayList<AssetSourceValue>();
        List
            asList =
            EnvAssetSource.findByAssetId(assetId);
        for (Object j : asList)
        {
            EnvAssetSource
                as =
                (EnvAssetSource) j;
            try
            {
                AssetSourceValue
                    asf =
                    new AssetSourceValue(as);
                EnvSource
                    source =
                    new EnvSource(as.getSourceId());
                if (source.load())
                {
                    asf.setDisplayName(source.getDisplayName());
                    if (source.getDisplayColor()
                        != null)
                    {
                        asf.setDisplayColor(source.getDisplayColor()
                                                .getCode());
                    }
                }
                assetSourceList.add(asf);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("asset.source.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.source.error.load.failed"),
                         request);
            }
        }
        ArrayList<SourceSubstanceValue>
            sourceSubstanceList =
            new ArrayList<SourceSubstanceValue>();
        if (assetSourceList.size()
            > 0)
        {
            for (Object assetSrc : assetSourceList)
            {
                Integer
                    sourceId =
                    ((AssetSourceValue) assetSrc).getSourceId();
                List
                    ssList =
                    EnvSourceSubstance.findBySourceId(sourceId);
                Integer
                    substanceId;
                for (Object j : ssList)
                {
                    EnvSourceSubstance
                        ss =
                        (EnvSourceSubstance) j;
                    try
                    {
                        SourceSubstanceValue
                            ssVal =
                            new SourceSubstanceValue();
                        substanceId =
                            ss.getSubstanceId();
                        ssVal.setSourceSubstanceId(ss.getId());
                        ssVal.setSourceId(ss.getSourceId());
                        ssVal.setSubstanceId(ss.getSubstanceId());
                        ssVal.setRatio(ss.getRatioString());
                        ssVal.setNatGasEmFactor(ss.getNatGasEmFactorString());
                        if (ss.getNatGasEfUnit()
                            != null)
                        {
                            ssVal.setNatGasEfUnit(ss.getNatGasEfUnit()
                                                      .getCode());
                            ssVal.setNatGasEfUnitDesc(ss.getNatGasEfUnit()
                                                          .getDescription());
                        }
                        ssVal.setBulkLiqEmFactor(ss.getBulkLiqEmFactorString());
                        if (ss.getBulkLiqEfUnit()
                            != null)
                        {
                            ssVal.setBulkLiqEfUnit(ss.getBulkLiqEfUnit()
                                                       .getCode());
                            ssVal.setBulkLiqEfUnitDesc(ss.getBulkLiqEfUnit()
                                                           .getDescription());
                        }
                        ssVal.setAssetName(((AssetSourceValue) assetSrc).getDisplayName());
                        EnvSubstance
                            substance =
                            new EnvSubstance(ss.getSubstanceId());
                        if (substance.load())
                        {
                            ssVal.setSubstanceName(substance.getName());
                            ssVal.setSubstancePartNumber(substance.getPartNum());
                        }
                        EnvSubstance
                            substanceVal =
                            EnvSubstance.findByClientIdAndSubstanceId(clientValue.getId(),
                                                                      substanceId);
                        ssVal.setSubstanceType(substanceVal.getSubstanceType()
                                                   .getDescription());
                        EnvSource
                            envSource =
                            new EnvSource(sourceId);
                        envSource.load();
                        if (!envSource.getStatus()
                            .getCode()
                            .equals(EnvStatusCodeData.STATUS_CODE_ACTIVE))
                        {
                            throw new Exception("The requested Source ID = "
                                                + sourceId
                                                + " does not exist.");
                        }
                        ssVal.setSourceTypeCode(envSource.getSourceType()
                                                    .getCode());
                        sourceSubstanceList.add(ssVal);
                    }
                    catch (Exception e)
                    {
                        LOG.error(ApplicationResources.getProperty("asset.source.substance.error.load.failed"));
                        LOG.error(e.getMessage());
                        addError(new ActionMessage("asset.source.substance.error.load.failed"),
                                 request);
                    }
                }
            }
        }
        ArrayList<AssetSubstanceValue>
            assetSubstanceList =
            new ArrayList<AssetSubstanceValue>();
        List
            efList =
            EnvAssetSubstance.findByAssetId(assetId);
        for (Object o : efList)
        {
            EnvAssetSubstance
                asub =
                (EnvAssetSubstance) o;
            try
            {
                AssetSubstanceValue
                    eff =
                    new AssetSubstanceValue();
                EnvSubstanceTypeData
                    sub =
                    EnvSubstanceTypeData.findByTypeCode(asub.getSubstanceTypeCd());
                if (sub
                    != null)
                {
                    eff.setSubstanceTypeName(sub.getDescription());
                }
                eff.setAssetSubstanceId(asub.getId());
                eff.setEfficiencyFactor(asub.getEfficiencyFactor());
                eff.setAdditionalInfo(asub.getAdditionalInfo());
                assetSubstanceList.add(eff);
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("asset.substance.error.load.failed"));
                LOG.error(e.getMessage());
                addError(new ActionMessage("asset.substance.error.load.failed"),
                         request);
            }
        }
        setSessionAttribute(SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST,
                            assetSourceList,
                            request);
        setSessionAttribute(SessionKeys.EV_ASSET_SUBSTANCE_LIST,
                            assetSubstanceList,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST,
                            sourceSubstanceList,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_ID,
                            null,
                            request);
        setSessionAttribute(SessionKeys.ASSET_SOURCE_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SOURCE_CREATE),
                            request);
        setSessionAttribute(SessionKeys.ASSET_SOURCE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SOURCE_DELETE),
                            request);
        setSessionAttribute(SessionKeys.ASSET_SUBSTANCE_CAN_CREATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SUBSTANCE_CREATE),
                            request);
        setSessionAttribute(SessionKeys.ASSET_SUBSTANCE_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_SUBSTANCE_DELETE),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.ASSET_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.ASSET_VIEW_MENU_ITEM);
    }
}