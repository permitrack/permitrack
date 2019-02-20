package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.AssetSourceValue;
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

public class SourceUsageCreatePageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageCreatePageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceUsageCreatePageAction. ";
        strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.usage.create.cancel.action"), request);
            return mapping.findForward("source.usage.list.page");
        }
        else
        {
            SecurityManager
                securityManager =
                getSecurityManager(request);
            if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_CREATE))
            {
                LOG.error("User ID "
                          + securityManager.getUserID()
                          + " is not authorized to create a new source usage.");
                addError(new ActionMessage("source.usage.create.unauthorized"), request);
                return mapping.findForward("source.usage.list.page");
            }
            else
            {
                LOG.debug(strLog
                          + "preparing to create new source usage page.");
                try
                {
                    Integer
                        assetId =
                        (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                                      request);
                    List<AssetSourceValue>
                        asvList =
                        new ArrayList<AssetSourceValue>();
                    List
                        assetSourceList =
                        EnvAssetSource.findByAssetId(assetId);
                    if (assetSourceList
                        != null)
                    {
                        for (Object o : assetSourceList)
                        {
                            EnvAssetSource
                                as =
                                (EnvAssetSource) o;
                            EnvAsset
                                asset =
                                new EnvAsset(as.getAssetId());
                            asset.load();
                            EnvSource
                                source =
                                new EnvSource(as.getSourceId());
                            source.load();
                            AssetSourceValue
                                asVal =
                                new AssetSourceValue(as);
                            asVal.setDisplayName(source.getDisplayName());
                            asvList.add(asVal);
                        }
                    }
                    setSessionAttribute(SessionKeys.EV_ASSET_SOURCE_ACTIVE_LIST,
                                        asvList,
                                        request);
                    ClientValue
                        clientValue =
                        getClientValue(request);
                    String
                        tableName =
                        EnvUnitOfMeasureMap.SOURCE_USAGE_TABLE;
                    List
                        srcUsage_units =
                        EnvUnitOfMeasureMap.getUnitOfMeasureList(clientValue.getId(),
                                                                 tableName,
                                                                 EnvUnitOfMeasureMap.SOURCE_USG_READING_COL);
                    setSessionAttribute(SessionKeys.EV_SRC_USAGE_CD_LIST,
                                        srcUsage_units,
                                        request);
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {e.getMessage()};
                    addError(new ActionMessage("source.usage.create.load.failed",
                                               parameters), request);
                    LOG.error(ApplicationResources.getProperty("source.usage.create.load.failed",
                                                               parameters));
                    return mapping.getInputForward();
                }
                return mapping.findForward("continue");
            }
        }
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_CREATE_MENU_ITEM);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }
}