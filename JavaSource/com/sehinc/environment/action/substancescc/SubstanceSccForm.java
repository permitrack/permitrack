package com.sehinc.environment.action.substancescc;

import com.sehinc.common.action.base.BaseValidatorForm;
import org.apache.struts.action.ActionErrors;

public class SubstanceSccForm
    extends BaseValidatorForm
{
    private
    Integer
        substanceId;
    private
    Integer
        sccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDesc;

    public void setSubstanceId(Integer substanceId)
    {
        this.substanceId =
            substanceId;
    }

    public Integer getSubstanceId()
    {
        return substanceId;
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
        substanceId =
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


