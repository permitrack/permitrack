package com.sehinc.stormwater.action.report;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.stormwater.db.plan.BMPData;
import com.sehinc.stormwater.server.permitperiod.PermitPeriodService;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.permitperiod.PermitPeriodValue;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

public class ReportRunAction
    extends ReportBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(ReportRunAction.class);
    private
    String
        reportQuery =
        "SELECT [PLAN].ID PLAN_ID, MCM.ID MCM_ID, MCM.NAME MCM_NAME, MCM.NUMBER MCM_NUMBER, "
        +
        "MCM.REQUIRED_DESCRIPTION MCM_REQUIRED_DESCRIPTION, "
        +
        "MCM.NECESSARY_DESCRIPTION MCM_NECESSARY_DESCRIPTION, MCM.OWNER_ID MCM_OWNER_ID, "
        +
        "BMP.ID BMP_ID, BMP.NAME BMP_NAME, BMP.SECTION BMP_SECTION, BMP.NUMBER BMP_NUMBER, BMP.FIELD_VALUE1 BMP_FIELD_VALUE1, BMP.FIELD_TYPE1 BMP_FIELD_TYPE1,"
        +
        "BMP.FIELD_VALUE2 BMP_FIELD_VALUE2, BMP.FIELD_TYPE2 BMP_FIELD_TYPE2, BMP.FIELD_VALUE3 BMP_FIELD_VALUE3, BMP.FIELD_TYPE3 BMP_FIELD_TYPE3,"
        +
        "BMP.FIELD_VALUE4 BMP_FIELD_VALUE4, BMP.FIELD_TYPE4 BMP_FIELD_TYPE4, BMP.FIELD_VALUE5 BMP_FIELD_VALUE5, BMP.FIELD_TYPE5 BMP_FIELD_TYPE5, "
        +
        "BMP.IS_REQUIRED BMP_REQUIRED, BMP.OWNER_ID BMP_OWNER_ID, GOAL.ID GOAL_ID, GOAL.NAME GOAL_NAME, GOAL.NUMBER GOAL_NUMBER, GOAL.DESCRIPTION GOAL_DESCRIPTION, "
        +
        "GOAL.OWNER_ID GOAL_OWNER_ID, "
        +
        "GOAL_ACTIVITY.ID GOAL_ACTIVITY_ID, GOAL_ACTIVITY.NAME GOAL_ACTIVITY_NAME, GOAL_ACTIVITY.DESCRIPTION GOAL_ACTIVITY_DESCRIPTION, "
        +
        "GOAL_ACTIVITY.OWNER_ID GOAL_ACTIVITY_OWNER_ID, GOAL_ACTIVITY.ACTIVITY_DATE GOAL_ACTIVITY_DATE "
        +
        "FROM [PLAN] LEFT OUTER JOIN MCM ON [PLAN].ID = MCM.PLAN_ID AND MCM.STATUS_CD = 1 "
        +
        "LEFT OUTER JOIN BMP ON MCM.ID = BMP.MCM_ID AND BMP.STATUS_CD = 1 "
        +
        "LEFT OUTER JOIN GOAL ON BMP.ID = GOAL.BMP_ID AND GOAL.STATUS_CD = 1 "
        +
        "LEFT OUTER JOIN GOAL_ACTIVITY ON GOAL.ID = GOAL_ACTIVITY.GOAL_ID AND GOAL_ACTIVITY.STATUS_CD = 1";

    @SuppressWarnings("ConstantConditions")
    public ActionForward reportAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
//        LOG.info("In ReportRunAction");
        ReportForm
            reportForm =
            (ReportForm) form;
        PlanValue
            planValue = getPlanValue(request);
        if (planValue
            == null)
        {
            return mapping.findForward("stormwater");
        }
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
        if (clientValue
            == null)
        {
            return mapping.findForward("fail");
        }
        Boolean
            showActivity;
        String
            reportTimeFrame =
            null;
        Date
            startDate =
            null;
        Date
            endDate =
            null;
        String
            dateSubQuery =
            "";
        String
            goalStatusSubQuery =
            "";
        PermitPeriodValue
            permitPeriodValue =
            PermitPeriodService.getPermitPeriodValue(planValue.getPermitPeriodId());
        if (permitPeriodValue
            == null)
        {
            LOG.info("permitPeriodValue is null!");
        }
        if (reportForm.getDateType()
            .equals("timeperiod"))
        {
            LOG.info("In timeperiod");
            PermitTimePeriodValue
                permitTimePeriodValue =
                PlanService.getSelectedPermitTimePeriodRadioBox(request);
            if (permitTimePeriodValue
                != null)
            {
                LOG.info("In permitTimePeriodValue");
                startDate =
                    permitTimePeriodValue.getStartDate();
                endDate =
                    permitTimePeriodValue.getEndDate();
                dateSubQuery =
                    " AND (GOAL_ACTIVITY.ACTIVITY_DATE >= \'"
                    + DateUtil.mdyDate(startDate)
                    + "\'"
                    +
                    " AND GOAL_ACTIVITY.ACTIVITY_DATE <= \'"
                    + DateUtil.mdyDate(endDate)
                    + "\')";
                reportTimeFrame =
                    permitTimePeriodValue.getName()
                    + " ("
                    + DateUtil.mmdyDate(startDate)
                    + " To "
                    + DateUtil.mmdyDate(endDate)
                    + ")";
                String
                    statusReportQuery =
                    "SELECT [PLAN].ID PLAN_ID, MCM.ID MCM_ID, MCM.NAME MCM_NAME, MCM.NUMBER MCM_NUMBER, "
                    +
                    "MCM.REQUIRED_DESCRIPTION MCM_REQUIRED_DESCRIPTION, "
                    +
                    "MCM.NECESSARY_DESCRIPTION MCM_NECESSARY_DESCRIPTION, MCM.OWNER_ID MCM_OWNER_ID, "
                    +
                    "BMP.ID BMP_ID, BMP.NAME BMP_NAME, BMP.SECTION BMP_SECTION, BMP.NUMBER BMP_NUMBER, BMP.FIELD_VALUE1 BMP_FIELD_VALUE1, BMP.FIELD_TYPE1 BMP_FIELD_TYPE1,"
                    +
                    "BMP.FIELD_VALUE2 BMP_FIELD_VALUE2, BMP.FIELD_TYPE2 BMP_FIELD_TYPE2, BMP.FIELD_VALUE3 BMP_FIELD_VALUE3, BMP.FIELD_TYPE3 BMP_FIELD_TYPE3,"
                    +
                    "BMP.FIELD_VALUE4 BMP_FIELD_VALUE4, BMP.FIELD_TYPE4 BMP_FIELD_TYPE4, BMP.FIELD_VALUE5 BMP_FIELD_VALUE5, BMP.FIELD_TYPE5 BMP_FIELD_TYPE5, "
                    +
                    "BMP.IS_REQUIRED BMP_REQUIRED, BMP.OWNER_ID BMP_OWNER_ID, GOAL.ID GOAL_ID, GOAL.NAME GOAL_NAME, GOAL.NUMBER GOAL_NUMBER, GOAL.DESCRIPTION GOAL_DESCRIPTION, "
                    +
                    "GOAL.OWNER_ID GOAL_OWNER_ID, "
                    +
                    "GOAL_ACTIVITY.ID GOAL_ACTIVITY_ID, GOAL_ACTIVITY.NAME GOAL_ACTIVITY_NAME, GOAL_ACTIVITY.DESCRIPTION GOAL_ACTIVITY_DESCRIPTION, "
                    +
                    "GOAL_ACTIVITY.OWNER_ID GOAL_ACTIVITY_OWNER_ID, GOAL_ACTIVITY.ACTIVITY_DATE GOAL_ACTIVITY_DATE "
                    +
                    "FROM [PLAN] LEFT OUTER JOIN MCM ON [PLAN].ID = MCM.PLAN_ID AND MCM.STATUS_CD = 1 "
                    +
                    "LEFT OUTER JOIN BMP ON MCM.ID = BMP.MCM_ID AND BMP.STATUS_CD = 1 "
                    +
                    "LEFT OUTER JOIN GOAL ON BMP.ID = GOAL.BMP_ID AND GOAL.STATUS_CD = 1 "
                    +
                    "LEFT OUTER JOIN GOAL_PERMIT_TIME_PERIOD ON GOAL.ID = GOAL_PERMIT_TIME_PERIOD.GOAL_ID "
                    +
                    "LEFT OUTER JOIN GOAL_ACTIVITY ON GOAL.ID = GOAL_ACTIVITY.GOAL_ID AND GOAL_ACTIVITY.STATUS_CD = 1";
                if (reportForm.getGoalStatus()
                    .equals("complete.status"))
                {
                    LOG.info("In complete.status");
                    goalStatusSubQuery =
                        " AND (GOAL_PERMIT_TIME_PERIOD.PERMIT_TIME_PERIOD_ID = "
                        + permitTimePeriodValue.getId()
                        + " AND GOAL_PERMIT_TIME_PERIOD.IS_COMPLETE = 1)";
                    reportQuery =
                        statusReportQuery;
                }
                else
                {
                    if (reportForm.getGoalStatus()
                        .equals("incomplete.status"))
                    {
                        LOG.info("In incomplete.status");
                        goalStatusSubQuery =
                            " AND (GOAL_PERMIT_TIME_PERIOD.PERMIT_TIME_PERIOD_ID = "
                            + permitTimePeriodValue.getId()
                            + " AND GOAL_PERMIT_TIME_PERIOD.IS_COMPLETE = 0)";
                        reportQuery =
                            statusReportQuery;
                    }
                    else
                    {
                        if (reportForm.getGoalStatus()
                            .equals("all.status"))
                        {
                            LOG.info("In all.status");
                            goalStatusSubQuery =
                                " AND (GOAL_PERMIT_TIME_PERIOD.PERMIT_TIME_PERIOD_ID = "
                                + permitTimePeriodValue.getId()
                                + ")";
                            reportQuery =
                                statusReportQuery;
                        }
                    }
                }
            }
            if (startDate
                == null
                || endDate
                   == null)
            {
                addError(new ActionMessage("error.missing.date"), request);
                return new ActionForward("/reportoptionsaction.do?"
                                         + "id"
                                         + "="
                                         + planValue.getId(),
                                         true);
            }
        }
        if (reportForm.getDateType()
            .equals("custom.dates"))
        {
            startDate =
                reportForm.getStartDate();
            endDate =
                reportForm.getEndDate();
            if (startDate
                == null
                || endDate
                   == null)
            {
                addError(new ActionMessage("error.missing.date"), request);
                return new ActionForward("/reportoptionsaction.do?"
                                         + "id"
                                         + "="
                                         + planValue.getId(),
                                         true);
            }
            dateSubQuery =
                " AND (GOAL_ACTIVITY.ACTIVITY_DATE >= \'"
                + DateUtil.mdyDate(startDate)
                + "\'"
                +
                " AND GOAL_ACTIVITY.ACTIVITY_DATE <= \'"
                + DateUtil.mdyDate(endDate)
                + "\')";
            reportTimeFrame =
                DateUtil.mmdyDate(startDate)
                + " To "
                + DateUtil.mmdyDate(endDate);
        }
        if (reportForm.getDateType()
            .equals("no.activity"))
        {
            showActivity =
                Boolean.FALSE;
            reportTimeFrame =
                null;
        }
        else
        {
            showActivity =
                Boolean.TRUE;
        }
        if (reportForm.getDateType()
            .equals("all.dates"))
        {
            LOG.info("In all.dates");
            LOG.info("permit time periods = "
                     + permitPeriodValue.getPermitTimePeriods()
                .size());
            startDate =
                ((PermitTimePeriodValue) permitPeriodValue.getPermitTimePeriods()
                    .get(0)).getStartDate();
            LOG.info("Got Start Date");
            endDate =
                ((PermitTimePeriodValue) permitPeriodValue.getPermitTimePeriods()
                    .get(permitPeriodValue.getPermitTimePeriods()
                             .size()
                         - 1)).getEndDate();
            LOG.info("Got End Date");
            dateSubQuery =
                " AND (GOAL_ACTIVITY.ACTIVITY_DATE >= \'"
                + DateUtil.mdyDate(startDate)
                + "\'"
                +
                " AND GOAL_ACTIVITY.ACTIVITY_DATE <= \'"
                + DateUtil.mdyDate(endDate)
                + "\')";
            LOG.info("got dateSubQuery");
            LOG.info(dateSubQuery);
            reportTimeFrame =
                DateUtil.mmdyDate(startDate)
                + " To "
                + DateUtil.mmdyDate(endDate);
            LOG.info("Done in all.dates");
        }
        Integer
            userId;
        String
            userSubQuery =
            "";
        if (reportForm.getUserType()
            .equals("one.user"))
        {
            userId =
                reportForm.getUserId();
            if (userId
                == null)
            {
                addError(new ActionMessage("error.no.user.selected"), request);
                return new ActionForward("/reportoptionsaction.do?"
                                         + "id"
                                         + "="
                                         + planValue.getId(),
                                         true);
            }
            userSubQuery =
                " AND (MCM.OWNER_ID = "
                + userId
                +
                " OR BMP.OWNER_ID = "
                + userId
                +
                " OR GOAL.OWNER_ID = "
                + userId
                +
                " OR GOAL_ACTIVITY.OWNER_ID = "
                + userId
                + ")";
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
            + String.valueOf(userValue.getId()
                             + ".pdf");
        String
            outputFilePath =
            ApplicationProperties.getProperty("base.document.directory")
            + "/client"
            + clientValue.getId()
            + "/temp/";
        File
            outputFile =
            new File(outputFilePath
                     + outputFileName);
        Integer
            mcmId;
        Integer
            bmpId;
        StringBuffer
            query =
            new StringBuffer(reportQuery);
        LOG.info("reportType = "
                 + reportForm.getReportType());
        if (reportForm.getReportType()
            .equals("mpca.permit"))
        {
            MPCAPermitReport
                mpcapermitreport =
                new MPCAPermitReport();
            try
            {
                mpcapermitreport.setReportParameter(SWReportParameter.CLIENT_ID,
                                                    clientValue.getId());
                mpcapermitreport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                                    query.toString());
                mpcapermitreport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                                    showActivity);
                mpcapermitreport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                                    reportTimeFrame);
                mpcapermitreport.setReportParameter(SWReportParameter.PLAN_ID,
                                                    planValue.getId());
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(mpcapermitreport);
                pdfReportRunner.runToFile(request,
                                          outputFile);
                reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                           clientValue.getId(),
                                                           outputFile,
                                                           request));
                reportForm.setTitle(mpcapermitreport.getReportTitle());
                reportForm.setPlanName(planValue.getName());
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage(),
                          e);
                addError(new ActionMessage("error.report.execution.failed",
                                           e.getMessage()), request);
                return mapping.findForward("fail");
            }
        }
        else if (reportForm.getReportType()
            .equals("all.mcm"))
        {
            query.append(dateSubQuery);
            query.append(" WHERE [PLAN].ID = "
                         + planValue.getId());
            query.append(userSubQuery);
            query.append(goalStatusSubQuery);
            query.append(" ORDER BY ISNULL(MCM.NUMBER, 0), ISNULL(BMP.SECTION, \'\'), ISNULL(BMP.NUMBER, 0), ISNULL(GOAL.NUMBER, 0), GOAL_ACTIVITY.ACTIVITY_DATE");
            LOG.info(query.toString());
            try
            {
                PlanReport
                    planReport =
                    new PlanReport();
                planReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                              clientValue.getId());
                planReport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                              query.toString());
                planReport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                              showActivity);
                planReport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                              reportTimeFrame);
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(planReport);
                pdfReportRunner.runToFile(request,
                                          outputFile);
                reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                           clientValue.getId(),
                                                           outputFile,
                                                           request));
                reportForm.setTitle(planReport.getReportTitle());
                reportForm.setPlanName(planValue.getName());
                LOG.info("PlanReport.execute() complete");
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage(),
                          e);
                addError(new ActionMessage("error.report.execution.failed",
                                           e.getMessage()), request);
                return mapping.findForward("fail");
            }
        }
        else
        {
            if (reportForm.getReportType()
                .equals("one.mcm"))
            {
                mcmId =
                    reportForm.getMCMId();
                if (mcmId
                    == null)
                {
                    addError(new ActionMessage("error.no.mcm.selected"), request);
                    return mapping.findForward("fail");
                }
                query.append(dateSubQuery);
                query.append(" WHERE MCM.ID = "
                             + mcmId);
                query.append(userSubQuery);
                query.append(goalStatusSubQuery);
                query.append(" ORDER BY ISNULL(MCM.NUMBER, 0), ISNULL(BMP.SECTION, \'\'), ISNULL(BMP.NUMBER, 0), ISNULL(GOAL.NUMBER, 0), GOAL_ACTIVITY.ACTIVITY_DATE");
                LOG.info(query.toString());
                try
                {
                    MCMReport
                        mcmReport =
                        new MCMReport();
                    mcmReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                                 clientValue.getId());
                    mcmReport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                                 query.toString());
                    mcmReport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                                 showActivity);
                    mcmReport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                                 reportTimeFrame);
                    PDFReportRunner
                        pdfReportRunner =
                        new PDFReportRunner(mcmReport);
                    pdfReportRunner.runToFile(request,
                                              outputFile);
                    reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                               clientValue.getId(),
                                                               outputFile,
                                                               request));
                    reportForm.setTitle(mcmReport.getReportTitle());
                    reportForm.setPlanName(planValue.getName());
                    LOG.info("MCMReport.execute() complete");
                }
                catch (Exception e)
                {
                    LOG.error(e.getMessage(),
                              e);
                    addError(new ActionMessage("error.report.execution.failed",
                                               e.getMessage()), request);
                    return mapping.findForward("fail");
                }
            }
            else
            {
                if (reportForm.getReportType()
                    .equals("one.bmp"))
                {
                    bmpId =
                        reportForm.getBMPId();
                    if (bmpId
                        == null)
                    {
                        addError(new ActionMessage("error.no.bmp.selected"), request);
                        return mapping.findForward("fail");
                    }
                    BMPData
                        bmpData =
                        PlanService.getBMP(bmpId);
                    if (bmpData
                        == null)
                    {
                        addError(new ActionMessage("error.bmp.not.exist"), request);
                        return mapping.findForward("fail");
                    }
                    query.append(dateSubQuery);
                    query.append(" WHERE MCM.ID = "
                                 + bmpData.getMcmId()
                                 + " AND BMP.ID = "
                                 + bmpData.getId());
                    query.append(userSubQuery);
                    query.append(goalStatusSubQuery);
                    query.append(" ORDER BY ISNULL(MCM.NUMBER, 0), ISNULL(BMP.SECTION, \'\'), ISNULL(BMP.NUMBER, 0), ISNULL(GOAL.NUMBER, 0), GOAL_ACTIVITY.ACTIVITY_DATE");
                    LOG.info(query);
                    try
                    {
                        BMPReport
                            bmpReport =
                            new BMPReport();
                        bmpReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                                     clientValue.getId());
                        bmpReport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                                     query.toString());
                        bmpReport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                                     showActivity);
                        bmpReport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                                     reportTimeFrame);
                        PDFReportRunner
                            pdfReportRunner =
                            new PDFReportRunner(bmpReport);
                        pdfReportRunner.runToFile(request,
                                                  outputFile);
                        reportForm.setReportURL(getFileDownloadURL(userValue.getUsername(),
                                                                   clientValue.getId(),
                                                                   outputFile,
                                                                   request));
                        reportForm.setTitle(bmpReport.getReportTitle());
                        reportForm.setPlanName(planValue.getName());
                        LOG.info("BMPReport.execute() complete");
                    }
                    catch (Exception e)
                    {
                        LOG.error(e.getMessage(),
                                  e);
                        addError(new ActionMessage("error.report.execution.failed",
                                                   e.getMessage()), request);
                        return mapping.findForward("fail");
                    }
                }
            }
        }
        setSessionAttribute("reportFormMS4",
                            reportForm,
                            request);
        return new ActionForward("/reportoptionsaction.do?"
                                 + "id"
                                 + "="
                                 + planValue.getId(),
                                 true);
    }

    private String getFileDownloadURL(String username, Integer clientId, File file, HttpServletRequest request)
    {
        FileDownloadWrapper
            fileDownloadWrapper =
            new FileDownloadWrapper();
        fileDownloadWrapper.setClientId(clientId);
        fileDownloadWrapper.setUsername(username);
        fileDownloadWrapper.setFile(file);
        return fileDownloadWrapper.getDownloadURL(request);
    }
}
