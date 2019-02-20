/*
package com.sehinc.erosioncontrol.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.SessionKeys;
import com.sehinc.erosioncontrol.value.inspection.InspectionValue;
import com.sehinc.erosioncontrol.value.project.ProjectValue;
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

public class ECInspectionReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ECInspectionReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException
    {
        LOG.info("In ECInspectionReportRunAction");
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
*/
/*
        //
        // Get the security manager
        //
        SecurityManager
            securityManager =
            (SecurityManager) getSessionAttribute(SessionKeys.SECURITY_MANAGER,
                                                  request);
*//*

        //
        //get the Project value from the session
        //
        ProjectValue
            projectValue =
            (ProjectValue) getSessionAttribute(SessionKeys.EC_PROJECT,
                                               request);
        if (projectValue
            == null)
        {
            LOG.error("No Project Value in session");
            return mapping.findForward("fail");
        }
        //
        //get the Inspection value from the session
        //
        InspectionValue
            inspectionValue =
            (InspectionValue) getSessionAttribute(SessionKeys.EC_INSPECTION,
                                                  request);
        if (inspectionValue
            == null)
        {
            LOG.error("No Inspection Value in session");
            return mapping.findForward("fail");
        }
        try
        {
            //Create the EC Inspection Report
            ECInspectionReport
                ecInspectionReport =
                new ECInspectionReport();
            //Set additional report parameters
            ecInspectionReport.setReportParameter(ECReportParameter.CLIENT_ID,
                                                  clientValue.getId());
            ecInspectionReport.setReportParameter(ECReportParameter.PROJECT_ID,
                                                  projectValue.getId());
            ecInspectionReport.setReportParameter(ECReportParameter.INSPECTION_ID,
                                                  inspectionValue.getId());
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
            // Transfer files to the output file path for use in the document
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
                    new ExcelApiReportRunner(ecInspectionReport);
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
                    new PDFReportRunner(ecInspectionReport);
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
            reportForm.setTitle((String) ecInspectionReport.getReportParameter(ECReportParameter.REPORT_TITLE));
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage(),
                      e);
            addError(new ActionMessage("error.report.execution.failed",
                                       e.getMessage()));
            return mapping.findForward("fail");
        }
        return mapping.findForward("continue");
    }
}*/
