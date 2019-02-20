package com.sehinc.security.action.report;

import com.sehinc.security.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionMessages;

public class ReportAccessForm
    extends BaseValidatorForm
{
    private
    Integer[]
        selectedReports;

    public Integer[] getSelectedReports()
    {
        return selectedReports;
    }

    public void setSelectedReports(Integer[] selectedReports)
    {
        this.selectedReports =
            selectedReports;
    }

    public void reset()
    {
        selectedReports =
            null;
    }

    public void validateForm(ActionMessages errors)
    {
    }
}