package com.sehinc.environment.action.process;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.db.process.EnvProcess;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessCreateAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessCreateAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strMod =
            "ProcessCreateAction. ";
        String
            strLog =
            strMod
            + "processAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("process.create.cancel.action"), request);
            return mapping.findForward("process.list.page");
        }
        else
        {
            try
            {
                UserValue
                    objUser =
                    getUserValue(request);
                ClientValue
                    clientValue =
                    getClientValue(request);
                Integer
                    facilityId =
                    (Integer) getFacility(request);
                ProcessForm
                    processForm =
                    (ProcessForm) form;
                EnvProcess
                    dupProcess =
                    EnvProcess.findByClientIdAndNumberAndFacilityId(clientValue.getId(),
                                                                    processForm.getProcessNumber(),
                                                                    facilityId);
                if (dupProcess
                    != null)
                {
                    addMessage(new ActionMessage("process.duplicate.error"), request);
                    return mapping.getInputForward();
                }
                else
                {
                    EnvProcess
                        processData =
                        new EnvProcess();
                    processData.setClientId(clientValue.getId());
                    processData.setProcessNumber(processForm.getProcessNumber());
                    processData.setName(processForm.getName());
                    processData.setDescription(processForm.getDescription());
                    if (processForm.getParentProcessId()
                        == 0)
                    {
                        processForm.setParentProcessId(null);
                    }
                    processData.setParentProcessId(processForm.getParentProcessId());
                    processData.setFacilityId(facilityId);
                    Integer
                        intProcessId =
                        processData.save(objUser);
                    LOG.debug(strLog
                              + "New Process created with ID = "
                              + intProcessId.toString());
                    setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                        intProcessId,
                                        request);
                }
            }
            catch (Exception eActive)
            {
                strLog =
                    strLog
                    + "Error.  Message: "
                    + eActive.getMessage();
                LOG.debug(strLog);
                setSessionAttribute(SessionKeys.ERROR_EXCEPTION,
                                    new Exception(strLog),
                                    request);
                addError(new ActionMessage("process.create.fail"), request);
                return mapping.findForward("process.list.page");
            }
        }
        addMessage(new ActionMessage("process.create.success"), request);
        LOG.debug(strLog
                  + "strForward = continue");
        return mapping.findForward("continue");
    }
}