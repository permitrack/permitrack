package com.sehinc.stormwater.action.report;

public class BMPReport
    extends MCMReport
{
    public BMPReport()
    {
        setReportTitle("Best Management Practice Report");
    }

    protected String getPlanFile()
    {
        return "/reports/ms4/BMPReport.jasper";
    }
}