package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.sourceusage.EnvSourceUsage;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceUsageCreateAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SourceUsageCreateAction.class);

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
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.usage.create.cancel.action"), request);
            return mapping.findForward("source.usage.list.page");
        }
        else
        {
            try
            {
                SourceUsageForm
                    suForm =
                    (SourceUsageForm) form;
                Integer
                    assetSourceId =
                    suForm.getAssetSourceId();
                String
                    meterReading =
                    suForm.getMeterReading();
                Integer
                    unit =
                    suForm.getUnitOfMeasureCd();
                String
                    periodStart =
                    suForm.getPeriodStartTsString();
                String
                    periodEnd =
                    suForm.getPeriodEndTsString();
                String
                    transferRate =
                    suForm.getTransferRate();
                EnvSourceUsage
                    envSU =
                    new EnvSourceUsage();
                envSU.setAssetSourceId(assetSourceId);
                envSU.setPeriodStartTs(DateUtil.parseDate(periodStart));
                envSU.setPeriodEndTs(DateUtil.parseDate(periodEnd));
                envSU.setMeterReadingString(meterReading);
                envSU.setUnitOfMeasureType(unit);
                envSU.setTransferRateString(transferRate);
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
        addMessage(new ActionMessage("source.usage.create.success"), request);
        return mapping.findForward("continue");
    }
}