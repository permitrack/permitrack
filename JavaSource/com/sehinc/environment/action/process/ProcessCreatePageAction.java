package com.sehinc.environment.action.process;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProcessCreatePageAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessCreatePageAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessCreatePageAction. ";
        strLog =
            strMod
            + "processAction() ";
        LOG.info(strLog
                 + "in action");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_PROCESS_CREATE))
        {
            LOG.error("User ID "
                      + securityManager.getUserID()
                      + " is not authorized to create a new process.");
            addError(new ActionMessage("process.create.unauthorized"), request);
            return mapping.findForward("process.list.page");
        }
        LOG.debug(strLog
                  + "preparing to create new process page.");
        try
        {
            ProcessForm
                processForm =
                (ProcessForm) form;
            processForm.reset();
            ClientValue
                clientValue =
                getClientValue(request);
            processForm.setClientName(clientValue.getName());
            Integer
                facilityId =
                (Integer) getFacility(request);
            List<EnvProcess>
                processes =
                EnvProcess.findByClientIdAndFacilityId(clientValue.getId(),
                                                       facilityId);
            setSessionAttribute(SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT,
                                processes,
                                request);
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("process.create.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("process.create.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PROCESS_NEW_MENU_ITEM);
    }
}

