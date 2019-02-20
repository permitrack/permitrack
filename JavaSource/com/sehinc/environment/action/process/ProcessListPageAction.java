package com.sehinc.environment.action.process;

import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.environment.action.base.SessionKeys;
import com.sehinc.environment.action.navigation.TertiaryMenu;
import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.facility.EnvFacility;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.environment.value.ProcessValue;
import com.sehinc.security.SecureObjectPermissionData;
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

public class ProcessListPageAction
    extends ProcessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessListPageAction.class);

    public ActionForward processAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In ProcessListPageAction");
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.EV_SOURCE_READ))
        {
            LOG.error(ApplicationResources.getProperty("error.page.access.denied"));
            addError(new ActionMessage("error.page.access.denied"), request);
            setSessionAttribute(SessionKeys.PAGE_PERMISSION_DENIED,
                                "View Processes",
                                request);
            return mapping.findForward("page.permission.denied");
        }
        setSessionAttribute(SessionKeys.PROCESS_CAN_UPDATE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PROCESS_UPDATE),
                            request);
        setSessionAttribute(SessionKeys.PROCESS_CAN_DELETE,
                            securityManager.HasECPermission(SecureObjectPermissionData.EV_PROCESS_DELETE),
                            request);
        ClientValue
            clientValue =
            getClientValue(request);
        Integer
            facilityId =
            (Integer) getFacility(request);
        EnvFacility
            facility =
            new EnvFacility(facilityId);
        String
            facilityName;
        try
        {
            facility.load();
            facilityName =
                facility.getName();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {e.getMessage()};
            addError(new ActionMessage("facility.error.load.failed",
                                       parameters), request);
            LOG.error(ApplicationResources.getProperty("facility.error.load.failed",
                                                       parameters));
            return mapping.getInputForward();
        }
        List<EnvProcess>
            processList =
            EnvProcess.findByClientIdAndFacilityId(clientValue.getId(),
                                                   facilityId);
        List<ProcessValue>
            pValueList =
            new ArrayList<ProcessValue>();
        for (EnvProcess process : processList)
        {
            if (process
                != null)
            {
                ProcessValue
                    processValue =
                    new ProcessValue(process);
                List<EnvAsset>
                    assets =
                    ProcessService.getAssetsForProcess(process.getId());
                if (assets
                    == null)
                {
                    addMessage(new ActionMessage("process.asset.error.load.asset.failed"), request);
                    return mapping.findForward("process.list.page");
                }
                processValue.setAssetList(assets);
                pValueList.add(processValue);
            }
        }
        request.getSession()
            .setAttribute(SessionKeys.EV_FACILITY_NAME,
                          facilityName);
        setSessionAttribute(SessionKeys.EV_PROCESS_ACTIVE_LIST_BY_CLIENT,
                            pValueList,
                            request);
        setSessionAttribute(SessionKeys.EV_PROCESS_ID,
                            null,
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setTertiaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setTertiaryMenu(TertiaryMenu.getInstance(TertiaryMenu.PROCESS_LIST_MENU),
                        request);
        getTertiaryMenu(request).setCurrentItem(TertiaryMenu.PROCESS_LIST_MENU_ITEM);
    }
}