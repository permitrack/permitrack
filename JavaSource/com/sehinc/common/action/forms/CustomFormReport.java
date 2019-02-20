package com.sehinc.common.action.forms;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.report.ReportParameter;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class CustomFormReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(CustomFormReport.class);

    public CustomFormReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Stormwater+ (MS4)");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(ReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/forms/CustomFormReport.jasper")));
        LOG.info("report file="
                 + getReportFile().getAbsolutePath());
        setReportParameter(ReportParameter.CLIENT_NAME,
                           clientData.getName());
        setReportParameter(ReportParameter.CLIENT_WEB_SITE,
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
        setReportParameter(ReportParameter.LOGO_LOCATION,
                           clientLogo);
    }
}
