package com.sehinc.environment.action.sourcescc;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SourceSccForm
    extends BaseValidatorForm
{
    private
    Integer
        sourceId;
    private
    Integer
        sccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDesc;

    public void setSourceId(Integer sourceId)
    {
        this.sourceId =
            sourceId;
    }

    public Integer getSourceId()
    {
        return sourceId;
    }

    public Integer getSccInfoId()
    {
        return sccInfoId;
    }

    public void setSccInfoId(Integer sccInfoId)
    {
        this.sccInfoId =
            sccInfoId;
    }

    public String getSccNumber()
    {
        return sccNumber;
    }

    public void setSccNumber(String sccNumber)
    {
        this.sccNumber =
            sccNumber;
    }

    public String getSccDesc()
    {
        return sccDesc;
    }

    public void setSccDesc(String sccDesc)
    {
        this.sccDesc =
            sccDesc;
    }

    public void reset()
    {
        sourceId =
            null;
        sccInfoId =
            null;
        sccNumber =
            null;
        sccDesc =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }
}

