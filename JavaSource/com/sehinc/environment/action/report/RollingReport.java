package com.sehinc.environment.action.report;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class RollingReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(RollingReport.class);

    public RollingReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ENV");
        setReportTitle("Rolling Report");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(EVReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/env/RollingReport.jasper")));
        setReportParameter(EVReportParameter.ROLLING_GRAND_TOTAL,
                           new File(servletContext.getRealPath("/reports/env/RollingReport_GrandTotal.jasper")));
        setReportParameter(EVReportParameter.ROLLING_PERMIT_TOTAL,
                           new File(servletContext.getRealPath("/reports/env/RollingReport_Permit.jasper")));
        setReportParameter(EVReportParameter.ROLLING_PERMIT_DETAIL_TOTAL,
                           new File(servletContext.getRealPath("/reports/env/RollingReport_PermitDetails.jasper")));
        setReportParameter(EVReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(EVReportParameter.CLIENT_NAME,
                           clientData.getName());
    }
}