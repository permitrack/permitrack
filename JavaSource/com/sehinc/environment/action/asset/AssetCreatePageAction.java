package com.sehinc.environment.action.asset;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.facilityasset.EnvFacilityAsset;
import com.sehinc.environment.db.lookup.EnvAssetTypeData;
import com.sehinc.environment.resources.ApplicationResources;
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

public class AssetCreatePageAction
    extends AssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AssetCreatePageAction.class);

    public ActionForward assetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "AssetCreatePageAction. ";
        String
            strLog =
            strMod
            + "assetAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_ASSET_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new asset.");
            addError(new ActionMessage("asset.create.unauthorized"), request);
            return mapping.findForward("asset.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new asset page.");
        try
        {
            AssetForm
                objA =
                (AssetForm) form;
            objA.reset();
            ClientValue
                clientValue =
                getClientValue(request);
            Integer
                facilityId =
                (Integer) getFacility(request);
            List
                assetTypes =
                EnvAssetTypeData.findAllByClientId(clientValue.getId());
            request.getSession()
                .setAttribute(RequestKeys.EV_ASSET_TYPE_LIST,
                              assetTypes);
            // set list of assets into request for use on page
            //second children cannot have children
            //a new asset can only be a parent, or
            //a child of another parent or the first child
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
                        for (EnvAsset asset2 : assets)
                        {
                            if (asset2.getId()
                                    .intValue()
                                != parentId)
                            {
                                ;
                            }
                            else
                            {
                                if (asset2.getParentAssetId()
                                    == null)
                                {
                                    //we know child is a first child; we only add first children to the list
                                    if (child.getMeter()
                                        == null
                                        || !child.getMeter())
                                    {
                                        assetList.add(child);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else
                    {
                        //all parent assets need to be displayed in the list
                        if (asset.getMeter()
                            == null
                            || !asset.getMeter())
                        {
                            assetList.add(asset);
                        }
                    }
                }
            }
            request.getSession()
                .setAttribute(RequestKeys.EV_ASSET_LIST,
                              assetList);
            // Set List of Meters on the page for use in asset creation
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
                    revisedMetersList.add(meter);
                }
            }
            request.getSession()
                .setAttribute(RequestKeys.EV_METER_LIST,
                              revisedMetersList);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            LOG.error(ApplicationResources.getProperty("asset.create.load.failed",
                                                       parameters));
            // TODO do all like this
            return mapping.findForward("asset.list.page");
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.ASSET_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.ASSET_NEW_MENU_ITEM);
    }
}