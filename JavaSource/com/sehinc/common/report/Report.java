package com.sehinc.common.report;

import net.sf.jasperreports.engine.JRException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class Report
{
/*
    private
    Logger
        LOG =
        Logger.getLogger(Report.class);
*/
    private
    HashMap
        parameters;
    private
    HttpServletRequest
        request;
    private
    String
        reportTitle;
    private
    String
        systemName;
    private
    String
        reportFilePath;
    private
    File
        reportFile;
    private
    File
        outputFile;
    private
    byte[]
        output;
    private
    boolean
        isInitComplete =
        false;

    public void setSystemName(String systemName)
    {
        this.systemName =
            systemName;
    }

    public String getSystemName()
    {
        return systemName;
    }

    public void setReportTitle(String reportTitle)
    {
        this.reportTitle =
            reportTitle;
    }

    public String getReportTitle()
    {
        return reportTitle;
    }

    public void setReportFile(File reportFile)
    {
        this.reportFile =
            reportFile;
    }

    public File getReportFile()
    {
        return reportFile;
    }

    public void setOutput(byte[] output)
    {
        this.output =
            output;
    }

    public byte[] getOutput()
    {
        return output;
    }

    public void setReportParameter(ReportParameter key, Object value)
    {
        if (parameters
            == null)
        {
            parameters =
                new HashMap();
        }
        parameters.put(key.toString(),
                       value);
    }

    public Object getReportParameter(ReportParameter key)
    {
        if (parameters
            != null)
        {
            if (parameters.containsKey(key.toString()))
            {
                return parameters.get(key.toString());
            }
        }
        return null;
    }

    public Map getReportParameters()
    {
        if (parameters
            == null)
        {
            return new HashMap();
        }
        return parameters;
    }

    protected abstract void initP(HttpServletRequest request)
        throws JRException;

    public void init(HttpServletRequest request)
        throws JRException
    {
        if (!isInitComplete)
        {
            initP(request);
        }
        isInitComplete =
            true;
    }
}