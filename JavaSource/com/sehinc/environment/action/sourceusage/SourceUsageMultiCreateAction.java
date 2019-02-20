package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.assetsource.EnvAssetSource;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.sourceusage.EnvSourceUsage;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SourceUsageMultiCreateAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageMultiCreateAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "SourceUsageCreateAction. ";
        strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
        StringBuffer
            failedEntries =
            new StringBuffer("");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.usage.create.cancel.action"), request);
            return mapping.findForward("source.usage.list.page");
        }
        else
        {
            try
            {
                SourceUsageMultiForm
                    suForm =
                    (SourceUsageMultiForm) form;
                Integer
                    assetId =
                    suForm.getAssetId();
                String[]
                    sources =
                    request.getParameterValues("sourceId");
                String[]
                    names =
                    request.getParameterValues("sourceName");
                String[]
                    readings =
                    request.getParameterValues("meterReading");
                String[]
                    transfers =
                    request.getParameterValues("transferRate");
                String[]
                    units =
                    request.getParameterValues("unitOfMeasureCd");
                String
                    startDate =
                    suForm.getStartDateString();
                String
                    endDate =
                    suForm.getEndDateString();
                if (sources.length
                    > 0)
                {
                    for (
                        int
                            i =
                            0; i
                               < sources.length; i++)
                    {
                        Integer
                            sourceId =
                            new Integer(sources[i]);
                        if (readings[i]
                            != null
                            && !readings[i].equals(""))
                        {
                            BigDecimal
                                reading;
                            try
                            {
                                reading =
                                    new BigDecimal(readings[i]);
                            }
                            catch (NumberFormatException n)
                            {
                                failedEntries.append(names[i])
                                    .append(", ");
                                continue;
                            }
                            BigDecimal
                                transferRate = null;
                            try
                            {
                                transferRate =
                                    new BigDecimal(transfers[i]);
                            }
                            catch (NumberFormatException n)
                            {
/*
                                failedEntries.append(names[i])
                                    .append(", ");
                                continue;
*/
                            }
                            Integer
                                unit =
                                new Integer(units[i]);
                            if (unit
                                == 0)
                            {
                                failedEntries.append(names[i])
                                    .append(", ");
                                continue;
                            }
                            DateFormat
                                df =
                                new SimpleDateFormat("MM/dd/yyyy");
                            Date
                                startDt =
                                df.parse(startDate);
                            if (startDt
                                == null)
                            {
                                failedEntries.append(startDate)
                                    .append(", ");
                                continue;
                            }
                            Date
                                endDt =
                                df.parse(endDate);
                            if (endDt
                                == null)
                            {
                                failedEntries.append(endDate)
                                    .append(", ");
                                continue;
                            }
                            EnvAssetSource
                                assetSource =
                                EnvAssetSource.findByAssetIdAndSourceId(assetId,
                                                                        sourceId);
                            Integer
                                assetSourceId =
                                assetSource.getId();
                            EnvSourceUsage
                                envSU =
                                new EnvSourceUsage();
                            envSU.setAssetSourceId(assetSourceId);
                            envSU.setPeriodStartTs(startDt);
                            envSU.setPeriodEndTs(endDt);
                            envSU.setPeriodStartTs(DateUtil.parseDate(startDate));
                            envSU.setPeriodEndTs(DateUtil.parseDate(endDate));
                            envSU.setMeterReading(reading);
                            envSU.setUnitOfMeasureType(unit);
                            envSU.setTransferRate(transferRate);
                            envSU.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                            UserValue
                                objUser =
                                getUserValue(request);
                            Integer
                                sourceUsageId =
                                envSU.save(objUser);
                            LOG.debug(strLog
                                      + "New Source Usage created with ID = "
                                      + sourceUsageId.toString());
                        }
                    }
                }
            }
            catch (Exception eActive)
            {
                LOG.debug("Error.  Message: "
                          + eActive.getMessage());
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strLog
                                                  + "Error.  Message: "
                                                  + eActive.getMessage()),
                                    request);
            }
        }
        if (!failedEntries.toString()
            .equals(""))
        {
            String
                failedConnects =
                failedEntries.substring(0,
                                        failedEntries.length()
                                        - 2);
            Object[]
                parameters =
                {failedConnects};
            LOG.error(ApplicationResources.getProperty("source.usage.error.multi",
                                                       parameters));
            addError(new ActionMessage("source.usage.error.multi",
                                       parameters), request);
        }
        addMessage(new ActionMessage("source.usage.create.success"), request);
        return mapping.findForward("continue");
    }
}