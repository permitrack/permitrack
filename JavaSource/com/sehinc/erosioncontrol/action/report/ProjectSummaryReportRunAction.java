package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.action.base.SessionService;
import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
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

public class ProjectSummaryReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectSummaryReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In ProjectSummaryReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        File
            outputFile =
            null;
        //
        //get the User value from the session
        //
        UserValue
            userValue =
            SessionService.getUserValue(request);
        if (userValue
            == null)
        {
            return mapping.findForward("fail");
        }
        //
        //get the Client value from the session
        //
        ClientValue
            clientValue =
            getClientValue(request);
        int
            clientId =
            clientValue
            != null
                ? clientValue.getId()
                : 0;
        //
        // Get the security manager
        //
        SecurityManager
            securityManager =
            getSecurityManager(request);
        //Declare the WHERE clause that will be defined later
        String
            whereClause =
            "";
        //Set the client Id
        LOG.debug("the report requested is "
                  + reportForm.getRunReport());
        if (reportForm.getRunReport()
            .equals(RequestKeys.EC_ADMIN_PROJECT_SUMMARY_REPORT))
        {
            //This report is being run from the sysadmin menu
            //First make sure that the user is authorized to run this report, must be a sysadmin
            if (!securityManager.getIsSystemAdministrator())
            {
                LOG.warn("The current user "
                         + userValue.getUsername()
                         + " is not authorized to run this report.");
                addError(new ActionMessage("error.report.not.authorized"),
                         request);
                return mapping.findForward("fail");
            }
            //Next, check for a value for reportForm.getClientId()
            if (reportForm.getClientId()
                != null
                && reportForm.getClientId()
                       .intValue()
                   > 0)
            {
                clientId =
                    reportForm.getClientId()
                        .intValue(); //clientData.getId();
                //                }
                //First set the WHERE clause
                whereClause =
                    " EC_PROJECT.OWNER_CLIENT_ID = "
                    + clientId
                    + " AND ";
            }
        }
        else
        {
            //This report is being run from the client menu
            //Set the Client's Id in the WHERE clause
            whereClause =
                " EC_PROJECT.OWNER_CLIENT_ID = "
                + clientId
                + " AND ";
        }
        StringBuffer
            reportQuery =
            new StringBuffer("SELECT CLIENT.ID CLIENT_ID, CLIENT.NAME CLIENT_NAME, "
                             + "EC_PROJECT_TYPE.NAME PROJECT_TYPE_NAME, "
                             + "COUNT(EC_PROJECT.PROJECT_TYPE_ID) TOTAL_PROJECTS, "
                             + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 1 THEN 1 ELSE 0 END) ACTIVE_PROJECTS, "
                             + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 2 THEN 1 ELSE 0 END) INACTIVE_PROJECTS, "
                             + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 6 THEN 1 ELSE 0 END) CLOSED_PROJECTS, "
                             + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 5 THEN 1 ELSE 0 END) ARCHIVED_PROJECTS, "
                             + "SUM(CASE WHEN EC_PROJECT.PROJECT_STATUS_CD = 3 THEN 1 ELSE 0 END) DELETED_PROJECTS "
                             + "FROM EC_PROJECT, EC_PROJECT_TYPE, CLIENT "
                             + "WHERE "
                             + whereClause
                             + "     EC_PROJECT.OWNER_CLIENT_ID = CLIENT.ID AND "
                             + "     EC_PROJECT.PROJECT_TYPE_ID = EC_PROJECT_TYPE.ID "
                             + "GROUP BY CLIENT.ID, CLIENT.NAME, EC_PROJECT_TYPE.NAME "
                             + "ORDER BY CLIENT.NAME, EC_PROJECT_TYPE.NAME");
        try
        {
            //Create the Project Summary Report
            ProjectSummaryReport
                projectSummaryReport =
                new ProjectSummaryReport();
            //Set additional report parameters
            if (reportForm.getRunReport()
                .equals(RequestKeys.EC_ADMIN_PROJECT_SUMMARY_REPORT))
            {
                projectSummaryReport.setReportParameter(ECReportParameter.IS_SYSTEM_ADMIN_REPORT,
                                                        new Boolean(true));
            }
            else
            {
                projectSummaryReport.setReportParameter(ECReportParameter.IS_SYSTEM_ADMIN_REPORT,
                                                        false);
            }
            projectSummaryReport.setReportParameter(ECReportParameter.REPORT_QUERY,
                                                    reportQuery.toString());
            projectSummaryReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                    clientId);
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
                + clientId
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
                    new ExcelApiReportRunner(projectSummaryReport);
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
                    new PDFReportRunner(projectSummaryReport);
                //Run the report
                pdfReportRunner.runToFile(request,
                                          outputFile);
            }
            //Set report URL in form
            reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                       clientId,
                                                       outputFile,
                                                       request));
            //Set report title in form
            reportForm.setTitle((String) projectSummaryReport.getReportParameter(ECReportParameter.REPORT_TITLE));
            setSessionAttribute("reportFormESC",
                                reportForm,
                                request);
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.report.execution.failed",
                                       e.getMessage()),
                     request);
            return mapping.findForward("fail");
        }
        return mapping.findForward("continue");
    }
}