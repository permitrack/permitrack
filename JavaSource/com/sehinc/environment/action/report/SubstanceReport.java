package com.sehinc.environment.action.report;

import com.sehinc.common.report.Report;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class SubstanceReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(SubstanceReport.class);

    public SubstanceReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ENV");
        setReportTitle("Total HAPs Report");
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/env/SubstanceLevels.jasper")));
        setReportParameter(EVReportParameter.SUB_LVL_SUB_REPORT,
                           new File(servletContext.getRealPath("/reports/env/SubstanceLevelsSubReport.jasper")));
        setReportParameter(EVReportParameter.SUB_LVL_GRAND_TOTAL_REPORT,
                           new File(servletContext.getRealPath("/reports/env/SubstanceGrandTotalsSubReport.jasper")));
        setReportParameter(EVReportParameter.SUB_LVL_CLIENT_TOTAL_REPORT,
                           new File(servletContext.getRealPath("/reports/env/SubstanceClientTotalsSubReport.jasper")));
        setReportParameter(EVReportParameter.REPORT_TITLE,
                           getReportTitle());
    }
}