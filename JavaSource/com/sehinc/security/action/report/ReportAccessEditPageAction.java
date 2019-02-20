package com.sehinc.security.action.report;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.report.CapReportAccess;
import com.sehinc.common.db.report.CapReportObject;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.report.ReportObjectValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.resources.ApplicationResources;
import com.sehinc.security.SecureObjectPermissionData;
import com.sehinc.security.action.base.RequestKeys;
import com.sehinc.security.action.navigation.PrimaryMenu;
import com.sehinc.security.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ReportAccessEditPageAction
    extends ReportAccessBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportAccessEditAction.class);

    public ActionForward reportAccessAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        String
            strLog =
            "ReportAccessEditPageAction.reportAccessAction() ";
        LOG.info(strLog
                 + "in action");
        ReportAccessForm
            raForm =
            (ReportAccessForm) form;
        ClientValue
            clientValue =
            getClientValue(request);
        UserValue
            userValue =
            getUserValue(request);
        SecurityManager
            securityManager =
            getSecurityManager(request);
        if (!securityManager.HasECPermission(SecureObjectPermissionData.RO_REPORT_UPDATE))
        {
            Object[]
                parameters =
                {
                    userValue.getUsername(),
                    "update"};
            LOG.info(ApplicationResources.getProperty("report.options.update.page.not.allowed",
                                                      parameters));
            addError(new ActionMessage("report.options.update.page.not.allowed",
                                       parameters), request);
            return mapping.findForward(CommonConstants.ACTION_ERROR);
        }
        ArrayList<Integer>
            selectedReports =
            new ArrayList<Integer>();
        List<CapReportAccess>
            reportsByClient =
            CapReportAccess.findByClientId(clientValue.getId());
        if (reportsByClient
            != null
            && reportsByClient.size()
               > 0)
        {
            for (CapReportAccess r : reportsByClient)
            {
                if (r.getEnabled())
                {
                    selectedReports.add(r.getReportId());
                }
            }
        }
        Integer[]
            selectedReportIds =
            new Integer[] {};
        if (selectedReports.size()
            > 0)
        {
            selectedReportIds =
                selectedReports.toArray(new Integer[selectedReports.size()]);
        }
        raForm.setSelectedReports(selectedReportIds);
        List
            allReportObjects =
            CapReportObject.findAllReports();
        List<ReportObjectValue>
            allReports =
            new ArrayList<ReportObjectValue>();
        for (Object o : allReportObjects)
        {
            CapReportObject
                rObject =
                (CapReportObject) o;
            ReportObjectValue
                value =
                new ReportObjectValue();
            try
            {
                CapModule
                    mod =
                    new CapModule(rObject.getModuleId());
                mod.load();
                value.setReportId(rObject.getId());
                value.setCode(rObject.getCode());
                value.setDescription(rObject.getDescription());
                value.setName(rObject.getName());
                value.setModuleName(mod.getName());
                allReports.add(value);
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {rObject.getId()};
                LOG.error(ApplicationResources.getProperty("report.access.error.load.failed",
                                                           parameters));
                LOG.error(e.getMessage());
                addError(new ActionMessage("report.access.error.load.failed",
                                           parameters), request);
                return mapping.findForward(CommonConstants.ACTION_ERROR);
            }
        }
        request.getSession()
            .setAttribute(RequestKeys.ALL_REPORTS,
                          allReports);
        return mapping.findForward("continue");
    }

    public void finalizeAction(HttpServletRequest request)
        throws Exception
    {
        super.finalizeAction(request);
        setPrimaryMenu(PrimaryMenu.SECURITY_MENU_NAME, request);
        setPrimaryMenuItem(request);
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.NONE_MENU_NAME), request);
    }
}
