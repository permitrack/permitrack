package com.sehinc.environment.action.process;

import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessEditAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessEditAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessEditAction. ";
        strLog =
            strMod
            + "processAction() ";
        LOG.info(strLog
                 + "in action");
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("process.edit.cancel.action"), request);
            return mapping.findForward("process.list.page");
        }
        else
        {
            ProcessForm
                processForm =
                (ProcessForm) form;
            UserValue
                userValue =
                getUserValue(request);
            Integer
                processId =
                processForm.getId();
            EnvProcess
                envProcess =
                new EnvProcess(processId);
            ClientValue
                clientValue =
                getClientValue(request);
            try
            {
                envProcess.load();
                envProcess.setId(processForm.getId());
                envProcess.setProcessNumber(processForm.getProcessNumber());
                envProcess.setClientId(clientValue.getId());
                envProcess.setName(processForm.getName());
                envProcess.setDescription(processForm.getDescription());
                if (processForm.getParentProcessId()
                    == 0)
                {
                    processForm.setParentProcessId(null);
                }
                envProcess.setParentProcessId(processForm.getParentProcessId());
                envProcess.save(userValue);
                setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                    processId,
                                    request);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {processId};
                LOG.error(ApplicationResources.getProperty("process.error.edit.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("process.error.edit.failed",
                                           parameters), request);
                return mapping.findForward("process.list.page");
            }
        }
        addMessage(new ActionMessage("process.edit.success"), request);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        setPrimaryMenuItem(request);
        setSecondaryMenu(request);
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_VIEW_MENU),
                        request);
    }
}