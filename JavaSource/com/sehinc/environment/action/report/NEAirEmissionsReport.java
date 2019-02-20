package com.sehinc.environment.action.report;

import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.report.Report;
import com.sehinc.common.service.client.ClientService;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class NEAirEmissionsReport
    extends Report
{
    private static
    Logger
        LOG =
        Logger.getLogger(NEAirEmissionsReport.class);

    public NEAirEmissionsReport()
    {
    }

    protected void initP(HttpServletRequest request)
        throws JRException
    {
        setSystemName("PermitTrack-ENV");
        setReportTitle("Nebraska Air Emissions Inventory Report");
        ClientData
            clientData =
            ClientService.getActiveClientById((Integer) getReportParameter(EVReportParameter.CLIENT_ID));
        ServletContext
            servletContext =
            request.getSession()
                .getServletContext();
        setReportFile(new File(servletContext.getRealPath("/reports/env/NE_Air_Form_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_1_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_1_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_1_0_SUBREPORT,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_1_0_Subreport.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_1_1,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_1_1.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_1_1_PROCESS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_1_1_Process.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_0_0_FACILITY_INFO,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_0_0_Facility_Info.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_0_AIR_POLLUTION,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_0_Air_Pollution.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_0_FACILITY_OP,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_0_Facility_Op.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_0_OPERATING,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_0_Operating.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_0_STACK,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_0_Stack.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_3_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_3_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_3_0_SUBREPORT,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_3_0_Subreport.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_4_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_4_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_4_0_OTHER_HAPS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_4_0_Other_Haps.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_4_0_GREATEST_HAP,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_4_0_Greatest_Hap.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_4_0_HAPS_LIST,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_4_0_Haps_List.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_5_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_5_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_5_0_VOCS_TOTALS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_5_0_VOCs_total.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_5_0_VOCS_LIST,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_5_0_Vocs_List.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_10_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_10_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_10_0_SUBREPORT,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_10_0_Subreport.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_10_1,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_10_1.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_10_1_SUBREPORT,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_10_1_Subreport.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_10_1_TOTALS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_10_1_Totals.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_1,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_1.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_1_EMISSIONS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_1_Emissions.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_2,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_2.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_2_EMISSIONS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_2_Emissions.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_3,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_3.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_2_3_EMISSIONS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_2_3_Emissions.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_12_0,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_12_0.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_12_0_SUBREPORT,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_12_0_Subreport.jasper")));
        setReportParameter(EVReportParameter.NE_AIR_FORM_12_0_TOTALS,
                           new File(servletContext.getRealPath("/reports/env/NE_Air_Form_12_0_Totals.jasper")));
        setReportParameter(EVReportParameter.REPORT_TITLE,
                           getReportTitle());
        setReportParameter(EVReportParameter.CLIENT_NAME,
                           clientData.getName());
    }
}