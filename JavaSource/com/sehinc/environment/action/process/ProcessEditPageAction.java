package com.sehinc.environment.action.process;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProcessEditPageAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessEditPageAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessEditPageAction. ";
        strLog =
            strMod
            + "processAction() ";
        LOG.info(strLog
                 + "in action");
        ProcessForm
            processForm =
            (ProcessForm) form;
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PROCESS_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("process.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("process.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward("process.list.page");
        }
        Integer
            processId;
        if (request.getParameter(RequestKeys.EV_PROCESS_ID)
            != null)
        {
            processId =
                new Integer(request.getParameter(RequestKeys.EV_PROCESS_ID));
            LOG.debug("processId="
                      + request.getParameter(RequestKeys.EV_PROCESS_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                     request)
                 != null)
        {
            processId =
                (Integer) getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                              request);
            LOG.debug("processId="
                      + getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                            request));
        }
        else
        {
            LOG.error(ApplicationResources.getProperty("process.error.no.process.on.request"));
            addError(new ActionMessage("process.error.no.process.on.request"), request);
            return mapping.findForward("process.list.page");
        }
        EnvProcess
            envProcess =
            new EnvProcess(processId);
        try
        {
            envProcess.load();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {processId};
            LOG.error(ApplicationResources.getProperty("process.error.load.failed",
                                                       parameters));
            LOG.error(e.getMessage());
            addError(new ActionMessage("process.error.load.failed",
                                       parameters), request);
            return mapping.findForward("process.list.page");
        }
        processForm.setId(envProcess.getId());
        processForm.setProcessNumber(envProcess.getProcessNumber());
        processForm.setClientId(envProcess.getClientId());
        processForm.setName(envProcess.getName());
        processForm.setDescription(envProcess.getDescription());
        processForm.setParentProcessId(envProcess.getParentProcessId());
        Integer
            facilityId =
            (Integer) getFacility(request);
        List<EnvProcess>
            processes =
            EnvProcess.findByClientIdAndFacilityId(envProcess.getClientId(),
                                                   facilityId);
        for (EnvProcess process : processes)
        {
            Integer
                temp_id =
                process.getId();
            if (temp_id.intValue()
                == processId.intValue())
            {
                processes.remove(process);
                break;
            }
        }
        setSessionAttribute(SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT,
                            processes,
                            request);
        setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                            envProcess.getId(),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PROCESS_EDIT_MENU_ITEM);
    }
}