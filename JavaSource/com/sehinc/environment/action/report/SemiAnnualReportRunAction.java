package com.sehinc.environment.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.db.user.AddressData;
import com.sehinc.common.report.ExcelApiReportRunner;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.service.client.ClientService;
import com.sehinc.common.service.contact.ContactService;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.environment.action.base.RequestKeys;
import com.sehinc.environment.db.permit.EnvPermit;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

public class SemiAnnualReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(SemiAnnualReportRunAction.class);

    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        LOG.info("In SemiAnnualReportRunAction");
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
            SemiAnnualReport
                semiAnnual =
                new SemiAnnualReport();
            CapContact
                contact =
                ContactService.getMainContactByClientId(clientValue.getId());
            AddressData
                address =
                ClientService.getClientAddress(clientValue.getId());
            Integer
                year =
                reportForm.getSemiAnnualYear();
            String
                period =
                reportForm.getPeriod();
            semiAnnual.setReportParameter(EVReportParameter.YEAR,
                                          year);
            semiAnnual.setReportParameter(EVReportParameter.PERIOD,
                                          period);
            semiAnnual.setReportParameter(EVReportParameter.CLIENT_NAME,
                                          clientValue.getName());
            semiAnnual.setReportParameter(EVReportParameter.CLIENT_ADDRESS,
                                          address.getLine1()
                                          + address.getLine2()
                                          + address.getLine3()
                                          + ","
                                          + address.getCity()
                                          + ","
                                          + address.getState()
                                          + ","
                                          + address.getPostalCode());
            semiAnnual.setReportParameter(EVReportParameter.CONTACT_NAME,
                                          contact.getFullName());
            semiAnnual.setReportParameter(EVReportParameter.CONTACT_PHONE,
                                          contact.getPrimaryPhone());
            semiAnnual.setReportParameter(EVReportParameter.CONTACT_TITLE,
                                          contact.getTitle());
            Integer
                permitId =
                reportForm.getPermitId();
            EnvPermit
                permit =
                new EnvPermit(permitId);
            permit.load();
            semiAnnual.setReportParameter(EVReportParameter.PERMIT_ID,
                                          permitId);
            semiAnnual.setReportParameter(EVReportParameter.PERMIT_NAME,
                                          permit.getName());
            ServletContext
                servletContext =
                request.getSession()
                    .getServletContext();
            semiAnnual.setReportParameter(EVReportParameter.SEMI_ANNUAL_HEADER,
                                          new File(servletContext.getRealPath("/reports/env/SemiAnnualReport_Header.jasper")));
            if (period.equalsIgnoreCase(RequestKeys.EV_SEMI_ANNUAL_REPORT_JAN_JUN))
            {
                String
                    nicelyFormattedPeriod =
                    "January through June";
                semiAnnual.setReportParameter(EVReportParameter.PERIOD,
                                              nicelyFormattedPeriod);
                semiAnnual.setReportParameter(EVReportParameter.SEMI_ANNUAL_DETAIL,
                                              new File(servletContext.getRealPath("/reports/env/SemiAnnualReport_Details1.jasper")));
            }
            else
            {
                String
                    nicelyFormattedPeriod =
                    "July through December";
                semiAnnual.setReportParameter(EVReportParameter.PERIOD,
                                              nicelyFormattedPeriod);
                semiAnnual.setReportParameter(EVReportParameter.SEMI_ANNUAL_DETAIL,
                                              new File(servletContext.getRealPath("/reports/env/SemiAnnualReport_Details2.jasper")));
            }
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
                    new ExcelApiReportRunner(semiAnnual);
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
                    new PDFReportRunner(semiAnnual);
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
            reportForm.setTitle((String) semiAnnual.getReportParameter(EVReportParameter.REPORT_TITLE));
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