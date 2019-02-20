package com.sehinc.common.report;

public class ReportParameter
    implements java.io.Serializable
{

    private final
    String
        module;

    protected ReportParameter(String module)
    {
        this.module =
            module;
    }

    public String toString()
    {
        return module;
    }

    public static final
    ReportParameter
        REPORT_QUERY =
        new ReportParameter("ReportQuery");
    public static final
    ReportParameter
        BASE_URL =
        new ReportParameter("BaseURL");
    public static final
    ReportParameter
        REPORT_FILE =
        new ReportParameter("ReportFile");
    public static final
    ReportParameter
        LOGO_LOCATION =
        new ReportParameter("LogoLocation");
    public static final
    ReportParameter
        CLIENT_NAME =
        new ReportParameter("ClientName");
    public static final
    ReportParameter
        CLIENT_ID =
        new ReportParameter("ClientId");
    public static final
    ReportParameter
        CLIENT_WEB_SITE =
        new ReportParameter("ClientWebSite");
    public static final
    ReportParameter
        REPORT_DIR =
        new ReportParameter("ReportDir");
    public static final
    ReportParameter
        REPORT_TITLE =
        new ReportParameter("ReportTitle");
    public static final
    ReportParameter
        FORM_INSTANCE_ID =
        new ReportParameter("FormInstanceId");
}
