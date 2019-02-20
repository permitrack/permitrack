package com.sehinc.environment.value;

public class SubstanceSccInfoValue
{
    private
    Integer
        substanceSccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDescription;

    public void setSubstanceSccInfoId(Integer substanceSccInfoId)
    {
        this.substanceSccInfoId =
            substanceSccInfoId;
    }

    public Integer getSubstanceSccInfoId()
    {
        return substanceSccInfoId;
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

    public String getSccDescription()
    {
        return sccDescription;
    }

    public void setSccDescription(String sccDescription)
    {
        this.sccDescription =
            sccDescription;
    }
}
