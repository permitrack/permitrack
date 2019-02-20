package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ProjectSummaryReport
    extends Report
{
    public ProjectSummaryReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ESC");
        setReportTitle("Project Summary Report");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(ECReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/esc/ProjectSummaryReport.jasper")));
        setReportParameter(ECReportParameter.SUBREPORT_FILE,
                           new File(servletContext.getRealPath("/reports/esc/ProjectPartnerSummaryReport.jasper")));
        setReportParameter(ECReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(ECReportParameter.CLIENT_NAME,
                           clientData
                           != null
                               ? clientData.getName()
                               : "All clients");
        setReportParameter(ECReportParameter.CLIENT_WEB_SITE,
                           clientData
                           != null
                               ? clientData.getWebPage()
                               : "");
        String
            clientLogo =
            null;
        if (clientData
            != null
            && clientData.getLogoLocation()
               != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                + clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
        }
        setReportParameter(ECReportParameter.LOGO_LOCATION,
                           clientLogo);
    }
}