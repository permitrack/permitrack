package com.sehinc.stormwater.action.report;

import com.sehinc.common.db.base.DBConnectionPool;
import com.sehinc.common.report.ReportParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public abstract class FileReport
{
    private
    Logger
        LOG =
        Logger.getLogger(FileReport.class);
    private
    HashMap
        parameters;
    protected
    String
        reportTitle;
    protected
    String
        systemName;
    private
    File
        reportFile;
    private
    File
        outputFile;

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

    public File getOutputFile()
    {
        return outputFile;
    }

    public void setOutputFile(File outputFile)
    {
        this.outputFile =
            outputFile;
    }

    protected abstract void init();

    private void run()
        throws Exception
    {
        Connection
            connection =
            DBConnectionPool.getConnection();
        byte[]
            bytes =
            null;
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Running report "
                      + reportFile.getPath());
            LOG.debug("Report parameters: "
                      + getReportParameters().toString());
        }
        try
        {
            bytes =
                JasperRunManager.runReportToPdf(reportFile.getPath(),
                                                getReportParameters(),
                                                connection);
            LOG.debug("JasperRunManager was successful");
        }
        finally
        {
            if (!connection.isClosed())
            {
                connection.close();
            }
        }
        if (bytes
            != null
            && bytes.length
               > 0)
        {
            outputFile.getParentFile()
                .mkdirs();
            ByteArrayInputStream
                stream =
                new ByteArrayInputStream(bytes);
            OutputStream
                bos =
                new FileOutputStream(outputFile);
            int
                bytesRead;
            byte[]
                buffer =
                new byte[8192];
            while ((
                       bytesRead =
                           stream.read(buffer,
                                       0,
                                       8192))
                   != -1)
            {
                bos.write(buffer,
                          0,
                          bytesRead);
            }
            bos.close();
            stream.close();
        }
        else
        {
            throw new Exception("No data found for the requested report.");
        }
    }

    public void execute()
        throws Exception
    {
        init();
        run();
    }
}