package com.sehinc.environment.value;

public class SourceSccInfoValue
{
    private
    Integer
        sourceSccInfoId;
    private
    String
        sccNumber;
    private
    String
        sccDescription;

    public void setSourceSccInfoId(Integer sourceSccInfoId)
    {
        this.sourceSccInfoId =
            sourceSccInfoId;
    }

    public Integer getSourceSccInfoId()
    {
        return sourceSccInfoId;
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
