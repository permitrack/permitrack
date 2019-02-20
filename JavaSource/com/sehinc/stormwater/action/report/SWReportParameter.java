package com.sehinc.stormwater.action.report;

import com.sehinc.common.report.ReportParameter;

public class SWReportParameter
    extends ReportParameter
{
    private SWReportParameter(String module)
    {
        super(module);
    }

    public static final
    SWReportParameter
        BMP_FORMATTER =
        new SWReportParameter("BMPFormatter");
    public static final
    SWReportParameter
        GOAL_ACTIVITY_ID =
        new SWReportParameter("GoalActivityId");
    public static final
    SWReportParameter
        PLAN_ID =
        new SWReportParameter("PlanId");
    public static final
    SWReportParameter
        PLAN_NAME =
        new SWReportParameter("PlanName");
    public static final
    SWReportParameter
        PUBLISH_URI =
        new SWReportParameter("PublishURI");
    public static final
    SWReportParameter
        SHOW_ACTIVITY =
        new SWReportParameter("ShowActivity");
    public static final
    SWReportParameter
        STORM_FILE_DIR =
        new SWReportParameter("StormFileDir");
    public static final
    SWReportParameter
        SUBREPORT_FILE =
        new SWReportParameter("PlanHeaderSubreport");
    public static final
    SWReportParameter
        TIME_FRAME_DESCRIPTION =
        new SWReportParameter("TimeFrameDescription");
    public static final
    SWReportParameter
        DRAFT_WATERMARK_FILE =
        new SWReportParameter("DraftWatermarkFile");
}
