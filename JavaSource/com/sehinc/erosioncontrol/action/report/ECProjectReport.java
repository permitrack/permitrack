package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ECProjectReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(ECProjectReport.class);

    public ECProjectReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Erosion Control (ESC)");
        setReportTitle("Project Summary");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(ECReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        // Define the subreport files
        File
            projectDocumentSubreportFile =
            new File(servletContext.getRealPath("/reports/esc/ECProjectDocumentSubreport.jasper"));
        File
            projectContactSubreportFile =
            new File(servletContext.getRealPath("/reports/esc/ECProjectContactSubreport.jasper"));
        File
            projectBMPSubreportFile =
            new File(servletContext.getRealPath("/reports/esc/ECProjectBMPSubreport.jasper"));
        File
            projectInspectionSubreportFile =
            new File(servletContext.getRealPath("/reports/esc/ECProjectInspectionSubreport.jasper"));
        setReportFile(new File(servletContext.getRealPath("/reports/esc/ECProjectReport.jasper")));
        setReportParameter(ECReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(ECReportParameter.CLIENT_NAME,
                           clientData.getName());
        setReportParameter(ECReportParameter.CLIENT_WEB_SITE,
                           clientData.getWebPage());
        setReportParameter(ECReportParameter.PROJECT_CONTACT_SUBREPORT,
                           JRLoader.loadObject(projectContactSubreportFile));
        setReportParameter(ECReportParameter.PROJECT_DOCUMENT_SUBREPORT,
                           JRLoader.loadObject(projectDocumentSubreportFile));
        setReportParameter(ECReportParameter.PROJECT_BMP_SUBREPORT,
                           JRLoader.loadObject(projectBMPSubreportFile));
        setReportParameter(ECReportParameter.PROJECT_INSPECTION_SUBREPORT,
                           JRLoader.loadObject(projectInspectionSubreportFile));
        //setReportParameter(ECReportParameter.DRAFT_WATERMARK_FILE, new File(servletContext.getRealPath("/reports/draft.png")));
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