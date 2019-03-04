package com.sehinc.erosioncontrol.value.inspection;

import com.sehinc.common.config.ApplicationProperties;
import com.sehinc.common.util.DateUtil;
import com.sehinc.common.util.crypto.CryptoException;
import com.sehinc.common.util.crypto.CryptoUtils;
import com.sehinc.common.value.client.ClientValue;
import com.sehinc.common.value.contact.ContactValue;
import com.sehinc.common.value.user.UserValue;
import com.sehinc.erosioncontrol.action.base.RequestKeys;
import com.sehinc.erosioncontrol.db.code.StatusCodeData;
import com.sehinc.erosioncontrol.db.inspection.EcInspection;
import com.sehinc.erosioncontrol.value.security.SecurityPermissionValue;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@SuppressWarnings(value = {
        "serial",
        "unused",
        "unchecked"})
public class InspectionListValue
    implements java.io.Serializable
{
    private static
    Logger
        LOG =
        Logger.getLogger(InspectionListValue.class);
    private
    Integer
        inspectionId;
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
    String
        inspectionReason;
    private
    String
        statusCode;
    private
    ContactValue
        inspector;
    private
    String
        reportUrl;
    private
    String
        inspectionFormUrl;
    private
    SecurityPermissionValue
        projectSecurityPermissionValue;
    private
    SecurityPermissionValue
        inspectionSecurityPermissionValue;
    private
    String
        bmpStatus;

    public InspectionListValue()
    {
    }

    public InspectionListValue(EcInspection inspection)
    {
        this.inspectionId =
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
            inspection.getInspectionReason();
        if (inspection.isFailed() == 1)
        {
            bmpStatus =
                "Failed";
        }
        else if (inspection.isFailed() == 2)
        {
            bmpStatus =
                "Warning";
        }
        else
        {
            bmpStatus =
                "Passed";
        }
        this.statusCode =
            inspection.getStatus()
                .getCode();
        try
        {
            this.inspector =
                new ContactValue(inspection.getInspector()
                                     .getId());
        }
        catch (Exception e)
        {
            LOG.error("Error creating inspection given the contact id ["
                      + inspection.getInspector()
                .getId()
                      + "].  <br>Message:<br>"
                      + e.getMessage());
        }
    }

    public Integer getInspectionId()
    {
        return this.inspectionId;
    }

    public void setInspectionId(Integer inspectionId)
    {
        this.inspectionId =
            inspectionId;
    }

    public String getBmpStatus()
    {
        return bmpStatus;
    }

    public void setBmpStatus(String bmpStatus)
    {
        this.bmpStatus =
            bmpStatus;
    }

    public String getBmpStatusImage()
    {
        if (bmpStatus.equals("Passed"))
        {
            return "/sehsvc/img/icons/sehinspect.png";
        }
        else if (bmpStatus.equals("Failed"))
        {
            return "/sehsvc/img/icons/sehdelete.png";
        }
        else if (bmpStatus.equals("Warning"))
        {
            return "/sehsvc/img/icons/sehwarning.png";
        }
        else
        {
            return "";
        }
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

    public String getInspectionReason()
    {
        return this.inspectionReason;
    }

    public String getInspectionReasonString()
    {
        return this.inspectionReason;
    }

    public void setInspectionReason(String inspectionReason)
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

    public ContactValue getInspector()
    {
        return this.inspector;
    }

    public String getInspectorNameString()
    {
        return this.inspector
                   .getFirstName()
               + " "
               + this.inspector
            .getLastName();
    }

    public String getInspectorPhoneNumber()
    {
        return this.inspector
            .getPrimaryPhone();
    }

    public String getInspectorEmailAddress()
    {
        return this.inspector
            .getEmail();
    }

    public void setInspector(ContactValue inspector)
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

    public void setProjectSecurityPermissionValue(SecurityPermissionValue projectSecurityPermissionValue)
    {
        this.projectSecurityPermissionValue =
            projectSecurityPermissionValue;
    }

    public SecurityPermissionValue getProjectSecurityPermissionValue()
    {
        if (projectSecurityPermissionValue
            == null)
        {
            projectSecurityPermissionValue =
                new SecurityPermissionValue();
        }
        return projectSecurityPermissionValue;
    }

    public void setInspectionSecurityPermissionValue(SecurityPermissionValue inspectionSecurityPermissionValue)
    {
        this.inspectionSecurityPermissionValue =
            inspectionSecurityPermissionValue;
    }

    public SecurityPermissionValue getInspectionSecurityPermissionValue()
    {
        if (inspectionSecurityPermissionValue
            == null)
        {
            inspectionSecurityPermissionValue =
                new SecurityPermissionValue();
        }
        return inspectionSecurityPermissionValue;
    }

    public String getReportUrl()
    {
        return this.reportUrl;
    }

    public void setReportUrl(String reportUrl)
    {
        this.reportUrl =
            reportUrl;
    }

    public void setReportUrl(ClientValue clientValue, UserValue userValue, HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientValue.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getProjectId().toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_INSPECTION_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getInspectionId().toString()),
                                              "UTF-8"));
            LOG.debug("Generating Inspection Report url for inspectionId: "
                      + getInspectionId());
            setReportUrl(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Inspection Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Inspection Report URL",
                      uee);
        }
    }

    public String getInspectionFormUrl()
    {
        return this.inspectionFormUrl;
    }

    public void setInspectionFormUrl(String inspectionFormUrl)
    {
        this.inspectionFormUrl =
            inspectionFormUrl;
    }

    public void setInspectionFormUrl(ClientValue clientValue, UserValue userValue, HttpServletRequest request)
    {
        StringBuffer
            buffer =
            new StringBuffer();
        String
            username =
            "";
        if (userValue
            != null)
        {
            username =
                userValue.getUsername();
        }
        try
        {
            buffer.append(ApplicationProperties.getBaseURL(request));
            buffer.append(ApplicationProperties.getProperty("base.webapp.context")
                          + ApplicationProperties.getProperty("erosioncontrol.public.report.servlet"));
            buffer.append("?"
                          + RequestKeys.ACTION_PARAMETER
                          + "="
                          + URLEncoder.encode(RequestKeys.EC_INSPECTION_FORM_REPORT_SECURE_ACTION,
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_MAP_CLIENT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  clientValue.getId()
                                                                      .toString()),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.USERNAME
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  username),
                                              "UTF-8"));
            buffer.append("&"
                          + RequestKeys.EC_PROJECT_ID
                          + "="
                          + URLEncoder.encode(CryptoUtils.encrypt(CryptoUtils.getDefaultKey(),
                                                                  getProjectId().toString()),
                                              "UTF-8"));
            LOG.debug("Generating Inspection Form Report url for projectId: "
                      + getProjectId());
            setInspectionFormUrl(buffer.toString());
        }
        catch (CryptoException ce)
        {
            LOG.error("Error generating Project Report URL",
                      ce);
        }
        catch (UnsupportedEncodingException uee)
        {
            LOG.error("Error generating Project Report URL",
                      uee);
        }
    }

    public String toString()
    {
        StringBuffer
            buffer =
            new StringBuffer();
        buffer.append("\nId="
                      + inspectionId);
        buffer.append("\nprojectId="
                      + projectId);
        buffer.append("\nclientId="
                      + clientId);
        buffer.append("\ninspectionDate="
                      + inspectionDate);
        buffer.append("\nenteredDate="
                      + enteredDate);
        buffer.append("\nweatherTrends="
                      + weatherTrends);
        buffer.append("\ntemperature="
                      + temperature);
        buffer.append("\ncomment="
                      + comment);
        buffer.append("\nprecipEndDate="
                      + precipEndDate);
        buffer.append("\nprecipAmount="
                      + precipAmount);
        buffer.append("\nprecipSource="
                      + precipSource);
        buffer.append("\ninspectionActionComment="
                      + inspectionActionComment);
        buffer.append("\ninspector="
                      + inspector);
        buffer.append("\nstatusCode="
                      + statusCode);
        buffer.append("\n\n");
        return buffer.toString();
    }
}
