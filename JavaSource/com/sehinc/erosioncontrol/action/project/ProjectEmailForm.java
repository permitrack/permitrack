package com.sehinc.erosioncontrol.action.project;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;

public class ProjectEmailForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectEmailForm.class);
    // Fields
    private
    Integer[]
        projectContacts =
        {};
    private
    String
        submit;

    public Integer[] getProjectContacts()
    {
        return this.projectContacts;
    }

    public void setProjectContacts(Integer[] projectContacts)
    {
        this.projectContacts =
            projectContacts;
    }

    public String getSubmit()
    {
        return submit;
    }

    public void setSubmit(String submit)
    {
        this.submit =
            submit;
    }

    public void reset()
    {
        this.projectContacts =
            new Integer[] {};
        this.submit =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}
