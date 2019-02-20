package com.sehinc.environment.action.asset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.db.lookup.EnvAssetTypeData;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AssetEditPageAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetEditPageAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "AssetEditPageAction. ";
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
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_ASSET_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("asset.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("asset.update.page.not.allowed",
                                       parameters),
                     request);
            return mapping.findForward("asset.list.page");
        }
        Integer
            assetId;
        if (request.getParameter(RequestKeys.EV_ASSET_ID)
            != null)
        {
            assetId =
                new Integer(request.getParameter(RequestKeys.EV_ASSET_ID));
            LOG.debug("assetId="
                      + request.getParameter(RequestKeys.EV_ASSET_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                     request)
                 != null)
        {
            assetId =
                (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                              request);
            LOG.debug("assetId="
                      + getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                            request));
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
        ClientValue
            clientValue =
            getClientValue(request);
        Integer
            facilityId =
            (Integer) getFacility(request);
        List<EnvAsset>
            assets =
            EnvAsset.findByClientIdSortByNumber(clientValue.getId());
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
                if (asset.getParentAssetId()
                    != null)
                {
                    EnvAsset
                        child =
                        asset;
                    Integer
                        parentId =
                        child.getParentAssetId();
                    if (parentId.intValue()
                        != assetId)
                    {
                        for (EnvAsset asset2 : assets)
                        {
                            if (asset2.getId()
                                    .intValue()
                                != parentId)
                            {
                            }
                            else
                            {
                                if (asset2.getParentAssetId()
                                    == null)
                                {
                                    if (asset.getId()
                                            .intValue()
                                        != assetId)
                                    {
                                        if (child.getMeter()
                                            == null
                                            || !child.getMeter())
                                        {
                                            assetList.add(child);
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                else
                {
                    if (asset.getId()
                            .intValue()
                        != assetId.intValue())
                    {
                        if (asset.getMeter()
                            == null
                            || !asset.getMeter())
                        {
                            assetList.add(asset);
                        }
                    }
                }
            }
        }
        request.getSession()
            .setAttribute(RequestKeys.EV_ASSET_LIST,
                          assetList);
        List
            assetTypes =
            EnvAssetTypeData.findAllByClientId(clientValue.getId());
        request.getSession()
            .setAttribute(RequestKeys.EV_ASSET_TYPE_LIST,
                          assetTypes);
        List<EnvProcess>
            processes =
            new ArrayList<EnvProcess>();
        if (envAsset.getProcess()
            != null
            && envAsset.getProcess()
               == true)
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
        List<EnvAsset>
            meters =
            getFinalList(EnvAsset.findMetersByClientIdSortByNumber(clientValue.getId()));
        List<EnvAsset>
            revisedMetersList =
            new ArrayList<EnvAsset>();
        for (EnvAsset meter : meters)
        {
            EnvFacilityAsset
                fa =
                EnvFacilityAsset.findByFacilityAndAssetId(facilityId,
                                                          meter.getId());
            if (fa
                != null)
            {
                if (!meter.getId()
                    .equals(assetId))
                {
                    revisedMetersList.add(meter);
                }
            }
        }
        request.getSession()
            .setAttribute(RequestKeys.EV_METER_LIST,
                          revisedMetersList);
        setSessionAttribute(SessionKeys.EV_ASSET_ID,
                            envAsset.getId(),
                            request);
        return mapping.findForward("continue");
    }
}