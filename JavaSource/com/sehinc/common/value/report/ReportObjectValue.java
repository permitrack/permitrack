package com.sehinc.common.value.report;

public class ReportObjectValue
{
    private
    Integer
        reportId;
    private
    String
        code;
    private
    String
        name;
    private
    String
        description;
    private
    Boolean
        enabled;
    private
    String
        moduleName;

    public ReportObjectValue()
    {
    }

    public void setReportId(Integer reportId)
    {
        this.reportId =
            reportId;
    }

    public Integer getReportId()
    {
        return reportId;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code =
            code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name =
            name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description =
            description;
    }

    public Boolean getEnabled()
    {
        return enabled;
    }

    public void setEnabled(Boolean enabled)
    {
        this.enabled =
            enabled;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName =
            moduleName;
    }
}
