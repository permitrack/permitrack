package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.CommonConstants;
import com.sehinc.common.db.security.CapModule;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.action.navigation.SecondaryMenu;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminReportOptionsAction
    extends AdminReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(AdminReportOptionsAction.class);

    public ActionForward adminReportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        LOG.info("In AdminReportOptionsAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        reportForm.reset();
        reportForm.setStartDate(DateUtil.getLastMonthStartDate());
        reportForm.setEndDate(DateUtil.getLastMonthEndDate());
        setSessionAttribute(SessionKeys.ADMIN_CLIENT_SELECT_LIST,
                            ClientService.getActiveClientsByModule(CapModule.findByCode(CommonConstants.EROSION_CONTROL_MODULE)),
                            request);
        return mapping.findForward("continue");
    }

    @Override
    protected void setSecondaryMenu(HttpServletRequest request)
        throws ServletException
    {
        setSecondaryMenu(SecondaryMenu.getInstance(SecondaryMenu.ADMIN_REPORT_MENU_NAME),
                         request);
    }
}