package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.security.SecurityManager;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

public class InvoiceReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(InvoiceReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        //        LOG.info("In InvoiceReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        File
            outputFile =
            null;
        UserValue
            userValue =
            getUserValue(request);
        if (userValue
            == null)
        {
            return mapping.findForward("fail");
        }
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
        //            SecurityManager.getSecurityManager(request);
        String
            reportTimeFrame;
        String
            dateSubQuery;
        if (reportForm.getStartDate()
            != null
            && reportForm.getEndDate()
               != null)
        {
            StringBuffer
                reportQuery =
                new StringBuffer("SELECT EC_PROJECT.ID PROJECT_ID, EC_PROJECT.NAME PROJECT_NAME, EC_PROJECT.OWNER_CLIENT_ID CLIENT_ID, CLIENT.NAME CLIENT_NAME, EC_PROJECT.CREATE_TS CREATE_TIMESTAMP, "
                                 + "EC_PROJECT_TYPE.NAME PROJECT_TYPE_NAME, EC_PROJECT_TYPE.SWMP PROJECT_TYPE_SWMP, EC_PROJECT_TYPE.EXTENDED_ON_LINE_ACCESS_MONTHS EXTENDED_ON_LINE_ACCESS_MONTHS, "
                                 + "EC_END_POINT_TYPE.NAME END_POINT_TYPE_NAME, CASE WHEN EC_PROJECT.CREATE_TS BETWEEN '"
                                 + DateUtil.mdyDate(reportForm.getStartDate())
                                 + "' AND '"
                                 + DateUtil.mdyDate(reportForm.getEndDate())
                                 + "' "
                                 + " THEN 1 ELSE 0 END AS PROJECT_GROUP_NUMBER, CASE WHEN EC_PROJECT.CREATE_TS BETWEEN '"
                                 + DateUtil.mdyDate(reportForm.getStartDate())
                                 + "' AND '"
                                 + DateUtil.mdyDate(reportForm.getEndDate())
                                 + "' "
                                 + " THEN 'Projects Initiated Between "
                                 + DateUtil.mdyDate(reportForm.getStartDate())
                                 + " and "
                                 + DateUtil.mdyDate(reportForm.getEndDate())
                                 + "' ELSE 'All Other Projects' END AS PROJECT_GROUP_TEXT, "
                                 +
                                 " LOOKUP_STATUS_CODE.DESCRIPTION AS PROJECT_STATUS "
                                 + "FROM EC_PROJECT LEFT OUTER JOIN CLIENT ON EC_PROJECT.OWNER_CLIENT_ID = CLIENT.ID "
                                 + "	LEFT OUTER JOIN EC_PROJECT_TYPE ON EC_PROJECT.PROJECT_TYPE_ID = EC_PROJECT_TYPE.ID "
                                 + "    LEFT OUTER JOIN EC_END_POINT_TYPE ON EC_PROJECT_TYPE.END_POINT_TYPE_ID = EC_END_POINT_TYPE.ID "
                                 + "    LEFT OUTER JOIN LOOKUP_STATUS_CODE ON EC_PROJECT.PROJECT_STATUS_CD = LOOKUP_STATUS_CODE.CODE ");
            dateSubQuery =
                " (EC_PROJECT.CREATE_TS >= \'"
                + DateUtil.mdyDate(reportForm.getStartDate())
                + "\'"
                + " AND EC_PROJECT.CREATE_TS <= \'"
                + DateUtil.mdyDate(reportForm.getEndDate())
                + "\')";
            reportTimeFrame =
                DateUtil.mmdyDate(reportForm.getStartDate())
                + " To "
                + DateUtil.mmdyDate(reportForm.getEndDate());
            reportQuery.append(" WHERE ");
            //Set the client Id
            if (reportForm.getRunReport()
                .equals(RequestKeys.EC_ADMIN_INVOICE_SUMMARY_REPORT))
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
                    return mapping.findForward("page.permission.denied");
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
                }
            }
            //This report is being run from the client menu
            //Use the clientId in the report
            if (clientId
                > 0)
            {
                reportQuery.append(" EC_PROJECT.OWNER_CLIENT_ID = "
                                   + clientId);
                dateSubQuery =
                    " AND "
                    + dateSubQuery;
            }
            //            }
            //reportQuery.append(" EC_PROJECT.PROJECT_STATUS_CD IN (1, 2, 4, 6) ");
            reportQuery.append(dateSubQuery);
            reportQuery.append(" ORDER BY CLIENT.NAME ASC, PROJECT_GROUP_NUMBER DESC, EC_PROJECT.CREATE_TS DESC, EC_PROJECT.NAME ASC");
            LOG.info(reportQuery.toString());
            try
            {
                //Create the Invoice Summary Report
                InvoiceSummaryReport
                    invoiceSummaryReport =
                    new InvoiceSummaryReport();
                //Set additional report parameters
                invoiceSummaryReport.setReportParameter(ECReportParameter.REPORT_QUERY,
                                                        reportQuery.toString());
                invoiceSummaryReport.setReportParameter(ECReportParameter.TIME_FRAME,
                                                        reportTimeFrame);
                invoiceSummaryReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                        clientId);
                if (reportForm.getRunReport()
                    .equals(RequestKeys.EC_ADMIN_INVOICE_SUMMARY_REPORT))
                {
                    invoiceSummaryReport.setReportParameter(ECReportParameter.IS_SYSTEM_ADMIN_REPORT,
                                                            new Boolean(true));
                }
                else
                {
                    invoiceSummaryReport.setReportParameter(ECReportParameter.IS_SYSTEM_ADMIN_REPORT,
                                                            false);
                }
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
                        new ExcelApiReportRunner(invoiceSummaryReport);
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
                        new PDFReportRunner(invoiceSummaryReport);
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
                reportForm.setTitle((String) invoiceSummaryReport.getReportParameter(ECReportParameter.REPORT_TITLE));
                setSessionAttribute("reportFormESC",
                                    reportForm,
                                    request);
            }
            catch (IndexOutOfBoundsException a)
            {
                addError(new ActionMessage("error.report.execution.failed",
                                           ": Report is empty"),
                         request);
                return mapping.findForward("fail");
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
        return mapping.findForward("fail");
    }
}