package com.sehinc.environment.action.process;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.ProcessAssetValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProcessViewPageAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessViewPageAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog;
        String
            strMod =
            "ProcessViewPageAction. ";
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
        if (!securityManager.HasECPermission(com.sehinc.security.SecureObjectPermissionData.EV_PROCESS_READ))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "read"};
            LOG.info(ApplicationResources.getProperty("process.view.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("process.view.page.not.allowed",
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
            LOG.debug("evProcessId="
                      + request.getParameter(RequestKeys.EV_PROCESS_ID));
        }
        else if (getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                     request)
                 != null)
        {
            processId =
                (Integer) getSessionAttribute(SessionKeys.EV_PROCESS_ID,
                                              request);
            LOG.debug("evProcessId="
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
        ClientData
            client =
            new ClientData(envProcess.getClientId());
        try
        {
            client.load();
        }
        catch (Exception e)
        {
            LOG.error(ApplicationResources.getProperty("process.error.client.load.failed"));
            addError(new ActionMessage("process.error.client.load.failed"), request);
            return mapping.findForward("process.list.page");
        }
        List<EnvProcessAsset>
            pa =
            EnvProcessAsset.findActiveByProcessId(processId);
        List<ProcessAssetValue>
            assetList =
            new ArrayList<ProcessAssetValue>();
        for (EnvProcessAsset epa : pa)
        {
            Integer
                assetId =
                epa.getAssetId();
            if (assetId
                != null)
            {
                ProcessAssetValue
                    pav =
                    new ProcessAssetValue(epa);
                EnvAsset
                    asset =
                    new EnvAsset(assetId);
                try
                {
                    if (asset.load())
                    {
                        pav.setAssetNumber(asset.getNumber());
                        pav.setAssetName(asset.getName());
                    }
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {assetId};
                    LOG.error(ApplicationResources.getProperty("asset.error.load.failed",
                                                               parameters));
                    LOG.error(e.getMessage());
                    addError(new ActionMessage("asset.error.load.failed",
                                               parameters), request);
                    return mapping.findForward("process.list.page");
                }
                assetList.add(pav);
            }
            processForm.setAssetList(assetList);
        }
        processForm.setId(envProcess.getId());
        processForm.setProcessNumber(envProcess.getProcessNumber());
        processForm.setClientId(envProcess.getClientId());
        processForm.setName(envProcess.getName());
        processForm.setDescription(envProcess.getDescription());
        setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                            envProcess.getId(),
                            request);
        if (envProcess.getParentProcessId()
            != null)
        {
            EnvProcess
                parentProcess =
                new EnvProcess(envProcess.getParentProcessId());
            parentProcess.load();
            processForm.setParentProcessName(parentProcess.getName());
            processForm.setParentProcessNumber(parentProcess.getProcessNumber());
        }
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_VIEW_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PROCESS_VIEW_MENU_ITEM);
    }
}