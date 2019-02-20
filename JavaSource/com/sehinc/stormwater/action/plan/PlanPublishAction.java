package com.sehinc.stormwater.action.plan;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.db.hibernate.HibernateUtil;
import com.sehinc.common.report.PDFReportRunner;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.FileUtil;
import com.sehinc.common.util.html.FileDownloadWrapper;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.stormwater.action.base.SessionKeys;
import com.sehinc.stormwater.action.report.PlanExportReport;
import com.sehinc.stormwater.action.report.PlanPublishReport;
import com.sehinc.stormwater.action.report.SWReportParameter;
import com.sehinc.stormwater.db.plan.GoalActivityFileLocationData;
import com.sehinc.stormwater.db.plan.PlanPublishData;
import com.sehinc.stormwater.server.plan.PlanService;
import com.sehinc.stormwater.value.permitperiod.PermitTimePeriodValue;
import com.sehinc.stormwater.value.plan.PlanValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PlanPublishAction
    extends PlanBaseAction
{
    private static
    Logger
        LOG =
        Logger.getLogger(PlanPublishAction.class);

    @SuppressWarnings("ConstantConditions")
    public ActionForward planAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws ServletException
    {
        DynaActionForm
            aForm =
            (DynaActionForm) form;
        PlanValue
            planValue =
            getPlanValue(request);
        if (planValue
            == null)
        {
            addError(new ActionMessage("error.plan.not.found.in.session"), request);
            return mapping.findForward("plan.list.action");
        }
        LOG.info("after get plan value");
        UserValue
            userValue =
            getUserValue(request);
        ClientValue
            clientValue =
            getClientValue(request);
        DateFormat
            aDateFormat =
            DateUtil.MDY_FORMAT;
        String
            aDateOption =
            (String) aForm.get("dateOption");
        String
            aStartDate =
            null;
        String
            aEndDate =
            null;
        if (aDateOption.equals("permitTimePeriod"))
        {
            PermitTimePeriodValue
                permitTimePeriodValue =
                PlanService.getSelectedPermitTimePeriodRadioBox(request);
            aStartDate =
                aDateFormat.format(permitTimePeriodValue.getStartDate());
            aEndDate =
                aDateFormat.format(permitTimePeriodValue.getEndDate());
        }
        else if (aDateOption.equals("custom"))
        {
            aStartDate =
                (String) aForm.get("startDate");
            aEndDate =
                (String) aForm.get("endDate");
        }
//        LOG.info("In PlanPublishAction");
        aForm.set("planName",
                  planValue.getName());
        setSecondaryMenu(request);
        request.getSession()
            .removeAttribute(SessionKeys.STORM_PLAN_EXPORT_FILE);
        request.getSession()
            .removeAttribute(SessionKeys.STORM_PLAN_PUBLISH_URL);
        String
            isExport =
            (String) aForm.get("isExport");
        if (isExport.equalsIgnoreCase("true"))
        {
            try
            {
                File
                    exportFile =
                    exportPlan(planValue,
                               clientValue,
                               aStartDate,
                               aEndDate,
                               request);
                FileDownloadWrapper
                    fileDownload =
                    new FileDownloadWrapper();
                fileDownload.setClientId(clientValue.getId());
                fileDownload.setUsername(userValue.getUsername());
                fileDownload.setFile(exportFile);
                setSessionAttribute(SessionKeys.STORM_PLAN_EXPORT_FILE,
                                    fileDownload.getDownloadURL(request), request);
            }
            catch (FileNotFoundException fnfe)
            {
                LOG.error(fnfe.getMessage());
                addError(new ActionMessage("plan.export.failed",
                                           fnfe.getMessage()), request);
                return new ActionForward("/planpublishaction.do?"
                                         + "id"
                                         + "="
                                         + getPlanValue(request).getId(),
                                         true);
            }
            catch (IOException ioe)
            {
                LOG.error(ioe.getMessage());
                addError(new ActionMessage("plan.export.failed",
                                           ioe.getMessage()), request);
                return new ActionForward("/planpublishaction.do?"
                                         + "id"
                                         + "="
                                         + getPlanValue(request).getId(),
                                         true);
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                addError(new ActionMessage("plan.export.failed",
                                           e.getMessage()), request);
                return new ActionForward("/planpublishaction.do?"
                                         + "id"
                                         + "="
                                         + getPlanValue(request).getId(),
                                         true);
            }
        }
        else
        {
            String
                aQueryString =
                buildQueryString(aStartDate,
                                 aEndDate, request);
            String
                aPublishURI =
                "/"
                + ApplicationProperties.getProperty("base.public.web.path")
                + "/"
                + "client"
                + clientValue.getId()
                + "/"
                + ApplicationProperties.getProperty("application.stormwater")
                + "/plan"
                + planValue.getId()
                + "/";
            File
                outputDir =
                new File(ApplicationProperties.getProperty("base.public.directory")
                         + "/"
                         + "client"
                         + clientValue.getId()
                         + "/"
                         + ApplicationProperties.getProperty("application.stormwater")
                         + "/plan"
                         + planValue.getId());
            File
                outputFile =
                new File(outputDir,
                         "PlanReport.pdf");
            try
            {
                PlanPublishReport
                    planPublishReport =
                    new PlanPublishReport();
                planPublishReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                                     clientValue.getId());
                planPublishReport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                                     aQueryString);
                planPublishReport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                                     true);
                planPublishReport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                                     DateUtil.mmdyDate(DateUtil.parseDate(aStartDate))
                                                     + " To "
                                                     + DateUtil.mmdyDate(DateUtil.parseDate(aEndDate)));
                planPublishReport.setReportParameter(SWReportParameter.BASE_URL,
                                                     ApplicationProperties.getProperty("base.public.web.url"));
                planPublishReport.setReportParameter(SWReportParameter.PUBLISH_URI,
                                                     aPublishURI);
                PDFReportRunner
                    pdfReportRunner =
                    new PDFReportRunner(planPublishReport);
                pdfReportRunner.runToFile(request,
                                          outputFile);
                LOG.info("PlanPublishReport.execute() complete");
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
                addError(new ActionMessage("plan.publish.failed",
                                           e.getMessage()), request);
                return new ActionForward("/planpublishaction.do?"
                                         + "id"
                                         + "="
                                         + getPlanValue(request).getId(),
                                         true);
            }
            if (planValue.getName()
                != null)
            {
                if (planValue.getName()
                        .length()
                    > 0)
                {
                    setSessionAttribute("ReportTitle",
                                        planValue.getName(), request);
                }
                else
                {
                    setSessionAttribute("ReportTitle",
                                        "NPDES Phase II Storm Water\nPollution Prevention Plan", request);
                }
            }
            setSessionAttribute(SessionKeys.STORM_PLAN_PUBLISH_URL,
                                ApplicationProperties.getProperty("base.public.web.url")
                                + aPublishURI
                                + "PlanReport.pdf", request);
            try
            {
                LOG.info("Get File directory for input files.");
                File
                    aFileStorage =
                    new File(ApplicationProperties.getProperty("base.document.directory")
                             + "/client"
                             + clientValue.getId()
                             + "/"
                             + ApplicationProperties.getProperty("application.stormwater")
                             + "/plan"
                             + planValue.getId());
                transferFiles(aFileStorage,
                              outputDir);
            }
            catch (Exception e)
            {
                LOG.error("Could not copy files to publish directory ",
                          e);
            }
            try
            {
                List
                    publishList =
                    PlanPublishData.findActiveByClientAndPlan(clientValue.getId(),
                                                              planValue.getId());
                for (Object o : publishList)
                {
                    PlanPublishData
                        publishData =
                        (PlanPublishData) o;
                    publishData.setStatusCode(StatusCodeData.STATUS_CODE_DELETED);
                    publishData.save(userValue);
                }
                PlanPublishData
                    newData =
                    new PlanPublishData();
                newData.setClientId(clientValue.getId());
                newData.setPlanId(planValue.getId());
                newData.setStartDate(DateUtil.parseDate(aStartDate));
                newData.setEndDate(DateUtil.parseDate(aEndDate));
                newData.setStatusCode(StatusCodeData.STATUS_CODE_ACTIVE);
                newData.save(userValue);
            }
            catch (Exception e)
            {
                LOG.error("Could not log publish audit info ",
                          e);
            }
        }
        return mapping.findForward("continue");
    }

    private void transferFiles(File pFileIn, File pFileOut)
        throws java.io.IOException
    {
        LOG.info("File In = "
                 + pFileIn.getName()
                 + " File Out = "
                 + pFileOut.getName());
        File[]
            aFileArray =
            pFileIn.listFiles();
        if (aFileArray
            != null)
        {
            for (
                int
                    i =
                    0; i
                       < aFileArray.length; i++)
            {
                File
                    aFileIn =
                    aFileArray[i];
                if (aFileIn.isDirectory())
                {
                    File
                        pNewFileOut =
                        new File(pFileOut.getAbsolutePath()
                                 + "/"
                                 + aFileIn.getName());
                    pNewFileOut.mkdirs();
                    LOG.info("Made Dir: "
                             + pNewFileOut.getAbsolutePath());
                    File[]
                        directoryFiles =
                        pNewFileOut.listFiles();
                    if (directoryFiles
                        != null)
                    {
                        for (
                            int
                                j =
                                0; j
                                   < directoryFiles.length; j++)
                        {
                            LOG.info("Deleting: "
                                     + directoryFiles[j].getAbsolutePath());
                            if (directoryFiles[j].isFile())
                            {
                                try
                                {
                                    directoryFiles[j].delete();
                                }
                                catch (Exception deleteFileException)
                                {
                                }
                            }
                        }
                    }
                    transferFiles(aFileIn,
                                  pNewFileOut);
                    File[]
                        transferFiles =
                        pNewFileOut.listFiles();
                    if (transferFiles
                        != null)
                    {
                        if (transferFiles.length
                            == 0)
                        {
                            LOG.info("Deleting Dir: "
                                     + pNewFileOut.getAbsolutePath());
                            try
                            {
                                pNewFileOut.delete();
                            }
                            catch (Exception deleteDirException)
                            {
                            }
                        }
                    }
                }
                else
                {
                    File
                        aFileOut =
                        new File(pFileOut.getAbsolutePath()
                                 + "/"
                                 + aFileIn.getName());
                    LOG.info("Out File: "
                             + aFileOut.getAbsolutePath());
                    LOG.info("Exists: "
                             + aFileOut.exists());
                    FileInputStream
                        aFileStorageInputStream =
                        new FileInputStream(aFileIn);
                    FileOutputStream
                        aFileOutputStream =
                        new FileOutputStream(aFileOut);
                    int
                        bytesRead;
                    while ((
                               bytesRead =
                                   aFileStorageInputStream.read())
                           != -1)
                    {
                        aFileOutputStream.write(bytesRead);
                    }
                    aFileOutputStream.close();
                    aFileStorageInputStream.close();
                    LOG.info("Copied: "
                             + aFileIn.getAbsolutePath()
                             + " to "
                             + aFileOut.getAbsolutePath());
                }
            }
        }
    }

    private String buildQueryString(String pStartDate, String pEndDate, HttpServletRequest request)
    {
        LOG.info("Building the query string");
        PlanValue
            planValue =
            getPlanValue(request);
        String
            dateSubQuery;
        try
        {
            DateFormat
                aDateFormat =
                DateFormat.getDateInstance(DateFormat.SHORT);
            Date
                startDate =
                aDateFormat.parse(pStartDate);
            Date
                endDate =
                aDateFormat.parse(pEndDate);
            if (startDate
                == null
                || endDate
                   == null)
            {
                throw new Exception();
            }
            dateSubQuery =
                " AND (GOAL_ACTIVITY.ACTIVITY_DATE >= \'"
                + DateUtil.mdyDate(startDate)
                + "\'"
                +
                " AND GOAL_ACTIVITY.ACTIVITY_DATE <= \'"
                + DateUtil.mdyDate(endDate)
                + "\')";
        }
        catch (Exception e)
        {
            java.util.Date
                aEndDate =
                new Date();
            Calendar
                aCalendar =
                Calendar.getInstance();
            aCalendar.set(Calendar.DAY_OF_MONTH,
                          1);
            aCalendar.set(Calendar.MONTH,
                          Calendar.JANUARY);
            java.util.Date
                aStartDate =
                aCalendar.getTime();
            dateSubQuery =
                " AND (GOAL_ACTIVITY.ACTIVITY_DATE >= \'"
                + DateUtil.mdyDate(aStartDate)
                + "\'"
                +
                " AND GOAL_ACTIVITY.ACTIVITY_DATE <= \'"
                + DateUtil.mdyDate(aEndDate)
                + "\')";
        }
        LOG.info("Made date query = "
                 + dateSubQuery);
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
            "GOAL_ACTIVITY.ID GOAL_ACTIVITY_ID, GOAL_ACTIVITY.DESCRIPTION GOAL_ACTIVITY_DESCRIPTION, GOAL_ACTIVITY.NAME GOAL_ACTIVITY_NAME, "
            +
            "GOAL_ACTIVITY.OWNER_ID GOAL_ACTIVITY_OWNER_ID, GOAL_ACTIVITY.ACTIVITY_DATE GOAL_ACTIVITY_DATE, "
            +
            "GOAL_ACTIVITY_FILE_LOCATION.ID GOAL_ACTIVITY_FILE_LOCATION_ID, "
            +
            "GOAL_ACTIVITY_FILE_LOCATION.NAME GOAL_ACTIVITY_FILE_LOCATION_NAME, "
            +
            "GOAL_ACTIVITY_FILE_LOCATION.LOCATION GOAL_ACTIVITY_FILE_LOCATION_LOCATION "
            +
            "FROM [PLAN] LEFT OUTER JOIN MCM ON [PLAN].ID = MCM.PLAN_ID AND MCM.STATUS_CD = 1 "
            +
            "LEFT OUTER JOIN BMP ON MCM.ID = BMP.MCM_ID AND BMP.STATUS_CD = 1 "
            +
            "LEFT OUTER JOIN GOAL ON BMP.ID = GOAL.BMP_ID AND GOAL.STATUS_CD = 1 "
            +
            "LEFT OUTER JOIN GOAL_ACTIVITY ON GOAL.ID = GOAL_ACTIVITY.GOAL_ID AND GOAL_ACTIVITY.STATUS_CD = 1 ";
        StringBuffer
            query =
            new StringBuffer(reportQuery);
        query.append(dateSubQuery);
        query.append(" LEFT OUTER JOIN GOAL_ACTIVITY_FILE_LOCATION ON GOAL_ACTIVITY.ID = GOAL_ACTIVITY_FILE_LOCATION.GOAL_ACTIVITY_ID ");
        query.append(" WHERE [PLAN].ID = "
                     + planValue.getId());
        query.append(" ORDER BY ISNULL(MCM.NUMBER, 0), ISNULL(BMP.SECTION, \'\'), ISNULL(BMP.NUMBER, 0), ISNULL(GOAL.NUMBER, 0), GOAL_ACTIVITY.ACTIVITY_DATE");
        LOG.info(query.toString());
        return query.toString();
    }

    public File exportPlan(PlanValue planValue, ClientValue clientValue, String startDate, String endDate, HttpServletRequest request)
        throws IOException
    {
        File
            outputDir =
            new File(ApplicationProperties.getProperty("base.document.directory")
                     + "/client"
                     + clientValue.getId()
                     + "/temp/export/plan"
                     + planValue.getId());
        if (outputDir.exists())
        {
            FileUtil.deleteDirectory(outputDir);
        }
        outputDir.mkdirs();
        String
            queryString =
            buildQueryString(startDate,
                             endDate, request);
        File
            reportFile =
            new File(outputDir,
                     "PlanReport.pdf");
        try
        {
            PlanExportReport
                planExportReport =
                new PlanExportReport();
            planExportReport.setReportParameter(SWReportParameter.CLIENT_ID,
                                                clientValue.getId());
            planExportReport.setReportParameter(SWReportParameter.REPORT_QUERY,
                                                queryString);
            planExportReport.setReportParameter(SWReportParameter.SHOW_ACTIVITY,
                                                true);
            planExportReport.setReportParameter(SWReportParameter.TIME_FRAME_DESCRIPTION,
                                                DateUtil.mmdyDate(DateUtil.parseDate(startDate))
                                                + " To "
                                                + DateUtil.mmdyDate(DateUtil.parseDate(endDate)));
            planExportReport.setReportParameter(SWReportParameter.BASE_URL,
                                                "."
                                                + System.getProperty("file.separator"));
            PDFReportRunner
                pdfReportRunner =
                new PDFReportRunner(planExportReport);
            pdfReportRunner.runToFile(request,
                                      reportFile);
            LOG.info("PlanPublishReport.execute() complete");
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
            addError(new ActionMessage("plan.export.failed",
                                       e.getMessage()), request);
            return null;
        }
        File
            zipFile =
            new File(outputDir,
                     "Plan"
                     + planValue.getId()
                     + ".zip");
        LOG.debug(zipFile
                  + ".zip");
        LOG.debug("parent="
                  + zipFile.getParent());
        LOG.debug("canonicalPath="
                  + zipFile.getCanonicalPath());
        LOG.debug("absolutePath="
                  + zipFile.getAbsolutePath());
        ZipOutputStream
            zos;
        zos =
            new ZipOutputStream(new FileOutputStream(zipFile));
        LOG.debug("reportFile.name="
                  + reportFile.getName());
        LOG.debug("reportFile.parent="
                  + reportFile.getParent());
        LOG.debug("reportFile.absolutePath="
                  + reportFile.getAbsolutePath());
        LOG.debug("reportFile.absoluteFile="
                  + reportFile.getAbsoluteFile());
        LOG.debug("reportFile.canonicalPath="
                  + reportFile.getCanonicalPath());
        LOG.debug("reportFile.canonicalFile="
                  + reportFile.getCanonicalFile());
        File
            parentDir =
            new File(reportFile.getParent());
        ZipEntry
            zipEntry =
            new ZipEntry(getZipEntryFileName(reportFile,
                                             parentDir.getCanonicalPath()));
        addZipEntry(reportFile,
                    zipEntry,
                    zos);
        LOG.debug("exportPlan(): casting strings to dates before hibernate query.");
        Date
            dStartDate =
            DateUtil.parseDate(startDate);
        Date
            dEndDate =
            DateUtil.parseDate(endDate);
        Object[][]
            parameters =
            {
                {
                    "planId",
                    planValue.getId()},
                {
                    "statusCode",
                    StatusCodeData.STATUS_CODE_ACTIVE},
                {
                    "startDate",
                    dStartDate},
                {
                    "endDate",
                    dEndDate}};
        List
            goalActivityFileLocationList =
            HibernateUtil.findByNamedQuery("com.sehinc.stormwater.db.plan.GoalActivityFileLocationData.byPlanId",
                                           parameters);
        if (goalActivityFileLocationList
            != null)
        {
            Iterator
                iter =
                goalActivityFileLocationList.iterator();
            while (iter.hasNext())
            {
                GoalActivityFileLocationData
                    fileLocation =
                    (GoalActivityFileLocationData) iter.next();
                File
                    aFile =
                    new File(fileLocation.getLocation(),
                             fileLocation.getName());
                zipEntry =
                    new ZipEntry(getZipEntryFileName(aFile,
                                                     outputDir.getCanonicalPath()));
                addZipEntry(aFile,
                            zipEntry,
                            zos);
            }
        }
        zos.close();
        return zipFile;
    }

    private void addZipEntry(File file, ZipEntry zipEntry, ZipOutputStream zos)
        throws IOException
    {
        byte[]
            buf =
            new byte[4096];
        int
            len;
        FileInputStream
            fin =
            new FileInputStream(file);
        BufferedInputStream
            in =
            new BufferedInputStream(fin);
        zos.putNextEntry(zipEntry);
        while ((
                   len =
                       in.read(buf))
               >= 0)
        {
            zos.write(buf,
                      0,
                      len);
        }
        in.close();
        zos.closeEntry();
    }

    private String getZipEntryFileName(File file, String root)
        throws IOException
    {
        if (file.getParent()
            != null)
        {
            String
                fileName;
            if (root
                != null)
            {
                fileName =
                    file.getCanonicalPath()
                        .substring(file.getCanonicalPath()
                                       .indexOf(root)
                                   + root.length());
            }
            else
            {
                fileName =
                    file.getCanonicalPath();
            }
            LOG.debug("fileName="
                      + fileName);
            return fileName;
        }
        return file.getName();
    }
}
