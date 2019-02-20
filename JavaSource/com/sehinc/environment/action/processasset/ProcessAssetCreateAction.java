package com.sehinc.environment.action.processasset;

import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.code.EnvStatusCodeData;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessAssetCreateAction
    extends ProcessAssetBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessAssetCreateAction.class);

    public ActionForward processAssetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessAssetCreateAction. ";
        strLog =
            strMod
            + "processAssetAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("process.asset.create.cancel.action"), request);
            return mapping.findForward("process.view.page");
        }
        else
        {
            try
            {
                ProcessAssetForm
                    paf =
                    (ProcessAssetForm) form;
                Integer
                    processId =
                    paf.getProcessId();
                setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                    processId,
                                    request);
                if (paf.getAssetId()
                    == 0)
                {
                    addMessage(new ActionMessage("process.asset.select"), request);
                    return mapping.getInputForward();
                }
                EnvProcessAsset
                    dupSS =
                    EnvProcessAsset.findActiveByProcessAndAssetId(processId,
                                                                  paf.getAssetId());
                if (dupSS
                    != null)
                {
                    addMessage(new ActionMessage("process.asset.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvProcessAsset
                        processAsset =
                        new EnvProcessAsset();
                    processAsset.setProcessId(processId);
                    processAsset.setAssetId(paf.getAssetId());
                    processAsset.setActiveTs(DateUtil.parseDate(paf.getActiveTsString()));
                    processAsset.setStatusCode(EnvStatusCodeData.STATUS_CODE_ACTIVE);
                    UserValue
                        objUser =
                        getUserValue(request);
                    Integer
                        processAssetId =
                        processAsset.save(objUser);
                    LOG.debug(strLog
                              + "New Process Asset created with ID = "
                              + processAssetId.toString());
                    EnvAsset
                        asset =
                        new EnvAsset(paf.getAssetId());
                    try
                    {
                        asset.load();
                        asset.setProcess(true);
                        asset.save(objUser);
                    }
                    catch (Exception e)
                    {
                        LOG.debug("Error.  Message: "
                                  + e.getMessage());
                        setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                            new Exception(strLog
                                                          + "Error.  Message: "
                                                          + e.getMessage()),
                                            request);
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
        return mapping.findForward("continue");
    }
}

