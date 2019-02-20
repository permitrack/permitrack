package com.sehinc.common.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class HTMLReportRunner
    extends ReportRunnerBase
{
    public HTMLReportRunner(Report report)
    {
        this.report =
            report;
    }

    public HTMLReportRunner(Report report, JRDataSource datasource)
    {
        this.report =
            report;
        this.datasource =
            datasource;
    }

    public void runToFile(HttpServletRequest request, File outputFile)
        throws SQLException, IOException, JRException, Exception
    {
        outputFile.getParentFile()
            .mkdirs();
        exporter =
            new JRHtmlExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE,
                              outputFile);
        run(request);
    }

    public void runToStream(HttpServletRequest request, OutputStream os)
        throws SQLException, IOException, JRException, Exception
    {
        exporter =
            new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                              os);
        run(request);
    }
}