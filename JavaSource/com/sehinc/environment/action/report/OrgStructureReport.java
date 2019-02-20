package com.sehinc.environment.action.report;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class OrgStructureReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(OrgStructureReport.class);

    public OrgStructureReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ENV");
        setReportTitle("Asset Structure Report");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(EVReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/env/AssetStructureReport.jasper")));
        setReportParameter(EVReportParameter.ASSETS_BY_SOURCE,
                           new File(servletContext.getRealPath("/reports/env/AssetsBySource_Report.jasper")));
        setReportParameter(EVReportParameter.SOURCES_BY_ASSET,
                           new File(servletContext.getRealPath("/reports/env/SourcesByAsset_Report.jasper")));
        setReportParameter(EVReportParameter.SOURCES_BY_SUBSTANCE,
                           new File(servletContext.getRealPath("/reports/env/SourcesBySubstance_Report.jasper")));
        setReportParameter(EVReportParameter.SUBSTANCES_BY_SOURCE,
                           new File(servletContext.getRealPath("/reports/env/SubstancesBySource_Report.jasper")));
        setReportParameter(EVReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(EVReportParameter.CLIENT_NAME,
                           clientData.getName());
    }
}