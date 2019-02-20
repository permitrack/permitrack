package com.sehinc.stormwater.action.report;

public class PlanExportReport
    extends PlanReport
{
    protected String getPlanFile()
    {
        return "/reports/ms4/PlanExportReport.jasper";
    }
}