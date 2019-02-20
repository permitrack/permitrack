package com.sehinc.environment.action.report;

import com.sehinc.common.report.Report;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class SemiAnnualReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(SemiAnnualReport.class);

    public SemiAnnualReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ENV");
        setReportTitle("Semi-Annual Report");
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/env/SemiAnnualReport.jasper")));
        setReportParameter(EVReportParameter.REPORT_TITLE,
                           getReportTitle());
    }
}