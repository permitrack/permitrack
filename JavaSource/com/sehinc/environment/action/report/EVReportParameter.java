package com.sehinc.environment.action.report;

import com.sehinc.common.report.ReportParameter;

public class EVReportParameter
    extends ReportParameter
{
    public EVReportParameter(String module)
    {
        super(module);
    }

    public static final
    EVReportParameter
        FILE_DIR =
        new EVReportParameter("FileDir");
    public static final
    EVReportParameter
        SHOW_ACTIVITY =
        new EVReportParameter("ShowActivity");
    public static final
    EVReportParameter
        REPORT_FILE =
        new EVReportParameter("ReportFile");
    public static final
    EVReportParameter
        SUBREPORT_FILE =
        new EVReportParameter("SubreportFile");
    public static final
    EVReportParameter
        REPORT_DIR =
        new EVReportParameter("ReportDir");
    public static final
    EVReportParameter
        REPORT_TITLE =
        new EVReportParameter("ReportTitle");
    public static final
    EVReportParameter
        CLIENT_NAME =
        new EVReportParameter("ClientName");
    public static final
    EVReportParameter
        CLIENT_WEB_SITE =
        new EVReportParameter("ClientWebSite");
    public static final
    EVReportParameter
        PROJECT_NAME =
        new EVReportParameter("ProjectName");
    public static final
    EVReportParameter
        PROJECT_ID =
        new EVReportParameter("ProjectId");
    public static final
    EVReportParameter
        INSPECTION_ID =
        new EVReportParameter("InspectionId");
    public static final
    EVReportParameter
        LOGO_LOCATION =
        new EVReportParameter("LogoLocation");
    public static final
    EVReportParameter
        TIME_FRAME =
        new EVReportParameter("TimeFrame");
    public static final
    EVReportParameter
        IS_MULTI_CLIENT_REPORT =
        new EVReportParameter("IsMultiClientReport");
    public static final
    EVReportParameter
        IS_SYSTEM_ADMIN_REPORT =
        new EVReportParameter("IsSystemAdminReport");
    public static final
    EVReportParameter
        DRAFT_WATERMARK_FILE =
        new EVReportParameter("DraftWatermarkFile");
    public static final
    EVReportParameter
        START_DATE =
        new EVReportParameter("StartDate");
    public static final
    EVReportParameter
        END_DATE =
        new EVReportParameter("EndDate");
    public static final
    EVReportParameter
        YEAR =
        new EVReportParameter("year");
    public static final
    EVReportParameter
        CLIENT_ID =
        new EVReportParameter("clientId");
    public static final
    EVReportParameter
        PERIOD =
        new EVReportParameter("period");
    public static final
    EVReportParameter
        EV_CLIENT_ID =
        new EVReportParameter("ClientId");
    public static final
    EVReportParameter
        CLIENT_ADDRESS =
        new EVReportParameter("ClientAddress");
    public static final
    EVReportParameter
        CONTACT_NAME =
        new EVReportParameter("ContactName");
    public static final
    EVReportParameter
        CONTACT_PHONE =
        new EVReportParameter("ContactPhone");
    public static final
    EVReportParameter
        CONTACT_TITLE =
        new EVReportParameter("ContactTitle");
    public static final
    EVReportParameter
        PERMIT_NAME =
        new EVReportParameter("PermitName");
    public static final
    EVReportParameter
        PERMIT_ID =
        new EVReportParameter("PermitId");
    public static final
    EVReportParameter
        ROLLING_GRAND_TOTAL =
        new EVReportParameter("GrandTotalFile");
    public static final
    EVReportParameter
        ROLLING_PERMIT_TOTAL =
        new EVReportParameter("PermitTotalFile");
    public static final
    EVReportParameter
        ROLLING_PERMIT_DETAIL_TOTAL =
        new EVReportParameter("PermitDetailTotalFile");
    public static final
    EVReportParameter
        SEMI_ANNUAL_DETAIL =
        new EVReportParameter("SemiAnnualDetailFile");
    public static final
    EVReportParameter
        ASSET_LIST =
        new EVReportParameter("AssetList");
    public final static
    EVReportParameter
        SEMI_ANNUAL_HEADER =
        new EVReportParameter("SemiAnnualHeaderFile");
    public static final
    EVReportParameter
        SUB_LVL_SUB_REPORT =
        new EVReportParameter("SubstanceLevelsSubreportFile");
    public static final
    EVReportParameter
        SUB_LVL_GRAND_TOTAL_REPORT =
        new EVReportParameter("SubstanceGrandTotalsSubreportFile");
    public static final
    EVReportParameter
        SUB_LVL_CLIENT_TOTAL_REPORT =
        new EVReportParameter("SubstanceClientTotalsSubreportFile");
    public static final
    EVReportParameter
        ASSETS_BY_SOURCE =
        new EVReportParameter("AssetsBySourceFile");
    public static final
    EVReportParameter
        SOURCES_BY_ASSET =
        new EVReportParameter("SourcesByAssetFile");
    public static final
    EVReportParameter
        SOURCES_BY_SUBSTANCE =
        new EVReportParameter("SourcesBySubstanceFile");
    public static final
    EVReportParameter
        SUBSTANCES_BY_SOURCE =
        new EVReportParameter("SubstancesBySourceFile");
    public static final
    EVReportParameter
        NE_AIR_FORM_0_0_FACILITY_INFO =
        new EVReportParameter("NE_Air_Form_0_0_Facility_Info_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_1_0 =
        new EVReportParameter("NE_Air_Form_1_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_1_0_SUBREPORT =
        new EVReportParameter("NE_Air_Form_1_0_Subreport_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_1_1 =
        new EVReportParameter("NE_Air_Form_1_1_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_1_1_PROCESS =
        new EVReportParameter("NE_Air_Form_1_1_Process_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_0 =
        new EVReportParameter("NE_Air_Form_2_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_0_AIR_POLLUTION =
        new EVReportParameter("NE_Air_Form_2_0_Air_Pollution_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_0_FACILITY_OP =
        new EVReportParameter("NE_Air_Form_2_0_Facility_Op_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_0_OPERATING =
        new EVReportParameter("NE_Air_Form_2_0_Operating_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_0_STACK =
        new EVReportParameter("NE_Air_Form_2_0_Stack_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_1 =
        new EVReportParameter("NE_Air_Form_2_1_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_1_EMISSIONS =
        new EVReportParameter("NE_Air_Form_2_1_Emissions_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_2 =
        new EVReportParameter("NE_Air_Form_2_2_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_2_EMISSIONS =
        new EVReportParameter("NE_Air_Form_2_2_Emissions_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_3 =
        new EVReportParameter("NE_Air_Form_2_3_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_2_3_EMISSIONS =
        new EVReportParameter("NE_Air_Form_2_3_Emissions_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_3_0 =
        new EVReportParameter("NE_Air_Form_3_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_3_0_SUBREPORT =
        new EVReportParameter("NE_Air_Form_3_0_Subreport_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_4_0 =
        new EVReportParameter("NE_Air_Form_4_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_4_0_OTHER_HAPS =
        new EVReportParameter("NE_Air_Form_4_0_Other_Haps_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_4_0_GREATEST_HAP =
        new EVReportParameter("NE_Air_Form_4_0_Greatest_Hap_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_4_0_HAPS_LIST =
        new EVReportParameter("NE_Air_Form_4_0_Haps_List_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_5_0 =
        new EVReportParameter("NE_Air_Form_5_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_5_0_VOCS_TOTALS =
        new EVReportParameter("NE_Air_Form_5_0_Vocs_Total_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_5_0_VOCS_LIST =
        new EVReportParameter("NE_Air_Form_5_0_Vocs_List_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_10_0 =
        new EVReportParameter("NE_Air_Form_10_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_10_0_SUBREPORT =
        new EVReportParameter("NE_Air_Form_10_0_Subreport_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_10_1 =
        new EVReportParameter("NE_Air_Form_10_1_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_10_1_SUBREPORT =
        new EVReportParameter("NE_Air_Form_10_1_Subreport_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_10_1_TOTALS =
        new EVReportParameter("NE_Air_Form_10_1_Totals_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_12_0 =
        new EVReportParameter("NE_Air_Form_12_0_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_12_0_SUBREPORT =
        new EVReportParameter("NE_Air_Form_12_0_Subreport_File");
    public static final
    EVReportParameter
        NE_AIR_FORM_12_0_TOTALS =
        new EVReportParameter("NE_Air_Form_12_0_Totals_File");
}