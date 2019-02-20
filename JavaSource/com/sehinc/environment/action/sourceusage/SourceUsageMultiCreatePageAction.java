package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.SourceUsageValue;
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

public class SourceUsageMultiCreatePageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageMultiCreatePageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceUsageMultiCreatePageAction. ";
        strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
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
        LOG.debug(strLog
                  + "preparing to create new source usage multi page.");
        try
        {
            SourceUsageMultiForm
                suForm =
                (SourceUsageMultiForm) form;
            Integer
                assetId =
                (Integer) getSessionAttribute(SessionKeys.EV_ASSET_ID,
                                              request);
            List<SourceUsageValue>
                sourceUsageValues =
                new ArrayList<SourceUsageValue>();
            List<EnvAssetSource>
                assetSources =
                EnvAssetSource.findByAssetId(assetId);
            for (Object o : assetSources)
            {
                EnvAssetSource
                    assetSource =
                    (EnvAssetSource) o;
                SourceUsageValue
                    sourceUsage =
                    new SourceUsageValue();
                Integer
                    sourceId =
                    assetSource.getSourceId();
                if (sourceId
                    != null
                    && sourceId
                       != 0)
                {
                    EnvSource
                        source =
                        new EnvSource(sourceId);
                    source.load();
                    if (source
                        != null)
                    {
                        sourceUsage.setSourceId(source.getId());
                        sourceUsage.setSourceName(source.getDisplayName());
                    }
                }
                else
                {
                    LOG.error(ApplicationResources.getProperty("source.usage.create.load.error"));
                    addError(new ActionMessage("source.usage.create.load.error"), request);
                    return mapping.findForward("source.usage.list.page");
                }
                sourceUsage.setAbleToEnterReading(true);
                if (assetSource.getInactiveTs()
                    != null)
                {
                    if (assetSource.getInactiveTs()
                        .before(suForm.getStartDate()))
                    {
                        sourceUsage.setAbleToEnterReading(false);
                    }
                }
                if (assetSource.getActiveTs()
                    != null)
                {
                    if (assetSource.getActiveTs()
                        .after(suForm.getEndDate()))
                    {
                        sourceUsage.setAbleToEnterReading(false);
                    }
                }
                sourceUsage.setPeriodStartTs(assetSource.getActiveTs());
                sourceUsage.setPeriodEndTs(assetSource.getInactiveTs());
                sourceUsageValues.add(sourceUsage);
            }
            suForm.setAssetId(assetId);
            suForm.setSourceUsages(sourceUsageValues);
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

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_CREATE_SET_MENU_ITEM);
    }
}