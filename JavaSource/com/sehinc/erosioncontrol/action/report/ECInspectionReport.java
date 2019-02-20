package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.erosioncontrol.value.inspection.InspectionMapValue;
import com.sehinc.erosioncontrol.value.project.ProjectMapValue;
import net.sf.jasperreports.engine.JRException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class ECInspectionReport
    extends Report
{
    public ECInspectionReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Erosion Control (ESC)");
        setReportTitle("Inspection Report");
        Integer
            clientId =
            (Integer) getReportParameter(ECReportParameter.CLIENT_ID);
        Integer
            projectId =
            (Integer) getReportParameter(ECReportParameter.PROJECT_ID);
        Integer
            inspectionId =
            (Integer) getReportParameter(ECReportParameter.INSPECTION_ID);
        ClientData
            clientData =
            ClientService.getActiveClientById(clientId);
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/esc/ECInspectionReport.jasper")));
        setReportParameter(ECReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(ECReportParameter.CLIENT_NAME,
                           clientData.getName());
        setReportParameter(ECReportParameter.CLIENT_WEB_SITE,
                           clientData.getWebPage());
        setReportParameter(ECReportParameter.DRAFT_WATERMARK_FILE,
                           new File(servletContext.getRealPath("/reports/img/draft.png")));
        setReportParameter(ECReportParameter.CERTIFICATION_ENABLED,
                           clientData.getClientAdminSettings()
                               .isInspectionCertificationEnabled());
        setReportParameter(ECReportParameter.CERTIFICATION_MESSAGE,
                           clientData.getClientAdminSettings()
                               .getInspectionCertificationMessage());
        ProjectMapValue
            projectMapValue =
            new ProjectMapValue();
        projectMapValue.setId(projectId);
        projectMapValue.setUrl(clientData.getId(),
                               request);
        setReportParameter(ECReportParameter.PROJECT_URL,
                           projectMapValue.getUrl());
        InspectionMapValue
            inspectionMapValue =
            new InspectionMapValue();
        inspectionMapValue.setId(inspectionId);
        inspectionMapValue.setProjectId(projectId);
        inspectionMapValue.setUrl(clientData.getId(),
                                  request);
        setReportParameter(ECReportParameter.INSPECTION_URL,
                           inspectionMapValue.getUrl());
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