package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.conversionservice.ConversionService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.action.source.SourceViewPageAction;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
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

public class SourceUsageViewPageAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceViewPageAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        if (!hasPermission(SecureObjectPermissionData.EV_SOURCE_USAGE_READ,
                           request))
        {
            mapping.findForward("page.permission.denied");
        }
        ClientValue
            clientValue =
            getClientValue(request);
        SourceUsageForm
            srcUsageForm =
            (SourceUsageForm) form;
        Integer
            sourceUsageId =
            getSourceUsageId(request);
        if (sourceUsageId
            < 1)
        {
            mapping.findForward("error");
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
            Integer
                convertFromUnit =
                envSourceUsage.getUnitOfMeasureCd()
                    .getCode();
            if (convertFromUnit
                == 6
                || convertFromUnit
                   == 7
                || convertFromUnit
                   == 8
                || convertFromUnit
                   == 11
                || convertFromUnit
                   == 12)
            {
                ssv.setCalculatedTotal(null);
            }
            else
            {
                Float
                    newMeteredReading =
                    ConversionService.convert(envSourceUsage.getMeterReading()
                                                  .floatValue(),
                                              convertFromUnit,
                                              1,
                                              source.getDensity()
                                                  .floatValue());
                ssv.setNewReading(newMeteredReading);
                Float
                    calculatedTotal =
                    ess.getRatio()
                        .floatValue()
                    * newMeteredReading
                    * source.getDensity()
                        .floatValue();
                ssv.setCalculatedTotal(calculatedTotal);
            }
            ssv.setCalculatedTotalUnit("Lbs");
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
        srcUsageForm.setSourceDensity(source.getDensityString());
        if (source.getDensityUm()
            != null)
        {
            srcUsageForm.setSourceDensityUm(source.getDensityUm()
                                                .getCode());
            srcUsageForm.setSourceDensityUmDesc(source.getDensityUm()
                                                    .getDescription());
        }
        srcUsageForm.setMeterReading(envSourceUsage.getMeterReadingString());
        srcUsageForm.setUnitOfMeasureCd(envSourceUsage.getUnitOfMeasureCd()
                                            .getCode());
        srcUsageForm.setUnitOfMeasureDesc(envSourceUsage.getUnitOfMeasureCd()
                                              .getDescription());
        srcUsageForm.setPeriodStartTs(envSourceUsage.getPeriodStartTs());
        srcUsageForm.setPeriodEndTs(envSourceUsage.getPeriodEndTs());
        srcUsageForm.setTransferRate(envSourceUsage.getTransferRateString());
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.SOURCE_USAGE_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.SOURCE_USAGE_VIEW_MENU_ITEM);
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(request);
    }

    private Integer getSourceUsageId(HttpServletRequest request)
    {
        Integer
            sourceUsageId;
        if (request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID)
            != null)
        {
            sourceUsageId =
                new Integer(request.getParameter(RequestKeys.EV_SOURCE_USAGE_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                     request)
                 != null)
        {
            sourceUsageId =
                (Integer) getSessionAttribute(SessionKeys.EV_SOURCE_USAGE_ID,
                                              request);
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("source.usage.error.no.source.usage.on.request"));
            addError(new ActionMessage("source.usage.error.no.source.usage.on.request"), request);
            return 0;
        }
        return sourceUsageId;
    }
}

