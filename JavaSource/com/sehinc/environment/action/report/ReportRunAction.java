package com.sehinc.environment.action.report;

import com.sehinc.environment.action.base.RequestKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In ReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        if (reportForm.getRunReport()
            == null)
        {
            String
                msg =
                "No report selected.";
            LOG.error("ReportRunAction: "
                      + msg);
            addMessage(new ActionMessage("report.no.report.selected"), request);
            return mapping.findForward("env.report.options.page");
        }
        String
            report =
            reportForm.getRunReport();
        if (report.equalsIgnoreCase(RequestKeys.EV_ROLLING_REPORT))
        {
            RollingReportRunAction
                reportRunAction =
                new RollingReportRunAction();
            return reportRunAction.reportAction(mapping,
                                                form,
                                                request,
                                                response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EV_ORG_STRUCTURE_REPORT))
        {
            OrgStructureRunAction
                orgStructRunAction =
                new OrgStructureRunAction();
            return orgStructRunAction.reportAction(mapping,
                                                   form,
                                                   request,
                                                   response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EV_SEMI_ANNUAL_REPORT))
        {
            SemiAnnualReportRunAction
                semiAnnualRunAction =
                new SemiAnnualReportRunAction();
            return semiAnnualRunAction.reportAction(mapping,
                                                    form,
                                                    request,
                                                    response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EV_SUBSTANCE_REPORT))
        {
            SubstanceReportRunAction
                substanceReportRunAction =
                new SubstanceReportRunAction();
            return substanceReportRunAction.reportAction(mapping,
                                                         form,
                                                         request,
                                                         response);
        }
        else if (report.equalsIgnoreCase(RequestKeys.EV_AIR_EM_INV_REPORT))
        {
            NEAirEmissionsReportRunAction
                airReportRunAction =
                new NEAirEmissionsReportRunAction();
            return airReportRunAction.reportAction(mapping,
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
        return mapping.findForward("env.report.options.page");
    }
}