package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ECInspectionFormReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(ECInspectionReport.class);

    public ECInspectionFormReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Erosion Control (ESC)");
        setReportTitle("Project Inspection Form");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(ECReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/esc/ECInspectionFormReport.jasper")));
        setReportParameter(ECReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(ECReportParameter.CLIENT_NAME,
                           clientData.getName());
        setReportParameter(ECReportParameter.CLIENT_WEB_SITE,
                           clientData.getWebPage());
        String
            clientLogo =
            null;
        if (clientData.getLogoLocation()
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