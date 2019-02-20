package com.sehinc.environment.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

public class RollingReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(RollingReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In RollingReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        File
            outputFile =
            null;
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        try
        {
            Integer
                year =
                reportForm.getYear();
            RollingReport
                rollingReport =
                new RollingReport();
            rollingReport.setReportParameter(EVReportParameter.CLIENT_ID,
                                             clientValue.getId());
            rollingReport.setReportParameter(EVReportParameter.YEAR,
                                             year);
            Date
                nowDate =
                new Date();
            String
                nowTime =
                String.valueOf(nowDate.getTime());
            String
                outputFileName =
                "report_"
                + nowTime
                + String.valueOf(userValue.getId());
            String
                outputFilePath =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                + clientValue.getId()
                + "/temp/";
            if (reportForm.getReportFormat()
                .equalsIgnoreCase("XLS"))
            {
                outputFile =
                    new File(outputFilePath
                             + outputFileName
                             + ".xls");
                ExcelApiReportRunner
                    excelApiReportRunner =
                    new ExcelApiReportRunner(rollingReport);
                excelApiReportRunner.runToFile(request,
                                               outputFile);
            }
            else if (reportForm.getReportFormat()
                .equalsIgnoreCase("PDF"))
            {
                outputFile =
                    new File(outputFilePath
                             + outputFileName
                             + ".pdf");
                LOG.debug("outputFile="
                          + outputFile.getAbsolutePath());
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(rollingReport);
                pdfReportRunner.runToFile(request,
                                          outputFile);
            }
            else if (reportForm.getReportFormat()
                .equalsIgnoreCase("CSV"))
            {
            }
            reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                       clientValue.getId(),
                                                       outputFile,
                                                       request));
            reportForm.setTitle((String) rollingReport.getReportParameter(EVReportParameter.REPORT_TITLE));
            setSessionAttribute("reportFormENV",
                                reportForm,
                                request);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.report.execution.failed",
                                       e.getMessage()), request);
            return mapping.findForward("env.report.options.page");
        }
        return mapping.findForward("continue");
    }
}