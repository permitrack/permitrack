package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.report.ReportParameter;

public class ECReportParameter
    extends ReportParameter
{
    public ECReportParameter(String module)
    {
        super(module);
    }

    public static final
    ECReportParameter
        FILE_DIR =
        new ECReportParameter("FileDir");
    public static final
    ECReportParameter
        SHOW_ACTIVITY =
        new ECReportParameter("ShowActivity");
    public static final
    ECReportParameter
        REPORT_FILE =
        new ECReportParameter("ReportFile");
    public static final
    ECReportParameter
        SUBREPORT_FILE =
        new ECReportParameter("SubreportFile");
    public static final
    ECReportParameter
        REPORT_DIR =
        new ECReportParameter("ReportDir");
    public static final
    ECReportParameter
        REPORT_TITLE =
        new ECReportParameter("ReportTitle");
    public static final
    ECReportParameter
        CLIENT_NAME =
        new ECReportParameter("ClientName");
    public static final
    ECReportParameter
        CLIENT_ID =
        new ECReportParameter("ClientId");
    public static final
    ECReportParameter
        CLIENT_WEB_SITE =
        new ECReportParameter("ClientWebSite");
    public static final
    ECReportParameter
        PROJECT_NAME =
        new ECReportParameter("ProjectName");
    public static final
    ECReportParameter
        PROJECT_ID =
        new ECReportParameter("ProjectId");
    public static final
    ECReportParameter
        INSPECTION_ID =
        new ECReportParameter("InspectionId");
    public static final
    ECReportParameter
        LOGO_LOCATION =
        new ECReportParameter("LogoLocation");
    public static final
    ECReportParameter
        TIME_FRAME =
        new ECReportParameter("TimeFrame");
    public static final
    ECReportParameter
        IS_MULTI_CLIENT_REPORT =
        new ECReportParameter("IsMultiClientReport");
    public static final
    ECReportParameter
        IS_SYSTEM_ADMIN_REPORT =
        new ECReportParameter("IsSystemAdminReport");
    public static final
    ECReportParameter
        DRAFT_WATERMARK_FILE =
        new ECReportParameter("DraftWatermarkFile");
    public static final
    ECReportParameter
        PUBLISH_URI =
        new ECReportParameter("PublishURI");
    public static final
    ECReportParameter
        PROJECT_URL =
        new ECReportParameter("ProjectURL");
    public static final
    ECReportParameter
        INSPECTION_URL =
        new ECReportParameter("InspectionURL");
    public static final
    ECReportParameter
        PROJECT_DOCUMENT_SUBREPORT =
        new ECReportParameter("ECProjectDocumentSubreport");
    public static final
    ECReportParameter
        PROJECT_CONTACT_SUBREPORT =
        new ECReportParameter("ECProjectContactSubreport");
    public static final
    ECReportParameter
        PROJECT_BMP_SUBREPORT =
        new ECReportParameter("ECProjectBMPSubreport");
    public static final
    ECReportParameter
        PROJECT_INSPECTION_SUBREPORT =
        new ECReportParameter("ECProjectInspectionSubreport");
    public static final
    ECReportParameter
        INSP_INSPECTIONS_SUBREPORT =
        new ECReportParameter("InspectionsSubreportFile");
    public static final
    ECReportParameter
        INSP_PROJECTS_SUBREPORT =
        new ECReportParameter("ProjectsSubreportFile");
    public static final
    ECReportParameter
        INSP_INSPECTORS_SUBREPORT =
        new ECReportParameter("InspectorsSubreportFile");
    public static final
    ECReportParameter
        INSP_DOCUMENT_SUBREPORT =
        new ECReportParameter("ECInspectionDocumentsSubreport");
    public static final
    ECReportParameter
        START_DATE =
        new ECReportParameter("StartDate");
    public static final
    ECReportParameter
        END_DATE =
        new ECReportParameter("EndDate");
    public static final
    ECReportParameter
        CERTIFICATION_ENABLED =
        new ECReportParameter("CertificationEnabled");
    public static final
    ECReportParameter
        CERTIFICATION_MESSAGE =
        new ECReportParameter("CertificationMsg");
}
