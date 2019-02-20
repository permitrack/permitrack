package com.sehinc.environment.action.sourceusage;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.controlusage.EnvControlUsage;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlUsageEditAction
    extends SourceUsageBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ControlUsageEditAction.class);

    public ActionForward sourceUsageAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ControlUsageEditAction. ";
        strLog =
            strMod
            + "sourceUsageAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("control.usage.edit.cancel.action"), request);
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
                    envSU =
                    new EnvControlUsage(suForm.getSourceUsageId());
                envSU.load();
                envSU.setAssetControlId(assetControlId);
                envSU.setPeriodStartTs(DateUtil.parseDate(periodStart));
                envSU.setPeriodEndTs(DateUtil.parseDate(periodEnd));
                envSU.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                UserValue
                    objUser =
                    getUserValue(request);
                Integer
                    controlUsageId =
                    envSU.save(objUser);
                LOG.debug(strLog
                          + "New Control Usage edited with ID = "
                          + controlUsageId.toString());
                addMessage(new ActionMessage("control.usage.edit.success"), request);
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
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.CONTROL_USAGE_LIST_MENU),
                        request);
    }
}
