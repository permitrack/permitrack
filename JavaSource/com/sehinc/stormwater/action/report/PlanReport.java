package com.sehinc.stormwater.action.report;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.report.ReportParameter;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.value.plan.PlanValue;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class PlanReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanReport.class);

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("Stormwater+ (MS4)");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(ReportParameter.CLIENT_ID));
        PlanValue
            planValue =
            (PlanValue) SessionService.getSessionAttribute(SessionKeys.PLAN,
                                                           request);
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        if (planValue.getName()
            != null)
        {
            if (planValue.getName()
                    .length()
                > 0)
            {
                setReportTitle(planValue.getName());
            }
            else
            {
                setReportTitle("NPDES Phase II Storm Water\nPollution Prevention Plan");
            }
        }
        setReportFile(new File(servletContext.getRealPath(getPlanFile())));
        LOG.info("report file="
                 + getReportFile().getAbsolutePath());
        File
            subreportFile =
            new File(servletContext.getRealPath("/reports/ms4/PlanHeaderSubreport.jasper"));
        LOG.info("subreport file="
                 + subreportFile.getAbsolutePath());
        setReportParameter(SWReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(SWReportParameter.CLIENT_NAME,
                           clientData.getName());
        setReportParameter(SWReportParameter.CLIENT_WEB_SITE,
                           clientData.getWebPage());
        setReportParameter(SWReportParameter.PLAN_NAME,
                           planValue.getName());
        setReportParameter(SWReportParameter.PLAN_ID,
                           planValue.getId());
        setReportParameter(SWReportParameter.SUBREPORT_FILE,
                           JRLoader.loadObject(subreportFile));
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
        setReportParameter(SWReportParameter.LOGO_LOCATION,
                           clientLogo);
    }

    protected String getPlanFile()
    {
        return "/reports/ms4/PlanReport.jasper";
    }
}