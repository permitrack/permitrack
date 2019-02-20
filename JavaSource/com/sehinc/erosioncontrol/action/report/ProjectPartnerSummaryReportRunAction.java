package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.client.ClientData;
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

public class ProjectPartnerSummaryReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProjectPartnerSummaryReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, Exception
    {
        LOG.info("In ProjectPartnerSummaryReportRunAction");
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
            getUserValue(request);
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
        if (clientValue
            == null)
        {
            return mapping.findForward("fail");
        }
        //
        // Get the security manager
        //
        SecurityManager
            securityManager =
            getSecurityManager(request);
        //Set the client Id
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
                ClientData
                    clientData =
                    new ClientData();
                clientData.setId(reportForm.getClientId());
                if (clientData.load())
                {
                    clientValue =
                        new ClientValue(clientData);
                }
            }
        }
        //Get the client logo
/*
        String
            clientLogo =
            null;
*/
        ClientData
            clientData =
            new ClientData();
        clientData.setId(clientValue.getId());
        clientData.load();
/*
        if (clientData.getLogoLocation()
            != null
            && clientData.getLogoLocation()
                   .length()
               > 0)
        {
            clientLogo =
                ApplicationProperties.getProperty("base.document.directory")
                + "/client"
                + clientData.getId()
                + "/logo/"
                + clientData.getLogoLocation();
        }
*/
/*
        StringBuffer
            reportQuery =
            new StringBuffer("SELECT EC_PROJECT.ID MY_ORDER, EC_PROJECT.OWNER_CLIENT_ID OWNER_ID, "
                             + "CLIENT.NAME OWNER_NAME, CLIENT.NAME PARTNER_NAME, CLIENT.NAME PARTNER_TYPE_NAME, "
                             + "SUM(EC_PROJECT.PERMIT_AUTHORITY_CLIENT_ID) PERMIT_AUTHORITY_SUM, "
                             + "SUM(EC_PROJECT.PERMITTED_CLIENT_ID) PERMITTED_SUM, "
                             + "SUM(EC_PROJECT.AUTHORIZED_INSPECTOR_CLIENT_ID) AUTHORIZED_INSPECTOR_SUM, "
                             + "COUNT(EC_PROJECT.ID) PROJECT_COUNT "
                             + "FROM EC_PROJECT, CLIENT "
                             + "WHERE EC_PROJECT.OWNER_CLIENT_ID = 2 AND "
                             + "	EC_PROJECT.OWNER_CLIENT_ID = CLIENT.ID "
                             + "GROUP BY EC_PROJECT.ID, EC_PROJECT.OWNER_CLIENT_ID, CLIENT.NAME, CLIENT.NAME, CLIENT.NAME");
*/
/*
        ArrayList
            columns =
            new ArrayList();
*/
        try
        {
            //Create the Project Summary Report
            ProjectPartnerSummaryReport
                projectPartnerSummaryReport =
                new ProjectPartnerSummaryReport();
            //Set additional report parameters
            //projectSummaryReport.setReportParameter(ECReportParameter.REPORT_QUERY, reportQuery.toString());
            //projectSummaryReport.setReportParameter(ECReportParameter.TIME_FRAME, reportTimeFrame);
            if (reportForm.getClientId()
                == null
                || reportForm.getClientId()
                       .intValue()
                   > 0)
            {
                //	projectSummaryReport.setReportParameter(ECReportParameter.IS_MULTI_CLIENT_REPORT, false);
            }
            else if (reportForm.getClientId()
                         .intValue()
                     == 0)
            {
                //	projectSummaryReport.setReportParameter(ECReportParameter.IS_MULTI_CLIENT_REPORT, new Boolean(true));
            }
            //projectPartnerSummaryReport.setReportParameter(ECReportParameter.REPORT_QUERY, reportQuery.toString());
            projectPartnerSummaryReport.setReportParameter(ECReportParameter.CLIENT_NAME,
                                                           clientValue.getName());
            projectPartnerSummaryReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                           clientValue.getId());
            projectPartnerSummaryReport.setReportParameter(ECReportParameter.REPORT_TITLE,
                                                           "Project Relationship Summary");
            //projectPartnerSummaryReport.setReportParameter(ECReportParameter.LOGO_LOCATION,  clientLogo);
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
                    new ExcelApiReportRunner(projectPartnerSummaryReport);
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
                //Create a PDF report runner
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(projectPartnerSummaryReport);
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
            reportForm.setTitle((String) projectPartnerSummaryReport.getReportParameter(ECReportParameter.REPORT_TITLE));
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