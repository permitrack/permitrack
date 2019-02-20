package com.sehinc.common.report;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class ExcelApiReportRunner
    extends ReportRunnerBase
{

    public ExcelApiReportRunner(Report report)
    {
        this.report =
            report;
    }

    public ExcelApiReportRunner(Report report, JRDataSource datasource)
    {
        this.report =
            report;
        this.datasource =
            datasource;
    }

    protected void run(HttpServletRequest request)
        throws SQLException, IOException, JRException, Exception
    {
//        report.init(request);
//        if (LOG.isDebugEnabled())
//        {
//            LOG.debug("Running report "
//                      + report.getReportFile()
//                .getPath());
//            LOG.debug("Report parameters: "
//                      + report.getReportParameters()
//                .toString());
//        }
//        try
//        {
//            long
//                start =
//                System.currentTimeMillis();
//            LOG.info("Running report "
//                     + report.getReportFile()
//                .getPath());
//            LOG.info(report.getReportParameters()
//                         .toString());
//            JasperReport
//                jasperReport =
//                (JasperReport) JRLoader.loadObject(report.getReportFile()
//                                                       .getAbsoluteFile());
//            JasperPrint
//                jasperPrint =
//                null;
//            if (datasource
//                != null)
//            {
//                jasperPrint =
//                    JasperFillManager.fillReport(jasperReport,
//                                                 report.getReportParameters(),
//                                                 datasource);
//            }
//            else
//            {
//                Connection
//                    connection =
//                    DBConnectionPool.getConnection();
//                if (connection
//                    == null)
//                {
//                    LOG.debug("CONNECTION IS NULL!");
//                }
//                try
//                {
//                    jasperPrint =
//                        JasperFillManager.fillReport(jasperReport,
//                                                     report.getReportParameters(),
//                                                     connection);
//                }
//                catch (JRException jre)
//                {
//                    LOG.debug("Error: ExcelApiReportRunner.run(), reportFile="
//                              + report.getReportFile()
//                        .getPath()
//                              + ", reportParameters="
//                              + report.getReportParameters()
//                        .toString());
//                    LOG.debug(jre.getMessage());
//                }
//                finally
//                {
//                    LOG.debug("Closing Connection");
//                    if (!connection.isClosed())
//                    {
//                        connection.close();
//                    }
//                }
//            }
//            LOG.info("Filling time : "
//                     + (System.currentTimeMillis()
//                        - start));
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
//                                  jasperPrint);
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                                  Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                                  Boolean.TRUE);

        super.run(request);
//            exporter.exportReport();
//            LOG.info("XLS creation time : "
//                     + (System.currentTimeMillis()
//                        - start));
//        }
//        catch (JRException jre)
//        {
//            LOG.debug("Error: ExcelApiReportRunner.run(), reportFile="
//                      + report.getReportFile()
//                .getPath()
//                      + ", reportParameters="
//                      + report.getReportParameters()
//                .toString());
//            LOG.debug(jre.getMessage());
//        }
    }

    public void runToFile(HttpServletRequest request, File outputFile)
        throws SQLException, IOException, JRException, Exception
    {
        outputFile.getParentFile()
            .mkdirs();
        exporter =
            new JExcelApiExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE,
                              outputFile);
        run(request);
    }

    public void runToStream(HttpServletRequest request, OutputStream os)
        throws SQLException, IOException, JRException, Exception
    {
        exporter =
            new JExcelApiExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                              os);
        run(request);
    }
}