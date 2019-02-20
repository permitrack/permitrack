package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.db.contact.CapContact;
import com.sehinc.common.util.DateUtil;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import org.apache.log4j.Logger;

import java.util.Date;

public class InspectionValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionValue.class);
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
        inspectionActionComment;
    private
    InspectionActionValue
        inspectionAction;
    private
    InspectionReasonValue
        inspectionReason;
    private
    String
        statusCode;
    private
    CapContact
        inspector;
    private
    SecurityPermissionValue
        inspectionSecurityPermissionValue;

    public InspectionValue()
    {
    }

    public InspectionValue(EcInspection inspection)
    {
        this.id =
            inspection.getId();
        this.projectId =
            inspection.getProjectId();
        this.clientId =
            inspection.getClientId();
        this.inspectionDate =
            inspection.getInspectionDate();
        this.enteredDate =
            inspection.getEnteredDate();
        this.weatherTrends =
            inspection.getWeatherTrends();
        this.temperature =
            inspection.getTemperature();
        this.comment =
            inspection.getComment();
        this.precipEndDate =
            inspection.getPrecipEndDate();
        this.precipAmount =
            inspection.getPrecipAmount();
        this.precipSource =
            inspection.getPrecipSource();
        this.inspectionActionComment =
            inspection.getInspectionActionComment();
        this.inspectionAction =
            new InspectionActionValue(inspection.getInspectionAction());
        this.inspectionReason =
            new InspectionReasonValue(inspection.getInspectionReason());
        this.statusCode =
            inspection.getStatus()
                .getCode();
        this.inspector =
            CapContact.getActiveById(inspection.getInspector()
                                         .getId());
    }

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
        return DateUtil.mdyDate(inspectionDate);
    }

    public void setInspectionDateString(String inspectionDate)
    {
        this.inspectionDate =
            DateUtil.parseDate(inspectionDate);
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

    public String getInspectionActionString()
    {
        return this.inspectionAction
            .getDescription();
    }

    public InspectionReasonValue getInspectionReason()
    {
        return this.inspectionReason;
    }

    public String getInspectionReasonString()
    {
        return this.inspectionReason
            .getDescription();
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

    public CapContact getInspector()
    {
        return this.inspector;
    }

    public String getInspectorNameString()
    {
        String
            completeName =
            this.inspector
                .getFirstName()
            + " "
            + this.inspector
                .getLastName();
        return completeName;
    }

    public String getInspectorPhoneNumber()
    {
        return this.inspector
            .getPrimaryPhone();
    }

    public String getInspectorEmailAddress()
    {
        String
            emailString =
            this.inspector
                .getEmail();
        return emailString;
    }

    public void setInspector(CapContact inspector)
    {
        this.inspector =
            inspector;
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

    public SecurityPermissionValue getInspectionSecurityPermissionValue()
    {
        return inspectionSecurityPermissionValue;
    }

    public void setInspectionSecurityPermissionValue(SecurityPermissionValue inspectionSecurityPermissionValue)
    {
        this.inspectionSecurityPermissionValue =
            inspectionSecurityPermissionValue;
    }
}