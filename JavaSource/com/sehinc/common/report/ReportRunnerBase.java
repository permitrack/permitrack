package com.sehinc.common.report;

import com.sehinc.common.db.base.DBConnectionPool;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Jesse
 * Date: 10/3/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ReportRunnerBase
    implements ReportRunner
{
    protected
    Logger
        LOG =
        Logger.getLogger(PDFReportRunner.class);
    protected
    Report
        report =
        null;
    protected
    JRAbstractExporter
        exporter =
        null;
    protected
    JRDataSource
        datasource =
        null;

    public OutputStream getOutputStream()
    {
        if (exporter
            == null)
        {
            return null;
        }
        return (OutputStream) exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
    }

    @Override
    public void runToFile(HttpServletRequest request, File outputFile)
        throws SQLException, IOException, JRException, Exception
    {
    }

    @Override
    public void runToStream(HttpServletRequest request, OutputStream outputStream)
        throws SQLException, IOException, JRException, Exception
    {
    }

    public File getOutputFile()
    {
        if (exporter
            == null)
        {
            return null;
        }
        return (File) exporter.getParameter(JRExporterParameter.OUTPUT_FILE);
    }

    protected void run(HttpServletRequest request)
        throws SQLException, IOException, JRException, Exception
    {
        report.init(request);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Running report "
                      + report.getReportFile()
                .getPath());
            LOG.debug("Report parameters: "
                      + report.getReportParameters()
                .toString());
        }
        try
        {
            long
                start =
                System.currentTimeMillis();
            LOG.info("Running report "
                     + report.getReportFile()
                .getPath());
            LOG.info(report.getReportParameters()
                         .toString());
            JasperReport
                jasperReport =
                (JasperReport) JRLoader.loadObject(report.getReportFile()
                                                       .getAbsoluteFile());
            JasperPrint
                jasperPrint =
                null;
            if (datasource
                != null)
            {
                jasperPrint =
                    JasperFillManager.fillReport(jasperReport,
                                                 report.getReportParameters(),
                                                 datasource);
            }
            else
            {
                Connection
                    connection =
                    DBConnectionPool.getConnection();
                if (connection
                    == null)
                {
                    LOG.debug("CONNECTION IS NULL!");
                }
                try
                {
                    jasperPrint =
                        JasperFillManager.fillReport(jasperReport,
                                                     report.getReportParameters(),
                                                     connection);
                }
                catch (JRException jre)
                {
                    LOG.debug("Error: PDFReportRunner.run(), reportFile="
                              + report.getReportFile()
                        .getPath()
                              + ", reportParameters="
                              + report.getReportParameters()
                        .toString());
                    LOG.debug(jre.getMessage());
                }
                finally
                {
                    LOG.debug("Closing Connection");
                    if (!connection.isClosed())
                    {
                        connection.close();
                    }
                }
            }
            LOG.info("Filling time : "
                     + (System.currentTimeMillis()
                        - start));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                                  jasperPrint);
            exporter.exportReport();
            LOG.info("PDF creation time : "
                     + (System.currentTimeMillis()
                        - start));
        }
        catch (JRException jre)
        {
            LOG.debug("Error with report: PDFReportRunner.run(), reportFile="
                      + report.getReportFile()
                .getPath()
                      + ", reportParameters="
                      + report.getReportParameters()
                .toString());
            LOG.debug("Error was: "
                      + jre.getMessage());
        }
    }
}
