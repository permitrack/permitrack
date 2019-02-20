package com.sehinc.erosioncontrol.action.report;

import com.sehinc.erosioncontrol.action.base.RequestKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In ReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        String
            report =
            reportForm.getRunReport();
        // Delegate the report to the proper action class
        if (report.equalsIgnoreCase(RequestKeys.EC_INVOICE_SUMMARY_REPORT)
            || report.equalsIgnoreCase(RequestKeys.EC_ADMIN_INVOICE_SUMMARY_REPORT))
        {
            InvoiceReportRunAction
                reportRunAction =
                new InvoiceReportRunAction();
            return reportRunAction.reportAction(mapping,
                                                form,
                                                request,
                                                response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EC_PROJECT_SUMMARY_REPORT)
                 || report.equalsIgnoreCase(RequestKeys.EC_ADMIN_PROJECT_SUMMARY_REPORT))
        {
            ProjectSummaryReportRunAction
                reportRunAction =
                new ProjectSummaryReportRunAction();
            return reportRunAction.reportAction(mapping,
                                                form,
                                                request,
                                                response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EC_INSPECTION_SUMMARY_REPORT))
        {
            InspectionSummaryReportRunAction
                reportRunAction =
                new InspectionSummaryReportRunAction();
            return reportRunAction.reportAction(mapping,
                                                form,
                                                request,
                                                response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EC_CLIENT_EXPORT_ALL))
        {
            ClientDataExport
                reportRunAction =
                new ClientDataExport();
            return reportRunAction.reportAction(mapping,
                                                form,
                                                request,
                                                response);
        }
        String
            msg =
            "The requested report '"
            + report
            + "' is undefined.";
        LOG.error("ReportRunAction: "
                  + msg);
        return mapping.findForward("fail");
    }

    @Override
    protected ActionForward handleError(ActionMapping mapping, String messageKey, HttpServletRequest request, Object... params)
    {
        if (messageKey.equals("error.no.client.in.session"))
        {
            return null;
        }
        else
        {
            return super.handleError(mapping,
                                     messageKey,
                                     request,
                                     params);
        }
    }
}