package com.sehinc.stormwater.action.report;

public class PlanPublishReport
    extends PlanReport
{
    protected String getPlanFile()
    {
        return "/reports/ms4/PlanPublishReport.jasper";
    }
}