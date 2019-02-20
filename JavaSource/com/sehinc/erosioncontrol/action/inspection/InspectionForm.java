package com.sehinc.erosioncontrol.action.inspection;

import com.sehinc.common.action.base.BaseValidatorForm;
import com.sehinc.common.db.client.ClientData;
import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.value.inspection.InspectionActionValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionDocumentValue;
import com.sehinc.erosioncontrol.value.inspection.InspectionReasonValue;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;

import java.util.ArrayList;
import java.util.Date;

public class InspectionForm
    extends BaseValidatorForm
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionForm.class);
    private
    Integer
        id;
    private
    Integer
        projectId;
    private
    Integer
        clientId;
    private
    Date
        inspectionDate;
    private
    Integer
        hours;
    private
    Integer
        minutes;
    private
    String
        timePeriod;
    private
    String
        timeString;
    private
    Date
        enteredDate;
    private
    String
        weatherTrends;
    private
    String
        temperature;
    private
    String
        comment;
    private
    Date
        precipEndDate;
    private
    String
        precipAmount;
    private
    String
        precipSource;
    private
    String
        precipSourceOther;
    private
    String
        inspectionActionComment;
    private
    InspectionActionValue
        inspectionAction =
        new InspectionActionValue();
    private
    InspectionReasonValue
        inspectionReason =
        new InspectionReasonValue();
    private
    String
        statusCode;
    private
    boolean
        isWorkInProgress =
        false;
    private
    InspectionBmpList
        bmps =
        new InspectionBmpList();
    private
    InspectionDocumentList
        documents =
        new InspectionDocumentList();
    private
    String
        reportURL;
    private
    String
        submit;
    private
    boolean
        deleteFile;
    private
    ArrayList<InspectionDocumentValue>
        inspectionDocumentArray =
        new ArrayList<InspectionDocumentValue>();
    private
    CapContact
        inspector;
    private
    ClientData
        inspectorClient;
    private
    Integer
        selectedInspector;
    private
    String
        addContactFirstName;
    private
    String
        addContactLastName;
    private
    String
        addContactEmail;
    private
    String
        addContactAddress1;
    private
    String
        addContactAddress2;
    private
    String
        addContactCity;
    private
    String
        addContactZip;
    private
    String
        addContactPhone;
    private
    String
        addContactState;
    private
    Integer
        addClientId;

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id =
            id;
    }

    public Integer getProjectId()
    {
        return this.projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId =
            projectId;
    }

    public Integer getClientId()
    {
        return this.clientId;
    }

    public void setClientId(Integer clientId)
    {
        this.clientId =
            clientId;
    }

    public Date getInspectionDate()
    {
        return this.inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate)
    {
        this.inspectionDate =
            inspectionDate;
    }

    public String getInspectionDateString()
    {
        return DateUtil.mdyDate(this.inspectionDate);
    }

    public void setInspectionDateString(String inspectionDate)
    {
        this.inspectionDate =
            DateUtil.parseDate(inspectionDate);
    }

    public Integer getHours()
    {
        return hours;
    }

    public void setHours(Integer hours)
    {
        this.hours =
            hours;
    }

    public String getHourString()
    {
        if (hours
            != null)
        {
            if (hours
                < 10)
            {
                return "0"
                       + hours;
            }
            else
            {
                return hours.toString();
            }
        }
        else
        {
            return null;
        }
    }

    public void setHourString(String hour)
    {
        if (hour
            != null
            && hour.length()
               > 0)
        {
            this.hours =
                Integer.parseInt(hour);
        }
        else
        {
            this.hours =
                null;
        }
    }

    public Integer getMinutes()
    {
        return minutes;
    }

    public void setMinutes(Integer minutes)
    {
        this.minutes =
            minutes;
    }

    public String getMinuteString()
    {
        if (minutes
            != null)
        {
            if (minutes
                < 10)
            {
                return "0"
                       + minutes;
            }
            else
            {
                return minutes.toString();
            }
        }
        else
        {
            return null;
        }
    }

    public void setMinuteString(String minute)
    {
        if (minute
            != null
            && minute.length()
               > 0)
        {
            this.minutes =
                Integer.parseInt(minute);
        }
        else
        {
            this.minutes =
                null;
        }
    }

    public String getTimePeriod()
    {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod)
    {
        this.timePeriod =
            timePeriod;
    }

    public String getTimeString()
    {
        return timeString;
    }

    public void setTimeString(String timeString)
    {
        this.timeString =
            timeString;
    }

    public Date getEnteredDate()
    {
        return this.enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate =
            enteredDate;
    }

    public String getEnteredDateString()
    {
        return DateUtil.mdyDate(enteredDate);
    }

    public void setEnteredDateString(String enteredDate)
    {
        this.enteredDate =
            DateUtil.parseDate(enteredDate);
    }

    public String getWeatherTrends()
    {
        return this.weatherTrends;
    }

    public void setWeatherTrends(String weatherTrends)
    {
        this.weatherTrends =
            weatherTrends;
    }

    public String getTemperature()
    {
        return this.temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature =
            temperature;
    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment =
            comment;
    }

    public Date getPrecipEndDate()
    {
        return this.precipEndDate;
    }

    public void setPrecipEndDate(Date precipEndDate)
    {
        this.precipEndDate =
            precipEndDate;
    }

    public String getPrecipEndDateString()
    {
        return DateUtil.mdyDate(precipEndDate);
    }

    public void setPrecipEndDateString(String precipEndDate)
    {
        this.precipEndDate =
            DateUtil.parseDate(precipEndDate);
    }

    public String getPrecipAmount()
    {
        return this.precipAmount;
    }

    public void setPrecipAmount(String precipAmount)
    {
        this.precipAmount =
            precipAmount;
    }

    public String getPrecipSource()
    {
        return this.precipSource;
    }

    public void setPrecipSource(String precipSource)
    {
        this.precipSource =
            precipSource;
    }

    public String getPrecipSourceOther()
    {
        return this.precipSourceOther;
    }

    public void setPrecipSourceOther(String precipSourceOther)
    {
        this.precipSourceOther =
            precipSourceOther;
    }

    public String getInspectionActionComment()
    {
        return this.inspectionActionComment;
    }

    public void setInspectionActionComment(String inspectionActionComment)
    {
        this.inspectionActionComment =
            inspectionActionComment;
    }

    public InspectionActionValue getInspectionAction()
    {
        return this.inspectionAction;
    }

    public void setInspectionAction(InspectionActionValue inspectionAction)
    {
        this.inspectionAction =
            inspectionAction;
    }

    public InspectionReasonValue getInspectionReason()
    {
        return this.inspectionReason;
    }

    public void setInspectionReason(InspectionReasonValue inspectionReason)
    {
        this.inspectionReason =
            inspectionReason;
    }

    public final String getStatusCode()
    {
        return statusCode;
    }

    public final void setStatusCode(String statusCode)
    {
        this.statusCode =
            statusCode;
    }

    public void setStatus(String status)
    {
    }

    public String getStatus()
    {
        if (StatusCodeData.STATUS_CODE_ACTIVE
            .equals(statusCode))
        {
            return "Final";
        }
        else if (StatusCodeData.STATUS_CODE_INCOMPLETE
            .equals(statusCode))
        {
            return "Draft";
        }
        return "Unknown";
    }

    public String getStatusCodeName()
    {
        return StatusCodeData.getStatusCodeName(this.statusCode);
    }

    public void setStatusCodeName(String statusCodeName)
    {
    }

    public boolean getIsWorkInProgress()
    {
        return this.isWorkInProgress;
    }

    public void setIsWorkInProgress(boolean isWorkInProgress)
    {
        this.isWorkInProgress =
            isWorkInProgress;
    }

    public String getIsWorkInProgressText()
    {
        if (isWorkInProgress)
        {
            return "true";
        }
        return "false";
    }

    public void setIsWorkInProgressText(String isWorkInProgress)
    {
        if (isWorkInProgress
            != null
            && isWorkInProgress
               != ""
            && isWorkInProgress.equalsIgnoreCase("true"))
        {
            this.isWorkInProgress =
                true;
        }
        else
        {
            this.isWorkInProgress =
                false;
        }
    }

    public InspectionBmpList getBmps()
    {
        LOG.debug("In InspectionForm.getBmps()");
        return this.bmps;
    }

    public void setBmps(InspectionBmpList bmps)
    {
        LOG.debug("In InspectionForm.setBmps(InspectionBmpList)");
        this.bmps =
            bmps;
    }

    public InspectionDocumentList getDocuments()
    {
        LOG.debug("In InspectionForm.getDocuments()");
        return this.documents;
    }

    public void setDocuments(InspectionDocumentList documents)
    {
        LOG.debug("In InspectionForm.setDocuments(InspectionDocumentList)");
        this.documents =
            documents;
    }

    public String getReportURL()
    {
        return this.reportURL;
    }

    public void setReportURL(String reportURL)
    {
        this.reportURL =
            reportURL;
    }

    public String getSubmit()
    {
        return submit;
    }

    public void setSubmit(String submit)
    {
        this.submit =
            submit;
    }

    public boolean isDeleteFile()
    {
        return deleteFile;
    }

    public void setDeleteFile(boolean deleteFile)
    {
        this.deleteFile =
            deleteFile;
    }

    public void setInspectionDocumentArray(ArrayList<InspectionDocumentValue> inspectionDocumentArray)
    {
        this.inspectionDocumentArray =
            inspectionDocumentArray;
    }

    public ArrayList<InspectionDocumentValue> getInspectionDocumentArray()
    {
        return inspectionDocumentArray;
    }

    public void setWorkInProgress(boolean isWorkInProgress)
    {
        this.isWorkInProgress =
            isWorkInProgress;
    }

    public CapContact getInspector()
    {
        return inspector;
    }

    public void setInspector(CapContact inspector)
    {
        this.inspector =
            inspector;
    }

    public ClientData getInspectorClient()
    {
        return inspectorClient;
    }

    public void setInspectorClient(ClientData inspectorClient)
    {
        this.inspectorClient =
            inspectorClient;
    }

    public Integer getSelectedInspector()
    {
        return selectedInspector;
    }

    public void setSelectedInspector(Integer selectedInspector)
    {
        this.selectedInspector =
            selectedInspector;
    }

    public String getAddContactFirstName()
    {
        return addContactFirstName;
    }

    public void setAddContactFirstName(String addContactFirstName)
    {
        this.addContactFirstName =
            addContactFirstName;
    }

    public String getAddContactLastName()
    {
        return addContactLastName;
    }

    public void setAddContactLastName(String addContactLastName)
    {
        this.addContactLastName =
            addContactLastName;
    }

    public String getAddContactEmail()
    {
        return addContactEmail;
    }

    public void setAddContactEmail(String addContactEmail)
    {
        this.addContactEmail =
            addContactEmail;
    }

    public String getAddContactAddress1()
    {
        return addContactAddress1;
    }

    public void setAddContactAddress1(String addContactAddress1)
    {
        this.addContactAddress1 =
            addContactAddress1;
    }

    public String getAddContactAddress2()
    {
        return addContactAddress2;
    }

    public void setAddContactAddress2(String addContactAddress2)
    {
        this.addContactAddress2 =
            addContactAddress2;
    }

    public String getAddContactCity()
    {
        return addContactCity;
    }

    public void setAddContactCity(String addContactCity)
    {
        this.addContactCity =
            addContactCity;
    }

    public String getAddContactZip()
    {
        return addContactZip;
    }

    public void setAddContactZip(String addContactZip)
    {
        this.addContactZip =
            addContactZip;
    }

    public String getAddContactPhone()
    {
        return addContactPhone;
    }

    public void setAddContactPhone(String addContactPhone)
    {
        this.addContactPhone =
            addContactPhone;
    }

    public String getAddContactState()
    {
        return addContactState;
    }

    public void setAddContactState(String addContactState)
    {
        this.addContactState =
            addContactState;
    }

    public Integer getAddClientId()
    {
        return addClientId;
    }

    public void setAddClientId(Integer addClientId)
    {
        this.addClientId =
            addClientId;
    }

    public void reset()
    {
        this.id =
            null;
        this.projectId =
            null;
        this.clientId =
            null;
        this.inspectionDate =
            null;
        this.hours =
            null;
        this.minutes =
            null;
        this.timePeriod =
            null;
        this.timeString =
            null;
        this.enteredDate =
            null;
        this.weatherTrends =
            null;
        this.temperature =
            null;
        this.comment =
            null;
        this.precipEndDate =
            null;
        this.precipAmount =
            null;
        this.precipSource =
            null;
        this.precipSourceOther =
            null;
        this.inspectionActionComment =
            null;
        this.inspectionAction =
            new InspectionActionValue();
        this.inspectionReason =
            new InspectionReasonValue();
        this.statusCode =
            null;
        this.isWorkInProgress =
            false;
        this.statusCode =
            null;
        this.bmps =
            new InspectionBmpList();
        this.documents =
            new InspectionDocumentList();
        this.reportURL =
            null;
        this.submit =
            null;
        this.deleteFile =
            false;
        this.inspector =
            new CapContact();
        this.inspectorClient =
            new ClientData();
        this.selectedInspector =
            null;
        this.inspectionDocumentArray =
            new ArrayList<InspectionDocumentValue>();
        this.addContactFirstName =
            null;
        this.addContactLastName =
            null;
        this.addContactEmail =
            null;
        this.addContactAddress1 =
            null;
        this.addContactAddress2 =
            null;
        this.addContactCity =
            null;
        this.addContactZip =
            null;
        this.addContactPhone =
            null;
        this.addContactState =
            null;
        this.addClientId =
            null;
    }

    public void validateForm(ActionErrors errors)
    {
    }

    private
    FormFile
        formFile0;
    private
    FormFile
        formFile1;
    private
    FormFile
        formFile2;
    private
    FormFile
        formFile3;
    private
    FormFile
        formFile4;
    private
    FormFile
        formFile5;
    private
    FormFile
        formFile6;
    private
    FormFile
        formFile7;
    private
    FormFile
        formFile8;
    private
    FormFile
        formFile9;
    private
    FormFile
        formFile10;
    private
    FormFile
        formFile11;
    private
    FormFile
        formFile12;
    private
    FormFile
        formFile13;
    private
    FormFile
        formFile14;
    private
    FormFile
        formFile15;
    private
    FormFile
        formFile16;
    private
    FormFile
        formFile17;
    private
    FormFile
        formFile18;
    private
    FormFile
        formFile19;
    private
    FormFile
        formFile20;

    public FormFile getFormFile0()
    {
        return formFile0;
    }

    public void setFormFile0(FormFile formFile)
    {
        this.formFile0 =
            formFile;
    }

    public FormFile getFormFile1()
    {
        return formFile1;
    }

    public void setFormFile1(FormFile formFile)
    {
        this.formFile1 =
            formFile;
    }

    public FormFile getFormFile2()
    {
        return formFile2;
    }

    public void setFormFile2(FormFile formFile)
    {
        this.formFile2 =
            formFile;
    }

    public FormFile getFormFile3()
    {
        return formFile3;
    }

    public void setFormFile3(FormFile formFile)
    {
        this.formFile3 =
            formFile;
    }

    public FormFile getFormFile4()
    {
        return formFile4;
    }

    public void setFormFile4(FormFile formFile)
    {
        this.formFile4 =
            formFile;
    }

    public FormFile getFormFile5()
    {
        return formFile5;
    }

    public void setFormFile5(FormFile formFile)
    {
        this.formFile5 =
            formFile;
    }

    public FormFile getFormFile6()
    {
        return formFile6;
    }

    public void setFormFile6(FormFile formFile)
    {
        this.formFile6 =
            formFile;
    }

    public FormFile getFormFile7()
    {
        return formFile7;
    }

    public void setFormFile7(FormFile formFile)
    {
        this.formFile7 =
            formFile;
    }

    public FormFile getFormFile8()
    {
        return formFile8;
    }

    public void setFormFile8(FormFile formFile)
    {
        this.formFile8 =
            formFile;
    }

    public FormFile getFormFile9()
    {
        return formFile9;
    }

    public void setFormFile9(FormFile formFile)
    {
        this.formFile9 =
            formFile;
    }

    public FormFile getFormFile10()
    {
        return formFile10;
    }

    public void setFormFile10(FormFile formFile)
    {
        this.formFile10 =
            formFile;
    }

    public FormFile getFormFile11()
    {
        return formFile11;
    }

    public void setFormFile11(FormFile formFile)
    {
        this.formFile11 =
            formFile;
    }

    public FormFile getFormFile12()
    {
        return formFile12;
    }

    public void setFormFile12(FormFile formFile)
    {
        this.formFile12 =
            formFile;
    }

    public FormFile getFormFile13()
    {
        return formFile13;
    }

    public void setFormFile13(FormFile formFile)
    {
        this.formFile13 =
            formFile;
    }

    public FormFile getFormFile14()
    {
        return formFile14;
    }

    public void setFormFile14(FormFile formFile)
    {
        this.formFile14 =
            formFile;
    }

    public FormFile getFormFile15()
    {
        return formFile15;
    }

    public void setFormFile15(FormFile formFile)
    {
        this.formFile15 =
            formFile;
    }

    public FormFile getFormFile16()
    {
        return formFile16;
    }

    public void setFormFile16(FormFile formFile)
    {
        this.formFile16 =
            formFile;
    }

    public FormFile getFormFile17()
    {
        return formFile17;
    }

    public void setFormFile17(FormFile formFile)
    {
        this.formFile17 =
            formFile;
    }

    public FormFile getFormFile18()
    {
        return formFile18;
    }

    public void setFormFile18(FormFile formFile)
    {
        this.formFile18 =
            formFile;
    }

    public FormFile getFormFile19()
    {
        return formFile19;
    }

    public void setFormFile19(FormFile formFile)
    {
        this.formFile19 =
            formFile;
    }

    public FormFile getFormFile20()
    {
        return formFile20;
    }

    public void setFormFile20(FormFile formFile)
    {
        this.formFile20 =
            formFile;
    }

    public FormFile getFormFile(int index)
    {
        switch (index)
        {
            case 0:
                return formFile0;
            case 1:
                return formFile1;
            case 2:
                return formFile2;
            case 3:
                return formFile3;
            case 4:
                return formFile4;
            case 5:
                return formFile5;
            case 6:
                return formFile6;
            case 7:
                return formFile7;
            case 8:
                return formFile8;
            case 9:
                return formFile9;
            case 10:
                return formFile10;
            case 11:
                return formFile11;
            case 12:
                return formFile12;
            case 13:
                return formFile13;
            case 14:
                return formFile14;
            case 15:
                return formFile15;
            case 16:
                return formFile16;
            case 17:
                return formFile17;
            case 18:
                return formFile18;
            case 19:
                return formFile19;
            case 20:
                return formFile20;
        }
        return null;
    }
}
