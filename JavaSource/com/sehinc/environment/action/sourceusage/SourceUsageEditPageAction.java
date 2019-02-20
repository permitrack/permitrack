package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.lookup.EnvUnitOfMeasureMap;
import com.sehinc.environment.db.source.EnvSource;
import com.sehinc.environment.db.sourcesubstance.EnvSourceSubstance;
import com.sehinc.environment.db.sourceusage.EnvSourceUsage;
import com.sehinc.environment.db.substance.EnvSubstance;
import com.sehinc.environment.resources.ApplicationResources;
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

public class SourceUsageEditPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageEditPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        ClientValue
            clientValue =
            getClientValue(request);
        SourceUsageForm
            srcUsageForm =
            (SourceUsageForm) form;
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_UPDATE,
                           request))
        {
            return mapping.findForward("page.permission.denied");
        }
        Integer
            sourceUsageId;
        if (request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID)
            != null)
        {
            sourceUsageId =
                new Integer(request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID));
            LOG.debug("sourceUsageId="
                      + request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                     request)
                 != null)
        {
            sourceUsageId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                              request);
            LOG.debug("Got sourceUsageId="
                      + sourceUsageId
                      + " from the session.");
            LOG.debug("sourceUsageId="
                      + getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("source.usage.error.no.source.usage.on.request"));
            addError(new ActionMessage("source.usage.error.no.source.usage.on.request"), request);
            return mapping.findForward("source.usage.list.page");
        }
        EnvSourceUsage
            envSourceUsage =
            new EnvSourceUsage();
        EnvAssetSource
            assetSource =
            new EnvAssetSource();
        EnvSource
            source =
            new EnvSource();
        try
        {
            envSourceUsage.setId(sourceUsageId);
            envSourceUsage.load();
            assetSource.setId(envSourceUsage.getAssetSourceId());
            assetSource.load();
            source.setId(assetSource.getSourceId());
            source.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {sourceUsageId};
            LOG.error(ApplicationResources.getProperty("source.usage.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("source.usage.error.load.failed",
                                       parameters), request);
            return mapping.findForward("source.usage.list.page");
        }
        List<EnvSourceSubstance>
            sourceSubstanceList =
            EnvSourceSubstance.findBySourceId(assetSource.getSourceId());
        List<SourceSubstanceValue>
            srcSubstanceValueList =
            new ArrayList<SourceSubstanceValue>();
        for (Object o : sourceSubstanceList)
        {
            EnvSourceSubstance
                ess =
                (EnvSourceSubstance) o;
            SourceSubstanceValue
                ssv =
                new SourceSubstanceValue();
            ssv.setRatio(ess.getRatioString());
            EnvSubstance
                substance =
                EnvSubstance.findByClientIdAndSubstanceId(clientValue.getId(),
                                                          ess.getSubstanceId());
            ssv.setSubstanceName(substance.getName());
            Float
                calculatedTotal =
                ess.getRatio()
                    .floatValue()
                * envSourceUsage.getMeterReading()
                    .floatValue();
            ssv.setCalculatedTotal(calculatedTotal);
            srcSubstanceValueList.add(ssv);
        }
        setSessionAttribute(SessionKeys.EV_SOURCE_SUBSTANCE_ACTIVE_LIST,
                            srcSubstanceValueList,
                            request);
        setSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                            sourceUsageId,
                            request);
        srcUsageForm.setSourceUsageId(envSourceUsage.getId());
        srcUsageForm.setSourceDisplayName(source.getDisplayName());
        srcUsageForm.setMeterReading(envSourceUsage.getMeterReadingString());
        srcUsageForm.setUnitOfMeasureCd(envSourceUsage.getUnitOfMeasureCd()
                                            .getCode());
        srcUsageForm.setUnitOfMeasureDesc(envSourceUsage.getUnitOfMeasureCd()
                                              .getDescription());
        srcUsageForm.setPeriodStartTs(envSourceUsage.getPeriodStartTs());
        srcUsageForm.setPeriodEndTs(envSourceUsage.getPeriodEndTs());
        srcUsageForm.setTransferRate(envSourceUsage.getTransferRateString());
        srcUsageForm.setAssetSourceId(envSourceUsage.getAssetSourceId());
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
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_EDIT_MENU_ITEM);
    }
}
