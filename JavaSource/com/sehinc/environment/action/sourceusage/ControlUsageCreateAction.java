package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.controlusage.EnvControlUsage;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlUsageCreateAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageCreateAction.class);

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
            + "controlUsageAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("source.usage.create.cancel.action"), request);
            return mapping.findForward("control.usage.list.page");
        }
        else
        {
            try
            {
                SourceUsageForm
                    suForm =
                    (SourceUsageForm) form;
                Integer
                    assetControlId =
                    suForm.getAssetSourceId();
                String
                    periodStart =
                    suForm.getPeriodStartTsString();
                String
                    periodEnd =
                    suForm.getPeriodEndTsString();
                EnvControlUsage
                    envControlUsage =
                    new EnvControlUsage();
                envControlUsage.setAssetControlId(assetControlId);
                envControlUsage.setPeriodStartTs(DateUtil.parseDate(periodStart));
                envControlUsage.setPeriodEndTs(DateUtil.parseDate(periodEnd));
                envControlUsage.setDescription(suForm.getDescription());
                UserValue
                    objUser =
                    getUserValue(request);
                Integer
                    sourceUsageId =
                    envControlUsage.save(objUser);
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
        addMessage(new ActionMessage("control.usage.create.success"), request);
        return mapping.findForward("continue");
    }
}