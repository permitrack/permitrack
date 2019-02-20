package com.sehinc.environment.action.process;

import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProcessDeleteAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessDeleteAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessDeleteAction. ";
        strLog =
            strMod
            + "processAction() ";
        LOG.info(strLog
                 + "in action");
        ProcessForm
            sForm =
            (ProcessForm) form;
        if (isCancelled(request))
        {
            addMessage(new ActionMessage("process.delete.cancel.action"), request);
            return mapping.findForward("process.list.page");
        }
        else
        {
            Integer
                processId;
            LOG.debug("processId="
                      + sForm.getId());
            if (sForm.getId()
                != null
                && sForm.getId()
                   != 0)
            {
                processId =
                    sForm.getId();
            }
            else
            {
                LOG.error(ApplicationResources.getProperty("process.error.no.process.id"));
                addError(new ActionMessage("process.error.no.process.id"), request);
                return mapping.findForward("process.list.page");
            }
            try
            {
                List<EnvProcess>
                    processes =
                    new ArrayList<EnvProcess>();
                processes.add(new EnvProcess(processId));
                ProcessService.findChildrenAndDelete(processes);
            }
            catch (Exception e)
            {
                addError(new ActionMessage("process.asset.delete.failed"), request);
                return mapping.findForward("process.list.page");
            }
            addMessage(new ActionMessage("process.delete.success"), request);
            return mapping.findForward("continue");
        }
    }
}