package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.report.Report;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ProjectPartnerSummaryReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectPartnerSummaryReport.class);

    public ProjectPartnerSummaryReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Erosion Control Management System");
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/esc/ProjectPartnerSummaryReport.jasper")));
    }
}