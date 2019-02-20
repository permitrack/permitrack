package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;

public class InspectionSummaryReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionSummaryReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In InspectionSummaryReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        File
            outputFile =
            null;
        //
        // Get the User value from the session
        //
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            return mapping.findForward("fail");
        }
        //
        // Get the Client value from the session
        //
        ClientValue
            clientValue =
            getClientValue(request);
        if (clientValue
            == null)
        {
            return mapping.findForward("fail");
        }
        String
            startDate =
            DateUtil.ymdDate(reportForm.getInspStartDate());
        String
            endDate =
            DateUtil.ymdDate(reportForm.getInspEndDate());
        String
            reportQuery =
            "SELECT CLIENT.ID CLIENT_ID, CLIENT.NAME CLIENT_NAME, "
            + "EC_PROJECT_TYPE.NAME PROJECT_TYPE_NAME, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD in (1,2,4,5,6,7,8) THEN 1 ELSE 0 END) TOTAL_INSPECTIONS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 1 THEN 1 ELSE 0 END) ACTIVE_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 2 THEN 1 ELSE 0 END) INACTIVE_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 4 THEN 1 ELSE 0 END) INCOMPLETE_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 5 THEN 1 ELSE 0 END) ARCHIVED_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 6 THEN 1 ELSE 0 END) CLOSED_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 7 THEN 1 ELSE 0 END) AUTOACTIVATE_PROJECTS, "
            + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 8 THEN 1 ELSE 0 END) COMPLETED_PROJECTS  "
            + "FROM EC_PROJECT, EC_PROJECT_TYPE, CLIENT, EC_INSPECTION "
            + "WHERE EC_PROJECT.OWNER_CLIENT_ID = "
            + clientValue.getId()
            + " AND "
            + "     EC_PROJECT.OWNER_CLIENT_ID = CLIENT.ID AND "
            + "     EC_PROJECT.PROJECT_TYPE_ID = EC_PROJECT_TYPE.ID AND "
            + "     EC_PROJECT.ID = EC_INSPECTION.PROJECT_ID AND "
            + "     EC_INSPECTION.INSPECTION_DATE BETWEEN "
            + "\'"
            + startDate
            + "\' AND \'"
            + endDate
            + "\' AND "
            + "EC_INSPECTION.STATUS_CD = 1 "
            + "GROUP BY CLIENT.ID, CLIENT.NAME, EC_PROJECT_TYPE.NAME "
            + "ORDER BY CLIENT.NAME, EC_PROJECT_TYPE.NAME";
        try
        {
            InspectionSummaryReport
                inspectionSummaryReport =
                new InspectionSummaryReport();
            inspectionSummaryReport.setReportParameter(ECReportParameter.REPORT_QUERY,
                                                       reportQuery);
            inspectionSummaryReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                       clientValue.getId());
            inspectionSummaryReport.setReportParameter(ECReportParameter.START_DATE,
                                                       startDate);
            inspectionSummaryReport.setReportParameter(ECReportParameter.END_DATE,
                                                       endDate);
            //
            // Create the output file name based on the current time, user, and report format
            //
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
                //Create a XLS report runner
                ExcelApiReportRunner
                    excelApiReportRunner =
                    new ExcelApiReportRunner(inspectionSummaryReport);
                //Run the report
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
                //Create a PDF report runner
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(inspectionSummaryReport);
                //Run the report
                pdfReportRunner.runToFile(request,
                                          outputFile);
            }
            //Set report URL in form
            reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                       clientValue.getId(),
                                                       outputFile,
                                                       request));
            //Set report title in form
            reportForm.setTitle((String) inspectionSummaryReport.getReportParameter(ECReportParameter.REPORT_TITLE));
            setSessionAttribute("reportFormESC",
                                reportForm,
                                request);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.report.execution.failed"),
                     request);
            return mapping.findForward("fail");
        }
        return mapping.findForward("continue");
    }
}